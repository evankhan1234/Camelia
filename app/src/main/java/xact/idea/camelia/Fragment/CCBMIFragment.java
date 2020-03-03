package xact.idea.camelia.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Database.Model.MeasurementDetails;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.HouseHoldFragment.HHMyselfFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;

import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.rounded;


public class CCBMIFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    Button create;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_time;
    @SuppressLint("StaticFieldLeak")
    static  EditText edit_date;
    EditText edit_height;
    EditText edit_weight;
    TextView text_bmi_number;
    TextView text_bmi_text;
    LinearLayout linear;
    private Calendar calendar;
    String type;
    String message;
    double bmi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ccbmi, container, false);

        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("Type", "");
            Log.e("UniqueId","uniquKey"+type);
        }
        initView();
        // display();
        return view;
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
    private void initView() {
        linear = view.findViewById(R.id.linear);
        text_bmi_number = view.findViewById(R.id.text_bmi_number);
        text_bmi_text = view.findViewById(R.id.text_bmi_text);
        create = view.findViewById(R.id.create);
        edit_time = view.findViewById(R.id.edit_time);
        edit_date = view.findViewById(R.id.edit_date);
        edit_height = view.findViewById(R.id.edit_height);
        edit_weight = view.findViewById(R.id.edit_weight);
        calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date(System.currentTimeMillis());
        edit_date.setText(formatter.format(date));
        //edit_end_date.setText(formatter.format(date));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        String format = "";
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        String value="";
        String value1="";
        int length = String.valueOf(min).length();
        int length1 = String.valueOf(hour).length();
        if (length>1){
            value=String.valueOf(min);
        }
        else{
            value="0"+String.valueOf(min);
        }
        if (length1>1){
            value1=String.valueOf(hour);
        }
        else{
            value1="0"+String.valueOf(hour);
        }
        edit_time.setText(value1+":"+value+" "+format);
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerDeadFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        edit_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TimePickerFragment();

                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNullOrEmpty(edit_height.getText().toString()) && isNullOrEmpty(edit_weight.getText().toString())){
                    Toast.makeText(mActivity, "Please insert all field", Toast.LENGTH_SHORT).show();

                }else {
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // Hide the soft keyboard
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                    Measurements measurements = new Measurements();
                    measurements.DateTime=date;
                    measurements.MemberIds=type;
                    measurements.Type="BMI";
                    measurements.Message=message;
                    measurements.Result=bmi;
                    measurements.Refer="";
                    Common.measurementsRepository.insertToMeasurements(measurements);
                    int memberId= Common.measurementsRepository.maxValue();
                    MeasurementDetails measurementDetails= new MeasurementDetails();
                    measurementDetails.DateTime=date;
                    measurementDetails.MeasurementId=memberId;
                    measurementDetails.Name="Weight";
                    measurementDetails.Result= Double.parseDouble(edit_weight.getText().toString());
                    Common.measurementDetailsRepository.insertToMeasurements(measurementDetails);

                    MeasurementDetails measurementDetails1= new MeasurementDetails();
                    measurementDetails.DateTime=date;
                    measurementDetails.MeasurementId=memberId;
                    measurementDetails.Name="Height";
                    measurementDetails.Result= Double.parseDouble(edit_height.getText().toString());
                    Common.measurementDetailsRepository.insertToMeasurements(measurementDetails1);
                    ((CCUserHomeActivity) getActivity()).backForDetails();

                }


            }
        });
        edit_height.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {

                try {
                    if (!edit_weight.getText().toString().equals(""))
                        linear.setVisibility(View.VISIBLE);
                    double weight=Double.parseDouble(edit_weight.getText().toString());
                    double height=Double.parseDouble(s.toString());
                     bmi = weight / Math.pow(height, 2.0);
                    linear.setVisibility(View.VISIBLE);
                    if(bmi<18.5){
                        linear.setBackground(mActivity.getDrawable(R.drawable.backgound_brown));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("UnderWeight");
                        message="UnderWeight";
                    }
                    else if(bmi>=18.5 && bmi<=24.99){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_green));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("Normal");
                        message="Normal";
                    }
                    else if(bmi>=25 && bmi<=29.99){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_yellow));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("OverWeight");
                        message="OverWeight";
                    }
                    else if(bmi>=30){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_red));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("Obese");
                        message="Obese";
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    linear.setVisibility(View.GONE);
                    message="";
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }


            }
        });

        edit_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {

                try {
                    double height=Double.parseDouble(edit_height.getText().toString());
                    double weight=Double.parseDouble(s.toString());
                    double bmi = weight / Math.pow(height, 2.0);
                   // linear.setVisibility(View.VISIBLE);
                    if(bmi<=18.5){
                        linear.setBackground(mActivity.getDrawable(R.drawable.backgound_brown));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("UnderWeight");
                        message="UnderWeight";
                    }
                    else if(bmi>18.5 && bmi<=24.99){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_green));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("Normal");
                        message="UnderWeight";
                    }
                    else if(bmi>25 && bmi<=29.99){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_yellow));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("OverWeight");
                        message="OverWeight";
                    }
                    else if(bmi>24.99){
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_red));
                        text_bmi_number.setText(rounded(bmi,2)+"(BMI)");
                        Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                                R.anim.blink);
                        text_bmi_text.startAnimation(animBlink);
                        text_bmi_text.setText("Obese");
                        message="Obese";
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                   // linear.setVisibility(View.GONE);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }


            }
        });



    }


    public static class DatePickerDeadFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                calendar.setTime(sdf.parse(edit_date.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);

            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar today = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(chosenDate);
            edit_date.setText(formattedDate);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String format = "";
            if (hourOfDay == 0) {
                hourOfDay += 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                hourOfDay -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            int length = String.valueOf(minute).length();
            int length1 = String.valueOf(hourOfDay).length();
            String value="";
            String value1="";
            if (length>1){
                value=String.valueOf(minute);
            }
            else{
                value="0"+String.valueOf(minute);
            }
            if (length1>1){
                value1=String.valueOf(hourOfDay);
            }
            else{
                value1="0"+String.valueOf(hourOfDay);
            }
            edit_time.setText(value1+":"+value+" "+format);
        }

    }
}

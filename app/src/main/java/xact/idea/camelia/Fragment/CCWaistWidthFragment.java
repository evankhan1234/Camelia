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
import android.widget.RadioButton;
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
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.rounded;


public class CCWaistWidthFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    LinearLayout linear;
    Button create;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_time;
    @SuppressLint("StaticFieldLeak")
    static  EditText edit_date;
    EditText edit_reading1;
    EditText edit_reading2;
    TextView text_number;
    TextView text_text;
    private Calendar calendar;

    String type;
    double total;
    String message;
    String refer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_ccwaist_width, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
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

    private void initView() {
        edit_date = view.findViewById(R.id.edit_date);
        edit_time = view.findViewById(R.id.edit_time);
        linear = view.findViewById(R.id.linear);
        edit_reading1 = view.findViewById(R.id.edit_reading1);
        edit_reading2 = view.findViewById(R.id.edit_reading2);
        text_number = view.findViewById(R.id.text_number);
        text_text = view.findViewById(R.id.text_text);
        create = view.findViewById(R.id.create);
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
                if (isNullOrEmpty(edit_reading1.getText().toString()) && isNullOrEmpty(edit_reading2.getText().toString())){
                    Toast.makeText(mActivity, "Please insert all field", Toast.LENGTH_SHORT).show();

                }else {
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // Hide the soft keyboard
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    final Date date = new Date(System.currentTimeMillis());
                    Date date1 = null;
                    String currentDate = formatter.format(date);
                    try {
                        date1 = new SimpleDateFormat("dd-MM-yyyy").parse(currentDate);
                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Measurements measurements = new Measurements();
                    measurements.DateTime=date1;
                    measurements.MemberIds=type;
                    measurements.Message=message;
                    measurements.Result=total;
                    measurements.Type="Diastolic";
                    measurements.Refer=refer;
                    Common.measurementsRepository.insertToMeasurements(measurements);
                    int memberId= Common.measurementsRepository.maxValue();
                    MeasurementDetails measurementDetails= new MeasurementDetails();
                    measurementDetails.DateTime=date1;
                    measurementDetails.MeasurementId=memberId;
                    measurementDetails.Name="Diastolic Reading1";
                    measurementDetails.Result= Double.parseDouble(edit_reading1.getText().toString());
                    Common.measurementDetailsRepository.insertToMeasurements(measurementDetails);
                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                    MeasurementDetails measurementDetails1= new MeasurementDetails();
                    measurementDetails.DateTime=date1;
                    measurementDetails.MeasurementId=memberId;
                    measurementDetails.Name="Diastolic Reading2";
                    measurementDetails.Result= Double.parseDouble(edit_reading2.getText().toString());
                    Common.measurementDetailsRepository.insertToMeasurements(measurementDetails1);

                    ((CCUserHomeActivity) getActivity()).backForDetails();
                }

            }
        });
        edit_reading1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().equals(""))
                    linear.setVisibility(View.VISIBLE);
                try {
                    double e1=Double.parseDouble(editable.toString());
                    double e2=Double.parseDouble(edit_reading2.getText().toString());
                     total=(e1+e2)/2;

                    text_number.setText(rounded(total,2)+"(mmol/L)");


                    Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                            R.anim.blink);
                    text_text.startAnimation(animBlink);


                    int DiabetisYesNo=0;
                    Questions questions1 = Common.qustionsRepository.getQuestions("Q50", type);
                    if (questions1!=null){
                        DiabetisYesNo= Integer.parseInt(questions1.answer);
                        if ( (DiabetisYesNo == 1) && (total >= 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            text_text.setText("Hypertension = Refer to UHC!");
                            message="Hypertension = Refer to UHC!";
                            refer="UHC";

                        } else if ( (DiabetisYesNo == 1) && (total < 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            text_text.setText("Normal = Follow up 6 months.");
                        } else if ( (DiabetisYesNo == 2) && (total >= 90)  ) {
                            message="Hypertension = Refer to UHC!";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            refer="UHC";
                            text_text.setText("Hypertension = Refer to UHC!");
                        } else if ( (DiabetisYesNo == 2) &&  (total < 90) ) {
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            text_text.setText("Normal = Follow up 6 months.");
                        } else {
                            message="";
                            refer="";
                            text_text.setText("");
                        }
                    }
                    else{
                        DiabetisYesNo=2;
                        if ( (DiabetisYesNo == 1) && (total >= 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            text_text.setText("Hypertension = Refer to UHC!");
                            message="Hypertension = Refer to UHC!";
                            refer="UHC";

                        } else if ( (DiabetisYesNo == 1) && (total < 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            text_text.setText("Normal = Follow up 6 months.");
                        } else if ( (DiabetisYesNo == 2) && (total >= 90)  ) {
                            message="Hypertension = Refer to UHC!";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            refer="UHC";
                            text_text.setText("Hypertension = Refer to UHC!");
                        } else if ( (DiabetisYesNo == 2) &&  (total < 90) ) {
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            text_text.setText("Normal = Follow up 6 months.");
                        } else {
                            message="";
                            refer="";
                            text_text.setText("");
                        }
                    }


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    message="";
                    refer="";
                    linear.setVisibility(View.GONE);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        edit_reading2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().equals(""))
                    linear.setVisibility(View.VISIBLE);
                try {
                    double e2=Double.parseDouble(editable.toString());
                    double e1=Double.parseDouble(edit_reading1.getText().toString());
                     total=(e1+e2)/2;

                    text_number.setText(rounded(total,2)+"(mmol/L)");


                    Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                            R.anim.blink);
                    text_text.startAnimation(animBlink);


                    int DiabetisYesNo=0;
                    Questions questions1 = Common.qustionsRepository.getQuestions("Q50", type);
                    if (questions1!=null){
                        DiabetisYesNo= Integer.parseInt(questions1.answer);
                        if ( (DiabetisYesNo == 1) && (total >= 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            text_text.setText("Hypertension = Refer to UHC!");
                            message="Hypertension = Refer to UHC!";
                            refer="UHC";

                        } else if ( (DiabetisYesNo == 1) && (total < 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            text_text.setText("Normal = Follow up 6 months.");
                        } else if ( (DiabetisYesNo == 2) && (total >= 90)  ) {
                            message="Hypertension = Refer to UHC!";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            refer="UHC";
                            text_text.setText("Hypertension = Refer to UHC!");
                        } else if ( (DiabetisYesNo == 2) &&  (total < 90) ) {
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            text_text.setText("Normal = Follow up 6 months.");
                        } else {
                            message="";
                            refer="";
                            text_text.setText("");
                        }
                    }
                    else{
                        DiabetisYesNo= 2;
                        if ( (DiabetisYesNo == 1) && (total >= 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            text_text.setText("Hypertension = Refer to UHC!");
                            message="Hypertension = Refer to UHC!";
                            refer="UHC";

                        } else if ( (DiabetisYesNo == 1) && (total < 80) ) {
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            text_text.setText("Normal = Follow up 6 months.");
                        } else if ( (DiabetisYesNo == 2) && (total >= 90)  ) {
                            message="Hypertension = Refer to UHC!";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_red));
                            refer="UHC";
                            text_text.setText("Hypertension = Refer to UHC!");
                        } else if ( (DiabetisYesNo == 2) &&  (total < 90) ) {
                            message="Normal = Follow up 6 months.";
                            refer="Follow";
                            linear.setBackground(mActivity.getResources().getDrawable(R.drawable.background_green));
                            text_text.setText("Normal = Follow up 6 months.");
                        } else {
                            message="";
                            refer="";
                            text_text.setText("");
                        }
                    }


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    linear.setVisibility(View.GONE);
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

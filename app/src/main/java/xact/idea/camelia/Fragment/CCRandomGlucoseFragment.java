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
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;

import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.rounded;


public class CCRandomGlucoseFragment extends Fragment {

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    LinearLayout linear;
    LinearLayout linear1;
    Button create;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_time;
    @SuppressLint("StaticFieldLeak")
    static  EditText edit_date;
    EditText edit_sugar;
    TextView text_number;
    TextView textView49;
    TextView text_text;
    private Calendar calendar;
    RadioButton radioFasting;
    RadioButton radioRandom;
    String type;
    String message;
    String refer;
    String typeGlucose;
    double total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_ccrandom_glucose, container, false);
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
        textView49 = view.findViewById(R.id.textView49);
        radioFasting = view.findViewById(R.id.radioFasting);
        radioRandom = view.findViewById(R.id.radioRandom);
        linear1 = view.findViewById(R.id.linear1);
        edit_date = view.findViewById(R.id.edit_date);
        edit_time = view.findViewById(R.id.edit_time);
        linear = view.findViewById(R.id.linear);
        edit_sugar = view.findViewById(R.id.edit_sugar);
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
        radioFasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               linear1.setVisibility(View.VISIBLE);
                textView49.setText("Fasting Reading");
                typeGlucose="F";
            }
        });
        radioRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear1.setVisibility(View.VISIBLE);
                textView49.setText("Random Reading");
                typeGlucose="R";
            }
        });
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
                if (isNullOrEmpty(edit_sugar.getText().toString())){
                    Toast.makeText(mActivity, "Please insert all field", Toast.LENGTH_SHORT).show();

                }else {
                    Measurements measurements = new Measurements();
                    measurements.DateTime=date;
                    measurements.MemberId=type;
                    measurements.Message=message;
                    measurements.Result=total;
                    measurements.Refer=refer;
                    measurements.Type="Diabetes";
                    Common.measurementsRepository.insertToMeasurements(measurements);
                    int memberId= Common.measurementsRepository.maxValue();
                    MeasurementDetails measurementDetails= new MeasurementDetails();
                    measurementDetails.DateTime=date;
                    measurementDetails.MeasurementId=memberId;
                    if (typeGlucose.equals("F")){
                        measurementDetails.Name="Fasting";
                    }
                    else if (typeGlucose.equals("R")){
                        measurementDetails.Name="Random";
                    }

                    measurementDetails.Result= Double.parseDouble(edit_sugar.getText().toString());
                    Common.measurementDetailsRepository.insertToMeasurements(measurementDetails);
                    ((CCUserHomeActivity) getActivity()).backForDetails();
                }



            }
        });
        edit_sugar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {
                double bg_diabetes_fasting=0;
                double bg_diabetes_random_1=0;
                if (!editable.toString().equals(""))
                    linear.setVisibility(View.VISIBLE);
                try {
                     total=Double.parseDouble(editable.toString());

                    text_number.setText(rounded(total,2)+"(mmol/L)");
                    if (typeGlucose.equals("F")){
                        bg_diabetes_fasting=Double.parseDouble(editable.toString());
                    }
                    else if (typeGlucose.equals("R")){
                        bg_diabetes_random_1=Double.parseDouble(editable.toString());
                    }

                    Animation animBlink = AnimationUtils.loadAnimation(mActivity,
                            R.anim.blink);
                    text_text.startAnimation(animBlink);


                    MemberMedicine memberMedicine= Common.memberMedicineRepository.getMemberMedicineNo(type);
                    if ( (memberMedicine.DiabetisYesNo == 1) && ((bg_diabetes_fasting > 8) || (bg_diabetes_random_1 > 10)) ) {
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_red));
                        text_text.setText("Uncontrolled Diabetes = Refer to UHC!");
                        refer="UHC";
                        message="Uncontrolled Diabetes = Refer to UHC!";
                    } else if ( (memberMedicine.DiabetisYesNo == 1) && ((bg_diabetes_fasting <= 8) || (bg_diabetes_random_1 <= 10)) ) {
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_green));
                        text_text.setText("Controlled Diabetes = Follow up 6 months.");
                        refer="";
                        message="Controlled Diabetes = Follow up 6 months.";
                    } else if ( (memberMedicine.DiabetisYesNo == 2) && (((bg_diabetes_fasting >= 6.1) && (bg_diabetes_fasting <= 6.9)) || ((bg_diabetes_random_1 >= 8.1) && (bg_diabetes_random_1 <= 11))) ) {
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_green));
                        text_text.setText("Pre-diabetic = Next week follow-up");
                        refer="";
                        message="Pre-diabetic = Next week follow-up";
                    } else if ( (memberMedicine.DiabetisYesNo == 2) && ((bg_diabetes_fasting >= 7) || (bg_diabetes_random_1 >= 11.1)) ) {
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_red));
                        text_text.setText("Diabetes = Refer to UHC!");
                        refer="UHC";
                        message="Diabetes = Refer to UHC!";
                    } else if ( (memberMedicine.DiabetisYesNo == 2) && ((bg_diabetes_fasting < 6.1) || (bg_diabetes_random_1 < 8)) ) {
                        linear.setBackground(mActivity.getDrawable(R.drawable.background_green));
                        text_text.setText("Normal");
                        refer="";
                        message="Normal";
                    } else {
                        refer="";
                        message="";
                        text_text.setText("");
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    refer="";
                    message="";
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

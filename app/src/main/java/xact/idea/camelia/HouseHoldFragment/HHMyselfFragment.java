package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.Utils;


public class HHMyselfFragment extends Fragment implements Handler.Callback{

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public  static Handler handler;
    EditText edit_date_of_death;
    EditText edit_national_id;
    EditText edit_mobile_number;
    EditText edit_name;
    static   EditText edit_birthday_date;
    static EditText edit_age;
    Spinner spinner_sex;
    Spinner spinner_religion;
    Spinner spinner_education;
    Spinner spinner_martial_status;
    Spinner spinner_occupation;
    Spinner spinner_blood_group;
    Spinner spinner_living_status;
    LinearLayout linear_edit_death;

    ArrayAdapter<SexModel> sexArrayAdapter;
    ArrayAdapter<ReligionModel> religionArrayAdapter;
    ArrayAdapter<OccupationModel> occupationArrayAdapter;
    ArrayAdapter<EducationModel> educationArrayAdapter;
    ArrayAdapter<BloodGroupModel> bloodGroupArrayAdapter;
    ArrayAdapter<LivingStatusModel> livingGroupArrayAdapter;
    ArrayAdapter<MaritialStatusModel> maritalArrayAdapter;
    ArrayList<SexModel> sexArrayList= new ArrayList<>();
    ArrayList<ReligionModel> religionArrayList= new ArrayList<>();
    ArrayList<OccupationModel> occupationArrayList= new ArrayList<>();
    ArrayList<EducationModel> educationArrayList= new ArrayList<>();
    ArrayList<BloodGroupModel> bloodGroupArrayList= new ArrayList<>();
    ArrayList<LivingStatusModel> livingGroupArrayList= new ArrayList<>();
    ArrayList<MaritialStatusModel> maritalArrayList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhmyself, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        handler = new Handler(this);
        initView();
        // display();
        return view;
    }

    private void initView() {
        sexArrayList=Utils.getSexList();
        religionArrayList=Utils.getReligionList();
        occupationArrayList=Utils.getOccupationList();
        educationArrayList=Utils.getEducationList();
        bloodGroupArrayList=Utils.getBloodGroupList();
        livingGroupArrayList=Utils.getLivingStatusList();
        maritalArrayList=Utils.getMaritialStatusList();
        linear_edit_death=view.findViewById(R.id.linear_edit_death);
        edit_date_of_death=view.findViewById(R.id.edit_date_of_death);
        edit_national_id=view.findViewById(R.id.edit_national_id);
        edit_mobile_number=view.findViewById(R.id.edit_mobile_number);
        edit_name=view.findViewById(R.id.edit_name);
        edit_birthday_date=view.findViewById(R.id.edit_birthday_date);
        edit_age=view.findViewById(R.id.edit_age);
        spinner_sex=view.findViewById(R.id.spinner_sex);
        spinner_religion=view.findViewById(R.id.spinner_religion);
        spinner_education=view.findViewById(R.id.spinner_education);
        spinner_martial_status=view.findViewById(R.id.spinner_martial_status);
        spinner_occupation=view.findViewById(R.id.spinner_occupation);
        spinner_blood_group=view.findViewById(R.id.spinner_blood_group);
        spinner_living_status=view.findViewById(R.id.spinner_living_status);

        sexArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, sexArrayList);
        sexArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sex.setAdapter(sexArrayAdapter);
        religionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, religionArrayList);
        religionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_religion.setAdapter(religionArrayAdapter);

        occupationArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, occupationArrayList);
        occupationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_occupation.setAdapter(occupationArrayAdapter);
        educationArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, educationArrayList);
        educationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_education.setAdapter(educationArrayAdapter);


        bloodGroupArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, bloodGroupArrayList);
        bloodGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood_group.setAdapter(bloodGroupArrayAdapter);
        livingGroupArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, livingGroupArrayList);
        livingGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_living_status.setAdapter(livingGroupArrayAdapter);


        maritalArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, maritalArrayList);
        maritalArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_martial_status.setAdapter(maritalArrayAdapter);
        edit_birthday_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFromFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        spinner_living_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + livingGroupArrayList.get(position).getId());
                if (livingGroupArrayList.get(position).getId()==1){
                    linear_edit_death.setVisibility(View.VISIBLE);
                }
                else {
                    linear_edit_death.setVisibility(View.GONE);
                    edit_date_of_death.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private boolean isChecked() {
        if (Utils.isEmpty(edit_birthday_date.getText().toString())) {
            Toast.makeText(mActivity, "Please fill birthday date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Utils.isEmpty(edit_national_id.getText().toString())) {
            Toast.makeText(mActivity, "Please fill national id", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Utils.isEmpty(edit_mobile_number.getText().toString())) {
            Toast.makeText(mActivity, "Please fill Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
    private void saveData(){

        if (isChecked()){
            HHCreateMemberFragment.nextPage(1);
            HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.what==0)
        {
            saveData();
        }
        return false;
    }
    public static class DatePickerFromFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                calendar.setTime(sdf.parse(edit_birthday_date.getText().toString()));
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
            EditText startTime2 = (EditText) getActivity().findViewById(R.id.edit_birthday_date);
            startTime2.setText(formattedDate);

            int age = today.get(Calendar.YEAR) - cal.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)){
                age--;
            }

            Integer ageInt = new Integer(age);
            String ageS = ageInt.toString();
            edit_age.setText(ageS);
        }
    }
}

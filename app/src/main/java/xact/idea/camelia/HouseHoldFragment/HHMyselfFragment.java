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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Occupation;
import xact.idea.camelia.Database.Model.StudyClass;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Ward;
import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.hideSoftKeyboard;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;


public class HHMyselfFragment extends Fragment implements Handler.Callback{
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public  static Handler handler;
    static   EditText edit_date_of_death;
    EditText edit_national_id;
    EditText edit_mobile_number;
    EditText edit_name;
    RadioButton radioAge;
    RadioButton radioBirthdate;
    static EditText edit_birthday_date_again;
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
    LinearLayout linear_age;
    LinearLayout linear_birthdate;
    Spinner spinner_head;

    ArrayAdapter<Female> sexArrayAdapter;
    ArrayAdapter<YesNoModel> headArrayAdapter;
    ArrayAdapter<ReligionModel> religionArrayAdapter;
    ArrayAdapter<Occupation> occupationArrayAdapter;
    ArrayAdapter<StudyClass> educationArrayAdapter;
    ArrayAdapter<BloodGroup> bloodGroupArrayAdapter;
    ArrayAdapter<LivingStatusModel> livingGroupArrayAdapter;
    ArrayAdapter<MaritialStatus> maritalArrayAdapter;
    List<Female> sexArrayList= new ArrayList<>();
    ArrayList<ReligionModel> religionArrayList= new ArrayList<>();
    ArrayList<OccupationModel> occupationArrayList= new ArrayList<>();
    ArrayList<EducationModel> educationArrayList= new ArrayList<>();
    ArrayList<BloodGroupModel> bloodGroupArrayList= new ArrayList<>();
    ArrayList<LivingStatusModel> livingGroupArrayList= new ArrayList<>();
    ArrayList<MaritialStatusModel> maritalArrayList= new ArrayList<>();
    ArrayList<YesNoModel> headArrayList= new ArrayList<>();
    List<StudyClass> studyClassResponses= new ArrayList<>();
    List<MaritialStatus> maritialStatuses= new ArrayList<>();
    List<Female> femaleList= new ArrayList<>();
    List<Occupation> occupationModels= new ArrayList<>();
    List<BloodGroup> bloodGroups= new ArrayList<>();
    int headId;
    int bloodGroupId;
    int genderId;
    int studyId;
    int maritialId;
    int livingId;
    int religionId;
    int occupationId;
    String uniqueId;
    RelativeLayout relativeLayout;

    public HHMyselfFragment(String uniquKey) {
        uniqueId=uniquKey;
        Log.e("uniqueId",""+uniqueId);
    }

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

        load();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                initalizeSpinner();
            }
        }, 300);
        // display();
        return view;
    }
    private  void initView(){
        relativeLayout=view.findViewById(R.id.relative);
        linear_birthdate=view.findViewById(R.id.linear_birthdate);
        radioAge=view.findViewById(R.id.radioAge);
        linear_age=view.findViewById(R.id.linear_age);
        radioBirthdate=view.findViewById(R.id.radioBirthdate);
        edit_birthday_date_again=view.findViewById(R.id.edit_birthday_date_again);
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
        spinner_head=view.findViewById(R.id.spinner_head);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });

    }


    private void initalizeSpinner() {
       // sexArrayList= Utils.getSexList();
        hideSoftKeyboard(mActivity);
        headArrayList=Utils.getyesNoList();
        religionArrayList=Utils.getReligionList();
        occupationArrayList=Utils.getOccupationList();
        educationArrayList=Utils.getEducationList();
        bloodGroupArrayList=Utils.getBloodGroupList();
        livingGroupArrayList=Utils.getLivingStatusList();
        maritalArrayList=Utils.getMaritialStatusList();
        //hideSoftKeyboard(mActivity,edit_national_id);


        headArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, headArrayList);
        headArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_head.setAdapter(headArrayAdapter);

        sexArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, femaleList);
        sexArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sex.setAdapter(sexArrayAdapter);
        religionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, religionArrayList);
        religionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_religion.setAdapter(religionArrayAdapter);

        occupationArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, occupationModels);
        occupationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_occupation.setAdapter(occupationArrayAdapter);
        educationArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, studyClassResponses);
        educationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_education.setAdapter(educationArrayAdapter);


        bloodGroupArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, bloodGroups);
        bloodGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood_group.setAdapter(bloodGroupArrayAdapter);
        livingGroupArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, livingGroupArrayList);
        livingGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_living_status.setAdapter(livingGroupArrayAdapter);


        maritalArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, maritialStatuses);
        maritalArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_martial_status.setAdapter(maritalArrayAdapter);
        edit_birthday_date_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFromFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        edit_date_of_death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerDeadFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        radioAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_age.setVisibility(View.VISIBLE);
                linear_birthdate.setVisibility(View.GONE);
            }
        });
        radioBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_age.setVisibility(View.GONE);
                linear_birthdate.setVisibility(View.VISIBLE);
            }
        });
        spinner_head.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + headArrayList.get(position).getId());
                headId=headArrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Log.e("sp_water", "" + sexArrayList.get(position).getId());
                genderId=sexArrayList.get(position).FemaleId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             //   Log.e("sp_water", "" + sexArrayList.get(position).getId());
                studyId=studyClassResponses.get(position).StudyClassId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //    Log.e("sp_water", "" + headArrayList.get(position).getId());
                religionId=religionArrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_martial_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + headArrayList.get(position).getId());
                maritialId=maritialStatuses.get(position).MaritialId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + headArrayList.get(position).getId());
                occupationId=occupationModels.get(position).OccupationId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("sp_water", "" + headArrayList.get(position).getId());
                bloodGroupId=bloodGroups.get(position).BloodId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_living_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + livingGroupArrayList.get(position).getId());
                livingId= livingGroupArrayList.get(position).getId();
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
        edit_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString()!=null){

                    int years= 0;
                    try {
                        years = Integer.parseInt(charSequence.toString());
                        Calendar today = Calendar.getInstance();

                        int total=today.get(Calendar.YEAR)-years;

                        edit_birthday_date.setText("01-01-" + total);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        edit_birthday_date.setText("");
                    }



                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public void show(){

    }
    private boolean isChecked() {
        if(radioBirthdate.isChecked())
        {
            // is checked
        }
        else
        {
            if(radioAge.isChecked())
            {
                // is checked
            }
            else
            {

                Toast.makeText(mActivity, "Please fill birthday date", Toast.LENGTH_SHORT).show();
                // not checked
                return false;
            }
            // not checked
        }
//        if (Utils.isEmpty(edit_birthday_date.getText().toString())) {
//            Toast.makeText(mActivity, "Please fill birthday date", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else
         if (Utils.isEmpty(edit_national_id.getText().toString())) {
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

            try {
                MemberMyself myself=Common.memberMyselfRepository.getMemberMyself(edit_mobile_number.getText().toString());
                if (myself==null){
                    MemberMyself memberMyself = new MemberMyself();
                    memberMyself.NationalId= Integer.parseInt(edit_national_id.getText().toString());
                    memberMyself.MobileNumber= edit_national_id.getText().toString();
                    memberMyself.FullName= edit_name.getText().toString();
                    if (isNullOrEmpty(edit_birthday_date.getText().toString())){
                        memberMyself.DateOfBirth= edit_birthday_date_again.getText().toString();
                    }
                    else {
                        memberMyself.DateOfBirth= edit_birthday_date.getText().toString();
                    }
                    Date date = new Date(System.currentTimeMillis());
                    memberMyself.CreatedDate=date;
                    memberMyself.GenderId=genderId;
                    memberMyself.BloodGroupId=bloodGroupId;
                    memberMyself.ReligionId=religionId;
                    memberMyself.StudyId=studyId;
                    memberMyself.MaritialId=maritialId;
                    memberMyself.OccupationId=occupationId;
                    memberMyself.LivingId=livingId;
                    memberMyself.HouseHeadId=headId;
                    memberMyself.UniqueId=uniqueId;
                    Common.memberMyselfRepository.insertToMemberMyself(memberMyself);

                    HHCreateMemberFragment.nextPage(1);
                }
                else {
                    MemberMyself memberMyself = new MemberMyself();
                    memberMyself.NationalId= Integer.parseInt(edit_national_id.getText().toString());
                    memberMyself.MobileNumber= edit_national_id.getText().toString();
                    memberMyself.FullName= edit_name.getText().toString();
                    if (isNullOrEmpty(edit_birthday_date.getText().toString())){
                        memberMyself.DateOfBirth= edit_birthday_date_again.getText().toString();
                    }
                    else {
                        memberMyself.DateOfBirth= edit_birthday_date.getText().toString();
                    }
                    Date date = new Date(System.currentTimeMillis());
                    memberMyself.CreatedDate=date;
                    memberMyself.id=myself.id;
                    memberMyself.GenderId=genderId;
                    memberMyself.BloodGroupId=bloodGroupId;
                    memberMyself.ReligionId=religionId;
                    memberMyself.StudyId=studyId;
                    memberMyself.MaritialId=maritialId;
                    memberMyself.OccupationId=occupationId;
                    memberMyself.LivingId=livingId;
                    memberMyself.HouseHeadId=headId;
                    memberMyself.UniqueId=uniqueId;
                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);

                    HHCreateMemberFragment.nextPage(1);
                }

                HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
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
            edit_birthday_date_again.setText(formattedDate);
        }
    }
    public static class DatePickerDeadFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
            edit_date_of_death.setText(formattedDate);
        }
    }
    private  void load() {
        showLoadingProgress(mActivity);
        compositeDisposable.add(Common.studyClassRepository.getStudyClassItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<StudyClass>>() {
            @Override
            public void accept(List<StudyClass> customers) throws Exception {
                Log.e("Division","Division"+new Gson().toJson(customers));
                studyClassResponses=customers;

                dismissLoadingProgress();

            }
        }));
        compositeDisposable.add(Common.maritialStatusRepository.getMaritialStatusItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MaritialStatus>>() {
            @Override
            public void accept(List<MaritialStatus> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                maritialStatuses=customers;
                dismissLoadingProgress();

            }
        }));
//
        compositeDisposable.add(Common.femaleRepository.getFemaleItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Female>>() {
            @Override
            public void accept(List<Female> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                femaleList=customers;
                dismissLoadingProgress();

            }
        }));

        compositeDisposable.add(Common.occupationRepository.getOccupationItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Occupation>>() {
            @Override
            public void accept(List<Occupation> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                occupationModels=customers;
                dismissLoadingProgress();
            }
        }));
        compositeDisposable.add(Common.bloodGroupRepository.getBloodGroupItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<BloodGroup>>() {
            @Override
            public void accept(List<BloodGroup> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                bloodGroups=customers;
                dismissLoadingProgress();
            }
        }));

        compositeDisposable.add(Common.femaleRepository.getFemaleItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Female>>() {
            @Override
            public void accept(List<Female> customers) throws Exception {
                Log.e("Division","Division"+new Gson().toJson(customers));
                sexArrayList=customers;

                dismissLoadingProgress();

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();

      //  Log.e("loadload","size"+divisionList.size());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}

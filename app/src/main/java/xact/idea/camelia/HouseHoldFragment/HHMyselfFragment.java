package xact.idea.camelia.HouseHoldFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.MemberId;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Occupation;
import xact.idea.camelia.Database.Model.StudyClass;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.hideSoftKeyboard;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;


public class HHMyselfFragment extends Fragment implements Handler.Callback {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    @SuppressLint("StaticFieldLeak")
    public static Handler handler;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_date_of_death;
    EditText edit_national_id;
    EditText edit_mobile_number;
    EditText edit_name;
    RadioButton radioAge;
    RadioButton radioBirthdate;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_birthday_date_again;
    @SuppressLint("StaticFieldLeak")
    static EditText edit_birthday_date;
    @SuppressLint("StaticFieldLeak")
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
    List<Female> sexArrayList = new ArrayList<>();
    ArrayList<ReligionModel> religionArrayList = new ArrayList<>();
    ArrayList<OccupationModel> occupationArrayList = new ArrayList<>();
    ArrayList<EducationModel> educationArrayList = new ArrayList<>();
    ArrayList<BloodGroupModel> bloodGroupArrayList = new ArrayList<>();
    ArrayList<LivingStatusModel> livingGroupArrayList = new ArrayList<>();
    ArrayList<MaritialStatusModel> maritalArrayList = new ArrayList<>();
    ArrayList<YesNoModel> headArrayList = new ArrayList<>();
    List<StudyClass> studyClassResponses = new ArrayList<>();
    List<MaritialStatus> maritialStatuses = new ArrayList<>();
    List<Female> femaleList = new ArrayList<>();
    List<Occupation> occupationModels = new ArrayList<>();
    List<BloodGroup> bloodGroups = new ArrayList<>();
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

    String memberId;
    String frags;
    static String update;

    TextView tv_full_name;
    TextView tv_gender;
    TextView tv_marital_status;
    TextView tv_religion;
    TextView tv_blood_group;
    TextView tv_dob;
    TextView tv_occupation;
    TextView tv_class;
    TextView tv_mobile_number;
    TextView tv_national_id;
    TextView tv_living_status;
    TextView tv_date_of_death;
    TextView tv_head;
    TextView tv_age;
    TextView tv_birthdate_again;
    TextView tv_birthdate_for;

    public HHMyselfFragment(String uniquKey, String updates,String frag) {
        uniqueId = uniquKey;
        update = updates;
        frags = frag;
        Log.e("uniqueId", "" + uniqueId);
        Log.e("update", "" + update);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hhmyself, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        setRetainInstance(true);
        handler = new Handler(this);
        initView();

        load();
        showLoadingProgress(mActivity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        load();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        initalizeSpinner();
                                        dismissLoadingProgress();

                                    }
                                });

                            }
                        }, 100);


                    }
                });

            }
        }, 30);

        // display();
        return view;
    }

    private void initView() {
        relativeLayout = view.findViewById(R.id.relative);
        linear_birthdate = view.findViewById(R.id.linear_birthdate);
        radioAge = view.findViewById(R.id.radioAge);
        linear_age = view.findViewById(R.id.linear_age);
        radioBirthdate = view.findViewById(R.id.radioBirthdate);
        edit_birthday_date_again = view.findViewById(R.id.edit_birthday_date_again);
        linear_edit_death = view.findViewById(R.id.linear_edit_death);
        edit_date_of_death = view.findViewById(R.id.edit_date_of_death);
        edit_national_id = view.findViewById(R.id.edit_national_id);
        edit_mobile_number = view.findViewById(R.id.edit_mobile_number);
        edit_name = view.findViewById(R.id.edit_name);
        edit_birthday_date = view.findViewById(R.id.edit_birthday_date);
        edit_age = view.findViewById(R.id.edit_age);
        spinner_sex = view.findViewById(R.id.spinner_sex);
        spinner_religion = view.findViewById(R.id.spinner_religion);
        spinner_education = view.findViewById(R.id.spinner_education);
        spinner_martial_status = view.findViewById(R.id.spinner_martial_status);
        spinner_occupation = view.findViewById(R.id.spinner_occupation);
        spinner_blood_group = view.findViewById(R.id.spinner_blood_group);
        spinner_living_status = view.findViewById(R.id.spinner_living_status);
        spinner_head = view.findViewById(R.id.spinner_head);
        tv_full_name = view.findViewById(R.id.tv_full_name);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_marital_status = view.findViewById(R.id.tv_marital_status);
        tv_religion = view.findViewById(R.id.tv_religion);
        tv_blood_group = view.findViewById(R.id.tv_blood_group);
        tv_dob = view.findViewById(R.id.tv_dob);
        tv_occupation = view.findViewById(R.id.tv_occupation);
        tv_class = view.findViewById(R.id.tv_class);
        tv_mobile_number = view.findViewById(R.id.tv_mobile_number);
        tv_national_id = view.findViewById(R.id.tv_national_id);
        tv_living_status = view.findViewById(R.id.tv_living_status);
        tv_date_of_death = view.findViewById(R.id.tv_date_of_death);
        tv_head = view.findViewById(R.id.tv_head);
        tv_age= view.findViewById(R.id.tv_age);
        tv_birthdate_again= view.findViewById(R.id.tv_birthdate_again);
        tv_birthdate_for= view.findViewById(R.id.tv_birthdate_for);
        Paper.init(mActivity);
        String language = SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language", language);
        updateView((String) Paper.book().read("language"));
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(mActivity, language);
        Resources resources = context.getResources();
        radioAge.setText(resources.getString(R.string.age));
        edit_mobile_number.setHint(resources.getString(R.string.mobile_number_));
        edit_birthday_date.setHint(resources.getString(R.string.birthday_date));
        edit_birthday_date_again.setHint(resources.getString(R.string.birthday_date));
        edit_name.setHint(resources.getString(R.string.full_name_));
        edit_age.setHint(resources.getString(R.string.age));
        edit_national_id.setHint(resources.getString(R.string.national_id_));
        edit_date_of_death.setHint(resources.getString(R.string.date_of_death));
        radioBirthdate.setText(resources.getString(R.string.birthday_date));
        tv_full_name.setText(resources.getString(R.string.full_name));
        tv_gender.setText(resources.getString(R.string.gender));
        tv_marital_status.setText(resources.getString(R.string.marital_status));
        tv_religion.setText(resources.getString(R.string.religion));
        tv_blood_group.setText(resources.getString(R.string.blood_group));
        tv_dob.setText(resources.getString(R.string.dob));
        tv_occupation.setText(resources.getString(R.string.occupation));
        tv_class.setText(resources.getString(R.string.classes));
        tv_mobile_number.setText(resources.getString(R.string.mobile_number));
        tv_national_id.setText(resources.getString(R.string.national_id));
        tv_living_status.setText(resources.getString(R.string.living_status));
        tv_date_of_death.setText(resources.getString(R.string.date_of_death));
        tv_head.setText(resources.getString(R.string.head_of));
        tv_age.setText(resources.getString(R.string.age));
        tv_birthdate_again.setText(resources.getString(R.string.birthday_date));
        tv_birthdate_for.setText(resources.getString(R.string.birthday_date));

    }

    private void initalizeSpinner() {
        // sexArrayList= Utils.getSexList();
        hideSoftKeyboard(mActivity);
        headArrayList = Utils.getyesNoList(mActivity);
        religionArrayList = Utils.getReligionList(mActivity);
        occupationArrayList = Utils.getOccupationList();
        educationArrayList = Utils.getEducationList();
        bloodGroupArrayList = Utils.getBloodGroupList();
        livingGroupArrayList = Utils.getLivingStatusList(mActivity);
        maritalArrayList = Utils.getMaritialStatusList();
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
                headId = headArrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("FemaleId", "genderId" + sexArrayList.get(position).FemaleId);
                genderId = sexArrayList.get(position).FemaleId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   Log.e("sp_water", "" + sexArrayList.get(position).getId());
                studyId = studyClassResponses.get(position).StudyClassId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    Log.e("sp_water", "" + headArrayList.get(position).getId());
                religionId = religionArrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_martial_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + headArrayList.get(position).getId());
                maritialId = maritialStatuses.get(position).MaritialId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   Log.e("sp_water", "" + headArrayList.get(position).getId());
                occupationId = occupationModels.get(position).OccupationId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("sp_water", "" + headArrayList.get(position).getId());
                bloodGroupId = bloodGroups.get(position).BloodId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_living_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + livingGroupArrayList.get(position).getId());
                livingId = livingGroupArrayList.get(position).getId();
                if (livingGroupArrayList.get(position).getId() == 2) {
                    linear_edit_death.setVisibility(View.VISIBLE);
                    String language = SharedPreferenceUtil.getLanguage(mActivity);
                    HHCreateMemberFragment.nextPagesAlive(language);
                } else {
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

                if (charSequence.toString() != null) {

                    int years = 0;
                    try {
                        years = Integer.parseInt(charSequence.toString());
                        Calendar today = Calendar.getInstance();

                        int total = today.get(Calendar.YEAR) - years;
                        int month = today.get(Calendar.MONTH)+1;
                        int day = today.get(Calendar.DAY_OF_MONTH);

                        String months = "";
                        String days = "";
                        if (month > 9) {
                            months = String.valueOf(month);
                        } else {
                            months = "0" + month;
                        }

                        if (day > 9) {
                            days = String.valueOf(day);
                        } else {
                            days = "0" + day;
                        }

                        edit_birthday_date.setText(days + "-" + months + "-" + total);
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
        show();

    }

    public void show() {
        MemberMyself memberId = Common.memberMyselfRepository.getMemberId(update);

        if (memberId != null) {
            edit_national_id.setText(String.valueOf(memberId.NationalId));
            edit_name.setText(memberId.FullName);
            edit_mobile_number.setText(memberId.MobileNumber);
            radioBirthdate.setChecked(true);
            linear_age.setVisibility(View.GONE);
            linear_birthdate.setVisibility(View.VISIBLE);
            edit_birthday_date_again.setText(memberId.DateOfBirth);
            if (memberId.GenderId != 0) {
                int div = memberId.GenderId;

                for (int i = 0; i < femaleList.size(); i++) {
                    if (femaleList.get(i).FemaleId == div) {
                        spinner_sex.setSelection(i);
                    }
                }
            }
            //////////////
            if (memberId.MaritialId != 0) {
                int div = memberId.MaritialId;

                for (int i = 0; i < maritialStatuses.size(); i++) {
                    if (maritialStatuses.get(i).MaritialId == div) {
                        spinner_martial_status.setSelection(i);
                    }
                }
            }
            //////////////
            if (memberId.ReligionId != 0) {
                int div = memberId.ReligionId;

                for (int i = 0; i < religionArrayList.size(); i++) {
                    if (religionArrayList.get(i).getId() == div) {
                        spinner_religion.setSelection(i);
                    }
                }
            }
            //////////////
            if (memberId.BloodGroupId != 0) {
                int div = memberId.BloodGroupId;

                for (int i = 0; i < bloodGroups.size(); i++) {
                    if (bloodGroups.get(i).BloodId == div) {
                        spinner_blood_group.setSelection(i);
                    }
                }
            }
            //////////////
            if (memberId.OccupationId != 0) {
                int div = memberId.OccupationId;

                for (int i = 0; i < occupationModels.size(); i++) {
                    if (occupationModels.get(i).OccupationId == div) {
                        spinner_occupation.setSelection(i);
                    }
                }
            }
            //////////////
            if (memberId.StudyId != 0) {
                int div = memberId.StudyId;

                for (int i = 0; i < studyClassResponses.size(); i++) {
                    if (studyClassResponses.get(i).StudyClassId == div) {
                        spinner_education.setSelection(i);
                    }
                }
            }

            //////////////
            if (memberId.HouseHeadId != 0) {
                int div = memberId.HouseHeadId;

                for (int i = 0; i < headArrayList.size(); i++) {
                    if (headArrayList.get(i).getId() == div) {
                        spinner_head.setSelection(i);
                    }
                }
            }
            //////////
            if (memberId.LivingId != 0) {
                int div = memberId.LivingId;

                for (int i = 0; i < livingGroupArrayList.size(); i++) {
                    if (livingGroupArrayList.get(i).getId() == div) {
                        spinner_living_status.setSelection(i);
                    }
                }
            }
            if (memberId.LivingId == 2) {
                linear_edit_death.setVisibility(View.VISIBLE);
                edit_date_of_death.setText(memberId.DateOfDeath);
                linear_edit_death.setVisibility(View.VISIBLE);
            } else {
                linear_edit_death.setVisibility(View.GONE);

            }

        } else {

        }
    }

    private boolean isChecked() {
        if (radioBirthdate.isChecked()) {
            // is checked
        } else {
            if (radioAge.isChecked()) {
                // is checked
            } else {

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
        if (Utils.isEmpty(edit_name.getText().toString())) {
            Toast.makeText(mActivity, "Please fill Full Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Utils.isEmpty(edit_national_id.getText().toString())) {
            Toast.makeText(mActivity, "Please fill National id", Toast.LENGTH_SHORT).show();
            return false;
        }
         else if (Utils.isEmpty(edit_mobile_number.getText().toString())) {
            Toast.makeText(mActivity, "Please fill Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void saveData() {

        if (isChecked()) {
            MemberMyself memberIn= Common.memberMyselfRepository.getMemberMyselfForHousehold(uniqueId);
            try {
                SimpleDateFormat formatterfor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (livingId==2)
                {


                    if (memberIn==null)
                    {
                        MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                        if (memberIds != null) {
                            MemberMyself memberMyself = new MemberMyself();
                            memberMyself.NationalId = edit_national_id.getText().toString();
                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                            memberMyself.FullName = edit_name.getText().toString();
                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                            } else {
                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                            }
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date(System.currentTimeMillis());
                            Date date1 = null;
                            String currentDate = formatter.format(date);

                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            memberMyself.CreatedDate = date1;
                            String created_at = formatterfor.format(date);
                            memberMyself.created_at = created_at;
                            memberMyself.id = memberIds.id;
                            memberMyself.VisitDate = memberIds.VisitDate;
                            memberMyself.GenderId = genderId;
                            memberMyself.BloodGroupId = bloodGroupId;
                            memberMyself.ReligionId = religionId;
                            memberMyself.StudyId = studyId;
                            memberMyself.From = memberIds.From;
                            memberMyself.To = memberIds.To;
                            memberMyself.MaritialId = maritialId;
                            memberMyself.OccupationId = occupationId;
                            memberMyself.LivingId = livingId;
                            memberMyself.HouseHeadId = headId;
                            memberMyself.MemberId = memberIds.MemberId;
                            memberMyself.UniqueId = uniqueId;
                            memberMyself.UniqueCode = uniqueId+memberIds.MemberId;
                            memberMyself.Status = memberIds.Status;
                            SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                            Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                            if (frags.equals("frag")) {
                                ((CCUserHomeActivity) getActivity()).backForDetails();
                            } else {
                                ((HouseholdHomeActivity) getActivity()).backForDetails();
                            }
                        } else {
                            MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                            if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                            } else {
                                if (myself == null) {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);
                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    memberMyself.CreatedDate = date1;
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.VisitDate = "";

                                    memberMyself.DateOfDeath = edit_date_of_death.getText().toString();

                                    int  count=Common.memberMyselfRepository.count(uniqueId);
                                    String countSize=String.valueOf(count+1);
                                    String memberValue="";
                                    if (countSize.length()<9){
                                        memberValue="00"+countSize;
                                    }
                                    else if (countSize.length()>10 && countSize.length()<99){
                                        memberValue="0"+countSize;
                                    }
                                    else{
                                        memberValue=countSize;
                                    }

                                    memberMyself.MemberId = memberValue;
                                    memberMyself.UniqueCode = uniqueId+memberValue;
                                    memberMyself.Status = "0";
                                    Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                    //Common.memberIdRepository.emptyMemberId(memberId);
                                    showInfoDialog(mActivity, uniqueId+memberValue);

                                }
                                else {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);
                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.CreatedDate = date1;
                                    memberMyself.id = myself.id;
                                    memberMyself.VisitDate = myself.VisitDate;
                                    memberMyself.Status = myself.Status;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.UniqueCode = myself.UniqueCode;
                                    memberMyself.DateOfDeath = edit_date_of_death.getText().toString();
                                    memberMyself.MemberId = myself.MemberId;
                                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                    if (frags.equals("frag")) {
                                        ((CCUserHomeActivity) getActivity()).backForDetails();
                                    } else {
                                        ((HouseholdHomeActivity) getActivity()).backForDetails();
                                    }

                                }


                            }
                        }
                        SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");
                    }
                    else{
                        if (headId==2)
                        {
                            MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                            if (memberIds != null) {
                                MemberMyself memberMyself = new MemberMyself();
                                memberMyself.NationalId = edit_national_id.getText().toString();
                                memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                memberMyself.FullName = edit_name.getText().toString();
                                if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                    memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                } else {
                                    memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                }
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date(System.currentTimeMillis());
                                Date date1 = null;
                                String currentDate = formatter.format(date);

                                try {
                                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                    // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                memberMyself.CreatedDate = date1;
                                String created_at = formatterfor.format(date);
                                memberMyself.created_at = created_at;
                                memberMyself.id = memberIds.id;
                                memberMyself.VisitDate = memberIds.VisitDate;
                                memberMyself.GenderId = genderId;
                                memberMyself.BloodGroupId = bloodGroupId;
                                memberMyself.ReligionId = religionId;
                                memberMyself.StudyId = studyId;
                                memberMyself.From = memberIds.From;
                                memberMyself.To = memberIds.To;
                                memberMyself.MaritialId = maritialId;
                                memberMyself.OccupationId = occupationId;
                                memberMyself.LivingId = livingId;
                                memberMyself.HouseHeadId = headId;
                                memberMyself.MemberId = memberIds.MemberId;
                                memberMyself.UniqueId = uniqueId;
                                memberMyself.UniqueCode = uniqueId+memberIds.MemberId;
                                memberMyself.Status = memberIds.Status;
                                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                                Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                if (frags.equals("frag")) {
                                    ((CCUserHomeActivity) getActivity()).backForDetails();
                                } else {
                                    ((HouseholdHomeActivity) getActivity()).backForDetails();
                                }
                            } else {
                                MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                                if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                    Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (myself == null) {
                                        MemberMyself memberMyself = new MemberMyself();
                                        memberMyself.NationalId = edit_national_id.getText().toString();
                                        memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                        memberMyself.FullName = edit_name.getText().toString();
                                        if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                            memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                        } else {
                                            memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date1 = null;
                                        String currentDate = formatter.format(date);
                                        try {
                                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        memberMyself.CreatedDate = date1;
                                        String created_at = formatterfor.format(date);
                                        memberMyself.created_at = created_at;
                                        memberMyself.GenderId = genderId;
                                        memberMyself.BloodGroupId = bloodGroupId;
                                        memberMyself.ReligionId = religionId;
                                        memberMyself.StudyId = studyId;
                                        memberMyself.MaritialId = maritialId;
                                        memberMyself.OccupationId = occupationId;
                                        memberMyself.LivingId = livingId;
                                        memberMyself.HouseHeadId = headId;
                                        memberMyself.UniqueId = uniqueId;
                                        memberMyself.VisitDate = "";

                                        memberMyself.DateOfDeath = edit_date_of_death.getText().toString();

                                        int  count=Common.memberMyselfRepository.count(uniqueId);
                                        String countSize=String.valueOf(count+1);
                                        String memberValue="";
                                        if (countSize.length()<9){
                                            memberValue="00"+countSize;
                                        }
                                        else if (countSize.length()>10 && countSize.length()<99){
                                            memberValue="0"+countSize;
                                        }
                                        else{
                                            memberValue=countSize;
                                        }

                                        memberMyself.MemberId = memberValue;
                                        memberMyself.UniqueCode = uniqueId+memberValue;
                                        memberMyself.Status = "0";
                                        Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                        SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                        //Common.memberIdRepository.emptyMemberId(memberId);
                                        showInfoDialog(mActivity, uniqueId+memberValue);

                                    }
                                    else {
                                        MemberMyself memberMyself = new MemberMyself();
                                        memberMyself.NationalId = edit_national_id.getText().toString();
                                        memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                        memberMyself.FullName = edit_name.getText().toString();
                                        if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                            memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                        } else {
                                            memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date1 = null;
                                        String currentDate = formatter.format(date);
                                        try {
                                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        String created_at = formatterfor.format(date);
                                        memberMyself.created_at = created_at;
                                        memberMyself.CreatedDate = date1;
                                        memberMyself.id = myself.id;
                                        memberMyself.VisitDate = myself.VisitDate;
                                        memberMyself.Status = myself.Status;
                                        memberMyself.GenderId = genderId;
                                        memberMyself.BloodGroupId = bloodGroupId;
                                        memberMyself.ReligionId = religionId;
                                        memberMyself.StudyId = studyId;
                                        memberMyself.MaritialId = maritialId;
                                        memberMyself.OccupationId = occupationId;
                                        memberMyself.LivingId = livingId;
                                        memberMyself.HouseHeadId = headId;
                                        memberMyself.UniqueId = uniqueId;
                                        memberMyself.UniqueCode = myself.UniqueCode;
                                        memberMyself.DateOfDeath = edit_date_of_death.getText().toString();
                                        memberMyself.MemberId = myself.MemberId;
                                        Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                        if (frags.equals("frag")) {
                                            ((CCUserHomeActivity) getActivity()).backForDetails();
                                        } else {
                                            ((HouseholdHomeActivity) getActivity()).backForDetails();
                                        }

                                    }


                                }
                            }
                        }
                        else{

                            if (memberIn.NationalId.equals(edit_national_id.getText().toString())){
                                MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                                if (memberIds != null) {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);

                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    memberMyself.CreatedDate = date1;
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.id = memberIds.id;
                                    memberMyself.VisitDate = memberIds.VisitDate;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.From = memberIds.From;
                                    memberMyself.To = memberIds.To;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.MemberId = memberIds.MemberId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.UniqueCode = uniqueId+memberIds.MemberId;
                                    memberMyself.Status = memberIds.Status;
                                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                    if (frags.equals("frag")) {
                                        ((CCUserHomeActivity) getActivity()).backForDetails();
                                    } else {
                                        ((HouseholdHomeActivity) getActivity()).backForDetails();
                                    }
                                } else {
                                    MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                                    if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                        Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (myself == null) {
                                            MemberMyself memberMyself = new MemberMyself();
                                            memberMyself.NationalId = edit_national_id.getText().toString();
                                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                            memberMyself.FullName = edit_name.getText().toString();
                                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                            } else {
                                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                            }
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date = new Date(System.currentTimeMillis());
                                            Date date1 = null;
                                            String currentDate = formatter.format(date);
                                            try {
                                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            memberMyself.CreatedDate = date1;
                                            String created_at = formatterfor.format(date);
                                            memberMyself.created_at = created_at;
                                            memberMyself.GenderId = genderId;
                                            memberMyself.BloodGroupId = bloodGroupId;
                                            memberMyself.ReligionId = religionId;
                                            memberMyself.StudyId = studyId;
                                            memberMyself.MaritialId = maritialId;
                                            memberMyself.OccupationId = occupationId;
                                            memberMyself.LivingId = livingId;
                                            memberMyself.HouseHeadId = headId;
                                            memberMyself.UniqueId = uniqueId;
                                            memberMyself.VisitDate = "";

                                            memberMyself.DateOfDeath = edit_date_of_death.getText().toString();

                                            int  count=Common.memberMyselfRepository.count(uniqueId);
                                            String countSize=String.valueOf(count+1);
                                            String memberValue="";
                                            if (countSize.length()<9){
                                                memberValue="00"+countSize;
                                            }
                                            else if (countSize.length()>10 && countSize.length()<99){
                                                memberValue="0"+countSize;
                                            }
                                            else{
                                                memberValue=countSize;
                                            }

                                            memberMyself.MemberId = memberValue;
                                            memberMyself.UniqueCode = uniqueId+memberValue;
                                            memberMyself.Status = "0";
                                            Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                            SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                            //Common.memberIdRepository.emptyMemberId(memberId);
                                            showInfoDialog(mActivity, uniqueId+memberValue);

                                        }
                                        else {
                                            MemberMyself memberMyself = new MemberMyself();
                                            memberMyself.NationalId = edit_national_id.getText().toString();
                                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                            memberMyself.FullName = edit_name.getText().toString();
                                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                            } else {
                                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                            }
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date = new Date(System.currentTimeMillis());
                                            Date date1 = null;
                                            String currentDate = formatter.format(date);
                                            try {
                                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            String created_at = formatterfor.format(date);
                                            memberMyself.created_at = created_at;
                                            memberMyself.CreatedDate = date1;
                                            memberMyself.id = myself.id;
                                            memberMyself.VisitDate = myself.VisitDate;
                                            memberMyself.Status = myself.Status;
                                            memberMyself.GenderId = genderId;
                                            memberMyself.BloodGroupId = bloodGroupId;
                                            memberMyself.ReligionId = religionId;
                                            memberMyself.StudyId = studyId;
                                            memberMyself.MaritialId = maritialId;
                                            memberMyself.OccupationId = occupationId;
                                            memberMyself.LivingId = livingId;
                                            memberMyself.HouseHeadId = headId;
                                            memberMyself.UniqueId = uniqueId;
                                            memberMyself.UniqueCode = myself.UniqueCode;
                                            memberMyself.DateOfDeath = edit_date_of_death.getText().toString();
                                            memberMyself.MemberId = myself.MemberId;
                                            Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                            if (frags.equals("frag")) {
                                                ((CCUserHomeActivity) getActivity()).backForDetails();
                                            } else {
                                                ((HouseholdHomeActivity) getActivity()).backForDetails();
                                            }

                                        }


                                    }
                                }
                            }
                            else{
                                Toast.makeText(mActivity, "Already Select Household Head", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                    SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");

                    ////////Test
                }
                else{
                    if (memberIn==null){
                        MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                        if (memberIds != null) {
                            MemberMyself memberMyself = new MemberMyself();
                            memberMyself.NationalId = edit_national_id.getText().toString();
                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                            memberMyself.FullName = edit_name.getText().toString();
                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                            } else {
                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                            }
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date(System.currentTimeMillis());
                            Date date1 = null;
                            String currentDate = formatter.format(date);
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String created_at = formatterfor.format(date);
                            memberMyself.created_at = created_at;
                            memberMyself.CreatedDate = date1;
                            memberMyself.id = memberIds.id;
                            memberMyself.VisitDate = memberIds.VisitDate;
                            memberMyself.GenderId = genderId;
                            memberMyself.BloodGroupId = bloodGroupId;
                            memberMyself.ReligionId = religionId;
                            memberMyself.StudyId = studyId;
                            memberMyself.MaritialId = maritialId;
                            memberMyself.OccupationId = occupationId;
                            memberMyself.LivingId = livingId;
                            memberMyself.HouseHeadId = headId;
                            memberMyself.MemberId = memberIds.MemberId;
                            memberMyself.UniqueId = uniqueId;
                            memberMyself.From = memberIds.From;
                            memberMyself.To = memberIds.To;
                            memberMyself.UniqueCode = memberIds.UniqueCode;
                            memberMyself.Status = memberIds.Status;
                            SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                            Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                            HHCreateMemberFragment.nextPage(1);
                            HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                        } else {
                            MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                            if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                            } else {
                                if (myself == null) {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);
                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    memberMyself.CreatedDate = date1;
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.VisitDate = "";

                                    int  count=Common.memberMyselfRepository.count(uniqueId);
                                    String countSize=String.valueOf(count+1);
                                    String memberValue="";
                                    if (countSize.length()<9){
                                        memberValue="00"+countSize;
                                    }
                                    else if (countSize.length()>10 && countSize.length()<99){
                                        memberValue="0"+countSize;
                                    }
                                    else{
                                        memberValue=countSize;
                                    }
                                    memberMyself.MemberId = memberValue;
                                    memberMyself.UniqueCode = uniqueId+memberValue;
                                    memberMyself.Status = "0";
                                    Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                    //  Common.memberIdRepository.emptyMemberId(memberId);
                                    HHCreateMemberFragment.nextPage(1);
                                } else {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);
                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.CreatedDate = date1;
                                    memberMyself.id = myself.id;
                                    memberMyself.VisitDate = myself.VisitDate;
                                    memberMyself.Status = myself.Status;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.UniqueCode = myself.UniqueCode;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.MemberId = myself.MemberId;
                                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);

                                    HHCreateMemberFragment.nextPage(1);
                                }

                                HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                            }
                        }
                        SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");
                    }
                    else{
                        if (headId==2)
                        {
                            MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                            if (memberIds != null) {
                                MemberMyself memberMyself = new MemberMyself();
                                memberMyself.NationalId = edit_national_id.getText().toString();
                                memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                memberMyself.FullName = edit_name.getText().toString();
                                if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                    memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                } else {
                                    memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                }
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date(System.currentTimeMillis());
                                Date date1 = null;
                                String currentDate = formatter.format(date);
                                try {
                                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                    // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String created_at = formatterfor.format(date);
                                memberMyself.created_at = created_at;
                                memberMyself.CreatedDate = date1;
                                memberMyself.id = memberIds.id;
                                memberMyself.VisitDate = memberIds.VisitDate;
                                memberMyself.GenderId = genderId;
                                memberMyself.BloodGroupId = bloodGroupId;
                                memberMyself.ReligionId = religionId;
                                memberMyself.StudyId = studyId;
                                memberMyself.MaritialId = maritialId;
                                memberMyself.OccupationId = occupationId;
                                memberMyself.LivingId = livingId;
                                memberMyself.HouseHeadId = headId;
                                memberMyself.MemberId = memberIds.MemberId;
                                memberMyself.UniqueId = uniqueId;
                                memberMyself.From = memberIds.From;
                                memberMyself.To = memberIds.To;
                                memberMyself.UniqueCode = memberIds.UniqueCode;
                                memberMyself.Status = memberIds.Status;
                                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                                Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                HHCreateMemberFragment.nextPage(1);
                                HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                            } else {
                                MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                                if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                    Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (myself == null) {
                                        MemberMyself memberMyself = new MemberMyself();
                                        memberMyself.NationalId = edit_national_id.getText().toString();
                                        memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                        memberMyself.FullName = edit_name.getText().toString();
                                        if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                            memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                        } else {
                                            memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date1 = null;
                                        String currentDate = formatter.format(date);
                                        try {
                                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        memberMyself.CreatedDate = date1;
                                        String created_at = formatterfor.format(date);
                                        memberMyself.created_at = created_at;
                                        memberMyself.GenderId = genderId;
                                        memberMyself.BloodGroupId = bloodGroupId;
                                        memberMyself.ReligionId = religionId;
                                        memberMyself.StudyId = studyId;
                                        memberMyself.MaritialId = maritialId;
                                        memberMyself.OccupationId = occupationId;
                                        memberMyself.LivingId = livingId;
                                        memberMyself.HouseHeadId = headId;
                                        memberMyself.UniqueId = uniqueId;
                                        memberMyself.VisitDate = "";

                                        int  count=Common.memberMyselfRepository.count(uniqueId);
                                        String countSize=String.valueOf(count+1);
                                        String memberValue="";
                                        if (countSize.length()<9){
                                            memberValue="00"+countSize;
                                        }
                                        else if (countSize.length()>10 && countSize.length()<99){
                                            memberValue="0"+countSize;
                                        }
                                        else{
                                            memberValue=countSize;
                                        }
                                        memberMyself.MemberId = memberValue;
                                        memberMyself.UniqueCode = uniqueId+memberValue;
                                        memberMyself.Status = "0";
                                        Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                        SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                        //  Common.memberIdRepository.emptyMemberId(memberId);
                                        HHCreateMemberFragment.nextPage(1);
                                    } else {
                                        MemberMyself memberMyself = new MemberMyself();
                                        memberMyself.NationalId = edit_national_id.getText().toString();
                                        memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                        memberMyself.FullName = edit_name.getText().toString();
                                        if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                            memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                        } else {
                                            memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date1 = null;
                                        String currentDate = formatter.format(date);
                                        try {
                                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        String created_at = formatterfor.format(date);
                                        memberMyself.created_at = created_at;
                                        memberMyself.CreatedDate = date1;
                                        memberMyself.id = myself.id;
                                        memberMyself.VisitDate = myself.VisitDate;
                                        memberMyself.Status = myself.Status;
                                        memberMyself.GenderId = genderId;
                                        memberMyself.BloodGroupId = bloodGroupId;
                                        memberMyself.ReligionId = religionId;
                                        memberMyself.StudyId = studyId;
                                        memberMyself.MaritialId = maritialId;
                                        memberMyself.UniqueCode = myself.UniqueCode;
                                        memberMyself.OccupationId = occupationId;
                                        memberMyself.LivingId = livingId;
                                        memberMyself.HouseHeadId = headId;
                                        memberMyself.UniqueId = uniqueId;
                                        memberMyself.MemberId = myself.MemberId;
                                        Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                        SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");
                                        HHCreateMemberFragment.nextPage(1);
                                    }

                                    HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        else{
                            if (memberIn.NationalId.equals(edit_national_id.getText().toString())){
                                MemberMyself memberIds = Common.memberMyselfRepository.getMemberId(update);
                                if (memberIds != null) {
                                    MemberMyself memberMyself = new MemberMyself();
                                    memberMyself.NationalId = edit_national_id.getText().toString();
                                    memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                    memberMyself.FullName = edit_name.getText().toString();
                                    if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                        memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                    } else {
                                        memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date(System.currentTimeMillis());
                                    Date date1 = null;
                                    String currentDate = formatter.format(date);
                                    try {
                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    String created_at = formatterfor.format(date);
                                    memberMyself.created_at = created_at;
                                    memberMyself.CreatedDate = date1;
                                    memberMyself.id = memberIds.id;
                                    memberMyself.VisitDate = memberIds.VisitDate;
                                    memberMyself.GenderId = genderId;
                                    memberMyself.BloodGroupId = bloodGroupId;
                                    memberMyself.ReligionId = religionId;
                                    memberMyself.StudyId = studyId;
                                    memberMyself.MaritialId = maritialId;
                                    memberMyself.OccupationId = occupationId;
                                    memberMyself.LivingId = livingId;
                                    memberMyself.HouseHeadId = headId;
                                    memberMyself.MemberId = memberIds.MemberId;
                                    memberMyself.UniqueId = uniqueId;
                                    memberMyself.From = memberIds.From;
                                    memberMyself.To = memberIds.To;
                                    memberMyself.UniqueCode = memberIds.UniqueCode;
                                    memberMyself.Status = memberIds.Status;
                                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                    HHCreateMemberFragment.nextPage(1);
                                    HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                                } else {
                                    MemberMyself myself = Common.memberMyselfRepository.getMemberMyself(edit_national_id.getText().toString());

                                    if (genderId == -1 || bloodGroupId == -1 || religionId == -1 || studyId == -1 || maritialId == -1 || livingId == -1 || headId == -1 || occupationId == -1) {
                                        Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (myself == null) {
                                            MemberMyself memberMyself = new MemberMyself();
                                            memberMyself.NationalId = edit_national_id.getText().toString();
                                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                            memberMyself.FullName = edit_name.getText().toString();
                                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                            } else {
                                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                            }
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date = new Date(System.currentTimeMillis());
                                            Date date1 = null;
                                            String currentDate = formatter.format(date);
                                            try {
                                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            memberMyself.CreatedDate = date1;
                                            String created_at = formatterfor.format(date);
                                            memberMyself.created_at = created_at;
                                            memberMyself.GenderId = genderId;
                                            memberMyself.BloodGroupId = bloodGroupId;
                                            memberMyself.ReligionId = religionId;
                                            memberMyself.StudyId = studyId;
                                            memberMyself.MaritialId = maritialId;
                                            memberMyself.OccupationId = occupationId;
                                            memberMyself.LivingId = livingId;
                                            memberMyself.HouseHeadId = headId;
                                            memberMyself.UniqueId = uniqueId;
                                            memberMyself.VisitDate = "";

                                            int  count=Common.memberMyselfRepository.count(uniqueId);
                                            String countSize=String.valueOf(count+1);
                                            String memberValue="";
                                            if (countSize.length()<9){
                                                memberValue="00"+countSize;
                                            }
                                            else if (countSize.length()>10 && countSize.length()<99){
                                                memberValue="0"+countSize;
                                            }
                                            else{
                                                memberValue=countSize;
                                            }
                                            memberMyself.MemberId = memberValue;
                                            memberMyself.UniqueCode = uniqueId+memberValue;
                                            memberMyself.Status = "0";
                                            Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                                            SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                                            //  Common.memberIdRepository.emptyMemberId(memberId);
                                            HHCreateMemberFragment.nextPage(1);
                                        } else {
                                            MemberMyself memberMyself = new MemberMyself();
                                            memberMyself.NationalId = edit_national_id.getText().toString();
                                            memberMyself.MobileNumber = edit_mobile_number.getText().toString();
                                            memberMyself.FullName = edit_name.getText().toString();
                                            if (isNullOrEmpty(edit_birthday_date.getText().toString())) {
                                                memberMyself.DateOfBirth = edit_birthday_date_again.getText().toString();
                                            } else {
                                                memberMyself.DateOfBirth = edit_birthday_date.getText().toString();
                                            }
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date = new Date(System.currentTimeMillis());
                                            Date date1 = null;
                                            String currentDate = formatter.format(date);
                                            try {
                                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            String created_at = formatterfor.format(date);
                                            memberMyself.created_at = created_at;
                                            memberMyself.CreatedDate = date1;
                                            memberMyself.id = myself.id;
                                            memberMyself.VisitDate = myself.VisitDate;
                                            memberMyself.Status = myself.Status;
                                            memberMyself.GenderId = genderId;
                                            memberMyself.BloodGroupId = bloodGroupId;
                                            memberMyself.ReligionId = religionId;
                                            memberMyself.StudyId = studyId;
                                            memberMyself.MaritialId = maritialId;
                                            memberMyself.UniqueCode = myself.UniqueCode;
                                            memberMyself.OccupationId = occupationId;
                                            memberMyself.LivingId = livingId;
                                            memberMyself.HouseHeadId = headId;
                                            memberMyself.UniqueId = uniqueId;
                                            memberMyself.MemberId = myself.MemberId;
                                            Common.memberMyselfRepository.updateMemberMyself(memberMyself);
                                            SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");
                                            HHCreateMemberFragment.nextPage(1);
                                        }

                                        HHCreateMemberFragment.btn_back.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            else{
                                Toast.makeText(mActivity, "Already Select Household Head", Toast.LENGTH_SHORT).show();
                            }

                        }
                        SharedPreferenceUtil.saveShared(getActivity(), SharedPreferenceUtil.SYNC, "on");
                    }

                }



            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 0) {
            saveData();
        }
        return false;
    }

    public static class DatePickerFromFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();

            MemberMyself memberId = Common.memberMyselfRepository.getMemberId(update);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy",Locale.US);
            SimpleDateFormat df2 = new SimpleDateFormat("MM",Locale.US);
            SimpleDateFormat df3 = new SimpleDateFormat("dd",Locale.US);

            if (memberId!=null){
                try {
                    Date d = df.parse(memberId.DateOfBirth);
                    String year = df1.format(d);
                    String month = df2.format(d);
                    String day = df3.format(d);
                    int mon=Integer.parseInt(month)-1;
                    calendar.set(Integer.parseInt(year),mon,Integer.parseInt(day));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(chosenDate);
            edit_birthday_date_again.setText(formattedDate);
        }
    }

    public static class DatePickerDeadFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(chosenDate);
            edit_date_of_death.setText(formattedDate);
        }
    }

    private void load() {
        showLoadingProgress(mActivity);
        compositeDisposable.add(Common.studyClassRepository.getStudyClassItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<StudyClass>>() {
            @Override
            public void accept(List<StudyClass> customers) throws Exception {
                Log.e("Division", "Division" + new Gson().toJson(customers));
                studyClassResponses = customers;

                dismissLoadingProgress();

            }
        }));
        compositeDisposable.add(Common.maritialStatusRepository.getMaritialStatusItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MaritialStatus>>() {
            @Override
            public void accept(List<MaritialStatus> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                maritialStatuses = customers;
                dismissLoadingProgress();

            }
        }));
//
        compositeDisposable.add(Common.femaleRepository.getFemaleItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Female>>() {
            @Override
            public void accept(List<Female> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                femaleList = customers;
                dismissLoadingProgress();

            }
        }));

        compositeDisposable.add(Common.occupationRepository.getOccupationItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Occupation>>() {
            @Override
            public void accept(List<Occupation> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                occupationModels = customers;
                dismissLoadingProgress();
            }
        }));
        compositeDisposable.add(Common.bloodGroupRepository.getBloodGroupItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<BloodGroup>>() {
            @Override
            public void accept(List<BloodGroup> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                bloodGroups = customers;
                dismissLoadingProgress();
            }
        }));

        compositeDisposable.add(Common.femaleRepository.getFemaleItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Female>>() {
            @Override
            public void accept(List<Female> customers) throws Exception {
                Log.e("Division", "Division" + new Gson().toJson(customers));
                sexArrayList = customers;

                dismissLoadingProgress();

            }
        }));
        compositeDisposable.add(Common.memberIdRepository.getMemberIdItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberId>>() {
            @Override
            public void accept(List<MemberId> memberIds) throws Exception {
                Log.e("asads", "ds" + new Gson().toJson(memberIds));

                if (memberIds.size() > 0) {
                    memberId = memberIds.get(0).Value;
                }
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
    public void showInfoDialog(final Context mContext, final String member) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_succesfull, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        // Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        TextView tv_info = infoDialog.findViewById(R.id.tv_info);
        String agent_no = "<b><font color=#000 >Member Id :  </font></b> <font color=#03A9F4> " + member + "</font>";
        tv_info.setText(Html.fromHtml(agent_no));
        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (frags.equals("frag")) {
                    ((CCUserHomeActivity) getActivity()).backForDetails();
                } else {
                    ((HouseholdHomeActivity) getActivity()).backForDetails();
                }
                infoDialog.dismiss();

            }
        });
//      //  btn_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                infoDialog.dismiss();
//            }
//        });
        infoDialog.show();
    }


}

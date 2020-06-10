package xact.idea.camelia.Activity.Household;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Database.DataSource.AuthDataSources;
import xact.idea.camelia.Database.DataSource.BlockDataSources;
import xact.idea.camelia.Database.DataSource.BloodGroupDataSources;
import xact.idea.camelia.Database.DataSource.CCDataSources;
import xact.idea.camelia.Database.DataSource.DistrictDataSources;
import xact.idea.camelia.Database.DataSource.DivisionDataSources;
import xact.idea.camelia.Database.DataSource.FemaleDataSources;
import xact.idea.camelia.Database.DataSource.HouseholdDataSources;
import xact.idea.camelia.Database.DataSource.MaritialStatusDataSources;
import xact.idea.camelia.Database.DataSource.MeasurementsDataSources;
import xact.idea.camelia.Database.DataSource.MedicineDatasources;
import xact.idea.camelia.Database.DataSource.MemberHabitDataSources;
import xact.idea.camelia.Database.DataSource.MemberIdDatasources;
import xact.idea.camelia.Database.DataSource.MemberMedicineDataSources;
import xact.idea.camelia.Database.DataSource.MemberMyselfDataSources;
import xact.idea.camelia.Database.DataSource.OccupationDataSources;
import xact.idea.camelia.Database.DataSource.QuestionsDataSources;
import xact.idea.camelia.Database.DataSource.ReferralHistoryDatasources;
import xact.idea.camelia.Database.DataSource.StudyClassDatasources;
import xact.idea.camelia.Database.DataSource.SurveyDataSources;
import xact.idea.camelia.Database.DataSource.UHCDataSources;
import xact.idea.camelia.Database.DataSource.UnionDataSources;
import xact.idea.camelia.Database.DataSource.UpazilaDatasources;
import xact.idea.camelia.Database.DataSource.VisitDataSources;
import xact.idea.camelia.Database.DataSource.WardDatasources;
import xact.idea.camelia.Database.MainDataBase;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.Medicine;
import xact.idea.camelia.Database.Model.MemberHabit;
import xact.idea.camelia.Database.Model.MemberId;
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Occupation;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.Database.Model.ReferHistory;
import xact.idea.camelia.Database.Model.StudyClass;
import xact.idea.camelia.Database.Model.Survey;
import xact.idea.camelia.Database.Model.UHC;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Ward;
import xact.idea.camelia.Database.Repository.AuthRepository;
import xact.idea.camelia.Database.Repository.BlockRepository;
import xact.idea.camelia.Database.Repository.BloodGroupRepository;
import xact.idea.camelia.Database.Repository.CCRepository;
import xact.idea.camelia.Database.Repository.DistrictRepository;
import xact.idea.camelia.Database.Repository.DivisionRepository;
import xact.idea.camelia.Database.Repository.FemaleRepository;
import xact.idea.camelia.Database.Repository.HouseholdRepository;
import xact.idea.camelia.Database.Repository.MaritialStatusRepository;
import xact.idea.camelia.Database.Repository.MeasurementsRepository;
import xact.idea.camelia.Database.Repository.MedicineRepository;
import xact.idea.camelia.Database.Repository.MemberHabitRepository;
import xact.idea.camelia.Database.Repository.MemberIdRepository;
import xact.idea.camelia.Database.Repository.MemberMedicineRepository;
import xact.idea.camelia.Database.Repository.MemberMyselfRepository;
import xact.idea.camelia.Database.Repository.OccupationRepository;
import xact.idea.camelia.Database.Repository.QustionsRepository;
import xact.idea.camelia.Database.Repository.ReferRepository;
import xact.idea.camelia.Database.Repository.StudyClassRepository;
import xact.idea.camelia.Database.Repository.SurveyRepository;
import xact.idea.camelia.Database.Repository.UHCRepository;
import xact.idea.camelia.Database.Repository.UnionRepository;
import xact.idea.camelia.Database.Repository.UpazilaRepository;
import xact.idea.camelia.Database.Repository.VisitRepository;
import xact.idea.camelia.Database.Repository.WardRepository;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Network.IRetrofitApi;
import xact.idea.camelia.NetworkModel.BlockResponses;
import xact.idea.camelia.NetworkModel.BloodGroupResponses;
import xact.idea.camelia.NetworkModel.CCModelresponse;
import xact.idea.camelia.NetworkModel.DistrictResponses;
import xact.idea.camelia.NetworkModel.DivisionResponses;
import xact.idea.camelia.NetworkModel.GenderResponses;
import xact.idea.camelia.NetworkModel.HouseholdGetResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdListResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdPostModel;
import xact.idea.camelia.NetworkModel.HouseholdResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdUploadModel;
import xact.idea.camelia.NetworkModel.KhanaServeyResponseModel;
import xact.idea.camelia.NetworkModel.KhanaServeyUploadModel;
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.MedicalHistoryResponseModel;
import xact.idea.camelia.NetworkModel.MedicalHistoryUpload;
import xact.idea.camelia.NetworkModel.MedicineResponses;
import xact.idea.camelia.NetworkModel.MemberAlocatePostModel;
import xact.idea.camelia.NetworkModel.MemberAlocateResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialUploadModel;
import xact.idea.camelia.NetworkModel.MemberGetResponseModel;
import xact.idea.camelia.NetworkModel.MemberPrescriptionResponseModel;
import xact.idea.camelia.NetworkModel.MemberResponseModel;
import xact.idea.camelia.NetworkModel.MemberUploadModel;
import xact.idea.camelia.NetworkModel.OccupationResponses;
import xact.idea.camelia.NetworkModel.ReferallPostModel;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.NetworkModel.UHCModel;
import xact.idea.camelia.NetworkModel.UnionResponses;
import xact.idea.camelia.NetworkModel.UpazilaResponses;
import xact.idea.camelia.NetworkModel.WardResponses;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class HouseHoldActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    LinearLayout linear_dashboard;
    LinearLayout linear_member_status;
    LinearLayout linear_summary;
    LinearLayout linear_logout;
    LinearLayout linear_sync;

    RelativeLayout relative;
    TextView tv_store;
    TextView text_welcome;
    TextView text_sync;
    TextView text_log_out;
    TextView text_website;
    TextView text_cc_sending;
    TextView text_household;
    TextView text_dashboard;
    IRetrofitApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold);
        mService = Common.getApiXact();
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.root_rlt_dashboard));
        linear_sync = findViewById(R.id.linear_sync);
        linear_dashboard = findViewById(R.id.linear_dashboard);
        text_website = findViewById(R.id.text_website);
        linear_member_status = findViewById(R.id.linear_member_status);
        linear_summary = findViewById(R.id.linear_summary);
        linear_logout = findViewById(R.id.linear_logout);
        relative = findViewById(R.id.relative);
        tv_store = findViewById(R.id.tv_store);
        text_welcome = findViewById(R.id.text_welcome);
        text_sync = findViewById(R.id.text_sync);
        text_log_out = findViewById(R.id.text_log_out);
        text_household = findViewById(R.id.text_household);
        text_cc_sending = findViewById(R.id.text_cc_sending);
        text_dashboard = findViewById(R.id.text_dashboard);
        Paper.init(this);
        String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
        Paper.book().write("language", language);
        updateView((String) Paper.book().read("language"));
        tv_store.setSelected(true);
        linear_logout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                //  getSharedPreferences(SharedPreferenceUtil.TYPE_USER_ID, 0).edit().clear().apply();
                SharedPreferenceUtil.removeShared(HouseHoldActivity.this, SharedPreferenceUtil.TYPE_USER_ID);
                Intent intent = new Intent(HouseHoldActivity.this, LoginActivity.class);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
                finishAffinity();
            }
        });
        linear_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
            }
        });
        linear_member_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.putExtra("EXTRA_SESSION", "status");
                startActivity(intent);
            }
        });
        linear_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.putExtra("EXTRA_SESSION", "summary");
                startActivity(intent);
            }
        });
        linear_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog(HouseHoldActivity.this);
            }
        });

        if (SharedPreferenceUtil.getSync(HouseHoldActivity.this).equals("on")) {
            linear_sync.setBackground(getResources().getDrawable(R.drawable.background_programitical));

        } else {
            linear_sync.setBackground(getResources().getDrawable(R.drawable.background_black));
        }

    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(this, language);
        Resources resources = context.getResources();
        tv_store.setText(resources.getString(R.string.real_time));
        text_welcome.setText(resources.getString(R.string.welcome));
        text_sync.setText(resources.getString(R.string.sync));
        text_website.setText(resources.getString(R.string.website));
        text_log_out.setText(resources.getString(R.string.log_out));
        text_cc_sending.setText(resources.getString(R.string.cc_information));
        text_household.setText(resources.getString(R.string.family));
        text_dashboard.setText(resources.getString(R.string.dashboard));
    }

    boolean t1 = true;

    private void loadHousehold() {

        compositeDisposable.add(Common.householdRepository.getHouseHoldItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
            @Override
            public void accept(List<HouseHold> houseHoldList) throws Exception {
                Log.e("Division", "Division" + new Gson().toJson(houseHoldList));
                HouseholdUploadModel householdUpload = new HouseholdUploadModel();
                MemberUploadModel model = new MemberUploadModel();
                MedicalHistoryUpload medicalHistoryUpload = new MedicalHistoryUpload();
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
                householdUpload.user_credential = auth.email;
                ArrayList<HouseholdUploadModel.Data> sync = new ArrayList<>();
                ArrayList<MemberUploadModel.Data> syncMember = new ArrayList<>();
                for (HouseHold houseHold : houseHoldList) {
                    HouseholdUploadModel.Data householdUploadModel = new HouseholdUploadModel.Data();
                    householdUploadModel.block_id = String.valueOf(houseHold.BlockId);
                    householdUploadModel.district_id = String.valueOf(houseHold.DistrictId);
                    householdUploadModel.division_id = String.valueOf(houseHold.DivisionId);
                    householdUploadModel.union_id = String.valueOf(houseHold.UnionId);
                    householdUploadModel.upazila_id = String.valueOf(houseHold.UpazilaId);
                    householdUploadModel.ward_id = String.valueOf(houseHold.WordId);
                    householdUploadModel.family_member = String.valueOf(houseHold.FamilyMember);
                    householdUploadModel.income_per_month = String.valueOf(houseHold.FamilyIncome);
                    householdUploadModel.hh_number = String.valueOf(houseHold.HH);
                    householdUploadModel.village = houseHold.VillageName;
                    householdUploadModel.sub_hh_number = String.valueOf(houseHold.SHH);
                    householdUploadModel.household_uniqe_id = houseHold.UniqueId;
                    syncMember.addAll(getMemberMyself(houseHold.UniqueId, auth.email));
                    ////
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);

                    String value = "";
                    if (language.equals("en")) {
                        value = houseHold.DateValue;
                        if (value.equals("--")) {
                            value = Utils.getValue(houseHold.DateValue);
                        }
                    } else {
                        value = Utils.getValue(houseHold.DateValue);
                        if (value.equals("--")) {
                            value = houseHold.DateValue;
                        }
                    }
                    ///
                    householdUploadModel.created_at = value;
                    householdUploadModel.created_at = houseHold.DateValue;
                    householdUploadModel.status = "1";
                    sync.add(householdUploadModel);
                }
                householdUpload.data = sync;
                model.user_credential = auth.email;
                model.data = syncMember;
                Log.e("sync", "sync" + new Gson().toJson(householdUpload));
                Log.e("sync1", "sync1" + new Gson().toJson(model));
                showLoadingProgress(HouseHoldActivity.this);
                compositeDisposable.add(mService.postHouseholdUpload(householdUpload).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<HouseholdResponseModel>() {
                    @Override
                    public void accept(HouseholdResponseModel memberResponseModel) throws Exception {
                        Log.e("Execute", "----1" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Execute", "----12" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));

                showLoadingProgress(HouseHoldActivity.this);
                compositeDisposable.add(mService.postMemberUpload(model).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberResponseModel>() {
                    @Override
                    public void accept(MemberResponseModel memberResponseModel) throws Exception {
                        Log.e("Execute", "------2" + new Gson().toJson(memberResponseModel));
                        Common.memberMyselfRepository.updateStatus("1","0");
                        dismissLoadingProgress();


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Execute", "------21" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));


            }
        }));
    }


    private void medicineList() {
        showLoadingProgress(HouseHoldActivity.this);

        compositeDisposable.add(Common.memberMedicineRepository.getMemberMedicineItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMedicine>>() {
            @Override
            public void accept(List<MemberMedicine> memberMedicineList) throws Exception {
                MedicalHistoryUpload data = new MedicalHistoryUpload();
                ArrayList<MedicalHistoryUpload.Data> medicalHistoryUploadList = new ArrayList<>();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(System.currentTimeMillis());
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
                String currentDate = "";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                if (language.equals("en")) {
                    currentDate = formatter.format(date);
                    if (currentDate.equals("--")) {
                        currentDate = Utils.getValue(formatter.format(date));
                    }
                } else {
                    currentDate = Utils.getValue(formatter.format(date));
                    if (currentDate.equals("--")) {
                        currentDate = formatter.format(date);
                    }
                }
                ArrayList<MedicalHistoryUpload.Data.Details> memberMyselvesdetails = new ArrayList<>();
                for (MemberMedicine memberMedicine : memberMedicineList) {
                    MedicalHistoryUpload.Data mdata = new MedicalHistoryUpload.Data();
                    mdata.household_uniqe_id = memberMedicine.household_uniqe_id;
                    mdata.member_id = memberMedicine.MemberId;
                    mdata.member_national_id = memberMedicine.member_national_id;
                    memberMyselvesdetails = getMedicalHistoryDetailsData(memberMedicine.member_unique_code, currentDate, memberMedicine.id);
                    mdata.member_unique_code = memberMedicine.member_unique_code;
                    mdata.status = "1";
                    mdata.update_no = "1";
                    mdata.created_at = currentDate;
                    mdata.details = memberMyselvesdetails;
                    medicalHistoryUploadList.add(mdata);
                    dismissLoadingProgress();
                }
                data.data = medicalHistoryUploadList;
                data.user_credential = auth.email;
                compositeDisposable.add(mService.postMedicalHistoryUpload(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MedicalHistoryResponseModel>() {
                    @Override
                    public void accept(MedicalHistoryResponseModel memberResponseModel) throws Exception {
                        Log.e("Execute", "------3" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Execute", "-------31" + throwable.getMessage());
                        dismissLoadingProgress();
//                        if (medicine=false){
//                            medicineList();
//                            medicine=true;
//                        }

                    }
                }));
                Log.e("Medical", "Medical" + new Gson().toJson(data));
            }
        }));


    }

    boolean beahve = false;
    boolean medicine = false;

    private void getBehaviorialList() {
        showLoadingProgress(HouseHoldActivity.this);

        compositeDisposable.add(Common.memberHabitRepository.getMemberHabitItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberHabit>>() {
            @Override
            public void accept(List<MemberHabit> memberMedicineList) throws Exception {
                MemberBehaviorialUploadModel data = new MemberBehaviorialUploadModel();
                ArrayList<MemberBehaviorialUploadModel.Data> medicalHistoryUploadList = new ArrayList<>();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(System.currentTimeMillis());
                Date date1 = null;
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
                String currentDate = "";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                if (language.equals("en")) {
                    currentDate = formatter.format(date);
                    if (currentDate.equals("--")) {
                        currentDate = Utils.getValue(formatter.format(date));
                    }

                } else {
                    currentDate = Utils.getValue(formatter.format(date));
                    if (currentDate.equals("--")) {
                        currentDate = formatter.format(date);
                    }
                }
                ArrayList<MemberBehaviorialUploadModel.Data.Details> memberMyselvesdetails = new ArrayList<>();
                for (MemberHabit memberMedicine : memberMedicineList) {
                    MemberBehaviorialUploadModel.Data mdata = new MemberBehaviorialUploadModel.Data();
                    mdata.household_uniqe_id = memberMedicine.household_uniqe_id;
                    mdata.member_id = memberMedicine.MemberId;
                    mdata.member_national_id = memberMedicine.member_national_id;
                    memberMyselvesdetails = getBeahviorialHistoryDetailsData(memberMedicine.member_unique_code, currentDate, memberMedicine.id);
                    mdata.member_unique_code = memberMedicine.member_unique_code;
                    mdata.status = "1";
                    mdata.update_no = "1";
                    mdata.created_at = currentDate;
                    mdata.details = memberMyselvesdetails;
                    medicalHistoryUploadList.add(mdata);
                    dismissLoadingProgress();
                }
                data.data = medicalHistoryUploadList;
                data.user_credential = auth.email;
                showLoadingProgress(HouseHoldActivity.this);
                compositeDisposable.add(mService.postMemberBehaviorialUpload(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberBehaviorialResponseModel>() {
                    @Override
                    public void accept(MemberBehaviorialResponseModel memberResponseModel) throws Exception {
                        Log.e("Execute", "e1" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Execute", "e12" + throwable.getMessage());
//                        if (beahve=false){
//                            getBehaviorialList();
//                            beahve=true;
//                        }
                    }
                }));
                Log.e("Behaviorial", "Behaviorial" + new Gson().toJson(data));
            }
        }));
    }

    private ArrayList<MemberUploadModel.Data> getMemberMyself(String UniqueId, String credential) {
        final ArrayList<MemberUploadModel.Data> memberMyselves = new ArrayList<>();

        Flowable<List<MemberMyself>> myselfLists = Common.memberMyselfRepository.getMemberMyselfItemById(UniqueId);
        for (MemberMyself memberMyself : myselfLists.blockingFirst()) {
            MemberUploadModel.Data mData = new MemberUploadModel.Data();
            mData.household_uniqe_id = memberMyself.UniqueId;
            mData.unique_code = memberMyself.UniqueCode;
            mData.name = memberMyself.FullName;
            mData.death_date = memberMyself.DateOfDeath;
            mData.member_id = memberMyself.MemberId;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = "";

            String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
            if (language.equals("en")) {
                currentDate = formatter.format(memberMyself.CreatedDate);
                if (currentDate.equals("--")) {
                    currentDate = Utils.getValue(formatter.format(memberMyself.CreatedDate));
                }
            } else {
                currentDate = Utils.getValue(formatter.format(memberMyself.CreatedDate));
                if (currentDate.equals("--")) {
                    currentDate = formatter.format(memberMyself.CreatedDate);
                }
            }
            mData.updated_no = "1";
            mData.created_at = currentDate;
            mData.visit_date = memberMyself.VisitDate;
            mData.living_status = String.valueOf(memberMyself.LivingId);
            mData.blood_group = String.valueOf(memberMyself.BloodGroupId);
            mData.date_of_data_collection = currentDate;
            mData.birth_date = memberMyself.DateOfBirth;
            mData.mobile_number = memberMyself.MobileNumber;
            mData.referred_to = memberMyself.From;
            mData.id = memberMyself.id;
            mData.refer_to_id = memberMyself.To;
            mData.national_id = memberMyself.NationalId;
            mData.marital_status = String.valueOf(memberMyself.MaritialId);
            mData.education = String.valueOf(memberMyself.StudyId);
            mData.religion = String.valueOf(memberMyself.ReligionId);
            mData.occupation = String.valueOf(memberMyself.OccupationId);
            mData.sex = String.valueOf(memberMyself.GenderId);
            mData.head_of_house = String.valueOf(memberMyself.HouseHeadId);
            memberMyselves.add(mData);
            dismissLoadingProgress();
        }

        return memberMyselves;
    }

    private ArrayList<MedicalHistoryUpload.Data> getMedicalHistoryData(String date) {
        final ArrayList<MedicalHistoryUpload.Data> memberMyselves = new ArrayList<>();
        final ArrayList<MedicalHistoryUpload.Data.Details> memberMyselvesdetails = new ArrayList<>();
        showLoadingProgress(HouseHoldActivity.this);
        Flowable<List<MemberMedicine>> memberMedicineList = Common.memberMedicineRepository.getMemberMedicineItems();
        for (MemberMedicine memberMedicine : memberMedicineList.blockingFirst()) {
            MedicalHistoryUpload.Data mdata = new MedicalHistoryUpload.Data();
            mdata.household_uniqe_id = memberMedicine.household_uniqe_id;
            mdata.member_id = memberMedicine.MemberId;
            mdata.member_national_id = memberMedicine.member_national_id;
            mdata.household_uniqe_id = memberMedicine.member_unique_code;
            mdata.status = "1";
            mdata.created_at = date;
            mdata.details = memberMyselvesdetails;
            memberMyselves.add(mdata);
            dismissLoadingProgress();
        }
        Log.e("sync4", "sync4" + new Gson().toJson(memberMyselves));
        return memberMyselves;
    }

    private ArrayList<MedicalHistoryUpload.Data.Details> getMedicalHistoryDetailsData(String memberId, String date, int id) {
        final ArrayList<MedicalHistoryUpload.Data.Details> memberMyselves = new ArrayList<>();
        showLoadingProgress(HouseHoldActivity.this);
        Flowable<List<Questions>> questionsList = Common.qustionsRepository.getQuestionsItemById("medicine", memberId);
        for (Questions questions : questionsList.blockingFirst()) {
            MedicalHistoryUpload.Data.Details mdata = new MedicalHistoryUpload.Data.Details();

            if (questions.question.equals("Q55a")) {
                Log.e("answer__", "answer__" + questions.answer);
            }
            mdata.parent_question = "";
            mdata.member_id = memberId;
            mdata.answer = questions.answer;
            mdata.question = questions.question;
            mdata.question_type = questions.type;
            mdata.created_at = date;
            mdata.id = questions.id;
            mdata.master_id = id;
            memberMyselves.add(mdata);
            dismissLoadingProgress();
        }
        return memberMyselves;
    }

    private ArrayList<MemberBehaviorialUploadModel.Data.Details> getBeahviorialHistoryDetailsData(String memberId, String date, int id) {
        final ArrayList<MemberBehaviorialUploadModel.Data.Details> memberMyselves = new ArrayList<>();
        showLoadingProgress(HouseHoldActivity.this);
        Flowable<List<Questions>> questionsList = Common.qustionsRepository.getQuestionsItemById("behavioral", memberId);
        for (Questions questions : questionsList.blockingFirst()) {
            MemberBehaviorialUploadModel.Data.Details mdata = new MemberBehaviorialUploadModel.Data.Details();
           // mdata.update_no = "2";
            mdata.parent_question = "";
            mdata.member_id = memberId;
            mdata.answer = questions.answer;
            mdata.question = questions.question;
            mdata.created_at = date;
            mdata.id = questions.id;
            mdata.question_type = questions.type;
            mdata.master_id = id;
            memberMyselves.add(mdata);
            dismissLoadingProgress();
        }
        return memberMyselves;
    }

    private void loadSurvey() {

        showLoadingProgress(HouseHoldActivity.this);

        compositeDisposable.add(Common.surveyRepository.getSurveyItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Survey>>() {
            @Override
            public void accept(List<Survey> memberMedicineList) throws Exception {
                KhanaServeyUploadModel data = new KhanaServeyUploadModel();
                ArrayList<KhanaServeyUploadModel.Data> medicalHistoryUploadList = new ArrayList<>();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));

                ArrayList<KhanaServeyUploadModel.Data.KhanaDetails> memberMyselvesdetails = new ArrayList<>();
                for (Survey measurements : memberMedicineList) {
                    KhanaServeyUploadModel.Data mdata = new KhanaServeyUploadModel.Data();
                    mdata.id = measurements.id;


                    String currentDate = "";
                    try {

                        String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                        if (language.equals("en")) {
                            currentDate = formatter.format(measurements.CreatedDate);
                            if (currentDate.equals("--")) {
                                currentDate = Utils.getValue(formatter.format(measurements.CreatedDate));
                            }
                        } else {
                            currentDate = Utils.getValue(formatter.format(measurements.CreatedDate));
                            if (currentDate.equals("--")) {
                                currentDate = formatter.format(measurements.CreatedDate);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Date date = new Date(System.currentTimeMillis());

                        String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                        if (language.equals("en")) {
                            currentDate = formatter.format(date);
                        } else {
                            currentDate = Utils.getValue(formatter.format(date));
                        }

                    }
                    memberMyselvesdetails = getKhanaDetailsData(String.valueOf(measurements.id), currentDate);
                    mdata.status = "1";
                    mdata.update_no = "0";
                    mdata.created_by = "0";
                    mdata.created_at = currentDate;
                    mdata.household_uniqe_id = measurements.UniqueId;
                    mdata.khana_details = memberMyselvesdetails;
                    medicalHistoryUploadList.add(mdata);
                    dismissLoadingProgress();
                }
                data.data = medicalHistoryUploadList;
                data.user_credential = auth.email;
                //medicalHistoryUploadList.clear();

                compositeDisposable.add(mService.postKhanaServeyUpload(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<KhanaServeyResponseModel>() {
                    @Override
                    public void accept(KhanaServeyResponseModel memberResponseModel) throws Exception {
                        Log.e("KhanaServey", "KhanaServey" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("KhanaServey", "KhanaServey" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));
                Log.e("sync2", "sync2" + new Gson().toJson(data));
                Log.e("sync2", "sync2" + new Gson().toJson(data));
            }
        }));

    }

    private ArrayList<KhanaServeyUploadModel.Data.KhanaDetails> getKhanaDetailsData(String id, String Date) {
        final ArrayList<KhanaServeyUploadModel.Data.KhanaDetails> memberMyselves = new ArrayList<>();
        showLoadingProgress(HouseHoldActivity.this);

        Flowable<List<Questions>> questionsList = Common.qustionsRepository.getQuestionsItemById("survey", id);

        for (Questions questions : questionsList.blockingFirst()) {
            KhanaServeyUploadModel.Data.KhanaDetails mdata = new KhanaServeyUploadModel.Data.KhanaDetails();
            mdata.id = Integer.parseInt(questions.member_id);
            mdata.question = questions.question;
            mdata.answer = questions.answer;
            mdata.created_at = Date;
            mdata.question_type = questions.type;
            mdata.update_no = "0";
            memberMyselves.add(mdata);
            dismissLoadingProgress();
        }
        return memberMyselves;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDB();
        if (Common.householdRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
              downloadHousehold();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      downloadWorkingArea();

                    }
                }, 500);
                dismissLoadingProgress();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        if (Common.studyClassRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                StudyClass studyClass = new StudyClass();
                studyClass.StudyClassId = -1;
                studyClass.class_name_en = "Select";
                studyClass.class_name_bn = "সিলেক্ট";
                studyClass.note_en = "";
                studyClass.note_bn = "";
                studyClass.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                studyClass.ln = language;
                Common.studyClassRepository.insertToStudyClass(studyClass);
                loadStudyClass();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.occupationRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                Occupation occupation = new Occupation();
                occupation.OccupationId = -1;
                occupation.occupation_name_en = "Select";
                occupation.occupation_name_bn = "সিলেক্ট";
                occupation.note_en = "";
                occupation.note_bn = "";
                occupation.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                occupation.ln = language;
                Common.occupationRepository.insertToOccupation(occupation);
                loadOccupation();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.femaleRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                Female female = new Female();
                female.FemaleId = -1;
                female.gender_name_en = "Select";
                female.gender_name_bn = "সিলেক্ট";
                female.note_en = "";
                female.note_bn = "";
                female.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                female.ln = language;
                Common.femaleRepository.insertToFemale(female);
                loadGender();

            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.maritialStatusRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                MaritialStatus maritialStatus = new MaritialStatus();
                maritialStatus.MaritialId = -1;
                maritialStatus.marital_name_en = "Select";
                maritialStatus.marital_name_bn = "সিলেক্ট";
                maritialStatus.note_en = "";
                maritialStatus.note_bn = "";
                maritialStatus.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                maritialStatus.ln = language;
                Common.maritialStatusRepository.insertToMaritialStatus(maritialStatus);
                loadMaritialStatus();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.blockRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {

                Block block = new Block();
                block.BlockId = -1;
                block.block_name_en = "Select";
                block.block_name_bn = "সিলেক্ট";
                block.block_shortname_en = "";
                block.block_shortname_bn = "";
                block.block_code = "-1";
                block.note_en = "";
                block.note_bn = "";
                block.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                block.ln = language;
                Common.blockRepository.insertToBlock(block);
                loadBlock();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.wardRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {

                Ward ward = new Ward();
                ward.WardId = -1;
                ward.ward_name_en = "Select";
                ward.ward_name_bn = "সিলেক্ট";
                ward.ward_shortname_bn = "";
                ward.ward_shortname_en = "";
                ward.ward_code = "-1";
                ward.note_en = "";
                ward.note_bn = "";
                ward.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                ward.ln = language;
                Common.wardRepository.insertToWard(ward);
                loadWard();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.bloodGroupRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                BloodGroup bloodGroup = new BloodGroup();
                bloodGroup.BloodId = -1;
                bloodGroup.blood_group_name_en = "Select";
                bloodGroup.blood_group_name_bn = "সিলেক্ট";
                bloodGroup.note_en = "";
                bloodGroup.note_bn = "";
                bloodGroup.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                bloodGroup.ln = language;
                Common.bloodGroupRepository.insertToBloodGroup(bloodGroup);

                loadBloodGroup();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.divisionRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {

                Division division = new Division();
                division.DivisionId = -1;
                division.division_name_en = "Select";
                division.division_name_bn = "সিলেক্ট";
                division.division_shortname_en = "";
                division.division_shortname_bn = "";
                division.division_code = "-1";
                division.note_en = "";
                division.note_bn = "";
                division.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                division.ln = language;
                Common.divisionRepository.insertToDivision(division);
                loadDivision();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.unionRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                Unions unions1 = new Unions();
                unions1.UnionId = -1;
                unions1.upazila_id = -1;
                unions1.union_name_en = "Select";
                unions1.union_name_bn = "সিলেক্ট";
                unions1.union_shortname_en = "";
                unions1.union_shortname_bn = "";
                unions1.union_code = "-1";
                unions1.note_en = "";
                unions1.note_bn = "";
                unions1.status = "";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                unions1.ln = language;
                Common.unionRepository.insertToUnion(unions1);
                loadUnion();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.districtRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                District district = new District();
                district.DistrictId = -1;
                district.DivisionId = -1;
                district.district_name_en = "Select";
                district.district_name_bn = "সিলেক্ট";
                district.district_shortname_en = "";
                district.district_shortname_bn = "";
                district.district_code = "-1";
                district.note_en = "";
                district.note_bn = "";
                district.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                district.ln = language;
                Common.districtRepository.insertToDistrict(district);
                loadDistrict();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.upazilaRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                Upazila upazila = new Upazila();
                upazila.UpazilaId = -1;
                upazila.district_id = -1;
                upazila.upazila_code = "-1";
                upazila.upazila_name_en = "Select";
                upazila.upazila_name_bn = "সিলেক্ট";
                upazila.upazila_shortname_en = "";
                upazila.upazila_shortname_bn = "";
                upazila.note_en = "";
                upazila.note_bn = "";
                upazila.status = "1";
                String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                upazila.ln = language;
                Common.upazilaRepository.insertToUpazila(upazila);
                loadUpazila();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.medicineRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                loadMedicine();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.memberIdRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                loadMemberId();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        } else {

            int maxId = Common.memberIdRepository.maxValue();
            MemberId memberId = Common.memberIdRepository.getMemberIdNo(String.valueOf(maxId));
            if (!SharedPreferenceUtil.getUserRole(HouseHoldActivity.this).equals(memberId.From)) {
                Common.memberIdRepository.emptyMember();
                loadMemberId();
            }

        }
        if (Common.uhcRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                loadUHC();

            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.ccRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                loadCC();

            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
//        int value = Common.memberIdRepository.maxValue();
//
//        MemberId memberId = Common.memberIdRepository.getMemberIdNo(String.valueOf(value));
//        Log.e("memberId", "memberId" + memberId.Value);
    }

    private void loadMedicine() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getMedicines().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MedicineResponses>() {
            @Override
            public void accept(MedicineResponses medicineResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(medicineResponses));
                for (MedicineResponses.Data wards : medicineResponses.data) {
                    Medicine medicine = new Medicine();
                    medicine.MedicineId = wards.id;
                    medicine.group_type_id = wards.group_type_id;
                    medicine.Name = wards.name;
                    medicine.short_name = wards.short_name;
                    medicine.note = wards.note;
                    medicine.status = wards.status;
                    medicine.disease = wards.disease;
                    medicine.group_id = wards.group_type.id;
                    medicine.group_name = wards.group_type.name;
                    medicine.group_note = wards.group_type.note;
                    medicine.short_name = wards.group_type.short_name;
                    Common.medicineRepository.insertToMedicine(medicine);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadMemberId() {
        showLoadingProgress(HouseHoldActivity.this);
        MemberAlocatePostModel memberAlocatePostModel = new MemberAlocatePostModel();
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
        int maxValue = Common.memberIdRepository.maxValue();
        if (maxValue > 0) {
            MemberId memberId = Common.memberIdRepository.getMemberIdNo(String.valueOf(maxValue));
            Log.e("memberId", "memberId" + memberId.Value);
            Flowable<List<MemberId>> memberIdList = Common.memberIdRepository.getMemberIdItems();
            if (memberIdList.blockingFirst().size() > 0) {
                memberAlocatePostModel.data.last_used_id = memberId.Value;
            } else {
                memberAlocatePostModel.data.last_used_id = "";
            }
        } else {
            memberAlocatePostModel.data.last_used_id = "";
        }

        Log.e("auth", "auth" + auth.user_id);
        memberAlocatePostModel.user_credential = auth.email;
        compositeDisposable.add(mService.getMemberAlocate(memberAlocatePostModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberAlocateResponseModel>() {
            @Override
            public void accept(MemberAlocateResponseModel upazilaResponses) throws Exception {
                Log.e("loadMemberId", "loadMemberId" + new Gson().toJson(upazilaResponses));
                for (MemberAlocateResponseModel.Data.AllocatedMember alocateResponseModel : upazilaResponses.data.newly_allocated_member_ids) {
                    MemberId id = new MemberId();
                    id.Value = alocateResponseModel.generated_member_id;
                    id.From = SharedPreferenceUtil.getUserRole(HouseHoldActivity.this);
                    Common.memberIdRepository.insertToMemberId(id);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("loadMemberId", "loadMemberId" + throwable.getMessage());
                dismissLoadingProgress();
            }
        }));

    }

    private void loadStudyClass() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getStudyClass().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<StudyClassResponses>() {
            @Override
            public void accept(StudyClassResponses studyClassResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(studyClassResponses));
                for (StudyClassResponses.Data stdy : studyClassResponses.data) {
                    StudyClass studyClass = new StudyClass();
                    studyClass.StudyClassId = stdy.id;
                    studyClass.class_name_en = stdy.class_name_en;
                    studyClass.class_name_bn = stdy.class_name_bn;
                    studyClass.note_en = stdy.note_en;
                    studyClass.note_bn = stdy.note_bn;
                    studyClass.status = stdy.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    studyClass.ln = language;
                    Common.studyClassRepository.insertToStudyClass(studyClass);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadOccupation() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getOccupation().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<OccupationResponses>() {
            @Override
            public void accept(OccupationResponses occupationResponses) throws Exception {
                Log.e("Occupation", "Occupation" + new Gson().toJson(occupationResponses));
                for (OccupationResponses.Data occupations : occupationResponses.data) {
                    Occupation occupation = new Occupation();
                    occupation.OccupationId = occupations.id;
                    occupation.occupation_name_en = occupations.occupation_name_en;
                    occupation.occupation_name_bn = occupations.occupation_name_bn;
                    occupation.note_en = occupations.note_en;
                    occupation.note_bn = occupations.note_bn;
                    occupation.status = occupations.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    occupation.ln = language;
                    Common.occupationRepository.insertToOccupation(occupation);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadMaritialStatus() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getMaritialStatus().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MaritialStatusResponses>() {
            @Override
            public void accept(MaritialStatusResponses maritialStatusResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(maritialStatusResponses));
                for (MaritialStatusResponses.Data maritial : maritialStatusResponses.data) {
                    MaritialStatus maritialStatus = new MaritialStatus();
                    maritialStatus.MaritialId = maritial.id;
                    maritialStatus.marital_name_en = maritial.marital_name_en;
                    maritialStatus.marital_name_bn = maritial.marital_name_bn;
                    maritialStatus.note_en = maritial.note_en;
                    maritialStatus.note_bn = maritial.note_bn;
                    maritialStatus.status = maritial.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    maritialStatus.ln = language;
                    Common.maritialStatusRepository.insertToMaritialStatus(maritialStatus);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadGender() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getGender().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<GenderResponses>() {
            @Override
            public void accept(GenderResponses genderResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(genderResponses));
                for (GenderResponses.Data genders : genderResponses.data) {
                    Female female = new Female();
                    female.FemaleId = genders.id;
                    female.gender_name_en = genders.gender_name_en;
                    female.gender_name_bn = genders.gender_name_bn;
                    female.note_en = genders.note_en;
                    female.note_bn = genders.note_bn;
                    female.status = genders.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    female.ln = language;
                    Common.femaleRepository.insertToFemale(female);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadWard() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getWard().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<WardResponses>() {
            @Override
            public void accept(WardResponses wardResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(wardResponses));
                for (WardResponses.Data wards : wardResponses.data) {
                    Ward ward = new Ward();
                    ward.WardId = wards.id;
                    ward.ward_name_en = wards.ward_name_en;
                    ward.ward_name_bn = wards.ward_name_bn;
                    ward.ward_shortname_bn = wards.ward_shortname_bn;
                    ward.ward_shortname_en = wards.ward_shortname_en;
                    ward.ward_code = String.valueOf(wards.id);
                    ward.note_en = wards.note_en;
                    ward.note_bn = wards.note_bn;
                    ward.status = wards.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    ward.ln = language;
                    Common.wardRepository.insertToWard(ward);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadBlock() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getBlock().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<BlockResponses>() {
            @Override
            public void accept(BlockResponses blockResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(blockResponses));
                for (BlockResponses.Data blocks : blockResponses.data) {
                    Block block = new Block();
                    block.BlockId = blocks.id;
                    block.block_name_en = blocks.block_name_en;
                    block.block_name_bn = blocks.block_name_bn;
                    block.block_shortname_en = blocks.block_shortname_en;
                    block.block_shortname_bn = blocks.block_shortname_bn;
                    block.block_code = String.valueOf(blocks.id);
                    block.note_en = blocks.note_en;
                    block.note_bn = blocks.note_bn;
                    block.status = blocks.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    block.ln = language;
                    Common.blockRepository.insertToBlock(block);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadBloodGroup() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getBloodGroup().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<BloodGroupResponses>() {
            @Override
            public void accept(BloodGroupResponses bloodGroupResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(bloodGroupResponses));
                for (BloodGroupResponses.Data bloods : bloodGroupResponses.data) {
                    BloodGroup bloodGroup = new BloodGroup();
                    bloodGroup.BloodId = bloods.id;
                    bloodGroup.blood_group_name_en = bloods.blood_group_name_en;
                    bloodGroup.blood_group_name_bn = bloods.blood_group_name_bn;
                    bloodGroup.note_en = bloods.note_en;
                    bloodGroup.note_bn = bloods.note_bn;
                    bloodGroup.status = bloods.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    bloodGroup.ln = language;
                    Common.bloodGroupRepository.insertToBloodGroup(bloodGroup);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadUnion() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getUnion().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<UnionResponses>() {
            @Override
            public void accept(UnionResponses unionResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(unionResponses));
                for (UnionResponses.Data unions : unionResponses.data) {
                    Unions unions1 = new Unions();
                    unions1.UnionId = unions.id;
                    unions1.upazila_id = unions.upazila_id;
                    unions1.union_name_en = unions.union_name_en;
                    unions1.union_name_bn = unions.union_name_bn;
                    unions1.union_shortname_en = unions.union_shortname_en;
                    unions1.union_shortname_bn = unions.union_shortname_bn;
                    unions1.union_code = String.valueOf(unions.id);
                    unions1.code = unions.union_code;
                    unions1.note_en = unions.note_en;
                    unions1.note_bn = unions.note_bn;
                    unions1.status = unions.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    unions1.ln = language;
                    Common.unionRepository.insertToUnion(unions1);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadDivision() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getDivision().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<DivisionResponses>() {
            @Override
            public void accept(DivisionResponses divisionResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(divisionResponses));
                for (DivisionResponses.Data divisions : divisionResponses.data) {
                    Division division = new Division();
                    division.DivisionId = divisions.id;
                    division.code = divisions.division_code;
                    division.division_name_en = divisions.division_name_en;
                    division.division_name_bn = divisions.division_name_bn;
                    division.division_shortname_en = divisions.division_shortname_en;
                    division.division_shortname_bn = divisions.division_shortname_bn;
                    division.division_code = String.valueOf(divisions.id);
                    division.note_en = divisions.note_en;
                    division.note_bn = divisions.note_bn;
                    division.status = divisions.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    division.ln = language;
                    Common.divisionRepository.insertToDivision(division);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadDistrict() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getDistrictClass().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<DistrictResponses>() {
            @Override
            public void accept(DistrictResponses districtResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(districtResponses));
                for (DistrictResponses.Data districts : districtResponses.data) {
                    District district = new District();
                    district.DistrictId = districts.id;
                    district.code = districts.district_code;
                    district.DivisionId = districts.division_id;
                    district.district_name_en = districts.district_name_en;
                    district.district_name_bn = districts.district_name_bn;
                    district.district_shortname_en = districts.district_shortname_en;
                    district.district_shortname_bn = districts.district_shortname_bn;
                    district.district_code = String.valueOf(districts.id);
                    district.note_en = districts.note_en;
                    district.note_bn = districts.note_bn;
                    district.status = districts.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    district.ln = language;
                    Common.districtRepository.insertToDistrict(district);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadUpazila() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getUpazilaClass().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<UpazilaResponses>() {
            @Override
            public void accept(UpazilaResponses upazilaResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(upazilaResponses));
                for (UpazilaResponses.Data upazilas : upazilaResponses.data) {
                    Upazila upazila = new Upazila();
                    upazila.UpazilaId = upazilas.id;
                    upazila.code = upazilas.upazila_code;
                    upazila.upazila_code = String.valueOf(upazilas.id);
                    upazila.district_id = upazilas.district_id;
                    upazila.upazila_name_en = upazilas.upazila_name_en;
                    upazila.upazila_name_bn = upazilas.upazila_name_bn;
                    upazila.upazila_shortname_en = upazilas.upazila_shortname_en;
                    upazila.upazila_shortname_bn = upazilas.upazila_shortname_bn;
                    upazila.note_en = upazilas.note_en;
                    upazila.note_bn = upazilas.note_bn;
                    upazila.status = upazilas.status;
                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    upazila.ln = language;
                    Common.upazilaRepository.insertToUpazila(upazila);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadCC() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getCC().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<CCModelresponse>() {
            @Override
            public void accept(CCModelresponse ccModelresponse) throws Exception {
                Log.e("study", "study" + new Gson().toJson(ccModelresponse));
                for (CCModelresponse.Data cc : ccModelresponse.data) {
                    CCModel ccModel = new CCModel();

                    ccModel.CCId = cc.id;
                    ccModel.name = cc.name;
                    ccModel.short_name = cc.short_name;
                    ccModel.information = cc.information;
                    ccModel.district_code = cc.district_code;
                    ccModel.division_code = cc.division_code;
                    ccModel.upazila_code = cc.upazila_code;
                    ccModel.union_code = cc.union_code;
                    ccModel.block_code = cc.block_code;
                    ccModel.ward_code = cc.ward_code;
                    ccModel.status = cc.status;
                    Common.ccRepository.insertToCCModel(ccModel);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void loadUHC() {
        showLoadingProgress(HouseHoldActivity.this);
        compositeDisposable.add(mService.getUHC().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<UHCModel>() {
            @Override
            public void accept(UHCModel uhcModel) throws Exception {
                Log.e("study", "study" + new Gson().toJson(uhcModel));
                for (UHCModel.Data uhc : uhcModel.data) {
                    UHC uhc1 = new UHC();
                    uhc1.name = uhc.name;
                    uhc1.code = uhc.code;
                    uhc1.information = uhc.information;
                    uhc1.district_code = uhc.district_code;
                    uhc1.division_code = uhc.division_code;
                    uhc1.upazila_code = uhc.upazila_code;
                    uhc1.union_code = uhc.union_code;
                    uhc1.block_code = uhc.block_code;
                    uhc1.ward_code = uhc.ward_code;
                    uhc1.status = uhc.status;
                    Common.uhcRepository.insertToUHC(uhc1);
                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));

    }

    private void initDB() {
        Common.mainDatabase = MainDataBase.getInstance(this);
        Common.authRepository = AuthRepository.getInstance(AuthDataSources.getInstance(Common.mainDatabase.authDao()));
        Common.blockRepository = BlockRepository.getInstance(BlockDataSources.getInstance(Common.mainDatabase.blockDao()));
        Common.bloodGroupRepository = BloodGroupRepository.getInstance(BloodGroupDataSources.getInstance(Common.mainDatabase.bloodGroupDao()));
        Common.districtRepository = DistrictRepository.getInstance(DistrictDataSources.getInstance(Common.mainDatabase.districtDao()));
        Common.divisionRepository = DivisionRepository.getInstance(DivisionDataSources.getInstance(Common.mainDatabase.divisionDao()));
        Common.femaleRepository = FemaleRepository.getInstance(FemaleDataSources.getInstance(Common.mainDatabase.femaleDao()));
        Common.maritialStatusRepository = MaritialStatusRepository.getInstance(MaritialStatusDataSources.getInstance(Common.mainDatabase.maritialStatusDao()));
        Common.occupationRepository = OccupationRepository.getInstance(OccupationDataSources.getInstance(Common.mainDatabase.occupationDao()));
        Common.studyClassRepository = StudyClassRepository.getInstance(StudyClassDatasources.getInstance(Common.mainDatabase.studyClassDao()));
        Common.unionRepository = UnionRepository.getInstance(UnionDataSources.getInstance(Common.mainDatabase.unionDao()));
        Common.upazilaRepository = UpazilaRepository.getInstance(UpazilaDatasources.getInstance(Common.mainDatabase.upazilaDao()));
        Common.wardRepository = WardRepository.getInstance(WardDatasources.getInstance(Common.mainDatabase.wardDao()));
        Common.householdRepository = HouseholdRepository.getInstance(HouseholdDataSources.getInstance(Common.mainDatabase.householdDao()));
        Common.measurementsRepository = MeasurementsRepository.getInstance(MeasurementsDataSources.getInstance(Common.mainDatabase.measurementsDao()));
        Common.medicineRepository = MedicineRepository.getInstance(MedicineDatasources.getInstance(Common.mainDatabase.medicineDao()));
        Common.memberHabitRepository = MemberHabitRepository.getInstance(MemberHabitDataSources.getInstance(Common.mainDatabase.memberHabitDao()));
        Common.memberMedicineRepository = MemberMedicineRepository.getInstance(MemberMedicineDataSources.getInstance(Common.mainDatabase.memberMedicineDao()));
        Common.memberMyselfRepository = MemberMyselfRepository.getInstance(MemberMyselfDataSources.getInstance(Common.mainDatabase.memberMyselfDao()));
        Common.surveyRepository = SurveyRepository.getInstance(SurveyDataSources.getInstance(Common.mainDatabase.surveyDao()));
        Common.memberIdRepository = MemberIdRepository.getInstance(MemberIdDatasources.getInstance(Common.mainDatabase.memberIdDao()));
        Common.qustionsRepository = QustionsRepository.getInstance(QuestionsDataSources.getInstance(Common.mainDatabase.questionsDao()));
        Common.referRepository = ReferRepository.getInstance(ReferralHistoryDatasources.getInstance(Common.mainDatabase.referralHistoryDao()));
        Common.ccRepository = CCRepository.getInstance(CCDataSources.getInstance(Common.mainDatabase.ccDao()));
        Common.uhcRepository = UHCRepository.getInstance(UHCDataSources.getInstance(Common.mainDatabase.uhcDao()));
        Common.visitRepository = VisitRepository.getInstance(VisitDataSources.getInstance(Common.mainDatabase.visitDao()));

    }


    private void downloadHousehold() {
        showLoadingProgress(HouseHoldActivity.this);

        final Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
        compositeDisposable.add(mService.getHouseholdList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<HouseholdListResponseModel>() {
            @Override
            public void accept(HouseholdListResponseModel uhcModel) throws Exception {
                Log.e("study", "study" + new Gson().toJson(uhcModel));
                for (HouseholdListResponseModel.Data house : uhcModel.data) {

                    if (auth.division.equals(house.division_id) && auth.district.equals(house.district_id)) {
                        HouseHold houseHold = new HouseHold();
                        HouseHold houseHoldNew = Common.householdRepository.getHouseHold(house.household_uniqe_id);

                        if (houseHoldNew != null) {
                            houseHold.MemberId = 100000000;
                            houseHold.id = houseHoldNew.id;
                            houseHold.BlockId = Integer.parseInt(house.block_id);
                            houseHold.DistrictId = Integer.parseInt(house.district_id);
                            houseHold.DivisionId = Integer.parseInt(house.division_id);
                            houseHold.UpazilaId = Integer.parseInt(house.upazila_id);
                            houseHold.UnionId = Integer.parseInt(house.union_id);
                            houseHold.WordId = Integer.parseInt(house.ward_id);
                            houseHold.HH = Integer.parseInt(house.hh_number);
                            //  houseHold.SHH = Integer.parseInt(house.sub_hh_number);
                            houseHold.UniqueId = house.household_uniqe_id;
                            // downloadMember(house.household_uniqe_id);
                            houseHold.VillageName = house.village;
                            houseHold.FamilyIncome = Double.parseDouble(house.income_per_month);
                            houseHold.FamilyMember = Integer.parseInt(house.family_member);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            downloadSurvey(house.household_uniqe_id);
                            Date date = null;
                            try {
                                date = new SimpleDateFormat("yyyy-MM-dd").parse(house.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date);
                            houseHold.DateValue = currentDate;
                            houseHold.Date = date;
                            Common.householdRepository.updateHouseHold(houseHold);
                        } else {
                            houseHold.MemberId = 100000000;
                            houseHold.BlockId = Integer.parseInt(house.block_id);
                            houseHold.DistrictId = Integer.parseInt(house.district_id);
                            downloadSurvey(house.household_uniqe_id);
                            houseHold.DivisionId = Integer.parseInt(house.division_id);
                            houseHold.UpazilaId = Integer.parseInt(house.upazila_id);
                            houseHold.UnionId = Integer.parseInt(house.union_id);
                            houseHold.WordId = Integer.parseInt(house.ward_id);
                            houseHold.HH = Integer.parseInt(house.hh_number);
//                            houseHold.SHH = Integer.parseInt(house.sub_hh_number);
                            houseHold.UniqueId = house.household_uniqe_id;
                            houseHold.VillageName = house.village;
                            // downloadMember(house.household_uniqe_id);
                            houseHold.FamilyIncome = Double.parseDouble(house.income_per_month);
                            houseHold.FamilyMember = Integer.parseInt(house.family_member);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = null;
                            try {
                                date = new SimpleDateFormat("yyyy-MM-dd").parse(house.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date);
                            houseHold.DateValue = currentDate;
                            houseHold.Date = date;
                            Common.householdRepository.insertToHouseHold(houseHold);
                        }

                    }


                }
                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
            }
        }));
    }

    private void downloadSurvey(final String houseHoldId) {
        HouseholdPostModel householdPostModel = new HouseholdPostModel();
        householdPostModel.household_uniqe_id = houseHoldId;
        compositeDisposable.add(mService.getHouseholdShow(householdPostModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<HouseholdGetResponseModel>() {
            @Override
            public void accept(HouseholdGetResponseModel memberResponseModel) throws Exception {
                Log.e("HouseholdSurvey", "Household" + new Gson().toJson(memberResponseModel));

                for (HouseholdGetResponseModel.Details khanas : memberResponseModel.khanaSurveys) {
                    DownSurveyList(khanas.details, houseHoldId);
                }
                dismissLoadingProgress();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("HouseholdSurvey", "Household" + throwable.getMessage());
                dismissLoadingProgress();
            }
        }));

    }

    String val = "";

    private void DownSurveyList(ArrayList<HouseholdGetResponseModel.Details.Khana> khanas, String houseHoldId) {
        Survey survey = new Survey();
        survey.UniqueId = houseHoldId;
        Date date11 = null;


        for (HouseholdGetResponseModel.Details.Khana khan : khanas) {
            val = khan.master_id;
            if (khan.question.equals("Q12")) {
                survey.SafeDrinkingYesNo = Integer.parseInt(khan.answer);
                try {
                    date11 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (khan.question.equals("Q12a")) {
                survey.SafeDrinkingDetails = Integer.parseInt(khan.answer);
            } else if (khan.question.equals("Q13")) {
                survey.SanitaryYesNo = Integer.parseInt(khan.answer);
            } else if (khan.question.equals("Q14")) {
                survey.BondhoChulaYesNo = Integer.parseInt(khan.answer);
            } else if (khan.question.equals("Q15")) {
                survey.BiomasFuelYesNo = Integer.parseInt(khan.answer);
            } else if (khan.question.equals("Q15a")) {
                survey.BiomasFuelDetails = Integer.parseInt(khan.answer);
            }


            if (khan.question.equals("Q12")) {

                Questions questions49 = Common.qustionsRepository.getQuestions("Q12", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            } else if (khan.question.equals("Q12a")) {
                Questions questions49 = Common.qustionsRepository.getQuestions("Q12a", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            } else if (khan.question.equals("Q13")) {
                Questions questions49 = Common.qustionsRepository.getQuestions("Q13", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            } else if (khan.question.equals("Q14")) {
                Questions questions49 = Common.qustionsRepository.getQuestions("Q14", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            } else if (khan.question.equals("Q15")) {
                Questions questions49 = Common.qustionsRepository.getQuestions("Q15", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            } else if (khan.question.equals("Q15a")) {
                Questions questions49 = Common.qustionsRepository.getQuestions("Q15a", khan.master_id);
                if (questions49 != null) {
                    Questions questions = new Questions();
                    questions.id = questions49.id;
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.updateQuestions(questions);
                } else {
                    Questions questions = new Questions();
                    questions.type = khan.question_type;
                    questions.question = khan.question;
                    questions.member_id = khan.master_id;
                    questions.answer = khan.answer;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(khan.created_at);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String currentDate = formatter.format(date1);
                    questions.date = currentDate;
                    Common.qustionsRepository.insertToQuestions(questions);
                }
            }


        }
        survey.CreatedDate = date11;
        Survey surveys = Common.surveyRepository.getSurveyNo(val);
        if (surveys != null) {
            survey.id = surveys.id;
            Common.surveyRepository.updateSurvey(survey);
        } else {
            Common.surveyRepository.insertToSurvey(survey);
        }

    }

    private void downloadWorkingArea() {
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
        String divisionId = "";
        String districtId = "";
        String upazilaId = "";
        String unionId = "";
        divisionId = auth.division;
        districtId = auth.district;
        upazilaId = auth.upazila;
        unionId = auth.union;
        if (divisionId != null && districtId != null && upazilaId != null && unionId != null) {
            compositeDisposable.add(Common.householdRepository.getHouseHoldItemByFour(divisionId, districtId, upazilaId, unionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
                @Override
                public void accept(List<HouseHold> houseHolds) throws Exception {


                    for (HouseHold houseHold : houseHolds) {
                        downloadMember(houseHold.UniqueId);
                    }
                }
            }));
        } else if (divisionId != null && districtId != null && upazilaId != null) {
            compositeDisposable.add(Common.householdRepository.getHouseHoldItemByThree(divisionId, districtId, upazilaId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
                @Override
                public void accept(List<HouseHold> houseHolds) throws Exception {
                    for (HouseHold houseHold : houseHolds) {
                        downloadMember(houseHold.UniqueId);
                    }
                }
            }));
        } else if (divisionId != null && districtId != null) {
            compositeDisposable.add(Common.householdRepository.getHouseHoldItemByTwo(divisionId, districtId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
                @Override
                public void accept(List<HouseHold> houseHolds) throws Exception {
                    for (HouseHold houseHold : houseHolds) {
                        downloadMember(houseHold.UniqueId);
                    }

                }
            }));
        } else if (divisionId != null) {
            compositeDisposable.add(Common.householdRepository.getHouseHoldItemByOne(divisionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
                @Override
                public void accept(List<HouseHold> houseHolds) throws Exception {
                    for (HouseHold houseHold : houseHolds) {
                        downloadMember(houseHold.UniqueId);
                    }

                }
            }));
        } else {
            compositeDisposable.add(Common.householdRepository.getHouseHoldItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
                @Override
                public void accept(List<HouseHold> houseHolds) throws Exception {
                    for (HouseHold houseHold : houseHolds) {
                        downloadMember(houseHold.UniqueId);
                    }

                }
            }));
        }
    }

    private void downloadMember(String uniueId) {
        showLoadingProgress(HouseHoldActivity.this);
        Log.e("asdas", "asd" + uniueId);
        ReferallPostModel referallPostModel = new ReferallPostModel();
        referallPostModel.household_uniqe_id = uniueId;
        compositeDisposable.add(mService.getMemberShow(referallPostModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberGetResponseModel>() {
            @Override
            public void accept(MemberGetResponseModel memberGetResponseModel) throws Exception {

                for (MemberGetResponseModel.Data member : memberGetResponseModel.member) {
                    Log.e("evan", "asd" + new Gson().toJson(memberGetResponseModel));
                    MemberMyself memberMyself1 = Common.memberMyselfRepository.getMemberId(member.unique_code);
                    if (memberMyself1 != null) {
                        MemberMyself memberMyself = new MemberMyself();
                        memberMyself.id = memberMyself1.id;
                        memberMyself.NationalId = member.national_id;
                        memberMyself.MobileNumber = member.mobile_number;
                        memberMyself.FullName = member.name;
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        Date date1 = null;

                        try {
                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(member.birth_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date date2 = null;
                        try {
                            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(member.created_at);
                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (member.death_date != null) {
                            Date date3 = null;
                            try {
                                date3 = new SimpleDateFormat("yyyy-MM-dd").parse(member.death_date);
                                String deathDate = formatter.format(date3);
                                memberMyself.DateOfDeath = deathDate;
                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        String birthDate = formatter.format(date1);

                        memberMyself.DateOfBirth = birthDate;
                        memberMyself.From = member.referred_to;
                        memberMyself.To = member.refer_to_id;
                        memberMyself.CreatedDate = date2;
                        downMedicalHistory(member.medical_history_details, member.unique_code, member.household_uniqe_id, member.national_id);
                        downBehaviorialHistory(member.behavioral_info_details, member.unique_code, member.household_uniqe_id, member.national_id);
                        memberMyself.GenderId = Integer.parseInt(member.sex);
                        memberMyself.BloodGroupId = Integer.parseInt(member.blood_group);
                        memberMyself.ReligionId = Integer.parseInt(member.religion);
                        memberMyself.StudyId = Integer.parseInt(member.education);
                        memberMyself.MaritialId = Integer.parseInt(member.marital_status);
                        memberMyself.OccupationId = Integer.parseInt(member.occupation);
                        memberMyself.LivingId = Integer.parseInt(member.living_status);
                        memberMyself.HouseHeadId = Integer.parseInt(member.head_of_house);
                        memberMyself.UniqueId = member.household_uniqe_id;
                        memberMyself.VisitDate = member.visit_date;
                        memberMyself.MemberId = member.member_id;
                        memberMyself.UniqueCode = member.unique_code;
                        memberMyself.Status = "1";
                        Common.memberMyselfRepository.updateMemberMyself(memberMyself);

                    } else {
                        MemberMyself memberMyself = new MemberMyself();
                        memberMyself.NationalId = member.national_id;
                        memberMyself.MobileNumber = member.mobile_number;
                        memberMyself.FullName = member.name;
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        Date date1 = null;


                        try {
                            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(member.birth_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date date2 = null;
                        try {
                            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(member.created_at);
                            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (member.death_date != null) {
                            Date date3 = null;
                            try {
                                date3 = new SimpleDateFormat("yyyy-MM-dd").parse(member.death_date);
                                String deathDate = formatter.format(date3);
                                memberMyself.DateOfDeath = deathDate;
                                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        downMedicalHistory(member.medical_history_details, member.unique_code, member.household_uniqe_id, member.national_id);
                        downBehaviorialHistory(member.behavioral_info_details, member.unique_code, member.household_uniqe_id, member.national_id);
                        String birthDate = formatter.format(date1);
                        memberMyself.DateOfBirth = birthDate;
                        memberMyself.CreatedDate = date2;
                        memberMyself.GenderId = Integer.parseInt(member.sex);
                        memberMyself.BloodGroupId = Integer.parseInt(member.blood_group);
                        memberMyself.ReligionId = Integer.parseInt(member.religion);
                        memberMyself.StudyId = Integer.parseInt(member.education);
                        memberMyself.MaritialId = Integer.parseInt(member.marital_status);
                        memberMyself.OccupationId = Integer.parseInt(member.occupation);
                        memberMyself.LivingId = Integer.parseInt(member.living_status);
                        memberMyself.HouseHeadId = Integer.parseInt(member.head_of_house);
                        memberMyself.UniqueId = member.household_uniqe_id;
                        memberMyself.VisitDate = member.visit_date;
                        memberMyself.MemberId = member.member_id;
                        memberMyself.UniqueCode = member.unique_code;
                        memberMyself.Status = "1";
                        memberMyself.From = member.referred_to;
                        memberMyself.To = member.refer_to_id;
                        Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
                    }
                }

                dismissLoadingProgress();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissLoadingProgress();
                Log.e("evan", "asd" + throwable.getMessage());
            }
        }));
    }

    private void downMedicalHistory(ArrayList<MemberGetResponseModel.Data.Visit> medical_history, String MemberId, String HouseholdId, String NationalId) {


        if (medical_history != null) {
            if (medical_history.size() > 1) {
                for (MemberGetResponseModel.Data.Visit visit : medical_history) {
                    MemberMedicine memberMedicine = new MemberMedicine();
                    MemberMedicine memberMed = Common.memberMedicineRepository.getMemberMedicineNo(MemberId);
                    if (memberMed != null) {
                        memberMedicine.id = memberMed.id;
                        memberMedicine.MemberId = memberMed.MemberId;
                        memberMedicine.household_uniqe_id = memberMed.household_uniqe_id;
                        memberMedicine.member_unique_code = memberMed.member_unique_code;
                        memberMedicine.member_national_id = String.valueOf(memberMed.member_national_id);
                        Common.memberMedicineRepository.updateMemberMedicine(memberMedicine);
                    } else {
                        memberMedicine.MemberId = MemberId;
                        memberMedicine.household_uniqe_id = HouseholdId;
                        memberMedicine.member_unique_code = MemberId;
                        memberMedicine.member_national_id = NationalId;
                        Common.memberMedicineRepository.insertToMemberMedicine(memberMedicine);
                    }


                    if (visit.question.equals("Q49")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q49", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q49a")) {
                        Questions questions49a = Common.qustionsRepository.getQuestions("Q49a", visit.member_id);
                        if (questions49a != null) {
                            Questions questions = new Questions();
                            questions.id = questions49a.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q49b")) {
                        Questions questions49b = Common.qustionsRepository.getQuestions("Q49b", visit.member_id);
                        if (questions49b != null) {
                            Questions questions = new Questions();
                            questions.id = questions49b.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q49c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q49c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q50")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q50", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q50a")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q50a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q50b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q50b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q50c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q50c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q51")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q51", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q51a")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q51a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q51b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q51b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q51c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q51c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q52")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q52", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q52a")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q52a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q52b")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q52b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q52c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q52c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q53")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q53", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q53a")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q53a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q53b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q53b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q53c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q53c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q54")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q54", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q54a")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q54a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q54b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q54b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q54c")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q54c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q55")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q55", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q55a")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q55a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q55b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q55b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q55c")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q55c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q56")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q56", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q56a")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q56a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q56b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q56b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q56c")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q56c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q57")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q57", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q57a")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q57a", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q57b")) {

                        Questions questions49c = Common.qustionsRepository.getQuestions("Q57b", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q57c")) {
                        Questions questions49c = Common.qustionsRepository.getQuestions("Q57c", visit.member_id);
                        if (questions49c != null) {
                            Questions questions = new Questions();
                            questions.id = questions49c.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    }

                }
            }


        }

    }

    private void downBehaviorialHistory(ArrayList<MemberGetResponseModel.Data.Behavior> behavioral_info, String MemberId, String HouseholdId, String NationalId) {

        if (behavioral_info != null) {

            if (behavioral_info.size() > 1) {
                for (MemberGetResponseModel.Data.Behavior visit : behavioral_info) {
                    MemberHabit memberMedicine = new MemberHabit();
                    MemberHabit memberMed = Common.memberHabitRepository.getMemberHabitNo(MemberId);
                    if (memberMed != null) {
                        memberMedicine.id = memberMed.id;
                        memberMedicine.MemberId = memberMed.MemberId;
                        memberMedicine.household_uniqe_id = memberMed.household_uniqe_id;
                        memberMedicine.member_unique_code = memberMed.member_unique_code;
                        memberMedicine.member_national_id = String.valueOf(memberMed.member_national_id);
                        Common.memberHabitRepository.updateMemberHabit(memberMedicine);
                    } else {
                        memberMedicine.MemberId = MemberId;
                        memberMedicine.household_uniqe_id = HouseholdId;
                        memberMedicine.member_unique_code = MemberId;
                        memberMedicine.member_national_id = NationalId;
                        Common.memberHabitRepository.insertToMemberHabit(memberMedicine);
                    }
                    if (visit.question.equals("Q32")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q32", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q32a")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q32a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q32b")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q32b", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q33")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q33", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q33a")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q33a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q34")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q34", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q34a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q34a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q35")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q35", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q35a")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q35a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q36")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q36", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q37")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q37", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q38")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q38", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q39")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q39", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q41")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q41", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q42")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q42", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q42a")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q42a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q43")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                    ////////////////////////////////////END ERA/////////////////////////////////
                    else if (visit.question.equals("Q43a1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43a16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                    ////////////////////////////////////////

                    else if (visit.question.equals("Q44a1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a17")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a17", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a18")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a18", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a19")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a19", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a20")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a20", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a21")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a21", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b17")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b17", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b18")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b18", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b19")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b19", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b20")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b20", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b21")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b21", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c10")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c10", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c11")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c11", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c12")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c12", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c13")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c13", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c14")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c14", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c15")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c15", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c16")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c16", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c17")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c17", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c18")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c18", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c19")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c19", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c20")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c20", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c21")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c21", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                    //////////////////////////////

                    else if (visit.question.equals("Q45a1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c1")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c1", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c2")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c2", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c3")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c3", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c4")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c4", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c5")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c5", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c6")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c6", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c7")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c7", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c8")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c8", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c9")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c9", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                    else if (visit.question.equals("Q47")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q47", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                    ////////////////////////////////////END ERA/////////////////////////////////

                    else if (visit.question.equals("Q43a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43b")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43b", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q43c")) {
                        Questions questions49 = Common.qustionsRepository.getQuestions("Q43c", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }

                    } else if (visit.question.equals("Q44")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44b")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44b", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q44c")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q44c", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45b")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45b", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q45c")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q45c", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46b")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46b", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q46c")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q46c", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q48")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q48", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    } else if (visit.question.equals("Q48a")) {

                        Questions questions49 = Common.qustionsRepository.getQuestions("Q48a", visit.member_id);
                        if (questions49 != null) {
                            Questions questions = new Questions();
                            questions.id = questions49.id;
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.updateQuestions(questions);
                        } else {
                            Questions questions = new Questions();
                            questions.type = visit.question_type;
                            questions.question = visit.question;
                            questions.member_id = visit.member_id;
                            questions.answer = visit.answer;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visit.created_at);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String currentDate = formatter.format(date1);
                            questions.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions);
                        }
                    }
                }
            }

        }
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

    private void loadReferHistory() {
        compositeDisposable.add(Common.referRepository.getReferHistoryItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<ReferHistory>>() {
            @Override
            public void accept(List<ReferHistory> referHistoryList) throws Exception {
                MemberPrescriptionResponseModel memberPrescriptionResponseModel = new MemberPrescriptionResponseModel();
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
                memberPrescriptionResponseModel.user_credential = auth.email;
                ArrayList<MemberPrescriptionResponseModel.Data> sync = new ArrayList<>();
                for (ReferHistory referHistory : referHistoryList) {

                    MemberPrescriptionResponseModel.Data data = new MemberPrescriptionResponseModel.Data();
                    data.from = referHistory.From;
                    data.id = referHistory.ids;
                    data.from_id = referHistory.FromId;
                    data.to = referHistory.To;
                    data.to_id = referHistory.ToId;
                    data.household_uniqe_id = referHistory.UniqueId;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    String visitDate = "";

                    String language = SharedPreferenceUtil.getLanguage(HouseHoldActivity.this);
                    if (language.equals("en")) {
                        visitDate = referHistory.VisitDate;
                        if (visitDate.equals("--")) {
                            visitDate = Utils.getValue(referHistory.VisitDate);
                        }
                    } else {
                        visitDate = Utils.getValue(referHistory.VisitDate);
                        if (visitDate.equals("--")) {
                            visitDate = referHistory.VisitDate;
                        }
                    }

                    data.visit_date = visitDate;
                    data.member_unique_code = referHistory.MemberUniqueCode;
                    data.reason = referHistory.Reason;
                    data.ref_type = referHistory.Type;
                    String currentDat1 = "";
                    if (language.equals("en")) {
                        currentDat1 = formatter.format(referHistory.Date);
                        if (currentDat1.equals("--")) {
                            currentDat1 = Utils.getValue(formatter.format(referHistory.Date));
                        }
                    } else {
                        currentDat1 = Utils.getValue(formatter.format(referHistory.Date));
                        if (currentDat1.equals("--")) {
                            currentDat1 = formatter.format(referHistory.Date);
                        }
                    }

                    data.created_at = currentDat1;
                    data.update_no = "1";
                    sync.add(data);


                }
                memberPrescriptionResponseModel.data = sync;
                showLoadingProgress(HouseHoldActivity.this);
                compositeDisposable.add(mService.postReferral_historyList(memberPrescriptionResponseModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberBehaviorialResponseModel>() {
                    @Override
                    public void accept(MemberBehaviorialResponseModel memberResponseModel) throws Exception {
                        Log.e("Referalla", "Referalla" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Referalla", "Referalla" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));
                Log.e("Referalla", "Referalla" + new Gson().toJson(memberPrescriptionResponseModel));

            }


        }));
    }

    public void showInfoDialog(final Context mContext) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_sync, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);

        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                downloadHousehold();
                loadMemberId();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        downloadWorkingArea();
                    }
                }, 500);
                infoDialog.dismiss();

            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadHousehold();
                loadSurvey();
                medicineList();
                getBehaviorialList();
                loadReferHistory();

                SharedPreferenceUtil.saveShared(HouseHoldActivity.this, SharedPreferenceUtil.SYNC, "off");
                linear_sync.setBackground(getResources().getDrawable(R.drawable.background_black));
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
}

package xact.idea.camelia.Activity.Household;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.LoginActivity;
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
import xact.idea.camelia.Database.Model.StudyClass;
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
import xact.idea.camelia.Database.Repository.WardRepository;
import xact.idea.camelia.Network.IRetrofitApi;
import xact.idea.camelia.NetworkModel.BlockResponses;
import xact.idea.camelia.NetworkModel.BloodGroupResponses;
import xact.idea.camelia.NetworkModel.CCModelresponse;
import xact.idea.camelia.NetworkModel.DistrictResponses;
import xact.idea.camelia.NetworkModel.DivisionResponses;
import xact.idea.camelia.NetworkModel.GenderResponses;
import xact.idea.camelia.NetworkModel.HouseholdListResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdUploadModel;
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.MedicalHistoryResponseModel;
import xact.idea.camelia.NetworkModel.MedicalHistoryUpload;
import xact.idea.camelia.NetworkModel.MedicineResponses;
import xact.idea.camelia.NetworkModel.MemberAlocatePostModel;
import xact.idea.camelia.NetworkModel.MemberAlocateResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialUploadModel;
import xact.idea.camelia.NetworkModel.MemberGetResponseModel;
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
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
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
        linear_member_status = findViewById(R.id.linear_member_status);
        linear_summary = findViewById(R.id.linear_summary);
        linear_logout = findViewById(R.id.linear_logout);
        relative = findViewById(R.id.relative);
        tv_store = findViewById(R.id.tv_store);
        tv_store.setSelected(true);
        linear_logout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
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
                //  finish();
            }
        });
        linear_member_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);

                intent.putExtra("EXTRA_SESSION", "status");
                startActivity(intent);
                //  finish();
            }
        });
        linear_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);

                intent.putExtra("EXTRA_SESSION", "summary");
                startActivity(intent);
                //  finish();
            }
        });
        linear_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadHousehold();
//                medicineList();
//                getBehaviorialList();

                downloadHousehold();
            }
        });
    }

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
                    householdUploadModel.sub_hh_number = String.valueOf(houseHold.SHH);
                    householdUploadModel.household_uniqe_id = houseHold.UniqueId;
                    syncMember.addAll(getMemberMyself(houseHold.UniqueId, auth.email));
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
                        Log.e("HouseholdResponseModel", "HouseholdResponseModel" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("HouseholdResponseModel", "HouseholdResponseModel" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));

                showLoadingProgress(HouseHoldActivity.this);
                compositeDisposable.add(mService.postMemberUpload(model).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberResponseModel>() {
                    @Override
                    public void accept(MemberResponseModel memberResponseModel) throws Exception {
                        Log.e("memberResponseModel", "memberResponseModel" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("memberResponseModel", "memberResponseModel" + throwable.getMessage());
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
                Date date1 = null;
                Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
                String currentDate = formatter.format(date);
                ArrayList<MedicalHistoryUpload.Data.Details> memberMyselvesdetails = new ArrayList<>();
                for (MemberMedicine memberMedicine : memberMedicineList) {
                    MedicalHistoryUpload.Data mdata = new MedicalHistoryUpload.Data();
                    mdata.household_uniqe_id = memberMedicine.household_uniqe_id;
                    mdata.member_id = memberMedicine.MemberId;
                    mdata.member_national_id = memberMedicine.member_national_id;
                    memberMyselvesdetails = getMedicalHistoryDetailsData(memberMedicine.MemberId, currentDate, memberMedicine.id);
                    mdata.member_unique_code = memberMedicine.household_uniqe_id;
                    mdata.status = "1";
                    mdata.update_no = "0";
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
                        Log.e("MedicalBehaviorial", "MedicalBehaviorial" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MedicalBehaviorial", "MedicalBehaviorial" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));
                Log.e("sync2", "sync2" + new Gson().toJson(data));
            }
        }));


    }

    private void getBehaviorialList(){
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
                String currentDate = formatter.format(date);
                ArrayList<MemberBehaviorialUploadModel.Data.Details> memberMyselvesdetails = new ArrayList<>();
                for (MemberHabit memberMedicine : memberMedicineList) {
                    MemberBehaviorialUploadModel.Data mdata = new MemberBehaviorialUploadModel.Data();
                    mdata.household_uniqe_id = memberMedicine.household_uniqe_id;
                    mdata.member_id = memberMedicine.MemberId;
                    mdata.member_national_id = memberMedicine.member_national_id;
                    memberMyselvesdetails = getBeahviorialHistoryDetailsData(memberMedicine.MemberId, currentDate, memberMedicine.id);
                    mdata.member_unique_code = memberMedicine.household_uniqe_id;
                    mdata.status = "1";
                    mdata.update_no = "0";
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
                        Log.e("MemberBehaviorial", "MemberBehaviorialResponse" + new Gson().toJson(memberResponseModel));
                        dismissLoadingProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MemberBehaviorial", "MemberBehaviorialResponsel" + throwable.getMessage());
                        dismissLoadingProgress();
                    }
                }));
                Log.e("sync2", "sync2" + new Gson().toJson(data));
            }
        }));
    }

    private ArrayList<MemberUploadModel.Data> getMemberMyself(String UniqueId, String credential) {
        final ArrayList<MemberUploadModel.Data> memberMyselves = new ArrayList<>();

        Flowable<List<MemberMyself>> myselfLists = Common.memberMyselfRepository.getMemberMyselfItemById(UniqueId);
        for (MemberMyself memberMyself : myselfLists.blockingFirst()) {
            MemberUploadModel.Data mData = new MemberUploadModel.Data();
            mData.household_uniqe_id = memberMyself.UniqueId;
            mData.unique_code = memberMyself.UniqueId;
            mData.name = memberMyself.FullName;
            mData.death_date = memberMyself.DateOfDeath;
            mData.member_id = memberMyself.MemberId;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = formatter.format(memberMyself.CreatedDate);
            mData.created_at = currentDate;
            mData.visit_date = memberMyself.VisitDate;
            mData.living_status = String.valueOf(memberMyself.LivingId);
            mData.blood_group = String.valueOf(memberMyself.BloodGroupId);
            mData.date_of_data_collection = currentDate;
            mData.birth_date = memberMyself.DateOfBirth;
            mData.mobile_number = memberMyself.MobileNumber;
            mData.referred_to = memberMyself.To;
            mData.id = memberMyself.id;
            mData.refer_to_id = memberMyself.From;
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
            mdata.parent_question = "";
            mdata.member_id = memberId;
            mdata.answer = questions.answer;
            mdata.question = questions.question;
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
            mdata.parent_question = "";
            mdata.member_id = memberId;
            mdata.answer = questions.answer;
            mdata.question = questions.question;
            mdata.created_at = date;
            mdata.id = questions.id;
            mdata.master_id = id;
            memberMyselves.add(mdata);
            dismissLoadingProgress();
        }
        return memberMyselves;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDB();
        if (Common.studyClassRepository.size() < 1) {
            if (Utils.broadcastIntent(HouseHoldActivity.this, relative)) {
                StudyClass studyClass = new StudyClass();
                studyClass.StudyClassId = -1;
                studyClass.class_name_en = "Select";
                studyClass.class_name_bn = "Select";
                studyClass.note_en = "";
                studyClass.note_bn = "";
                studyClass.status = "1";
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
                occupation.occupation_name_bn = "Select";
                occupation.note_en = "";
                occupation.note_bn = "";
                occupation.status = "1";
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
                female.gender_name_bn = "Select";
                female.note_en = "";
                female.note_bn = "";
                female.status = "1";
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
                maritialStatus.marital_name_bn = "Select";
                maritialStatus.note_en = "";
                maritialStatus.note_bn = "";
                maritialStatus.status = "1";
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
                block.block_name_bn = "Select";
                block.block_shortname_en = "";
                block.block_shortname_bn = "";
                block.block_code = "";
                block.note_en = "";
                block.note_bn = "";
                block.status = "1";
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
                ward.ward_name_bn = "Select";
                ward.ward_shortname_bn = "";
                ward.ward_shortname_en = "";
                ward.ward_code = "";
                ward.note_en = "";
                ward.note_bn = "";
                ward.status = "1";
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
                bloodGroup.blood_group_name_bn = "Select";
                bloodGroup.note_en = "";
                bloodGroup.note_bn = "";
                bloodGroup.status = "1";
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
                division.division_name_bn = "Select";
                division.division_shortname_en = "";
                division.division_shortname_bn = "";
                division.division_code = "";
                division.note_en = "";
                division.note_bn = "";
                division.status = "1";
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
                unions1.union_name_bn = "Select";
                unions1.union_shortname_en = "";
                unions1.union_shortname_bn = "";
                unions1.union_code = "";
                unions1.note_en = "";
                unions1.note_bn = "";
                unions1.status = "";
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
                district.district_name_bn = "Select";
                district.district_shortname_en = "";
                district.district_shortname_bn = "";
                district.district_code = "";
                district.note_en = "";
                district.note_bn = "";
                district.status = "1";
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
                upazila.upazila_name_en = "Select";
                upazila.upazila_name_bn = "Select";
                upazila.upazila_shortname_en = "";
                upazila.upazila_shortname_bn = "";
                upazila.note_en = "";
                upazila.note_bn = "";
                upazila.status = "1";
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
        memberAlocatePostModel.data.last_used_id = "";
        Log.e("auth", "auth" + auth.user_id);
        memberAlocatePostModel.user_credential = auth.email;
        compositeDisposable.add(mService.getMemberAlocate(memberAlocatePostModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberAlocateResponseModel>() {
            @Override
            public void accept(MemberAlocateResponseModel upazilaResponses) throws Exception {
                Log.e("loadMemberId", "loadMemberId" + new Gson().toJson(upazilaResponses));
                for (MemberAlocateResponseModel.Data.AllocatedMember alocateResponseModel : upazilaResponses.data.newly_allocated_member_ids) {
                    MemberId id = new MemberId();
                    id.Value = alocateResponseModel.generated_member_id;
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
                    ward.ward_code = wards.ward_code;
                    ward.note_en = wards.note_en;
                    ward.note_bn = wards.note_bn;
                    ward.status = wards.status;
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
                    block.block_code = blocks.block_code;
                    block.note_en = blocks.note_en;
                    block.note_bn = blocks.note_bn;
                    block.status = blocks.status;
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
                    unions1.union_code = unions.union_code;
                    unions1.note_en = unions.note_en;
                    unions1.note_bn = unions.note_bn;
                    unions1.status = unions.status;
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
                    division.division_name_en = divisions.division_name_en;
                    division.division_name_bn = divisions.division_name_bn;
                    division.division_shortname_en = divisions.division_shortname_en;
                    division.division_shortname_bn = divisions.division_shortname_bn;
                    division.division_code = divisions.division_code;
                    division.note_en = divisions.note_en;
                    division.note_bn = divisions.note_bn;
                    division.status = divisions.status;
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
                    district.DivisionId = districts.division_id;
                    district.district_name_en = districts.district_name_en;
                    district.district_name_bn = districts.district_name_bn;
                    district.district_shortname_en = districts.district_shortname_en;
                    district.district_shortname_bn = districts.district_shortname_bn;
                    district.district_code = districts.district_code;
                    district.note_en = districts.note_en;
                    district.note_bn = districts.note_bn;
                    district.status = districts.status;
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
                    upazila.district_id = upazilas.district_id;
                    upazila.upazila_name_en = upazilas.upazila_name_en;
                    upazila.upazila_name_bn = upazilas.upazila_name_bn;
                    upazila.upazila_shortname_en = upazilas.upazila_shortname_en;
                    upazila.upazila_shortname_bn = upazilas.upazila_shortname_bn;
                    upazila.note_en = upazilas.note_en;
                    upazila.note_bn = upazilas.note_bn;
                    upazila.status = upazilas.status;
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
    }


    private void downloadHousehold(){
        showLoadingProgress(HouseHoldActivity.this);

        final Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseHoldActivity.this));
        compositeDisposable.add(mService.getHouseholdList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<HouseholdListResponseModel>() {
            @Override
            public void accept(HouseholdListResponseModel uhcModel) throws Exception {
                Log.e("study", "study" + new Gson().toJson(uhcModel));
                for (HouseholdListResponseModel.Data house : uhcModel.data) {

                    if (auth.division.equals(house.division_id) && auth.district.equals(house.district_id)){
                        HouseHold houseHold = new HouseHold();
                        HouseHold houseHoldNew=Common.householdRepository.getHouseHold(house.household_uniqe_id);

                        if(houseHoldNew!=null){
                            houseHold.MemberId = 100000000;
                            houseHold.id = houseHoldNew.id;
                            houseHold.BlockId = Integer.parseInt(house.block_id);
                            houseHold.DistrictId = Integer.parseInt(house.district_id);;
                            houseHold.DivisionId = Integer.parseInt(house.division_id);;
                            houseHold.UpazilaId = Integer.parseInt(house.upazila_id);;
                            houseHold.UnionId = Integer.parseInt(house.union_id);;
                            houseHold.WordId = Integer.parseInt(house.ward_id);;
                            houseHold.HH = Integer.parseInt(house.hh_number);
                            houseHold.SHH = Integer.parseInt(house.sub_hh_number);
                            houseHold.UniqueId = house.household_uniqe_id;
                            downloadMember(house.household_uniqe_id);
                            houseHold.VillageName = house.village;
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
                            Common.householdRepository.updateHouseHold(houseHold);
                        }
                        else{
                            houseHold.MemberId = 100000000;
                            houseHold.BlockId = Integer.parseInt(house.block_id);
                            houseHold.DistrictId = Integer.parseInt(house.district_id);;
                            houseHold.DivisionId = Integer.parseInt(house.division_id);;
                            houseHold.UpazilaId = Integer.parseInt(house.upazila_id);;
                            houseHold.UnionId = Integer.parseInt(house.union_id);;
                            houseHold.WordId = Integer.parseInt(house.ward_id);;
                            houseHold.HH = Integer.parseInt(house.hh_number);
                            houseHold.SHH = Integer.parseInt(house.sub_hh_number);
                            houseHold.UniqueId = house.household_uniqe_id;
                            houseHold.VillageName = house.village;
                            downloadMember(house.household_uniqe_id);
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

    private void downloadMember(String uniueId){
        showLoadingProgress(HouseHoldActivity.this);
        Log.e("asdas", "asd" + uniueId);
        ReferallPostModel referallPostModel = new ReferallPostModel();
        referallPostModel.unique_code=uniueId;
        compositeDisposable.add(mService.getMemberShow(referallPostModel).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<MemberGetResponseModel>() {
            @Override
            public void accept(MemberGetResponseModel memberGetResponseModel) throws Exception {

                Log.e("evan", "asd" + new Gson().toJson(memberGetResponseModel));
                MemberMyself memberMyself1 = Common.memberMyselfRepository.getMemberId(memberGetResponseModel.member.member_id);
                if (memberMyself1 != null) {
                    MemberMyself memberMyself = new MemberMyself();
                    memberMyself.id = memberMyself1.id;
                    memberMyself.NationalId = memberGetResponseModel.member.national_id;
                    memberMyself.MobileNumber = memberGetResponseModel.member.mobile_number;
                    memberMyself.FullName = memberGetResponseModel.member.name;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                    Date date1 = null;

                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.birth_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date2 = null;
                    try {
                        date2 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.created_at);
                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date3 = null;
                    try {
                        date3 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.death_date);
                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String birthDate = formatter.format(date1);
                    String deathDate = formatter.format(date3);
                    memberMyself.DateOfBirth = birthDate;
                    memberMyself.DateOfDeath = deathDate;
                    memberMyself.CreatedDate = date2;
                    memberMyself.GenderId = Integer.parseInt(memberGetResponseModel.member.sex);
                    memberMyself.BloodGroupId =  Integer.parseInt(memberGetResponseModel.member.blood_group);
                    memberMyself.ReligionId =  Integer.parseInt(memberGetResponseModel.member.religion);
                    memberMyself.StudyId = Integer.parseInt(memberGetResponseModel.member.education);
                    memberMyself.MaritialId = Integer.parseInt(memberGetResponseModel.member.marital_status);
                    memberMyself.OccupationId = Integer.parseInt(memberGetResponseModel.member.occupation);
                    memberMyself.LivingId = Integer.parseInt(memberGetResponseModel.member.living_status);
                    memberMyself.HouseHeadId = Integer.parseInt(memberGetResponseModel.member.head_of_house);
                    memberMyself.UniqueId = memberGetResponseModel.member.household_uniqe_id;
                    memberMyself.VisitDate = memberGetResponseModel.member.visit_date;
                    memberMyself.MemberId = memberGetResponseModel.member.member_id;
                    memberMyself.Status = "1";
                    Common.memberMyselfRepository.updateMemberMyself(memberMyself);

                }
                else{
                    MemberMyself memberMyself = new MemberMyself();
                    memberMyself.NationalId = memberGetResponseModel.member.national_id;
                    memberMyself.MobileNumber = memberGetResponseModel.member.mobile_number;
                    memberMyself.FullName = memberGetResponseModel.member.name;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                    Date date1 = null;

                    try {
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.birth_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date2 = null;
                    try {
                        date2 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.created_at);
                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date3 = null;
                    try {
                        date3 = new SimpleDateFormat("yyyy-MM-dd").parse(memberGetResponseModel.member.death_date);
                        // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String birthDate = formatter.format(date1);
                    String deathDate = formatter.format(date3);
                    memberMyself.DateOfBirth = birthDate;
                    memberMyself.DateOfDeath = deathDate;
                    memberMyself.CreatedDate = date2;
                    memberMyself.GenderId = Integer.parseInt(memberGetResponseModel.member.sex);
                    memberMyself.BloodGroupId =  Integer.parseInt(memberGetResponseModel.member.blood_group);
                    memberMyself.ReligionId =  Integer.parseInt(memberGetResponseModel.member.religion);
                    memberMyself.StudyId = Integer.parseInt(memberGetResponseModel.member.education);
                    memberMyself.MaritialId = Integer.parseInt(memberGetResponseModel.member.marital_status);
                    memberMyself.OccupationId = Integer.parseInt(memberGetResponseModel.member.occupation);
                    memberMyself.LivingId = Integer.parseInt(memberGetResponseModel.member.living_status);
                    memberMyself.HouseHeadId = Integer.parseInt(memberGetResponseModel.member.head_of_house);
                    memberMyself.UniqueId = memberGetResponseModel.member.household_uniqe_id;
                    memberMyself.VisitDate = memberGetResponseModel.member.visit_date;
                    memberMyself.MemberId = memberGetResponseModel.member.member_id;
                    memberMyself.Status = "1";
                    Common.memberMyselfRepository.insertToMemberMyself(memberMyself);
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

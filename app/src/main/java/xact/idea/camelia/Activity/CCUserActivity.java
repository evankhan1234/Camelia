package xact.idea.camelia.Activity;

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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.Database.DataSource.AuthDataSources;
import xact.idea.camelia.Database.DataSource.BlockDataSources;
import xact.idea.camelia.Database.DataSource.BloodGroupDataSources;
import xact.idea.camelia.Database.DataSource.DistrictDataSources;
import xact.idea.camelia.Database.DataSource.DivisionDataSources;
import xact.idea.camelia.Database.DataSource.FemaleDataSources;
import xact.idea.camelia.Database.DataSource.HouseholdDataSources;
import xact.idea.camelia.Database.DataSource.MaritialStatusDataSources;
import xact.idea.camelia.Database.DataSource.MeasurementDetailsDataSources;
import xact.idea.camelia.Database.DataSource.MeasurementsDataSources;
import xact.idea.camelia.Database.DataSource.MedicineDatasources;
import xact.idea.camelia.Database.DataSource.MemberHabitDataSources;
import xact.idea.camelia.Database.DataSource.MemberIdDatasources;
import xact.idea.camelia.Database.DataSource.MemberMedicineDataSources;
import xact.idea.camelia.Database.DataSource.MemberMyselfDataSources;
import xact.idea.camelia.Database.DataSource.OccupationDataSources;
import xact.idea.camelia.Database.DataSource.QuestionsDataSources;
import xact.idea.camelia.Database.DataSource.StudyClassDatasources;
import xact.idea.camelia.Database.DataSource.SurveyDataSources;
import xact.idea.camelia.Database.DataSource.UnionDataSources;
import xact.idea.camelia.Database.DataSource.UpazilaDatasources;
import xact.idea.camelia.Database.DataSource.WardDatasources;
import xact.idea.camelia.Database.MainDataBase;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.Medicine;
import xact.idea.camelia.Database.Model.MemberId;
import xact.idea.camelia.Database.Model.Occupation;
import xact.idea.camelia.Database.Model.StudyClass;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Ward;
import xact.idea.camelia.Database.Repository.AuthRepository;
import xact.idea.camelia.Database.Repository.BlockRepository;
import xact.idea.camelia.Database.Repository.BloodGroupRepository;
import xact.idea.camelia.Database.Repository.DistrictRepository;
import xact.idea.camelia.Database.Repository.DivisionRepository;
import xact.idea.camelia.Database.Repository.FemaleRepository;
import xact.idea.camelia.Database.Repository.HouseholdRepository;
import xact.idea.camelia.Database.Repository.MaritialStatusRepository;
import xact.idea.camelia.Database.Repository.MeasurementDetailsRepository;
import xact.idea.camelia.Database.Repository.MeasurementsRepository;
import xact.idea.camelia.Database.Repository.MedicineRepository;
import xact.idea.camelia.Database.Repository.MemberHabitRepository;
import xact.idea.camelia.Database.Repository.MemberIdRepository;
import xact.idea.camelia.Database.Repository.MemberMedicineRepository;
import xact.idea.camelia.Database.Repository.MemberMyselfRepository;
import xact.idea.camelia.Database.Repository.OccupationRepository;
import xact.idea.camelia.Database.Repository.QustionsRepository;
import xact.idea.camelia.Database.Repository.StudyClassRepository;
import xact.idea.camelia.Database.Repository.SurveyRepository;
import xact.idea.camelia.Database.Repository.UnionRepository;
import xact.idea.camelia.Database.Repository.UpazilaRepository;
import xact.idea.camelia.Database.Repository.WardRepository;
import xact.idea.camelia.Network.IRetrofitApi;
import xact.idea.camelia.NetworkModel.BlockResponses;
import xact.idea.camelia.NetworkModel.BloodGroupResponses;
import xact.idea.camelia.NetworkModel.DistrictResponses;
import xact.idea.camelia.NetworkModel.DivisionResponses;
import xact.idea.camelia.NetworkModel.GenderResponses;
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.MedicineResponses;
import xact.idea.camelia.NetworkModel.MemberAlocatePostModel;
import xact.idea.camelia.NetworkModel.MemberAlocateResponseModel;
import xact.idea.camelia.NetworkModel.OccupationResponses;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.NetworkModel.UnionResponses;
import xact.idea.camelia.NetworkModel.UpazilaResponses;
import xact.idea.camelia.NetworkModel.WardResponses;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class CCUserActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    LinearLayout linear_dashboard;
    LinearLayout linear_member_status;
    LinearLayout linear_summary;
    LinearLayout linear_logout;
    LinearLayout linear_household_member;
    TextView tv_store;
    IRetrofitApi mService;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccuser);
        mService = Common.getApiXact();
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.root_rlt_dashboard));
        linear_dashboard = findViewById(R.id.linear_dashboard);
        linear_logout = findViewById(R.id.linear_logout);
        linear_member_status = findViewById(R.id.linear_member_status);
        linear_summary = findViewById(R.id.linear_summary);
        linear_household_member = findViewById(R.id.linear_household_member);
        tv_store = findViewById(R.id.tv_store);
        relative = findViewById(R.id.relative);
        tv_store.setSelected(true);
        linear_logout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CCUserActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
                finishAffinity();
            }
        });
        linear_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CCUserActivity.this, CCUserHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
                //  finish();
            }
        });
        linear_member_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CCUserActivity.this, CCUserHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "status");
                startActivity(intent);
                //  finish();
            }
        });
        linear_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CCUserActivity.this, CCUserHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "summary");
                startActivity(intent);
                //  finish();
            }
        });
        linear_household_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CCUserActivity.this, CCUserHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "household");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDB();
        if (Common.studyClassRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadStudyClass();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.occupationRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadOccupation();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.femaleRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadGender();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.maritialStatusRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadMaritialStatus();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.blockRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadBlock();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.wardRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadWard();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.bloodGroupRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadBloodGroup();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.divisionRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadDivision();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.unionRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadUnion();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.districtRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadDistrict();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.upazilaRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadUpazila();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (Common.medicineRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadMedicine();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        if (Common.memberIdRepository.size() < 1) {
            if (Utils.broadcastIntent(CCUserActivity.this, relative)) {
                loadMemberId();
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

    private void loadStudyClass() {
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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

    private void loadMedicine() {
        showLoadingProgress(CCUserActivity.this);
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

    private void loadBlock() {
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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
        showLoadingProgress(CCUserActivity.this);
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

    private void loadMemberId() {
        showLoadingProgress(CCUserActivity.this);
        MemberAlocatePostModel memberAlocatePostModel = new MemberAlocatePostModel();
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(CCUserActivity.this));
        memberAlocatePostModel.last_used_id = "";
        Log.e("auth", "auth" + auth.user_id);
        memberAlocatePostModel.user_id = "4";
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
        Common.measurementDetailsRepository = MeasurementDetailsRepository.getInstance(MeasurementDetailsDataSources.getInstance(Common.mainDatabase.measurementDetailsDao()));
        Common.memberIdRepository = MemberIdRepository.getInstance(MemberIdDatasources.getInstance(Common.mainDatabase.memberIdDao()));
        Common.qustionsRepository = QustionsRepository.getInstance(QuestionsDataSources.getInstance(Common.mainDatabase.questionsDao()));
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

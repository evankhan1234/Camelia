package xact.idea.camelia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.Medicine;
import xact.idea.camelia.Database.Model.Occupation;
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
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.MedicineResponses;
import xact.idea.camelia.NetworkModel.OccupationResponses;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.NetworkModel.UHCModel;
import xact.idea.camelia.NetworkModel.UnionResponses;
import xact.idea.camelia.NetworkModel.UpazilaResponses;
import xact.idea.camelia.NetworkModel.WardResponses;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class LanguageActivity extends AppCompatActivity {

    RelativeLayout relative_bangla;
    RelativeLayout relative_english;
    RelativeLayout relative;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        relative_bangla = findViewById(R.id.relative_bangla);
        relative = findViewById(R.id.relative);
        relative_english = findViewById(R.id.relative_english);
        Paper.init(this);
        mService=Common.getApiXact();
        String language= Paper.book().read("language");
        if (language==null){
            Paper.book().write("language","en");
        }
        relative_bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.saveShared(LanguageActivity.this, SharedPreferenceUtil.LANG,  "bn");

                startActivity(new Intent(LanguageActivity.this, LoginActivity.class));
                finish();
            }
        });
        relative_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.saveShared(LanguageActivity.this, SharedPreferenceUtil.LANG, "en");
                startActivity(new Intent(LanguageActivity.this, LoginActivity.class));
                finish();
            }
        });
        relative_english = findViewById(R.id.relative_english);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initDB();
        if (Common.studyClassRepository.size() < 1) {
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
                StudyClass studyClass = new StudyClass();
                studyClass.StudyClassId = -1;
                studyClass.class_name_en = "Select";
                studyClass.class_name_bn = "সিলেক্ট";
                studyClass.note_en = "";
                studyClass.note_bn = "";
                studyClass.status = "1";
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
                Occupation occupation = new Occupation();
                occupation.OccupationId = -1;
                occupation.occupation_name_en = "Select";
                occupation.occupation_name_bn = "সিলেক্ট";
                occupation.note_en = "";
                occupation.note_bn = "";
                occupation.status = "1";
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
                Female female = new Female();
                female.FemaleId = -1;
                female.gender_name_en = "Select";
                female.gender_name_bn = "সিলেক্ট";
                female.note_en = "";
                female.note_bn = "";
                female.status = "1";
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
                MaritialStatus maritialStatus = new MaritialStatus();
                maritialStatus.MaritialId = -1;
                maritialStatus.marital_name_en = "Select";
                maritialStatus.marital_name_bn = "সিলেক্ট";
                maritialStatus.note_en = "";
                maritialStatus.note_bn = "";
                maritialStatus.status = "1";
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {

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
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
                block.ln = language;
                Common.blockRepository.insertToBlock(block);
                loadBlock();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        if (Common.bloodGroupRepository.size() < 1) {
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
                BloodGroup bloodGroup = new BloodGroup();
                bloodGroup.BloodId = -1;
                bloodGroup.blood_group_name_en = "Select";
                bloodGroup.blood_group_name_bn = "সিলেক্ট";
                bloodGroup.note_en = "";
                bloodGroup.note_bn = "";
                bloodGroup.status = "1";
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {

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
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
                division.ln = language;
                Common.divisionRepository.insertToDivision(division);
                loadDivision();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        if (Common.districtRepository.size() < 1) {
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
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
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
            if (Utils.broadcastIntent(LanguageActivity.this, relative)) {
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
                String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
                upazila.ln = language;
                Common.upazilaRepository.insertToUpazila(upazila);
                loadUpazila();
            } else {
                Snackbar snackbar = Snackbar
                        .make(relative, "No Internet", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

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
    private void loadStudyClass() {
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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



    private void loadBlock() {
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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



    private void loadDivision() {
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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
        showLoadingProgress(LanguageActivity.this);
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
                    String language = SharedPreferenceUtil.getLanguage(LanguageActivity.this);
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


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
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

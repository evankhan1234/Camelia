package xact.idea.camelia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.Database.DataSource.AuthDataSources;
import xact.idea.camelia.Database.DataSource.BlockDataSources;
import xact.idea.camelia.Database.DataSource.BloodGroupDataSources;
import xact.idea.camelia.Database.DataSource.CCDataSources;
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
import xact.idea.camelia.Database.Repository.AuthRepository;
import xact.idea.camelia.Database.Repository.BlockRepository;
import xact.idea.camelia.Database.Repository.BloodGroupRepository;
import xact.idea.camelia.Database.Repository.CCRepository;
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
import xact.idea.camelia.NetworkModel.AuthPost;
import xact.idea.camelia.NetworkModel.AuthResponse;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class LoginActivity extends AppCompatActivity {

    Button sign_in;
    EditText edit_text_email;
    TextView forgot;
    TextView textView13;
    EditText edit_text_password;
    ImageView show_pass;
    boolean test = true;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    RelativeLayout rlt_root;
    LinearLayout layoutq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mService=Common.getApiXact();
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.rlt_root));
        sign_in = findViewById(R.id.sign_in);
        textView13 = findViewById(R.id.textView13);
        forgot = findViewById(R.id.forgot);
        show_pass = findViewById(R.id.show_pass);
        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        layoutq = findViewById(R.id.layout);
        Paper.init(this);
        String language=SharedPreferenceUtil.getLanguage(LoginActivity.this);
        Paper.book().write("language",language);
        updateView((String)Paper.book().read("language"));
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.broadcastIntent(LoginActivity.this, layoutq)) {
                    showLoadingProgress(LoginActivity.this);

                    if (!edit_text_email.getText().toString().equals("") && !edit_text_password.getText().toString().equals(""))
                    {

                        AuthPost authPost = new AuthPost();
                        authPost.email = edit_text_email.getText().toString();
                        authPost.password = edit_text_password.getText().toString();

                        Log.e("ff", "dgg" + Common.authRepository.size());
//                        if (Common.authRepository.size() > 0) {
//
//                            Auth login = Common.authRepository.getAuth(authPost.email,  authPost.password);
//                            if (login != null) {
//                                if (login.role_code.equals("hh")){
//                                    startActivity(new Intent(LoginActivity.this, HouseHoldActivity.class));
//                                    finish();
//                                }
//                                else {
//                                    startActivity(new Intent(LoginActivity.this, CCUserActivity.class));
//                                    finish();
//                                }
//
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Username and Password Incorrect", Toast.LENGTH_SHORT).show();
//                                dismissLoadingProgress();
//                            }
//                        } else {

                            Log.e("ff", "dgg" + new Gson().toJson(authPost));
                            compositeDisposable.add(mService.Login(authPost).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<AuthResponse>() {
                                @Override
                                public void accept(AuthResponse loginEntity) throws Exception {
                                    Log.e("ff", "dgg" + new Gson().toJson(loginEntity));
                                    Log.e("message", "message" + loginEntity.message);
                                    if (loginEntity.status_code==200)
                                    {

                                        SharedPreferenceUtil.saveShared(LoginActivity.this, SharedPreferenceUtil.TYPE_USER_ID, loginEntity.data.profile.user_id + "");
                                        SharedPreferenceUtil.saveShared(LoginActivity.this, SharedPreferenceUtil.USER_ROLE, loginEntity.data.role_code + "");


                                        Auth login = new Auth();
                                        login.user_id = loginEntity.data.profile.user_id;
                                        login.role_code = loginEntity.data.role_code;
                                        login.user_role = Integer.parseInt(loginEntity.data.user_role);
                                        login.role_name = loginEntity.data.role_name;
                                        login.email = loginEntity.data.user_email;
                                        login.fullname = loginEntity.data.fullname;
                                        login.password = edit_text_password.getText().toString();
                                        login.city = loginEntity.data.profile.city;
                                        login.address = loginEntity.data.profile.address;
                                        login.dob = loginEntity.data.profile.dob;
                                        login.gender = loginEntity.data.profile.gender;
                                        login.image = loginEntity.data.profile.image;
                                        login.phone = loginEntity.data.profile.phone;
                                        login.division = loginEntity.data.workingarea.division_id;
                                        login.district = loginEntity.data.workingarea.district_id;
                                        login.upazila = loginEntity.data.workingarea.upazila_id;
                                        login.union = loginEntity.data.workingarea.union_id;
                                        login.block = loginEntity.data.workingarea.block_id;
                                        login.ward = loginEntity.data.workingarea.ward_id;
                                        login.village = loginEntity.data.workingarea.village;
                                        Common.authRepository.insertToAuth(login);
                                        if (loginEntity.data.role_code.equals("hh")){
                                            startActivity(new Intent(LoginActivity.this, HouseHoldActivity.class));
                                            finish();
                                            dismissLoadingProgress();
                                        }
                                        else {
                                            startActivity(new Intent(LoginActivity.this, CCUserActivity.class));
                                            finish();
                                            dismissLoadingProgress();
                                        }

                                    }
                                    else {

                                            Toast.makeText(LoginActivity.this, loginEntity.message, Toast.LENGTH_SHORT).show();
                                            dismissLoadingProgress();

                                    }



                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    dismissLoadingProgress();
                                    Log.e("ff", "dgg" + throwable.getMessage());

                                }
                            }));



                    } else {
                        Toast.makeText(LoginActivity.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                        dismissLoadingProgress();
                    }

                } else {
                    Snackbar snackbar = Snackbar
                            .make(rlt_root, "No Internet", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

//        edit_text_password.addTextChangedListener(new
//
//                                                          TextWatcher() {
//                                                              @Override
//                                                              public void afterTextChanged(Editable mEdit) {
//                                                                  show_pass.setImageDrawable(getResources().getDrawable(R.drawable.show_password));
//                                                                  //  edit_text_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                                                              }
//
//                                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                                                              }
//
//                                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                                              }
//                                                          });
        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cursorPosition = edit_text_password.getSelectionStart();

                if (test) {
                    Log.e("show", "show");

                    test = false;
                    edit_text_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show_pass.setImageDrawable(getResources().getDrawable(R.drawable.hidden_password));

                } else {
                    Log.e("hidden", "hidden");
                    show_pass.setImageDrawable(getResources().getDrawable(R.drawable.show_password));
                    test = true;
                    edit_text_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edit_text_password.setSelection(cursorPosition);
            }
        });


    }

    private void updateView(String language) {
        Context context=LocaleHelper.setLocale(this,language);
        Resources resources= context.getResources();
        edit_text_email.setHint(resources.getString(R.string.email));
        edit_text_password.setHint(resources.getString(R.string.password));
        sign_in.setText(resources.getString(R.string.sign));
        forgot.setText(resources.getString(R.string.forgot));
        textView13.setText(resources.getString(R.string.account));
    }


    @Override
    protected void onResume() {
        super.onResume();
        initDB();
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
        Common.referRepository = ReferRepository.getInstance(ReferralHistoryDatasources.getInstance(Common.mainDatabase.referralHistoryDao()));
        Common.ccRepository = CCRepository.getInstance(CCDataSources.getInstance(Common.mainDatabase.ccDao()));
        Common.uhcRepository = UHCRepository.getInstance(UHCDataSources.getInstance(Common.mainDatabase.uhcDao()));
        Common.visitRepository = VisitRepository.getInstance(VisitDataSources.getInstance(Common.mainDatabase.visitDao()));
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

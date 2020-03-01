package xact.idea.camelia.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import xact.idea.camelia.Model.DropDownModel.BiomasFuelModel;
import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.CenterModel;
import xact.idea.camelia.Model.DropDownModel.ClinicModel;
import xact.idea.camelia.Model.DropDownModel.ControlDiseaseModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.FruitsCardModel;
import xact.idea.camelia.Model.DropDownModel.FruitsModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.ModerateModel;
import xact.idea.camelia.Model.DropDownModel.ModerateRecreationalModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SaltModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.Model.DropDownModel.TubewellModel;
import xact.idea.camelia.Model.DropDownModel.TypicalVigorousModel;
import xact.idea.camelia.Model.DropDownModel.TypicalVigorousRecreationModel;
import xact.idea.camelia.Model.DropDownModel.VegetableModel;
import xact.idea.camelia.Model.DropDownModel.VegetablesCardModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;


public class Utils {
    private static CustomProgressDialog sPdLoading = null;
    //    public static void showInfoDialog(final Context mContext) {
//
//        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
//        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflator.inflate(R.layout.layout_pop_up_nav, null);
//
//        infoDialog.setContentView(v);
//        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
//        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
//        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
//
//        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
//        btn_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferenceUtil.removeShared(mContext,SharedPreferenceUtil.TYPE_USER_ID);
//                infoDialog.dismiss();
//                mContext.startActivity(new Intent(mContext, LoginActivity.class));
//                ((Activity) mContext).finish();
//            }
//        });
//        btn_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                infoDialog.dismiss();
//            }
//        });
//        infoDialog.show();
//    }
//    public static void hideSoftKeyboard(Activity activity, EditText editText) {
////        InputMethodManager inputMethodManager =
////                (InputMethodManager) activity.getSystemService(
////                        Activity.INPUT_METHOD_SERVICE);
////        inputMethodManager.hideSoftInputFromWindow(
////                activity.getCurrentFocus().getWindowToken(), 0);
//        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//    }
   // @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
//        inputMethodManager.hideSoftInputFromWindow(
//                activity.getCurrentFocus().getWindowToken(), 0);
      //  inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }

    public static void dismissLoadingProgress() {


        if (CustomProgressDialog.sPdCount <= 1) {
            if (sPdLoading != null && sPdLoading.isShowing())
                sPdLoading.dismiss();
            CustomProgressDialog.sPdCount--;
        } else {
            CustomProgressDialog.sPdCount--;
        }
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    public static boolean broadcastIntent(Context context,View view) {
        // registerReceiver(myReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            Snackbar snackbar = Snackbar
                    .make(view, "Connected Mobile Network", Snackbar.LENGTH_LONG);
            snackbar.show();
            return  true;
        }
        else  if ( connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            Snackbar snackbar = Snackbar
                    .make(view, "Connected WIFI Network", Snackbar.LENGTH_LONG);
            snackbar.show();
            return  true;
        }
        else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.CONNECTED && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.CONNECTED){

            return  false;
        }

        return  false;
    }
    public static void showLoadingProgress(final Context context) {

        if (CustomProgressDialog.sPdCount <= 0) {
            CustomProgressDialog.sPdCount = 0;
            sPdLoading = null;
            try {
                sPdLoading = new CustomProgressDialog(context, R.style.CustomDialogTheme);
                if (!sPdLoading.isShowing())
                    sPdLoading.show();
                if (Build.VERSION.SDK_INT > 10) {
                    LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View loadingV = inflator.inflate(R.layout.layout_dialog_spinner, null);
                    CorrectSizeUtil.getInstance((Activity) context).correctSize(loadingV);
                    sPdLoading.setContentView(loadingV);
                } else {
                    String message = "Loading...";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            CustomProgressDialog.sPdCount++;
        } else {
            CustomProgressDialog.sPdCount++;
        }


    }
    public static double rounded(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static boolean isEmpty(String emptyCheckValue) {
        if (emptyCheckValue != null && !emptyCheckValue.trim().isEmpty() && !emptyCheckValue.equals(Constant.NULL) && emptyCheckValue.trim().length() > 0)
            return false;
        else return true;
    }
    public static ArrayList<ClinicModel> getClinicList() {
        ArrayList<ClinicModel> sexModelArrayList = new ArrayList<>();
        ClinicModel sexModelMale = new ClinicModel("JagannathPur-CC", 1);
        ClinicModel sexModelFemale = new ClinicModel("Laximipur -CC", 2);
        ClinicModel sexModelOthers = new ClinicModel("Kathundu-CC", 3);
        sexModelArrayList.add(sexModelMale);
        sexModelArrayList.add(sexModelFemale);
        sexModelArrayList.add(sexModelOthers);
        return sexModelArrayList;

    }
    public static ArrayList<CenterModel> getCenterList() {
        ArrayList<CenterModel> sexModelArrayList = new ArrayList<>();
        CenterModel sexModelMale = new CenterModel("UHC-01", 1);
        CenterModel sexModelFemale = new CenterModel("UHC-02", 2);
        CenterModel sexModelOthers = new CenterModel("UHC-03", 3);
        sexModelArrayList.add(sexModelMale);
        sexModelArrayList.add(sexModelFemale);
        sexModelArrayList.add(sexModelOthers);
        return sexModelArrayList;

    }

    public static ArrayList<SexModel> getSexList() {
        ArrayList<SexModel> sexModelArrayList = new ArrayList<>();
        SexModel sexModelMale = new SexModel("Male", 1);
        SexModel sexModelFemale = new SexModel("Female", 2);
        SexModel sexModelOthers = new SexModel("Others", 99);
        sexModelArrayList.add(sexModelMale);
        sexModelArrayList.add(sexModelFemale);
        sexModelArrayList.add(sexModelOthers);
        return sexModelArrayList;

    }
    public static ArrayList<ModerateModel> getModerateModelList() {
        ArrayList<ModerateModel> fruitsModelArrayList = new ArrayList<>();

        ModerateModel fruitsModelNo0 = new ModerateModel("0 Day/Week", 1);
        ModerateModel fruitsModelNo1 = new ModerateModel("1 Day/Week", 2);
        ModerateModel fruitsModelNo2 = new ModerateModel("2 Day/Week", 3);
        ModerateModel fruitsModelNo3 = new ModerateModel("3 Day/Week", 4);
        ModerateModel fruitsModelNo4 = new ModerateModel("4 Day/Week", 5);
        ModerateModel fruitsModelNo5 = new ModerateModel("5 Day/Week", 6);
        ModerateModel fruitsModelNo6 = new ModerateModel("6 Day/Week", 7);
        ModerateModel fruitsModelNo7 = new ModerateModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }
    public static ArrayList<ModerateRecreationalModel> getModerateRecreationalModelList() {
        ArrayList<ModerateRecreationalModel> fruitsModelArrayList = new ArrayList<>();

        ModerateRecreationalModel fruitsModelNo0 = new ModerateRecreationalModel("0 Day/Week", 1);
        ModerateRecreationalModel fruitsModelNo1 = new ModerateRecreationalModel("1 Day/Week", 2);
        ModerateRecreationalModel fruitsModelNo2 = new ModerateRecreationalModel("2 Day/Week", 3);
        ModerateRecreationalModel fruitsModelNo3 = new ModerateRecreationalModel("3 Day/Week", 4);
        ModerateRecreationalModel fruitsModelNo4 = new ModerateRecreationalModel("4 Day/Week", 5);
        ModerateRecreationalModel fruitsModelNo5 = new ModerateRecreationalModel("5 Day/Week", 6);
        ModerateRecreationalModel fruitsModelNo6 = new ModerateRecreationalModel("6 Day/Week", 7);
        ModerateRecreationalModel fruitsModelNo7 = new ModerateRecreationalModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }
    public static ArrayList<TypicalVigorousModel> getTypicalVigorousModelList() {
        ArrayList<TypicalVigorousModel> fruitsModelArrayList = new ArrayList<>();

        TypicalVigorousModel fruitsModelNo0 = new TypicalVigorousModel("0 Day/Week", 1);
        TypicalVigorousModel fruitsModelNo1 = new TypicalVigorousModel("1 Day/Week", 2);
        TypicalVigorousModel fruitsModelNo2 = new TypicalVigorousModel("2 Day/Week", 3);
        TypicalVigorousModel fruitsModelNo3 = new TypicalVigorousModel("3 Day/Week", 4);
        TypicalVigorousModel fruitsModelNo4 = new TypicalVigorousModel("4 Day/Week", 5);
        TypicalVigorousModel fruitsModelNo5 = new TypicalVigorousModel("5 Day/Week", 6);
        TypicalVigorousModel fruitsModelNo6 = new TypicalVigorousModel("6 Day/Week", 7);
        TypicalVigorousModel fruitsModelNo7 = new TypicalVigorousModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }
    public static ArrayList<TypicalVigorousRecreationModel> getTypicalVigorousRecreationModelList() {
        ArrayList<TypicalVigorousRecreationModel> fruitsModelArrayList = new ArrayList<>();

        TypicalVigorousRecreationModel fruitsModelNo0 = new TypicalVigorousRecreationModel("0 Day/Week", 1);
        TypicalVigorousRecreationModel fruitsModelNo1 = new TypicalVigorousRecreationModel("1 Day/Week", 2);
        TypicalVigorousRecreationModel fruitsModelNo2 = new TypicalVigorousRecreationModel("2 Day/Week", 3);
        TypicalVigorousRecreationModel fruitsModelNo3 = new TypicalVigorousRecreationModel("3 Day/Week", 4);
        TypicalVigorousRecreationModel fruitsModelNo4 = new TypicalVigorousRecreationModel("4 Day/Week", 5);
        TypicalVigorousRecreationModel fruitsModelNo5 = new TypicalVigorousRecreationModel("5 Day/Week", 6);
        TypicalVigorousRecreationModel fruitsModelNo6 = new TypicalVigorousRecreationModel("6 Day/Week", 7);
        TypicalVigorousRecreationModel fruitsModelNo7 = new TypicalVigorousRecreationModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }

    public static ArrayList<ReligionModel> getReligionList() {
        ArrayList<ReligionModel> religionModelArrayList = new ArrayList<>();
        ReligionModel religionModelIslam4 = new ReligionModel("Select", -1);
        ReligionModel religionModelIslam = new ReligionModel("Islam", 1);
        ReligionModel religionModelHindu = new ReligionModel("Hindu", 2);
        ReligionModel religionModelChristan = new ReligionModel("Christian", 3);
        ReligionModel religionModelBuddhist = new ReligionModel("Buddhisht", 4);
        ReligionModel religionModelOthers = new ReligionModel("Others", 99);
        religionModelArrayList.add(religionModelIslam4);
        religionModelArrayList.add(religionModelIslam);
        religionModelArrayList.add(religionModelHindu);
        religionModelArrayList.add(religionModelChristan);
        religionModelArrayList.add(religionModelBuddhist);
        religionModelArrayList.add(religionModelOthers);
        return religionModelArrayList;

    }

    public static ArrayList<EducationModel> getEducationList() {
        ArrayList<EducationModel> religionModelArrayList = new ArrayList<>();
        EducationModel educationModelIlliterate = new EducationModel("Illiterate", 1);
        EducationModel educationModelClass1 = new EducationModel("Class 1", 2);
        EducationModel educationModelClass2 = new EducationModel("Class 2", 3);
        EducationModel educationModelClass3 = new EducationModel("Class 3", 4);
        EducationModel educationModelClass4 = new EducationModel("Class 4", 5);
        EducationModel educationModelClass5 = new EducationModel("Class 5", 6);
        EducationModel educationModelClass6 = new EducationModel("Class 6", 7);
        EducationModel educationModelClass7 = new EducationModel("Class 7", 8);
        EducationModel educationModelClass8 = new EducationModel("Class 8", 9);
        EducationModel educationModelClass9 = new EducationModel("Class 9", 10);
        EducationModel educationModelClass10 = new EducationModel("Class 10", 11);
        EducationModel educationModelClass11 = new EducationModel("Class 11", 12);
        EducationModel educationModelClass12 = new EducationModel("Class 12", 13);
        EducationModel educationModelGraduate = new EducationModel("Graduate", 14);
        EducationModel educationModelAbove = new EducationModel("Above Graduate", 15);
        religionModelArrayList.add(educationModelIlliterate);
        religionModelArrayList.add(educationModelClass1);
        religionModelArrayList.add(educationModelClass2);
        religionModelArrayList.add(educationModelClass3);
        religionModelArrayList.add(educationModelClass4);
        religionModelArrayList.add(educationModelClass5);
        religionModelArrayList.add(educationModelClass6);
        religionModelArrayList.add(educationModelClass7);
        religionModelArrayList.add(educationModelClass8);
        religionModelArrayList.add(educationModelClass9);
        religionModelArrayList.add(educationModelClass10);
        religionModelArrayList.add(educationModelClass11);
        religionModelArrayList.add(educationModelClass12);
        religionModelArrayList.add(educationModelGraduate);
        religionModelArrayList.add(educationModelAbove);
        return religionModelArrayList;

    }
    public static ArrayList<TubewellModel> getTubewellList(Activity mActivity) {
        ArrayList<TubewellModel> sexModelArrayList = new ArrayList<>();
        TubewellModel maritialStatusUnmarried1 = new TubewellModel(mActivity.getResources().getString(R.string.Select), -1);
        TubewellModel maritialStatusUnmarried = new TubewellModel(mActivity.getResources().getString(R.string.tubewell), 1);
        TubewellModel maritialStatusMarried = new TubewellModel(mActivity.getResources().getString(R.string.filter_water), 2);
        TubewellModel maritialStatusDivorced = new TubewellModel(mActivity.getResources().getString(R.string.tap_water), 3);
        TubewellModel maritialStatusWidow = new TubewellModel(mActivity.getResources().getString(R.string.boil_water), 4);
        TubewellModel maritialStatusWidower = new TubewellModel(mActivity.getResources().getString(R.string.chlorine_water), 5);
        sexModelArrayList.add(maritialStatusUnmarried1);
        sexModelArrayList.add(maritialStatusUnmarried);
        sexModelArrayList.add(maritialStatusMarried);
        sexModelArrayList.add(maritialStatusDivorced);
        sexModelArrayList.add(maritialStatusWidow);
        sexModelArrayList.add(maritialStatusWidower);
        return sexModelArrayList;

    }
    public static ArrayList<BiomasFuelModel> getBiomasFuelList(Activity mActivity) {

        ArrayList<BiomasFuelModel> sexModelArrayList = new ArrayList<>();
        BiomasFuelModel maritialStatusUnmarried1 = new BiomasFuelModel(mActivity.getResources().getString(R.string.Select), -1);
        BiomasFuelModel maritialStatusUnmarried = new BiomasFuelModel(mActivity.getResources().getString(R.string.kath), 1);
        BiomasFuelModel maritialStatusMarried = new BiomasFuelModel(mActivity.getResources().getString(R.string.patkhori), 2);
        BiomasFuelModel maritialStatusDivorced = new BiomasFuelModel(mActivity.getResources().getString(R.string.gobor), 3);
        BiomasFuelModel maritialStatusWidow = new BiomasFuelModel(mActivity.getResources().getString(R.string.fosol), 4);
        BiomasFuelModel maritialStatusWidower1 = new BiomasFuelModel(mActivity.getResources().getString(R.string.kura), 5);
        BiomasFuelModel maritialStatusWidower2 = new BiomasFuelModel(mActivity.getResources().getString(R.string.alchocol), 6);
        BiomasFuelModel maritialStatusWidower3 = new BiomasFuelModel(mActivity.getResources().getString(R.string.kerosin_chula), 7);
        BiomasFuelModel maritialStatusWidower4 = new BiomasFuelModel(mActivity.getResources().getString(R.string.land_fill_gas), 8);
        BiomasFuelModel maritialStatusWidower5 = new BiomasFuelModel(mActivity.getResources().getString(R.string.electric_chula), 9);
        BiomasFuelModel maritialStatusWidower6 = new BiomasFuelModel(mActivity.getResources().getString(R.string.ittadi), 10);
        sexModelArrayList.add(maritialStatusUnmarried1);
        sexModelArrayList.add(maritialStatusUnmarried);
        sexModelArrayList.add(maritialStatusMarried);
        sexModelArrayList.add(maritialStatusDivorced);
        sexModelArrayList.add(maritialStatusWidow);
        sexModelArrayList.add(maritialStatusWidower1);
        sexModelArrayList.add(maritialStatusWidower2);
        sexModelArrayList.add(maritialStatusWidower3);
        sexModelArrayList.add(maritialStatusWidower4);
        sexModelArrayList.add(maritialStatusWidower5);
        sexModelArrayList.add(maritialStatusWidower6);
        return sexModelArrayList;

    }
    public static ArrayList<MaritialStatusModel> getMaritialStatusList() {
        ArrayList<MaritialStatusModel> sexModelArrayList = new ArrayList<>();
        MaritialStatusModel maritialStatusUnmarried = new MaritialStatusModel("Unmarried", 1);
        MaritialStatusModel maritialStatusMarried = new MaritialStatusModel("Married", 2);
        MaritialStatusModel maritialStatusDivorced = new MaritialStatusModel("Divorced", 3);
        MaritialStatusModel maritialStatusWidow = new MaritialStatusModel("Widow", 4);
        MaritialStatusModel maritialStatusWidower = new MaritialStatusModel("Widower", 5);
        sexModelArrayList.add(maritialStatusUnmarried);
        sexModelArrayList.add(maritialStatusMarried);
        sexModelArrayList.add(maritialStatusDivorced);
        sexModelArrayList.add(maritialStatusWidow);
        sexModelArrayList.add(maritialStatusWidower);
        return sexModelArrayList;

    }

    public static ArrayList<OccupationModel> getOccupationList() {
        ArrayList<OccupationModel> occupationModelArrayList = new ArrayList<>();
        OccupationModel occupationModelDoctor = new OccupationModel("Doctor", 1);
        OccupationModel occupationModelEnginner = new OccupationModel("Engineer", 2);
        OccupationModel occupationModelBuisnessman = new OccupationModel("Busness Man", 3);
        OccupationModel occupationModelHouseWife = new OccupationModel("Housewife", 4);
        OccupationModel occupationModelFarmer = new OccupationModel("Farmer", 5);
        OccupationModel occupationModelStudent = new OccupationModel("Student", 6);
        OccupationModel occupationModelOthers = new OccupationModel("Others", 99);
        occupationModelArrayList.add(occupationModelDoctor);
        occupationModelArrayList.add(occupationModelEnginner);
        occupationModelArrayList.add(occupationModelBuisnessman);
        occupationModelArrayList.add(occupationModelHouseWife);
        occupationModelArrayList.add(occupationModelFarmer);
        occupationModelArrayList.add(occupationModelStudent);
        occupationModelArrayList.add(occupationModelOthers);
        return occupationModelArrayList;

    }

    public static ArrayList<BloodGroupModel> getBloodGroupList() {
        ArrayList<BloodGroupModel> bloodGroupModelArrayList = new ArrayList<>();
        BloodGroupModel bloodGroupModelAPositive = new BloodGroupModel("A+", 1);
        BloodGroupModel bloodGroupModelAnegative = new BloodGroupModel("A-", 2);
        BloodGroupModel bloodGroupModelBpostive = new BloodGroupModel("B+", 3);
        BloodGroupModel bloodGroupModelBnegative = new BloodGroupModel("B-", 4);
        BloodGroupModel bloodGroupModelOPositive = new BloodGroupModel("O+", 5);
        BloodGroupModel bloodGroupModelOnegative = new BloodGroupModel("O-", 6);
        BloodGroupModel bloodGroupModelABPositive = new BloodGroupModel("AB+", 7);
        BloodGroupModel bloodGroupModelABnegative = new BloodGroupModel("AB-", 8);
        BloodGroupModel bloodGroupModelOthers = new BloodGroupModel("Don't Know", 93);
        bloodGroupModelArrayList.add(bloodGroupModelAPositive);
        bloodGroupModelArrayList.add(bloodGroupModelAnegative);
        bloodGroupModelArrayList.add(bloodGroupModelBpostive);
        bloodGroupModelArrayList.add(bloodGroupModelBnegative);
        bloodGroupModelArrayList.add(bloodGroupModelOnegative);
        bloodGroupModelArrayList.add(bloodGroupModelOPositive);
        bloodGroupModelArrayList.add(bloodGroupModelABPositive);
        bloodGroupModelArrayList.add(bloodGroupModelABnegative);
        bloodGroupModelArrayList.add(bloodGroupModelOthers);
        return bloodGroupModelArrayList;

    }

    public static ArrayList<LivingStatusModel> getLivingStatusList() {
        ArrayList<LivingStatusModel> livingStatusModelArrayList = new ArrayList<>();
        LivingStatusModel livingStatusModelAlive1 = new LivingStatusModel("Select", -1);
        LivingStatusModel livingStatusModelAlive = new LivingStatusModel("Alive", 2);
        LivingStatusModel livingStatusModelDead = new LivingStatusModel("Dead", 1);

        livingStatusModelArrayList.add(livingStatusModelAlive1);
        livingStatusModelArrayList.add(livingStatusModelAlive);
        livingStatusModelArrayList.add(livingStatusModelDead);


        return livingStatusModelArrayList;

    }

    public static ArrayList<YesNoModel> getyesNoList() {
        ArrayList<YesNoModel> yesNoModelArrayList = new ArrayList<>();

        YesNoModel yesNoModelNo1 = new YesNoModel("Select", -1);
        YesNoModel yesNoModelNo = new YesNoModel("No", 2);
        YesNoModel yesNoModelYes = new YesNoModel("Yes", 1);

        yesNoModelArrayList.add(yesNoModelNo1);
        yesNoModelArrayList.add(yesNoModelNo);
        yesNoModelArrayList.add(yesNoModelYes);


        return yesNoModelArrayList;

    }
    public static ArrayList<ControlDiseaseModel> getControlDiseaseList() {
        ArrayList<ControlDiseaseModel> controlDiseaseModelArrayList = new ArrayList<>();

        //ControlDiseaseModel controlDiseaseModel12= new ControlDiseaseModel("Select", -1);
        ControlDiseaseModel controlDiseaseModel1= new ControlDiseaseModel("Mouth to Mouth  Medicine", 1);
        ControlDiseaseModel controlDiseaseModel2 = new ControlDiseaseModel("Don't Know", 2);

       // controlDiseaseModelArrayList.add(controlDiseaseModel12);
        controlDiseaseModelArrayList.add(controlDiseaseModel1);
        controlDiseaseModelArrayList.add(controlDiseaseModel2);


        return controlDiseaseModelArrayList;

    }
    public static ArrayList<SaltModel> getSaltModelList() {
        ArrayList<SaltModel> saltModelArrayList = new ArrayList<>();

        SaltModel saltModel05 = new SaltModel("0.5 Kg", 1);
        SaltModel saltModel1 = new SaltModel("1 Kg", 2);
        SaltModel saltModel15 = new SaltModel("1.5 Kg", 3);
        SaltModel saltModel2 = new SaltModel("2 Kg", 4);
        SaltModel saltModel25 = new SaltModel("2.5 Kg", 5);
        SaltModel saltModel3 = new SaltModel("3 Kg", 6);
        SaltModel saltModel35 = new SaltModel("3.5 Kg", 7);
        SaltModel saltModel4 = new SaltModel("4 Kg", 8);
        SaltModel saltModel45 = new SaltModel("4.5 Kg", 9);
        SaltModel saltModel50 = new SaltModel("5 Kg", 10);
        SaltModel saltModel5up = new SaltModel("More than 5 Kg", 11);

        saltModelArrayList.add(saltModel05);
        saltModelArrayList.add(saltModel1);
        saltModelArrayList.add(saltModel15);
        saltModelArrayList.add(saltModel2);
        saltModelArrayList.add(saltModel25);
        saltModelArrayList.add(saltModel3);
        saltModelArrayList.add(saltModel35);
        saltModelArrayList.add(saltModel4);
        saltModelArrayList.add(saltModel45);
        saltModelArrayList.add(saltModel50);
        saltModelArrayList.add(saltModel5up);


        return saltModelArrayList;

    }

    public static ArrayList<FruitsModel> getfruitsModelList() {
        ArrayList<FruitsModel> fruitsModelArrayList = new ArrayList<>();

        FruitsModel fruitsModelNo0 = new FruitsModel("0 Day/Week", 1);
        FruitsModel fruitsModelNo1 = new FruitsModel("1 Day/Week", 2);
        FruitsModel fruitsModelNo2 = new FruitsModel("2 Day/Week", 3);
        FruitsModel fruitsModelNo3 = new FruitsModel("3 Day/Week", 4);
        FruitsModel fruitsModelNo4 = new FruitsModel("4 Day/Week", 5);
        FruitsModel fruitsModelNo5 = new FruitsModel("5 Day/Week", 6);
        FruitsModel fruitsModelNo6 = new FruitsModel("6 Day/Week", 7);
        FruitsModel fruitsModelNo7 = new FruitsModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }
    public static ArrayList<VegetableModel> getvegetablesModelList() {
        ArrayList<VegetableModel> fruitsModelArrayList = new ArrayList<>();

        VegetableModel fruitsModelNo0 = new VegetableModel("0 Day/Week", 1);
        VegetableModel fruitsModelNo1 = new VegetableModel("1 Day/Week", 2);
        VegetableModel fruitsModelNo2 = new VegetableModel("2 Day/Week", 3);
        VegetableModel fruitsModelNo3 = new VegetableModel("3 Day/Week", 4);
        VegetableModel fruitsModelNo4 = new VegetableModel("4 Day/Week", 5);
        VegetableModel fruitsModelNo5 = new VegetableModel("5 Day/Week", 6);
        VegetableModel fruitsModelNo6 = new VegetableModel("6 Day/Week", 7);
        VegetableModel fruitsModelNo7 = new VegetableModel("7 Day/Week", 8);

        fruitsModelArrayList.add(fruitsModelNo0);
        fruitsModelArrayList.add(fruitsModelNo1);
        fruitsModelArrayList.add(fruitsModelNo2);
        fruitsModelArrayList.add(fruitsModelNo3);
        fruitsModelArrayList.add(fruitsModelNo4);
        fruitsModelArrayList.add(fruitsModelNo5);
        fruitsModelArrayList.add(fruitsModelNo6);
        fruitsModelArrayList.add(fruitsModelNo7);


        return fruitsModelArrayList;

    }

    public static ArrayList<FruitsCardModel> getfruitsCardModelList() {
        ArrayList<FruitsCardModel> fruitsCardModelArrayList = new ArrayList<>();

        FruitsCardModel fruitsModelNo0 = new FruitsCardModel("0 Serving/Day", 1);
        FruitsCardModel fruitsModelNo05 = new FruitsCardModel("0.5 Serving/Day", 2);
        FruitsCardModel fruitsModelNo1 = new FruitsCardModel("1 erving/Day", 3);
        FruitsCardModel fruitsModelNo15 = new FruitsCardModel("1.5 Serving/Day", 4);
        FruitsCardModel fruitsModelNo20 = new FruitsCardModel("2 Serving/Day", 5);
        FruitsCardModel fruitsModelNo25 = new FruitsCardModel("2.5 Serving/Day", 6);
        FruitsCardModel fruitsModelNo30 = new FruitsCardModel("3 Serving/Day", 7);
        FruitsCardModel fruitsModelNo35 = new FruitsCardModel("3.5 Serving/Day", 8);
        FruitsCardModel fruitsModelNo40 = new FruitsCardModel("4 Serving/Day", 9);
        FruitsCardModel fruitsModelNo45 = new FruitsCardModel("4.5 Serving/Day", 10);
        FruitsCardModel fruitsModelNo5 = new FruitsCardModel("5 Serving/Day", 11);
        FruitsCardModel fruitsModelNo6 = new FruitsCardModel("6 Serving/Day", 12);
        FruitsCardModel fruitsModelNo7 = new FruitsCardModel("7 Serving/Day", 13);
        FruitsCardModel fruitsModelNo8 = new FruitsCardModel("8 Serving/Day", 14);
        FruitsCardModel fruitsModelNo9 = new FruitsCardModel("9 Serving/Day", 15);
        FruitsCardModel fruitsModelNo10 = new FruitsCardModel("10 Serving/Day", 16);

        fruitsCardModelArrayList.add(fruitsModelNo0);
        fruitsCardModelArrayList.add(fruitsModelNo05);
        fruitsCardModelArrayList.add(fruitsModelNo1);
        fruitsCardModelArrayList.add(fruitsModelNo15);
        fruitsCardModelArrayList.add(fruitsModelNo20);
        fruitsCardModelArrayList.add(fruitsModelNo25);
        fruitsCardModelArrayList.add(fruitsModelNo30);
        fruitsCardModelArrayList.add(fruitsModelNo35);
        fruitsCardModelArrayList.add(fruitsModelNo40);
        fruitsCardModelArrayList.add(fruitsModelNo45);
        fruitsCardModelArrayList.add(fruitsModelNo5);
        fruitsCardModelArrayList.add(fruitsModelNo6);
        fruitsCardModelArrayList.add(fruitsModelNo7);
        fruitsCardModelArrayList.add(fruitsModelNo8);
        fruitsCardModelArrayList.add(fruitsModelNo9);
        fruitsCardModelArrayList.add(fruitsModelNo10);



        return fruitsCardModelArrayList;

    }
    public static ArrayList<VegetablesCardModel> getvegetableCardModelList() {
        ArrayList<VegetablesCardModel> fruitsCardModelArrayList = new ArrayList<>();

        VegetablesCardModel fruitsModelNo0 = new VegetablesCardModel("0 Serving/Day", 1);
        VegetablesCardModel fruitsModelNo05 = new VegetablesCardModel("0.5 Serving/Day", 2);
        VegetablesCardModel fruitsModelNo1 = new VegetablesCardModel("1 Serving/Day", 3);
        VegetablesCardModel fruitsModelNo15 = new VegetablesCardModel("1.5 Serving/Day", 4);
        VegetablesCardModel fruitsModelNo20 = new VegetablesCardModel("2 Serving/Day", 5);
        VegetablesCardModel fruitsModelNo25 = new VegetablesCardModel("2.5 Serving/Day", 6);
        VegetablesCardModel fruitsModelNo30 = new VegetablesCardModel("3 Serving/Day", 7);
        VegetablesCardModel fruitsModelNo35 = new VegetablesCardModel("3.5 Serving/Day", 8);
        VegetablesCardModel fruitsModelNo40 = new VegetablesCardModel("4 Serving/Day", 9);
        VegetablesCardModel fruitsModelNo45 = new VegetablesCardModel("4.5 Serving/Day", 10);
        VegetablesCardModel fruitsModelNo5 = new VegetablesCardModel("5 Serving/Day", 11);
        VegetablesCardModel fruitsModelNo6 = new VegetablesCardModel("6 Serving/Day", 12);
        VegetablesCardModel fruitsModelNo7 = new VegetablesCardModel("7 Serving/Day", 13);
        VegetablesCardModel fruitsModelNo8 = new VegetablesCardModel("8 Serving/Day", 14);
        VegetablesCardModel fruitsModelNo9 = new VegetablesCardModel("9 Serving/Day", 15);
        VegetablesCardModel fruitsModelNo10 = new VegetablesCardModel("10 Serving/Day", 16);

        fruitsCardModelArrayList.add(fruitsModelNo0);
        fruitsCardModelArrayList.add(fruitsModelNo05);
        fruitsCardModelArrayList.add(fruitsModelNo1);
        fruitsCardModelArrayList.add(fruitsModelNo15);
        fruitsCardModelArrayList.add(fruitsModelNo20);
        fruitsCardModelArrayList.add(fruitsModelNo25);
        fruitsCardModelArrayList.add(fruitsModelNo30);
        fruitsCardModelArrayList.add(fruitsModelNo35);
        fruitsCardModelArrayList.add(fruitsModelNo40);
        fruitsCardModelArrayList.add(fruitsModelNo45);
        fruitsCardModelArrayList.add(fruitsModelNo5);
        fruitsCardModelArrayList.add(fruitsModelNo6);
        fruitsCardModelArrayList.add(fruitsModelNo7);
        fruitsCardModelArrayList.add(fruitsModelNo8);
        fruitsCardModelArrayList.add(fruitsModelNo9);
        fruitsCardModelArrayList.add(fruitsModelNo10);



        return fruitsCardModelArrayList;

    }
}

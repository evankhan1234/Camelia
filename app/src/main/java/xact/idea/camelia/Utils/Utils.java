package xact.idea.camelia.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

import io.paperdb.Paper;
import xact.idea.camelia.Helper.LocaleHelper;
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
    public static ArrayList<ModerateModel> getModerateModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<ModerateModel> fruitsModelArrayList = new ArrayList<>();

        ModerateModel fruitsModel = new ModerateModel(resources.getString(R.string.select), -1);
        ModerateModel fruitsModelNo0 = new ModerateModel(resources.getString(R.string.zero_week), 1);
        ModerateModel fruitsModelNo1 = new ModerateModel(resources.getString(R.string.one_week), 2);
        ModerateModel fruitsModelNo2 = new ModerateModel(resources.getString(R.string.two_week), 3);
        ModerateModel fruitsModelNo3 = new ModerateModel(resources.getString(R.string.three_week), 4);
        ModerateModel fruitsModelNo4 = new ModerateModel(resources.getString(R.string.four_week), 5);
        ModerateModel fruitsModelNo5 = new ModerateModel(resources.getString(R.string.five_week), 6);
        ModerateModel fruitsModelNo6 = new ModerateModel(resources.getString(R.string.six_week), 7);
        ModerateModel fruitsModelNo7 = new ModerateModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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
    public static ArrayList<ModerateRecreationalModel> getModerateRecreationalModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<ModerateRecreationalModel> fruitsModelArrayList = new ArrayList<>();

        ModerateRecreationalModel fruitsModel= new ModerateRecreationalModel(resources.getString(R.string.select), -1);
        ModerateRecreationalModel fruitsModelNo0 = new ModerateRecreationalModel(resources.getString(R.string.zero_week), 1);
        ModerateRecreationalModel fruitsModelNo1 = new ModerateRecreationalModel(resources.getString(R.string.one_week), 2);
        ModerateRecreationalModel fruitsModelNo2 = new ModerateRecreationalModel(resources.getString(R.string.two_week), 3);
        ModerateRecreationalModel fruitsModelNo3 = new ModerateRecreationalModel(resources.getString(R.string.three_week), 4);
        ModerateRecreationalModel fruitsModelNo4 = new ModerateRecreationalModel(resources.getString(R.string.four_week), 5);
        ModerateRecreationalModel fruitsModelNo5 = new ModerateRecreationalModel(resources.getString(R.string.five_week), 6);
        ModerateRecreationalModel fruitsModelNo6 = new ModerateRecreationalModel(resources.getString(R.string.six_week), 7);
        ModerateRecreationalModel fruitsModelNo7 = new ModerateRecreationalModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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
    public static ArrayList<TypicalVigorousModel> getTypicalVigorousModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<TypicalVigorousModel> fruitsModelArrayList = new ArrayList<>();

        TypicalVigorousModel fruitsModel = new TypicalVigorousModel(resources.getString(R.string.select), -1);
        TypicalVigorousModel fruitsModelNo0 = new TypicalVigorousModel(resources.getString(R.string.zero_week), 1);
        TypicalVigorousModel fruitsModelNo1 = new TypicalVigorousModel(resources.getString(R.string.one_week), 2);
        TypicalVigorousModel fruitsModelNo2 = new TypicalVigorousModel(resources.getString(R.string.two_week), 3);
        TypicalVigorousModel fruitsModelNo3 = new TypicalVigorousModel(resources.getString(R.string.three_week), 4);
        TypicalVigorousModel fruitsModelNo4 = new TypicalVigorousModel(resources.getString(R.string.four_week), 5);
        TypicalVigorousModel fruitsModelNo5 = new TypicalVigorousModel(resources.getString(R.string.five_week), 6);
        TypicalVigorousModel fruitsModelNo6 = new TypicalVigorousModel(resources.getString(R.string.six_week), 7);
        TypicalVigorousModel fruitsModelNo7 = new TypicalVigorousModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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
    public static ArrayList<TypicalVigorousRecreationModel> getTypicalVigorousRecreationModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<TypicalVigorousRecreationModel> fruitsModelArrayList = new ArrayList<>();

        TypicalVigorousRecreationModel fruitsModel= new TypicalVigorousRecreationModel(resources.getString(R.string.select), -1);
        TypicalVigorousRecreationModel fruitsModelNo0 = new TypicalVigorousRecreationModel(resources.getString(R.string.zero_week), 1);
        TypicalVigorousRecreationModel fruitsModelNo1 = new TypicalVigorousRecreationModel(resources.getString(R.string.one_week), 2);
        TypicalVigorousRecreationModel fruitsModelNo2 = new TypicalVigorousRecreationModel(resources.getString(R.string.two_week), 3);
        TypicalVigorousRecreationModel fruitsModelNo3 = new TypicalVigorousRecreationModel(resources.getString(R.string.three_week), 4);
        TypicalVigorousRecreationModel fruitsModelNo4 = new TypicalVigorousRecreationModel(resources.getString(R.string.four_week), 5);
        TypicalVigorousRecreationModel fruitsModelNo5 = new TypicalVigorousRecreationModel(resources.getString(R.string.five_week), 6);
        TypicalVigorousRecreationModel fruitsModelNo6 = new TypicalVigorousRecreationModel(resources.getString(R.string.six_week), 7);
        TypicalVigorousRecreationModel fruitsModelNo7 = new TypicalVigorousRecreationModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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

    public static ArrayList<ReligionModel> getReligionList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<ReligionModel> religionModelArrayList = new ArrayList<>();
        ReligionModel religionModelIslam4 = new ReligionModel(resources.getString(R.string.select), -1);
        ReligionModel religionModelIslam = new ReligionModel(resources.getString(R.string.islam), 5);
        ReligionModel religionModelHindu = new ReligionModel(resources.getString(R.string.hindu), 2);
        ReligionModel religionModelChristan = new ReligionModel(resources.getString(R.string.christain), 4);
        ReligionModel religionModelBuddhist = new ReligionModel(resources.getString(R.string.buddhist), 6);
        ReligionModel religionModelOthers = new ReligionModel(resources.getString(R.string.others_region), 7);
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
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<TubewellModel> sexModelArrayList = new ArrayList<>();
        TubewellModel maritialStatusUnmarried1 = new TubewellModel(resources.getString(R.string.Select), -1);
        TubewellModel maritialStatusUnmarried = new TubewellModel(resources.getString(R.string.tubewell), 1);
        TubewellModel maritialStatusMarried = new TubewellModel(resources.getString(R.string.filter_water), 2);
        TubewellModel maritialStatusDivorced = new TubewellModel(resources.getString(R.string.tap_water), 3);
        TubewellModel maritialStatusWidow = new TubewellModel(resources.getString(R.string.boil_water), 4);
        TubewellModel maritialStatusWidower = new TubewellModel(resources.getString(R.string.chlorine_water), 5);
        sexModelArrayList.add(maritialStatusUnmarried1);
        sexModelArrayList.add(maritialStatusUnmarried);
        sexModelArrayList.add(maritialStatusMarried);
        sexModelArrayList.add(maritialStatusDivorced);
        sexModelArrayList.add(maritialStatusWidow);
        sexModelArrayList.add(maritialStatusWidower);
        return sexModelArrayList;

    }
    public static ArrayList<BiomasFuelModel> getBiomasFuelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<BiomasFuelModel> sexModelArrayList = new ArrayList<>();
        BiomasFuelModel maritialStatusUnmarried1 = new BiomasFuelModel(resources.getString(R.string.Select), -1);
        BiomasFuelModel maritialStatusUnmarried = new BiomasFuelModel(resources.getString(R.string.kath), 1);
        BiomasFuelModel maritialStatusMarried = new BiomasFuelModel(resources.getString(R.string.patkhori), 2);
        BiomasFuelModel maritialStatusDivorced = new BiomasFuelModel(resources.getString(R.string.gobor), 3);
        BiomasFuelModel maritialStatusWidow = new BiomasFuelModel(resources.getString(R.string.fosol), 4);
        BiomasFuelModel maritialStatusWidower1 = new BiomasFuelModel(resources.getString(R.string.kura), 5);
        BiomasFuelModel maritialStatusWidower2 = new BiomasFuelModel(resources.getString(R.string.alchocol), 6);
        BiomasFuelModel maritialStatusWidower3 = new BiomasFuelModel(resources.getString(R.string.kerosin_chula), 7);
        BiomasFuelModel maritialStatusWidower4 = new BiomasFuelModel(resources.getString(R.string.land_fill_gas), 8);
        BiomasFuelModel maritialStatusWidower5 = new BiomasFuelModel(resources.getString(R.string.electric_chula), 9);
        BiomasFuelModel maritialStatusWidower6 = new BiomasFuelModel(resources.getString(R.string.ittadi), 10);
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

    public static ArrayList<LivingStatusModel> getLivingStatusList(Activity mActivity){
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<LivingStatusModel> livingStatusModelArrayList = new ArrayList<>();
        LivingStatusModel livingStatusModelAlive1 = new LivingStatusModel(resources.getString(R.string.select), -1);
        LivingStatusModel livingStatusModelAlive = new LivingStatusModel(resources.getString(R.string.alive), 1);
        LivingStatusModel livingStatusModelDead = new LivingStatusModel(resources.getString(R.string.dead), 2);

        livingStatusModelArrayList.add(livingStatusModelAlive1);
        livingStatusModelArrayList.add(livingStatusModelAlive);
        livingStatusModelArrayList.add(livingStatusModelDead);


        return livingStatusModelArrayList;

    }

    public static ArrayList<YesNoModel> getyesNoList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<YesNoModel> yesNoModelArrayList = new ArrayList<>();
            YesNoModel yesNoModelNo1 = new YesNoModel(resources.getString(R.string.select), -1);
            YesNoModel yesNoModelNo = new YesNoModel(resources.getString(R.string.no), 2);
            YesNoModel yesNoModelYes = new YesNoModel(resources.getString(R.string.yes), 1);
            yesNoModelArrayList.add(yesNoModelNo1);
            yesNoModelArrayList.add(yesNoModelNo);
            yesNoModelArrayList.add(yesNoModelYes);
            return yesNoModelArrayList;
    }
    public static ArrayList<ControlDiseaseModel> getControlDiseaseList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<ControlDiseaseModel> controlDiseaseModelArrayList = new ArrayList<>();

        //ControlDiseaseModel controlDiseaseModel12= new ControlDiseaseModel("Select", -1);
        ControlDiseaseModel controlDiseaseModel1= new ControlDiseaseModel(resources.getString(R.string.mouthe_medicine_1), 1);
        ControlDiseaseModel controlDiseaseModel2 = new ControlDiseaseModel(resources.getString(R.string.no_one_1), 2);

       // controlDiseaseModelArrayList.add(controlDiseaseModel12);
        controlDiseaseModelArrayList.add(controlDiseaseModel1);
        controlDiseaseModelArrayList.add(controlDiseaseModel2);


        return controlDiseaseModelArrayList;

    }
    public static ArrayList<SaltModel> getSaltModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<SaltModel> saltModelArrayList = new ArrayList<>();

        SaltModel saltModel = new SaltModel(resources.getString(R.string.select), -1);
        SaltModel saltModel05 = new SaltModel(resources.getString(R.string.zero_five_kg), 1);
        SaltModel saltModel1 = new SaltModel(resources.getString(R.string.one_kg), 2);
        SaltModel saltModel15 = new SaltModel(resources.getString(R.string.one_five_kg), 3);
        SaltModel saltModel2 = new SaltModel(resources.getString(R.string.two_kg), 4);
        SaltModel saltModel25 = new SaltModel(resources.getString(R.string.two_five_kg), 5);
        SaltModel saltModel3 = new SaltModel(resources.getString(R.string.three_kg), 6);
        SaltModel saltModel35 = new SaltModel(resources.getString(R.string.three_five_kg), 7);
        SaltModel saltModel4 = new SaltModel(resources.getString(R.string.four_kg), 8);
        SaltModel saltModel45 = new SaltModel(resources.getString(R.string.four_five_kg), 9);
        SaltModel saltModel50 = new SaltModel(resources.getString(R.string.five_kg), 10);
        SaltModel saltModel5up = new SaltModel(resources.getString(R.string.more_five_kg), 11);

        saltModelArrayList.add(saltModel);
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

    public static ArrayList<FruitsModel> getfruitsModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<FruitsModel> fruitsModelArrayList = new ArrayList<>();

        FruitsModel fruitsModel= new FruitsModel(resources.getString(R.string.select), -1);
        FruitsModel fruitsModelNo0 = new FruitsModel(resources.getString(R.string.zero_week), 1);
        FruitsModel fruitsModelNo1 = new FruitsModel(resources.getString(R.string.one_week), 2);
        FruitsModel fruitsModelNo2 = new FruitsModel(resources.getString(R.string.two_week), 3);
        FruitsModel fruitsModelNo3 = new FruitsModel(resources.getString(R.string.three_week), 4);
        FruitsModel fruitsModelNo4 = new FruitsModel(resources.getString(R.string.four_week), 5);
        FruitsModel fruitsModelNo5 = new FruitsModel(resources.getString(R.string.five_week), 6);
        FruitsModel fruitsModelNo6 = new FruitsModel(resources.getString(R.string.six_week), 7);
        FruitsModel fruitsModelNo7 = new FruitsModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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
    public static ArrayList<VegetableModel> getvegetablesModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<VegetableModel> fruitsModelArrayList = new ArrayList<>();

        VegetableModel fruitsModel = new VegetableModel(resources.getString(R.string.select), -1);
        VegetableModel fruitsModelNo0 = new VegetableModel(resources.getString(R.string.zero_week), 1);
        VegetableModel fruitsModelNo1 = new VegetableModel(resources.getString(R.string.one_week), 2);
        VegetableModel fruitsModelNo2 = new VegetableModel(resources.getString(R.string.two_week), 3);
        VegetableModel fruitsModelNo3 = new VegetableModel(resources.getString(R.string.three_week), 4);
        VegetableModel fruitsModelNo4 = new VegetableModel(resources.getString(R.string.four_week), 5);
        VegetableModel fruitsModelNo5 = new VegetableModel(resources.getString(R.string.five_week), 6);
        VegetableModel fruitsModelNo6 = new VegetableModel(resources.getString(R.string.six_week), 7);
        VegetableModel fruitsModelNo7 = new VegetableModel(resources.getString(R.string.seven_week), 8);

        fruitsModelArrayList.add(fruitsModel);
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

    public static ArrayList<FruitsCardModel> getfruitsCardModelList(Activity mActivity) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<FruitsCardModel> fruitsCardModelArrayList = new ArrayList<>();

        FruitsCardModel fruitsModel = new FruitsCardModel(resources.getString(R.string.select), -1);
        FruitsCardModel fruitsModelNo0 = new FruitsCardModel(resources.getString(R.string.zero_day), 0);
        FruitsCardModel fruitsModelNo05 = new FruitsCardModel(resources.getString(R.string.zero_five_day), 0.5);
        FruitsCardModel fruitsModelNo1 = new FruitsCardModel(resources.getString(R.string.one_day), 1);
        FruitsCardModel fruitsModelNo15 = new FruitsCardModel(resources.getString(R.string.one_five_day), 1.5);
        FruitsCardModel fruitsModelNo20 = new FruitsCardModel(resources.getString(R.string.two_day), 2);
        FruitsCardModel fruitsModelNo25 = new FruitsCardModel(resources.getString(R.string.two_five_day), 2.5);
        FruitsCardModel fruitsModelNo30 = new FruitsCardModel(resources.getString(R.string.three_day), 3);
        FruitsCardModel fruitsModelNo35 = new FruitsCardModel(resources.getString(R.string.three_five_day), 3.5);
        FruitsCardModel fruitsModelNo40 = new FruitsCardModel(resources.getString(R.string.four_day), 4);
        FruitsCardModel fruitsModelNo45 = new FruitsCardModel(resources.getString(R.string.four_five_day), 4.5);
        FruitsCardModel fruitsModelNo5 = new FruitsCardModel(resources.getString(R.string.fifth_day), 5);
        FruitsCardModel fruitsModelNo6 = new FruitsCardModel(resources.getString(R.string.sixth_day), 6);
        FruitsCardModel fruitsModelNo7 = new FruitsCardModel(resources.getString(R.string.seven_day), 7);
        FruitsCardModel fruitsModelNo8 = new FruitsCardModel(resources.getString(R.string.eight_day), 8);
        FruitsCardModel fruitsModelNo9 = new FruitsCardModel(resources.getString(R.string.nine_day), 9);
        FruitsCardModel fruitsModelNo10 = new FruitsCardModel(resources.getString(R.string.ten_day), 10);

        fruitsCardModelArrayList.add(fruitsModel);
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
    public static ArrayList<VegetablesCardModel> getvegetableCardModelList(Activity mActivity){
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ArrayList<VegetablesCardModel> fruitsCardModelArrayList = new ArrayList<>();

        VegetablesCardModel fruitsModel = new VegetablesCardModel(resources.getString(R.string.select), -1);
        VegetablesCardModel fruitsModelNo0 = new VegetablesCardModel(resources.getString(R.string.zero_day), 0);
        VegetablesCardModel fruitsModelNo05 = new VegetablesCardModel(resources.getString(R.string.zero_five_day), 0.5);
        VegetablesCardModel fruitsModelNo1 = new VegetablesCardModel(resources.getString(R.string.one_day), 1);
        VegetablesCardModel fruitsModelNo15 = new VegetablesCardModel(resources.getString(R.string.one_five_day), 1.5);
        VegetablesCardModel fruitsModelNo20 = new VegetablesCardModel(resources.getString(R.string.two_day), 2);
        VegetablesCardModel fruitsModelNo25 = new VegetablesCardModel(resources.getString(R.string.two_five_day), 2.5);
        VegetablesCardModel fruitsModelNo30 = new VegetablesCardModel(resources.getString(R.string.three_day), 3);
        VegetablesCardModel fruitsModelNo35 = new VegetablesCardModel(resources.getString(R.string.three_five_day), 3.5);
        VegetablesCardModel fruitsModelNo40 = new VegetablesCardModel(resources.getString(R.string.four_day), 4);
        VegetablesCardModel fruitsModelNo45 = new VegetablesCardModel(resources.getString(R.string.four_five_day), 4.5);
        VegetablesCardModel fruitsModelNo5 = new VegetablesCardModel(resources.getString(R.string.fifth_day), 5);
        VegetablesCardModel fruitsModelNo6 = new VegetablesCardModel(resources.getString(R.string.sixth_day), 6);
        VegetablesCardModel fruitsModelNo7 = new VegetablesCardModel(resources.getString(R.string.seven_day), 7);
        VegetablesCardModel fruitsModelNo8 = new VegetablesCardModel(resources.getString(R.string.eight_day), 8);
        VegetablesCardModel fruitsModelNo9 = new VegetablesCardModel(resources.getString(R.string.nine_day), 9);
        VegetablesCardModel fruitsModelNo10 = new VegetablesCardModel(resources.getString(R.string.ten_day), 10);

        fruitsCardModelArrayList.add(fruitsModel);
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
    public static String getValue(String value)
    {
        char[] chBookTotalPrice = new char[(String.valueOf(value).length())];
//
        // Copy character by character into array
        for (int i = 0; i < (String.valueOf(value).length()); i++) {
            chBookTotalPrice[i] = (String.valueOf(value).charAt(i));
        }

        StringBuilder stringBuilderBookTotalPrice = new StringBuilder();
        // Printing content of array
        for (char c2 : chBookTotalPrice) {
            if (c2 == '১') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('১', '1');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '২') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('২', '2');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৩') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৩', '3');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৪') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৪', '4');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৫') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৫', '5');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৬') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৬', '6');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৭') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৭', '7');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৮') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৮', '8');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '৯') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('৯', '9');
                stringBuilderBookTotalPrice.append(replaceString);
            } else if (c2 == '০') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('০','0');
                stringBuilderBookTotalPrice.append(replaceString);
            }
            else if (c2 == '-') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace('.', '.');
                stringBuilderBookTotalPrice.append(replaceString);
            }
            else if (c2 == ':') {
                String s = String.valueOf(c2);
                String replaceString;
                replaceString = s.replace(':', ':');
                stringBuilderBookTotalPrice.append(replaceString);
            }

        }
        String prices = stringBuilderBookTotalPrice.toString();
        return prices;
    }
}

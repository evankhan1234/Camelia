package xact.idea.camelia.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.FruitsCardModel;
import xact.idea.camelia.Model.DropDownModel.FruitsModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SaltModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.Model.DropDownModel.VegetableModel;
import xact.idea.camelia.Model.DropDownModel.VegetablesCardModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;


public class Utils {
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

    public static ArrayList<ReligionModel> getReligionList() {
        ArrayList<ReligionModel> religionModelArrayList = new ArrayList<>();
        ReligionModel religionModelIslam = new ReligionModel("Islam", 1);
        ReligionModel religionModelHindu = new ReligionModel("Hindu", 2);
        ReligionModel religionModelChristan = new ReligionModel("Christian", 3);
        ReligionModel religionModelBuddhist = new ReligionModel("Buddhisht", 4);
        ReligionModel religionModelOthers = new ReligionModel("Others", 99);
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

    public static ArrayList<MaritialStatusModel> getMaritialStatusList() {
        ArrayList<MaritialStatusModel> sexModelArrayList = new ArrayList<>();
        MaritialStatusModel maritialStatusUnmarried = new MaritialStatusModel("Male", 1);
        MaritialStatusModel maritialStatusMarried = new MaritialStatusModel("Female", 2);
        MaritialStatusModel maritialStatusDivorced = new MaritialStatusModel("Others", 99);
        MaritialStatusModel maritialStatusWidow = new MaritialStatusModel("Others", 99);
        MaritialStatusModel maritialStatusWidower = new MaritialStatusModel("Others", 99);
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
        LivingStatusModel livingStatusModelAlive = new LivingStatusModel("Alive", 2);
        LivingStatusModel livingStatusModelDead = new LivingStatusModel("Dead", 1);

        livingStatusModelArrayList.add(livingStatusModelAlive);
        livingStatusModelArrayList.add(livingStatusModelDead);


        return livingStatusModelArrayList;

    }

    public static ArrayList<YesNoModel> getyesNoList() {
        ArrayList<YesNoModel> yesNoModelArrayList = new ArrayList<>();

        YesNoModel yesNoModelNo = new YesNoModel("No", 2);
        YesNoModel yesNoModelYes = new YesNoModel("Yes", 1);

        yesNoModelArrayList.add(yesNoModelNo);
        yesNoModelArrayList.add(yesNoModelYes);


        return yesNoModelArrayList;

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

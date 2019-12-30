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
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;


public  class Utils {
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

    public static ArrayList<SexModel> getSexList(){
        ArrayList<SexModel> sexModelArrayList= new ArrayList<>();
        SexModel sexModelMale = new SexModel("Male",1);
        SexModel sexModelFemale = new SexModel("Female",2);
        SexModel sexModelOthers= new SexModel("Others",99);
        sexModelArrayList.add(sexModelMale);
        sexModelArrayList.add(sexModelFemale);
        sexModelArrayList.add(sexModelOthers);
        return sexModelArrayList;

    }
    public static ArrayList<ReligionModel> getReligionList(){
        ArrayList<ReligionModel> religionModelArrayList= new ArrayList<>();
        ReligionModel religionModelIslam = new ReligionModel("Islam",1);
        ReligionModel religionModelHindu = new ReligionModel("Hindu",2);
        ReligionModel religionModelChristan= new ReligionModel("Christian",3);
        ReligionModel religionModelBuddhist= new ReligionModel("Buddhisht",4);
        ReligionModel religionModelOthers= new ReligionModel("Others",99);
        religionModelArrayList.add(religionModelIslam);
        religionModelArrayList.add(religionModelHindu);
        religionModelArrayList.add(religionModelChristan);
        religionModelArrayList.add(religionModelBuddhist);
        religionModelArrayList.add(religionModelOthers);
        return religionModelArrayList;

    }
    public static ArrayList<EducationModel> getEducationList(){
        ArrayList<EducationModel> religionModelArrayList= new ArrayList<>();
        EducationModel educationModelIlliterate = new EducationModel("Illiterate",1);
        EducationModel educationModelClass1= new EducationModel("Class 1",2);
        EducationModel educationModelClass2= new EducationModel("Class 2",3);
        EducationModel educationModelClass3= new EducationModel("Class 3",4);
        EducationModel educationModelClass4= new EducationModel("Class 4",5);
        EducationModel educationModelClass5= new EducationModel("Class 5",6);
        EducationModel educationModelClass6= new EducationModel("Class 6",7);
        EducationModel educationModelClass7= new EducationModel("Class 7",8);
        EducationModel educationModelClass8= new EducationModel("Class 8",9);
        EducationModel educationModelClass9= new EducationModel("Class 9",10);
        EducationModel educationModelClass10= new EducationModel("Class 10",11);
        EducationModel educationModelClass11= new EducationModel("Class 11",12);
        EducationModel educationModelClass12= new EducationModel("Class 12",13);
        EducationModel educationModelGraduate= new EducationModel("Graduate",14);
        EducationModel educationModelAbove= new EducationModel("Above Graduate",15);
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
    public static ArrayList<MaritialStatusModel> getMaritialStatusList(){
        ArrayList<MaritialStatusModel> sexModelArrayList= new ArrayList<>();
        MaritialStatusModel maritialStatusUnmarried = new MaritialStatusModel("Male",1);
        MaritialStatusModel maritialStatusMarried  = new MaritialStatusModel("Female",2);
        MaritialStatusModel maritialStatusDivorced= new MaritialStatusModel("Others",99);
        MaritialStatusModel maritialStatusWidow= new MaritialStatusModel("Others",99);
        MaritialStatusModel maritialStatusWidower= new MaritialStatusModel("Others",99);
        sexModelArrayList.add(maritialStatusUnmarried);
        sexModelArrayList.add(maritialStatusMarried);
        sexModelArrayList.add(maritialStatusDivorced);
        sexModelArrayList.add(maritialStatusWidow);
        sexModelArrayList.add(maritialStatusWidower);
        return sexModelArrayList;

    }
    public static ArrayList<OccupationModel> getOccupationList(){
        ArrayList<OccupationModel> occupationModelArrayList= new ArrayList<>();
        OccupationModel occupationModelDoctor = new OccupationModel("Doctor",1);
        OccupationModel occupationModelEnginner = new OccupationModel("Engineer",2);
        OccupationModel occupationModelBuisnessman= new OccupationModel("Busness Man",3);
        OccupationModel occupationModelHouseWife= new OccupationModel("Housewife",4);
        OccupationModel occupationModelFarmer= new OccupationModel("Farmer",5);
        OccupationModel occupationModelStudent= new OccupationModel("Student",6);
        OccupationModel occupationModelOthers= new OccupationModel("Others",99);
        occupationModelArrayList.add(occupationModelDoctor);
        occupationModelArrayList.add(occupationModelEnginner);
        occupationModelArrayList.add(occupationModelBuisnessman);
        occupationModelArrayList.add(occupationModelHouseWife);
        occupationModelArrayList.add(occupationModelFarmer);
        occupationModelArrayList.add(occupationModelStudent);
        occupationModelArrayList.add(occupationModelOthers);
        return occupationModelArrayList;

    }
    public static ArrayList<BloodGroupModel> getBloodGroupList(){
        ArrayList<BloodGroupModel>bloodGroupModelArrayList= new ArrayList<>();
        BloodGroupModel bloodGroupModelAPositive= new BloodGroupModel("A+",1);
        BloodGroupModel bloodGroupModelAnegative = new BloodGroupModel("A-",2);
        BloodGroupModel bloodGroupModelBpostive = new BloodGroupModel("B+",3);
        BloodGroupModel bloodGroupModelBnegative = new BloodGroupModel("B-",4);
        BloodGroupModel bloodGroupModelOPositive = new BloodGroupModel("O+",5);
        BloodGroupModel bloodGroupModelOnegative = new BloodGroupModel("O-",6);
        BloodGroupModel bloodGroupModelABPositive= new BloodGroupModel("AB+",7);
        BloodGroupModel bloodGroupModelABnegative = new BloodGroupModel("AB-",8);
        BloodGroupModel bloodGroupModelOthers= new BloodGroupModel("Don't Know",93);
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
    public static ArrayList<LivingStatusModel> getLivingStatusList(){
        ArrayList<LivingStatusModel>livingStatusModelArrayList= new ArrayList<>();
        LivingStatusModel livingStatusModelAlive= new LivingStatusModel("Alive",2);
        LivingStatusModel livingStatusModelDead= new LivingStatusModel("Dead",1);

        livingStatusModelArrayList.add(livingStatusModelAlive);
        livingStatusModelArrayList.add(livingStatusModelDead);


        return livingStatusModelArrayList;

    }
    public static ArrayList<YesNoModel> getyesNoList(){
        ArrayList<YesNoModel>yesNoModelArrayList= new ArrayList<>();

        YesNoModel yesNoModelNo= new YesNoModel("No",2);
        YesNoModel yesNoModelYes= new YesNoModel("Yes",1);

        yesNoModelArrayList.add(yesNoModelNo);
        yesNoModelArrayList.add(yesNoModelYes);


        return yesNoModelArrayList;

    }
}

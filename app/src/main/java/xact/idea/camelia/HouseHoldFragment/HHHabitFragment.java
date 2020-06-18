package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.paperdb.Paper;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Database.Model.MemberHabit;
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.Fragment.CCBloodPressureFragment;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Model.DropDownModel.FruitsCardModel;
import xact.idea.camelia.Model.DropDownModel.FruitsModel;
import xact.idea.camelia.Model.DropDownModel.ModerateModel;
import xact.idea.camelia.Model.DropDownModel.ModerateRecreationalModel;
import xact.idea.camelia.Model.DropDownModel.SaltModel;
import xact.idea.camelia.Model.DropDownModel.TypicalVigorousModel;
import xact.idea.camelia.Model.DropDownModel.TypicalVigorousRecreationModel;
import xact.idea.camelia.Model.DropDownModel.VegetableModel;
import xact.idea.camelia.Model.DropDownModel.VegetablesCardModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class HHHabitFragment extends Fragment implements Handler.Callback {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public static Handler handler;
    LinearLayout linear_heart_show_moderate_card_typical;
    LinearLayout linear_workplace;
    LinearLayout linear_heart_show_card_day;
    LinearLayout linear_heart_show_card_typical_week;
    LinearLayout linear_heart_show_card;
    LinearLayout linear_alcohol;
    LinearLayout linear_smoke;
    LinearLayout lineat_jorda;
    LinearLayout linear_recreational_activities_typical_day;
    LinearLayout linear_recreational_activities;
    LinearLayout linear_smoke_per_day;
    LinearLayout linear_taking_meal;
    LinearLayout linear_moderate_intensity_recreational_activities_typical;
    LinearLayout linear_recreational_activities_typical;
    LinearLayout linear_heart_show_card_activities;
    LinearLayout linear_heart_show_moderate_card;
    LinearLayout linear_reclining;
    LinearLayout linear_moderate_intensity_recreational_activities;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day;
    Spinner spinner_heart_show_moderate_card;
    Spinner spinner_salt_month;
    Spinner spinner_recreational_activities;
    Spinner spinner_heart_show_card;
    Spinner spinner_salt_meal;
    Spinner spinner_fruits;
    Spinner spinner_fruits_card;
    Spinner spinner_typical_week_moderate;
    Spinner spinner_vegetables;
    Spinner spinner_vegetables_card;
    Spinner spinner_smoke;
    Spinner spinner_moderate_intensity_recreational_activities;
    Spinner spinner_typical_week_recreational;
    Spinner spinner_typical_week_moderate_recreational;
    Spinner spinner_alcohol;
    Spinner spinner_jorda;
    Spinner spinner_workplace;
    Spinner spinner_typical_week;
    Spinner spinner_reclining;
    EditText edit_smoke_years;
    EditText edit_yes_reclining;
    EditText edit_smoke_stick;
    EditText edit_jorda;
    static EditText edit_typical_day_moderate_recreational;
    static EditText edit_typical_day_moderate;
    EditText edit_workplace;
    static EditText edit_typical_day;
    EditText edit_alcohol;
    EditText edit_yes_extra_salt;
    static EditText edit_typical_day_recreational;
    CheckBox checkBoxHeaveyLoad;
    CheckBox checkBoxDigging;
    CheckBox checkBoxFurniture;
    CheckBox checkBoxPickingCrops;
    CheckBox checkBoxCuttingTrees;
    CheckBox checkBoxBreakUpPaddy;
    CheckBox checkBoxDrivingRickshaw;
    CheckBox checkBoxFishing;
    CheckBox checkBoxPlouging;
    CheckBox checkBoxHeaveyConstructionWork;
    CheckBox checkBoxHeaveyGoods;
    CheckBox checkBoxHeaveyGoodsHead;
    CheckBox checkBoxSoldDigging;
    CheckBox checkBoxWashing;
    CheckBox checkBoxStepping;
    CheckBox checkBoxOthers;
    CheckBox checkBoxHouseHoldWork;
    CheckBox checkBoxGardening;
    CheckBox checkBoxMilkingCows;
    CheckBox checkBoxCultivatingLand;
    CheckBox checkBoxPlantingHarvest;
    CheckBox checkBoxWeavingCloth;
    CheckBox checkBoxWashingCloths;
    CheckBox checkBoxRearing;
    CheckBox checkBoxMixingCement;
    CheckBox checkBoxWoodWork;
    CheckBox checkBoxDrawingWater;
    CheckBox checkBoxCarryingLightWeight;
    CheckBox checkBoxWashingCloths1;
    CheckBox checkBoxGardening1;
    CheckBox checkBoxMilkingCows1;
    CheckBox checkBoxRoping;
    CheckBox checkBoxFarming;
    CheckBox checkBoxParlour;
    CheckBox checkBoxCloth;
    CheckBox checkBoxHouseHoldWork1;
    CheckBox checkBoxOthers1;
    CheckBox checkBoxRunning;
    CheckBox checkBoxBadminton;
    CheckBox checkBoxSwimming;
    CheckBox checkBoxHockey;
    CheckBox checkBoxHadudu;
    CheckBox checkBoxVolleyball;
    CheckBox checkBoxFootbal;
    CheckBox checkBoxTenis;
    CheckBox checkBoxOthers_recreational;
    CheckBox checkBoxFastWalking;
    CheckBox checkBoxJogging;
    CheckBox checkBoxCycling;
    CheckBox checkBoxCricket;
    CheckBox checkBoxYoga;
    CheckBox checkBoxAerobics;
    CheckBox checkBoxExercise;
    CheckBox checkBoxOthersDancing;
    CheckBox checkBoxOthers_moderate_recreational_others;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterForSmoke;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterJorda;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterWorkplace;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterAlcohol;
    ArrayAdapter<FruitsModel> yesNoArrayAdapterFruits;
    ArrayAdapter<FruitsCardModel> yesNoArrayAdapterFruitsCard;
    ArrayAdapter<VegetableModel> yesNoArrayAdapterVegetables;
    ArrayAdapter<VegetablesCardModel> yesNoArrayAdapterVegetablesCard;
    ArrayAdapter<SaltModel> yesNoArrayAdapterSaltBuy;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterTakingSalt;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterVigorousIntensityHeart;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterModerateIntensityHeart;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterModerateRecreationalHeart;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterVigorousRecreationalHeart;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterReclinig;
    ArrayAdapter<ModerateModel> yesNoArrayAdapterModerate;
    ArrayAdapter<ModerateRecreationalModel> yesNoArrayAdapterModerateRecreational;
    ArrayAdapter<TypicalVigorousModel> yesNoArrayAdapterTypicalVigorous;
    ArrayAdapter<TypicalVigorousRecreationModel> yesNoArrayAdapterTypicalVigorousRecreation;

    ArrayList<YesNoModel> yesNoArrayListForSmoke = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForJorda = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForWorkplace = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForAlcohol = new ArrayList<>();
    ArrayList<FruitsModel> yesNoArrayListForFruits = new ArrayList<>();
    ArrayList<FruitsCardModel> yesNoArrayListForFruitsCard = new ArrayList<>();
    ArrayList<VegetableModel> yesNoArrayListForVegetables = new ArrayList<>();
    ArrayList<VegetablesCardModel> yesNoArrayListForVegetablesCard = new ArrayList<>();
    ArrayList<SaltModel> yesNoArrayListForSaltBuy = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForTakingSalt = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForVigorousIntensityHeart = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForModerateIntensityHeart = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForModerateRecreationalHeartt = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForVigorousRecreationalHeart = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForReclinig = new ArrayList<>();
    ArrayList<ModerateModel> yesNoArrayListForModerate = new ArrayList<>();
    ArrayList<ModerateRecreationalModel> yesNoArrayListForModerateRecreational = new ArrayList<>();
    ArrayList<TypicalVigorousModel> yesNoArrayListForTypicalVigorous = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListForTypicalVigorousRecreation = new ArrayList<>();

    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_1 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_2 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_3 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_4 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_5 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_6 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_7 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_8 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_9 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_10 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_11 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_12 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_13 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_14 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_15 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor43_b_16 = new ArrayList<>();


    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_1 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_2 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_3 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_4 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_5 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_6 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_7 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_8 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_9 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_10 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_11 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_12 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_13 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_14 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_15 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_16 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_17 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_18 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_19 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_20 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor44_b_21 = new ArrayList<>();

    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_1 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_2 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_3 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_4 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_5 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_6 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_7 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_8 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor45_b_9 = new ArrayList<>();

    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_1 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_2 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_3 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_4 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_5 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_6 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_7 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_8 = new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListFor46_b_9 = new ArrayList<>();


    int smokeYesNo;
    int jordaYesNo;
    int workplaceYesNo;
    int alcoholYesNo;
    int typicalFruits;
    double fruitsShowCard;
    int typicalVegetables;
    double vegetablesShowCard;
    int saltyBuy;
    int takingSalt;
    int vigorousIntensity;
    int vigorousIntensityTypical;
    int vigorousIntensityRecreational;
    int vigorousIntensityRecreationalTypical;
    int moderateIntensityRecreational;
    int moderateIntensityRecreationalTypical;
    int moderateIntensity;
    int moderateIntensityTypical;
    int recliningActivities;


    ////////VB1/////
    int vigirousIntensityb1;
    int vigirousIntensityb2;
    int vigirousIntensityb3;
    int vigirousIntensityb4;
    int vigirousIntensityb5;
    int vigirousIntensityb6;
    int vigirousIntensityb7;
    int vigirousIntensityb8;
    int vigirousIntensityb9;
    int vigirousIntensityb10;
    int vigirousIntensityb11;
    int vigirousIntensityb12;
    int vigirousIntensityb13;
    int vigirousIntensityb14;
    int vigirousIntensityb15;
    int vigirousIntensityb16;

    //////MB1/////
    int moderateIntensityb1;
    int moderateIntensityb2;
    int moderateIntensityb3;
    int moderateIntensityb4;
    int moderateIntensityb5;
    int moderateIntensityb6;
    int moderateIntensityb7;
    int moderateIntensityb8;
    int moderateIntensityb9;
    int moderateIntensityb10;
    int moderateIntensityb11;
    int moderateIntensityb12;
    int moderateIntensityb13;
    int moderateIntensityb14;
    int moderateIntensityb15;
    int moderateIntensityb16;
    int moderateIntensityb17;
    int moderateIntensityb18;
    int moderateIntensityb19;
    int moderateIntensityb20;
    int moderateIntensityb21;
    ////////VRB1/////////
    int vigiriousRecreationIntensityb1;
    int vigiriousRecreationIntensityb2;
    int vigiriousRecreationIntensityb3;
    int vigiriousRecreationIntensityb4;
    int vigiriousRecreationIntensityb5;
    int vigiriousRecreationIntensityb6;
    int vigiriousRecreationIntensityb7;
    int vigiriousRecreationIntensityb8;
    int vigiriousRecreationIntensityb9;

    //////////MRB1////////
    int moderateRecreationIntensityb1;
    int moderateRecreationIntensityb2;
    int moderateRecreationIntensityb3;
    int moderateRecreationIntensityb4;
    int moderateRecreationIntensityb5;
    int moderateRecreationIntensityb6;
    int moderateRecreationIntensityb7;
    int moderateRecreationIntensityb8;
    int moderateRecreationIntensityb9;
    RelativeLayout relativeLayout;
    String frag;
    String update;

    TextView tv_smoke;
    TextView tv_smoke_stick_day;
    TextView tv_smoke_day;
    TextView tv_jorda;
    TextView tv_jorda_day;
    TextView tv_workplace;
    TextView tv_workplace_day;
    TextView tv_alcohol;
    TextView tv_alcohol_day;
    TextView tv_fruits;
    TextView tv_fruits_card;
    TextView tv_vegetables;
    TextView tv_vegetables_card;
    TextView tv_salt_month;
    TextView tv_salt_meal;
    TextView tv_extra_salt;
    TextView tv_vigirous_intensity_card;
    TextView tv_vigirous_intensity_card_checkbox;
    TextView tv_typcal_week_1;
    TextView tv_typical_day_1;
    TextView tv_moderate_intensity_card;
    TextView tv_moderate_intensity_card_checkbox;
    TextView tv_typcal_week_2;
    TextView tv_typical_day_2;
    TextView tv_vigorous_intensity_recreational;
    TextView tv_vigorous_intensity_recreational_checkbox;
    TextView tv_typcal_week_3;
    TextView tv_typical_day_3;
    TextView tv_moderate_intensity_recreational_card;
    TextView tv_moderate_intensity_recreational_card_checkbox;
    TextView tv_typcal_week_4;
    TextView tv_typical_day_4;
    TextView tv_reclining;
    TextView tv_typical_day_5;


    LinearLayout linear_heart_show_card_typical_week_43_a1;
    LinearLayout linear_heart_show_card_typical_week_43_a2;
    LinearLayout linear_heart_show_card_typical_week_43_a3;
    LinearLayout linear_heart_show_card_typical_week_43_a4;
    LinearLayout linear_heart_show_card_typical_week_43_a5;
    LinearLayout linear_heart_show_card_typical_week_43_a6;
    LinearLayout linear_heart_show_card_typical_week_43_a7;
    LinearLayout linear_heart_show_card_typical_week_43_a8;
    LinearLayout linear_heart_show_card_typical_week_43_a9;
    LinearLayout linear_heart_show_card_typical_week_43_a10;
    LinearLayout linear_heart_show_card_typical_week_43_a11;
    LinearLayout linear_heart_show_card_typical_week_43_a12;
    LinearLayout linear_heart_show_card_typical_week_43_a13;
    LinearLayout linear_heart_show_card_typical_week_43_a14;
    LinearLayout linear_heart_show_card_typical_week_43_a15;
    LinearLayout linear_heart_show_card_typical_week_43_a16;
    TextView tv_typcal_week_43_a1;
    TextView tv_typcal_week_43_a2;
    TextView tv_typcal_week_43_a3;
    TextView tv_typcal_week_43_a4;
    TextView tv_typcal_week_43_a5;
    TextView tv_typcal_week_43_a6;
    TextView tv_typcal_week_43_a7;
    TextView tv_typcal_week_43_a8;
    TextView tv_typcal_week_43_a9;
    TextView tv_typcal_week_43_a10;
    TextView tv_typcal_week_43_a11;
    TextView tv_typcal_week_43_a12;
    TextView tv_typcal_week_43_a13;
    TextView tv_typcal_week_43_a14;
    TextView tv_typcal_week_43_a15;
    TextView tv_typcal_week_43_a16;
    Spinner spinner_typical_week_43_a1;
    Spinner spinner_typical_week_43_a2;
    Spinner spinner_typical_week_43_a3;
    Spinner spinner_typical_week_43_a4;
    Spinner spinner_typical_week_43_a5;
    Spinner spinner_typical_week_43_a6;
    Spinner spinner_typical_week_43_a7;
    Spinner spinner_typical_week_43_a8;
    Spinner spinner_typical_week_43_a9;
    Spinner spinner_typical_week_43_a10;
    Spinner spinner_typical_week_43_a11;
    Spinner spinner_typical_week_43_a12;
    Spinner spinner_typical_week_43_a13;
    Spinner spinner_typical_week_43_a14;
    Spinner spinner_typical_week_43_a15;
    Spinner spinner_typical_week_43_a16;
    LinearLayout linear_heart_show_card_activities_43_a1;
    LinearLayout linear_heart_show_card_activities_43_a2;
    LinearLayout linear_heart_show_card_activities_43_a3;
    LinearLayout linear_heart_show_card_activities_43_a4;
    LinearLayout linear_heart_show_card_activities_43_a5;
    LinearLayout linear_heart_show_card_activities_43_a6;
    LinearLayout linear_heart_show_card_activities_43_a7;
    LinearLayout linear_heart_show_card_activities_43_a8;
    LinearLayout linear_heart_show_card_activities_43_a9;
    LinearLayout linear_heart_show_card_activities_43_a10;
    LinearLayout linear_heart_show_card_activities_43_a11;
    LinearLayout linear_heart_show_card_activities_43_a12;
    LinearLayout linear_heart_show_card_activities_43_a13;
    LinearLayout linear_heart_show_card_activities_43_a14;
    LinearLayout linear_heart_show_card_activities_43_a15;
    LinearLayout linear_heart_show_card_activities_43_a16;
    TextView tv_typical_day_43_a1;
    TextView tv_typical_day_43_a2;
    TextView tv_typical_day_43_a3;
    TextView tv_typical_day_43_a4;
    TextView tv_typical_day_43_a5;
    TextView tv_typical_day_43_a6;
    TextView tv_typical_day_43_a7;
    TextView tv_typical_day_43_a8;
    TextView tv_typical_day_43_a9;
    TextView tv_typical_day_43_a10;
    TextView tv_typical_day_43_a11;
    TextView tv_typical_day_43_a12;
    TextView tv_typical_day_43_a13;
    TextView tv_typical_day_43_a14;
    TextView tv_typical_day_43_a15;
    TextView tv_typical_day_43_a16;
    EditText edit_typical_day_43_a1;
    EditText edit_typical_day_43_a2;
    EditText edit_typical_day_43_a3;
    EditText edit_typical_day_43_a4;
    EditText edit_typical_day_43_a5;
    EditText edit_typical_day_43_a6;
    EditText edit_typical_day_43_a7;
    EditText edit_typical_day_43_a8;
    EditText edit_typical_day_43_a9;
    EditText edit_typical_day_43_a10;
    EditText edit_typical_day_43_a11;
    EditText edit_typical_day_43_a12;
    EditText edit_typical_day_43_a13;
    EditText edit_typical_day_43_a14;
    EditText edit_typical_day_43_a15;
    EditText edit_typical_day_43_a16;


    LinearLayout linear_heart_show_moderate_card_typical_44_a1;
    LinearLayout linear_heart_show_moderate_card_typical_44_a2;
    LinearLayout linear_heart_show_moderate_card_typical_44_a3;
    LinearLayout linear_heart_show_moderate_card_typical_44_a4;
    LinearLayout linear_heart_show_moderate_card_typical_44_a5;
    LinearLayout linear_heart_show_moderate_card_typical_44_a6;
    LinearLayout linear_heart_show_moderate_card_typical_44_a7;
    LinearLayout linear_heart_show_moderate_card_typical_44_a8;
    LinearLayout linear_heart_show_moderate_card_typical_44_a9;
    LinearLayout linear_heart_show_moderate_card_typical_44_a10;
    LinearLayout linear_heart_show_moderate_card_typical_44_a11;
    LinearLayout linear_heart_show_moderate_card_typical_44_a12;
    LinearLayout linear_heart_show_moderate_card_typical_44_a13;
    LinearLayout linear_heart_show_moderate_card_typical_44_a14;
    LinearLayout linear_heart_show_moderate_card_typical_44_a15;
    LinearLayout linear_heart_show_moderate_card_typical_44_a16;
    LinearLayout linear_heart_show_moderate_card_typical_44_a17;
    LinearLayout linear_heart_show_moderate_card_typical_44_a18;
    LinearLayout linear_heart_show_moderate_card_typical_44_a19;
    LinearLayout linear_heart_show_moderate_card_typical_44_a20;
    LinearLayout linear_heart_show_moderate_card_typical_44_a21;

    TextView tv_typcal_week_44_a1;
    TextView tv_typcal_week_44_a2;
    TextView tv_typcal_week_44_a3;
    TextView tv_typcal_week_44_a4;
    TextView tv_typcal_week_44_a5;
    TextView tv_typcal_week_44_a6;
    TextView tv_typcal_week_44_a7;
    TextView tv_typcal_week_44_a8;
    TextView tv_typcal_week_44_a9;
    TextView tv_typcal_week_44_a10;
    TextView tv_typcal_week_44_a11;
    TextView tv_typcal_week_44_a12;
    TextView tv_typcal_week_44_a13;
    TextView tv_typcal_week_44_a14;
    TextView tv_typcal_week_44_a15;
    TextView tv_typcal_week_44_a16;
    TextView tv_typcal_week_44_a17;
    TextView tv_typcal_week_44_a18;
    TextView tv_typcal_week_44_a19;
    TextView tv_typcal_week_44_a20;
    TextView tv_typcal_week_44_a21;

    Spinner spinner_typical_week_moderate_44_a1;
    Spinner spinner_typical_week_moderate_44_a2;
    Spinner spinner_typical_week_moderate_44_a3;
    Spinner spinner_typical_week_moderate_44_a4;
    Spinner spinner_typical_week_moderate_44_a5;
    Spinner spinner_typical_week_moderate_44_a6;
    Spinner spinner_typical_week_moderate_44_a7;
    Spinner spinner_typical_week_moderate_44_a8;
    Spinner spinner_typical_week_moderate_44_a9;
    Spinner spinner_typical_week_moderate_44_a10;
    Spinner spinner_typical_week_moderate_44_a11;
    Spinner spinner_typical_week_moderate_44_a12;
    Spinner spinner_typical_week_moderate_44_a13;
    Spinner spinner_typical_week_moderate_44_a14;
    Spinner spinner_typical_week_moderate_44_a15;
    Spinner spinner_typical_week_moderate_44_a16;
    Spinner spinner_typical_week_moderate_44_a17;
    Spinner spinner_typical_week_moderate_44_a18;
    Spinner spinner_typical_week_moderate_44_a19;
    Spinner spinner_typical_week_moderate_44_a20;
    Spinner spinner_typical_week_moderate_44_a21;

    LinearLayout linear_heart_show_card_day_44_a1;
    LinearLayout linear_heart_show_card_day_44_a2;
    LinearLayout linear_heart_show_card_day_44_a3;
    LinearLayout linear_heart_show_card_day_44_a4;
    LinearLayout linear_heart_show_card_day_44_a5;
    LinearLayout linear_heart_show_card_day_44_a6;
    LinearLayout linear_heart_show_card_day_44_a7;
    LinearLayout linear_heart_show_card_day_44_a8;
    LinearLayout linear_heart_show_card_day_44_a9;
    LinearLayout linear_heart_show_card_day_44_a10;
    LinearLayout linear_heart_show_card_day_44_a11;
    LinearLayout linear_heart_show_card_day_44_a12;
    LinearLayout linear_heart_show_card_day_44_a13;
    LinearLayout linear_heart_show_card_day_44_a14;
    LinearLayout linear_heart_show_card_day_44_a15;
    LinearLayout linear_heart_show_card_day_44_a16;
    LinearLayout linear_heart_show_card_day_44_a17;
    LinearLayout linear_heart_show_card_day_44_a18;
    LinearLayout linear_heart_show_card_day_44_a19;
    LinearLayout linear_heart_show_card_day_44_a20;
    LinearLayout linear_heart_show_card_day_44_a21;

    TextView tv_typical_day_44_a1;
    TextView tv_typical_day_44_a2;
    TextView tv_typical_day_44_a3;
    TextView tv_typical_day_44_a4;
    TextView tv_typical_day_44_a5;
    TextView tv_typical_day_44_a6;
    TextView tv_typical_day_44_a7;
    TextView tv_typical_day_44_a8;
    TextView tv_typical_day_44_a9;
    TextView tv_typical_day_44_a10;
    TextView tv_typical_day_44_a11;
    TextView tv_typical_day_44_a12;
    TextView tv_typical_day_44_a13;
    TextView tv_typical_day_44_a14;
    TextView tv_typical_day_44_a15;
    TextView tv_typical_day_44_a16;
    TextView tv_typical_day_44_a17;
    TextView tv_typical_day_44_a18;
    TextView tv_typical_day_44_a19;
    TextView tv_typical_day_44_a20;
    TextView tv_typical_day_44_a21;

    EditText edit_typical_day_moderate_44_a1;
    EditText edit_typical_day_moderate_44_a2;
    EditText edit_typical_day_moderate_44_a3;
    EditText edit_typical_day_moderate_44_a4;
    EditText edit_typical_day_moderate_44_a5;
    EditText edit_typical_day_moderate_44_a6;
    EditText edit_typical_day_moderate_44_a7;
    EditText edit_typical_day_moderate_44_a8;
    EditText edit_typical_day_moderate_44_a9;
    EditText edit_typical_day_moderate_44_a10;
    EditText edit_typical_day_moderate_44_a11;
    EditText edit_typical_day_moderate_44_a12;
    EditText edit_typical_day_moderate_44_a13;
    EditText edit_typical_day_moderate_44_a14;
    EditText edit_typical_day_moderate_44_a15;
    EditText edit_typical_day_moderate_44_a16;
    EditText edit_typical_day_moderate_44_a17;
    EditText edit_typical_day_moderate_44_a18;
    EditText edit_typical_day_moderate_44_a19;
    EditText edit_typical_day_moderate_44_a20;
    EditText edit_typical_day_moderate_44_a21;


    LinearLayout linear_recreational_activities_typical_45_a1;
    LinearLayout linear_recreational_activities_typical_45_a2;
    LinearLayout linear_recreational_activities_typical_45_a3;
    LinearLayout linear_recreational_activities_typical_45_a4;
    LinearLayout linear_recreational_activities_typical_45_a5;
    LinearLayout linear_recreational_activities_typical_45_a6;
    LinearLayout linear_recreational_activities_typical_45_a7;
    LinearLayout linear_recreational_activities_typical_45_a8;
    LinearLayout linear_recreational_activities_typical_45_a9;

    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a1;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a2;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a3;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a4;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a5;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a6;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a7;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a8;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_46_a9;

    TextView tv_typcal_week_45_a1;
    TextView tv_typcal_week_45_a2;
    TextView tv_typcal_week_45_a3;
    TextView tv_typcal_week_45_a4;
    TextView tv_typcal_week_45_a5;
    TextView tv_typcal_week_45_a6;
    TextView tv_typcal_week_45_a7;
    TextView tv_typcal_week_45_a8;
    TextView tv_typcal_week_45_a9;

    TextView tv_typcal_week_46_a1;
    TextView tv_typcal_week_46_a2;
    TextView tv_typcal_week_46_a3;
    TextView tv_typcal_week_46_a4;
    TextView tv_typcal_week_46_a5;
    TextView tv_typcal_week_46_a6;
    TextView tv_typcal_week_46_a7;
    TextView tv_typcal_week_46_a8;
    TextView tv_typcal_week_46_a9;

    Spinner spinner_typical_week_recreational_45_a1;
    Spinner spinner_typical_week_recreational_45_a2;
    Spinner spinner_typical_week_recreational_45_a3;
    Spinner spinner_typical_week_recreational_45_a4;
    Spinner spinner_typical_week_recreational_45_a5;
    Spinner spinner_typical_week_recreational_45_a6;
    Spinner spinner_typical_week_recreational_45_a7;
    Spinner spinner_typical_week_recreational_45_a8;
    Spinner spinner_typical_week_recreational_45_a9;

    Spinner spinner_typical_week_moderate_recreational_46_a1;
    Spinner spinner_typical_week_moderate_recreational_46_a2;
    Spinner spinner_typical_week_moderate_recreational_46_a3;
    Spinner spinner_typical_week_moderate_recreational_46_a4;
    Spinner spinner_typical_week_moderate_recreational_46_a5;
    Spinner spinner_typical_week_moderate_recreational_46_a6;
    Spinner spinner_typical_week_moderate_recreational_46_a7;
    Spinner spinner_typical_week_moderate_recreational_46_a8;
    Spinner spinner_typical_week_moderate_recreational_46_a9;


    LinearLayout linear_recreational_activities_typical_day_45_a1;
    LinearLayout linear_recreational_activities_typical_day_45_a2;
    LinearLayout linear_recreational_activities_typical_day_45_a3;
    LinearLayout linear_recreational_activities_typical_day_45_a4;
    LinearLayout linear_recreational_activities_typical_day_45_a5;
    LinearLayout linear_recreational_activities_typical_day_45_a6;
    LinearLayout linear_recreational_activities_typical_day_45_a7;
    LinearLayout linear_recreational_activities_typical_day_45_a8;
    LinearLayout linear_recreational_activities_typical_day_45_a9;

    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a1;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a2;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a3;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a4;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a5;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a6;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a7;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a8;
    LinearLayout linear_moderate_intensity_recreational_activities_typical_day_46_a9;

    TextView tv_typical_day_45_a1;
    TextView tv_typical_day_45_a2;
    TextView tv_typical_day_45_a3;
    TextView tv_typical_day_45_a4;
    TextView tv_typical_day_45_a5;
    TextView tv_typical_day_45_a6;
    TextView tv_typical_day_45_a7;
    TextView tv_typical_day_45_a8;
    TextView tv_typical_day_45_a9;

    TextView tv_typical_day_46_a1;
    TextView tv_typical_day_46_a2;
    TextView tv_typical_day_46_a3;
    TextView tv_typical_day_46_a4;
    TextView tv_typical_day_46_a5;
    TextView tv_typical_day_46_a6;
    TextView tv_typical_day_46_a7;
    TextView tv_typical_day_46_a8;
    TextView tv_typical_day_46_a9;

    EditText edit_typical_day_recreational_45_a1;
    EditText edit_typical_day_recreational_45_a2;
    EditText edit_typical_day_recreational_45_a3;
    EditText edit_typical_day_recreational_45_a4;
    EditText edit_typical_day_recreational_45_a5;
    EditText edit_typical_day_recreational_45_a6;
    EditText edit_typical_day_recreational_45_a7;
    EditText edit_typical_day_recreational_45_a8;
    EditText edit_typical_day_recreational_45_a9;

    EditText edit_typical_day_moderate_recreational_46_a1;
    EditText edit_typical_day_moderate_recreational_46_a2;
    EditText edit_typical_day_moderate_recreational_46_a3;
    EditText edit_typical_day_moderate_recreational_46_a4;
    EditText edit_typical_day_moderate_recreational_46_a5;
    EditText edit_typical_day_moderate_recreational_46_a6;
    EditText edit_typical_day_moderate_recreational_46_a7;
    EditText edit_typical_day_moderate_recreational_46_a8;
    EditText edit_typical_day_moderate_recreational_46_a9;


    CheckBox checkbox_mat;
    CheckBox checkbox_plus;
    LinearLayout linear_mat;
    LinearLayout linear_plus;
    EditText edit_mat;
    EditText edit_plus;
    TextView tv_mat;
    public HHHabitFragment(String frags, String updates) {
        frag = frags;
        update = updates;
        Log.e("uniqueId", "" + frag);
        Log.e("updates", "" + updates);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hhhabit, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        setRetainInstance(true);
//        showLoadingProgress(mActivity);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        dismissLoadingProgress();
//                    }
//                });
//
//            }
//        }, 100);
        initView();
        // display();
        handler = new Handler(this);
        return view;
    }

    private void initView1() {

        checkbox_plus = view.findViewById(R.id.checkbox_plus);
        linear_plus = view.findViewById(R.id.linear_plus);
        edit_plus = view.findViewById(R.id.edit_plus);
        linear_heart_show_card_typical_week_43_a1 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a1);
        linear_heart_show_card_typical_week_43_a2 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a2);
        linear_heart_show_card_typical_week_43_a3 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a3);
        linear_heart_show_card_typical_week_43_a4 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a4);
        linear_heart_show_card_typical_week_43_a5 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a5);
        linear_heart_show_card_typical_week_43_a6 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a6);
        linear_heart_show_card_typical_week_43_a7 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a7);
        linear_heart_show_card_typical_week_43_a8 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a8);
        linear_heart_show_card_typical_week_43_a9 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a9);
        linear_heart_show_card_typical_week_43_a10 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a10);
        linear_heart_show_card_typical_week_43_a11 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a11);
        linear_heart_show_card_typical_week_43_a12 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a12);
        linear_heart_show_card_typical_week_43_a13 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a13);
        linear_heart_show_card_typical_week_43_a14 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a14);
        linear_heart_show_card_typical_week_43_a15 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a15);
        linear_heart_show_card_typical_week_43_a16 = view.findViewById(R.id.linear_heart_show_card_typical_week_43_a16);
        tv_typcal_week_43_a1 = view.findViewById(R.id.tv_typcal_week_43_a1);
        tv_typcal_week_43_a2 = view.findViewById(R.id.tv_typcal_week_43_a2);
        tv_typcal_week_43_a3 = view.findViewById(R.id.tv_typcal_week_43_a3);
        tv_typcal_week_43_a4 = view.findViewById(R.id.tv_typcal_week_43_a4);
        tv_typcal_week_43_a5 = view.findViewById(R.id.tv_typcal_week_43_a5);
        tv_typcal_week_43_a6 = view.findViewById(R.id.tv_typcal_week_43_a6);
        tv_typcal_week_43_a7 = view.findViewById(R.id.tv_typcal_week_43_a7);
        tv_typcal_week_43_a8 = view.findViewById(R.id.tv_typcal_week_43_a8);
        tv_typcal_week_43_a9 = view.findViewById(R.id.tv_typcal_week_43_a9);
        tv_typcal_week_43_a10 = view.findViewById(R.id.tv_typcal_week_43_a10);
        tv_typcal_week_43_a11 = view.findViewById(R.id.tv_typcal_week_43_a11);
        tv_typcal_week_43_a12 = view.findViewById(R.id.tv_typcal_week_43_a12);
        tv_typcal_week_43_a13 = view.findViewById(R.id.tv_typcal_week_43_a13);
        tv_typcal_week_43_a14 = view.findViewById(R.id.tv_typcal_week_43_a14);
        tv_typcal_week_43_a15 = view.findViewById(R.id.tv_typcal_week_43_a15);
        tv_typcal_week_43_a16 = view.findViewById(R.id.tv_typcal_week_43_a16);
        spinner_typical_week_43_a1 = view.findViewById(R.id.spinner_typical_week_43_a1);
        spinner_typical_week_43_a2 = view.findViewById(R.id.spinner_typical_week_43_a2);
        spinner_typical_week_43_a3 = view.findViewById(R.id.spinner_typical_week_43_a3);
        spinner_typical_week_43_a4 = view.findViewById(R.id.spinner_typical_week_43_a4);
        spinner_typical_week_43_a5 = view.findViewById(R.id.spinner_typical_week_43_a5);
        spinner_typical_week_43_a6 = view.findViewById(R.id.spinner_typical_week_43_a6);
        spinner_typical_week_43_a7 = view.findViewById(R.id.spinner_typical_week_43_a7);
        spinner_typical_week_43_a8 = view.findViewById(R.id.spinner_typical_week_43_a8);
        spinner_typical_week_43_a9 = view.findViewById(R.id.spinner_typical_week_43_a9);
        spinner_typical_week_43_a10 = view.findViewById(R.id.spinner_typical_week_43_a10);
        spinner_typical_week_43_a11 = view.findViewById(R.id.spinner_typical_week_43_a11);
        spinner_typical_week_43_a12 = view.findViewById(R.id.spinner_typical_week_43_a12);
        spinner_typical_week_43_a13 = view.findViewById(R.id.spinner_typical_week_43_a13);
        spinner_typical_week_43_a14 = view.findViewById(R.id.spinner_typical_week_43_a14);
        spinner_typical_week_43_a15 = view.findViewById(R.id.spinner_typical_week_43_a15);
        spinner_typical_week_43_a16 = view.findViewById(R.id.spinner_typical_week_43_a16);
        linear_heart_show_card_activities_43_a1 = view.findViewById(R.id.linear_heart_show_card_activities_43_a1);
        linear_heart_show_card_activities_43_a2 = view.findViewById(R.id.linear_heart_show_card_activities_43_a2);
        linear_heart_show_card_activities_43_a3 = view.findViewById(R.id.linear_heart_show_card_activities_43_a3);
        linear_heart_show_card_activities_43_a4 = view.findViewById(R.id.linear_heart_show_card_activities_43_a4);
        linear_heart_show_card_activities_43_a5 = view.findViewById(R.id.linear_heart_show_card_activities_43_a5);
        linear_heart_show_card_activities_43_a6 = view.findViewById(R.id.linear_heart_show_card_activities_43_a6);
        linear_heart_show_card_activities_43_a7 = view.findViewById(R.id.linear_heart_show_card_activities_43_a7);
        linear_heart_show_card_activities_43_a8 = view.findViewById(R.id.linear_heart_show_card_activities_43_a8);
        linear_heart_show_card_activities_43_a9 = view.findViewById(R.id.linear_heart_show_card_activities_43_a9);
        linear_heart_show_card_activities_43_a10 = view.findViewById(R.id.linear_heart_show_card_activities_43_a10);
        linear_heart_show_card_activities_43_a11 = view.findViewById(R.id.linear_heart_show_card_activities_43_a11);
        linear_heart_show_card_activities_43_a12 = view.findViewById(R.id.linear_heart_show_card_activities_43_a12);
        linear_heart_show_card_activities_43_a13 = view.findViewById(R.id.linear_heart_show_card_activities_43_a13);
        linear_heart_show_card_activities_43_a14 = view.findViewById(R.id.linear_heart_show_card_activities_43_a14);
        linear_heart_show_card_activities_43_a15 = view.findViewById(R.id.linear_heart_show_card_activities_43_a15);
        linear_heart_show_card_activities_43_a16 = view.findViewById(R.id.linear_heart_show_card_activities_43_a16);
        tv_typical_day_43_a1 = view.findViewById(R.id.tv_typical_day_43_a1);
        tv_typical_day_43_a2 = view.findViewById(R.id.tv_typical_day_43_a2);
        tv_typical_day_43_a3 = view.findViewById(R.id.tv_typical_day_43_a3);
        tv_typical_day_43_a4 = view.findViewById(R.id.tv_typical_day_43_a4);
        tv_typical_day_43_a5 = view.findViewById(R.id.tv_typical_day_43_a5);
        tv_typical_day_43_a6 = view.findViewById(R.id.tv_typical_day_43_a6);
        tv_typical_day_43_a7 = view.findViewById(R.id.tv_typical_day_43_a7);
        tv_typical_day_43_a8 = view.findViewById(R.id.tv_typical_day_43_a8);
        tv_typical_day_43_a9 = view.findViewById(R.id.tv_typical_day_43_a9);
        tv_typical_day_43_a10 = view.findViewById(R.id.tv_typical_day_43_a10);
        tv_typical_day_43_a11 = view.findViewById(R.id.tv_typical_day_43_a11);
        tv_typical_day_43_a12 = view.findViewById(R.id.tv_typical_day_43_a12);
        tv_typical_day_43_a13 = view.findViewById(R.id.tv_typical_day_43_a13);
        tv_typical_day_43_a14 = view.findViewById(R.id.tv_typical_day_43_a14);
        tv_typical_day_43_a15 = view.findViewById(R.id.tv_typical_day_43_a15);
        tv_typical_day_43_a16 = view.findViewById(R.id.tv_typical_day_43_a16);
        edit_typical_day_43_a1 = view.findViewById(R.id.edit_typical_day_43_a1);
        edit_typical_day_43_a2 = view.findViewById(R.id.edit_typical_day_43_a2);
        edit_typical_day_43_a3 = view.findViewById(R.id.edit_typical_day_43_a3);
        edit_typical_day_43_a4 = view.findViewById(R.id.edit_typical_day_43_a4);
        edit_typical_day_43_a5 = view.findViewById(R.id.edit_typical_day_43_a5);
        edit_typical_day_43_a6 = view.findViewById(R.id.edit_typical_day_43_a6);
        edit_typical_day_43_a7 = view.findViewById(R.id.edit_typical_day_43_a7);
        edit_typical_day_43_a8 = view.findViewById(R.id.edit_typical_day_43_a8);
        edit_typical_day_43_a9 = view.findViewById(R.id.edit_typical_day_43_a9);
        edit_typical_day_43_a10 = view.findViewById(R.id.edit_typical_day_43_a10);
        edit_typical_day_43_a11 = view.findViewById(R.id.edit_typical_day_43_a11);
        edit_typical_day_43_a12 = view.findViewById(R.id.edit_typical_day_43_a12);
        edit_typical_day_43_a13 = view.findViewById(R.id.edit_typical_day_43_a13);
        edit_typical_day_43_a14 = view.findViewById(R.id.edit_typical_day_43_a14);
        edit_typical_day_43_a15 = view.findViewById(R.id.edit_typical_day_43_a15);
        edit_typical_day_43_a16 = view.findViewById(R.id.edit_typical_day_43_a16);
        checkbox_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_plus.setVisibility(View.VISIBLE);

                double sum=fruitsShowCard+vegetablesShowCard;
                edit_plus.setText(String.valueOf(sum));
            }
        });
    }

    private void initView2() {

        linear_heart_show_moderate_card_typical_44_a1 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a1);
        linear_heart_show_moderate_card_typical_44_a2 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a2);
        linear_heart_show_moderate_card_typical_44_a3 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a3);
        linear_heart_show_moderate_card_typical_44_a4 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a4);
        linear_heart_show_moderate_card_typical_44_a5 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a5);
        linear_heart_show_moderate_card_typical_44_a6 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a6);
        linear_heart_show_moderate_card_typical_44_a7 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a7);
        linear_heart_show_moderate_card_typical_44_a8 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a8);
        linear_heart_show_moderate_card_typical_44_a9 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a9);
        linear_heart_show_moderate_card_typical_44_a10 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a10);
        linear_heart_show_moderate_card_typical_44_a11 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a11);
        linear_heart_show_moderate_card_typical_44_a12 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a12);
        linear_heart_show_moderate_card_typical_44_a13 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a13);
        linear_heart_show_moderate_card_typical_44_a14 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a14);
        linear_heart_show_moderate_card_typical_44_a15 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a15);
        linear_heart_show_moderate_card_typical_44_a16 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a16);
        linear_heart_show_moderate_card_typical_44_a17 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a17);
        linear_heart_show_moderate_card_typical_44_a18 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a18);
        linear_heart_show_moderate_card_typical_44_a19 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a19);
        linear_heart_show_moderate_card_typical_44_a20 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a20);
        linear_heart_show_moderate_card_typical_44_a21 = view.findViewById(R.id.linear_heart_show_moderate_card_typical_44_a21);

        tv_typcal_week_44_a1 = view.findViewById(R.id.tv_typcal_week_44_a1);
        tv_typcal_week_44_a2 = view.findViewById(R.id.tv_typcal_week_44_a2);
        tv_typcal_week_44_a3 = view.findViewById(R.id.tv_typcal_week_44_a3);
        tv_typcal_week_44_a4 = view.findViewById(R.id.tv_typcal_week_44_a4);
        tv_typcal_week_44_a5 = view.findViewById(R.id.tv_typcal_week_44_a5);
        tv_typcal_week_44_a6 = view.findViewById(R.id.tv_typcal_week_44_a6);
        tv_typcal_week_44_a7 = view.findViewById(R.id.tv_typcal_week_44_a7);
        tv_typcal_week_44_a8 = view.findViewById(R.id.tv_typcal_week_44_a8);
        tv_typcal_week_44_a9 = view.findViewById(R.id.tv_typcal_week_44_a9);
        tv_typcal_week_44_a10 = view.findViewById(R.id.tv_typcal_week_44_a10);
        tv_typcal_week_44_a11 = view.findViewById(R.id.tv_typcal_week_44_a11);
        tv_typcal_week_44_a12 = view.findViewById(R.id.tv_typcal_week_44_a12);
        tv_typcal_week_44_a13 = view.findViewById(R.id.tv_typcal_week_44_a13);
        tv_typcal_week_44_a14 = view.findViewById(R.id.tv_typcal_week_44_a14);
        tv_typcal_week_44_a15 = view.findViewById(R.id.tv_typcal_week_44_a15);
        tv_typcal_week_44_a16 = view.findViewById(R.id.tv_typcal_week_44_a16);
        tv_typcal_week_44_a17 = view.findViewById(R.id.tv_typcal_week_44_a17);
        tv_typcal_week_44_a18 = view.findViewById(R.id.tv_typcal_week_44_a18);
        tv_typcal_week_44_a19 = view.findViewById(R.id.tv_typcal_week_44_a19);
        tv_typcal_week_44_a20 = view.findViewById(R.id.tv_typcal_week_44_a20);
        tv_typcal_week_44_a21 = view.findViewById(R.id.tv_typcal_week_44_a21);


        spinner_typical_week_moderate_44_a1 = view.findViewById(R.id.spinner_typical_week_moderate_44_a1);
        spinner_typical_week_moderate_44_a2 = view.findViewById(R.id.spinner_typical_week_moderate_44_a2);
        spinner_typical_week_moderate_44_a3 = view.findViewById(R.id.spinner_typical_week_moderate_44_a3);
        spinner_typical_week_moderate_44_a4 = view.findViewById(R.id.spinner_typical_week_moderate_44_a4);
        spinner_typical_week_moderate_44_a5 = view.findViewById(R.id.spinner_typical_week_moderate_44_a5);
        spinner_typical_week_moderate_44_a6 = view.findViewById(R.id.spinner_typical_week_moderate_44_a6);
        spinner_typical_week_moderate_44_a7 = view.findViewById(R.id.spinner_typical_week_moderate_44_a7);
        spinner_typical_week_moderate_44_a8 = view.findViewById(R.id.spinner_typical_week_moderate_44_a8);
        spinner_typical_week_moderate_44_a9 = view.findViewById(R.id.spinner_typical_week_moderate_44_a9);
        spinner_typical_week_moderate_44_a10 = view.findViewById(R.id.spinner_typical_week_moderate_44_a10);
        spinner_typical_week_moderate_44_a11 = view.findViewById(R.id.spinner_typical_week_moderate_44_a11);
        spinner_typical_week_moderate_44_a12 = view.findViewById(R.id.spinner_typical_week_moderate_44_a12);
        spinner_typical_week_moderate_44_a13 = view.findViewById(R.id.spinner_typical_week_moderate_44_a13);
        spinner_typical_week_moderate_44_a14 = view.findViewById(R.id.spinner_typical_week_moderate_44_a14);
        spinner_typical_week_moderate_44_a15 = view.findViewById(R.id.spinner_typical_week_moderate_44_a15);
        spinner_typical_week_moderate_44_a16 = view.findViewById(R.id.spinner_typical_week_moderate_44_a16);
        spinner_typical_week_moderate_44_a17 = view.findViewById(R.id.spinner_typical_week_moderate_44_a17);
        spinner_typical_week_moderate_44_a18 = view.findViewById(R.id.spinner_typical_week_moderate_44_a18);
        spinner_typical_week_moderate_44_a19 = view.findViewById(R.id.spinner_typical_week_moderate_44_a19);
        spinner_typical_week_moderate_44_a20 = view.findViewById(R.id.spinner_typical_week_moderate_44_a20);
        spinner_typical_week_moderate_44_a21 = view.findViewById(R.id.spinner_typical_week_moderate_44_a21);

        linear_heart_show_card_day_44_a1 = view.findViewById(R.id.linear_heart_show_card_day_44_a1);
        linear_heart_show_card_day_44_a2 = view.findViewById(R.id.linear_heart_show_card_day_44_a2);
        linear_heart_show_card_day_44_a3 = view.findViewById(R.id.linear_heart_show_card_day_44_a3);
        linear_heart_show_card_day_44_a4 = view.findViewById(R.id.linear_heart_show_card_day_44_a4);
        linear_heart_show_card_day_44_a5 = view.findViewById(R.id.linear_heart_show_card_day_44_a5);
        linear_heart_show_card_day_44_a6 = view.findViewById(R.id.linear_heart_show_card_day_44_a6);
        linear_heart_show_card_day_44_a7 = view.findViewById(R.id.linear_heart_show_card_day_44_a7);
        linear_heart_show_card_day_44_a8 = view.findViewById(R.id.linear_heart_show_card_day_44_a8);
        linear_heart_show_card_day_44_a9 = view.findViewById(R.id.linear_heart_show_card_day_44_a9);
        linear_heart_show_card_day_44_a10 = view.findViewById(R.id.linear_heart_show_card_day_44_a10);
        linear_heart_show_card_day_44_a11 = view.findViewById(R.id.linear_heart_show_card_day_44_a11);
        linear_heart_show_card_day_44_a12 = view.findViewById(R.id.linear_heart_show_card_day_44_a12);
        linear_heart_show_card_day_44_a13 = view.findViewById(R.id.linear_heart_show_card_day_44_a13);
        linear_heart_show_card_day_44_a14 = view.findViewById(R.id.linear_heart_show_card_day_44_a14);
        linear_heart_show_card_day_44_a15 = view.findViewById(R.id.linear_heart_show_card_day_44_a15);
        linear_heart_show_card_day_44_a16 = view.findViewById(R.id.linear_heart_show_card_day_44_a16);
        linear_heart_show_card_day_44_a17 = view.findViewById(R.id.linear_heart_show_card_day_44_a17);
        linear_heart_show_card_day_44_a18 = view.findViewById(R.id.linear_heart_show_card_day_44_a18);
        linear_heart_show_card_day_44_a19 = view.findViewById(R.id.linear_heart_show_card_day_44_a19);
        linear_heart_show_card_day_44_a20 = view.findViewById(R.id.linear_heart_show_card_day_44_a20);
        linear_heart_show_card_day_44_a21 = view.findViewById(R.id.linear_heart_show_card_day_44_a21);


        tv_typical_day_44_a1 = view.findViewById(R.id.tv_typical_day_44_a1);
        tv_typical_day_44_a2 = view.findViewById(R.id.tv_typical_day_44_a2);
        tv_typical_day_44_a3 = view.findViewById(R.id.tv_typical_day_44_a3);
        tv_typical_day_44_a4 = view.findViewById(R.id.tv_typical_day_44_a4);
        tv_typical_day_44_a5 = view.findViewById(R.id.tv_typical_day_44_a5);
        tv_typical_day_44_a6 = view.findViewById(R.id.tv_typical_day_44_a6);
        tv_typical_day_44_a7 = view.findViewById(R.id.tv_typical_day_44_a7);
        tv_typical_day_44_a8 = view.findViewById(R.id.tv_typical_day_44_a8);
        tv_typical_day_44_a9 = view.findViewById(R.id.tv_typical_day_44_a9);
        tv_typical_day_44_a10 = view.findViewById(R.id.tv_typical_day_44_a10);
        tv_typical_day_44_a11 = view.findViewById(R.id.tv_typical_day_44_a11);
        tv_typical_day_44_a12 = view.findViewById(R.id.tv_typical_day_44_a12);
        tv_typical_day_44_a13 = view.findViewById(R.id.tv_typical_day_44_a13);
        tv_typical_day_44_a14 = view.findViewById(R.id.tv_typical_day_44_a14);
        tv_typical_day_44_a15 = view.findViewById(R.id.tv_typical_day_44_a15);
        tv_typical_day_44_a16 = view.findViewById(R.id.tv_typical_day_44_a16);
        tv_typical_day_44_a17 = view.findViewById(R.id.tv_typical_day_44_a17);
        tv_typical_day_44_a18 = view.findViewById(R.id.tv_typical_day_44_a18);
        tv_typical_day_44_a19 = view.findViewById(R.id.tv_typical_day_44_a19);
        tv_typical_day_44_a20 = view.findViewById(R.id.tv_typical_day_44_a20);
        tv_typical_day_44_a21 = view.findViewById(R.id.tv_typical_day_44_a21);


        edit_typical_day_moderate_44_a1 = view.findViewById(R.id.edit_typical_day_moderate_44_a1);
        edit_typical_day_moderate_44_a2 = view.findViewById(R.id.edit_typical_day_moderate_44_a2);
        edit_typical_day_moderate_44_a3 = view.findViewById(R.id.edit_typical_day_moderate_44_a3);
        edit_typical_day_moderate_44_a4 = view.findViewById(R.id.edit_typical_day_moderate_44_a4);
        edit_typical_day_moderate_44_a5 = view.findViewById(R.id.edit_typical_day_moderate_44_a5);
        edit_typical_day_moderate_44_a6 = view.findViewById(R.id.edit_typical_day_moderate_44_a6);
        edit_typical_day_moderate_44_a7 = view.findViewById(R.id.edit_typical_day_moderate_44_a7);
        edit_typical_day_moderate_44_a8 = view.findViewById(R.id.edit_typical_day_moderate_44_a8);
        edit_typical_day_moderate_44_a9 = view.findViewById(R.id.edit_typical_day_moderate_44_a9);
        edit_typical_day_moderate_44_a10 = view.findViewById(R.id.edit_typical_day_moderate_44_a10);
        edit_typical_day_moderate_44_a11 = view.findViewById(R.id.edit_typical_day_moderate_44_a11);
        edit_typical_day_moderate_44_a12 = view.findViewById(R.id.edit_typical_day_moderate_44_a12);
        edit_typical_day_moderate_44_a13 = view.findViewById(R.id.edit_typical_day_moderate_44_a13);
        edit_typical_day_moderate_44_a14 = view.findViewById(R.id.edit_typical_day_moderate_44_a14);
        edit_typical_day_moderate_44_a15 = view.findViewById(R.id.edit_typical_day_moderate_44_a15);
        edit_typical_day_moderate_44_a16 = view.findViewById(R.id.edit_typical_day_moderate_44_a16);
        edit_typical_day_moderate_44_a17 = view.findViewById(R.id.edit_typical_day_moderate_44_a17);
        edit_typical_day_moderate_44_a18 = view.findViewById(R.id.edit_typical_day_moderate_44_a18);
        edit_typical_day_moderate_44_a19 = view.findViewById(R.id.edit_typical_day_moderate_44_a19);
        edit_typical_day_moderate_44_a20 = view.findViewById(R.id.edit_typical_day_moderate_44_a20);
        edit_typical_day_moderate_44_a21 = view.findViewById(R.id.edit_typical_day_moderate_44_a21);


    }

    private void initView3() {
        linear_recreational_activities_typical_45_a1 = view.findViewById(R.id.linear_recreational_activities_typical_45_a1);
        linear_recreational_activities_typical_45_a2 = view.findViewById(R.id.linear_recreational_activities_typical_45_a2);
        linear_recreational_activities_typical_45_a3 = view.findViewById(R.id.linear_recreational_activities_typical_45_a3);
        linear_recreational_activities_typical_45_a4 = view.findViewById(R.id.linear_recreational_activities_typical_45_a4);
        linear_recreational_activities_typical_45_a5 = view.findViewById(R.id.linear_recreational_activities_typical_45_a5);
        linear_recreational_activities_typical_45_a6 = view.findViewById(R.id.linear_recreational_activities_typical_45_a6);
        linear_recreational_activities_typical_45_a7 = view.findViewById(R.id.linear_recreational_activities_typical_45_a7);
        linear_recreational_activities_typical_45_a8 = view.findViewById(R.id.linear_recreational_activities_typical_45_a8);
        linear_recreational_activities_typical_45_a9 = view.findViewById(R.id.linear_recreational_activities_typical_45_a9);

        linear_moderate_intensity_recreational_activities_typical_46_a1 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a1);
        linear_moderate_intensity_recreational_activities_typical_46_a2 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a2);
        linear_moderate_intensity_recreational_activities_typical_46_a3 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a3);
        linear_moderate_intensity_recreational_activities_typical_46_a4 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a4);
        linear_moderate_intensity_recreational_activities_typical_46_a5 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a5);
        linear_moderate_intensity_recreational_activities_typical_46_a6 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a6);
        linear_moderate_intensity_recreational_activities_typical_46_a7 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a7);
        linear_moderate_intensity_recreational_activities_typical_46_a8 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a8);
        linear_moderate_intensity_recreational_activities_typical_46_a9 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_46_a9);

        tv_typcal_week_45_a1 = view.findViewById(R.id.tv_typcal_week_45_a1);
        tv_typcal_week_45_a2 = view.findViewById(R.id.tv_typcal_week_45_a2);
        tv_typcal_week_45_a3 = view.findViewById(R.id.tv_typcal_week_45_a3);
        tv_typcal_week_45_a4 = view.findViewById(R.id.tv_typcal_week_45_a4);
        tv_typcal_week_45_a5 = view.findViewById(R.id.tv_typcal_week_45_a5);
        tv_typcal_week_45_a6 = view.findViewById(R.id.tv_typcal_week_45_a6);
        tv_typcal_week_45_a7 = view.findViewById(R.id.tv_typcal_week_45_a7);
        tv_typcal_week_45_a8 = view.findViewById(R.id.tv_typcal_week_45_a8);
        tv_typcal_week_45_a9 = view.findViewById(R.id.tv_typcal_week_45_a9);

        tv_typcal_week_46_a1 = view.findViewById(R.id.tv_typcal_week_46_a1);
        tv_typcal_week_46_a2 = view.findViewById(R.id.tv_typcal_week_46_a2);
        tv_typcal_week_46_a3 = view.findViewById(R.id.tv_typcal_week_46_a3);
        tv_typcal_week_46_a4 = view.findViewById(R.id.tv_typcal_week_46_a4);
        tv_typcal_week_46_a5 = view.findViewById(R.id.tv_typcal_week_46_a5);
        tv_typcal_week_46_a6 = view.findViewById(R.id.tv_typcal_week_46_a6);
        tv_typcal_week_46_a7 = view.findViewById(R.id.tv_typcal_week_46_a7);
        tv_typcal_week_46_a8 = view.findViewById(R.id.tv_typcal_week_46_a8);
        tv_typcal_week_46_a9 = view.findViewById(R.id.tv_typcal_week_46_a9);

        spinner_typical_week_recreational_45_a1 = view.findViewById(R.id.spinner_typical_week_recreational_45_a1);
        spinner_typical_week_recreational_45_a2 = view.findViewById(R.id.spinner_typical_week_recreational_45_a2);
        spinner_typical_week_recreational_45_a3 = view.findViewById(R.id.spinner_typical_week_recreational_45_a3);
        spinner_typical_week_recreational_45_a4 = view.findViewById(R.id.spinner_typical_week_recreational_45_a4);
        spinner_typical_week_recreational_45_a5 = view.findViewById(R.id.spinner_typical_week_recreational_45_a5);
        spinner_typical_week_recreational_45_a6 = view.findViewById(R.id.spinner_typical_week_recreational_45_a6);
        spinner_typical_week_recreational_45_a7 = view.findViewById(R.id.spinner_typical_week_recreational_45_a7);
        spinner_typical_week_recreational_45_a8 = view.findViewById(R.id.spinner_typical_week_recreational_45_a8);
        spinner_typical_week_recreational_45_a9 = view.findViewById(R.id.spinner_typical_week_recreational_45_a9);

        spinner_typical_week_moderate_recreational_46_a1 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a1);
        spinner_typical_week_moderate_recreational_46_a2 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a2);
        spinner_typical_week_moderate_recreational_46_a3 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a3);
        spinner_typical_week_moderate_recreational_46_a4 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a4);
        spinner_typical_week_moderate_recreational_46_a5 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a5);
        spinner_typical_week_moderate_recreational_46_a6 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a6);
        spinner_typical_week_moderate_recreational_46_a7 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a7);
        spinner_typical_week_moderate_recreational_46_a8 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a8);
        spinner_typical_week_moderate_recreational_46_a9 = view.findViewById(R.id.spinner_typical_week_moderate_recreational_46_a9);


        linear_recreational_activities_typical_day_45_a1 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a1);
        linear_recreational_activities_typical_day_45_a2 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a2);
        linear_recreational_activities_typical_day_45_a3 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a3);
        linear_recreational_activities_typical_day_45_a4 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a4);
        linear_recreational_activities_typical_day_45_a5 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a5);
        linear_recreational_activities_typical_day_45_a6 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a6);
        linear_recreational_activities_typical_day_45_a7 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a7);
        linear_recreational_activities_typical_day_45_a8 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a8);
        linear_recreational_activities_typical_day_45_a9 = view.findViewById(R.id.linear_recreational_activities_typical_day_45_a9);

        linear_moderate_intensity_recreational_activities_typical_day_46_a1 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a1);
        linear_moderate_intensity_recreational_activities_typical_day_46_a2 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a2);
        linear_moderate_intensity_recreational_activities_typical_day_46_a3 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a3);
        linear_moderate_intensity_recreational_activities_typical_day_46_a4 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a4);
        linear_moderate_intensity_recreational_activities_typical_day_46_a5 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a5);
        linear_moderate_intensity_recreational_activities_typical_day_46_a6 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a6);
        linear_moderate_intensity_recreational_activities_typical_day_46_a7 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a7);
        linear_moderate_intensity_recreational_activities_typical_day_46_a8 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a8);
        linear_moderate_intensity_recreational_activities_typical_day_46_a9 = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day_46_a9);

        tv_typical_day_45_a1 = view.findViewById(R.id.tv_typical_day_45_a1);
        tv_typical_day_45_a2 = view.findViewById(R.id.tv_typical_day_45_a2);
        tv_typical_day_45_a3 = view.findViewById(R.id.tv_typical_day_45_a3);
        tv_typical_day_45_a4 = view.findViewById(R.id.tv_typical_day_45_a4);
        tv_typical_day_45_a5 = view.findViewById(R.id.tv_typical_day_45_a5);
        tv_typical_day_45_a6 = view.findViewById(R.id.tv_typical_day_45_a6);
        tv_typical_day_45_a7 = view.findViewById(R.id.tv_typical_day_45_a7);
        tv_typical_day_45_a8 = view.findViewById(R.id.tv_typical_day_45_a8);
        tv_typical_day_45_a9 = view.findViewById(R.id.tv_typical_day_45_a9);

        tv_typical_day_46_a1 = view.findViewById(R.id.tv_typical_day_46_a1);
        tv_typical_day_46_a2 = view.findViewById(R.id.tv_typical_day_46_a2);
        tv_typical_day_46_a3 = view.findViewById(R.id.tv_typical_day_46_a3);
        tv_typical_day_46_a4 = view.findViewById(R.id.tv_typical_day_46_a4);
        tv_typical_day_46_a5 = view.findViewById(R.id.tv_typical_day_46_a5);
        tv_typical_day_46_a6 = view.findViewById(R.id.tv_typical_day_46_a6);
        tv_typical_day_46_a7 = view.findViewById(R.id.tv_typical_day_46_a7);
        tv_typical_day_46_a8 = view.findViewById(R.id.tv_typical_day_46_a8);
        tv_typical_day_46_a9 = view.findViewById(R.id.tv_typical_day_46_a9);

        edit_typical_day_recreational_45_a1 = view.findViewById(R.id.edit_typical_day_recreational_45_a1);
        edit_typical_day_recreational_45_a2 = view.findViewById(R.id.edit_typical_day_recreational_45_a2);
        edit_typical_day_recreational_45_a3 = view.findViewById(R.id.edit_typical_day_recreational_45_a3);
        edit_typical_day_recreational_45_a4 = view.findViewById(R.id.edit_typical_day_recreational_45_a4);
        edit_typical_day_recreational_45_a5 = view.findViewById(R.id.edit_typical_day_recreational_45_a5);
        edit_typical_day_recreational_45_a6 = view.findViewById(R.id.edit_typical_day_recreational_45_a6);
        edit_typical_day_recreational_45_a7 = view.findViewById(R.id.edit_typical_day_recreational_45_a7);
        edit_typical_day_recreational_45_a8 = view.findViewById(R.id.edit_typical_day_recreational_45_a8);
        edit_typical_day_recreational_45_a9 = view.findViewById(R.id.edit_typical_day_recreational_45_a9);

        edit_typical_day_moderate_recreational_46_a1 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a1);
        edit_typical_day_moderate_recreational_46_a2 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a2);
        edit_typical_day_moderate_recreational_46_a3 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a3);
        edit_typical_day_moderate_recreational_46_a4 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a4);
        edit_typical_day_moderate_recreational_46_a5 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a5);
        edit_typical_day_moderate_recreational_46_a6 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a6);
        edit_typical_day_moderate_recreational_46_a7 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a7);
        edit_typical_day_moderate_recreational_46_a8 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a8);
        edit_typical_day_moderate_recreational_46_a9 = view.findViewById(R.id.edit_typical_day_moderate_recreational_46_a9);
    }

    private void initView() {
        initView1();
        initView2();
        initView3();

        checkbox_mat = view.findViewById(R.id.checkbox_mat);
        linear_mat = view.findViewById(R.id.linear_mat);
        edit_mat = view.findViewById(R.id.edit_mat);
        tv_mat = view.findViewById(R.id.tv_mat);
        tv_smoke = view.findViewById(R.id.tv_smoke);
        tv_smoke_day = view.findViewById(R.id.tv_smoke_day);
        tv_jorda = view.findViewById(R.id.tv_jorda);
        tv_jorda_day = view.findViewById(R.id.tv_jorda_day);
        tv_workplace = view.findViewById(R.id.tv_workplace);
        tv_workplace_day = view.findViewById(R.id.tv_workplace_day);
        tv_alcohol = view.findViewById(R.id.tv_alcohol);
        tv_alcohol_day = view.findViewById(R.id.tv_alcohol_day);
        tv_fruits = view.findViewById(R.id.tv_fruits);
        tv_fruits_card = view.findViewById(R.id.tv_fruits_card);
        tv_vegetables = view.findViewById(R.id.tv_vegetables);
        tv_vegetables_card = view.findViewById(R.id.tv_vegetables_card);
        tv_salt_month = view.findViewById(R.id.tv_salt_month);
        tv_salt_meal = view.findViewById(R.id.tv_salt_meal);
        tv_extra_salt = view.findViewById(R.id.tv_extra_salt);
        tv_vigirous_intensity_card = view.findViewById(R.id.tv_vigirous_intensity_card);
        tv_vigirous_intensity_card_checkbox = view.findViewById(R.id.tv_vigirous_intensity_card_checkbox);
        tv_typcal_week_1 = view.findViewById(R.id.tv_typcal_week_1);
        tv_typical_day_1 = view.findViewById(R.id.tv_typical_day_1);
        tv_moderate_intensity_card = view.findViewById(R.id.tv_moderate_intensity_card);
        tv_moderate_intensity_card_checkbox = view.findViewById(R.id.tv_moderate_intensity_card_checkbox);
        tv_typcal_week_2 = view.findViewById(R.id.tv_typcal_week_2);
        tv_typical_day_2 = view.findViewById(R.id.tv_typical_day_2);
        tv_vigorous_intensity_recreational = view.findViewById(R.id.tv_vigorous_intensity_recreational);
        tv_vigorous_intensity_recreational_checkbox = view.findViewById(R.id.tv_vigorous_intensity_recreational_checkbox);
        tv_typcal_week_3 = view.findViewById(R.id.tv_typcal_week_3);
        tv_typical_day_3 = view.findViewById(R.id.tv_typical_day_3);
        tv_moderate_intensity_recreational_card = view.findViewById(R.id.tv_moderate_intensity_recreational_card);
        tv_moderate_intensity_recreational_card_checkbox = view.findViewById(R.id.tv_moderate_intensity_recreational_card_checkbox);
        tv_typcal_week_4 = view.findViewById(R.id.tv_typcal_week_4);
        tv_typical_day_4 = view.findViewById(R.id.tv_typical_day_4);
        tv_reclining = view.findViewById(R.id.tv_reclining);
        tv_typical_day_5 = view.findViewById(R.id.tv_typical_day_5);
        linear_moderate_intensity_recreational_activities_typical = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical);
        checkBoxFastWalking = view.findViewById(R.id.checkBoxFastWalking);
        linear_moderate_intensity_recreational_activities_typical_day = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day);
        edit_typical_day_moderate_recreational = view.findViewById(R.id.edit_typical_day_moderate_recreational);
        checkBoxJogging = view.findViewById(R.id.checkBoxJogging);
        relativeLayout = view.findViewById(R.id.relative);
        //  edit_typical_day_recreational = view.findViewById(R.id.edit_typical_day_recreational);
        checkBoxCycling = view.findViewById(R.id.checkBoxCycling);
        linear_reclining = view.findViewById(R.id.linear_reclining);
        edit_yes_reclining = view.findViewById(R.id.edit_yes_reclining);
        spinner_typical_week_moderate_recreational = view.findViewById(R.id.spinner_typical_week_moderate_recreational);
        checkBoxCricket = view.findViewById(R.id.checkBoxCricket);
        checkBoxYoga = view.findViewById(R.id.checkBoxYoga);
        spinner_reclining = view.findViewById(R.id.spinner_reclining);
        checkBoxAerobics = view.findViewById(R.id.checkBoxAerobics);
        checkBoxExercise = view.findViewById(R.id.checkBoxExercise);
        checkBoxOthersDancing = view.findViewById(R.id.checkBoxOthersDancing);
        checkBoxOthers_moderate_recreational_others = view.findViewById(R.id.checkBoxOthers_moderate_recreational_others);
        linear_recreational_activities_typical = view.findViewById(R.id.linear_recreational_activities_typical);
        checkBoxOthers_recreational = view.findViewById(R.id.checkBoxOthers_recreational);
        spinner_typical_week_recreational = view.findViewById(R.id.spinner_typical_week_recreational);
        checkBoxTenis = view.findViewById(R.id.checkBoxTenis);
        edit_typical_day_recreational = view.findViewById(R.id.edit_typical_day_recreational);
        linear_recreational_activities_typical_day = view.findViewById(R.id.linear_recreational_activities_typical_day);
        spinner_moderate_intensity_recreational_activities = view.findViewById(R.id.spinner_moderate_intensity_recreational_activities);
        linear_moderate_intensity_recreational_activities = view.findViewById(R.id.linear_moderate_intensity_recreational_activities);
        checkBoxVolleyball = view.findViewById(R.id.checkBoxVolleyball);
        checkBoxFootbal = view.findViewById(R.id.checkBoxFootbal);
        checkBoxHadudu = view.findViewById(R.id.checkBoxHadudu);
        checkBoxHockey = view.findViewById(R.id.checkBoxHockey);
        checkBoxSwimming = view.findViewById(R.id.checkBoxSwimming);
        checkBoxBadminton = view.findViewById(R.id.checkBoxBadminton);
        checkBoxRunning = view.findViewById(R.id.checkBoxRunning);
        linear_heart_show_moderate_card_typical = view.findViewById(R.id.linear_heart_show_moderate_card_typical);
        checkBoxOthers1 = view.findViewById(R.id.checkBoxOthers1);
        linear_recreational_activities = view.findViewById(R.id.linear_recreational_activities);
        spinner_recreational_activities = view.findViewById(R.id.spinner_recreational_activities);
        edit_typical_day_moderate = view.findViewById(R.id.edit_typical_day_moderate);
        spinner_typical_week_moderate = view.findViewById(R.id.spinner_typical_week_moderate);
        checkBoxHouseHoldWork1 = view.findViewById(R.id.checkBoxHouseHoldWork1);
        checkBoxCloth = view.findViewById(R.id.checkBoxCloth);
        checkBoxParlour = view.findViewById(R.id.checkBoxParlour);
        checkBoxFarming = view.findViewById(R.id.checkBoxFarming);
        checkBoxRoping = view.findViewById(R.id.checkBoxRoping);
        linear_heart_show_card_typical_week = view.findViewById(R.id.linear_heart_show_card_typical_week);
        linear_heart_show_moderate_card = view.findViewById(R.id.linear_heart_show_moderate_card);
        linear_smoke_per_day = view.findViewById(R.id.linear_smoke_per_day);
        spinner_smoke = view.findViewById(R.id.spinner_smoke);
        linear_smoke = view.findViewById(R.id.linear_smoke);
        linear_heart_show_card_day = view.findViewById(R.id.linear_heart_show_card_day);
        edit_smoke_years = view.findViewById(R.id.edit_smoke_years);
        edit_smoke_stick = view.findViewById(R.id.edit_smoke_stick);
        spinner_jorda = view.findViewById(R.id.spinner_jorda);
        lineat_jorda = view.findViewById(R.id.lineat_jorda);
        edit_jorda = view.findViewById(R.id.edit_jorda);
        spinner_workplace = view.findViewById(R.id.spinner_workplace);
        linear_workplace = view.findViewById(R.id.linear_workplace);
        edit_workplace = view.findViewById(R.id.edit_workplace);
        spinner_alcohol = view.findViewById(R.id.spinner_alcohol);
        linear_alcohol = view.findViewById(R.id.linear_alcohol);
        edit_alcohol = view.findViewById(R.id.edit_alcohol);
        spinner_fruits = view.findViewById(R.id.spinner_fruits);
        spinner_fruits_card = view.findViewById(R.id.spinner_fruits_card);
        spinner_vegetables = view.findViewById(R.id.spinner_vegetables);
        spinner_vegetables_card = view.findViewById(R.id.spinner_vegetables_card);
        spinner_salt_month = view.findViewById(R.id.spinner_salt_month);
        spinner_salt_meal = view.findViewById(R.id.spinner_salt_meal);
        linear_taking_meal = view.findViewById(R.id.linear_taking_meal);
        edit_yes_extra_salt = view.findViewById(R.id.edit_yes_extra_salt);
        spinner_heart_show_card = view.findViewById(R.id.spinner_heart_show_card);
        linear_heart_show_card = view.findViewById(R.id.linear_heart_show_card);
        checkBoxHeaveyLoad = view.findViewById(R.id.checkBoxHeaveyLoad);
        checkBoxDigging = view.findViewById(R.id.checkBoxDigging);
        checkBoxFurniture = view.findViewById(R.id.checkBoxFurniture);
        checkBoxPickingCrops = view.findViewById(R.id.checkBoxPickingCrops);
        checkBoxCuttingTrees = view.findViewById(R.id.checkBoxCuttingTrees);
        checkBoxBreakUpPaddy = view.findViewById(R.id.checkBoxBreakUpPaddy);
        checkBoxDrivingRickshaw = view.findViewById(R.id.checkBoxDrivingRickshaw);
        checkBoxFishing = view.findViewById(R.id.checkBoxFishing);
        checkBoxPlouging = view.findViewById(R.id.checkBoxPlouging);
        checkBoxHeaveyConstructionWork = view.findViewById(R.id.checkBoxHeaveyConstructionWork);
        checkBoxHeaveyGoods = view.findViewById(R.id.checkBoxHeaveyGoods);
        checkBoxHeaveyGoodsHead = view.findViewById(R.id.checkBoxHeaveyGoodsHead);
        checkBoxSoldDigging = view.findViewById(R.id.checkBoxSoldDigging);
        checkBoxWashing = view.findViewById(R.id.checkBoxWashing);
        checkBoxStepping = view.findViewById(R.id.checkBoxStepping);
        checkBoxOthers = view.findViewById(R.id.checkBoxOthers);
        spinner_typical_week = view.findViewById(R.id.spinner_typical_week);
        linear_heart_show_card_activities = view.findViewById(R.id.linear_heart_show_card_activities);
        edit_typical_day = view.findViewById(R.id.edit_typical_day);
        spinner_heart_show_moderate_card = view.findViewById(R.id.spinner_heart_show_moderate_card);
        checkBoxHouseHoldWork = view.findViewById(R.id.checkBoxHouseHoldWork);
        checkBoxGardening = view.findViewById(R.id.checkBoxGardening);
        checkBoxMilkingCows = view.findViewById(R.id.checkBoxMilkingCows);
        checkBoxCultivatingLand = view.findViewById(R.id.checkBoxCultivatingLand);
        checkBoxPlantingHarvest = view.findViewById(R.id.checkBoxPlantingHarvest);
        checkBoxWeavingCloth = view.findViewById(R.id.checkBoxWeavingCloth);
        checkBoxWashingCloths = view.findViewById(R.id.checkBoxWashingCloths);
        checkBoxRearing = view.findViewById(R.id.checkBoxRearing);
        checkBoxMixingCement = view.findViewById(R.id.checkBoxMixingCement);
        checkBoxWoodWork = view.findViewById(R.id.checkBoxWoodWork);
        checkBoxDrawingWater = view.findViewById(R.id.checkBoxDrawingWater);
        checkBoxCarryingLightWeight = view.findViewById(R.id.checkBoxCarryingLightWeight);
        checkBoxWashingCloths1 = view.findViewById(R.id.checkBoxWashingCloths1);
        checkBoxGardening1 = view.findViewById(R.id.checkBoxGardening1);
        checkBoxMilkingCows1 = view.findViewById(R.id.checkBoxMilkingCows1);
        tv_smoke_stick_day = view.findViewById(R.id.tv_smoke_stick_day);

        Paper.init(mActivity);
        String language = SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language", language);
        updateView((String) Paper.book().read("language"));
        yesNoArrayListForSmoke = Utils.getyesNoList(mActivity);
        yesNoArrayListForWorkplace = Utils.getyesNoList(mActivity);
        yesNoArrayListForAlcohol = Utils.getyesNoList(mActivity);
        yesNoArrayListForFruits = Utils.getfruitsModelList(mActivity);
        yesNoArrayListForFruitsCard = Utils.getfruitsCardModelList(mActivity);
        yesNoArrayListForVegetables = Utils.getvegetablesModelList(mActivity);
        yesNoArrayListForVegetablesCard = Utils.getvegetableCardModelList(mActivity);
        yesNoArrayListForJorda = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerateIntensityHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerateRecreationalHeartt = Utils.getyesNoList(mActivity);
        yesNoArrayListForReclinig = Utils.getyesNoList(mActivity);
        yesNoArrayListForVigorousIntensityHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForVigorousRecreationalHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForSaltBuy = Utils.getSaltModelList(mActivity);
        yesNoArrayListForTakingSalt = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerate = Utils.getModerateModelList(mActivity);
        yesNoArrayListForModerateRecreational = Utils.getModerateRecreationalModelList(mActivity);
        yesNoArrayListForTypicalVigorous = Utils.getTypicalVigorousModelList(mActivity);
        yesNoArrayListForTypicalVigorousRecreation = Utils.getTypicalVigorousRecreationModelList(mActivity);


        yesNoArrayListFor43_b_1 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_2 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_3 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_4 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_5 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_6 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_7 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_8 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_9 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_10 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_11 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_12 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_13 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_14 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_15 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor43_b_16 = Utils.getTypicalVigorousRecreationModelList(mActivity);

        ///

        yesNoArrayListFor44_b_1 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_2 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_3 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_4 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_5 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_6 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_7 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_8 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_9 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_10 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_11 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_12 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_13 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_14 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_15 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_16 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_17 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_18 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_19 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_20 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor44_b_21 = Utils.getTypicalVigorousRecreationModelList(mActivity);

        ///
        yesNoArrayListFor45_b_1 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_2 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_3 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_4 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_5 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_6 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_7 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_8 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor45_b_9 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        ////
        yesNoArrayListFor46_b_1 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_2 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_3 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_4 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_5 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_6 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_7 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_8 = Utils.getTypicalVigorousRecreationModelList(mActivity);
        yesNoArrayListFor46_b_9 = Utils.getTypicalVigorousRecreationModelList(mActivity);


        initDiabetisSpinner();
        initJordaSpinner();
        initWorkstationSpinner();
        initAlcoholSpinner();
        initFruitsSpinner();
        initFruitsCardSpinner();
        initVegetablesSpinner();
        initVegetablesCardSpinner();
        initTalkingSaltSpinner();
        initBuySaltSpinner();
        initHeartShowCardSpinner();
        initHeartShowModerateCardSpinner();
        initRecreationalActivitiesSpinner();
        initModerateRecreationalActivitiesSpinner();
        initRecilingSpinner();
        initVigiriousTypicalSpinner();
        initModerateRecreationalTypicalSpinner();
        initVigriousRecreationalTypicalSpinner();
        initModerateTypicalSpinner();
        initVigiorosIntensityClickListener();
        initModerateIntensityClickListener();
        initVigiorosIntensityRecreationClickListener();
        initModerateIntensityRecreationClickListener();
        initVigirousIntensityTypicalSpinner();
        initModerateIntensityTypicalSpinner();
        initVigiriousIntensityRecreationalSpinner();
        initModerateIntensityRecreationSpinner();

        initFirst();
        initSecond();
        initThird();
        initFourth();

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
//        edit_typical_day.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment dFragment = new TypicalDayTimePickerFragment();
//
//                dFragment.show(getFragmentManager(), "Time Picker");
//            }
//        });
//        edit_typical_day_moderate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment dFragment = new TypicalDayModerateTimePickerFragment();
//
//                dFragment.show(getFragmentManager(), "Time Picker");
//            }
//        });
//        edit_typical_day_recreational.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment dFragment = new TypicalDayRecreationalTimePickerFragment();
//
//                dFragment.show(getFragmentManager(), "Time Picker");
//            }
//        });
//        edit_typical_day_moderate_recreational.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment dFragment = new ModerateRecreationalTimePickerFragment();
//
//                dFragment.show(getFragmentManager(), "Time Picker");
//            }
//        });
        show();
        initMat();
        initTouch46();
        initTouch45();
        initTouch44();
        initTouch43();

    }
    private void initTouch43(){
        edit_typical_day_43_a1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a13.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a14.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a15.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_43_a16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });

    }
    private void initTouch44(){
        edit_typical_day_moderate_44_a1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a13.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a14.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a15.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a17.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });

        edit_typical_day_moderate_44_a19.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a20.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_44_a21.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
    }
    private void initTouch45(){
        edit_typical_day_recreational_45_a1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_recreational_45_a9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });

    }
    private void initTouch46(){
        edit_typical_day_moderate_recreational_46_a1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });
        edit_typical_day_moderate_recreational_46_a9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
                return false;
            }
        });

    }
    private void initMat(){

        checkbox_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_mat.isChecked()) {
                    linear_mat.setVisibility(View.VISIBLE);
                    int vi1= 0;
                    try {
                        vi1 = Integer.parseInt(edit_typical_day_43_a1.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi1Sum=vigirousIntensityb1*vi1*8;

                    int vi2= 0;
                    try {
                        vi2 = Integer.parseInt(edit_typical_day_43_a2.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi2Sum=vigirousIntensityb2*vi2*8;

                    int vi3= 0;
                    try {
                        vi3 = Integer.parseInt(edit_typical_day_43_a3.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi3Sum=vigirousIntensityb3*vi3*8;

                    int vi4= 0;
                    try {
                        vi4 = Integer.parseInt(edit_typical_day_43_a4.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi4Sum=vigirousIntensityb4*vi4*8;

                    int vi5= 0;
                    try {
                        vi5 = Integer.parseInt(edit_typical_day_43_a5.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi5Sum=vigirousIntensityb5*vi5*8;

                    int vi6= 0;
                    try {
                        vi6 = Integer.parseInt(edit_typical_day_43_a6.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi6Sum=vigirousIntensityb6*vi6*8;

                    int vi7= 0;
                    try {
                        vi7 = Integer.parseInt(edit_typical_day_43_a7.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi7Sum=vigirousIntensityb7*vi7*8;

                    int vi8= 0;
                    try {
                        vi8 = Integer.parseInt(edit_typical_day_43_a8.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi8Sum=vigirousIntensityb8*vi8*8;

                    int vi9= 0;
                    try {
                        vi9 = Integer.parseInt(edit_typical_day_43_a9.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi9Sum=vigirousIntensityb9*vi9*8;

                    int vi10= 0;
                    try {
                        vi10 = Integer.parseInt(edit_typical_day_43_a10.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi10Sum=vigirousIntensityb10*vi10*8;

                    int vi11= 0;
                    try {
                        vi11 = Integer.parseInt(edit_typical_day_43_a11.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi11Sum=vigirousIntensityb11*vi11*8;

                    int vi12= 0;
                    try {
                        vi12 = Integer.parseInt(edit_typical_day_43_a12.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi12Sum=vigirousIntensityb12*vi12*8;

                    int vi13= 0;
                    try {
                        vi13 = Integer.parseInt(edit_typical_day_43_a13.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi13Sum=vigirousIntensityb13*vi13*8;

                    int vi14= 0;
                    try {
                        vi14 = Integer.parseInt(edit_typical_day_43_a14.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi14Sum=vigirousIntensityb14*vi14*8;

                    int vi15= 0;
                    try {
                        vi15 = Integer.parseInt(edit_typical_day_43_a15.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi15Sum=vigirousIntensityb15*vi15*8;

                    int vi16= 0;
                    try {
                        vi16 = Integer.parseInt(edit_typical_day_43_a16.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vi16Sum=vigirousIntensityb16*vi16*8;

                    int sum=vi1Sum+vi2Sum+vi3Sum+vi4Sum+vi5Sum+vi6Sum+vi7Sum+vi8Sum+vi9Sum+vi10Sum+vi11Sum+vi12Sum+vi13Sum+vi14Sum+vi15Sum+vi16Sum;


                    int mi1= 0;
                    try {
                        mi1 = Integer.parseInt(edit_typical_day_moderate_44_a1.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi1Sum=moderateIntensityb1*mi1*4;

                    int mi2= 0;
                    try {
                        mi2 = Integer.parseInt(edit_typical_day_moderate_44_a2.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi2Sum=moderateIntensityb2*mi2*4;

                    int mi3= 0;
                    try {
                        mi3 = Integer.parseInt(edit_typical_day_moderate_44_a3.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi3Sum=moderateIntensityb3*mi3*4;

                    int mi4= 0;
                    try {
                        mi4 = Integer.parseInt(edit_typical_day_moderate_44_a4.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi4Sum=moderateIntensityb4*mi4*4;

                    int mi5= 0;
                    try {
                        mi5 = Integer.parseInt(edit_typical_day_moderate_44_a5.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi5Sum=moderateIntensityb5*mi5*4;

                    int mi6= 0;
                    try {
                        mi6 = Integer.parseInt(edit_typical_day_moderate_44_a6.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi6Sum=moderateIntensityb6*mi6*4;

                    int mi7= 0;
                    try {
                        mi7 = Integer.parseInt(edit_typical_day_moderate_44_a7.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi7Sum=moderateIntensityb7*mi7*4;

                    int mi8= 0;
                    try {
                        mi8 = Integer.parseInt(edit_typical_day_moderate_44_a8.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi8Sum=moderateIntensityb8*mi8*4;

                    int mi9= 0;
                    try {
                        mi9 = Integer.parseInt(edit_typical_day_moderate_44_a9.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi9Sum=moderateIntensityb9*mi9*4;

                    int mi10= 0;
                    try {
                        mi10 = Integer.parseInt(edit_typical_day_moderate_44_a10.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi10Sum=vigirousIntensityb10*mi10*4;

                    int mi11= 0;
                    try {
                        mi11 = Integer.parseInt(edit_typical_day_moderate_44_a11.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi11Sum=moderateIntensityb11*mi11*4;

                    int mi12= 0;
                    try {
                        mi12 = Integer.parseInt(edit_typical_day_moderate_44_a12.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi12Sum=moderateIntensityb12*mi12*4;

                    int mi13= 0;
                    try {
                        mi13 = Integer.parseInt(edit_typical_day_moderate_44_a13.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi13Sum=moderateIntensityb13*mi13*4;

                    int mi14= 0;
                    try {
                        mi14 = Integer.parseInt(edit_typical_day_moderate_44_a14.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi14Sum=moderateIntensityb14*mi14*4;

                    int mi15= 0;
                    try {
                        mi15 = Integer.parseInt(edit_typical_day_moderate_44_a15.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi15Sum=moderateIntensityb15*mi15*4;

                    int mi16= 0;
                    try {
                        mi16 = Integer.parseInt(edit_typical_day_moderate_44_a16.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi16Sum=moderateIntensityb16*mi16*4;

                    int mi17= 0;
                    try {
                        mi17 = Integer.parseInt(edit_typical_day_moderate_44_a17.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi17Sum=moderateIntensityb17*mi17*4;

                    int mi18= 0;
                    try {
                        mi18 = Integer.parseInt(edit_typical_day_moderate_44_a18.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi18Sum=moderateIntensityb18*mi18*4;

                    int mi19= 0;
                    try {
                        mi19 = Integer.parseInt(edit_typical_day_moderate_44_a19.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi19Sum=moderateIntensityb19*mi19*4;

                    int mi20= 0;
                    try {
                        mi20 = Integer.parseInt(edit_typical_day_moderate_44_a20.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi20Sum=moderateIntensityb20*mi20*4;

                    int mi21= 0;
                    try {
                        mi21 = Integer.parseInt(edit_typical_day_moderate_44_a21.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mi21Sum=moderateIntensityb21*mi21*4;

                    int sum2=mi1Sum+mi2Sum+mi3Sum+mi4Sum+mi5Sum+mi6Sum+mi7Sum+mi8Sum+mi9Sum+mi10Sum+mi11Sum+mi12Sum+mi13Sum+mi14Sum+mi15Sum+mi16Sum+mi17Sum+mi18Sum+mi19Sum+mi20Sum+mi21Sum;

                    int vri1= 0;
                    try {
                        vri1 = Integer.parseInt(edit_typical_day_recreational_45_a1.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri1Sum=vigiriousRecreationIntensityb1*vri1*4;

                    int vri2= 0;
                    try {
                        vri2 = Integer.parseInt(edit_typical_day_recreational_45_a2.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri2Sum=vigiriousRecreationIntensityb2*vri2*4;

                    int vri3= 0;
                    try {
                        vri3 = Integer.parseInt(edit_typical_day_recreational_45_a3.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri3Sum=vigiriousRecreationIntensityb3*vri3*4;

                    int vri4= 0;
                    try {
                        vri4 = Integer.parseInt(edit_typical_day_recreational_45_a4.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri4Sum=vigiriousRecreationIntensityb4*vri4*4;

                    int vri5= 0;
                    try {
                        vri5 = Integer.parseInt(edit_typical_day_recreational_45_a5.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri5Sum=vigiriousRecreationIntensityb5*vri5*4;

                    int vri6= 0;
                    try {
                        vri6 = Integer.parseInt(edit_typical_day_recreational_45_a6.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri6Sum=vigiriousRecreationIntensityb6*vri6*4;

                    int vri7= 0;
                    try {
                        vri7 = Integer.parseInt(edit_typical_day_recreational_45_a7.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri7Sum=vigiriousRecreationIntensityb7*vri7*4;

                    int vri8= 0;
                    try {
                        vri8 = Integer.parseInt(edit_typical_day_recreational_45_a8.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri8Sum=vigiriousRecreationIntensityb8*vri8*4;

                    int vri9= 0;
                    try {
                        vri9 = Integer.parseInt(edit_typical_day_recreational_45_a9.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int vri9Sum=vigiriousRecreationIntensityb9*vri9*4;



                    int sum3=vri1Sum+vri2Sum+vri3Sum+vri4Sum+vri5Sum+vri6Sum+vri7Sum+vri8Sum+vri9Sum;


                    int mri1= 0;
                    try {
                        mri1 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a1.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri1Sum=moderateRecreationIntensityb1*mri1*4;

                    int mri2= 0;
                    try {
                        mri2 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a2.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri2Sum=moderateRecreationIntensityb2*mri2*4;

                    int mri3= 0;
                    try {
                        mri3 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a3.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri3Sum=moderateRecreationIntensityb3*mri3*4;

                    int mri4= 0;
                    try {
                        mri4 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a4.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri4Sum=moderateRecreationIntensityb4*mri4*4;

                    int mri5= 0;
                    try {
                        mri5 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a5.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri5Sum=moderateRecreationIntensityb5*mri5*4;

                    int mri6= 0;
                    try {
                        mri6 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a6.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri6Sum=moderateRecreationIntensityb6*mri6*4;

                    int mri7= 0;
                    try {
                        mri7 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a7.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri7Sum=moderateRecreationIntensityb7*mri7*4;

                    int mri8= 0;
                    try {
                        mri8 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a8.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri8Sum=moderateRecreationIntensityb8*mri8*4;

                    int mri9= 0;
                    try {
                        mri9 = Integer.parseInt(edit_typical_day_moderate_recreational_46_a9.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    int mri9Sum=moderateRecreationIntensityb9*mri9*4;



                    int sum4=mri1Sum+mri2Sum+mri3Sum+mri4Sum+mri5Sum+mri6Sum+mri7Sum+mri8Sum+mri9Sum;
                    edit_mat.setText(String.valueOf(sum+sum2+sum3+sum4));
                } else {
                    linear_mat.setVisibility(View.GONE);

                }
            }
        });
    }
    private void initFirst() {
        checkBoxHeaveyLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHeaveyLoad.isChecked()) {
                    linear_heart_show_card_typical_week_43_a1.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a1.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a1.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a1.setVisibility(View.GONE);
                }
            }
        });

        //Happen/////


        //
        checkBoxDigging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxDigging.isChecked()) {
                    linear_heart_show_card_typical_week_43_a2.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a2.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a2.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a2.setVisibility(View.GONE);
                }
            }
        });

        //
        checkBoxFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxFurniture.isChecked()) {
                    linear_heart_show_card_typical_week_43_a3.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a3.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a3.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a3.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxPickingCrops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxPickingCrops.isChecked()) {
                    linear_heart_show_card_typical_week_43_a4.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a4.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a4.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a4.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCuttingTrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxCuttingTrees.isChecked()) {
                    linear_heart_show_card_typical_week_43_a5.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a5.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a5.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a5.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxBreakUpPaddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxBreakUpPaddy.isChecked()) {
                    linear_heart_show_card_typical_week_43_a6.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a6.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a6.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a6.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxDrivingRickshaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxDrivingRickshaw.isChecked()) {
                    linear_heart_show_card_typical_week_43_a7.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a7.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a7.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a7.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxFishing.isChecked()) {
                    linear_heart_show_card_typical_week_43_a8.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a8.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a8.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a8.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxPlouging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxPlouging.isChecked()) {
                    linear_heart_show_card_typical_week_43_a9.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a9.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a9.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a9.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHeaveyConstructionWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHeaveyConstructionWork.isChecked()) {
                    linear_heart_show_card_typical_week_43_a10.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a10.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a10.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a10.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHeaveyGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHeaveyGoods.isChecked()) {
                    linear_heart_show_card_typical_week_43_a11.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a11.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a11.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a11.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHeaveyGoodsHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHeaveyGoodsHead.isChecked()) {
                    linear_heart_show_card_typical_week_43_a12.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a12.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a12.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a12.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxSoldDigging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);

                if (checkBoxSoldDigging.isChecked()) {
                    linear_heart_show_card_typical_week_43_a13.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a13.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a13.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a13.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxWashing.isChecked()) {
                    linear_heart_show_card_typical_week_43_a14.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a14.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a14.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a14.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxStepping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxStepping.isChecked()) {
                    linear_heart_show_card_typical_week_43_a15.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a15.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a15.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a15.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers.isChecked()) {
                    linear_heart_show_card_typical_week_43_a16.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities_43_a16.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_card_typical_week_43_a16.setVisibility(View.GONE);
                    linear_heart_show_card_activities_43_a16.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initSecond() {
        checkBoxHouseHoldWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHouseHoldWork.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a1.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a1.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a1.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a1.setVisibility(View.GONE);
                }
            }
        });

        //

        checkBoxGardening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxGardening.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a2.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a2.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a2.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a2.setVisibility(View.GONE);
                }
            }
        });

        //
        checkBoxMilkingCows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxMilkingCows.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a3.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a3.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a3.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a3.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCultivatingLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCultivatingLand.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a4.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a4.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a4.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a4.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxPlantingHarvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxPlantingHarvest.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a5.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a5.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a5.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a5.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxWeavingCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxWeavingCloth.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a6.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a6.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a6.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a6.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxWashingCloths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxWashingCloths.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a7.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a7.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a7.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a7.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxRearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxRearing.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a8.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a8.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a8.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a8.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxMixingCement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxMixingCement.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a9.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a9.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a9.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a9.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxWoodWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxWoodWork.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a10.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a10.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a10.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a10.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxDrawingWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxDrawingWater.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a11.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a11.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a11.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a11.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCarryingLightWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxCarryingLightWeight.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a12.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a12.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a12.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a12.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxWashingCloths1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxWashingCloths1.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a13.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a13.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a13.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a13.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxGardening1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxGardening1.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a14.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a14.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a14.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a14.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxMilkingCows1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxMilkingCows1.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a15.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a15.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a15.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a15.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxRoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHouseHoldWork.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a16.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a16.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a16.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a16.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxFarming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxFarming.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a17.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a17.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a17.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a17.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxParlour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxParlour.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a18.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a18.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a18.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a18.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxCloth.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a19.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a19.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a19.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a19.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHouseHoldWork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHouseHoldWork1.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a20.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a20.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a20.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a20.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxOthers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxOthers1.isChecked()) {
                    linear_heart_show_moderate_card_typical_44_a21.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day_44_a21.setVisibility(View.VISIBLE);

                } else {
                    linear_heart_show_moderate_card_typical_44_a21.setVisibility(View.GONE);
                    linear_heart_show_card_day_44_a21.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initThird() {
        checkBoxRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxRunning.isChecked()) {
                    linear_recreational_activities_typical_45_a1.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a1.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a1.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a1.setVisibility(View.GONE);
                }
            }
        });

        //
        checkBoxBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxBadminton.isChecked()) {
                    linear_recreational_activities_typical_45_a2.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a2.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a2.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a2.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxSwimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxSwimming.isChecked()) {
                    linear_recreational_activities_typical_45_a3.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a3.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a3.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a3.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHockey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHockey.isChecked()) {
                    linear_recreational_activities_typical_45_a4.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a4.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a4.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a4.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxHadudu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxHadudu.isChecked()) {
                    linear_recreational_activities_typical_45_a5.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a5.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a5.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a5.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxFootbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxRunning.isChecked()) {
                    linear_recreational_activities_typical_45_a6.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a6.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a6.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a6.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxVolleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxVolleyball.isChecked()) {
                    linear_recreational_activities_typical_45_a7.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a7.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a7.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a7.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxTenis.isChecked()) {
                    linear_recreational_activities_typical_45_a8.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a8.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a8.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a8.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxOthers_recreational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxOthers_recreational.isChecked()) {
                    linear_recreational_activities_typical_45_a9.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day_45_a9.setVisibility(View.VISIBLE);

                } else {
                    linear_recreational_activities_typical_45_a9.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day_45_a9.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initFourth() {
        checkBoxFastWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxFastWalking.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a1.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a1.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a1.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a1.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxJogging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxJogging.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a2.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a2.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a2.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a2.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxCycling.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a3.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a3.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a3.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a3.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxCricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxCricket.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a4.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a4.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a4.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a4.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxYoga.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a5.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a5.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a5.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a5.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxAerobics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxAerobics.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a6.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a6.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a6.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a6.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxExercise.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a7.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a7.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a7.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a7.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxOthersDancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxOthersDancing.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a8.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a8.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a8.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a8.setVisibility(View.GONE);
                }
            }
        });
        //
        checkBoxOthers_moderate_recreational_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox_mat.setChecked(false);
                linear_mat.setVisibility(View.GONE);
                if (checkBoxOthers_moderate_recreational_others.isChecked()) {
                    linear_moderate_intensity_recreational_activities_typical_46_a9.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a9.setVisibility(View.VISIBLE);

                } else {
                    linear_moderate_intensity_recreational_activities_typical_46_a9.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day_46_a9.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(mActivity, language);
        Resources resources = context.getResources();

        tv_typcal_week_43_a1.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a2.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a3.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a4.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a5.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a6.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a7.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a8.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a9.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a10.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a11.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a12.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a13.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a14.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a15.setText(resources.getString(R.string.week));
        tv_typcal_week_43_a16.setText(resources.getString(R.string.week));

        tv_typcal_week_44_a1.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a2.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a3.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a4.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a5.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a6.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a8.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a9.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a10.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a11.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a12.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a13.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a14.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a15.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a16.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a17.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a18.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a19.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a20.setText(resources.getString(R.string.week));
        tv_typcal_week_44_a21.setText(resources.getString(R.string.week));

        tv_typcal_week_45_a1.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a2.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a3.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a4.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a5.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a6.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a7.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a8.setText(resources.getString(R.string.week));
        tv_typcal_week_45_a9.setText(resources.getString(R.string.week));

        tv_typcal_week_46_a1.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a2.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a3.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a4.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a5.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a6.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a7.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a8.setText(resources.getString(R.string.week));
        tv_typcal_week_46_a9.setText(resources.getString(R.string.week));

        tv_typical_day_43_a1.setText(resources.getString(R.string.day));
        tv_typical_day_43_a2.setText(resources.getString(R.string.day));
        tv_typical_day_43_a3.setText(resources.getString(R.string.day));
        tv_typical_day_43_a4.setText(resources.getString(R.string.day));
        tv_typical_day_43_a5.setText(resources.getString(R.string.day));
        tv_typical_day_43_a6.setText(resources.getString(R.string.day));
        tv_typical_day_43_a7.setText(resources.getString(R.string.day));
        tv_typical_day_43_a8.setText(resources.getString(R.string.day));
        tv_typical_day_43_a9.setText(resources.getString(R.string.day));
        tv_typical_day_43_a10.setText(resources.getString(R.string.day));
        tv_typical_day_43_a11.setText(resources.getString(R.string.day));
        tv_typical_day_43_a12.setText(resources.getString(R.string.day));
        tv_typical_day_43_a13.setText(resources.getString(R.string.day));
        tv_typical_day_43_a14.setText(resources.getString(R.string.day));
        tv_typical_day_43_a15.setText(resources.getString(R.string.day));
        tv_typical_day_43_a16.setText(resources.getString(R.string.day));

        tv_typical_day_44_a1.setText(resources.getString(R.string.day));
        tv_typical_day_44_a2.setText(resources.getString(R.string.day));
        tv_typical_day_44_a3.setText(resources.getString(R.string.day));
        tv_typical_day_44_a4.setText(resources.getString(R.string.day));
        tv_typical_day_44_a5.setText(resources.getString(R.string.day));
        tv_typical_day_44_a6.setText(resources.getString(R.string.day));
        tv_typical_day_44_a7.setText(resources.getString(R.string.day));
        tv_typical_day_44_a8.setText(resources.getString(R.string.day));
        tv_typical_day_44_a9.setText(resources.getString(R.string.day));
        tv_typical_day_44_a10.setText(resources.getString(R.string.day));
        tv_typical_day_44_a11.setText(resources.getString(R.string.day));
        tv_typical_day_44_a12.setText(resources.getString(R.string.day));
        tv_typical_day_44_a13.setText(resources.getString(R.string.day));
        tv_typical_day_44_a14.setText(resources.getString(R.string.day));
        tv_typical_day_44_a15.setText(resources.getString(R.string.day));
        tv_typical_day_44_a16.setText(resources.getString(R.string.day));
        tv_typical_day_44_a17.setText(resources.getString(R.string.day));
        tv_typical_day_44_a18.setText(resources.getString(R.string.day));
        tv_typical_day_44_a19.setText(resources.getString(R.string.day));
        tv_typical_day_44_a20.setText(resources.getString(R.string.day));
        tv_typical_day_44_a21.setText(resources.getString(R.string.day));

        tv_typical_day_45_a1.setText(resources.getString(R.string.day));
        tv_typical_day_45_a2.setText(resources.getString(R.string.day));
        tv_typical_day_45_a3.setText(resources.getString(R.string.day));
        tv_typical_day_45_a4.setText(resources.getString(R.string.day));
        tv_typical_day_45_a5.setText(resources.getString(R.string.day));
        tv_typical_day_45_a6.setText(resources.getString(R.string.day));
        tv_typical_day_45_a7.setText(resources.getString(R.string.day));
        tv_typical_day_45_a8.setText(resources.getString(R.string.day));
        tv_typical_day_45_a9.setText(resources.getString(R.string.day));

        tv_typical_day_46_a1.setText(resources.getString(R.string.day));
        tv_typical_day_46_a2.setText(resources.getString(R.string.day));
        tv_typical_day_46_a3.setText(resources.getString(R.string.day));
        tv_typical_day_46_a4.setText(resources.getString(R.string.day));
        tv_typical_day_46_a5.setText(resources.getString(R.string.day));
        tv_typical_day_46_a6.setText(resources.getString(R.string.day));
        tv_typical_day_46_a7.setText(resources.getString(R.string.day));
        tv_typical_day_46_a8.setText(resources.getString(R.string.day));
        tv_typical_day_46_a9.setText(resources.getString(R.string.day));

        tv_smoke_stick_day.setText(resources.getString(R.string.stick));
        tv_smoke.setText(resources.getString(R.string.smoke));
        tv_smoke_day.setText(resources.getString(R.string.smoke_years));
        tv_jorda.setText(resources.getString(R.string.jorda));
        tv_jorda_day.setText(resources.getString(R.string.jorda_years));
        tv_workplace.setText(resources.getString(R.string.workplace));
        tv_workplace_day.setText(resources.getString(R.string.workplace_years));
        tv_alcohol.setText(resources.getString(R.string.alcohol));
        tv_alcohol_day.setText(resources.getString(R.string.alcohol_years));
        tv_fruits.setText(resources.getString(R.string.fruits));
        tv_fruits_card.setText(resources.getString(R.string.fruits_show_card));
        tv_vegetables.setText(resources.getString(R.string.vegetables));
        tv_vegetables_card.setText(resources.getString(R.string.vegetables_show_card));
        tv_salt_month.setText(resources.getString(R.string.buy_salt));
        tv_salt_meal.setText(resources.getString(R.string.talking_meal));
        tv_extra_salt.setText(resources.getString(R.string.talking_salt));
        tv_vigirous_intensity_card.setText(resources.getString(R.string.vigorous_intensity));
        tv_vigirous_intensity_card_checkbox.setText(resources.getString(R.string.yes_activities));
        tv_typcal_week_1.setText(resources.getString(R.string.week));
        tv_typical_day_1.setText(resources.getString(R.string.day));
        tv_moderate_intensity_card.setText(resources.getString(R.string.moderate_intensit));
        tv_moderate_intensity_card_checkbox.setText(resources.getString(R.string.yes_activities));
        tv_typcal_week_2.setText(resources.getString(R.string.week));
        tv_typical_day_2.setText(resources.getString(R.string.day));
        tv_vigorous_intensity_recreational.setText(resources.getString(R.string.vigorous_intensity_recreational));
        tv_vigorous_intensity_recreational_checkbox.setText(resources.getString(R.string.yes_activities));
        tv_typcal_week_3.setText(resources.getString(R.string.week));
        tv_typical_day_3.setText(resources.getString(R.string.day));
        tv_moderate_intensity_recreational_card.setText(resources.getString(R.string.moderate_intensity_recreational));
        tv_moderate_intensity_recreational_card_checkbox.setText(resources.getString(R.string.yes_activities));
        tv_typcal_week_4.setText(resources.getString(R.string.week));
        tv_typical_day_4.setText(resources.getString(R.string.day));
        tv_reclining.setText(resources.getString(R.string.reclining));
        tv_typical_day_5.setText(resources.getString(R.string.day));
        checkBoxHeaveyLoad.setText(resources.getString(R.string.carrying_lifting));
        checkBoxDigging.setText(resources.getString(R.string.digging));
        checkBoxFurniture.setText(resources.getString(R.string.furniture));
        checkBoxPickingCrops.setText(resources.getString(R.string.crops));
        checkBoxCuttingTrees.setText(resources.getString(R.string.cutt_tree));
        checkBoxBreakUpPaddy.setText(resources.getString(R.string.paddy));
        checkBoxDrivingRickshaw.setText(resources.getString(R.string.rickshaw));
        checkBoxFishing.setText(resources.getString(R.string.fishing));
        checkBoxPlouging.setText(resources.getString(R.string.plouging));
        checkBoxHeaveyConstructionWork.setText(resources.getString(R.string.construction));
        checkBoxHeaveyGoods.setText(resources.getString(R.string.heavy));
        checkBoxHeaveyGoodsHead.setText(resources.getString(R.string.heavy_head));
        checkBoxSoldDigging.setText(resources.getString(R.string.soil_digging));
        checkBoxWashing.setText(resources.getString(R.string.washing_heavy_clothes));
        checkBoxStepping.setText(resources.getString(R.string.stepping));
        checkBoxOthers.setText(resources.getString(R.string.others));
        edit_typical_day.setHint(resources.getString(R.string.day_1));
        checkBoxHouseHoldWork.setText(resources.getString(R.string.household_work));
        checkBoxGardening.setText(resources.getString(R.string.gardening));
        checkBoxMilkingCows.setText(resources.getString(R.string.milking));
        checkBoxCultivatingLand.setText(resources.getString(R.string.cultivate));
        checkBoxPlantingHarvest.setText(resources.getString(R.string.planting_harvesting));
        checkBoxWeavingCloth.setText(resources.getString(R.string.weaving_cloth));
        checkBoxWashingCloths.setText(resources.getString(R.string.washing_cloth_carpet));
        checkBoxRearing.setText(resources.getString(R.string.rearing_tending));
        checkBoxMixingCement.setText(resources.getString(R.string.masonary));
        checkBoxWoodWork.setText(resources.getString(R.string.woodwork));
        checkBoxDrawingWater.setText(resources.getString(R.string.drawing_water));
        checkBoxCarryingLightWeight.setText(resources.getString(R.string.carrying_light_weight));
        checkBoxWashingCloths1.setText(resources.getString(R.string.washing_clothes));
        checkBoxGardening1.setText(resources.getString(R.string.gardening));
        checkBoxMilkingCows1.setText(resources.getString(R.string.milking));
        checkBoxRoping.setText(resources.getString(R.string.roping));
        checkBoxFarming.setText(resources.getString(R.string.farming));
        checkBoxParlour.setText(resources.getString(R.string.parlour));
        checkBoxCloth.setText(resources.getString(R.string.weaving_cloth));
        checkBoxHouseHoldWork1.setText(resources.getString(R.string.household_work));
        checkBoxOthers1.setText(resources.getString(R.string.others));
        edit_typical_day_moderate.setHint(resources.getString(R.string.day_1));
        checkBoxVolleyball.setText(resources.getString(R.string.volleyball));
        checkBoxFootbal.setText(resources.getString(R.string.football));
        checkBoxHadudu.setText(resources.getString(R.string.hadudu));
        checkBoxHockey.setText(resources.getString(R.string.hockey));
        checkBoxSwimming.setText(resources.getString(R.string.fast_swimming));
        checkBoxBadminton.setText(resources.getString(R.string.badminton));
        checkBoxRunning.setText(resources.getString(R.string.running));
        checkBoxTenis.setText(resources.getString(R.string.tennis));
        checkBoxOthers_recreational.setText(resources.getString(R.string.others));
        edit_typical_day_recreational.setHint(resources.getString(R.string.hour));
        checkBoxFastWalking.setText(resources.getString(R.string.fast_walking));
        checkBoxJogging.setText(resources.getString(R.string.jogging));
        checkBoxCycling.setText(resources.getString(R.string.cycling));
        checkBoxCricket.setText(resources.getString(R.string.yoga));
        checkBoxAerobics.setText(resources.getString(R.string.aerobics));
        checkBoxExercise.setText(resources.getString(R.string.exercise));
        checkBoxOthersDancing.setText(resources.getString(R.string.dancing));
        checkBoxOthers_moderate_recreational_others.setText(resources.getString(R.string.others));
        edit_typical_day_moderate_recreational.setHint(resources.getString(R.string.hour));
        edit_yes_reclining.setHint(resources.getString(R.string.hour));
        edit_smoke_years.setHint(resources.getString(R.string.year));
        edit_jorda.setHint(resources.getString(R.string.year));
        edit_alcohol.setHint(resources.getString(R.string.year));
        edit_workplace.setHint(resources.getString(R.string.year));
        edit_smoke_stick.setHint(resources.getString(R.string.pcs));
        tv_extra_salt.setHint(resources.getString(R.string.day_1));
    }

    public static class TypicalDayTimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String format = "";
            if (hourOfDay == 0) {
                hourOfDay += 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                hourOfDay -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            int length = String.valueOf(minute).length();
            int length1 = String.valueOf(hourOfDay).length();
            String value = "";
            String value1 = "";
            if (length > 1) {
                value = String.valueOf(minute);
            } else {
                value = "0" + String.valueOf(minute);
            }
            if (length1 > 1) {
                value1 = String.valueOf(hourOfDay);
            } else {
                value1 = "0" + String.valueOf(hourOfDay);
            }
            edit_typical_day.setText(value1 + ":" + value + " " + format);
        }

    }

    public static class TypicalDayModerateTimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String format = "";
            if (hourOfDay == 0) {
                hourOfDay += 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                hourOfDay -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            int length = String.valueOf(minute).length();
            int length1 = String.valueOf(hourOfDay).length();
            String value = "";
            String value1 = "";
            if (length > 1) {
                value = String.valueOf(minute);
            } else {
                value = "0" + String.valueOf(minute);
            }
            if (length1 > 1) {
                value1 = String.valueOf(hourOfDay);
            } else {
                value1 = "0" + String.valueOf(hourOfDay);
            }
            edit_typical_day_moderate.setText(value1 + ":" + value + " " + format);
        }

    }

    public static class TypicalDayRecreationalTimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String format = "";
            if (hourOfDay == 0) {
                hourOfDay += 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                hourOfDay -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            int length = String.valueOf(minute).length();
            int length1 = String.valueOf(hourOfDay).length();
            String value = "";
            String value1 = "";
            if (length > 1) {
                value = String.valueOf(minute);
            } else {
                value = "0" + String.valueOf(minute);
            }
            if (length1 > 1) {
                value1 = String.valueOf(hourOfDay);
            } else {
                value1 = "0" + String.valueOf(hourOfDay);
            }
            edit_typical_day_recreational.setText(value1 + ":" + value + " " + format);
        }

    }

    public static class ModerateRecreationalTimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String format = "";
            if (hourOfDay == 0) {
                hourOfDay += 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                hourOfDay -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            int length = String.valueOf(minute).length();
            int length1 = String.valueOf(hourOfDay).length();
            String value = "";
            String value1 = "";
            if (length > 1) {
                value = String.valueOf(minute);
            } else {
                value = "0" + String.valueOf(minute);
            }
            if (length1 > 1) {
                value1 = String.valueOf(hourOfDay);
            } else {
                value1 = "0" + String.valueOf(hourOfDay);
            }
            edit_typical_day_moderate_recreational.setText(value1 + ":" + value + " " + format);
        }

    }

    private void show() {
        MemberHabit memberHabitsFor = Common.memberHabitRepository.getMemberHabit(update);
        if (memberHabitsFor != null) {
            int SmokeYesNo = 0;
            Questions questions1 = Common.qustionsRepository.getQuestions("Q32", update);
            try {
                SmokeYesNo = Integer.parseInt(questions1.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                SmokeYesNo = -1;
            }
            if (SmokeYesNo != 0) {
                int div = SmokeYesNo;

                if (div == 1) {
                    try {
                        Questions questions1a = Common.qustionsRepository.getQuestions("Q32a", update);
                        Questions questions1b = Common.qustionsRepository.getQuestions("Q32b", update);
                        edit_smoke_years.setText(String.valueOf(questions1a.answer));
                        edit_smoke_stick.setText(String.valueOf(questions1b.answer));
                    } catch (Exception e) {
                        edit_smoke_years.setText("");
                        edit_smoke_stick.setText("");
                    }
                }

                for (int i = 0; i < yesNoArrayListForSmoke.size(); i++) {
                    if (yesNoArrayListForSmoke.get(i).getId() == div) {
                        spinner_smoke.setSelection(i);
                    }
                }
            }

            initJarda();
            initWorkPlaceSmoke();
            initAlcohol();
            initFruitsTypical();
            initFruitsShowCard();
            initTypicalVegetable();
            initVegetablesShowCard();
            initSaltBuy();
            initTakingMeal();
            initRecllining();
            initVigorousIntensity();
            initModerateIntensity();
            initVigiriosIntensityRecreational();
            initModerateIntensityRecreational();
        }
    }

    private void initJarda() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q33", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q33a", update);
                    edit_jorda.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_jorda.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForJorda.size(); i++) {
                if (yesNoArrayListForJorda.get(i).getId() == div) {
                    spinner_jorda.setSelection(i);
                }
            }
        }
    }

    private void initWorkPlaceSmoke() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q34", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q34a", update);
                    edit_workplace.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_workplace.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForWorkplace.size(); i++) {
                if (yesNoArrayListForWorkplace.get(i).getId() == div) {
                    spinner_workplace.setSelection(i);
                }
            }
        }
    }

    private void initAlcohol() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q35", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q35a", update);
                    edit_alcohol.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_alcohol.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForAlcohol.size(); i++) {
                if (yesNoArrayListForAlcohol.get(i).getId() == div) {
                    spinner_alcohol.setSelection(i);
                }
            }
        }
    }

    private void initFruitsTypical() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q36", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;


            for (int i = 0; i < yesNoArrayListForFruits.size(); i++) {
                if (yesNoArrayListForFruits.get(i).getId() == div) {
                    spinner_fruits.setSelection(i);
                }
            }
        }
    }

    private void initFruitsShowCard() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q37", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;


            for (int i = 0; i < yesNoArrayListForFruitsCard.size(); i++) {
                if (yesNoArrayListForFruitsCard.get(i).getId() == div) {
                    spinner_fruits_card.setSelection(i);
                }
            }
        }
    }

    private void initTypicalVegetable() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q38", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;


            for (int i = 0; i < yesNoArrayListForVegetables.size(); i++) {
                if (yesNoArrayListForVegetables.get(i).getId() == div) {
                    spinner_vegetables.setSelection(i);
                }
            }
        }
    }

    private void initVegetablesShowCard() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q39", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;


            for (int i = 0; i < yesNoArrayListForVegetablesCard.size(); i++) {
                if (yesNoArrayListForVegetablesCard.get(i).getId() == div) {
                    spinner_vegetables_card.setSelection(i);
                }
            }
        }
    }

    private void initSaltBuy() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q41", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;


            for (int i = 0; i < yesNoArrayListForSaltBuy.size(); i++) {
                if (yesNoArrayListForSaltBuy.get(i).getId() == div) {
                    spinner_salt_month.setSelection(i);
                }
            }
        }
    }

    private void initTakingMeal() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q42", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q42a", update);
                    edit_yes_extra_salt.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_yes_extra_salt.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForTakingSalt.size(); i++) {
                if (yesNoArrayListForTakingSalt.get(i).getId() == div) {
                    spinner_salt_meal.setSelection(i);
                }
            }
        }
    }

    private void initVigorousIntensity() {
        int YesNo = 0;

        Questions questions1 = Common.qustionsRepository.getQuestions("Q43", update);
        Questions Q43a1 = Common.qustionsRepository.getQuestions("Q43a1", update);
        Questions Q43a2 = Common.qustionsRepository.getQuestions("Q43a2", update);
        Questions Q43a3 = Common.qustionsRepository.getQuestions("Q43a3", update);
        Questions Q43a4 = Common.qustionsRepository.getQuestions("Q43a4", update);
        Questions Q43a5 = Common.qustionsRepository.getQuestions("Q43a5", update);
        Questions Q43a6 = Common.qustionsRepository.getQuestions("Q43a6", update);
        Questions Q43a7 = Common.qustionsRepository.getQuestions("Q43a7", update);
        Questions Q43a8 = Common.qustionsRepository.getQuestions("Q43a8", update);
        Questions Q43a9 = Common.qustionsRepository.getQuestions("Q43a9", update);
        Questions Q43a10 = Common.qustionsRepository.getQuestions("Q43a10", update);
        Questions Q43a11 = Common.qustionsRepository.getQuestions("Q43a11", update);
        Questions Q43a12 = Common.qustionsRepository.getQuestions("Q43a12", update);
        Questions Q43a13 = Common.qustionsRepository.getQuestions("Q43a13", update);
        Questions Q43a14 = Common.qustionsRepository.getQuestions("Q43a14", update);
        Questions Q43a15 = Common.qustionsRepository.getQuestions("Q43a15", update);
        Questions Q43a16 = Common.qustionsRepository.getQuestions("Q43a16", update);


        if (Q43a1 != null) {
            checkBoxHeaveyLoad.setChecked(true);
            linear_heart_show_card_typical_week_43_a1.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a1.setVisibility(View.VISIBLE);
        }
        if (Q43a2 != null) {
            checkBoxDigging.setChecked(true);
            linear_heart_show_card_typical_week_43_a2.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a2.setVisibility(View.VISIBLE);
        }
        if (Q43a3 != null) {
            checkBoxFurniture.setChecked(true);
            linear_heart_show_card_typical_week_43_a3.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a1.setVisibility(View.VISIBLE);
        }
        if (Q43a4 != null) {
            checkBoxPickingCrops.setChecked(true);
            linear_heart_show_card_typical_week_43_a4.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a4.setVisibility(View.VISIBLE);
        }
        if (Q43a5 != null) {
            checkBoxCuttingTrees.setChecked(true);
            linear_heart_show_card_typical_week_43_a5.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a5.setVisibility(View.VISIBLE);
        }
        ////6///
        if (Q43a6 != null) {
            checkBoxBreakUpPaddy.setChecked(true);
            linear_heart_show_card_typical_week_43_a6.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a6.setVisibility(View.VISIBLE);
        }
        if (Q43a7 != null) {
            checkBoxDrivingRickshaw.setChecked(true);
            linear_heart_show_card_typical_week_43_a7.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a7.setVisibility(View.VISIBLE);
        }
        if (Q43a8 != null) {
            checkBoxFishing.setChecked(true);
            linear_heart_show_card_typical_week_43_a8.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a8.setVisibility(View.VISIBLE);
        }
        if (Q43a9 != null) {
            checkBoxPlouging.setChecked(true);
            linear_heart_show_card_typical_week_43_a9.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a9.setVisibility(View.VISIBLE);
        }
        if (Q43a10 != null) {
            checkBoxHeaveyConstructionWork.setChecked(true);
            linear_heart_show_card_typical_week_43_a10.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a10.setVisibility(View.VISIBLE);
        }
        //////11/////

        if (Q43a11 != null) {
            checkBoxHeaveyGoods.setChecked(true);
            linear_heart_show_card_typical_week_43_a11.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a11.setVisibility(View.VISIBLE);
        }
        if (Q43a12 != null) {
            checkBoxHeaveyGoodsHead.setChecked(true);
            linear_heart_show_card_typical_week_43_a12.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a12.setVisibility(View.VISIBLE);
        }
        if (Q43a13 != null) {
            checkBoxSoldDigging.setChecked(true);
            linear_heart_show_card_typical_week_43_a13.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a13.setVisibility(View.VISIBLE);
        }
        if (Q43a14 != null) {
            checkBoxWashing.setChecked(true);
            linear_heart_show_card_typical_week_43_a14.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a14.setVisibility(View.VISIBLE);
        }
        if (Q43a15 != null) {
            checkBoxStepping.setChecked(true);
            linear_heart_show_card_typical_week_43_a15.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a15.setVisibility(View.VISIBLE);
        }
        if (Q43a16 != null) {
            checkBoxOthers.setChecked(true);
            linear_heart_show_card_typical_week_43_a16.setVisibility(View.VISIBLE);
            linear_heart_show_card_activities_43_a16.setVisibility(View.VISIBLE);
        }
        ////////////////////////////////////////////Spineer


        Questions Q43b1 = Common.qustionsRepository.getQuestions("Q43b1", update);
        Questions Q43b2 = Common.qustionsRepository.getQuestions("Q43b2", update);
        Questions Q43b3 = Common.qustionsRepository.getQuestions("Q43b3", update);
        Questions Q43b4 = Common.qustionsRepository.getQuestions("Q43b4", update);
        Questions Q43b5 = Common.qustionsRepository.getQuestions("Q43b5", update);
        Questions Q43b6 = Common.qustionsRepository.getQuestions("Q43b6", update);
        Questions Q43b7 = Common.qustionsRepository.getQuestions("Q43b7", update);
        Questions Q43b8 = Common.qustionsRepository.getQuestions("Q43b8", update);
        Questions Q43b9 = Common.qustionsRepository.getQuestions("Q43b9", update);
        Questions Q43b10 = Common.qustionsRepository.getQuestions("Q43b10", update);
        Questions Q43b11 = Common.qustionsRepository.getQuestions("Q43b11", update);
        Questions Q43b12 = Common.qustionsRepository.getQuestions("Q43b12", update);
        Questions Q43b13 = Common.qustionsRepository.getQuestions("Q43b13", update);
        Questions Q43b14 = Common.qustionsRepository.getQuestions("Q43b14", update);
        Questions Q43b15 = Common.qustionsRepository.getQuestions("Q43b15", update);
        Questions Q43b16 = Common.qustionsRepository.getQuestions("Q43b16", update);

        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int b4 = 0;
        int b5 = 0;
        int b6 = 0;
        int b7 = 0;
        int b8 = 0;
        int b9 = 0;
        int b10 = 0;
        int b11 = 0;
        int b12 = 0;
        int b13 = 0;
        int b14 = 0;
        int b15 = 0;
        int b16 = 0;
        if (Q43b1 != null) {
            try {
                b1 = Integer.parseInt(Q43b1.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b1 = 1;
            }
            if (b1 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_1.size(); i++) {
                    if (yesNoArrayListFor43_b_1.get(i).getId() == b1) {
                        spinner_typical_week_43_a1.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b2 != null) {
            try {
                b2 = Integer.parseInt(Q43b2.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b2 = 1;
            }
            if (b2 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_2.size(); i++) {
                    if (yesNoArrayListFor43_b_2.get(i).getId() == b2) {
                        spinner_typical_week_43_a2.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b3 != null) {
            try {
                b3 = Integer.parseInt(Q43b3.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b3 = 1;
            }
            if (b3 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_3.size(); i++) {
                    if (yesNoArrayListFor43_b_3.get(i).getId() == b3) {
                        spinner_typical_week_43_a3.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b4 != null) {
            try {
                b4 = Integer.parseInt(Q43b4.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b4 = 1;
            }
            if (b4 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_4.size(); i++) {
                    if (yesNoArrayListFor43_b_4.get(i).getId() == b4) {
                        spinner_typical_week_43_a4.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b5 != null) {
            try {
                b5 = Integer.parseInt(Q43b5.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b5= 1;
            }
            if (b5 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_5.size(); i++) {
                    if (yesNoArrayListFor43_b_5.get(i).getId() == b5) {
                        spinner_typical_week_43_a5.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b6 != null) {
            try {
                b6 = Integer.parseInt(Q43b6.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b6= 1;
            }
            if (b6 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_6.size(); i++) {
                    if (yesNoArrayListFor43_b_6.get(i).getId() == b6) {
                        spinner_typical_week_43_a6.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b7 != null) {
            try {
                b7 = Integer.parseInt(Q43b7.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b7= 1;
            }
            if (b7 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_7.size(); i++) {
                    if (yesNoArrayListFor43_b_7.get(i).getId() == b7) {
                        spinner_typical_week_43_a7.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b8 != null) {
            try {
                b8 = Integer.parseInt(Q43b8.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b8= 1;
            }
            if (b8 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_8.size(); i++) {
                    if (yesNoArrayListFor43_b_8.get(i).getId() == b8) {
                        spinner_typical_week_43_a8.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b9 != null) {
            try {
                b9 = Integer.parseInt(Q43b9.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b9= 1;
            }
            if (b9 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_9.size(); i++) {
                    if (yesNoArrayListFor43_b_9.get(i).getId() == b9) {
                        spinner_typical_week_43_a9.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b10 != null) {
            try {
                b10 = Integer.parseInt(Q43b10.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b10= 1;
            }
            if (b10 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_10.size(); i++) {
                    if (yesNoArrayListFor43_b_10.get(i).getId() == b10) {
                        spinner_typical_week_43_a10.setSelection(i);
                    }
                }
            }
        }

        //////////////////////////////
        if (Q43b11 != null) {
            try {
                b11 = Integer.parseInt(Q43b11.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b11= 1;
            }
            if (b11 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_11.size(); i++) {
                    if (yesNoArrayListFor43_b_11.get(i).getId() == b11) {
                        spinner_typical_week_43_a11.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b12 != null) {
            try {
                b12 = Integer.parseInt(Q43b12.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b12= 1;
            }
            if (b12 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_12.size(); i++) {
                    if (yesNoArrayListFor43_b_12.get(i).getId() == b12) {
                        spinner_typical_week_43_a12.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b13 != null) {
            try {
                b13 = Integer.parseInt(Q43b13.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b13= 1;
            }
            if (b13 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_13.size(); i++) {
                    if (yesNoArrayListFor43_b_13.get(i).getId() == b13) {
                        spinner_typical_week_43_a13.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b14 != null) {
            try {
                b14= Integer.parseInt(Q43b14.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b14= 1;
            }
            if (b14 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_14.size(); i++) {
                    if (yesNoArrayListFor43_b_14.get(i).getId() == b14) {
                        spinner_typical_week_43_a14.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b15 != null) {
            try {
                b15 = Integer.parseInt(Q43b15.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b15= 1;
            }
            if (b15 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_15.size(); i++) {
                    if (yesNoArrayListFor43_b_15.get(i).getId() == b15) {
                        spinner_typical_week_43_a15.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q43b16 != null) {
            try {
                b16 = Integer.parseInt(Q43b16.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b16= 1;
            }
            if (b16 != 0) {
                for (int i = 0; i < yesNoArrayListFor43_b_16.size(); i++) {
                    if (yesNoArrayListFor43_b_16.get(i).getId() == b16) {
                        spinner_typical_week_43_a16.setSelection(i);
                    }
                }
            }
        }
        ////////////////////////////////////////////EditText//
        Questions Q43c1 = Common.qustionsRepository.getQuestions("Q43c1", update);
        Questions Q43c2 = Common.qustionsRepository.getQuestions("Q43c2", update);
        Questions Q43c3 = Common.qustionsRepository.getQuestions("Q43c3", update);
        Questions Q43c4 = Common.qustionsRepository.getQuestions("Q43c4", update);
        Questions Q43c5 = Common.qustionsRepository.getQuestions("Q43c5", update);
        Questions Q43c6 = Common.qustionsRepository.getQuestions("Q43c6", update);
        Questions Q43c7 = Common.qustionsRepository.getQuestions("Q43c7", update);
        Questions Q43c8 = Common.qustionsRepository.getQuestions("Q43c8", update);
        Questions Q43c9 = Common.qustionsRepository.getQuestions("Q43c9", update);
        Questions Q43c10 = Common.qustionsRepository.getQuestions("Q43c10", update);
        Questions Q43c11 = Common.qustionsRepository.getQuestions("Q43c11", update);
        Questions Q43c12 = Common.qustionsRepository.getQuestions("Q43c12", update);
        Questions Q43c13 = Common.qustionsRepository.getQuestions("Q43c13", update);
        Questions Q43c14 = Common.qustionsRepository.getQuestions("Q43c14", update);
        Questions Q43c15 = Common.qustionsRepository.getQuestions("Q43c15", update);
        Questions Q43c16 = Common.qustionsRepository.getQuestions("Q43c16", update);

        if (Q43c1!=null){
            edit_typical_day_43_a1.setText(Q43c1.answer);
        }
        if (Q43c2!=null){
            edit_typical_day_43_a2.setText(Q43c2.answer);
        }
        if (Q43c3!=null){
            edit_typical_day_43_a3.setText(Q43c3.answer);
        }
        if (Q43c4!=null){
            edit_typical_day_43_a4.setText(Q43c4.answer);
        }
        if (Q43c5!=null){
            edit_typical_day_43_a5.setText(Q43c5.answer);
        }
/////////////////
        if (Q43c6!=null){
            edit_typical_day_43_a6.setText(Q43c6.answer);
        }
        if (Q43c7!=null){
            edit_typical_day_43_a7.setText(Q43c7.answer);
        }
        if (Q43c8!=null){
            edit_typical_day_43_a8.setText(Q43c8.answer);
        }
        if (Q43c9!=null){
            edit_typical_day_43_a9.setText(Q43c9.answer);
        }
        if (Q43c10!=null){
            edit_typical_day_43_a10.setText(Q43c10.answer);
        }

        /////////////////
        if (Q43c11!=null){
            edit_typical_day_43_a11.setText(Q43c11.answer);
        }
        if (Q43c12!=null){
            edit_typical_day_43_a12.setText(Q43c12.answer);
        }
        if (Q43c13!=null){
            edit_typical_day_43_a13.setText(Q43c13.answer);
        }
        if (Q43c14!=null){
            edit_typical_day_43_a14.setText(Q43c14.answer);
        }
        if (Q43c15!=null){
            edit_typical_day_43_a15.setText(Q43c15.answer);
        }
        if (Q43c16!=null){
            edit_typical_day_43_a16.setText(Q43c16.answer);
        }
        ////////////////////////////////////////////EditText////


        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }

//        if (questions2 != null) {
//            String diac = questions2.answer;
//            String[] values = diac.split(",");
//            for (String s : values) {
//                Log.e("fdf", "dfdf" + s);
//                if (s.equals("1")) {
//                    checkBoxHeaveyLoad.setChecked(true);
//                } else if (s.equals("2")) {
//                    checkBoxDigging.setChecked(true);
//                } else if (s.equals("3")) {
//                    checkBoxFurniture.setChecked(true);
//                } else if (s.equals("4")) {
//                    checkBoxPickingCrops.setChecked(true);
//                } else if (s.equals("5")) {
//                    checkBoxCuttingTrees.setChecked(true);
//                } else if (s.equals("6")) {
//                    checkBoxBreakUpPaddy.setChecked(true);
//                } else if (s.equals("7")) {
//                    checkBoxDrivingRickshaw.setChecked(true);
//                } else if (s.equals("8")) {
//                    checkBoxFishing.setChecked(true);
//                } else if (s.equals("9")) {
//                    checkBoxPlouging.setChecked(true);
//                } else if (s.equals("10")) {
//                    checkBoxHeaveyConstructionWork.setChecked(true);
//                } else if (s.equals("11")) {
//                    checkBoxHeaveyGoods.setChecked(true);
//                } else if (s.equals("12")) {
//                    checkBoxHeaveyGoodsHead.setChecked(true);
//                } else if (s.equals("13")) {
//                    checkBoxSoldDigging.setChecked(true);
//                } else if (s.equals("14")) {
//                    checkBoxWashing.setChecked(true);
//                } else if (s.equals("15")) {
//                    checkBoxStepping.setChecked(true);
//                } else if (s.equals("16")) {
//                    checkBoxOthers.setChecked(true);
//                }
//
//            }
//        }


//        if (YesNob != 0) {
//            for (int i = 0; i < yesNoArrayListForTypicalVigorous.size(); i++) {
//                if (yesNoArrayListForTypicalVigorous.get(i).getId() == YesNob) {
//                    spinner_typical_week.setSelection(i);
//                }
//            }
//        }
        if (YesNo != 0) {
            int div = YesNo;
//
//            if (div == 1)
//            {
//                try {
//                    Questions questions1a = Common.qustionsRepository.getQuestions("Q43c", update);
//                    edit_typical_day.setText(questions1a.answer);
//                } catch (Exception e) {
//                    edit_typical_day.setText("");
//                }
//            }

            for (int i = 0; i < yesNoArrayListForVigorousIntensityHeart.size(); i++) {
                if (yesNoArrayListForVigorousIntensityHeart.get(i).getId() == div) {
                    spinner_heart_show_card.setSelection(i);
                }
            }
        }
    }
    //////////////

    private void initModerateIntensity() {
        int YesNo = 0;
        int YesNob = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q44", update);
        Questions Q44a1 = Common.qustionsRepository.getQuestions("Q44a1", update);
        Questions Q44a2 = Common.qustionsRepository.getQuestions("Q44a2", update);
        Questions Q44a3 = Common.qustionsRepository.getQuestions("Q44a3", update);
        Questions Q44a4 = Common.qustionsRepository.getQuestions("Q44a4", update);
        Questions Q44a5 = Common.qustionsRepository.getQuestions("Q44a5", update);
        Questions Q44a6 = Common.qustionsRepository.getQuestions("Q44a6", update);
        Questions Q44a7 = Common.qustionsRepository.getQuestions("Q44a7", update);
        Questions Q44a8 = Common.qustionsRepository.getQuestions("Q44a8", update);
        Questions Q44a9 = Common.qustionsRepository.getQuestions("Q44a9", update);
        Questions Q44a10 = Common.qustionsRepository.getQuestions("Q44a10", update);
        Questions Q44a11 = Common.qustionsRepository.getQuestions("Q44a11", update);
        Questions Q44a12 = Common.qustionsRepository.getQuestions("Q44a12", update);
        Questions Q44a13 = Common.qustionsRepository.getQuestions("Q44a13", update);
        Questions Q44a14 = Common.qustionsRepository.getQuestions("Q44a14", update);
        Questions Q44a15 = Common.qustionsRepository.getQuestions("Q44a15", update);
        Questions Q44a16 = Common.qustionsRepository.getQuestions("Q44a16", update);
        Questions Q44a17 = Common.qustionsRepository.getQuestions("Q44a17", update);
        Questions Q44a18 = Common.qustionsRepository.getQuestions("Q44a18", update);
        Questions Q44a19 = Common.qustionsRepository.getQuestions("Q44a19", update);
        Questions Q44a20 = Common.qustionsRepository.getQuestions("Q44a20", update);
        Questions Q44a21 = Common.qustionsRepository.getQuestions("Q44a21", update);

        if (Q44a1 != null) {
            checkBoxHouseHoldWork.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a1.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a1.setVisibility(View.VISIBLE);
        }
        if (Q44a2 != null) {
            checkBoxGardening.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a2.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a2.setVisibility(View.VISIBLE);
        }
        if (Q44a3 != null) {
            checkBoxMilkingCows.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a3.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a3.setVisibility(View.VISIBLE);
        }
        if (Q44a4 != null) {
            checkBoxCultivatingLand.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a4.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a4.setVisibility(View.VISIBLE);
        }
        if (Q44a5 != null) {
            checkBoxPlantingHarvest.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a5.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a5.setVisibility(View.VISIBLE);
        }
        ////6///
        if (Q44a6 != null) {
            checkBoxWeavingCloth.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a6.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a6.setVisibility(View.VISIBLE);
        }
        if (Q44a7 != null) {
            checkBoxWashingCloths.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a7.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a7.setVisibility(View.VISIBLE);
        }
        if (Q44a8 != null) {
            checkBoxRearing.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a8.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a8.setVisibility(View.VISIBLE);
        }
        if (Q44a9 != null) {
            checkBoxMixingCement.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a9.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a9.setVisibility(View.VISIBLE);
        }
        if (Q44a10 != null) {
            checkBoxWoodWork.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a10.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a10.setVisibility(View.VISIBLE);
        }
        //////11/////

        if (Q44a11 != null) {
            checkBoxDrawingWater.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a11.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a11.setVisibility(View.VISIBLE);
        }
        if (Q44a12 != null) {
            checkBoxCarryingLightWeight.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a12.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a12.setVisibility(View.VISIBLE);
        }
        if (Q44a13 != null) {
            checkBoxWashingCloths1.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a13.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a13.setVisibility(View.VISIBLE);
        }
        if (Q44a14 != null) {
            checkBoxGardening1.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a14.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a14.setVisibility(View.VISIBLE);
        }
        if (Q44a15 != null) {
            checkBoxMilkingCows1.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a15.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a15.setVisibility(View.VISIBLE);
        }
        if (Q44a16 != null) {
            checkBoxRoping.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a16.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a16.setVisibility(View.VISIBLE);
        }
        if (Q44a17 != null) {
            checkBoxFarming.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a17.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a17.setVisibility(View.VISIBLE);
        }
        if (Q44a18 != null) {
            checkBoxParlour.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a18.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a18.setVisibility(View.VISIBLE);
        }
        if (Q44a19 != null) {
            checkBoxCloth.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a19.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a19.setVisibility(View.VISIBLE);
        }
        if (Q44a20 != null) {
            checkBoxHouseHoldWork1.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a20.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a20.setVisibility(View.VISIBLE);
        }
        if (Q44a21 != null) {
            checkBoxOthers1.setChecked(true);
            linear_heart_show_moderate_card_typical_44_a21.setVisibility(View.VISIBLE);
            linear_heart_show_card_day_44_a21.setVisibility(View.VISIBLE);
        }

        ////////////////////////////////////////////Spineer


        Questions Q44b1 = Common.qustionsRepository.getQuestions("Q44b1", update);
        Questions Q44b2 = Common.qustionsRepository.getQuestions("Q44b2", update);
        Questions Q44b3 = Common.qustionsRepository.getQuestions("Q44b3", update);
        Questions Q44b4 = Common.qustionsRepository.getQuestions("Q44b4", update);
        Questions Q44b5 = Common.qustionsRepository.getQuestions("Q44b5", update);
        Questions Q44b6 = Common.qustionsRepository.getQuestions("Q44b6", update);
        Questions Q44b7 = Common.qustionsRepository.getQuestions("Q44b7", update);
        Questions Q44b8 = Common.qustionsRepository.getQuestions("Q44b8", update);
        Questions Q44b9 = Common.qustionsRepository.getQuestions("Q44b9", update);
        Questions Q44b10 = Common.qustionsRepository.getQuestions("Q44b10", update);
        Questions Q44b11 = Common.qustionsRepository.getQuestions("Q44b11", update);
        Questions Q44b12 = Common.qustionsRepository.getQuestions("Q44b12", update);
        Questions Q44b13 = Common.qustionsRepository.getQuestions("Q44b13", update);
        Questions Q44b14 = Common.qustionsRepository.getQuestions("Q44b14", update);
        Questions Q44b15 = Common.qustionsRepository.getQuestions("Q44b15", update);
        Questions Q44b16 = Common.qustionsRepository.getQuestions("Q44b16", update);
        Questions Q44b17 = Common.qustionsRepository.getQuestions("Q44b17", update);
        Questions Q44b18 = Common.qustionsRepository.getQuestions("Q44b18", update);
        Questions Q44b19 = Common.qustionsRepository.getQuestions("Q44b19", update);
        Questions Q44b20 = Common.qustionsRepository.getQuestions("Q44b20", update);
        Questions Q44b21 = Common.qustionsRepository.getQuestions("Q44b21", update);

        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int b4 = 0;
        int b5 = 0;
        int b6 = 0;
        int b7 = 0;
        int b8 = 0;
        int b9 = 0;
        int b10 = 0;
        int b11 = 0;
        int b12 = 0;
        int b13 = 0;
        int b14 = 0;
        int b15 = 0;
        int b16 = 0;
        int b17 = 0;
        int b18 = 0;
        int b19 = 0;
        int b20 = 0;
        int b21 = 0;
        if (Q44b1 != null) {
            try {
                b1 = Integer.parseInt(Q44b1.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b1 = 1;
            }
            if (b1 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_1.size(); i++) {
                    if (yesNoArrayListFor44_b_1.get(i).getId() == b1) {
                        spinner_typical_week_moderate_44_a1.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b2 != null) {
            try {
                b2 = Integer.parseInt(Q44b2.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b2 = 1;
            }
            if (b2 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_2.size(); i++) {
                    if (yesNoArrayListFor44_b_2.get(i).getId() == b2) {
                        spinner_typical_week_moderate_44_a2.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b3 != null) {
            try {
                b3 = Integer.parseInt(Q44b3.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b3 = 1;
            }
            if (b3 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_3.size(); i++) {
                    if (yesNoArrayListFor44_b_3.get(i).getId() == b3) {
                        spinner_typical_week_moderate_44_a3.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b4 != null) {
            try {
                b4 = Integer.parseInt(Q44b4.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b4 = 1;
            }
            if (b4 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_4.size(); i++) {
                    if (yesNoArrayListFor44_b_4.get(i).getId() == b4) {
                        spinner_typical_week_moderate_44_a4.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b5 != null) {
            try {
                b5 = Integer.parseInt(Q44b5.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b5= 1;
            }
            if (b5 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_5.size(); i++) {
                    if (yesNoArrayListFor44_b_5.get(i).getId() == b5) {
                        spinner_typical_week_moderate_44_a5.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b6 != null) {
            try {
                b6 = Integer.parseInt(Q44b6.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b6= 1;
            }
            if (b6 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_6.size(); i++) {
                    if (yesNoArrayListFor44_b_6.get(i).getId() == b6) {
                        spinner_typical_week_moderate_44_a6.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b7 != null) {
            try {
                b7 = Integer.parseInt(Q44b7.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b7= 1;
            }
            if (b7 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_7.size(); i++) {
                    if (yesNoArrayListFor44_b_7.get(i).getId() == b7) {
                        spinner_typical_week_moderate_44_a7.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b8 != null) {
            try {
                b8 = Integer.parseInt(Q44b8.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b8= 1;
            }
            if (b8 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_8.size(); i++) {
                    if (yesNoArrayListFor44_b_8.get(i).getId() == b8) {
                        spinner_typical_week_moderate_44_a8.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b9 != null) {
            try {
                b9 = Integer.parseInt(Q44b9.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b9= 1;
            }
            if (b9 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_9.size(); i++) {
                    if (yesNoArrayListFor44_b_9.get(i).getId() == b9) {
                        spinner_typical_week_moderate_44_a9.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b10 != null) {
            try {
                b10 = Integer.parseInt(Q44b10.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b10= 1;
            }
            if (b10 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_10.size(); i++) {
                    if (yesNoArrayListFor44_b_10.get(i).getId() == b10) {
                        spinner_typical_week_moderate_44_a10.setSelection(i);
                    }
                }
            }
        }

        //////////////////////////////
        if (Q44b11 != null) {
            try {
                b11 = Integer.parseInt(Q44b11.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b11= 1;
            }
            if (b11 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_11.size(); i++) {
                    if (yesNoArrayListFor44_b_11.get(i).getId() == b11) {
                        spinner_typical_week_moderate_44_a11.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b12 != null) {
            try {
                b12 = Integer.parseInt(Q44b12.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b12= 1;
            }
            if (b12 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_12.size(); i++) {
                    if (yesNoArrayListFor44_b_12.get(i).getId() == b12) {
                        spinner_typical_week_moderate_44_a12.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b13 != null) {
            try {
                b13 = Integer.parseInt(Q44b13.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b13= 1;
            }
            if (b13 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_13.size(); i++) {
                    if (yesNoArrayListFor44_b_13.get(i).getId() == b13) {
                        spinner_typical_week_moderate_44_a13.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b14 != null) {
            try {
                b14= Integer.parseInt(Q44b14.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b14= 1;
            }
            if (b14 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_14.size(); i++) {
                    if (yesNoArrayListFor44_b_14.get(i).getId() == b14) {
                        spinner_typical_week_moderate_44_a14.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b15 != null) {
            try {
                b15 = Integer.parseInt(Q44b15.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b15= 1;
            }
            if (b15 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_15.size(); i++) {
                    if (yesNoArrayListFor44_b_15.get(i).getId() == b15) {
                        spinner_typical_week_moderate_44_a15.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b16 != null) {
            try {
                b16 = Integer.parseInt(Q44b16.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b16= 1;
            }
            if (b16 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_16.size(); i++) {
                    if (yesNoArrayListFor44_b_16.get(i).getId() == b16) {
                        spinner_typical_week_moderate_44_a16.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b17 != null) {
            try {
                b17 = Integer.parseInt(Q44b17.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b17= 1;
            }
            if (b17 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_17.size(); i++) {
                    if (yesNoArrayListFor44_b_17.get(i).getId() == b17) {
                        spinner_typical_week_moderate_44_a17.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b18 != null) {
            try {
                b18 = Integer.parseInt(Q44b18.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b18= 1;
            }
            if (b18 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_18.size(); i++) {
                    if (yesNoArrayListFor44_b_18.get(i).getId() == b18) {
                        spinner_typical_week_moderate_44_a18.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b19 != null) {
            try {
                b19 = Integer.parseInt(Q44b19.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b19= 1;
            }
            if (b19 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_19.size(); i++) {
                    if (yesNoArrayListFor44_b_19.get(i).getId() == b19) {
                        spinner_typical_week_moderate_44_a19.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b20 != null) {
            try {
                b20 = Integer.parseInt(Q44b20.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b20= 1;
            }
            if (b20 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_20.size(); i++) {
                    if (yesNoArrayListFor44_b_20.get(i).getId() == b20) {
                        spinner_typical_week_moderate_44_a20.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q44b21 != null) {
            try {
                b21 = Integer.parseInt(Q44b21.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b21= 1;
            }
            if (b21 != 0) {
                for (int i = 0; i < yesNoArrayListFor44_b_21.size(); i++) {
                    if (yesNoArrayListFor44_b_21.get(i).getId() == b21) {
                        spinner_typical_week_moderate_44_a21.setSelection(i);
                    }
                }
            }
        }

        ////////////////////////////////////////////EditText//
        Questions Q44c1 = Common.qustionsRepository.getQuestions("Q44c1", update);
        Questions Q44c2 = Common.qustionsRepository.getQuestions("Q44c2", update);
        Questions Q44c3 = Common.qustionsRepository.getQuestions("Q44c3", update);
        Questions Q44c4 = Common.qustionsRepository.getQuestions("Q44c4", update);
        Questions Q44c5 = Common.qustionsRepository.getQuestions("Q44c5", update);
        Questions Q44c6 = Common.qustionsRepository.getQuestions("Q44c6", update);
        Questions Q44c7 = Common.qustionsRepository.getQuestions("Q44c7", update);
        Questions Q44c8 = Common.qustionsRepository.getQuestions("Q44c8", update);
        Questions Q44c9 = Common.qustionsRepository.getQuestions("Q44c9", update);
        Questions Q44c10 = Common.qustionsRepository.getQuestions("Q44c10", update);
        Questions Q44c11 = Common.qustionsRepository.getQuestions("Q44c11", update);
        Questions Q44c12 = Common.qustionsRepository.getQuestions("Q44c12", update);
        Questions Q44c13 = Common.qustionsRepository.getQuestions("Q44c13", update);
        Questions Q44c14 = Common.qustionsRepository.getQuestions("Q44c14", update);
        Questions Q44c15 = Common.qustionsRepository.getQuestions("Q44c15", update);
        Questions Q44c16 = Common.qustionsRepository.getQuestions("Q44c16", update);
        Questions Q44c17 = Common.qustionsRepository.getQuestions("Q44c17", update);
        Questions Q44c18 = Common.qustionsRepository.getQuestions("Q44c18", update);
        Questions Q44c19 = Common.qustionsRepository.getQuestions("Q44c19", update);
        Questions Q44c20 = Common.qustionsRepository.getQuestions("Q44c20", update);
        Questions Q44c21 = Common.qustionsRepository.getQuestions("Q44c21", update);

        if (Q44c1!=null){
            edit_typical_day_moderate_44_a1.setText(Q44c1.answer);
        }
        if (Q44c2!=null){
            edit_typical_day_moderate_44_a2.setText(Q44c2.answer);
        }
        if (Q44c3!=null){
            edit_typical_day_moderate_44_a3.setText(Q44c3.answer);
        }
        if (Q44c4!=null){
            edit_typical_day_moderate_44_a4.setText(Q44c4.answer);
        }
        if (Q44c5!=null){
            edit_typical_day_moderate_44_a5.setText(Q44c5.answer);
        }
/////////////////
        if (Q44c6!=null){
            edit_typical_day_moderate_44_a6.setText(Q44c6.answer);
        }
        if (Q44c7!=null){
            edit_typical_day_moderate_44_a7.setText(Q44c7.answer);
        }
        if (Q44c8!=null){
            edit_typical_day_moderate_44_a8.setText(Q44c8.answer);
        }
        if (Q44c9!=null){
            edit_typical_day_moderate_44_a9.setText(Q44c9.answer);
        }
        if (Q44c10!=null){
            edit_typical_day_moderate_44_a10.setText(Q44c10.answer);
        }

        /////////////////
        if (Q44c11!=null){
            edit_typical_day_moderate_44_a11.setText(Q44c11.answer);
        }
        if (Q44c12!=null){
            edit_typical_day_moderate_44_a12.setText(Q44c12.answer);
        }
        if (Q44c13!=null){
            edit_typical_day_moderate_44_a13.setText(Q44c13.answer);
        }
        if (Q44c14!=null){
            edit_typical_day_moderate_44_a14.setText(Q44c14.answer);
        }
        if (Q44c15!=null){
            edit_typical_day_moderate_44_a15.setText(Q44c15.answer);
        }
        if (Q44c16!=null){
            edit_typical_day_moderate_44_a16.setText(Q44c16.answer);
        }
        if (Q44c17!=null){
            edit_typical_day_moderate_44_a17.setText(Q44c17.answer);
        }
        if (Q44c18!=null){
            edit_typical_day_moderate_44_a18.setText(Q44c18.answer);
        }
        if (Q44c19!=null){
            edit_typical_day_moderate_44_a19.setText(Q44c19.answer);
        }
        if (Q44c20!=null){
            edit_typical_day_moderate_44_a20.setText(Q44c20.answer);
        }
        if (Q44c21!=null){
            edit_typical_day_moderate_44_a21.setText(Q44c21.answer);
        }
        ////////////////////////////////////////////EditText////
//        Questions questions3 = Common.qustionsRepository.getQuestions("Q44b", update);
//        Questions questions2 = Common.qustionsRepository.getQuestions("Q44a", update);

//
//        try {
//            YesNob = Integer.parseInt(questions3.answer);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            YesNob = -1;
//        } catch (Exception e) {
//            e.printStackTrace();
//            YesNob = -1;
//        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }

//        if (questions2 != null) {
//            String diac = questions2.answer;
//            String[] values = diac.split(",");
//            for (String s : values) {
//                Log.e("fdf", "dfdf" + s);
//                if (s.equals("1")) {
//                    checkBoxHouseHoldWork.setChecked(true);
//                } else if (s.equals("2")) {
//                    checkBoxGardening.setChecked(true);
//                } else if (s.equals("3")) {
//                    checkBoxMilkingCows.setChecked(true);
//                } else if (s.equals("4")) {
//                    checkBoxCultivatingLand.setChecked(true);
//                } else if (s.equals("5")) {
//                    checkBoxPlantingHarvest.setChecked(true);
//                } else if (s.equals("6")) {
//                    checkBoxWeavingCloth.setChecked(true);
//                } else if (s.equals("7")) {
//                    checkBoxWashingCloths.setChecked(true);
//                } else if (s.equals("8")) {
//                    checkBoxRearing.setChecked(true);
//                } else if (s.equals("9")) {
//                    checkBoxMixingCement.setChecked(true);
//                } else if (s.equals("10")) {
//                    checkBoxWoodWork.setChecked(true);
//                } else if (s.equals("11")) {
//                    checkBoxDrawingWater.setChecked(true);
//                } else if (s.equals("12")) {
//                    checkBoxCarryingLightWeight.setChecked(true);
//                } else if (s.equals("13")) {
//                    checkBoxWashingCloths1.setChecked(true);
//                } else if (s.equals("14")) {
//                    checkBoxGardening1.setChecked(true);
//                } else if (s.equals("15")) {
//                    checkBoxMilkingCows1.setChecked(true);
//                } else if (s.equals("16")) {
//                    checkBoxRoping.setChecked(true);
//                } else if (s.equals("17")) {
//                    checkBoxFarming.setChecked(true);
//                } else if (s.equals("18")) {
//                    checkBoxParlour.setChecked(true);
//                } else if (s.equals("19")) {
//                    checkBoxCloth.setChecked(true);
//                } else if (s.equals("20")) {
//                    checkBoxHouseHoldWork1.setChecked(true);
//                } else if (s.equals("21")) {
//                    checkBoxOthers1.setChecked(true);
//                }
//            }
//        }


//        if (YesNob != 0) {
//            for (int i = 0; i < yesNoArrayListForModerate.size(); i++) {
//                if (yesNoArrayListForModerate.get(i).getId() == YesNob) {
//                    spinner_typical_week_moderate.setSelection(i);
//                }
//            }
//        }
        if (YesNo != 0) {
            int div = YesNo;

//            if (div == 1) {
//                try {
//                    Questions questions1a = Common.qustionsRepository.getQuestions("Q44c", update);
//                    edit_typical_day_moderate.setText(questions1a.answer);
//                } catch (Exception e) {
//                    edit_typical_day_moderate.setText("");
//                }
//            }

            for (int i = 0; i < yesNoArrayListForModerateIntensityHeart.size(); i++) {
                if (yesNoArrayListForModerateIntensityHeart.get(i).getId() == div) {
                    spinner_heart_show_moderate_card.setSelection(i);
                }
            }
        }
    }

    private void initVigiriosIntensityRecreational() {
        int YesNo = 0;
        int YesNob = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q45", update);

        Questions Q45a1 = Common.qustionsRepository.getQuestions("Q45a1", update);
        Questions Q45a2 = Common.qustionsRepository.getQuestions("Q45a2", update);
        Questions Q45a3 = Common.qustionsRepository.getQuestions("Q45a3", update);
        Questions Q45a4 = Common.qustionsRepository.getQuestions("Q45a4", update);
        Questions Q45a5 = Common.qustionsRepository.getQuestions("Q45a5", update);
        Questions Q45a6 = Common.qustionsRepository.getQuestions("Q45a6", update);
        Questions Q45a7 = Common.qustionsRepository.getQuestions("Q45a7", update);
        Questions Q45a8 = Common.qustionsRepository.getQuestions("Q45a8", update);
        Questions Q45a9 = Common.qustionsRepository.getQuestions("Q45a9", update);

        if (Q45a1 != null) {
            checkBoxRunning.setChecked(true);
            linear_recreational_activities_typical_45_a1.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a1.setVisibility(View.VISIBLE);
        }
        if (Q45a2 != null) {
            checkBoxBadminton.setChecked(true);
            linear_recreational_activities_typical_45_a2.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a2.setVisibility(View.VISIBLE);
        }
        if (Q45a3 != null) {
            checkBoxSwimming.setChecked(true);
            linear_recreational_activities_typical_45_a3.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a3.setVisibility(View.VISIBLE);
        }
        if (Q45a4 != null) {
            checkBoxHockey.setChecked(true);
            linear_recreational_activities_typical_45_a4.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a4.setVisibility(View.VISIBLE);
        }
        if (Q45a5 != null) {
            checkBoxHadudu.setChecked(true);
            linear_recreational_activities_typical_45_a5.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a5.setVisibility(View.VISIBLE);
        }
        ////6///
        if (Q45a6 != null) {
            checkBoxFootbal.setChecked(true);
            linear_recreational_activities_typical_45_a6.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a6.setVisibility(View.VISIBLE);
        }
        if (Q45a7 != null) {
            checkBoxVolleyball.setChecked(true);
            linear_recreational_activities_typical_45_a7.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a7.setVisibility(View.VISIBLE);
        }
        if (Q45a8 != null) {
            checkBoxTenis.setChecked(true);
            linear_recreational_activities_typical_45_a8.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a8.setVisibility(View.VISIBLE);;
        }
        if (Q45a9 != null) {
            checkBoxOthers_recreational.setChecked(true);
            linear_recreational_activities_typical_45_a9.setVisibility(View.VISIBLE);
            linear_recreational_activities_typical_day_45_a9.setVisibility(View.VISIBLE);
        }
        ////////////////////////////////////////////Spineer


        Questions Q45b1 = Common.qustionsRepository.getQuestions("Q45b1", update);
        Questions Q45b2 = Common.qustionsRepository.getQuestions("Q45b2", update);
        Questions Q45b3 = Common.qustionsRepository.getQuestions("Q45b3", update);
        Questions Q45b4 = Common.qustionsRepository.getQuestions("Q45b4", update);
        Questions Q45b5 = Common.qustionsRepository.getQuestions("Q45b5", update);
        Questions Q45b6 = Common.qustionsRepository.getQuestions("Q45b6", update);
        Questions Q45b7 = Common.qustionsRepository.getQuestions("Q45b7", update);
        Questions Q45b8 = Common.qustionsRepository.getQuestions("Q45b8", update);
        Questions Q45b9 = Common.qustionsRepository.getQuestions("Q45b9", update);


        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int b4 = 0;
        int b5 = 0;
        int b6 = 0;
        int b7 = 0;
        int b8 = 0;
        int b9 = 0;

        if (Q45b1 != null) {
            try {
                b1 = Integer.parseInt(Q45b1.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b1 = 1;
            }
            if (b1 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_1.size(); i++) {
                    if (yesNoArrayListFor45_b_1.get(i).getId() == b1) {
                        spinner_typical_week_recreational_45_a1.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b2 != null) {
            try {
                b2 = Integer.parseInt(Q45b2.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b2 = 1;
            }
            if (b2 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_2.size(); i++) {
                    if (yesNoArrayListFor45_b_2.get(i).getId() == b2) {
                        spinner_typical_week_recreational_45_a2.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b3 != null) {
            try {
                b3 = Integer.parseInt(Q45b3.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b3 = 1;
            }
            if (b3 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_3.size(); i++) {
                    if (yesNoArrayListFor45_b_3.get(i).getId() == b3) {
                        spinner_typical_week_recreational_45_a3.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b4 != null) {
            try {
                b4 = Integer.parseInt(Q45b4.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b4 = 1;
            }
            if (b4 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_4.size(); i++) {
                    if (yesNoArrayListFor45_b_4.get(i).getId() == b4) {
                        spinner_typical_week_recreational_45_a4.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b5 != null) {
            try {
                b5 = Integer.parseInt(Q45b5.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b5= 1;
            }
            if (b5 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_5.size(); i++) {
                    if (yesNoArrayListFor45_b_5.get(i).getId() == b5) {
                        spinner_typical_week_recreational_45_a5.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b6 != null) {
            try {
                b6 = Integer.parseInt(Q45b6.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b6= 1;
            }
            if (b6 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_6.size(); i++) {
                    if (yesNoArrayListFor45_b_6.get(i).getId() == b6) {
                        spinner_typical_week_recreational_45_a6.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b7 != null) {
            try {
                b7 = Integer.parseInt(Q45b7.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b7= 1;
            }
            if (b7 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_7.size(); i++) {
                    if (yesNoArrayListFor45_b_7.get(i).getId() == b7) {
                        spinner_typical_week_recreational_45_a7.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b8 != null) {
            try {
                b8 = Integer.parseInt(Q45b8.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b8= 1;
            }
            if (b8 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_8.size(); i++) {
                    if (yesNoArrayListFor45_b_8.get(i).getId() == b8) {
                        spinner_typical_week_recreational_45_a8.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q45b9 != null) {
            try {
                b9 = Integer.parseInt(Q45b9.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b9= 1;
            }
            if (b9 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_9.size(); i++) {
                    if (yesNoArrayListFor45_b_9.get(i).getId() == b9) {
                        spinner_typical_week_recreational_45_a9.setSelection(i);
                    }
                }
            }
        }

        ////////////////////////////////////////////EditText//
        Questions Q45c1 = Common.qustionsRepository.getQuestions("Q45c1", update);
        Questions Q45c2 = Common.qustionsRepository.getQuestions("Q45c2", update);
        Questions Q45c3 = Common.qustionsRepository.getQuestions("Q45c3", update);
        Questions Q45c4 = Common.qustionsRepository.getQuestions("Q45c4", update);
        Questions Q45c5 = Common.qustionsRepository.getQuestions("Q45c5", update);
        Questions Q45c6 = Common.qustionsRepository.getQuestions("Q45c6", update);
        Questions Q45c7 = Common.qustionsRepository.getQuestions("Q45c7", update);
        Questions Q45c8 = Common.qustionsRepository.getQuestions("Q45c8", update);
        Questions Q45c9 = Common.qustionsRepository.getQuestions("Q45c9", update);


        if (Q45c1!=null){
            edit_typical_day_recreational_45_a1.setText(Q45c1.answer);
        }
        if (Q45c2!=null){
            edit_typical_day_recreational_45_a2.setText(Q45c2.answer);
        }
        if (Q45c3!=null){
            edit_typical_day_recreational_45_a3.setText(Q45c3.answer);
        }
        if (Q45c4!=null){
            edit_typical_day_recreational_45_a4.setText(Q45c4.answer);
        }
        if (Q45c5!=null){
            edit_typical_day_recreational_45_a5.setText(Q45c5.answer);
        }
/////////////////
        if (Q45c6!=null){
            edit_typical_day_recreational_45_a6.setText(Q45c6.answer);
        }
        if (Q45c7!=null){
            edit_typical_day_recreational_45_a7.setText(Q45c7.answer);
        }
        if (Q45c8!=null){
            edit_typical_day_recreational_45_a8.setText(Q45c8.answer);
        }
        if (Q45c9!=null){
            edit_typical_day_recreational_45_a9.setText(Q45c9.answer);
        }
        //////////////////////////////
//        Questions questions3 = Common.qustionsRepository.getQuestions("Q45b", update);
//        Questions questions2 = Common.qustionsRepository.getQuestions("Q45a", update);

//
//        try {
//            YesNob = Integer.parseInt(questions3.answer);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            YesNob = -1;
//        } catch (Exception e) {
//            e.printStackTrace();
//            YesNob = -1;
//        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
//        if (questions2 != null) {
//            String diac = questions2.answer;
//            String[] values = diac.split(",");
//            for (String s : values) {
//                Log.e("fdf", "dfdf" + s);
//                if (s.equals("1")) {
//                    checkBoxRunning.setChecked(true);
//                } else if (s.equals("2")) {
//                    checkBoxBadminton.setChecked(true);
//                } else if (s.equals("3")) {
//                    checkBoxSwimming.setChecked(true);
//                } else if (s.equals("4")) {
//                    checkBoxHockey.setChecked(true);
//                } else if (s.equals("5")) {
//                    checkBoxHadudu.setChecked(true);
//                } else if (s.equals("6")) {
//                    checkBoxFootbal.setChecked(true);
//                } else if (s.equals("7")) {
//                    checkBoxVolleyball.setChecked(true);
//                } else if (s.equals("8")) {
//                    checkBoxTenis.setChecked(true);
//                } else if (s.equals("9")) {
//                    checkBoxOthers_recreational.setChecked(true);
//                }
//            }
//        }


//        if (YesNob != 0) {
//            for (int i = 0; i < yesNoArrayListForTypicalVigorousRecreation.size(); i++) {
//                if (yesNoArrayListForTypicalVigorousRecreation.get(i).getId() == YesNob) {
//                    spinner_typical_week_recreational.setSelection(i);
//                }
//            }
//        }
        if (YesNo != 0) {
            int div = YesNo;

//            if (div == 1) {
//                try {
//                    Questions questions1a = Common.qustionsRepository.getQuestions("Q45c", update);
//                    edit_typical_day_recreational.setText(questions1a.answer);
//                } catch (Exception e) {
//                    edit_typical_day_recreational.setText("");
//                }
//            }

            for (int i = 0; i < yesNoArrayListForVigorousRecreationalHeart.size(); i++) {
                if (yesNoArrayListForVigorousRecreationalHeart.get(i).getId() == div) {
                    spinner_recreational_activities.setSelection(i);
                }
            }
        }
    }

    private void initModerateIntensityRecreational() {

        Questions questions1 = Common.qustionsRepository.getQuestions("Q46", update);
        Questions Q46a1 = Common.qustionsRepository.getQuestions("Q46a1", update);
        Questions Q46a2 = Common.qustionsRepository.getQuestions("Q46a2", update);
        Questions Q46a3 = Common.qustionsRepository.getQuestions("Q46a3", update);
        Questions Q46a4 = Common.qustionsRepository.getQuestions("Q46a4", update);
        Questions Q46a5 = Common.qustionsRepository.getQuestions("Q46a5", update);
        Questions Q46a6 = Common.qustionsRepository.getQuestions("Q46a6", update);
        Questions Q46a7 = Common.qustionsRepository.getQuestions("Q46a7", update);
        Questions Q46a8 = Common.qustionsRepository.getQuestions("Q46a8", update);
        Questions Q46a9 = Common.qustionsRepository.getQuestions("Q46a9", update);

        if (Q46a1 != null) {
            checkBoxFastWalking.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a1.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a1.setVisibility(View.VISIBLE);
        }
        if (Q46a2 != null) {
            checkBoxJogging.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a2.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a2.setVisibility(View.VISIBLE);
        }
        if (Q46a3 != null) {
            checkBoxCycling.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a3.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a3.setVisibility(View.VISIBLE);
        }
        if (Q46a4 != null) {
            checkBoxCricket.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a4.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a4.setVisibility(View.VISIBLE);
        }
        if (Q46a5 != null) {
            checkBoxYoga.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a5.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a5.setVisibility(View.VISIBLE);
        }
        ////6///
        if (Q46a6 != null) {
            checkBoxAerobics.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a6.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a6.setVisibility(View.VISIBLE);
        }
        if (Q46a7 != null) {
            checkBoxExercise.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a7.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a7.setVisibility(View.VISIBLE);
        }
        if (Q46a8 != null) {
            checkBoxOthersDancing.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a8.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a8.setVisibility(View.VISIBLE);
        }
        if (Q46a9 != null) {
            checkBoxOthers_moderate_recreational_others.setChecked(true);
            linear_moderate_intensity_recreational_activities_typical_46_a9.setVisibility(View.VISIBLE);
            linear_moderate_intensity_recreational_activities_typical_day_46_a9.setVisibility(View.VISIBLE);
        }
        ////////////////////////////////////////////Spineer


        Questions Q46b1 = Common.qustionsRepository.getQuestions("Q46b1", update);
        Questions Q46b2 = Common.qustionsRepository.getQuestions("Q46b2", update);
        Questions Q46b3 = Common.qustionsRepository.getQuestions("Q46b3", update);
        Questions Q46b4 = Common.qustionsRepository.getQuestions("Q46b4", update);
        Questions Q46b5 = Common.qustionsRepository.getQuestions("Q46b5", update);
        Questions Q46b6 = Common.qustionsRepository.getQuestions("Q46b6", update);
        Questions Q46b7 = Common.qustionsRepository.getQuestions("Q46b7", update);
        Questions Q46b8 = Common.qustionsRepository.getQuestions("Q46b8", update);
        Questions Q46b9 = Common.qustionsRepository.getQuestions("Q46b9", update);


        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int b4 = 0;
        int b5 = 0;
        int b6 = 0;
        int b7 = 0;
        int b8 = 0;
        int b9 = 0;

        if (Q46b1 != null) {
            try {
                b1 = Integer.parseInt(Q46b1.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b1 = 1;
            }
            if (b1 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_1.size(); i++) {
                    if (yesNoArrayListFor46_b_1.get(i).getId() == b1) {
                        spinner_typical_week_moderate_recreational_46_a1.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b2 != null) {
            try {
                b2 = Integer.parseInt(Q46b2.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b2 = 1;
            }
            if (b2 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_2.size(); i++) {
                    if (yesNoArrayListFor46_b_2.get(i).getId() == b2) {
                        spinner_typical_week_moderate_recreational_46_a2.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b3 != null) {
            try {
                b3 = Integer.parseInt(Q46b3.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b3 = 1;
            }
            if (b3 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_3.size(); i++) {
                    if (yesNoArrayListFor46_b_3.get(i).getId() == b3) {
                        spinner_typical_week_moderate_recreational_46_a3.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b4 != null) {
            try {
                b4 = Integer.parseInt(Q46b4.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b4 = 1;
            }
            if (b4 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_4.size(); i++) {
                    if (yesNoArrayListFor46_b_4.get(i).getId() == b4) {
                        spinner_typical_week_moderate_recreational_46_a4.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b5 != null) {
            try {
                b5 = Integer.parseInt(Q46b5.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b5= 1;
            }
            if (b5 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_5.size(); i++) {
                    if (yesNoArrayListFor46_b_5.get(i).getId() == b5) {
                        spinner_typical_week_moderate_recreational_46_a5.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b6 != null) {
            try {
                b6 = Integer.parseInt(Q46b6.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b6= 1;
            }
            if (b6 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_6.size(); i++) {
                    if (yesNoArrayListFor46_b_6.get(i).getId() == b6) {
                        spinner_typical_week_moderate_recreational_46_a6.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b7 != null) {
            try {
                b7 = Integer.parseInt(Q46b7.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b7= 1;
            }
            if (b7 != 0) {
                for (int i = 0; i < yesNoArrayListFor45_b_7.size(); i++) {
                    if (yesNoArrayListFor45_b_7.get(i).getId() == b7) {
                        spinner_typical_week_moderate_recreational_46_a7.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b8 != null) {
            try {
                b8 = Integer.parseInt(Q46b8.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b8= 1;
            }
            if (b8 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_8.size(); i++) {
                    if (yesNoArrayListFor46_b_8.get(i).getId() == b8) {
                        spinner_typical_week_moderate_recreational_46_a8.setSelection(i);
                    }
                }
            }
        }
        //////////////////////////////
        if (Q46b9 != null) {
            try {
                b9 = Integer.parseInt(Q46b9.answer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                b9= 1;
            }
            if (b9 != 0) {
                for (int i = 0; i < yesNoArrayListFor46_b_9.size(); i++) {
                    if (yesNoArrayListFor46_b_9.get(i).getId() == b9) {
                        spinner_typical_week_moderate_recreational_46_a9.setSelection(i);
                    }
                }
            }
        }

        ////////////////////////////////////////////EditText//
        Questions Q46c1 = Common.qustionsRepository.getQuestions("Q46c1", update);
        Questions Q46c2 = Common.qustionsRepository.getQuestions("Q46c2", update);
        Questions Q46c3 = Common.qustionsRepository.getQuestions("Q46c3", update);
        Questions Q46c4 = Common.qustionsRepository.getQuestions("Q46c4", update);
        Questions Q46c5 = Common.qustionsRepository.getQuestions("Q46c5", update);
        Questions Q46c6 = Common.qustionsRepository.getQuestions("Q46c6", update);
        Questions Q46c7 = Common.qustionsRepository.getQuestions("Q46c7", update);
        Questions Q46c8 = Common.qustionsRepository.getQuestions("Q46c8", update);
        Questions Q46c9 = Common.qustionsRepository.getQuestions("Q46c9", update);


        if (Q46c1!=null){
            edit_typical_day_moderate_recreational_46_a1.setText(Q46c1.answer);
        }
        if (Q46c2!=null){
            edit_typical_day_moderate_recreational_46_a2.setText(Q46c2.answer);
        }
        if (Q46c3!=null){
            edit_typical_day_moderate_recreational_46_a3.setText(Q46c3.answer);
        }
        if (Q46c4!=null){
            edit_typical_day_moderate_recreational_46_a4.setText(Q46c4.answer);
        }
        if (Q46c5!=null){
            edit_typical_day_moderate_recreational_46_a5.setText(Q46c5.answer);
        }
/////////////////
        if (Q46c6!=null){
            edit_typical_day_moderate_recreational_46_a6.setText(Q46c6.answer);
        }
        if (Q46c7!=null){
            edit_typical_day_moderate_recreational_46_a7.setText(Q46c7.answer);
        }
        if (Q46c8!=null){
            edit_typical_day_moderate_recreational_46_a8.setText(Q46c8.answer);
        }
        if (Q46c9!=null){
            edit_typical_day_moderate_recreational_46_a9.setText(Q46c9.answer);
        }
//        Questions questions3 = Common.qustionsRepository.getQuestions("Q46b", update);
//        Questions questions2 = Common.qustionsRepository.getQuestions("Q46a", update);


//        try {
//            YesNob = Integer.parseInt(questions3.answer);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            YesNob = -1;
//        } catch (Exception e) {
//            e.printStackTrace();
//            YesNob = -1;
//        }


//        if (questions2 != null) {
//            String diac = questions2.answer;
//            String[] values = diac.split(",");
//            for (String s : values) {
//                Log.e("fdf", "dfdf" + s);
//                if (s.equals("1")) {
//                    checkBoxFastWalking.setChecked(true);
//                } else if (s.equals("2")) {
//                    checkBoxJogging.setChecked(true);
//                } else if (s.equals("3")) {
//                    checkBoxCycling.setChecked(true);
//                } else if (s.equals("4")) {
//                    checkBoxCricket.setChecked(true);
//                } else if (s.equals("5")) {
//                    checkBoxYoga.setChecked(true);
//                } else if (s.equals("6")) {
//                    checkBoxAerobics.setChecked(true);
//                } else if (s.equals("7")) {
//                    checkBoxExercise.setChecked(true);
//                } else if (s.equals("8")) {
//                    checkBoxOthersDancing.setChecked(true);
//                } else if (s.equals("9")) {
//                    checkBoxOthers_moderate_recreational_others.setChecked(true);
//                }
//
//            }
//        }
//
//
//        if (YesNob != 0) {
//            for (int i = 0; i < yesNoArrayListForModerateRecreational.size(); i++) {
//                if (yesNoArrayListForModerateRecreational.get(i).getId() == YesNob) {
//                    spinner_typical_week_moderate_recreational.setSelection(i);
//                }
//            }
//        }
//        if (YesNo != 0) {
//            int div = YesNo;
//
////            if (div == 1) {
////                try {
////                    Questions questions1a = Common.qustionsRepository.getQuestions("Q46c", update);
////                    edit_typical_day_moderate_recreational.setText(questions1a.answer);
////                } catch (Exception e) {
////                    edit_typical_day_moderate_recreational.setText("");
////                }
////            }
//
//            for (int i = 0; i < yesNoArrayListForModerateRecreationalHeartt.size(); i++) {
//                if (yesNoArrayListForModerateRecreationalHeartt.get(i).getId() == div) {
//                    spinner_moderate_intensity_recreational_activities.setSelection(i);
//                }
//            }
//        }
    }

    private void initRecllining() {
        int YesNo = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q48", update);
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q48a", update);
                    edit_yes_reclining.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_yes_reclining.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForReclinig.size(); i++) {
                if (yesNoArrayListForReclinig.get(i).getId() == div) {
                    spinner_reclining.setSelection(i);
                }
            }
        }
    }

    private void initModerateIntensityRecreationClickListener() {

        checkBoxOthers_moderate_recreational_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers_moderate_recreational_others.isChecked()) {
                    checkBoxOthers_moderate_recreational_others.setChecked(false);
                } else {
                    checkBoxOthers_moderate_recreational_others.setChecked(true);
                }
                checkBox(checkBoxOthers_moderate_recreational_others);
            }
        });
        checkBoxFastWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFastWalking.isChecked()) {
                    checkBoxFastWalking.setChecked(false);
                } else {
                    checkBoxFastWalking.setChecked(true);
                }
                checkBox(checkBoxFastWalking);
            }
        });
        checkBoxJogging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxJogging.isChecked()) {
                    checkBoxJogging.setChecked(false);
                } else {
                    checkBoxJogging.setChecked(true);
                }
                checkBox(checkBoxJogging);
            }
        });
        checkBoxCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCycling.isChecked()) {
                    checkBoxCycling.setChecked(false);
                } else {
                    checkBoxCycling.setChecked(true);
                }
                checkBox(checkBoxCycling);
            }
        });
        checkBoxCricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCricket.isChecked()) {
                    checkBoxCricket.setChecked(false);
                } else {
                    checkBoxCricket.setChecked(true);
                }
                checkBox(checkBoxCricket);
            }
        });

        checkBoxYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxYoga.isChecked()) {
                    checkBoxYoga.setChecked(false);
                } else {
                    checkBoxYoga.setChecked(true);
                }
                checkBox(checkBoxYoga);
            }
        });
        checkBoxAerobics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxAerobics.isChecked()) {
                    checkBoxAerobics.setChecked(false);
                } else {
                    checkBoxAerobics.setChecked(true);
                }
                checkBox(checkBoxAerobics);
            }
        });
        checkBoxExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxExercise.isChecked()) {
                    checkBoxExercise.setChecked(false);
                } else {
                    checkBoxExercise.setChecked(true);
                }
                checkBox(checkBoxExercise);
            }
        });
        checkBoxOthersDancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthersDancing.isChecked()) {
                    checkBoxOthersDancing.setChecked(false);
                } else {
                    checkBoxOthersDancing.setChecked(true);
                }
                checkBox(checkBoxOthersDancing);
            }
        });
    }

    private void initVigiorosIntensityRecreationClickListener() {

        checkBoxFootbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFootbal.isChecked()) {
                    checkBoxFootbal.setChecked(false);
                } else {
                    checkBoxFootbal.setChecked(true);
                }
                checkBox(checkBoxFootbal);
            }
        });
        checkBoxRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxRunning.isChecked()) {
                    checkBoxRunning.setChecked(false);
                } else {
                    checkBoxRunning.setChecked(true);
                }
                checkBox(checkBoxRunning);
            }
        });
        checkBoxBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxBadminton.isChecked()) {
                    checkBoxBadminton.setChecked(false);
                } else {
                    checkBoxBadminton.setChecked(true);
                }
                checkBox(checkBoxBadminton);
            }
        });
        checkBoxSwimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSwimming.isChecked()) {
                    checkBoxSwimming.setChecked(false);
                } else {
                    checkBoxSwimming.setChecked(true);
                }
                checkBox(checkBoxSwimming);
            }
        });
        checkBoxHockey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHockey.isChecked()) {
                    checkBoxHockey.setChecked(false);
                } else {
                    checkBoxHockey.setChecked(true);
                }
                checkBox(checkBoxHockey);
            }
        });


        checkBoxHadudu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHadudu.isChecked()) {
                    checkBoxHadudu.setChecked(false);
                } else {
                    checkBoxHadudu.setChecked(true);
                }
                checkBox(checkBoxHadudu);
            }
        });
        checkBoxVolleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxVolleyball.isChecked()) {
                    checkBoxVolleyball.setChecked(false);
                } else {
                    checkBoxVolleyball.setChecked(true);
                }
                checkBox(checkBoxVolleyball);
            }
        });
        checkBoxTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxTenis.isChecked()) {
                    checkBoxTenis.setChecked(false);
                } else {
                    checkBoxTenis.setChecked(true);
                }
                checkBox(checkBoxTenis);
            }
        });
        checkBoxOthers_recreational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers_recreational.isChecked()) {
                    checkBoxOthers_recreational.setChecked(false);
                } else {
                    checkBoxOthers_recreational.setChecked(true);
                }
                checkBox(checkBoxOthers_recreational);
            }
        });
    }

    private void initModerateIntensityClickListener() {


        checkBoxHouseHoldWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHouseHoldWork.isChecked()) {
                    checkBoxHouseHoldWork.setChecked(false);
                } else {
                    checkBoxHouseHoldWork.setChecked(true);
                }
                checkBox(checkBoxHouseHoldWork);
            }
        });
        checkBoxGardening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxGardening.isChecked()) {
                    checkBoxGardening.setChecked(false);
                } else {
                    checkBoxGardening.setChecked(true);
                }
                checkBox(checkBoxGardening);
            }
        });
        checkBoxMilkingCows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMilkingCows.isChecked()) {
                    checkBoxMilkingCows.setChecked(false);
                } else {
                    checkBoxMilkingCows.setChecked(true);
                }
                checkBox(checkBoxMilkingCows);
            }
        });
        checkBoxCultivatingLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCultivatingLand.isChecked()) {
                    checkBoxCultivatingLand.setChecked(false);
                } else {
                    checkBoxCultivatingLand.setChecked(true);
                }
                checkBox(checkBoxCultivatingLand);
            }
        });
        checkBoxPlantingHarvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPlantingHarvest.isChecked()) {
                    checkBoxPlantingHarvest.setChecked(false);
                } else {
                    checkBoxPlantingHarvest.setChecked(true);
                }
                checkBox(checkBoxPlantingHarvest);
            }
        });
        checkBoxWeavingCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxWeavingCloth.isChecked()) {
                    checkBoxWeavingCloth.setChecked(false);
                } else {
                    checkBoxWeavingCloth.setChecked(true);
                }
                checkBox(checkBoxWeavingCloth);
            }
        });


        checkBoxWashingCloths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxWashingCloths.isChecked()) {
                    checkBoxWashingCloths.setChecked(false);
                } else {
                    checkBoxWashingCloths.setChecked(true);
                }
                checkBox(checkBoxWashingCloths);
            }
        });


        checkBoxRearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxRearing.isChecked()) {
                    checkBoxRearing.setChecked(false);
                } else {
                    checkBoxRearing.setChecked(true);
                }
                checkBox(checkBoxRearing);
            }
        });

        checkBoxMixingCement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMixingCement.isChecked()) {
                    checkBoxMixingCement.setChecked(false);
                } else {
                    checkBoxMixingCement.setChecked(true);
                }
                checkBox(checkBoxMixingCement);
            }
        });

        checkBoxWoodWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxWoodWork.isChecked()) {
                    checkBoxWoodWork.setChecked(false);
                } else {
                    checkBoxWoodWork.setChecked(true);
                }
                checkBox(checkBoxWoodWork);
            }
        });

        checkBoxDrawingWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxDrawingWater.isChecked()) {
                    checkBoxDrawingWater.setChecked(false);
                } else {
                    checkBoxDrawingWater.setChecked(true);
                }
                checkBox(checkBoxDrawingWater);
            }
        });

        checkBoxCarryingLightWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCarryingLightWeight.isChecked()) {
                    checkBoxCarryingLightWeight.setChecked(false);
                } else {
                    checkBoxCarryingLightWeight.setChecked(true);
                }
                checkBox(checkBoxCarryingLightWeight);
            }
        });

        checkBoxWashingCloths1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxWashingCloths1.isChecked()) {
                    checkBoxWashingCloths1.setChecked(false);
                } else {
                    checkBoxWashingCloths1.setChecked(true);
                }
                checkBox(checkBoxWashingCloths1);
            }
        });

        checkBoxGardening1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxGardening1.isChecked()) {
                    checkBoxGardening1.setChecked(false);
                } else {
                    checkBoxGardening1.setChecked(true);
                }
                checkBox(checkBoxGardening1);
            }
        });


        checkBoxMilkingCows1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMilkingCows1.isChecked()) {
                    checkBoxMilkingCows1.setChecked(false);
                } else {
                    checkBoxMilkingCows1.setChecked(true);
                }
                checkBox(checkBoxMilkingCows1);
            }
        });
        checkBoxMilkingCows1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMilkingCows1.isChecked()) {
                    checkBoxMilkingCows1.setChecked(false);
                } else {
                    checkBoxMilkingCows1.setChecked(true);
                }
                checkBox(checkBoxMilkingCows1);
            }
        });
        checkBoxRoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxRoping.isChecked()) {
                    checkBoxRoping.setChecked(false);
                } else {
                    checkBoxRoping.setChecked(true);
                }
                checkBox(checkBoxRoping);
            }
        });
        checkBoxFarming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFarming.isChecked()) {
                    checkBoxFarming.setChecked(false);
                } else {
                    checkBoxFarming.setChecked(true);
                }
                checkBox(checkBoxFarming);
            }
        });
        checkBoxParlour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxParlour.isChecked()) {
                    checkBoxParlour.setChecked(false);
                } else {
                    checkBoxParlour.setChecked(true);
                }
                checkBox(checkBoxParlour);
            }
        });
        checkBoxCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCloth.isChecked()) {
                    checkBoxCloth.setChecked(false);
                } else {
                    checkBoxCloth.setChecked(true);
                }
                checkBox(checkBoxCloth);
            }
        });
        checkBoxHouseHoldWork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHouseHoldWork1.isChecked()) {
                    checkBoxHouseHoldWork1.setChecked(false);
                } else {
                    checkBoxHouseHoldWork1.setChecked(true);
                }
                checkBox(checkBoxHouseHoldWork1);
            }
        });
        checkBoxOthers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers1.isChecked()) {
                    checkBoxOthers1.setChecked(false);
                } else {
                    checkBoxOthers1.setChecked(true);
                }
                checkBox(checkBoxOthers1);
            }
        });

    }

    private void initVigiorosIntensityClickListener() {


        checkBoxHeaveyLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHeaveyLoad.isChecked()) {
                    checkBoxHeaveyLoad.setChecked(false);
                } else {
                    checkBoxHeaveyLoad.setChecked(true);
                }
                checkBox(checkBoxHeaveyLoad);
            }
        });
        checkBoxDigging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxDigging.isChecked()) {
                    checkBoxDigging.setChecked(false);
                } else {
                    checkBoxDigging.setChecked(true);
                }
                checkBox(checkBoxDigging);
            }
        });
        checkBoxFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFurniture.isChecked()) {
                    checkBoxFurniture.setChecked(false);
                } else {
                    checkBoxFurniture.setChecked(true);
                }
                checkBox(checkBoxFurniture);
            }
        });
        checkBoxPickingCrops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPickingCrops.isChecked()) {
                    checkBoxPickingCrops.setChecked(false);
                } else {
                    checkBoxPickingCrops.setChecked(true);
                }
                checkBox(checkBoxPickingCrops);
            }
        });
        checkBoxCuttingTrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCuttingTrees.isChecked()) {
                    checkBoxCuttingTrees.setChecked(false);
                } else {
                    checkBoxCuttingTrees.setChecked(true);
                }
                checkBox(checkBoxCuttingTrees);
            }
        });
        checkBoxBreakUpPaddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxBreakUpPaddy.isChecked()) {
                    checkBoxBreakUpPaddy.setChecked(false);
                } else {
                    checkBoxBreakUpPaddy.setChecked(true);
                }
                checkBox(checkBoxBreakUpPaddy);
            }
        });


        checkBoxDrivingRickshaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxDrivingRickshaw.isChecked()) {
                    checkBoxDrivingRickshaw.setChecked(false);
                } else {
                    checkBoxDrivingRickshaw.setChecked(true);
                }
                checkBox(checkBoxDrivingRickshaw);
            }
        });


        checkBoxFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFishing.isChecked()) {
                    checkBoxFishing.setChecked(false);
                } else {
                    checkBoxFishing.setChecked(true);
                }
                checkBox(checkBoxFishing);
            }
        });

        checkBoxPlouging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPlouging.isChecked()) {
                    checkBoxPlouging.setChecked(false);
                } else {
                    checkBoxPlouging.setChecked(true);
                }
                checkBox(checkBoxPlouging);
            }
        });

        checkBoxHeaveyConstructionWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHeaveyConstructionWork.isChecked()) {
                    checkBoxHeaveyConstructionWork.setChecked(false);
                } else {
                    checkBoxHeaveyConstructionWork.setChecked(true);
                }
                checkBox(checkBoxHeaveyConstructionWork);
            }
        });

        checkBoxHeaveyGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHeaveyGoods.isChecked()) {
                    checkBoxHeaveyGoods.setChecked(false);
                } else {
                    checkBoxHeaveyGoods.setChecked(true);
                }
                checkBox(checkBoxHeaveyGoods);
            }
        });

        checkBoxHeaveyGoodsHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHeaveyGoodsHead.isChecked()) {
                    checkBoxHeaveyGoodsHead.setChecked(false);
                } else {
                    checkBoxHeaveyGoodsHead.setChecked(true);
                }
                checkBox(checkBoxHeaveyGoodsHead);
            }
        });

        checkBoxSoldDigging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSoldDigging.isChecked()) {
                    checkBoxSoldDigging.setChecked(false);
                } else {
                    checkBoxSoldDigging.setChecked(true);
                }
                checkBox(checkBoxSoldDigging);
            }
        });

        checkBoxWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxWashing.isChecked()) {
                    checkBoxWashing.setChecked(false);
                } else {
                    checkBoxWashing.setChecked(true);
                }
                checkBox(checkBoxWashing);
            }
        });


        checkBoxStepping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxStepping.isChecked()) {
                    checkBoxStepping.setChecked(false);
                } else {
                    checkBoxStepping.setChecked(true);
                }
                checkBox(checkBoxStepping);
            }
        });
        checkBoxOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers.isChecked()) {
                    checkBoxOthers.setChecked(false);
                } else {
                    checkBoxOthers.setChecked(true);
                }
                checkBox(checkBoxOthers);
            }
        });

    }

    private void initDiabetisSpinner() {
        yesNoArrayAdapterForSmoke = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForSmoke);
        yesNoArrayAdapterForSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_smoke.setAdapter(yesNoArrayAdapterForSmoke);

        spinner_smoke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForSmoke.get(position).getId());
                smokeYesNo = yesNoArrayListForSmoke.get(position).getId();
                if (yesNoArrayListForSmoke.get(position).getId() == 1) {
                    linear_smoke.setVisibility(View.VISIBLE);
                    linear_smoke_per_day.setVisibility(View.VISIBLE);

                } else {
                    linear_smoke.setVisibility(View.GONE);
                    linear_smoke_per_day.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initJordaSpinner() {
        yesNoArrayAdapterJorda = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForJorda);
        yesNoArrayAdapterJorda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_jorda.setAdapter(yesNoArrayAdapterJorda);

        spinner_jorda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForJorda.get(position).getId());
                jordaYesNo = yesNoArrayListForJorda.get(position).getId();
                if (yesNoArrayListForJorda.get(position).getId() == 1) {
                    lineat_jorda.setVisibility(View.VISIBLE);


                } else {
                    lineat_jorda.setVisibility(View.GONE);
                    edit_jorda.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initWorkstationSpinner() {
        yesNoArrayAdapterWorkplace = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForWorkplace);
        yesNoArrayAdapterWorkplace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_workplace.setAdapter(yesNoArrayAdapterWorkplace);

        spinner_workplace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForWorkplace.get(position).getId());
                workplaceYesNo = yesNoArrayListForWorkplace.get(position).getId();
                if (yesNoArrayListForWorkplace.get(position).getId() == 1) {
                    linear_workplace.setVisibility(View.VISIBLE);


                } else {
                    linear_workplace.setVisibility(View.GONE);
                    edit_workplace.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initAlcoholSpinner() {
        yesNoArrayAdapterAlcohol = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForAlcohol);
        yesNoArrayAdapterAlcohol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_alcohol.setAdapter(yesNoArrayAdapterAlcohol);

        spinner_alcohol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForAlcohol.get(position).getId());
                alcoholYesNo = yesNoArrayListForAlcohol.get(position).getId();
                if (yesNoArrayListForAlcohol.get(position).getId() == 1) {
                    linear_alcohol.setVisibility(View.VISIBLE);


                } else {
                    linear_alcohol.setVisibility(View.GONE);
                    edit_alcohol.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initFruitsSpinner() {
        yesNoArrayAdapterFruits = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForFruits);
        yesNoArrayAdapterFruits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fruits.setAdapter(yesNoArrayAdapterFruits);

        spinner_fruits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForFruits.get(position).getId());
                typicalFruits = yesNoArrayListForFruits.get(position).getId();
//                if (yesNoArrayListForFruits.get(position).getId() == 1) {
//                    linear_f.setVisibility(View.VISIBLE);
//
//
//                } else {
//                    linear_alcohol.setVisibility(View.GONE);
//                    edit_alcohol.setText("");
//
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initFruitsCardSpinner() {
        yesNoArrayAdapterFruitsCard = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForFruitsCard);
        yesNoArrayAdapterFruitsCard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fruits_card.setAdapter(yesNoArrayAdapterFruitsCard);

        spinner_fruits_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForFruitsCard.get(position).getId());
                fruitsShowCard = yesNoArrayListForFruitsCard.get(position).getId();
                linear_plus.setVisibility(View.GONE);
                checkbox_plus.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initVegetablesSpinner() {
        yesNoArrayAdapterVegetables = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVegetables);
        yesNoArrayAdapterVegetables.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vegetables.setAdapter(yesNoArrayAdapterVegetables);

        spinner_vegetables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVegetables.get(position).getId());
                typicalVegetables = yesNoArrayListForVegetables.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initVegetablesCardSpinner() {
        yesNoArrayAdapterVegetablesCard = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVegetablesCard);
        yesNoArrayAdapterVegetablesCard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vegetables_card.setAdapter(yesNoArrayAdapterVegetablesCard);

        spinner_vegetables_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVegetablesCard.get(position).getId());
                vegetablesShowCard = yesNoArrayListForVegetablesCard.get(position).getId();
                linear_plus.setVisibility(View.GONE);
                checkbox_plus.setChecked(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initBuySaltSpinner() {
        yesNoArrayAdapterSaltBuy = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForSaltBuy);
        yesNoArrayAdapterSaltBuy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_salt_month.setAdapter(yesNoArrayAdapterSaltBuy);

        spinner_salt_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForSaltBuy.get(position).getId());
                saltyBuy = yesNoArrayListForSaltBuy.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initTalkingSaltSpinner() {
        yesNoArrayAdapterTakingSalt = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTakingSalt);
        yesNoArrayAdapterTakingSalt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_salt_meal.setAdapter(yesNoArrayAdapterTakingSalt);

        spinner_salt_meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTakingSalt.get(position).getId());
                takingSalt = yesNoArrayListForTakingSalt.get(position).getId();

                if (yesNoArrayListForTakingSalt.get(position).getId() == 1) {
                    linear_taking_meal.setVisibility(View.VISIBLE);
                } else {
                    linear_taking_meal.setVisibility(View.GONE);

                    edit_yes_extra_salt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initHeartShowCardSpinner() {
        yesNoArrayAdapterVigorousIntensityHeart = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVigorousIntensityHeart);
        yesNoArrayAdapterVigorousIntensityHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heart_show_card.setAdapter(yesNoArrayAdapterVigorousIntensityHeart);

        spinner_heart_show_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVigorousIntensityHeart.get(position).getId());
                vigorousIntensity = yesNoArrayListForVigorousIntensityHeart.get(position).getId();

                if (yesNoArrayListForTakingSalt.get(position).getId() == 1) {
                    linear_heart_show_card.setVisibility(View.VISIBLE);
                    linear_heart_show_card_typical_week.setVisibility(View.GONE);
                    linear_heart_show_card_activities.setVisibility(View.GONE);
                } else {
                    linear_heart_show_card.setVisibility(View.GONE);
                    linear_heart_show_card_typical_week.setVisibility(View.GONE);
                    linear_heart_show_card_activities.setVisibility(View.GONE);

                    edit_typical_day.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initHeartShowModerateCardSpinner() {
        yesNoArrayAdapterModerateIntensityHeart = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateIntensityHeart);
        yesNoArrayAdapterModerateIntensityHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heart_show_moderate_card.setAdapter(yesNoArrayAdapterModerateIntensityHeart);

        spinner_heart_show_moderate_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateIntensityHeart.get(position).getId());
                moderateIntensity = yesNoArrayListForModerateIntensityHeart.get(position).getId();

                if (yesNoArrayListForModerateIntensityHeart.get(position).getId() == 1) {
                    linear_heart_show_moderate_card.setVisibility(View.VISIBLE);
                    linear_heart_show_moderate_card_typical.setVisibility(View.GONE);
                    linear_heart_show_card_day.setVisibility(View.GONE);
                } else {
                    linear_heart_show_moderate_card.setVisibility(View.GONE);
                    linear_heart_show_moderate_card_typical.setVisibility(View.GONE);
                    linear_heart_show_card_day.setVisibility(View.GONE);

                    edit_typical_day_moderate.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initRecreationalActivitiesSpinner() {
        yesNoArrayAdapterVigorousRecreationalHeart = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVigorousRecreationalHeart);
        yesNoArrayAdapterVigorousRecreationalHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_recreational_activities.setAdapter(yesNoArrayAdapterVigorousRecreationalHeart);

        spinner_recreational_activities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVigorousRecreationalHeart.get(position).getId());
                vigorousIntensityRecreational = yesNoArrayListForVigorousRecreationalHeart.get(position).getId();
                if (yesNoArrayListForVigorousRecreationalHeart.get(position).getId() == 1) {
                    linear_recreational_activities.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day.setVisibility(View.GONE);
                } else {
                    linear_recreational_activities.setVisibility(View.GONE);
                    linear_recreational_activities_typical.setVisibility(View.GONE);
                    linear_recreational_activities_typical_day.setVisibility(View.GONE);

                    edit_typical_day_recreational.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initModerateRecreationalActivitiesSpinner() {
        yesNoArrayAdapterModerateRecreationalHeart = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateRecreationalHeartt);
        yesNoArrayAdapterModerateRecreationalHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_moderate_intensity_recreational_activities.setAdapter(yesNoArrayAdapterModerateRecreationalHeart);

        spinner_moderate_intensity_recreational_activities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateRecreationalHeartt.get(position).getId());
                moderateIntensityRecreational = yesNoArrayListForModerateRecreationalHeartt.get(position).getId();
                if (yesNoArrayListForModerateRecreationalHeartt.get(position).getId() == 1) {
                    linear_moderate_intensity_recreational_activities.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day.setVisibility(View.GONE);
                } else {
                    linear_moderate_intensity_recreational_activities.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical.setVisibility(View.GONE);
                    linear_moderate_intensity_recreational_activities_typical_day.setVisibility(View.GONE);

                    edit_typical_day_moderate_recreational.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initRecilingSpinner() {
        yesNoArrayAdapterReclinig = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForReclinig);
        yesNoArrayAdapterReclinig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reclining.setAdapter(yesNoArrayAdapterReclinig);

        spinner_reclining.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForReclinig.get(position).getId());
                recliningActivities = yesNoArrayListForReclinig.get(position).getId();
                if (yesNoArrayListForReclinig.get(position).getId() == 1) {
                    linear_reclining.setVisibility(View.VISIBLE);

                } else {
                    linear_reclining.setVisibility(View.GONE);
                    edit_yes_reclining.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initVigiriousTypicalSpinner() {
        yesNoArrayAdapterTypicalVigorous = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTypicalVigorous);
        yesNoArrayAdapterTypicalVigorous.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week.setAdapter(yesNoArrayAdapterTypicalVigorous);

        spinner_typical_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTypicalVigorous.get(position).getId());

                vigorousIntensityTypical = yesNoArrayListForTypicalVigorous.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void initModerateTypicalSpinner() {
        yesNoArrayAdapterModerate = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerate);
        yesNoArrayAdapterModerate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate.setAdapter(yesNoArrayAdapterModerate);

        spinner_typical_week_moderate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerate.get(position).getId());
                moderateIntensityTypical = yesNoArrayListForModerate.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initVigriousRecreationalTypicalSpinner() {
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTypicalVigorousRecreation);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTypicalVigorousRecreation.get(position).getId());
                vigorousIntensityRecreationalTypical = yesNoArrayListForTypicalVigorousRecreation.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initModerateRecreationalTypicalSpinner() {
        yesNoArrayAdapterModerateRecreational = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateRecreational);
        yesNoArrayAdapterModerateRecreational.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational.setAdapter(yesNoArrayAdapterModerateRecreational);

        spinner_typical_week_moderate_recreational.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateRecreational.get(position).getId());
                moderateIntensityRecreationalTypical = yesNoArrayListForModerateRecreational.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean isChecked() {
//        if (Utils.isEmpty(etLocationDescriptio.getText().toString())) {
//            ((NewGroupActivty) mActivity).showTitleAlertDialog(getResources().getString(R.string.err_title), mActivity.getResources().getString(R.string.please_enter) + " Location Description", null);
//            return false;
//        }


        return true;
    }

    private String vigiriousIntensity() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isVigiriousIntensityChecked(i)) {
                if (isNullOrEmpty(ageList))
                    ageList = String.valueOf((i));
                else
                    ageList = ageList + "," + (i) + "";
            }
        }

        if (ageList.length() > 0) {
            return ageList;

        } else {
            return "";
        }


    }

    private String vigorousIntensityRecreational() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isVigorousIntensityRecreationalChecked(i)) {
                if (isNullOrEmpty(ageList))
                    ageList = String.valueOf((i));
                else
                    ageList = ageList + "," + (i) + "";
            }
        }

        if (ageList.length() > 0) {
            return ageList;

        } else {
            return "";
        }


    }

    private String moderateIntensityRecreational() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isModerateIntensityRecreationalChecked(i)) {
                if (isNullOrEmpty(ageList))
                    ageList = String.valueOf((i));
                else
                    ageList = ageList + "," + (i) + "";
            }
        }

        if (ageList.length() > 0) {
            return ageList;

        } else {
            return "";
        }


    }

    private String moderateIntensity() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isModerateIntensityChecked(i)) {
                if (isNullOrEmpty(ageList))
                    ageList = String.valueOf((i));
                else
                    ageList = ageList + "," + (i) + "";
            }
        }

        if (ageList.length() > 0) {
            return ageList;

        } else {
            return "";
        }


    }

    private boolean isVigiriousIntensityChecked(int pos) {
        if (pos == 1 && checkBoxHeaveyLoad.isChecked())
            return true;
        if (pos == 2 && checkBoxDigging.isChecked())
            return true;
        if (pos == 3 && checkBoxFurniture.isChecked())
            return true;
        if (pos == 4 && checkBoxPickingCrops.isChecked())
            return true;
        if (pos == 5 && checkBoxCuttingTrees.isChecked())
            return true;
        if (pos == 6 && checkBoxBreakUpPaddy.isChecked())
            return true;
        if (pos == 7 && checkBoxDrivingRickshaw.isChecked())
            return true;
        if (pos == 8 && checkBoxFishing.isChecked())
            return true;
        if (pos == 9 && checkBoxPlouging.isChecked())
            return true;
        if (pos == 10 && checkBoxHeaveyConstructionWork.isChecked())
            return true;
        if (pos == 11 && checkBoxHeaveyGoods.isChecked())
            return true;
        if (pos == 12 && checkBoxHeaveyGoodsHead.isChecked())
            return true;
        if (pos == 13 && checkBoxSoldDigging.isChecked())
            return true;
        if (pos == 14 && checkBoxWashing.isChecked())
            return true;
        if (pos == 15 && checkBoxStepping.isChecked())
            return true;
        if (pos == 16 && checkBoxOthers.isChecked())
            return true;


        return false;
    }


    private boolean isModerateIntensityRecreationalChecked(int pos) {

        if (pos == 1 && checkBoxFastWalking.isChecked())
            return true;
        if (pos == 2 && checkBoxJogging.isChecked())
            return true;
        if (pos == 3 && checkBoxCycling.isChecked())
            return true;
        if (pos == 4 && checkBoxCricket.isChecked())
            return true;
        if (pos == 5 && checkBoxYoga.isChecked())
            return true;
        if (pos == 6 && checkBoxAerobics.isChecked())
            return true;
        if (pos == 7 && checkBoxExercise.isChecked())
            return true;
        if (pos == 8 && checkBoxOthersDancing.isChecked())
            return true;
        if (pos == 9 && checkBoxOthers_moderate_recreational_others.isChecked())
            return true;
        return false;
    }

    private boolean isVigorousIntensityRecreationalChecked(int pos) {

        if (pos == 1 && checkBoxRunning.isChecked())
            return true;
        if (pos == 2 && checkBoxBadminton.isChecked())
            return true;
        if (pos == 3 && checkBoxSwimming.isChecked())
            return true;
        if (pos == 4 && checkBoxHockey.isChecked())
            return true;
        if (pos == 5 && checkBoxHadudu.isChecked())
            return true;
        if (pos == 6 && checkBoxFootbal.isChecked())
            return true;
        if (pos == 7 && checkBoxVolleyball.isChecked())
            return true;
        if (pos == 8 && checkBoxTenis.isChecked())
            return true;
        if (pos == 9 && checkBoxOthers_recreational.isChecked())
            return true;
        return false;
    }

    private boolean
    isModerateIntensityChecked(int pos) {


        if (pos == 1 && checkBoxHouseHoldWork.isChecked())
            return true;
        if (pos == 2 && checkBoxGardening.isChecked())
            return true;
        if (pos == 3 && checkBoxMilkingCows.isChecked())
            return true;
        if (pos == 4 && checkBoxCultivatingLand.isChecked())
            return true;
        if (pos == 5 && checkBoxPlantingHarvest.isChecked())
            return true;
        if (pos == 6 && checkBoxWeavingCloth.isChecked())
            return true;
        if (pos == 7 && checkBoxWashingCloths.isChecked())
            return true;
        if (pos == 8 && checkBoxRearing.isChecked())
            return true;
        if (pos == 9 && checkBoxMixingCement.isChecked())
            return true;
        if (pos == 10 && checkBoxWoodWork.isChecked())
            return true;
        if (pos == 11 && checkBoxDrawingWater.isChecked())
            return true;
        if (pos == 12 && checkBoxCarryingLightWeight.isChecked())
            return true;
        if (pos == 13 && checkBoxWashingCloths1.isChecked())
            return true;
        if (pos == 14 && checkBoxGardening1.isChecked())
            return true;
        if (pos == 15 && checkBoxMilkingCows1.isChecked())
            return true;
        if (pos == 16 && checkBoxRoping.isChecked())
            return true;
        if (pos == 17 && checkBoxFarming.isChecked())
            return true;
        if (pos == 18 && checkBoxParlour.isChecked())
            return true;
        if (pos == 19 && checkBoxCloth.isChecked())
            return true;
        if (pos == 20 && checkBoxHouseHoldWork1.isChecked())
            return true;
        if (pos == 21 && checkBoxOthers1.isChecked())
            return true;

        return false;
    }

    private void checkBox(CheckBox checkBox) {
        {
            if (!checkBox.isChecked())
                checkBox.setChecked(true);
            else if (checkBox.isChecked())
                checkBox.setChecked(false);
        }

    }

    private void saveData() {

        if (isChecked()) {
            MemberHabit memberHabitsFor = Common.memberHabitRepository.getMemberHabit(update);
            MemberHabit memberHabit = new MemberHabit();
            if (memberHabitsFor != null)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(date);
                if (checkBoxHeaveyLoad.isChecked())
                {
                    Log.e("evan","evan");
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a1", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b1", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb1);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb1);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c1", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a1.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxDigging.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a2", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b2", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb2);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb2);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c2", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a2.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxFurniture.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a3", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b3", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb3);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb3);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c3", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a3.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxPickingCrops.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a4", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b4", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb4);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb4);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c4", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a4.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxCuttingTrees.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a5", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b5", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb5);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb5);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c5", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a5.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxBreakUpPaddy.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a6", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b6", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb6);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb6);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c6", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a6.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxDrivingRickshaw.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a7", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b7", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb7);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb7);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c7", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a7.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxFishing.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a8", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b8", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb8);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb8);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c8", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a8.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                if (checkBoxPlouging.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a9", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b9", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb9);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb9);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c9", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a9.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHeaveyConstructionWork.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a10", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "10";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "10";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b10", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb10);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb10);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c10", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a10.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a10.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHeaveyGoods.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a11", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "11";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "11";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b11", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb11);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb11);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c11", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a11.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a11.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHeaveyGoodsHead.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a12", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "12";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "12";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b12", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb12);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb12);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c12", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a12.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a12.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxSoldDigging.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a13", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "13";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "13";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b13", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb13);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb13);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c13", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a13.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a13.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxWashing.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a14", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "14";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "14";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b14", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb14);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb14);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c14", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a14.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a14.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxStepping.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q43a15", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "15";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "15";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b15", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb1);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb15);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c15", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a15.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a15.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxOthers.isChecked()) {
                    Questions questionsForLoad1 = Common.qustionsRepository.getQuestions("Q43a16", update);
                    if (questionsForLoad1 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "16";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43a16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "16";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad1.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q43b16", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb16);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43b16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigirousIntensityb16);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }


                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q43c16", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a16.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q43c16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_43_a16.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                ///////////////EVAN KHAN///////////////////////////////////

                if (checkBoxHouseHoldWork.isChecked())
                {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a1", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b1", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb1);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb1);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c1", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a1.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                /////////


                if (checkBoxGardening.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a2", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b2", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb2);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb2);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c2", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a2.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                ///////
                if (checkBoxMilkingCows.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a3", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b3", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb3);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb3);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c3", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a3.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                ////
                if (checkBoxCultivatingLand.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a4", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b4", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb4);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb4);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c4", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a4.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////
                if (checkBoxPlantingHarvest.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a5", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b5", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb5);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb5);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c5", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a5.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }


                ////////6/////////

                //////
                if (checkBoxWeavingCloth.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a6", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b6", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb6);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb6);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c6", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a6.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////
                if (checkBoxWashingCloths.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a7", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b7", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb7);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb7);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c7", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a7.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////
                if (checkBoxRearing.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a8", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b8", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb8);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb8);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c8", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a8.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////
                if (checkBoxMixingCement.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a9", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b9", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb9);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb9);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c9", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a9.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////
                if (checkBoxWoodWork.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a10", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "10";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "10";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b10", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb10);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb10);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c10", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a10.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c10";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a10.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////////11///////

                if (checkBoxDrawingWater.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a11", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "11";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "11";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b11", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb11);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb11);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c11", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a11.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c11";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a11.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxCarryingLightWeight.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a12", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "12";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "12";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b12", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb12);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb12);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c12", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a12.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c12";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a12.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxWashingCloths1.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a13", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "13";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "13";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b13", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb13);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb13);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c13", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a13.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c13";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a13.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxGardening1.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a14", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "14";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "14";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b14", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb14);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb14);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c14", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a14.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c14";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a14.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxMilkingCows1.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a15", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "15";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "15";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b15", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb15);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb15);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c15", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a15.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c15";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a15.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                ////////16/////
                if (checkBoxRoping.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a16", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "16";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "16";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b16", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb16);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb16);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c16", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a16.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c16";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a16.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxFarming.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a17", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "17";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "17";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b17", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb17);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb17);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c17", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a17.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c17";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a17.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxParlour.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a18", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "18";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "18";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b18", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb18);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb18);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c18", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a18.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c18";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a18.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxCloth.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a19", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "19";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "19";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b19", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb19);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb19);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c19", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a19.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c19";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a19.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHouseHoldWork1.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a20", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "20";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "20";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b20", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb20);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb20);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c20", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a20.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c20";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a20.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxOthers1.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q44a21", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "21";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44a21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "21";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q44b21", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb21);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44b21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateIntensityb21);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q44c21", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a21.getText().toString();

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q44c21";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_44_a21.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxRunning.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a1", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b1", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb1);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb1);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c1", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                ////E
                if (checkBoxBadminton.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a2", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b2", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb2);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb2);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c2", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxSwimming.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a3", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b3", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb3);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb3);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c3", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHockey.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a4", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b4", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb4);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb4);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c4", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxHadudu.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a5", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b5", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb5);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb5);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c5", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                ////////////6//////
                if (checkBoxVolleyball.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a6", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b6", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb6);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb6);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c6", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxFootbal.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a7", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b7", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb7);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb7);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c7", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxTenis.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a8", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b8", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb8);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb8);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c8", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxOthers_recreational.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q45a9", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q45b9", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb9);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(vigiriousRecreationIntensityb9);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q45c9", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q45c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_recreational_45_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                //////////////EVAN KHAN12///////////////////////////////////

                if (checkBoxFastWalking.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a1", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b1", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb1);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb1);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c1", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c1";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a1.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                /////E//
                if (checkBoxJogging.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a2", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b2", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb2);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb2);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c2", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c2";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a2.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxCycling.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a3", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "3";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b3", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb3);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb3);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c3", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c3";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a3.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxCricket.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a4", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "4";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b4", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb4);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb4);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c4", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c4";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a4.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxYoga.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a5", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "5";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b5", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb5);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb5);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c5", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c5";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a5.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }

                //////6//
                if (checkBoxAerobics.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a6", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "6";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b6", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb6);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb6);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c6", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c6";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a6.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxExercise.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a7", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "7";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b7", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb7);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb7);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c7", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c7";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a7.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxOthersDancing.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a8", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "8";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b8", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb8);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb8);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c8", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c8";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a8.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (checkBoxOthers_moderate_recreational_others.isChecked()) {
                    Questions questionsForLoad = Common.qustionsRepository.getQuestions("Q46a9", update);
                    if (questionsForLoad == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46a9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "9";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoad.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadq = Common.qustionsRepository.getQuestions("Q46b9", update);
                    if (questionsForLoadq == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb9);

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46b9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = String.valueOf(moderateRecreationIntensityb9);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadq.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    //
                    Questions questionsForLoadc = Common.qustionsRepository.getQuestions("Q46c9", update);
                    if (questionsForLoadc == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q46c9";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = edit_typical_day_moderate_recreational_46_a9.getText().toString();
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsForLoadc.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }
                }
                if (smokeYesNo == 2)
                {
                    Questions questions1 = Common.qustionsRepository.getQuestions("Q32", update);

                    if (questions1 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.id = questions1.id;
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                }
                else
                    {


                    Questions questionsFor711 = Common.qustionsRepository.getQuestions("Q32", update);
                    if (questionsFor711 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor711.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

///////////////////////////Khan//////////


                    //////////////EVAN KHAN1///////////////////////////////////


                    //////////////EVAN KHAN12///////////////////////////////////
                    Questions questionsFor451 = Common.qustionsRepository.getQuestions("Q32a", update);
                    if (questionsFor451 == null) {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q32a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_smoke_years.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q32a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_smoke_years.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        questions1.id = questionsFor451.id;
                        Common.qustionsRepository.updateQuestions(questions1);
                    }
                    Questions questionsFor4561 = Common.qustionsRepository.getQuestions("Q32b", update);
                    if (questionsFor4561 == null) {
                        Questions questions2 = new Questions();
                        questions2.type = "behavioral";
                        questions2.question = "Q32b";
                        questions2.member_id = memberHabitsFor.member_unique_code;
                        questions2.answer = edit_smoke_stick.getText().toString();
                        questions2.date = currentDate;
                        questions2.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions2);
                    } else {
                        Questions questions2 = new Questions();
                        questions2.type = "behavioral";
                        questions2.question = "Q32b";
                        questions2.member_id = memberHabitsFor.member_unique_code;
                        questions2.answer = edit_smoke_stick.getText().toString();
                        questions2.date = currentDate;
                        questions2.master_id = memberHabitsFor.id;

                        Common.qustionsRepository.updateQuestions(questions2);
                    }

                }
                if (jordaYesNo == 2) {
                    Questions questionsFor1988 = Common.qustionsRepository.getQuestions("Q33", update);
                    if (questionsFor1988 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor1988.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {

                    Questions questionsFor132 = Common.qustionsRepository.getQuestions("Q33", update);
                    if (questionsFor132 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor132.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }


                    ////
                    Questions questionsFor8451 = Common.qustionsRepository.getQuestions("Q33a", update);
                    if (questionsFor8451 == null) {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q33a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_jorda.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q33a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_jorda.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        questions1.id = questionsFor8451.id;
                        Common.qustionsRepository.updateQuestions(questions1);
                    }

                }
                memberHabit.WorkplaceYesNo = workplaceYesNo;
                if (workplaceYesNo == 2) {
                    Questions questionsFor1778 = Common.qustionsRepository.getQuestions("Q34", update);
                    if (questionsFor1778 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor1778.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {

                    Questions questionsFor158 = Common.qustionsRepository.getQuestions("Q34", update);
                    if (questionsFor158 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor158.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }


                    ////
                    Questions questionsFor441 = Common.qustionsRepository.getQuestions("Q34a", update);
                    if (questionsFor441 == null) {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q34a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_workplace.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q34a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_workplace.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        questions1.id = questionsFor441.id;
                        Common.qustionsRepository.updateQuestions(questions1);
                    }

                }
                if (alcoholYesNo == 2) {
                    Questions questionsFor781 = Common.qustionsRepository.getQuestions("Q35", update);
                    if (questionsFor781 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;

                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "2";
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor781.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {


                    Questions questionsFor151 = Common.qustionsRepository.getQuestions("Q35", update);
                    if (questionsFor151 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);

                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.member_unique_code;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor151.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }

                    ////
                    Questions questionsFor561 = Common.qustionsRepository.getQuestions("Q35a", update);
                    if (questionsFor561 == null) {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q35a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_alcohol.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q35a";
                        questions1.member_id = memberHabitsFor.member_unique_code;
                        questions1.answer = edit_alcohol.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        questions1.id = questionsFor561.id;
                        Common.qustionsRepository.updateQuestions(questions1);
                    }

                }



                Questions questionsFor167 = Common.qustionsRepository.getQuestions("Q36", update);
                if (questionsFor167 == null) {
                    Questions questions5 = new Questions();
                    questions5.type = "behavioral";
                    questions5.question = "Q36";
                    questions5.member_id = memberHabitsFor.member_unique_code;
                    questions5.answer = String.valueOf(typicalFruits);

                    questions5.date = currentDate;
                    questions5.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions5);
                } else {
                    Questions questions5 = new Questions();
                    questions5.type = "behavioral";
                    questions5.question = "Q36";
                    questions5.member_id = memberHabitsFor.member_unique_code;
                    questions5.answer = String.valueOf(typicalFruits);

                    questions5.date = currentDate;
                    questions5.master_id = memberHabitsFor.id;
                    questions5.id = questionsFor167.id;
                    Common.qustionsRepository.updateQuestions(questions5);
                }

                ///

                Questions questionsFor413 = Common.qustionsRepository.getQuestions("Q37", update);
                if (questionsFor413 == null) {
                    Questions questions6 = new Questions();
                    questions6.type = "behavioral";
                    questions6.question = "Q37";
                    questions6.member_id = memberHabitsFor.member_unique_code;
                    questions6.answer = String.valueOf(fruitsShowCard);
                    questions6.date = currentDate;
                    questions6.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions6);
                } else {
                    Questions questions6 = new Questions();
                    questions6.type = "behavioral";
                    questions6.question = "Q37";
                    questions6.member_id = memberHabitsFor.member_unique_code;
                    questions6.answer = String.valueOf(fruitsShowCard);
                    questions6.date = currentDate;
                    questions6.master_id = memberHabitsFor.id;
                    questions6.id = questionsFor413.id;
                    Common.qustionsRepository.updateQuestions(questions6);
                }


                ///

                Questions questionsFor26 = Common.qustionsRepository.getQuestions("Q38", update);
                if (questionsFor26 == null) {
                    Questions questions7 = new Questions();
                    questions7.type = "behavioral";
                    questions7.question = "Q38";
                    questions7.member_id = memberHabitsFor.member_unique_code;
                    questions7.answer = String.valueOf(typicalVegetables);
                    questions7.date = currentDate;
                    questions7.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions7);
                } else {
                    Questions questions7 = new Questions();
                    questions7.type = "behavioral";
                    questions7.question = "Q38";
                    questions7.member_id = memberHabitsFor.member_unique_code;
                    questions7.answer = String.valueOf(typicalVegetables);
                    questions7.date = currentDate;
                    questions7.master_id = memberHabitsFor.id;
                    questions7.id = questionsFor26.id;
                    Common.qustionsRepository.updateQuestions(questions7);
                }


                ///

                Questions questionsFor61 = Common.qustionsRepository.getQuestions("Q39", update);
                if (questionsFor61 == null) {
                    Questions questions8 = new Questions();
                    questions8.type = "behavioral";
                    questions8.question = "Q39";
                    questions8.member_id = memberHabitsFor.member_unique_code;
                    questions8.answer = String.valueOf(vegetablesShowCard);
                    questions8.date = currentDate;
                    questions8.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions8);
                } else {
                    Questions questions8 = new Questions();
                    questions8.type = "behavioral";
                    questions8.question = "Q39";
                    questions8.member_id = memberHabitsFor.member_unique_code;
                    questions8.answer = String.valueOf(vegetablesShowCard);
                    questions8.date = currentDate;
                    questions8.master_id = memberHabitsFor.id;
                    questions8.id = questionsFor61.id;
                    Common.qustionsRepository.updateQuestions(questions8);
                }


                ///

                Questions questionsFor45 = Common.qustionsRepository.getQuestions("Q39", update);
                if (questionsFor45 == null) {
                    Questions questions9 = new Questions();
                    questions9.type = "behavioral";
                    questions9.question = "Q39";
                    questions9.member_id = memberHabitsFor.member_unique_code;
                    questions9.answer = String.valueOf(vegetablesShowCard);
                    questions9.date = currentDate;
                    questions9.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions9);
                } else {
                    Questions questions9 = new Questions();
                    questions9.type = "behavioral";
                    questions9.question = "Q39";
                    questions9.member_id = memberHabitsFor.member_unique_code;
                    questions9.answer = String.valueOf(vegetablesShowCard);
                    questions9.date = currentDate;
                    questions9.master_id = memberHabitsFor.id;
                    questions9.id = questionsFor45.id;
                    Common.qustionsRepository.updateQuestions(questions9);
                }


                ///

                Questions questionsFor41 = Common.qustionsRepository.getQuestions("Q41", update);
                if (questionsFor41 == null) {
                    Questions questions10 = new Questions();
                    questions10.type = "behavioral";
                    questions10.question = "Q41";
                    questions10.member_id = memberHabitsFor.member_unique_code;
                    questions10.answer = String.valueOf(saltyBuy);
                    questions10.date = currentDate;
                    questions10.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions10);
                } else {
                    Questions questions10 = new Questions();
                    questions10.type = "behavioral";
                    questions10.question = "Q41";
                    questions10.member_id = memberHabitsFor.member_unique_code;
                    questions10.answer = String.valueOf(saltyBuy);
                    questions10.date = currentDate;
                    questions10.master_id = memberHabitsFor.id;
                    questions10.id = questionsFor41.id;
                    Common.qustionsRepository.updateQuestions(questions10);
                }


                ///
                Questions questionsFor13 = Common.qustionsRepository.getQuestions("Q42", update);
                if (questionsFor13 == null) {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42";
                    questions11.member_id = memberHabitsFor.member_unique_code;
                    questions11.answer = String.valueOf(takingSalt);
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions11);
                } else {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42";
                    questions11.member_id = memberHabitsFor.member_unique_code;
                    questions11.answer = String.valueOf(takingSalt);
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    questions11.id = questionsFor13.id;
                    Common.qustionsRepository.updateQuestions(questions11);
                }
                Questions questionsFor137 = Common.qustionsRepository.getQuestions("Q42a", update);
                if (questionsFor137 == null) {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42a";
                    questions11.member_id = memberHabitsFor.member_unique_code;
                    questions11.answer = edit_yes_extra_salt.getText().toString();
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions11);
                } else {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42";
                    questions11.member_id = memberHabitsFor.member_unique_code;
                    questions11.answer = edit_yes_extra_salt.getText().toString();
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    questions11.id = questionsFor137.id;
                    Common.qustionsRepository.updateQuestions(questions11);
                }


                if (vigorousIntensity == 2) {
                    Questions questionsFor14 = Common.qustionsRepository.getQuestions("Q43", update);
                    if (questionsFor14 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor14.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                } else {
                    Questions questionsFor6 = Common.qustionsRepository.getQuestions("Q43", update);
                    if (questionsFor6 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor6.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

//                    Questions questionsFor8 = Common.qustionsRepository.getQuestions("Q43a", update);
//                    if (questionsFor8 == null) {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q43a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = vigiriousIntensity();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions13);
//                    } else {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q43a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = vigiriousIntensity();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        questions13.id = questionsFor8.id;
//                        Common.qustionsRepository.updateQuestions(questions13);
//                    }
//
//
//                    Questions questionsFor9 = Common.qustionsRepository.getQuestions("Q43b", update);
//                    if (questionsFor9 == null) {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q43b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(vigorousIntensityTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions14);
//                    } else {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q43b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(vigorousIntensityTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        questions14.id = questionsFor9.id;
//                        Common.qustionsRepository.updateQuestions(questions14);
//                    }
//
//                    Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q43c", update);
//                    if (questionsFor19 == null) {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q43c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions15);
//                    } else {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q43c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        questions15.id = questionsFor19.id;
//                        Common.qustionsRepository.updateQuestions(questions15);
//                    }

                }

                ////
                if (moderateIntensity == 2) {
                    Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q44", update);
                    if (questionsFor15 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor15.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                } else {
                    Questions questionsFor111 = Common.qustionsRepository.getQuestions("Q44", update);
                    if (questionsFor111 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor111.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

//                    Questions questionsFor91 = Common.qustionsRepository.getQuestions("Q44a", update);
//                    if (questionsFor91 == null) {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q44a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = moderateIntensity();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions13);
//                    } else {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q44a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = moderateIntensity();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        questions13.id = questionsFor91.id;
//                        Common.qustionsRepository.updateQuestions(questions13);
//                    }
//
//                    Questions questionsFor89 = Common.qustionsRepository.getQuestions("Q44b", update);
//                    if (questionsFor89 == null) {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q44b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(moderateIntensityTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions14);
//                    } else {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q44b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(moderateIntensityTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        questions14.id = questionsFor89.id;
//                        Common.qustionsRepository.updateQuestions(questions14);
//                    }
//
//                    Questions questionsFor67 = Common.qustionsRepository.getQuestions("Q44c", update);
//                    if (questionsFor67 == null) {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q44c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day_moderate.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions15);
//                    } else {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q44c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day_moderate.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        questions15.id = questionsFor67.id;
//                        Common.qustionsRepository.updateQuestions(questions15);
//                    }

                }


                ////
                if (vigorousIntensityRecreational == 2) {
                    Questions questionsFor78 = Common.qustionsRepository.getQuestions("Q45", update);
                    if (questionsFor78 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor78.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                } else {
                    Questions questionsFor616 = Common.qustionsRepository.getQuestions("Q45", update);
                    if (questionsFor616 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor616.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }


//                    Questions questionsFor51 = Common.qustionsRepository.getQuestions("Q45a", update);
//                    if (questionsFor51 == null) {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q45a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = vigorousIntensityRecreational();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions13);
//                    } else {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q45a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = vigorousIntensityRecreational();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        questions13.id = questionsFor51.id;
//                        Common.qustionsRepository.updateQuestions(questions13);
//                    }
//
//
//                    Questions questionsFor18 = Common.qustionsRepository.getQuestions("Q45b", update);
//                    if (questionsFor18 == null) {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q45b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions14);
//                    } else {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q45b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        questions14.id = questionsFor18.id;
//                        Common.qustionsRepository.updateQuestions(questions14);
//                    }
//
//                    Questions questionsFor241 = Common.qustionsRepository.getQuestions("Q45c", update);
//                    if (questionsFor241 == null) {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q45c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day_recreational.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions15);
//                    } else {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q45c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day_recreational.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        questions15.id = questionsFor241.id;
//                        Common.qustionsRepository.updateQuestions(questions15);
//                    }

                }


                if (moderateIntensityRecreational == 2) {
                    Questions questionsFor31 = Common.qustionsRepository.getQuestions("Q46", update);
                    if (questionsFor31 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.id = questionsFor31.id;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                } else {
                    Questions questionsFor21 = Common.qustionsRepository.getQuestions("Q46", update);
                    if (questionsFor21 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.id = questionsFor21.id;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

//                    Questions questionsFor178 = Common.qustionsRepository.getQuestions("Q46a", update);
//                    if (questionsFor178 == null) {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q46a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.answer = moderateIntensityRecreational();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions13);
//                    } else {
//                        Questions questions13 = new Questions();
//                        questions13.type = "behavioral";
//                        questions13.question = "Q46a";
//                        questions13.member_id = memberHabitsFor.member_unique_code;
//                        questions13.id = questionsFor178.id;
//                        questions13.answer = moderateIntensityRecreational();
//                        questions13.date = currentDate;
//                        questions13.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.updateQuestions(questions13);
//                    }
//
//                    Questions questionsFor17 = Common.qustionsRepository.getQuestions("Q46b", update);
//                    if (questionsFor17 == null) {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q46b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions14);
//                    } else {
//                        Questions questions14 = new Questions();
//                        questions14.type = "behavioral";
//                        questions14.question = "Q46b";
//                        questions14.member_id = memberHabitsFor.member_unique_code;
//                        questions14.id = questionsFor17.id;
//                        questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
//                        questions14.date = currentDate;
//                        questions14.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.updateQuestions(questions14);
//                    }
//
//                    Questions questionsFor199 = Common.qustionsRepository.getQuestions("Q46c", update);
//                    if (questionsFor199 == null) {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q46c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.insertToQuestions(questions15);
//                    } else {
//                        Questions questions15 = new Questions();
//                        questions15.type = "behavioral";
//                        questions15.question = "Q46c";
//                        questions15.member_id = memberHabitsFor.member_unique_code;
//                        questions15.id = questionsFor199.id;
//                        questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
//                        questions15.date = currentDate;
//                        questions15.master_id = memberHabitsFor.id;
//                        Common.qustionsRepository.updateQuestions(questions15);
//                    }

                }
                Questions questionsFor47 = Common.qustionsRepository.getQuestions("Q47", update);
                if (questionsFor47 == null) {
                    Questions questions12 = new Questions();
                    questions12.type = "behavioral";
                    questions12.question = "Q47";
                    questions12.member_id = memberHabitsFor.member_unique_code;
                    questions12.answer = edit_mat.getText().toString();
                    questions12.date = currentDate;
                    questions12.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions12);
                } else {
                    Questions questions12 = new Questions();
                    questions12.type = "behavioral";
                    questions12.question = "Q47";
                    questions12.member_id = memberHabitsFor.member_unique_code;
                    questions12.id = questionsFor47.id;
                    questions12.answer = edit_mat.getText().toString();
                    questions12.date = currentDate;
                    questions12.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.updateQuestions(questions12);
                }

                if (recliningActivities == 2) {
                    Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q48", update);
                    if (questionsFor19 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.id = questionsFor19.id;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                } else {
                    Questions questionsFor12 = Common.qustionsRepository.getQuestions("Q48", update);
                    if (questionsFor12 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.id = questionsFor12.id;
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.member_unique_code;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }


                    Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q48a", update);
                    if (questionsFor15 == null) {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q48a";
                        questions15.member_id = memberHabitsFor.member_unique_code;
                        questions15.answer = edit_yes_reclining.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.id = questionsFor15.id;
                        questions15.type = "behavioral";
                        questions15.question = "Q48a";
                        questions15.member_id = memberHabitsFor.member_unique_code;
                        questions15.answer = edit_yes_reclining.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }
                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                memberHabit.household_uniqe_id = memberHabitsFor.household_uniqe_id;
                memberHabit.MemberId = memberHabitsFor.MemberId;
                memberHabit.member_unique_code = memberHabitsFor.member_unique_code;
                memberHabit.member_national_id = String.valueOf(memberHabitsFor.member_national_id);
                Common.memberHabitRepository.updateMemberHabit(memberHabit);
                if (frag.equals("frag")) {
                    ((CCUserHomeActivity) getActivity()).backForDetails();
                } else {
                    ((HouseholdHomeActivity) getActivity()).backForDetails();
                }
                //////////////////////FIRST/////////////////
            } else {
                int id = Common.memberMyselfRepository.maxValue();

                MemberMyself memberMyself = Common.memberMyselfRepository.getMemberMyselfNo(id);
                memberHabit.MemberId = memberMyself.MemberId;
                memberHabit.household_uniqe_id = memberMyself.UniqueId;
                memberHabit.member_unique_code = memberMyself.UniqueCode;
                memberHabit.member_national_id = String.valueOf(memberMyself.NationalId);
                MemberHabit memberHabits = Common.memberHabitRepository.getMemberHabitNo(memberMyself.UniqueCode);
                if (smokeYesNo == -1 || jordaYesNo == -1 || workplaceYesNo == -1 || alcoholYesNo == -1 || takingSalt == -1 || vigorousIntensity == -1 || moderateIntensity == -1 || vigorousIntensityRecreational == -1 || moderateIntensityRecreational == -1 || recliningActivities == -1) {
                    Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                    if (memberHabits == null) {

                        if (smokeYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q32";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {


                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q32";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q32a";
                            questions1.member_id = memberMyself.UniqueCode;
                            questions1.answer = edit_smoke_years.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);

                            Questions questions2 = new Questions();
                            questions2.type = "behavioral";
                            questions2.question = "Q32b";
                            questions2.member_id = memberMyself.UniqueCode;
                            questions2.answer = edit_smoke_stick.getText().toString();
                            questions2.date = currentDate;
                            questions2.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions2);

                        }

                        if (jordaYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q33";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {
                            try {
                                memberHabit.JordaYears = Integer.parseInt(edit_jorda.getText().toString());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                memberHabit.JordaYears = 0;
                            }
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q33";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q33a";
                            questions1.member_id = memberMyself.UniqueCode;
                            questions1.answer = edit_jorda.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }

                        if (workplaceYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q34";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {

                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q34";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q34a";
                            questions1.member_id = memberMyself.UniqueCode;
                            questions1.answer = edit_workplace.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }
                        if (alcoholYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q35";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {

                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q35";
                            questions.member_id = memberMyself.UniqueCode;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q35a";
                            questions1.member_id = memberMyself.UniqueCode;
                            questions1.answer = edit_alcohol.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }


                        Questions questions5 = new Questions();
                        questions5.type = "behavioral";
                        questions5.question = "Q36";
                        questions5.member_id = memberMyself.UniqueCode;
                        questions5.answer = String.valueOf(typicalFruits);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions5.date = currentDate;
                        questions5.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions5);
                        ///


                        Questions questions6 = new Questions();
                        questions6.type = "behavioral";
                        questions6.question = "Q37";
                        questions6.member_id = memberMyself.UniqueCode;
                        questions6.answer = String.valueOf(fruitsShowCard);
                        questions6.date = currentDate;
                        questions6.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions6);

                        ///


                        Questions questions7 = new Questions();
                        questions7.type = "behavioral";
                        questions7.question = "Q38";
                        questions7.member_id = memberMyself.UniqueCode;
                        questions7.answer = String.valueOf(typicalVegetables);
                        questions7.date = currentDate;
                        questions7.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions7);

                        ///


                        Questions questions8 = new Questions();
                        questions8.type = "behavioral";
                        questions8.question = "Q39";
                        questions8.member_id = memberMyself.UniqueCode;
                        questions8.answer = String.valueOf(vegetablesShowCard);
                        questions8.date = currentDate;
                        questions8.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions8);


                        ///

//
//                        Questions questions9 = new Questions();
//                        questions9.type = "behavioral";
//                        questions9.question = "Q40";
//                        questions9.member_id = memberMyself.MemberId;
//                        questions9.answer = String.valueOf(vegetablesShowCard);
//                        questions9.date = currentDate;
//                        questions9.master_id = memberMyself.id;
//                        Common.qustionsRepository.insertToQuestions(questions9);


                        ///


                        Questions questions10 = new Questions();
                        questions10.type = "behavioral";
                        questions10.question = "Q41";
                        questions10.member_id = memberMyself.UniqueCode;
                        questions10.answer = String.valueOf(saltyBuy);
                        questions10.date = currentDate;
                        questions10.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions10);


                        ///


                        if (takingSalt == 2) {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberMyself.UniqueCode;
                            questions11.answer = String.valueOf(takingSalt);
                            questions11.date = currentDate;
                            questions11.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions11);
                        } else {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberMyself.UniqueCode;
                            questions11.answer = String.valueOf(takingSalt);
                            questions11.date = currentDate;
                            questions11.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions11);

                            Questions questions311 = new Questions();
                            questions311.type = "behavioral";
                            questions311.question = "Q42a";
                            questions311.member_id = memberMyself.UniqueCode;
                            questions311.answer = edit_yes_extra_salt.getText().toString();
                            questions311.date = currentDate;
                            questions311.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions311);
                        }


////////////////////////////////Q43E

                        if (vigorousIntensity == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q43";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q43";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

                            if (checkBoxHeaveyLoad.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a1";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "1";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b1";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb1);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c1";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a1.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxDigging.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a2";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "2";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b2";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb2);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c2";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a2.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxFurniture.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a3";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "3";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b3";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb3);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c3";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a3.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxPickingCrops.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a4";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "4";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b4";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb4);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c4";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a4.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxCuttingTrees.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a5";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "5";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b5";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb5);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c5";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a5.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxBreakUpPaddy.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a6";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "6";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b6";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb6);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c6";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a6.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxDrivingRickshaw.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a7";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "7";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b7";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb7);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c7";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a7.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxFishing.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a8";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "8";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b8";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb8);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c8";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a8.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxPlouging.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a9";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "9";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b9";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb9);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c9";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a9.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHeaveyConstructionWork.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a10";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "10";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b10";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb10);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c10";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a10.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHeaveyGoods.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a11";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "11";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b11";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb11);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c11";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a11.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHeaveyGoodsHead.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a12";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "12";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b12";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb12);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c12";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a12.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxSoldDigging.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a13";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "13";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b13";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb13);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c13";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a13.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxWashing.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a14";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "14";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b14";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb14);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c14";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a14.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxStepping.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a15";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "15";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b15";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb15);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c15";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a15.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxOthers.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q43a16";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "16";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q43b16";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigirousIntensityb16);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q43c16";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_43_a16.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

////////////////////////////////////////Q44E///////////////////


                            if (checkBoxHouseHoldWork.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a1";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "1";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b1";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb1);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c1";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a1.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxGardening.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a2";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "2";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b2";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb2);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c2";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a2.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxMilkingCows.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a3";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "3";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b3";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb3);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c3";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a3.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxCultivatingLand.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a4";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "4";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b4";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb4);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c4";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a4.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxPlantingHarvest.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a5";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "5";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b5";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb5);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c5";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a5.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }


                            if (checkBoxWeavingCloth.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a6";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "6";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b6";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb6);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c6";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a6.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxWashingCloths.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a7";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "7";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b7";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb7);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c7";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a7.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxRearing.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a8";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "8";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b8";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb8);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c8";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a8.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxMixingCement.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a9";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "9";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b9";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb9);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c9";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a9.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxWoodWork.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a10";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "10";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b10";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb10);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c10";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a10.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxDrawingWater.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a11";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "11";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b11";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb11);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c11";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a11.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxCarryingLightWeight.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a12";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "12";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b12";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb12);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c12";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a12.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxWashingCloths1.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a13";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "13";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b13";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb13);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c13";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a13.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxGardening1.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a14";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "14";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b14";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb14);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c14";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a14.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxMilkingCows1.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a15";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "15";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b15";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb15);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c15";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a15.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxRoping.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a16";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "16";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b16";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb16);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c16";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a16.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxFarming.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a17";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "17";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b17";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb17);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c17";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a17.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxParlour.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a18";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "18";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b18";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb18);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c18";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a18.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxCloth.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a19";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "19";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b19";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb19);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c19";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a19.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHouseHoldWork1.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a20";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "20";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b20";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb20);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c20";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a15.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxOthers1.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q44a21";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "21";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q44b21";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateIntensityb21);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q44c21";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_44_a20.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            ////////////////////////////////////Q45E//////////


                            if (checkBoxRunning.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a1";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "1";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b1";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb1);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c1";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a1.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxBadminton.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a2";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "2";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b2";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb2);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c2";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a2.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxSwimming.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a3";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "3";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b3";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb3);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c3";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a3.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHockey.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a4";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "4";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b4";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb4);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c4";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a4.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxHadudu.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a5";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "5";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b5";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb5);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c5";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a5.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            /////////6////

                            if (checkBoxVolleyball.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a6";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "6";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b6";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb6);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c6";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a6.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxFootbal.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a7";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "7";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b7";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb7);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c7";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a7.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxTenis.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a8";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "8";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b8";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb8);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c8";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a8.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxOthers_recreational.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q45a9";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "9";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q45b9";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(vigiriousRecreationIntensityb9);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q45c9";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_recreational_45_a9.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            ////////////////////////////////////Q46E//////////

                            if (checkBoxFastWalking.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a1";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "1";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b1";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb1);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c1";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a1.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }

                            if (checkBoxJogging.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a2";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "2";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b2";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb2);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c2";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a2.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxCycling.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a3";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "1";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b3";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb3);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c3";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a3.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxCricket.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a4";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "4";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b4";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb4);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c4";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a4.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            /////////5///
                            if (checkBoxYoga.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a5";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "5";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b5";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb5);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c5";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a5.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxAerobics.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a6";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "6";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b6";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb6);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c6";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a6.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxExercise.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a7";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "7";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b7";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb7);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c7";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a7.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxOthersDancing.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a8";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "8";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b8";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb8);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c8";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a8.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
                            if (checkBoxOthers_moderate_recreational_others.isChecked()) {
                                Questions questionsLoad = new Questions();
                                questionsLoad.type = "behavioral";
                                questionsLoad.question = "Q46a9";
                                questionsLoad.member_id = memberMyself.UniqueCode;
                                questionsLoad.answer = "9";
                                questionsLoad.date = currentDate;
                                questionsLoad.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad);

                                Questions questionsLoad1 = new Questions();
                                questionsLoad1.type = "behavioral";
                                questionsLoad1.question = "Q46b9";
                                questionsLoad1.member_id = memberMyself.UniqueCode;
                                questionsLoad1.answer = String.valueOf(moderateRecreationIntensityb9);
                                questionsLoad1.date = currentDate;
                                questionsLoad1.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad1);

                                Questions questionsLoad2 = new Questions();
                                questionsLoad2.type = "behavioral";
                                questionsLoad2.question = "Q46c9";
                                questionsLoad2.member_id = memberMyself.UniqueCode;
                                questionsLoad2.answer = edit_typical_day_moderate_recreational_46_a9.getText().toString();
                                questionsLoad2.date = currentDate;
                                questionsLoad2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questionsLoad2);
                            }
//                            Questions questions13 = new Questions();
//                            questions13.type = "behavioral";
//                            questions13.question = "Q43a";
//                            questions13.member_id = memberMyself.UniqueCode;
//                            questions13.answer = vigiriousIntensity();
//                            questions13.date = currentDate;
//                            questions13.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions13);
//                            Questions questions14 = new Questions();
//                            questions14.type = "behavioral";
//                            questions14.question = "Q43b";
//                            questions14.member_id = memberMyself.UniqueCode;
//                            questions14.answer = String.valueOf(vigorousIntensityTypical);
//                            questions14.date = currentDate;
//                            questions14.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions14);
//
//                            Questions questions15 = new Questions();
//                            questions15.type = "behavioral";
//                            questions15.question = "Q43c";
//                            questions15.member_id = memberMyself.UniqueCode;
//                            questions15.answer = edit_typical_day.getText().toString();
//                            questions15.date = currentDate;
//                            questions15.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions15);
                        }

                        ////
                        if (moderateIntensity == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q44";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q44";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

//                            Questions questions13 = new Questions();
//                            questions13.type = "behavioral";
//                            questions13.question = "Q44a";
//                            questions13.member_id = memberMyself.UniqueCode;
//                            questions13.answer = moderateIntensity();
//                            questions13.date = currentDate;
//                            questions13.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions13);
//                            Questions questions14 = new Questions();
//                            questions14.type = "behavioral";
//                            questions14.question = "Q44b";
//                            questions14.member_id = memberMyself.UniqueCode;
//                            questions14.answer = String.valueOf(moderateIntensityTypical);
//                            questions14.date = currentDate;
//                            questions14.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions14);
//
//                            Questions questions15 = new Questions();
//                            questions15.type = "behavioral";
//                            questions15.question = "Q44c";
//                            questions15.member_id = memberMyself.UniqueCode;
//                            questions15.answer = edit_typical_day_moderate.getText().toString();
//                            questions15.date = currentDate;
//                            questions15.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions15);
                        }


                        ////
                        if (vigorousIntensityRecreational == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q45";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q45";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

//                            Questions questions13 = new Questions();
//                            questions13.type = "behavioral";
//                            questions13.question = "Q45a";
//                            questions13.member_id = memberMyself.UniqueCode;
//                            questions13.answer = vigorousIntensityRecreational();
//                            questions13.date = currentDate;
//                            questions13.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions13);
//                            Questions questions14 = new Questions();
//                            questions14.type = "behavioral";
//                            questions14.question = "Q45b";
//                            questions14.member_id = memberMyself.UniqueCode;
//                            questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
//                            questions14.date = currentDate;
//                            questions14.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions14);
//
//                            Questions questions15 = new Questions();
//                            questions15.type = "behavioral";
//                            questions15.question = "Q45c";
//                            questions15.member_id = memberMyself.UniqueCode;
//                            questions15.answer = edit_typical_day_recreational.getText().toString();
//                            questions15.date = currentDate;
//                            questions15.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions15);
                        }


                        if (moderateIntensityRecreational == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q46";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
//                            Questions questions12 = new Questions();
//                            questions12.type = "behavioral";
//                            questions12.question = "Q46";
//                            questions12.member_id = memberMyself.UniqueCode;
//                            questions12.answer = "1";
//                            questions12.date = currentDate;
//                            questions12.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions12);
//
//                            Questions questions13 = new Questions();
//                            questions13.type = "behavioral";
//                            questions13.question = "Q46a";
//                            questions13.member_id = memberMyself.UniqueCode;
//                            questions13.answer = moderateIntensityRecreational();
//                            questions13.date = currentDate;
//                            questions13.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions13);
//
//                            Questions questions14 = new Questions();
//                            questions14.type = "behavioral";
//                            questions14.question = "Q46b";
//                            questions14.member_id = memberMyself.UniqueCode;
//                            questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
//                            questions14.date = currentDate;
//                            questions14.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions14);
//
//                            Questions questions15 = new Questions();
//                            questions15.type = "behavioral";
//                            questions15.question = "Q46c";
//                            questions15.member_id = memberMyself.UniqueCode;
//                            questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
//                            questions15.date = currentDate;
//                            questions15.master_id = memberMyself.id;
//                            Common.qustionsRepository.insertToQuestions(questions15);
                        }

                        Questions questions47 = new Questions();
                        questions47.type = "behavioral";
                        questions47.question = "Q47";
                        questions47.member_id = memberMyself.UniqueCode;
                        questions47.answer = edit_mat.getText().toString();
                        questions47.date = currentDate;
                        questions47.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions47);
                        if (recliningActivities == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q48";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q48";
                            questions12.member_id = memberMyself.UniqueCode;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);


                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q48a";
                            questions15.member_id = memberMyself.UniqueCode;
                            questions15.answer = edit_yes_reclining.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }
                        SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                        Common.memberHabitRepository.insertToMemberHabit(memberHabit);

                        showInfoDialog(mActivity, memberMyself.UniqueCode);

                    }

                    //////////CSK
                    else {
                        memberHabit.id = memberHabits.id;
                        if (smokeYesNo == 2) {
                            Questions questions1 = Common.qustionsRepository.getQuestions("Q32", update);

                            if (questions1 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.id = questions1.id;
                                questions.question = "Q32";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            Questions questionsFor711 = Common.qustionsRepository.getQuestions("Q32", update);
                            if (questionsFor711 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor711.id;
                                Common.qustionsRepository.updateQuestions(questions);

                            }


                            ////
                            Questions questionsFor451 = Common.qustionsRepository.getQuestions("Q32a", update);
                            if (questionsFor451 == null) {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q32a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_smoke_years.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q32a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_smoke_years.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                questions1.id = questionsFor451.id;
                                Common.qustionsRepository.updateQuestions(questions1);
                            }
                            Questions questionsFor4561 = Common.qustionsRepository.getQuestions("Q32b", update);
                            if (questionsFor4561 == null) {
                                Questions questions2 = new Questions();
                                questions2.type = "behavioral";
                                questions2.question = "Q32b";
                                questions2.member_id = memberHabits.member_unique_code;
                                questions2.answer = edit_smoke_stick.getText().toString();
                                questions2.date = currentDate;
                                questions2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questions2);
                            } else {
                                Questions questions2 = new Questions();
                                questions2.type = "behavioral";
                                questions2.question = "Q32b";
                                questions2.member_id = memberHabits.member_unique_code;
                                questions2.answer = edit_smoke_stick.getText().toString();
                                questions2.date = currentDate;
                                questions2.master_id = memberMyself.id;

                                Common.qustionsRepository.updateQuestions(questions2);
                            }

                        }
                        if (jordaYesNo == 2) {
                            Questions questionsFor1988 = Common.qustionsRepository.getQuestions("Q33", update);
                            if (questionsFor1988 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor1988.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor132 = Common.qustionsRepository.getQuestions("Q33", update);
                            if (questionsFor132 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor132.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }


                            ////
                            Questions questionsFor8451 = Common.qustionsRepository.getQuestions("Q33a", update);
                            if (questionsFor8451 == null) {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q33a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_jorda.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q33a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_jorda.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                questions1.id = questionsFor8451.id;
                                Common.qustionsRepository.updateQuestions(questions1);
                            }

                        }
                        memberHabit.WorkplaceYesNo = workplaceYesNo;
                        if (workplaceYesNo == 2) {
                            Questions questionsFor1778 = Common.qustionsRepository.getQuestions("Q34", update);
                            if (questionsFor1778 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor1778.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor158 = Common.qustionsRepository.getQuestions("Q34", update);
                            if (questionsFor158 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor158.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }


                            ////
                            Questions questionsFor441 = Common.qustionsRepository.getQuestions("Q34a", update);
                            if (questionsFor441 == null) {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q34a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_workplace.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q34a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_workplace.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                questions1.id = questionsFor441.id;
                                Common.qustionsRepository.updateQuestions(questions1);
                            }

                        }
                        if (alcoholYesNo == 2) {
                            Questions questionsFor781 = Common.qustionsRepository.getQuestions("Q35", update);
                            if (questionsFor781 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;

                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor781.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor151 = Common.qustionsRepository.getQuestions("Q35", update);
                            if (questionsFor151 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);

                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.member_unique_code;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor151.id;
                                Common.qustionsRepository.updateQuestions(questions);

                            }

                            ////
                            Questions questionsFor561 = Common.qustionsRepository.getQuestions("Q35a", update);
                            if (questionsFor561 == null) {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q35a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_alcohol.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q35a";
                                questions1.member_id = memberHabits.member_unique_code;
                                questions1.answer = edit_alcohol.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                questions1.id = questionsFor561.id;
                                Common.qustionsRepository.updateQuestions(questions1);
                            }

                        }


                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        Questions questionsFor167 = Common.qustionsRepository.getQuestions("Q36", update);
                        if (questionsFor167 == null) {
                            Questions questions5 = new Questions();
                            questions5.type = "behavioral";
                            questions5.question = "Q36";
                            questions5.member_id = memberHabits.member_unique_code;
                            questions5.answer = String.valueOf(typicalFruits);

                            questions5.date = currentDate;
                            questions5.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions5);
                        } else {
                            Questions questions5 = new Questions();
                            questions5.type = "behavioral";
                            questions5.question = "Q36";
                            questions5.member_id = memberHabits.member_unique_code;
                            questions5.answer = String.valueOf(typicalFruits);

                            questions5.date = currentDate;
                            questions5.master_id = memberHabits.id;
                            questions5.id = questionsFor167.id;
                            Common.qustionsRepository.updateQuestions(questions5);
                        }

                        ///

                        Questions questionsFor413 = Common.qustionsRepository.getQuestions("Q37", update);
                        if (questionsFor413 == null) {
                            Questions questions6 = new Questions();
                            questions6.type = "behavioral";
                            questions6.question = "Q37";
                            questions6.member_id = memberHabits.member_unique_code;
                            questions6.answer = String.valueOf(fruitsShowCard);
                            questions6.date = currentDate;
                            questions6.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions6);
                        } else {
                            Questions questions6 = new Questions();
                            questions6.type = "behavioral";
                            questions6.question = "Q37";
                            questions6.member_id = memberHabits.member_unique_code;
                            questions6.answer = String.valueOf(fruitsShowCard);
                            questions6.date = currentDate;
                            questions6.master_id = memberHabits.id;
                            questions6.id = questionsFor413.id;
                            Common.qustionsRepository.updateQuestions(questions6);
                        }


                        ///

                        Questions questionsFor26 = Common.qustionsRepository.getQuestions("Q38", update);
                        if (questionsFor26 == null) {
                            Questions questions7 = new Questions();
                            questions7.type = "behavioral";
                            questions7.question = "Q38";
                            questions7.member_id = memberHabits.member_unique_code;
                            questions7.answer = String.valueOf(typicalVegetables);
                            questions7.date = currentDate;
                            questions7.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions7);
                        } else {
                            Questions questions7 = new Questions();
                            questions7.type = "behavioral";
                            questions7.question = "Q38";
                            questions7.member_id = memberHabits.member_unique_code;
                            questions7.answer = String.valueOf(typicalVegetables);
                            questions7.date = currentDate;
                            questions7.master_id = memberHabits.id;
                            questions7.id = questionsFor26.id;
                            Common.qustionsRepository.updateQuestions(questions7);
                        }


                        ///

                        Questions questionsFor61 = Common.qustionsRepository.getQuestions("Q39", update);
                        if (questionsFor61 == null) {
                            Questions questions8 = new Questions();
                            questions8.type = "behavioral";
                            questions8.question = "Q39";
                            questions8.member_id = memberHabits.member_unique_code;
                            questions8.answer = String.valueOf(vegetablesShowCard);
                            questions8.date = currentDate;
                            questions8.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions8);
                        } else {
                            Questions questions8 = new Questions();
                            questions8.type = "behavioral";
                            questions8.question = "Q39";
                            questions8.member_id = memberHabits.member_unique_code;
                            questions8.answer = String.valueOf(vegetablesShowCard);
                            questions8.date = currentDate;
                            questions8.master_id = memberHabits.id;
                            questions8.id = questionsFor61.id;
                            Common.qustionsRepository.updateQuestions(questions8);
                        }


                        ///

                        Questions questionsFor45 = Common.qustionsRepository.getQuestions("Q39", update);
                        if (questionsFor45 == null) {
                            Questions questions9 = new Questions();
                            questions9.type = "behavioral";
                            questions9.question = "Q39";
                            questions9.member_id = memberHabits.member_unique_code;
                            questions9.answer = String.valueOf(vegetablesShowCard);
                            questions9.date = currentDate;
                            questions9.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions9);
                        } else {
                            Questions questions9 = new Questions();
                            questions9.type = "behavioral";
                            questions9.question = "Q39";
                            questions9.member_id = memberHabits.member_unique_code;
                            questions9.answer = String.valueOf(vegetablesShowCard);
                            questions9.date = currentDate;
                            questions9.master_id = memberHabits.id;
                            questions9.id = questionsFor45.id;
                            Common.qustionsRepository.updateQuestions(questions9);
                        }


                        ///

                        Questions questionsFor41 = Common.qustionsRepository.getQuestions("Q41", update);
                        if (questionsFor41 == null) {
                            Questions questions10 = new Questions();
                            questions10.type = "behavioral";
                            questions10.question = "Q41";
                            questions10.member_id = memberHabits.member_unique_code;
                            questions10.answer = String.valueOf(saltyBuy);
                            questions10.date = currentDate;
                            questions10.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions10);
                        } else {
                            Questions questions10 = new Questions();
                            questions10.type = "behavioral";
                            questions10.question = "Q41";
                            questions10.member_id = memberHabits.member_unique_code;
                            questions10.answer = String.valueOf(saltyBuy);
                            questions10.date = currentDate;
                            questions10.master_id = memberHabits.id;
                            questions10.id = questionsFor41.id;
                            Common.qustionsRepository.updateQuestions(questions10);
                        }


                        ///
                        Questions questionsFor13 = Common.qustionsRepository.getQuestions("Q42", update);
                        if (questionsFor13 == null) {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberHabits.member_unique_code;
                            questions11.answer = String.valueOf(takingSalt);
                            questions11.date = currentDate;
                            questions11.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions11);
                        } else {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberHabits.member_unique_code;
                            questions11.answer = String.valueOf(takingSalt);
                            questions11.date = currentDate;
                            questions11.master_id = memberHabits.id;
                            questions11.id = questionsFor13.id;
                            Common.qustionsRepository.updateQuestions(questions11);
                        }

                        Questions questionsFor137 = Common.qustionsRepository.getQuestions("Q42a", update);
                        if (questionsFor137 == null) {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42a";
                            questions11.member_id = memberHabitsFor.member_unique_code;
                            questions11.answer = edit_yes_extra_salt.getText().toString();
                            questions11.date = currentDate;
                            questions11.master_id = memberHabitsFor.id;
                            Common.qustionsRepository.insertToQuestions(questions11);
                        } else {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberHabitsFor.member_unique_code;
                            questions11.answer = edit_yes_extra_salt.getText().toString();
                            questions11.date = currentDate;
                            questions11.master_id = memberHabitsFor.id;
                            questions11.id = questionsFor137.id;
                            Common.qustionsRepository.updateQuestions(questions11);
                        }


                        if (vigorousIntensity == 2) {
                            Questions questionsFor14 = Common.qustionsRepository.getQuestions("Q43", update);
                            if (questionsFor14 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor14.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                        } else {
                            Questions questionsFor6 = Common.qustionsRepository.getQuestions("Q43", update);
                            if (questionsFor6 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor6.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

//                            Questions questionsFor8 = Common.qustionsRepository.getQuestions("Q43a", update);
//                            if (questionsFor8 == null) {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q43a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = vigiriousIntensity();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions13);
//                            } else {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q43a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = vigiriousIntensity();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                questions13.id = questionsFor8.id;
//                                Common.qustionsRepository.updateQuestions(questions13);
//                            }
//
//
//                            Questions questionsFor9 = Common.qustionsRepository.getQuestions("Q43b", update);
//                            if (questionsFor9 == null) {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q43b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(vigorousIntensityTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions14);
//                            } else {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q43b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(vigorousIntensityTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                questions14.id = questionsFor9.id;
//                                Common.qustionsRepository.updateQuestions(questions14);
//                            }
//
//                            Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q43c", update);
//                            if (questionsFor19 == null) {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q43c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions15);
//                            } else {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q43c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                questions15.id = questionsFor19.id;
//                                Common.qustionsRepository.updateQuestions(questions15);
//                            }

                        }

                        ////
                        if (moderateIntensity == 2) {
                            Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q44", update);
                            if (questionsFor15 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor15.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                        } else {
                            Questions questionsFor111 = Common.qustionsRepository.getQuestions("Q44", update);
                            if (questionsFor111 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor111.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

//                            Questions questionsFor91 = Common.qustionsRepository.getQuestions("Q44a", update);
//                            if (questionsFor91 == null) {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q44a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = moderateIntensity();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions13);
//                            } else {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q44a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = moderateIntensity();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                questions13.id = questionsFor91.id;
//                                Common.qustionsRepository.updateQuestions(questions13);
//                            }
//
//                            Questions questionsFor89 = Common.qustionsRepository.getQuestions("Q44b", update);
//                            if (questionsFor89 == null) {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q44b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(moderateIntensityTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions14);
//                            } else {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q44b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(moderateIntensityTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                questions14.id = questionsFor89.id;
//                                Common.qustionsRepository.updateQuestions(questions14);
//                            }
//
//                            Questions questionsFor67 = Common.qustionsRepository.getQuestions("Q44c", update);
//                            if (questionsFor67 == null) {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q44c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day_moderate.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions15);
//                            } else {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q44c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day_moderate.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                questions15.id = questionsFor67.id;
//                                Common.qustionsRepository.updateQuestions(questions15);
//                            }

                        }


                        ////
                        if (vigorousIntensityRecreational == 2) {
                            Questions questionsFor78 = Common.qustionsRepository.getQuestions("Q45", update);
                            if (questionsFor78 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor78.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                        } else {
                            Questions questionsFor616 = Common.qustionsRepository.getQuestions("Q45", update);
                            if (questionsFor616 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor616.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }


//                            Questions questionsFor51 = Common.qustionsRepository.getQuestions("Q45a", update);
//                            if (questionsFor51 == null) {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q45a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = vigorousIntensityRecreational();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions13);
//                            } else {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q45a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = vigorousIntensityRecreational();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                questions13.id = questionsFor51.id;
//                                Common.qustionsRepository.updateQuestions(questions13);
//                            }
//
//
//                            Questions questionsFor18 = Common.qustionsRepository.getQuestions("Q45b", update);
//                            if (questionsFor18 == null) {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q45b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions14);
//                            } else {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q45b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                questions14.id = questionsFor18.id;
//                                Common.qustionsRepository.updateQuestions(questions14);
//                            }
//
//                            Questions questionsFor241 = Common.qustionsRepository.getQuestions("Q45c", update);
//                            if (questionsFor241 == null) {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q45c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day_recreational.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions15);
//                            } else {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q45c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day_recreational.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                questions15.id = questionsFor241.id;
//                                Common.qustionsRepository.updateQuestions(questions15);
//                            }

                        }


                        if (moderateIntensityRecreational == 2) {
                            Questions questionsFor31 = Common.qustionsRepository.getQuestions("Q46", update);
                            if (questionsFor31 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.id = questionsFor31.id;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                        } else {
                            Questions questionsFor21 = Common.qustionsRepository.getQuestions("Q46", update);
                            if (questionsFor21 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.id = questionsFor21.id;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

//                            Questions questionsFor178 = Common.qustionsRepository.getQuestions("Q46a", update);
//                            if (questionsFor178 == null) {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q46a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.answer = moderateIntensityRecreational();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions13);
//                            } else {
//                                Questions questions13 = new Questions();
//                                questions13.type = "behavioral";
//                                questions13.question = "Q46a";
//                                questions13.member_id = memberHabits.member_unique_code;
//                                questions13.id = questionsFor178.id;
//                                questions13.answer = moderateIntensityRecreational();
//                                questions13.date = currentDate;
//                                questions13.master_id = memberHabits.id;
//                                Common.qustionsRepository.updateQuestions(questions13);
//                            }
//
//                            Questions questionsFor17 = Common.qustionsRepository.getQuestions("Q46b", update);
//                            if (questionsFor17 == null) {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q46b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions14);
//                            } else {
//                                Questions questions14 = new Questions();
//                                questions14.type = "behavioral";
//                                questions14.question = "Q46b";
//                                questions14.member_id = memberHabits.member_unique_code;
//                                questions14.id = questionsFor17.id;
//                                questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
//                                questions14.date = currentDate;
//                                questions14.master_id = memberHabits.id;
//                                Common.qustionsRepository.updateQuestions(questions14);
//                            }
//
//                            Questions questionsFor199 = Common.qustionsRepository.getQuestions("Q46c", update);
//                            if (questionsFor199 == null) {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q46c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                Common.qustionsRepository.insertToQuestions(questions15);
//                            } else {
//                                Questions questions15 = new Questions();
//                                questions15.type = "behavioral";
//                                questions15.question = "Q46c";
//                                questions15.member_id = memberHabits.member_unique_code;
//                                questions15.id = questionsFor199.id;
//                                questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
//                                questions15.date = currentDate;
//                                questions15.master_id = memberHabits.id;
//                                Common.qustionsRepository.updateQuestions(questions15);
//                            }

                        }


                        if (recliningActivities == 2) {
                            Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q48", update);
                            if (questionsFor19 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.id = questionsFor19.id;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                        } else {
                            Questions questionsFor12 = Common.qustionsRepository.getQuestions("Q48", update);
                            if (questionsFor12 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.id = questionsFor12.id;
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.member_unique_code;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }


                            Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q48a", update);
                            if (questionsFor15 == null) {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q48a";
                                questions15.member_id = memberHabits.member_unique_code;
                                questions15.answer = edit_yes_reclining.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.id = questionsFor15.id;
                                questions15.type = "behavioral";
                                questions15.question = "Q48a";
                                questions15.member_id = memberHabits.member_unique_code;
                                questions15.answer = edit_yes_reclining.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }
                        memberHabit.household_uniqe_id = memberHabits.household_uniqe_id;
                        memberHabit.member_unique_code = memberHabits.member_unique_code;
                        memberHabit.member_national_id = String.valueOf(memberHabits.member_national_id);
                        Common.memberHabitRepository.updateMemberHabit(memberHabit);
                        if (frag.equals("frag")) {
                            ((CCUserHomeActivity) getActivity()).backForDetails();
                        } else {
                            ((HouseholdHomeActivity) getActivity()).backForDetails();
                        }


                    }
                }
            }


        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 2) {
            saveData();
        } else {
            String language = SharedPreferenceUtil.getLanguage(mActivity);
            if (language.equals("en")) {
                HHCreateMemberFragment.prevPage(1, "en");
            } else if (language.equals("bn")) {
                HHCreateMemberFragment.prevPage(1, "bn");
            }

        }
        return false;
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
                if (frag.equals("frag")) {
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

    private void initVigirousIntensityTypicalSpinner() {
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_1);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a1.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_1.get(position).getId());
                vigirousIntensityb1 = yesNoArrayListFor43_b_1.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_2);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a2.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_2.get(position).getId());
                vigirousIntensityb2 = yesNoArrayListFor43_b_2.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_3);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a3.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_3.get(position).getId());
                vigirousIntensityb3 = yesNoArrayListFor43_b_3.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_4);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a4.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_4.get(position).getId());
                vigirousIntensityb4 = yesNoArrayListFor43_b_4.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_5);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a5.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_5.get(position).getId());
                vigirousIntensityb5 = yesNoArrayListFor43_b_5.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_6);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a6.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_6.get(position).getId());
                vigirousIntensityb6 = yesNoArrayListFor43_b_6.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_7);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a7.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_7.get(position).getId());
                vigirousIntensityb7 = yesNoArrayListFor43_b_7.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_8);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a8.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_8.get(position).getId());
                vigirousIntensityb8 = yesNoArrayListFor43_b_8.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_9);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a9.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_9.get(position).getId());
                vigirousIntensityb9 = yesNoArrayListFor43_b_9.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_10);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a10.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_10.get(position).getId());
                vigirousIntensityb10 = yesNoArrayListFor43_b_10.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_11);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a11.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_11.get(position).getId());
                vigirousIntensityb11 = yesNoArrayListFor43_b_11.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_12);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a12.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_12.get(position).getId());
                vigirousIntensityb12 = yesNoArrayListFor43_b_12.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_13);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a13.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_13.get(position).getId());
                vigirousIntensityb13 = yesNoArrayListFor43_b_13.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_14);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a14.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_14.get(position).getId());
                vigirousIntensityb14 = yesNoArrayListFor43_b_14.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_15);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a15.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_15.get(position).getId());
                vigirousIntensityb15 = yesNoArrayListFor43_b_15.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor43_b_16);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_43_a16.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_43_a16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor43_b_16.get(position).getId());
                vigirousIntensityb16 = yesNoArrayListFor43_b_16.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initModerateIntensityTypicalSpinner() {
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_1);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a1.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_1.get(position).getId());
                moderateIntensityb1 = yesNoArrayListFor44_b_1.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_2);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a2.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_2.get(position).getId());
                moderateIntensityb2 = yesNoArrayListFor44_b_2.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_3);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a3.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_3.get(position).getId());
                moderateIntensityb3 = yesNoArrayListFor44_b_3.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_4);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a4.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_4.get(position).getId());
                moderateIntensityb4 = yesNoArrayListFor44_b_4.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_5);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a5.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_5.get(position).getId());
                moderateIntensityb5 = yesNoArrayListFor44_b_5.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_6);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a6.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_6.get(position).getId());
                moderateIntensityb6 = yesNoArrayListFor44_b_6.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_7);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a7.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_7.get(position).getId());
                moderateIntensityb7 = yesNoArrayListFor44_b_7.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_8);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a8.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_8.get(position).getId());
                moderateIntensityb8 = yesNoArrayListFor44_b_8.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_9);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a9.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_9.get(position).getId());
                moderateIntensityb9 = yesNoArrayListFor44_b_9.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_10);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a10.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_10.get(position).getId());
                moderateIntensityb10 = yesNoArrayListFor44_b_10.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_11);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a11.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_11.get(position).getId());
                moderateIntensityb11 = yesNoArrayListFor44_b_11.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_12);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a12.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_12.get(position).getId());
                moderateIntensityb12 = yesNoArrayListFor44_b_12.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_13);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a13.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_13.get(position).getId());
                moderateIntensityb13 = yesNoArrayListFor44_b_13.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_14);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a14.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_14.get(position).getId());
                moderateIntensityb14 = yesNoArrayListFor44_b_14.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_15);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a15.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_15.get(position).getId());
                moderateIntensityb15 = yesNoArrayListFor44_b_15.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_16);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a16.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_16.get(position).getId());
                moderateIntensityb16 = yesNoArrayListFor44_b_16.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_17);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a17.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_17.get(position).getId());
                moderateIntensityb17 = yesNoArrayListFor44_b_17.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_18);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a18.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_18.get(position).getId());
                moderateIntensityb18 = yesNoArrayListFor44_b_18.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_19);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a19.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_19.get(position).getId());
                moderateIntensityb19 = yesNoArrayListFor44_b_19.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_20);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a20.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_20.get(position).getId());
                moderateIntensityb20 = yesNoArrayListFor44_b_20.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor44_b_21);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_44_a21.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_44_a21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor44_b_21.get(position).getId());
                moderateIntensityb21 = yesNoArrayListFor44_b_21.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initVigiriousIntensityRecreationalSpinner() {
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_1);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a1.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_1.get(position).getId());
                vigiriousRecreationIntensityb1 = yesNoArrayListFor45_b_1.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_2);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a2.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_2.get(position).getId());
                vigiriousRecreationIntensityb2 = yesNoArrayListFor45_b_2.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_3);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a3.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_3.get(position).getId());
                vigiriousRecreationIntensityb3 = yesNoArrayListFor45_b_3.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_4);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a4.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_4.get(position).getId());
                vigiriousRecreationIntensityb4 = yesNoArrayListFor45_b_4.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_5);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a5.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_5.get(position).getId());
                vigiriousRecreationIntensityb5 = yesNoArrayListFor45_b_5.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_6);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a6.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_6.get(position).getId());
                vigiriousRecreationIntensityb6 = yesNoArrayListFor45_b_6.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_7);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a7.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_7.get(position).getId());
                vigiriousRecreationIntensityb7 = yesNoArrayListFor45_b_7.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_8);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a8.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_8.get(position).getId());
                vigiriousRecreationIntensityb8 = yesNoArrayListFor45_b_8.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor45_b_9);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational_45_a9.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational_45_a9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor45_b_9.get(position).getId());
                vigiriousRecreationIntensityb9 = yesNoArrayListFor45_b_9.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initModerateIntensityRecreationSpinner() {
        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_1);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a1.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_1.get(position).getId());
                moderateRecreationIntensityb1 = yesNoArrayListFor46_b_1.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_2);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a2.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_2.get(position).getId());
                moderateRecreationIntensityb2 = yesNoArrayListFor46_b_2.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_3);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a3.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_3.get(position).getId());
                moderateRecreationIntensityb3 = yesNoArrayListFor46_b_3.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_4);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a4.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_4.get(position).getId());
                moderateRecreationIntensityb4 = yesNoArrayListFor46_b_4.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_5);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a5.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_5.get(position).getId());
                moderateRecreationIntensityb5 = yesNoArrayListFor46_b_5.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_6);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a6.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_6.get(position).getId());
                moderateRecreationIntensityb6 = yesNoArrayListFor46_b_6.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_7);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a7.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_7.get(position).getId());
                moderateRecreationIntensityb7 = yesNoArrayListFor46_b_7.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_8);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a8.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_8.get(position).getId());
                moderateRecreationIntensityb8 = yesNoArrayListFor46_b_8.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        yesNoArrayAdapterTypicalVigorousRecreation = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListFor46_b_9);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational_46_a9.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_moderate_recreational_46_a9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListFor46_b_9.get(position).getId());
                moderateRecreationIntensityb9 = yesNoArrayListFor46_b_9.get(position).getId();
                linear_mat.setVisibility(View.GONE);
                checkbox_mat.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

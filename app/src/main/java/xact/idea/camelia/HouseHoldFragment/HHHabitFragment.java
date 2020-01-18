package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Database.Model.MemberHabit;
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
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
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;

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
    EditText edit_typical_day_moderate_recreational;
    EditText edit_typical_day_moderate;
    EditText edit_workplace;
    EditText edit_typical_day;
    EditText edit_alcohol;
    EditText edit_yes_extra_salt;
    EditText edit_typical_day_recreational;
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

    ArrayList<YesNoModel> yesNoArrayListForSmoke= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForJorda = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForWorkplace = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForAlcohol = new ArrayList<>();
    ArrayList<FruitsModel> yesNoArrayListForFruits = new ArrayList<>();
    ArrayList<FruitsCardModel> yesNoArrayListForFruitsCard = new ArrayList<>();
    ArrayList<VegetableModel> yesNoArrayListForVegetables = new ArrayList<>();
    ArrayList<VegetablesCardModel> yesNoArrayListForVegetablesCard = new ArrayList<>();
    ArrayList<SaltModel> yesNoArrayListForSaltBuy = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForTakingSalt = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForVigorousIntensityHeart= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForModerateIntensityHeart= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForModerateRecreationalHeartt= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForVigorousRecreationalHeart= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForReclinig= new ArrayList<>();
    ArrayList<ModerateModel> yesNoArrayListForModerate= new ArrayList<>();
    ArrayList<ModerateRecreationalModel> yesNoArrayListForModerateRecreational= new ArrayList<>();
    ArrayList<TypicalVigorousModel> yesNoArrayListForTypicalVigorous= new ArrayList<>();
    ArrayList<TypicalVigorousRecreationModel> yesNoArrayListForTypicalVigorousRecreation= new ArrayList<>();

    int smokeYesNo;
    int jordaYesNo;
    int workplaceYesNo;
    int alcoholYesNo;
    int typicalFruits;
    int fruitsShowCard;
    int typicalVegetables;
    int vegetablesShowCard;
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
   RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hhhabit, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        // display();
        handler = new Handler(this);
        return view;
    }

    private void initView() {
        relativeLayout=view.findViewById(R.id.relative);
        linear_moderate_intensity_recreational_activities_typical = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical);
        checkBoxFastWalking = view.findViewById(R.id.checkBoxFastWalking);
        linear_moderate_intensity_recreational_activities_typical_day = view.findViewById(R.id.linear_moderate_intensity_recreational_activities_typical_day);
        edit_typical_day_moderate_recreational = view.findViewById(R.id.edit_typical_day_moderate_recreational);
        checkBoxJogging = view.findViewById(R.id.checkBoxJogging);
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
        yesNoArrayListForSmoke= Utils.getyesNoList();
        yesNoArrayListForWorkplace= Utils.getyesNoList();
        yesNoArrayListForAlcohol= Utils.getyesNoList();
        yesNoArrayListForFruits= Utils.getfruitsModelList();
        yesNoArrayListForFruitsCard= Utils.getfruitsCardModelList();
        yesNoArrayListForVegetables= Utils.getvegetablesModelList();
        yesNoArrayListForVegetablesCard= Utils.getvegetableCardModelList();
        yesNoArrayListForJorda= Utils.getyesNoList();
        yesNoArrayListForModerateIntensityHeart= Utils.getyesNoList();
        yesNoArrayListForModerateRecreationalHeartt= Utils.getyesNoList();
        yesNoArrayListForReclinig= Utils.getyesNoList();
        yesNoArrayListForVigorousIntensityHeart= Utils.getyesNoList();
        yesNoArrayListForVigorousRecreationalHeart= Utils.getyesNoList();
        yesNoArrayListForSaltBuy= Utils.getSaltModelList();
        yesNoArrayListForTakingSalt= Utils.getyesNoList();
        yesNoArrayListForModerate= Utils.getModerateModelList();
        yesNoArrayListForModerateRecreational= Utils.getModerateRecreationalModelList();
        yesNoArrayListForTypicalVigorous= Utils.getTypicalVigorousModelList();
        yesNoArrayListForTypicalVigorousRecreation= Utils.getTypicalVigorousRecreationModelList();
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
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });
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
                smokeYesNo=yesNoArrayListForSmoke.get(position).getId();
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
                jordaYesNo=yesNoArrayListForJorda.get(position).getId();
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
        yesNoArrayAdapterWorkplace= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForWorkplace);
        yesNoArrayAdapterWorkplace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_workplace.setAdapter(yesNoArrayAdapterWorkplace);

        spinner_workplace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForWorkplace.get(position).getId());
                workplaceYesNo=yesNoArrayListForWorkplace.get(position).getId();
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
        yesNoArrayAdapterAlcohol= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForAlcohol);
        yesNoArrayAdapterAlcohol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_alcohol.setAdapter(yesNoArrayAdapterAlcohol);

        spinner_alcohol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForAlcohol.get(position).getId());
                alcoholYesNo=yesNoArrayListForAlcohol.get(position).getId();
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
        yesNoArrayAdapterFruits= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForFruits);
        yesNoArrayAdapterFruits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fruits.setAdapter(yesNoArrayAdapterFruits);

        spinner_fruits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForFruits.get(position).getId());
                typicalFruits=yesNoArrayListForFruits.get(position).getId();
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
        yesNoArrayAdapterFruitsCard= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForFruitsCard);
        yesNoArrayAdapterFruitsCard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fruits_card.setAdapter(yesNoArrayAdapterFruitsCard);

        spinner_fruits_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForFruitsCard.get(position).getId());
                fruitsShowCard=yesNoArrayListForFruitsCard.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initVegetablesSpinner() {
        yesNoArrayAdapterVegetables= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVegetables);
        yesNoArrayAdapterVegetables.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vegetables.setAdapter(yesNoArrayAdapterVegetables);

        spinner_vegetables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVegetables.get(position).getId());
                typicalVegetables=yesNoArrayListForVegetables.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initVegetablesCardSpinner() {
        yesNoArrayAdapterVegetablesCard= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVegetablesCard);
        yesNoArrayAdapterVegetablesCard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vegetables_card.setAdapter(yesNoArrayAdapterVegetablesCard);

        spinner_vegetables_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVegetablesCard.get(position).getId());
                vegetablesShowCard=yesNoArrayListForVegetablesCard.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initBuySaltSpinner() {
        yesNoArrayAdapterSaltBuy= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForSaltBuy);
        yesNoArrayAdapterSaltBuy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_salt_month.setAdapter(yesNoArrayAdapterSaltBuy);

        spinner_salt_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForSaltBuy.get(position).getId());
                saltyBuy=yesNoArrayListForSaltBuy.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initTalkingSaltSpinner() {
        yesNoArrayAdapterTakingSalt= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTakingSalt);
        yesNoArrayAdapterTakingSalt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_salt_meal.setAdapter(yesNoArrayAdapterTakingSalt);

        spinner_salt_meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTakingSalt.get(position).getId());
                takingSalt=yesNoArrayListForTakingSalt.get(position).getId();

                if (yesNoArrayListForTakingSalt.get(position).getId() == 1) {
                    linear_taking_meal.setVisibility(View.VISIBLE);
                }
                else {
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
        yesNoArrayAdapterVigorousIntensityHeart= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVigorousIntensityHeart);
        yesNoArrayAdapterVigorousIntensityHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heart_show_card.setAdapter(yesNoArrayAdapterVigorousIntensityHeart);

        spinner_heart_show_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVigorousIntensityHeart.get(position).getId());
                vigorousIntensity=yesNoArrayListForVigorousIntensityHeart.get(position).getId();

                if (yesNoArrayListForTakingSalt.get(position).getId() == 1) {
                    linear_heart_show_card.setVisibility(View.VISIBLE);
                    linear_heart_show_card_typical_week.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities.setVisibility(View.VISIBLE);
                }
                else {
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
        yesNoArrayAdapterModerateIntensityHeart= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateIntensityHeart);
        yesNoArrayAdapterModerateIntensityHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heart_show_moderate_card.setAdapter(yesNoArrayAdapterModerateIntensityHeart);

        spinner_heart_show_moderate_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateIntensityHeart.get(position).getId());
                moderateIntensity=yesNoArrayListForModerateIntensityHeart.get(position).getId();

                if (yesNoArrayListForModerateIntensityHeart.get(position).getId() == 1) {
                    linear_heart_show_moderate_card.setVisibility(View.VISIBLE);
                    linear_heart_show_moderate_card_typical.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day.setVisibility(View.VISIBLE);
                }
                else {
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
        yesNoArrayAdapterVigorousRecreationalHeart= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForVigorousRecreationalHeart);
        yesNoArrayAdapterVigorousRecreationalHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_recreational_activities.setAdapter(yesNoArrayAdapterVigorousRecreationalHeart);

        spinner_recreational_activities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForVigorousRecreationalHeart.get(position).getId());
                vigorousIntensityRecreational=yesNoArrayListForVigorousRecreationalHeart.get(position).getId();
                if (yesNoArrayListForVigorousRecreationalHeart.get(position).getId() == 1) {
                    linear_recreational_activities.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day.setVisibility(View.VISIBLE);
                }
                else {
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
        yesNoArrayAdapterModerateRecreationalHeart= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateRecreationalHeartt);
        yesNoArrayAdapterModerateRecreationalHeart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_moderate_intensity_recreational_activities.setAdapter(yesNoArrayAdapterModerateRecreationalHeart);

        spinner_moderate_intensity_recreational_activities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateRecreationalHeartt.get(position).getId());
                moderateIntensityRecreational=yesNoArrayListForModerateRecreationalHeartt.get(position).getId();
                if (yesNoArrayListForModerateRecreationalHeartt.get(position).getId() == 1) {
                    linear_moderate_intensity_recreational_activities.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day.setVisibility(View.VISIBLE);
                }
                else {
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
        yesNoArrayAdapterReclinig= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForReclinig);
        yesNoArrayAdapterReclinig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reclining.setAdapter(yesNoArrayAdapterReclinig);

        spinner_reclining.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForReclinig.get(position).getId());
                recliningActivities=yesNoArrayListForReclinig.get(position).getId();
                if (yesNoArrayListForReclinig.get(position).getId() == 1) {
                    linear_reclining.setVisibility(View.VISIBLE);

                }
                else {
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
        yesNoArrayAdapterTypicalVigorous =new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTypicalVigorous);
        yesNoArrayAdapterTypicalVigorous.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week.setAdapter(yesNoArrayAdapterTypicalVigorous);

        spinner_typical_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTypicalVigorous.get(position).getId());

                vigorousIntensityTypical=yesNoArrayListForTypicalVigorous.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }
    private void initModerateTypicalSpinner() {
        yesNoArrayAdapterModerate= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerate);
        yesNoArrayAdapterModerate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate.setAdapter(yesNoArrayAdapterModerate);

        spinner_typical_week_moderate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerate.get(position).getId());
                moderateIntensityTypical=yesNoArrayListForModerate.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initVigriousRecreationalTypicalSpinner() {
        yesNoArrayAdapterTypicalVigorousRecreation= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForTypicalVigorousRecreation);
        yesNoArrayAdapterTypicalVigorousRecreation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_recreational.setAdapter(yesNoArrayAdapterTypicalVigorousRecreation);

        spinner_typical_week_recreational.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForTypicalVigorousRecreation.get(position).getId());
                vigorousIntensityRecreationalTypical=yesNoArrayListForTypicalVigorousRecreation.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initModerateRecreationalTypicalSpinner() {
        yesNoArrayAdapterModerateRecreational= new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForModerateRecreational);
        yesNoArrayAdapterModerateRecreational.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typical_week_moderate_recreational.setAdapter(yesNoArrayAdapterModerateRecreational);

        spinner_typical_week_moderate_recreational.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForModerateRecreational.get(position).getId());
                moderateIntensityRecreationalTypical=yesNoArrayListForModerateRecreational.get(position).getId();
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
        if (pos == 8 && checkBoxOthers_moderate_recreational_others.isChecked())
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
        if (pos == 8 && checkBoxOthers_recreational.isChecked())
            return true;
        return false;
    }
    private boolean isModerateIntensityChecked(int pos) {


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
            MemberHabit memberHabit = new MemberHabit();
            int id= Common.memberMyselfRepository.maxValue();

            MemberMyself memberMyself=Common.memberMyselfRepository.getMemberMyselfNo(id);
            memberHabit.MemberMyselfPhoneNumber=memberMyself.MobileNumber;

            MemberHabit memberHabit1=Common.memberHabitRepository.getMemberHabitNo(memberMyself.MobileNumber);
            if (memberHabit1==null) {
                memberHabit.SmokeYesNo = smokeYesNo;
                if (smokeYesNo == 2) {

                } else {

                    try {
                        memberHabit.SmokeYesYears = Integer.parseInt(edit_smoke_years.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.SmokeYesYears = 0;
                    }
                    try {
                        memberHabit.SmokeYesPerday = Integer.parseInt(edit_smoke_stick.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.SmokeYesPerday = 0;
                    }


                }
                memberHabit.JordaYesNo = jordaYesNo;
                if (jordaYesNo == 2) {

                } else {
                    try {
                        memberHabit.JordaYears = Integer.parseInt(edit_jorda.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.JordaYears = 0;
                    }
                }
                memberHabit.WorkplaceYesNo = workplaceYesNo;
                if (workplaceYesNo == 2) {

                } else {
                    try {
                        memberHabit.WorkplaceYears = Integer.parseInt(edit_workplace.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.WorkplaceYears = 0;
                    }
                }
                memberHabit.AlcoholYesNo = alcoholYesNo;
                if (alcoholYesNo == 2) {

                } else {
                    try {
                        memberHabit.AlcoholYears = Integer.parseInt(edit_alcohol.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.AlcoholYears = 0;
                    }
                }
                memberHabit.FruitsTypicalWeek = typicalFruits;
                memberHabit.FruitsShowCard = fruitsShowCard;
                memberHabit.VegetablesTypicalWeek = typicalVegetables;
                memberHabit.VegetablesShowCard = vegetablesShowCard;
                memberHabit.SaltBuy = saltyBuy;
                memberHabit.TakingSalt = takingSalt;

                memberHabit.VigorousIntensityYesNo=vigorousIntensity;
                memberHabit.VigorousIntensityActivities=vigiriousIntensity();
                memberHabit.VigorousIntensityTypicalWeek=vigorousIntensityTypical;
                try {
                    memberHabit.VigorousIntensityTypicalDay = Integer.parseInt(edit_typical_day.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.VigorousIntensityTypicalDay = 0;
                }
                //////
                memberHabit.ModeratorIntensityYesNo=moderateIntensity;
                memberHabit.ModeratorIntensityActivities=moderateIntensity();
                memberHabit.ModeratorIntensityTypicalWeek=moderateIntensityTypical;
                try {
                    memberHabit.ModeratorIntensityTypicalDay = Integer.parseInt(edit_typical_day_moderate.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ModeratorIntensityTypicalDay = 0;
                }
                ////

                memberHabit.VigorousIntensityRecreationalYesNo=vigorousIntensityRecreational;
                memberHabit.VigorousIntensityRecreationalActivities=vigorousIntensityRecreational();
                memberHabit.VigorousIntensityRecreationalTypicalWeek=vigorousIntensityRecreationalTypical;
                try {
                    memberHabit.VigorousIntensityRecreationalTypicalDay = Integer.parseInt(edit_typical_day_recreational.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.VigorousIntensityRecreationalTypicalDay = 0;
                }
                ////
                memberHabit.ModeratorIntensityRecreationalYesNo=moderateIntensityRecreational;
                memberHabit.ModeratorIntensityRecreationalActivities=moderateIntensityRecreational();
                memberHabit.ModeratorIntensityRecreationalTypicalWeek=moderateIntensityRecreationalTypical;
                try {
                    memberHabit.ModeratorIntensityRecreationalTypicalDay = Integer.parseInt(edit_typical_day_moderate_recreational.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ModeratorIntensityRecreationalTypicalDay = 0;
                }
                memberHabit.ReclinigActivitiesYesNo=recliningActivities;
                try {
                    memberHabit.ReclinigActivitiesTypicalDay = Integer.parseInt(edit_yes_reclining.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ReclinigActivitiesTypicalDay = 0;
                }


                Common.memberHabitRepository.insertToMemberHabit(memberHabit);
                ((HouseholdHomeActivity) getActivity()).backForDetails();
            }
            else {
                memberHabit.id = memberHabit1.id;
                memberHabit.SmokeYesNo = smokeYesNo;
                if (smokeYesNo == 2) {

                } else {
                    try {
                        memberHabit.SmokeYesYears = Integer.parseInt(edit_smoke_years.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.SmokeYesYears = 0;
                    }
                    try {
                        memberHabit.SmokeYesPerday = Integer.parseInt(edit_smoke_stick.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.SmokeYesPerday = 0;
                    }
                }
                memberHabit.JordaYesNo = jordaYesNo;
                if (jordaYesNo == 2) {

                } else {
                    try {
                        memberHabit.JordaYears = Integer.parseInt(edit_jorda.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.JordaYears = 0;
                    }
                }
                memberHabit.WorkplaceYesNo = workplaceYesNo;
                if (workplaceYesNo == 2) {

                } else {
                    try {
                        memberHabit.WorkplaceYears = Integer.parseInt(edit_workplace.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.WorkplaceYears = 0;
                    }
                }
                memberHabit.AlcoholYesNo = alcoholYesNo;
                if (alcoholYesNo == 2) {

                } else {
                    try {
                        memberHabit.AlcoholYears = Integer.parseInt(edit_alcohol.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        memberHabit.AlcoholYears = 0;
                    }
                }
                memberHabit.FruitsTypicalWeek = typicalFruits;
                memberHabit.FruitsShowCard = fruitsShowCard;
                memberHabit.VegetablesTypicalWeek = typicalVegetables;
                memberHabit.VegetablesShowCard = vegetablesShowCard;
                memberHabit.SaltBuy = saltyBuy;
                memberHabit.TakingSalt = takingSalt;

                memberHabit.VigorousIntensityYesNo=vigorousIntensity;
                memberHabit.VigorousIntensityActivities=vigiriousIntensity();
                memberHabit.VigorousIntensityTypicalWeek=vigorousIntensityTypical;
                try {
                    memberHabit.VigorousIntensityTypicalDay = Integer.parseInt(edit_typical_day.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.VigorousIntensityTypicalDay = 0;
                }
                //////
                memberHabit.ModeratorIntensityYesNo=moderateIntensity;
                memberHabit.ModeratorIntensityActivities=moderateIntensity();
                memberHabit.ModeratorIntensityTypicalWeek=moderateIntensityTypical;
                try {
                    memberHabit.ModeratorIntensityTypicalDay = Integer.parseInt(edit_typical_day_moderate.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ModeratorIntensityTypicalDay = 0;
                }
                ////

                memberHabit.VigorousIntensityRecreationalYesNo=vigorousIntensityRecreational;
                memberHabit.VigorousIntensityRecreationalActivities=vigorousIntensityRecreational();
                memberHabit.VigorousIntensityRecreationalTypicalWeek=vigorousIntensityRecreationalTypical;
                try {
                    memberHabit.VigorousIntensityRecreationalTypicalDay = Integer.parseInt(edit_typical_day_recreational.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.VigorousIntensityRecreationalTypicalDay = 0;
                }
                ////
                memberHabit.ModeratorIntensityRecreationalYesNo=moderateIntensityRecreational;
                memberHabit.ModeratorIntensityRecreationalActivities=moderateIntensityRecreational();
                memberHabit.ModeratorIntensityRecreationalTypicalWeek=moderateIntensityRecreationalTypical;
                try {
                    memberHabit.ModeratorIntensityRecreationalTypicalDay = Integer.parseInt(edit_typical_day_moderate_recreational.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ModeratorIntensityRecreationalTypicalDay = 0;
                }
                memberHabit.ReclinigActivitiesYesNo=recliningActivities;
                try {
                    memberHabit.ReclinigActivitiesTypicalDay = Integer.parseInt(edit_yes_reclining.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    memberHabit.ReclinigActivitiesTypicalDay = 0;
                }

                Common.memberHabitRepository.updateMemberHabit(memberHabit);
                ((HouseholdHomeActivity) getActivity()).backForDetails();
            }

        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 2) {
            saveData();
        } else {
            HHCreateMemberFragment.prevPage(1);
        }
        return false;
    }
}

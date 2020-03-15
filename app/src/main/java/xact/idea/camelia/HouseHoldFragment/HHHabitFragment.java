package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
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

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Database.Model.MemberHabit;
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.Fragment.CCBloodPressureFragment;
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
    String frag;
    String update;

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

    private void initView() {
        relativeLayout = view.findViewById(R.id.relative);
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
        yesNoArrayListForSmoke = Utils.getyesNoList(mActivity);
        yesNoArrayListForWorkplace = Utils.getyesNoList(mActivity);
        yesNoArrayListForAlcohol = Utils.getyesNoList(mActivity);
        yesNoArrayListForFruits = Utils.getfruitsModelList();
        yesNoArrayListForFruitsCard = Utils.getfruitsCardModelList();
        yesNoArrayListForVegetables = Utils.getvegetablesModelList();
        yesNoArrayListForVegetablesCard = Utils.getvegetableCardModelList();
        yesNoArrayListForJorda = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerateIntensityHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerateRecreationalHeartt = Utils.getyesNoList(mActivity);
        yesNoArrayListForReclinig = Utils.getyesNoList(mActivity);
        yesNoArrayListForVigorousIntensityHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForVigorousRecreationalHeart = Utils.getyesNoList(mActivity);
        yesNoArrayListForSaltBuy = Utils.getSaltModelList();
        yesNoArrayListForTakingSalt = Utils.getyesNoList(mActivity);
        yesNoArrayListForModerate = Utils.getModerateModelList();
        yesNoArrayListForModerateRecreational = Utils.getModerateRecreationalModelList();
        yesNoArrayListForTypicalVigorous = Utils.getTypicalVigorousModelList();
        yesNoArrayListForTypicalVigorousRecreation = Utils.getTypicalVigorousRecreationModelList();
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
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        edit_typical_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TypicalDayTimePickerFragment();

                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        edit_typical_day_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TypicalDayModerateTimePickerFragment();

                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        edit_typical_day_recreational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TypicalDayRecreationalTimePickerFragment();

                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        edit_typical_day_moderate_recreational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new ModerateRecreationalTimePickerFragment();

                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        show();
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
                        edit_smoke_years.setText(questions1a.answer);
                        edit_smoke_stick.setText(questions1b.answer);
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
        int YesNob = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q43", update);
        Questions questions3 = Common.qustionsRepository.getQuestions("Q43b", update);
        Questions questions2 = Common.qustionsRepository.getQuestions("Q43a", update);


        try {
            YesNob = Integer.parseInt(questions3.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNob = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }

        if (questions2 != null) {
            String diac = questions2.answer;
            String[] values = diac.split(",");
            for (String s : values) {
                Log.e("fdf", "dfdf" + s);
                if (s.equals("1")) {
                    checkBoxHeaveyLoad.setChecked(true);
                } else if (s.equals("2")) {
                    checkBoxDigging.setChecked(true);
                } else if (s.equals("3")) {
                    checkBoxFurniture.setChecked(true);
                } else if (s.equals("4")) {
                    checkBoxPickingCrops.setChecked(true);
                } else if (s.equals("5")) {
                    checkBoxCuttingTrees.setChecked(true);
                } else if (s.equals("6")) {
                    checkBoxBreakUpPaddy.setChecked(true);
                } else if (s.equals("7")) {
                    checkBoxDrivingRickshaw.setChecked(true);
                } else if (s.equals("8")) {
                    checkBoxFishing.setChecked(true);
                } else if (s.equals("9")) {
                    checkBoxPlouging.setChecked(true);
                } else if (s.equals("10")) {
                    checkBoxHeaveyConstructionWork.setChecked(true);
                } else if (s.equals("11")) {
                    checkBoxHeaveyGoods.setChecked(true);
                } else if (s.equals("12")) {
                    checkBoxHeaveyGoodsHead.setChecked(true);
                } else if (s.equals("13")) {
                    checkBoxSoldDigging.setChecked(true);
                } else if (s.equals("14")) {
                    checkBoxWashing.setChecked(true);
                } else if (s.equals("15")) {
                    checkBoxStepping.setChecked(true);
                } else if (s.equals("16")) {
                    checkBoxOthers.setChecked(true);
                }

            }
        }


        if (YesNob != 0) {
            for (int i = 0; i < yesNoArrayListForTypicalVigorous.size(); i++) {
                if (yesNoArrayListForTypicalVigorous.get(i).getId() == YesNob) {
                    spinner_typical_week.setSelection(i);
                }
            }
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q43c", update);
                    edit_typical_day.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_typical_day.setText("");
                }
            }

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
        Questions questions3 = Common.qustionsRepository.getQuestions("Q44b", update);
        Questions questions2 = Common.qustionsRepository.getQuestions("Q44a", update);


        try {
            YesNob = Integer.parseInt(questions3.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNob = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }

        if (questions2 != null) {
            String diac = questions2.answer;
            String[] values = diac.split(",");
            for (String s : values) {
                Log.e("fdf", "dfdf" + s);
                if (s.equals("1")) {
                    checkBoxHouseHoldWork.setChecked(true);
                } else if (s.equals("2")) {
                    checkBoxGardening.setChecked(true);
                } else if (s.equals("3")) {
                    checkBoxMilkingCows.setChecked(true);
                } else if (s.equals("4")) {
                    checkBoxCultivatingLand.setChecked(true);
                } else if (s.equals("5")) {
                    checkBoxPlantingHarvest.setChecked(true);
                } else if (s.equals("6")) {
                    checkBoxWeavingCloth.setChecked(true);
                } else if (s.equals("7")) {
                    checkBoxWashingCloths.setChecked(true);
                } else if (s.equals("8")) {
                    checkBoxRearing.setChecked(true);
                } else if (s.equals("9")) {
                    checkBoxMixingCement.setChecked(true);
                } else if (s.equals("10")) {
                    checkBoxWoodWork.setChecked(true);
                } else if (s.equals("11")) {
                    checkBoxDrawingWater.setChecked(true);
                } else if (s.equals("12")) {
                    checkBoxCarryingLightWeight.setChecked(true);
                } else if (s.equals("13")) {
                    checkBoxWashingCloths1.setChecked(true);
                } else if (s.equals("14")) {
                    checkBoxGardening1.setChecked(true);
                } else if (s.equals("15")) {
                    checkBoxMilkingCows1.setChecked(true);
                } else if (s.equals("16")) {
                    checkBoxRoping.setChecked(true);
                } else if (s.equals("17")) {
                    checkBoxFarming.setChecked(true);
                } else if (s.equals("18")) {
                    checkBoxParlour.setChecked(true);
                } else if (s.equals("19")) {
                    checkBoxCloth.setChecked(true);
                } else if (s.equals("20")) {
                    checkBoxHouseHoldWork1.setChecked(true);
                } else if (s.equals("21")) {
                    checkBoxOthers1.setChecked(true);
                }
            }
        }


        if (YesNob != 0) {
            for (int i = 0; i < yesNoArrayListForModerate.size(); i++) {
                if (yesNoArrayListForModerate.get(i).getId() == YesNob) {
                    spinner_typical_week_moderate.setSelection(i);
                }
            }
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q44c", update);
                    edit_typical_day_moderate.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_typical_day_moderate.setText("");
                }
            }

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
        Questions questions3 = Common.qustionsRepository.getQuestions("Q45b", update);
        Questions questions2 = Common.qustionsRepository.getQuestions("Q45a", update);


        try {
            YesNob = Integer.parseInt(questions3.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNob = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
        if (questions2 != null) {
            String diac = questions2.answer;
            String[] values = diac.split(",");
            for (String s : values) {
                Log.e("fdf", "dfdf" + s);
                if (s.equals("1")) {
                    checkBoxRunning.setChecked(true);
                } else if (s.equals("2")) {
                    checkBoxBadminton.setChecked(true);
                } else if (s.equals("3")) {
                    checkBoxSwimming.setChecked(true);
                } else if (s.equals("4")) {
                    checkBoxHockey.setChecked(true);
                } else if (s.equals("5")) {
                    checkBoxHadudu.setChecked(true);
                } else if (s.equals("6")) {
                    checkBoxFootbal.setChecked(true);
                } else if (s.equals("7")) {
                    checkBoxVolleyball.setChecked(true);
                } else if (s.equals("8")) {
                    checkBoxTenis.setChecked(true);
                } else if (s.equals("9")) {
                    checkBoxOthers_recreational.setChecked(true);
                }
            }
        }


        if (YesNob != 0) {
            for (int i = 0; i < yesNoArrayListForTypicalVigorousRecreation.size(); i++) {
                if (yesNoArrayListForTypicalVigorousRecreation.get(i).getId() == YesNob) {
                    spinner_typical_week_recreational.setSelection(i);
                }
            }
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q45c", update);
                    edit_typical_day_recreational.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_typical_day_recreational.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForVigorousRecreationalHeart.size(); i++) {
                if (yesNoArrayListForVigorousRecreationalHeart.get(i).getId() == div) {
                    spinner_recreational_activities.setSelection(i);
                }
            }
        }
    }

    private void initModerateIntensityRecreational() {
        int YesNo = 0;
        int YesNob = 0;
        Questions questions1 = Common.qustionsRepository.getQuestions("Q46", update);
        Questions questions3 = Common.qustionsRepository.getQuestions("Q46b", update);
        Questions questions2 = Common.qustionsRepository.getQuestions("Q46a", update);


        try {
            YesNob = Integer.parseInt(questions3.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNob = -1;
        } catch (Exception e) {
            e.printStackTrace();
            YesNob = -1;
        }
        try {
            YesNo = Integer.parseInt(questions1.answer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            YesNo = -1;
        }

        if (questions2 != null) {
            String diac = questions2.answer;
            String[] values = diac.split(",");
            for (String s : values) {
                Log.e("fdf", "dfdf" + s);
                if (s.equals("1")) {
                    checkBoxFastWalking.setChecked(true);
                } else if (s.equals("2")) {
                    checkBoxJogging.setChecked(true);
                } else if (s.equals("3")) {
                    checkBoxCycling.setChecked(true);
                } else if (s.equals("4")) {
                    checkBoxCricket.setChecked(true);
                } else if (s.equals("5")) {
                    checkBoxYoga.setChecked(true);
                } else if (s.equals("6")) {
                    checkBoxAerobics.setChecked(true);
                } else if (s.equals("7")) {
                    checkBoxExercise.setChecked(true);
                } else if (s.equals("8")) {
                    checkBoxOthersDancing.setChecked(true);
                } else if (s.equals("9")) {
                    checkBoxOthers_moderate_recreational_others.setChecked(true);
                }

            }
        }


        if (YesNob != 0) {
            for (int i = 0; i < yesNoArrayListForModerateRecreational.size(); i++) {
                if (yesNoArrayListForModerateRecreational.get(i).getId() == YesNob) {
                    spinner_typical_week_moderate_recreational.setSelection(i);
                }
            }
        }
        if (YesNo != 0) {
            int div = YesNo;

            if (div == 1) {
                try {
                    Questions questions1a = Common.qustionsRepository.getQuestions("Q46c", update);
                    edit_typical_day_moderate_recreational.setText(questions1a.answer);
                } catch (Exception e) {
                    edit_typical_day_moderate_recreational.setText("");
                }
            }

            for (int i = 0; i < yesNoArrayListForModerateRecreationalHeartt.size(); i++) {
                if (yesNoArrayListForModerateRecreationalHeartt.get(i).getId() == div) {
                    spinner_moderate_intensity_recreational_activities.setSelection(i);
                }
            }
        }
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
                    linear_heart_show_card_typical_week.setVisibility(View.VISIBLE);
                    linear_heart_show_card_activities.setVisibility(View.VISIBLE);
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
                    linear_heart_show_moderate_card_typical.setVisibility(View.VISIBLE);
                    linear_heart_show_card_day.setVisibility(View.VISIBLE);
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
                    linear_recreational_activities_typical.setVisibility(View.VISIBLE);
                    linear_recreational_activities_typical_day.setVisibility(View.VISIBLE);
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
                    linear_moderate_intensity_recreational_activities_typical.setVisibility(View.VISIBLE);
                    linear_moderate_intensity_recreational_activities_typical_day.setVisibility(View.VISIBLE);
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
            MemberHabit memberHabitsFor = Common.memberHabitRepository.getMemberHabit(update);
            MemberHabit memberHabit = new MemberHabit();
            if (memberHabitsFor != null) {
                if (smokeYesNo == 2) {
                    Questions questions1 = Common.qustionsRepository.getQuestions("Q32", update);

                    if (questions1 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.id = questions1.id;
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date);
                    Questions questionsFor711 = Common.qustionsRepository.getQuestions("Q32", update);
                    if (questionsFor711 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q32";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor711.id;
                        Common.qustionsRepository.updateQuestions(questions);

                    }


                    ////
                    Questions questionsFor451 = Common.qustionsRepository.getQuestions("Q32a", update);
                    if (questionsFor451 == null) {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q32a";
                        questions1.member_id = memberHabitsFor.MemberId;
                        questions1.answer = edit_smoke_years.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q32a";
                        questions1.member_id = memberHabitsFor.MemberId;
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
                        questions2.member_id = memberHabitsFor.MemberId;
                        questions2.answer = edit_smoke_stick.getText().toString();
                        questions2.date = currentDate;
                        questions2.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions2);
                    } else {
                        Questions questions2 = new Questions();
                        questions2.type = "behavioral";
                        questions2.question = "Q32b";
                        questions2.member_id = memberHabitsFor.MemberId;
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
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor1988.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date);

                    Questions questionsFor132 = Common.qustionsRepository.getQuestions("Q33", update);
                    if (questionsFor132 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q33";
                        questions.member_id = memberHabitsFor.MemberId;
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
                        questions1.member_id = memberHabitsFor.MemberId;
                        questions1.answer = edit_jorda.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q33a";
                        questions1.member_id = memberHabitsFor.MemberId;
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
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor1778.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date);

                    Questions questionsFor158 = Common.qustionsRepository.getQuestions("Q34", update);
                    if (questionsFor158 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q34";
                        questions.member_id = memberHabitsFor.MemberId;
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
                        questions1.member_id = memberHabitsFor.MemberId;
                        questions1.answer = edit_workplace.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q34a";
                        questions1.member_id = memberHabitsFor.MemberId;
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
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;

                        Common.qustionsRepository.insertToQuestions(questions);
                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "2";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        questions.id = questionsFor781.id;
                        Common.qustionsRepository.updateQuestions(questions);
                    }

                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date);

                    Questions questionsFor151 = Common.qustionsRepository.getQuestions("Q35", update);
                    if (questionsFor151 == null) {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.MemberId;
                        questions.answer = "1";

                        questions.date = currentDate;
                        questions.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions);

                    } else {
                        Questions questions = new Questions();
                        questions.type = "behavioral";
                        questions.question = "Q35";
                        questions.member_id = memberHabitsFor.MemberId;
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
                        questions1.member_id = memberHabitsFor.MemberId;
                        questions1.answer = edit_alcohol.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions1);
                    } else {
                        Questions questions1 = new Questions();
                        questions1.type = "behavioral";
                        questions1.question = "Q35a";
                        questions1.member_id = memberHabitsFor.MemberId;
                        questions1.answer = edit_alcohol.getText().toString();
                        questions1.date = currentDate;
                        questions1.master_id = memberHabitsFor.id;
                        questions1.id = questionsFor561.id;
                        Common.qustionsRepository.updateQuestions(questions1);
                    }

                }


                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(date);
                Questions questionsFor167 = Common.qustionsRepository.getQuestions("Q36", update);
                if (questionsFor167 == null) {
                    Questions questions5 = new Questions();
                    questions5.type = "behavioral";
                    questions5.question = "Q36";
                    questions5.member_id = memberHabitsFor.MemberId;
                    questions5.answer = String.valueOf(typicalFruits);

                    questions5.date = currentDate;
                    questions5.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions5);
                } else {
                    Questions questions5 = new Questions();
                    questions5.type = "behavioral";
                    questions5.question = "Q36";
                    questions5.member_id = memberHabitsFor.MemberId;
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
                    questions6.member_id = memberHabitsFor.MemberId;
                    questions6.answer = String.valueOf(fruitsShowCard);
                    questions6.date = currentDate;
                    questions6.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions6);
                } else {
                    Questions questions6 = new Questions();
                    questions6.type = "behavioral";
                    questions6.question = "Q37";
                    questions6.member_id = memberHabitsFor.MemberId;
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
                    questions7.member_id = memberHabitsFor.MemberId;
                    questions7.answer = String.valueOf(typicalVegetables);
                    questions7.date = currentDate;
                    questions7.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions7);
                } else {
                    Questions questions7 = new Questions();
                    questions7.type = "behavioral";
                    questions7.question = "Q38";
                    questions7.member_id = memberHabitsFor.MemberId;
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
                    questions8.member_id = memberHabitsFor.MemberId;
                    questions8.answer = String.valueOf(vegetablesShowCard);
                    questions8.date = currentDate;
                    questions8.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions8);
                } else {
                    Questions questions8 = new Questions();
                    questions8.type = "behavioral";
                    questions8.question = "Q39";
                    questions8.member_id = memberHabitsFor.MemberId;
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
                    questions9.member_id = memberHabitsFor.MemberId;
                    questions9.answer = String.valueOf(vegetablesShowCard);
                    questions9.date = currentDate;
                    questions9.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions9);
                } else {
                    Questions questions9 = new Questions();
                    questions9.type = "behavioral";
                    questions9.question = "Q39";
                    questions9.member_id = memberHabitsFor.MemberId;
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
                    questions10.member_id = memberHabitsFor.MemberId;
                    questions10.answer = String.valueOf(saltyBuy);
                    questions10.date = currentDate;
                    questions10.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions10);
                } else {
                    Questions questions10 = new Questions();
                    questions10.type = "behavioral";
                    questions10.question = "Q41";
                    questions10.member_id = memberHabitsFor.MemberId;
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
                    questions11.member_id = memberHabitsFor.MemberId;
                    questions11.answer = String.valueOf(takingSalt);
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions11);
                } else {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42";
                    questions11.member_id = memberHabitsFor.MemberId;
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
                    questions11.member_id = memberHabitsFor.MemberId;
                    questions11.answer = edit_yes_extra_salt.getText().toString();
                    questions11.date = currentDate;
                    questions11.master_id = memberHabitsFor.id;
                    Common.qustionsRepository.insertToQuestions(questions11);
                } else {
                    Questions questions11 = new Questions();
                    questions11.type = "behavioral";
                    questions11.question = "Q42";
                    questions11.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q43";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor6.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                    Questions questionsFor8 = Common.qustionsRepository.getQuestions("Q43a", update);
                    if (questionsFor8 == null) {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q43a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = vigiriousIntensity();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions13);
                    } else {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q43a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = vigiriousIntensity();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        questions13.id = questionsFor8.id;
                        Common.qustionsRepository.updateQuestions(questions13);
                    }


                    Questions questionsFor9 = Common.qustionsRepository.getQuestions("Q43b", update);
                    if (questionsFor9 == null) {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q43b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(vigorousIntensityTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions14);
                    } else {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q43b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(vigorousIntensityTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        questions14.id = questionsFor9.id;
                        Common.qustionsRepository.updateQuestions(questions14);
                    }

                    Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q43c", update);
                    if (questionsFor19 == null) {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q43c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q43c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        questions15.id = questionsFor19.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }

                ////
                if (moderateIntensity == 2) {
                    Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q44", update);
                    if (questionsFor15 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q44";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor111.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                    Questions questionsFor91 = Common.qustionsRepository.getQuestions("Q44a", update);
                    if (questionsFor91 == null) {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q44a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = moderateIntensity();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions13);
                    } else {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q44a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = moderateIntensity();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        questions13.id = questionsFor91.id;
                        Common.qustionsRepository.updateQuestions(questions13);
                    }

                    Questions questionsFor89 = Common.qustionsRepository.getQuestions("Q44b", update);
                    if (questionsFor89 == null) {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q44b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(moderateIntensityTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions14);
                    } else {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q44b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(moderateIntensityTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        questions14.id = questionsFor89.id;
                        Common.qustionsRepository.updateQuestions(questions14);
                    }

                    Questions questionsFor67 = Common.qustionsRepository.getQuestions("Q44c", update);
                    if (questionsFor67 == null) {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q44c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day_moderate.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q44c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day_moderate.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        questions15.id = questionsFor67.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }


                ////
                if (vigorousIntensityRecreational == 2) {
                    Questions questionsFor78 = Common.qustionsRepository.getQuestions("Q45", update);
                    if (questionsFor78 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q45";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        questions12.id = questionsFor616.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }


                    Questions questionsFor51 = Common.qustionsRepository.getQuestions("Q45a", update);
                    if (questionsFor51 == null) {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q45a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = vigorousIntensityRecreational();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions13);
                    } else {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q45a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = vigorousIntensityRecreational();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        questions13.id = questionsFor51.id;
                        Common.qustionsRepository.updateQuestions(questions13);
                    }


                    Questions questionsFor18 = Common.qustionsRepository.getQuestions("Q45b", update);
                    if (questionsFor18 == null) {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q45b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions14);
                    } else {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q45b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        questions14.id = questionsFor18.id;
                        Common.qustionsRepository.updateQuestions(questions14);
                    }

                    Questions questionsFor241 = Common.qustionsRepository.getQuestions("Q45c", update);
                    if (questionsFor241 == null) {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q45c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day_recreational.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q45c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day_recreational.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        questions15.id = questionsFor241.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }


                if (moderateIntensityRecreational == 2) {
                    Questions questionsFor31 = Common.qustionsRepository.getQuestions("Q46", update);
                    if (questionsFor31 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "2";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q46";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.id = questionsFor21.id;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions12);
                    }

                    Questions questionsFor178 = Common.qustionsRepository.getQuestions("Q46a", update);
                    if (questionsFor178 == null) {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q46a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.answer = moderateIntensityRecreational();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions13);
                    } else {
                        Questions questions13 = new Questions();
                        questions13.type = "behavioral";
                        questions13.question = "Q46a";
                        questions13.member_id = memberHabitsFor.MemberId;
                        questions13.id = questionsFor178.id;
                        questions13.answer = moderateIntensityRecreational();
                        questions13.date = currentDate;
                        questions13.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions13);
                    }

                    Questions questionsFor17 = Common.qustionsRepository.getQuestions("Q46b", update);
                    if (questionsFor17 == null) {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q46b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions14);
                    } else {
                        Questions questions14 = new Questions();
                        questions14.type = "behavioral";
                        questions14.question = "Q46b";
                        questions14.member_id = memberHabitsFor.MemberId;
                        questions14.id = questionsFor17.id;
                        questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
                        questions14.date = currentDate;
                        questions14.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions14);
                    }

                    Questions questionsFor199 = Common.qustionsRepository.getQuestions("Q46c", update);
                    if (questionsFor199 == null) {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q46c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.type = "behavioral";
                        questions15.question = "Q46c";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.id = questionsFor199.id;
                        questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }


                if (recliningActivities == 2) {
                    Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q48", update);
                    if (questionsFor19 == null) {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions12.member_id = memberHabitsFor.MemberId;
                        questions12.answer = "1";
                        questions12.date = currentDate;
                        questions12.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions12);
                    } else {
                        Questions questions12 = new Questions();
                        questions12.id = questionsFor12.id;
                        questions12.type = "behavioral";
                        questions12.question = "Q48";
                        questions12.member_id = memberHabitsFor.MemberId;
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
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_yes_reclining.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.insertToQuestions(questions15);
                    } else {
                        Questions questions15 = new Questions();
                        questions15.id = questionsFor15.id;
                        questions15.type = "behavioral";
                        questions15.question = "Q48a";
                        questions15.member_id = memberHabitsFor.MemberId;
                        questions15.answer = edit_yes_reclining.getText().toString();
                        questions15.date = currentDate;
                        questions15.master_id = memberHabitsFor.id;
                        Common.qustionsRepository.updateQuestions(questions15);
                    }

                }
                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                memberHabit.household_uniqe_id = memberHabitsFor.household_uniqe_id;
                memberHabit.member_unique_code = "";
                memberHabit.member_national_id = String.valueOf(memberHabitsFor.member_national_id);
                Common.memberHabitRepository.updateMemberHabit(memberHabit);
                if (frag.equals("frag")) {
                    ((CCUserHomeActivity) getActivity()).backForDetails();
                } else {
                    ((HouseholdHomeActivity) getActivity()).backForDetails();
                }
            } else {
                int id = Common.memberMyselfRepository.maxValue();

                MemberMyself memberMyself = Common.memberMyselfRepository.getMemberMyselfNo(id);
                memberHabit.MemberId = memberMyself.MemberId;
                memberHabit.household_uniqe_id = memberMyself.UniqueId;
                memberHabit.member_unique_code = "";
                memberHabit.member_national_id = String.valueOf(memberMyself.NationalId);
                MemberHabit memberHabits = Common.memberHabitRepository.getMemberHabitNo(memberMyself.MemberId);
                if (smokeYesNo == -1 || jordaYesNo == -1 || workplaceYesNo == -1 || alcoholYesNo == -1 || takingSalt == -1 || vigorousIntensity == -1 || moderateIntensity == -1 || vigorousIntensityRecreational == -1 || moderateIntensityRecreational == -1 || recliningActivities == -1) {
                    Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                    if (memberHabits == null) {

                        if (smokeYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q32";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {


                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q32";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q32a";
                            questions1.member_id = memberMyself.MemberId;
                            questions1.answer = edit_smoke_years.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);

                            Questions questions2 = new Questions();
                            questions2.type = "behavioral";
                            questions2.question = "Q32b";
                            questions2.member_id = memberMyself.MemberId;
                            questions2.answer = edit_smoke_stick.getText().toString();
                            questions2.date = currentDate;
                            questions2.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions2);

                        }

                        if (jordaYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q33";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
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
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q33a";
                            questions1.member_id = memberMyself.MemberId;
                            questions1.answer = edit_jorda.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }

                        if (workplaceYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q34";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {

                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q34";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q34a";
                            questions1.member_id = memberMyself.MemberId;
                            questions1.answer = edit_workplace.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }
                        if (alcoholYesNo == 2) {
                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q35";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "2";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);
                        } else {

                            Questions questions = new Questions();
                            questions.type = "behavioral";
                            questions.question = "Q35";
                            questions.member_id = memberMyself.MemberId;
                            questions.answer = "1";
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            questions.date = currentDate;
                            questions.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions);

                            ////
                            Questions questions1 = new Questions();
                            questions1.type = "behavioral";
                            questions1.question = "Q35a";
                            questions1.member_id = memberMyself.MemberId;
                            questions1.answer = edit_alcohol.getText().toString();
                            questions1.date = currentDate;
                            questions1.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions1);
                        }


                        Questions questions5 = new Questions();
                        questions5.type = "behavioral";
                        questions5.question = "Q36";
                        questions5.member_id = memberMyself.MemberId;
                        questions5.answer = String.valueOf(typicalFruits);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        questions5.date = currentDate;
                        questions5.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions5);
                        ///


                        Questions questions6 = new Questions();
                        questions6.type = "behavioral";
                        questions6.question = "Q37";
                        questions6.member_id = memberMyself.MemberId;
                        questions6.answer = String.valueOf(fruitsShowCard);
                        questions6.date = currentDate;
                        questions6.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions6);

                        ///


                        Questions questions7 = new Questions();
                        questions7.type = "behavioral";
                        questions7.question = "Q38";
                        questions7.member_id = memberMyself.MemberId;
                        questions7.answer = String.valueOf(typicalVegetables);
                        questions7.date = currentDate;
                        questions7.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions7);

                        ///


                        Questions questions8 = new Questions();
                        questions8.type = "behavioral";
                        questions8.question = "Q39";
                        questions8.member_id = memberMyself.MemberId;
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
                        questions10.member_id = memberMyself.MemberId;
                        questions10.answer = String.valueOf(saltyBuy);
                        questions10.date = currentDate;
                        questions10.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions10);


                        ///

                        Questions questions311 = new Questions();
                        questions311.type = "behavioral";
                        questions311.question = "Q42a";
                        questions311.member_id = memberMyself.MemberId;
                        questions311.answer = edit_yes_extra_salt.getText().toString();
                        questions311.date = currentDate;
                        questions311.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions311);


                        Questions questions11 = new Questions();
                        questions11.type = "behavioral";
                        questions11.question = "Q42";
                        questions11.member_id = memberMyself.MemberId;
                        questions11.answer = String.valueOf(takingSalt);
                        questions11.date = currentDate;
                        questions11.master_id = memberMyself.id;
                        Common.qustionsRepository.insertToQuestions(questions11);


                        if (vigorousIntensity == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q43";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q43";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

                            Questions questions13 = new Questions();
                            questions13.type = "behavioral";
                            questions13.question = "Q43a";
                            questions13.member_id = memberMyself.MemberId;
                            questions13.answer = vigiriousIntensity();
                            questions13.date = currentDate;
                            questions13.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions13);
                            Questions questions14 = new Questions();
                            questions14.type = "behavioral";
                            questions14.question = "Q43b";
                            questions14.member_id = memberMyself.MemberId;
                            questions14.answer = String.valueOf(vigorousIntensityTypical);
                            questions14.date = currentDate;
                            questions14.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions14);

                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q43c";
                            questions15.member_id = memberMyself.MemberId;
                            questions15.answer = edit_typical_day.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }

                        ////
                        if (moderateIntensity == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q44";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q44";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

                            Questions questions13 = new Questions();
                            questions13.type = "behavioral";
                            questions13.question = "Q44a";
                            questions13.member_id = memberMyself.MemberId;
                            questions13.answer = moderateIntensity();
                            questions13.date = currentDate;
                            questions13.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions13);
                            Questions questions14 = new Questions();
                            questions14.type = "behavioral";
                            questions14.question = "Q44b";
                            questions14.member_id = memberMyself.MemberId;
                            questions14.answer = String.valueOf(moderateIntensityTypical);
                            questions14.date = currentDate;
                            questions14.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions14);

                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q44c";
                            questions15.member_id = memberMyself.MemberId;
                            questions15.answer = edit_typical_day_moderate.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }


                        ////
                        if (vigorousIntensityRecreational == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q45";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q45";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

                            Questions questions13 = new Questions();
                            questions13.type = "behavioral";
                            questions13.question = "Q45a";
                            questions13.member_id = memberMyself.MemberId;
                            questions13.answer = vigorousIntensityRecreational();
                            questions13.date = currentDate;
                            questions13.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions13);
                            Questions questions14 = new Questions();
                            questions14.type = "behavioral";
                            questions14.question = "Q45b";
                            questions14.member_id = memberMyself.MemberId;
                            questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
                            questions14.date = currentDate;
                            questions14.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions14);

                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q45c";
                            questions15.member_id = memberMyself.MemberId;
                            questions15.answer = edit_typical_day_recreational.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }


                        if (moderateIntensityRecreational == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q46";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q46";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);

                            Questions questions13 = new Questions();
                            questions13.type = "behavioral";
                            questions13.question = "Q46a";
                            questions13.member_id = memberMyself.MemberId;
                            questions13.answer = moderateIntensityRecreational();
                            questions13.date = currentDate;
                            questions13.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions13);

                            Questions questions14 = new Questions();
                            questions14.type = "behavioral";
                            questions14.question = "Q46b";
                            questions14.member_id = memberMyself.MemberId;
                            questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
                            questions14.date = currentDate;
                            questions14.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions14);

                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q46c";
                            questions15.member_id = memberMyself.MemberId;
                            questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }


                        if (recliningActivities == 2) {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q48";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "2";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);
                        } else {
                            Questions questions12 = new Questions();
                            questions12.type = "behavioral";
                            questions12.question = "Q48";
                            questions12.member_id = memberMyself.MemberId;
                            questions12.answer = "1";
                            questions12.date = currentDate;
                            questions12.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions12);


                            Questions questions15 = new Questions();
                            questions15.type = "behavioral";
                            questions15.question = "Q48a";
                            questions15.member_id = memberMyself.MemberId;
                            questions15.answer = edit_yes_reclining.getText().toString();
                            questions15.date = currentDate;
                            questions15.master_id = memberMyself.id;
                            Common.qustionsRepository.insertToQuestions(questions15);
                        }
                        SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
                        Common.memberHabitRepository.insertToMemberHabit(memberHabit);

                        showInfoDialog(mActivity, memberMyself.MemberId);

                    } else {
                        memberHabit.id = memberHabits.id;
                        if (smokeYesNo == 2) {
                            Questions questions1 = Common.qustionsRepository.getQuestions("Q32", update);

                            if (questions1 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.id = questions1.id;
                                questions.question = "Q32";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);
                            Questions questionsFor711 = Common.qustionsRepository.getQuestions("Q32", update);
                            if (questionsFor711 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q32";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor711.id;
                                Common.qustionsRepository.insertToQuestions(questions);

                            }


                            ////
                            Questions questionsFor451 = Common.qustionsRepository.getQuestions("Q32a", update);
                            if (questionsFor451 == null) {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q32a";
                                questions1.member_id = memberHabits.MemberId;
                                questions1.answer = edit_smoke_years.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q32a";
                                questions1.member_id = memberHabits.MemberId;
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
                                questions2.member_id = memberMyself.MemberId;
                                questions2.answer = edit_smoke_stick.getText().toString();
                                questions2.date = currentDate;
                                questions2.master_id = memberMyself.id;
                                Common.qustionsRepository.insertToQuestions(questions2);
                            } else {
                                Questions questions2 = new Questions();
                                questions2.type = "behavioral";
                                questions2.question = "Q32b";
                                questions2.member_id = memberMyself.MemberId;
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
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor1988.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor132 = Common.qustionsRepository.getQuestions("Q33", update);
                            if (questionsFor132 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q33";
                                questions.member_id = memberHabits.MemberId;
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
                                questions1.member_id = memberHabits.MemberId;
                                questions1.answer = edit_jorda.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q33a";
                                questions1.member_id = memberHabits.MemberId;
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
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor1778.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor158 = Common.qustionsRepository.getQuestions("Q34", update);
                            if (questionsFor158 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q34";
                                questions.member_id = memberHabits.MemberId;
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
                                questions1.member_id = memberHabits.MemberId;
                                questions1.answer = edit_workplace.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q34a";
                                questions1.member_id = memberHabits.MemberId;
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
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;

                                Common.qustionsRepository.insertToQuestions(questions);
                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "2";
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(System.currentTimeMillis());
                                String currentDate = formatter.format(date);
                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                questions.id = questionsFor781.id;
                                Common.qustionsRepository.updateQuestions(questions);
                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = new Date(System.currentTimeMillis());
                            String currentDate = formatter.format(date);

                            Questions questionsFor151 = Common.qustionsRepository.getQuestions("Q35", update);
                            if (questionsFor151 == null) {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.MemberId;
                                questions.answer = "1";

                                questions.date = currentDate;
                                questions.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions);

                            } else {
                                Questions questions = new Questions();
                                questions.type = "behavioral";
                                questions.question = "Q35";
                                questions.member_id = memberHabits.MemberId;
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
                                questions1.member_id = memberHabits.MemberId;
                                questions1.answer = edit_alcohol.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions1);
                            } else {
                                Questions questions1 = new Questions();
                                questions1.type = "behavioral";
                                questions1.question = "Q35a";
                                questions1.member_id = memberHabits.MemberId;
                                questions1.answer = edit_alcohol.getText().toString();
                                questions1.date = currentDate;
                                questions1.master_id = memberHabits.id;
                                questions1.id = questionsFor561.id;
                                Common.qustionsRepository.updateQuestions(questions1);
                            }

                        }


                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        Questions questionsFor167 = Common.qustionsRepository.getQuestions("Q36", update);
                        if (questionsFor167 == null) {
                            Questions questions5 = new Questions();
                            questions5.type = "behavioral";
                            questions5.question = "Q36";
                            questions5.member_id = memberHabits.MemberId;
                            questions5.answer = String.valueOf(typicalFruits);

                            questions5.date = currentDate;
                            questions5.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions5);
                        } else {
                            Questions questions5 = new Questions();
                            questions5.type = "behavioral";
                            questions5.question = "Q36";
                            questions5.member_id = memberHabits.MemberId;
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
                            questions6.member_id = memberHabits.MemberId;
                            questions6.answer = String.valueOf(fruitsShowCard);
                            questions6.date = currentDate;
                            questions6.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions6);
                        } else {
                            Questions questions6 = new Questions();
                            questions6.type = "behavioral";
                            questions6.question = "Q37";
                            questions6.member_id = memberHabits.MemberId;
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
                            questions7.member_id = memberHabits.MemberId;
                            questions7.answer = String.valueOf(typicalVegetables);
                            questions7.date = currentDate;
                            questions7.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions7);
                        } else {
                            Questions questions7 = new Questions();
                            questions7.type = "behavioral";
                            questions7.question = "Q38";
                            questions7.member_id = memberHabits.MemberId;
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
                            questions8.member_id = memberHabits.MemberId;
                            questions8.answer = String.valueOf(vegetablesShowCard);
                            questions8.date = currentDate;
                            questions8.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions8);
                        } else {
                            Questions questions8 = new Questions();
                            questions8.type = "behavioral";
                            questions8.question = "Q39";
                            questions8.member_id = memberHabits.MemberId;
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
                            questions9.member_id = memberHabits.MemberId;
                            questions9.answer = String.valueOf(vegetablesShowCard);
                            questions9.date = currentDate;
                            questions9.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions9);
                        } else {
                            Questions questions9 = new Questions();
                            questions9.type = "behavioral";
                            questions9.question = "Q39";
                            questions9.member_id = memberHabits.MemberId;
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
                            questions10.member_id = memberHabits.MemberId;
                            questions10.answer = String.valueOf(saltyBuy);
                            questions10.date = currentDate;
                            questions10.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions10);
                        } else {
                            Questions questions10 = new Questions();
                            questions10.type = "behavioral";
                            questions10.question = "Q41";
                            questions10.member_id = memberHabits.MemberId;
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
                            questions11.member_id = memberHabits.MemberId;
                            questions11.answer = String.valueOf(takingSalt);
                            questions11.date = currentDate;
                            questions11.master_id = memberHabits.id;
                            Common.qustionsRepository.insertToQuestions(questions11);
                        } else {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberHabits.MemberId;
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
                            questions11.member_id = memberHabitsFor.MemberId;
                            questions11.answer = edit_yes_extra_salt.getText().toString();
                            questions11.date = currentDate;
                            questions11.master_id = memberHabitsFor.id;
                            Common.qustionsRepository.insertToQuestions(questions11);
                        } else {
                            Questions questions11 = new Questions();
                            questions11.type = "behavioral";
                            questions11.question = "Q42";
                            questions11.member_id = memberHabitsFor.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q43";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor6.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                            Questions questionsFor8 = Common.qustionsRepository.getQuestions("Q43a", update);
                            if (questionsFor8 == null) {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q43a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = vigiriousIntensity();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions13);
                            } else {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q43a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = vigiriousIntensity();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                questions13.id = questionsFor8.id;
                                Common.qustionsRepository.updateQuestions(questions13);
                            }


                            Questions questionsFor9 = Common.qustionsRepository.getQuestions("Q43b", update);
                            if (questionsFor9 == null) {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q43b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(vigorousIntensityTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions14);
                            } else {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q43b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(vigorousIntensityTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                questions14.id = questionsFor9.id;
                                Common.qustionsRepository.updateQuestions(questions14);
                            }

                            Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q43c", update);
                            if (questionsFor19 == null) {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q43c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q43c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                questions15.id = questionsFor19.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }

                        ////
                        if (moderateIntensity == 2) {
                            Questions questionsFor15 = Common.qustionsRepository.getQuestions("Q44", update);
                            if (questionsFor15 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q44";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor111.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                            Questions questionsFor91 = Common.qustionsRepository.getQuestions("Q44a", update);
                            if (questionsFor91 == null) {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q44a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = moderateIntensity();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions13);
                            } else {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q44a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = moderateIntensity();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                questions13.id = questionsFor91.id;
                                Common.qustionsRepository.updateQuestions(questions13);
                            }

                            Questions questionsFor89 = Common.qustionsRepository.getQuestions("Q44b", update);
                            if (questionsFor89 == null) {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q44b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(moderateIntensityTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions14);
                            } else {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q44b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(moderateIntensityTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                questions14.id = questionsFor89.id;
                                Common.qustionsRepository.updateQuestions(questions14);
                            }

                            Questions questionsFor67 = Common.qustionsRepository.getQuestions("Q44c", update);
                            if (questionsFor67 == null) {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q44c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day_moderate.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q44c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day_moderate.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                questions15.id = questionsFor67.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }


                        ////
                        if (vigorousIntensityRecreational == 2) {
                            Questions questionsFor78 = Common.qustionsRepository.getQuestions("Q45", update);
                            if (questionsFor78 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q45";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                questions12.id = questionsFor616.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }


                            Questions questionsFor51 = Common.qustionsRepository.getQuestions("Q45a", update);
                            if (questionsFor51 == null) {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q45a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = vigorousIntensityRecreational();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions13);
                            } else {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q45a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = vigorousIntensityRecreational();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                questions13.id = questionsFor51.id;
                                Common.qustionsRepository.updateQuestions(questions13);
                            }


                            Questions questionsFor18 = Common.qustionsRepository.getQuestions("Q45b", update);
                            if (questionsFor18 == null) {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q45b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions14);
                            } else {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q45b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(vigorousIntensityRecreationalTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                questions14.id = questionsFor18.id;
                                Common.qustionsRepository.updateQuestions(questions14);
                            }

                            Questions questionsFor241 = Common.qustionsRepository.getQuestions("Q45c", update);
                            if (questionsFor241 == null) {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q45c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day_recreational.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q45c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day_recreational.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                questions15.id = questionsFor241.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }


                        if (moderateIntensityRecreational == 2) {
                            Questions questionsFor31 = Common.qustionsRepository.getQuestions("Q46", update);
                            if (questionsFor31 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "2";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q46";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.id = questionsFor21.id;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions12);
                            }

                            Questions questionsFor178 = Common.qustionsRepository.getQuestions("Q46a", update);
                            if (questionsFor178 == null) {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q46a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.answer = moderateIntensityRecreational();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions13);
                            } else {
                                Questions questions13 = new Questions();
                                questions13.type = "behavioral";
                                questions13.question = "Q46a";
                                questions13.member_id = memberHabits.MemberId;
                                questions13.id = questionsFor178.id;
                                questions13.answer = moderateIntensityRecreational();
                                questions13.date = currentDate;
                                questions13.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions13);
                            }

                            Questions questionsFor17 = Common.qustionsRepository.getQuestions("Q46b", update);
                            if (questionsFor17 == null) {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q46b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions14);
                            } else {
                                Questions questions14 = new Questions();
                                questions14.type = "behavioral";
                                questions14.question = "Q46b";
                                questions14.member_id = memberHabits.MemberId;
                                questions14.id = questionsFor17.id;
                                questions14.answer = String.valueOf(moderateIntensityRecreationalTypical);
                                questions14.date = currentDate;
                                questions14.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions14);
                            }

                            Questions questionsFor199 = Common.qustionsRepository.getQuestions("Q46c", update);
                            if (questionsFor199 == null) {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q46c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.type = "behavioral";
                                questions15.question = "Q46c";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.id = questionsFor199.id;
                                questions15.answer = edit_typical_day_moderate_recreational.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }


                        if (recliningActivities == 2) {
                            Questions questionsFor19 = Common.qustionsRepository.getQuestions("Q48", update);
                            if (questionsFor19 == null) {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions12.member_id = memberHabits.MemberId;
                                questions12.answer = "1";
                                questions12.date = currentDate;
                                questions12.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions12);
                            } else {
                                Questions questions12 = new Questions();
                                questions12.id = questionsFor12.id;
                                questions12.type = "behavioral";
                                questions12.question = "Q48";
                                questions12.member_id = memberHabits.MemberId;
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
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_yes_reclining.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.insertToQuestions(questions15);
                            } else {
                                Questions questions15 = new Questions();
                                questions15.id = questionsFor15.id;
                                questions15.type = "behavioral";
                                questions15.question = "Q48a";
                                questions15.member_id = memberHabits.MemberId;
                                questions15.answer = edit_yes_reclining.getText().toString();
                                questions15.date = currentDate;
                                questions15.master_id = memberHabits.id;
                                Common.qustionsRepository.updateQuestions(questions15);
                            }

                        }
                        memberHabit.household_uniqe_id = memberHabits.household_uniqe_id;
                        memberHabit.member_unique_code = "";
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
            HHCreateMemberFragment.prevPage(1);
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

}

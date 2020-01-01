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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

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
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.Utils;

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
    EditText edit_typical_day__recreational;
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

    }
    private void initDiabetisSpinner() {
        yesNoArrayAdapterForSmoke = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForSmoke);
        yesNoArrayAdapterForSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_smoke.setAdapter(yesNoArrayAdapterForSmoke);

        spinner_smoke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForSmoke.get(position).getId());
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

    private void saveData() {

        if (isChecked()) {
            //  HHCreateMemberFragment.nextPage(1);
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

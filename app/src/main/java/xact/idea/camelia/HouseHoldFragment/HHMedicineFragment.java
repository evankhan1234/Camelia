package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Adapter.HHAdapter.MedicineRemoveOrAdd;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.Medicine;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.EducationModel;
import xact.idea.camelia.Model.DropDownModel.LivingStatusModel;
import xact.idea.camelia.Model.DropDownModel.MaritialStatusModel;
import xact.idea.camelia.Model.DropDownModel.OccupationModel;
import xact.idea.camelia.Model.DropDownModel.ReligionModel;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.Model.DropDownModel.YesNoModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SpinnerForMedicine;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.isEmpty;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;


public class HHMedicineFragment extends Fragment implements Handler.Callback {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    private MedicineRemoveOrAdd mTagAddAdapters = null;
    View view;
    TextView text_medicine_diabetis;
    Spinner spinner_medicine_name_heart_attack;
    Spinner spinner_medicine_name_brain_stroke_disease;
    Spinner spinner_diabetis;
    Spinner spinner_medicine_name_mental_disorders;
    Spinner spinner_kidney;
    Spinner spinner_medicine_name_mental_disorder;
    Spinner spinner_medicine_control_name_kidney_disease;
    Spinner spinner_medicine_name_brain_stroke;
    Spinner spinner_brain_stroke;
    Spinner spinner_heart_attack;
    Spinner spinner_medicine_name;
    Spinner spinner_blood_pressure;
    Spinner spinner_lung;
    Spinner spinner_mental_disorder;
    Spinner spinner_medicine__name_ashma;
    Spinner spinner_medicine_control_name_ashma;
    Spinner spinner_ashma;
    Spinner spinner_cancer;
    LinearLayout linear_medicine_kidney;
    LinearLayout linear_control_cancer;
    Spinner spinner_medicine_name_lung_disease;
    Spinner spinner_medicine_name_control_lung_disease;
    Spinner spinner_medicine_name_blood_pressure;
    LinearLayout linear_cancer;
    LinearLayout linear_medicine_cancer;
    LinearLayout linear_medicine_lung;
    LinearLayout linear_kidney;
    LinearLayout linearDiabetis;
    LinearLayout linearDiabetisCheckbox;
    LinearLayout linear_medicine_diabetis;
    LinearLayout linear_blood_pressure;
    LinearLayout linear_blood_pressure_check_box;
    LinearLayout linear_medicine_blood;
    LinearLayout linear_heart_attack;
    LinearLayout linear_heart_attack_check_box;
    LinearLayout linear_brain_stroke;
    LinearLayout linear_brain_stroke_control;
    LinearLayout linear_medicine_brain_stroke;
    LinearLayout linear_medicine_control_lung;
    LinearLayout linear_lung_disease;
    LinearLayout linear_ashma;
    LinearLayout linear_control_mental_disorder;
    LinearLayout linear_mental_disorder;
    LinearLayout linear_medicine_mental_disorder;
    LinearLayout linear_medicine_heart_attack;
    LinearLayout linear_control_kidney;
    SpinnerForMedicine spinnerForMedicine;
    LinearLayout linear_medicine_ashma;
    LinearLayout linear_control_ashma;
    EditText edit_diabetis_month;
    EditText edit_yes_ashma;
    EditText edit_yes_mental_disorder;
    EditText edit_yes_cancer;
    EditText edit_yes_lung_disease;
    EditText edit_yes_heart_attack;
    EditText edit_yes_heart_brain_stroke;
    EditText edit_yes_blood;
    EditText edit_yes_kidney_disease;
    CheckBox checkBoxFoodHabit;
    CheckBox checkBoxMouthMedicine;
    CheckBox checkBoxInsulin;
    CheckBox checkBoxExercise;
    CheckBox checkBoxNo;
    CheckBox checkBoxFoodHabit_blood_pressure;
    CheckBox checkBoxMouthMedicine_blood_pressure;
    CheckBox checkBoxExercise_blood_pressure;
    CheckBox checkBoxNo_blood_pressure;
    CheckBox checkBoxFoodHabit_heart_attack;
    CheckBox checkBoxExercise_heart_attack;
    CheckBox checkBoxNo_heart_attack;
    public static Handler handler;
    ArrayAdapter<YesNoModel> yesNoArrayAdapter;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterBloodPressure;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterHeartAttack;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterBrainStroke;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterLungDisease;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterAshma;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterKidney;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterCancer;
    ArrayAdapter<YesNoModel> yesNoArrayAdapterMental;
    static ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForDiabetis = new ArrayList<>();
     List<Medicine> medicineArrayList = new ArrayList<>();
     List<String> medicineArrayListName = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForBloodPressure = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForHeartAttack = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForBrainStroke = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForLungDisease = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForAshma = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForKidney = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForCancer = new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForMental = new ArrayList<>();
    String medicineDiabetis;
    static RecyclerView rcl_this_medicine_diabetis;
    String diabetisControl;
    static TextView text_diabetis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hhmedicine, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        handler = new Handler(this);
        initView();
        load();
        // display();
        return view;
    }
    UccMemberClickListener bookItemInterface = new UccMemberClickListener() {
        @Override
        public void onItemClick(int position) {

            spinnerForMedicine.closeSpinerDialog();
            if(isEmpty(medicineDiabetis)){
                medicineDiabetis=medicineArrayList.get(position).Name;
            }
            else {
                medicineDiabetis+=","+ medicineArrayList.get(position).Name;
            }
          //  text_medicine_diabetis.setText(medicineDiabetis);
            arrayList.add(medicineArrayList.get(position).Name);
            HashSet hs = new HashSet();
            hs.addAll(arrayList);
            arrayList.clear();
            arrayList.addAll(hs);
            if (arrayList.size()>0){
                text_diabetis.setVisibility(View.GONE);
                rcl_this_medicine_diabetis.setVisibility(View.VISIBLE);
            }
            else {
                rcl_this_medicine_diabetis.setVisibility(View.GONE);
                text_diabetis.setVisibility(View.VISIBLE);
            }
            mTagAddAdapters.notifyDataSetChanged();


        }
    };
    private void initView() {
//        StringBuilder tag = new StringBuilder();
//        for (String s : arrayList) {
//            tag.append(s + ",");
//
//        }
//
//        String str = tag.toString();
//
//
//        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
//            str = str.substring(0, str.length() - 1);
//        }


        text_diabetis = view.findViewById(R.id.text_diabetis);
        spinner_medicine_name_mental_disorder = view.findViewById(R.id.spinner_medicine_name_mental_disorder);
        spinner_medicine_name_mental_disorders = view.findViewById(R.id.spinner_medicine_name_mental_disorders);
        linear_control_mental_disorder = view.findViewById(R.id.linear_control_mental_disorder);
        linear_medicine_mental_disorder = view.findViewById(R.id.linear_medicine_mental_disorder);
        edit_yes_mental_disorder = view.findViewById(R.id.edit_yes_mental_disorder);
        text_medicine_diabetis = view.findViewById(R.id.text_medicine_diabetis);
        rcl_this_medicine_diabetis = view.findViewById(R.id.rcl_this_medicine_diabetis);
        linear_mental_disorder = view.findViewById(R.id.linear_mental_disorder);
        linear_control_kidney = view.findViewById(R.id.linear_control_kidney);
        linear_medicine_heart_attack = view.findViewById(R.id.linear_medicine_heart_attack);
        spinner_mental_disorder = view.findViewById(R.id.spinner_mental_disorder);
        spinner_diabetis = view.findViewById(R.id.spinner_diabetis);
        linearDiabetis = view.findViewById(R.id.linearDiabetis);
        linear_medicine_cancer = view.findViewById(R.id.linear_medicine_cancer);
        linear_control_cancer = view.findViewById(R.id.linear_control_cancer);
        edit_diabetis_month = view.findViewById(R.id.edit_diabetis_month);
        linearDiabetisCheckbox = view.findViewById(R.id.linearDiabetisCheckbox);
        checkBoxFoodHabit = view.findViewById(R.id.checkBoxFoodHabit);
        checkBoxMouthMedicine = view.findViewById(R.id.checkBoxMouthMedicine);
        checkBoxInsulin = view.findViewById(R.id.checkBoxInsulin);
        checkBoxExercise = view.findViewById(R.id.checkBoxExercise);
        checkBoxNo = view.findViewById(R.id.checkBoxNo);
        linear_medicine_diabetis = view.findViewById(R.id.linear_medicine_diabetis);
        spinner_medicine_name = view.findViewById(R.id.spinner_medicine_name);
        spinner_blood_pressure = view.findViewById(R.id.spinner_blood_pressure);
        linear_blood_pressure = view.findViewById(R.id.linear_blood_pressure);
        edit_yes_blood = view.findViewById(R.id.edit_yes_blood);
        linear_blood_pressure_check_box = view.findViewById(R.id.linear_blood_pressure_check_box);
        checkBoxFoodHabit_blood_pressure = view.findViewById(R.id.checkBoxFoodHabit_blood_pressure);
        checkBoxMouthMedicine_blood_pressure = view.findViewById(R.id.checkBoxMouthMedicine_blood_pressure);
        checkBoxExercise_blood_pressure = view.findViewById(R.id.checkBoxExercise_blood_pressure);
        checkBoxNo_blood_pressure = view.findViewById(R.id.checkBoxNo_blood_pressure);
        linear_medicine_blood = view.findViewById(R.id.linear_medicine_blood);
        spinner_medicine_name_blood_pressure = view.findViewById(R.id.spinner_medicine_name_blood_pressure);
        spinner_heart_attack = view.findViewById(R.id.spinner_heart_attack);
        linear_heart_attack = view.findViewById(R.id.linear_heart_attack);
        edit_yes_heart_attack = view.findViewById(R.id.edit_yes_heart_attack);
        linear_heart_attack_check_box = view.findViewById(R.id.linear_heart_attack_check_box);
        checkBoxFoodHabit_heart_attack = view.findViewById(R.id.checkBoxFoodHabit_heart_attack);
        checkBoxExercise_heart_attack = view.findViewById(R.id.checkBoxExercise_heart_attack);
        checkBoxNo_heart_attack = view.findViewById(R.id.checkBoxNo_heart_attack);
        spinner_medicine_name_heart_attack = view.findViewById(R.id.spinner_medicine_name_heart_attack);
        spinner_brain_stroke = view.findViewById(R.id.spinner_brain_stroke);
        linear_brain_stroke = view.findViewById(R.id.linear_brain_stroke);
        edit_yes_heart_brain_stroke = view.findViewById(R.id.edit_yes_heart_brain_stroke);
        linear_brain_stroke_control = view.findViewById(R.id.linear_brain_stroke_control);
        spinner_medicine_name_brain_stroke_disease = view.findViewById(R.id.spinner_medicine_name_brain_stroke_disease);
        linear_medicine_brain_stroke = view.findViewById(R.id.linear_medicine_brain_stroke);
        spinner_medicine_name_brain_stroke = view.findViewById(R.id.spinner_medicine_name_brain_stroke);
        spinner_lung = view.findViewById(R.id.spinner_lung);
        linear_lung_disease = view.findViewById(R.id.linear_lung_disease);
        edit_yes_lung_disease = view.findViewById(R.id.edit_yes_lung_disease);
        linear_medicine_control_lung = view.findViewById(R.id.linear_medicine_control_lung);
        linear_medicine_lung = view.findViewById(R.id.linear_medicine_lung);
        spinner_medicine_name_control_lung_disease = view.findViewById(R.id.spinner_medicine_name_control_lung_disease);
        spinner_medicine_name_lung_disease = view.findViewById(R.id.spinner_medicine_name_lung_disease);
        spinner_ashma = view.findViewById(R.id.spinner_ashma);
        linear_ashma = view.findViewById(R.id.linear_ashma);
        edit_yes_ashma = view.findViewById(R.id.edit_yes_ashma);
        linear_control_ashma = view.findViewById(R.id.linear_control_ashma);
        spinner_medicine_control_name_ashma = view.findViewById(R.id.spinner_medicine_control_name_ashma);
        linear_medicine_ashma = view.findViewById(R.id.linear_medicine_ashma);
        spinner_medicine__name_ashma = view.findViewById(R.id.spinner_medicine__name_ashma);
        spinner_kidney = view.findViewById(R.id.spinner_kidney);
        linear_kidney = view.findViewById(R.id.linear_kidney);
        edit_yes_kidney_disease = view.findViewById(R.id.edit_yes_kidney_disease);
        linear_medicine_kidney = view.findViewById(R.id.linear_medicine_kidney);
        spinner_cancer = view.findViewById(R.id.spinner_cancer);
        linear_cancer = view.findViewById(R.id.linear_cancer);
        edit_yes_cancer = view.findViewById(R.id.edit_yes_cancer);
        spinner_medicine_control_name_kidney_disease = view.findViewById(R.id.spinner_medicine_control_name_kidney_disease);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
       // rcl_this_medicine_diabetis.setLayoutManager(new GridLayoutManager(mActivity, 2));
        //lm.setStackFromEnd(true);
        rcl_this_medicine_diabetis.setLayoutManager(lm);
       // onEditorAction(ed);

        mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayList);

        // mTagAddAdapters.onViewAttachedToWindow();
        rcl_this_medicine_diabetis.setAdapter(mTagAddAdapters);
        yesNoArrayListForDiabetis = Utils.getyesNoList();
        yesNoArrayListForBloodPressure = Utils.getyesNoList();
        yesNoArrayListForHeartAttack = Utils.getyesNoList();
        yesNoArrayListForBrainStroke = Utils.getyesNoList();
        yesNoArrayListForLungDisease = Utils.getyesNoList();
        yesNoArrayListForAshma = Utils.getyesNoList();
        yesNoArrayListForKidney = Utils.getyesNoList();
        yesNoArrayListForCancer = Utils.getyesNoList();
        yesNoArrayListForMental = Utils.getyesNoList();

        initDiabetisSpinner();
        initBloodPressureSpinner();
        initHeartAttackSpinner();
        initBrainStrokeSpinner();
        initLungDiseaseSpinner();
        initAshmaSpinner();
        initKidneySpinner();
        initCancerSpinner();
        initMentalDisorderSpinner();

        text_medicine_diabetis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//


                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine",bookItemInterface);
                spinnerForMedicine.showSpinerDialog();
                //showInfoDialog();
            }
        });

        checkboxClickListener();
    }

    public static void  Show(){
        if (arrayList.size()>0){
            text_diabetis.setVisibility(View.GONE);
            rcl_this_medicine_diabetis.setVisibility(View.VISIBLE);
        }
        else {
            rcl_this_medicine_diabetis.setVisibility(View.GONE);
            text_diabetis.setVisibility(View.VISIBLE);
        }
    }
    private void checkboxClickListener() {
        checkBoxFoodHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFoodHabit.isChecked()){
                    checkBoxFoodHabit.setChecked(false);
                }
                else{
                    checkBoxFoodHabit.setChecked(true);
                }
                checkBox(checkBoxFoodHabit);
//                if (checkBoxFoodHabit.isChecked()){
//
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="1";
//                    }
//                    else {
//                        medicineDiabetis+=","+ "1";
//                    }
//                }
//                else {
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="1";
//                    }
//                    else {
//                        medicineDiabetis.replace(",1","");
//                    }
//
//                }

            }
        });
        checkBoxMouthMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMouthMedicine.isChecked()){
                    checkBoxMouthMedicine.setChecked(false);
                }
                else{
                    checkBoxMouthMedicine.setChecked(true);
                }
                checkBox(checkBoxMouthMedicine);
//                if (checkBoxFoodHabit.isChecked()){
//
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="3";
//                    }
//                    else {
//                        medicineDiabetis+=","+ "3";
//                    }
//                }
//                else {
//                    medicineDiabetis.replace(",3","");
//                }

            }
        });
        checkBoxExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxExercise.isChecked()){
                    checkBoxExercise.setChecked(false);
                }
                else{
                    checkBoxExercise.setChecked(true);
                }
                checkBox(checkBoxExercise);
//                if (checkBoxFoodHabit.isChecked()){
//
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="2";
//                    }
//                    else {
//                        medicineDiabetis+=","+ "2";
//                    }
//                }
//                else {
//                    medicineDiabetis.replace(",2","");
//                }

            }
        });
        checkBoxInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxInsulin.isChecked()){
                    checkBoxInsulin.setChecked(false);
                }
                else{
                    checkBoxInsulin.setChecked(true);
                }
                checkBox(checkBoxInsulin);
                Log.e("fsf","c"+diabetis());
//
//                if (checkBoxInsulin.isChecked()){
//
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="4";
//                    }
//                    else {
//                        medicineDiabetis+=","+ "4";
//                    }
//                }
//                else {
//                    medicineDiabetis.replace(",4","");
//                }

            }
        });
        checkBoxNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxNo.isChecked()){
                    checkBoxNo.setChecked(false);
                }
                else{
                    checkBoxNo.setChecked(true);
                }

                checkBox(checkBoxNo);

//                if (checkBoxNo.isChecked()){
//
//                    if(isEmpty(diabetisControl)){
//                        diabetisControl="5";
//                    }
//                    else {
//                        medicineDiabetis+=","+ "5";
//                    }
//                }
//                else {
//                    medicineDiabetis.replace(",5","");
//                }

            }
        });
    }


   private boolean isChecked(int pos)
    {
        if(pos==1&&checkBoxFoodHabit.isChecked())
        return  true;
        if(pos==2&&checkBoxExercise.isChecked())
        return  true;
        if(pos==3&&checkBoxMouthMedicine.isChecked())
        return  true;
        if(pos==4&&checkBoxInsulin.isChecked())
        return  true;
        if(pos==5&&checkBoxNo.isChecked())
        return  true;



        return  false;
    }

    private String diabetis(){
        String ageList="";
        for(int i=0;i<6;i++)
        {
            if(isChecked(i)) {
                if(isNullOrEmpty(ageList))
                    ageList = String.valueOf((i));
                else
                    ageList = ageList + "," + (i) + "";
            }
        }

        if(ageList.length()>0)
        {        return  ageList;

        }
        else
        {
            return  "";
        }


    }
    private void checkBox(CheckBox checkBox){
        { if(!checkBox.isChecked())
            checkBox.setChecked(true);
        else if(checkBox.isChecked())
            checkBox.setChecked(false);
        }

    }

    private void load(){
        compositeDisposable.add(Common.medicineRepository.getMedicineItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Medicine>>() {
            @Override
            public void accept(List<Medicine> customers) throws Exception {
                Log.e("Division","Division"+new Gson().toJson(customers));
                medicineArrayList=customers;

                for (Medicine medicine:customers){
                    medicineArrayListName.add(medicine.Name);
                }

                dismissLoadingProgress();

            }
        }));
    }
    private void initDiabetisSpinner() {
        yesNoArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForDiabetis);
        yesNoArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_diabetis.setAdapter(yesNoArrayAdapter);

        spinner_diabetis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForDiabetis.get(position).getId());
                if (yesNoArrayListForDiabetis.get(position).getId() == 1) {
                    linearDiabetis.setVisibility(View.VISIBLE);
                    linearDiabetisCheckbox.setVisibility(View.VISIBLE);
                    linear_medicine_diabetis.setVisibility(View.VISIBLE);
                } else {
                    linearDiabetis.setVisibility(View.GONE);
                    linearDiabetisCheckbox.setVisibility(View.GONE);
                    linear_medicine_diabetis.setVisibility(View.GONE);
                    edit_diabetis_month.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initBloodPressureSpinner() {
        yesNoArrayAdapterBloodPressure = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForBloodPressure);
        yesNoArrayAdapterBloodPressure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood_pressure.setAdapter(yesNoArrayAdapterBloodPressure);

        spinner_blood_pressure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForBloodPressure.get(position).getId());
                if (yesNoArrayListForBloodPressure.get(position).getId() == 1) {
                    linear_blood_pressure.setVisibility(View.VISIBLE);
                    linear_blood_pressure_check_box.setVisibility(View.VISIBLE);
                    linear_medicine_blood.setVisibility(View.VISIBLE);
                } else {
                    linear_blood_pressure.setVisibility(View.GONE);
                    linear_blood_pressure_check_box.setVisibility(View.GONE);
                    linear_medicine_blood.setVisibility(View.GONE);
                    edit_yes_blood.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initHeartAttackSpinner() {
        yesNoArrayAdapterHeartAttack = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForHeartAttack);
        yesNoArrayAdapterHeartAttack.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heart_attack.setAdapter(yesNoArrayAdapterHeartAttack);

        spinner_heart_attack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForHeartAttack.get(position).getId());
                if (yesNoArrayListForHeartAttack.get(position).getId() == 1) {
                    linear_heart_attack.setVisibility(View.VISIBLE);
                    linear_heart_attack_check_box.setVisibility(View.VISIBLE);
                    linear_medicine_heart_attack.setVisibility(View.VISIBLE);
                } else {
                    linear_heart_attack.setVisibility(View.GONE);
                    linear_heart_attack_check_box.setVisibility(View.GONE);
                    linear_medicine_heart_attack.setVisibility(View.GONE);
                    edit_yes_heart_attack.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initBrainStrokeSpinner() {
        yesNoArrayAdapterBrainStroke = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForBrainStroke);
        yesNoArrayAdapterBrainStroke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_brain_stroke.setAdapter(yesNoArrayAdapterBrainStroke);

        spinner_brain_stroke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForBrainStroke.get(position).getId());
                if (yesNoArrayListForBrainStroke.get(position).getId() == 1) {
                    linear_brain_stroke.setVisibility(View.VISIBLE);
                    linear_brain_stroke_control.setVisibility(View.VISIBLE);
                    linear_medicine_brain_stroke.setVisibility(View.VISIBLE);
                } else {
                    linear_brain_stroke.setVisibility(View.GONE);
                    linear_brain_stroke_control.setVisibility(View.GONE);
                    linear_medicine_brain_stroke.setVisibility(View.GONE);
                    edit_yes_heart_brain_stroke.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initLungDiseaseSpinner() {
        yesNoArrayAdapterLungDisease = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForLungDisease);
        yesNoArrayAdapterLungDisease.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lung.setAdapter(yesNoArrayAdapterLungDisease);

        spinner_lung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForLungDisease.get(position).getId());
                if (yesNoArrayListForLungDisease.get(position).getId() == 1) {
                    linear_lung_disease.setVisibility(View.VISIBLE);
                    linear_medicine_control_lung.setVisibility(View.VISIBLE);
                    linear_medicine_lung.setVisibility(View.VISIBLE);
                } else {
                    linear_lung_disease.setVisibility(View.GONE);
                    linear_medicine_control_lung.setVisibility(View.GONE);
                    linear_medicine_lung.setVisibility(View.GONE);
                    edit_yes_lung_disease.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initAshmaSpinner() {
        yesNoArrayAdapterAshma = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForAshma);
        yesNoArrayAdapterAshma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ashma.setAdapter(yesNoArrayAdapterAshma);

        spinner_ashma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForAshma.get(position).getId());
                if (yesNoArrayListForAshma.get(position).getId() == 1) {
                    linear_ashma.setVisibility(View.VISIBLE);
                    linear_medicine_ashma.setVisibility(View.VISIBLE);
                    linear_control_ashma.setVisibility(View.VISIBLE);
                } else {
                    linear_ashma.setVisibility(View.GONE);
                    linear_medicine_ashma.setVisibility(View.GONE);
                    linear_control_ashma.setVisibility(View.GONE);
                    edit_yes_ashma.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initKidneySpinner() {
        yesNoArrayAdapterKidney = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForKidney);
        yesNoArrayAdapterKidney.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kidney.setAdapter(yesNoArrayAdapterKidney);

        spinner_kidney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForKidney.get(position).getId());
                if (yesNoArrayListForKidney.get(position).getId() == 1) {
                    linear_kidney.setVisibility(View.VISIBLE);
                    linear_medicine_kidney.setVisibility(View.VISIBLE);
                    linear_control_kidney.setVisibility(View.VISIBLE);
                } else {
                    linear_kidney.setVisibility(View.GONE);
                    linear_medicine_kidney.setVisibility(View.GONE);
                    linear_control_kidney.setVisibility(View.GONE);
                    edit_yes_kidney_disease.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initCancerSpinner() {
        yesNoArrayAdapterCancer = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForCancer);
        yesNoArrayAdapterCancer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cancer.setAdapter(yesNoArrayAdapterCancer);

        spinner_cancer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForCancer.get(position).getId());
                if (yesNoArrayListForCancer.get(position).getId() == 1) {
                    linear_cancer.setVisibility(View.VISIBLE);
                    linear_control_cancer.setVisibility(View.VISIBLE);
                    linear_medicine_cancer.setVisibility(View.VISIBLE);
                } else {
                    linear_cancer.setVisibility(View.GONE);
                    linear_control_cancer.setVisibility(View.GONE);
                    linear_medicine_cancer.setVisibility(View.GONE);
                    edit_yes_cancer.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initMentalDisorderSpinner() {
        yesNoArrayAdapterMental = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, yesNoArrayListForMental);
        yesNoArrayAdapterMental.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mental_disorder.setAdapter(yesNoArrayAdapterMental);

        spinner_mental_disorder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + yesNoArrayListForMental.get(position).getId());
                if (yesNoArrayListForMental.get(position).getId() == 1) {
                    linear_mental_disorder.setVisibility(View.VISIBLE);
                    linear_control_mental_disorder.setVisibility(View.VISIBLE);
                    linear_medicine_mental_disorder.setVisibility(View.VISIBLE);
                } else {
                    linear_mental_disorder.setVisibility(View.GONE);
                    linear_control_mental_disorder.setVisibility(View.GONE);
                    linear_medicine_mental_disorder.setVisibility(View.GONE);
                    edit_yes_mental_disorder.setText("");
                }

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
            HHCreateMemberFragment.nextPage(2);
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 1) {
            saveData();
        } else {
            HHCreateMemberFragment.prevPage(0);
        }
        return false;
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

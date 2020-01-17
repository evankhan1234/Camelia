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
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.Model.DropDownModel.BloodGroupModel;
import xact.idea.camelia.Model.DropDownModel.ControlDiseaseModel;
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
    Spinner spinner_medicine_control_name_cancer;
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
    CheckBox checkBoxMouthMedicine_heart_attack;
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
    ArrayAdapter<ControlDiseaseModel> controlDiseaseBrainStrokeArrayAdapter;
    ArrayAdapter<ControlDiseaseModel> controlDiseaseLungsArrayAdapter;
    ArrayAdapter<ControlDiseaseModel> controlDiseaseAshmaArrayAdapter;
    ArrayAdapter<ControlDiseaseModel> controlDiseaseKidneyArrayAdapter;
    ArrayAdapter<ControlDiseaseModel> controlDiseaseCancerArrayAdapter;
    ArrayAdapter<ControlDiseaseModel> controlDiseaseMentalArrayAdapter;
    static ArrayList<String> arrayList = new ArrayList<>();
    static ArrayList<String> arrayListBlood = new ArrayList<>();
    static ArrayList<String> arrayListHeart= new ArrayList<>();
    static ArrayList<String> arrayListBrainStroke= new ArrayList<>();
    static ArrayList<String> arrayListLung= new ArrayList<>();
    static ArrayList<String> arrayListAshma= new ArrayList<>();
    static ArrayList<String> arrayListKidney= new ArrayList<>();
    static ArrayList<String> arrayListCancer= new ArrayList<>();
    static ArrayList<String> arrayListMental= new ArrayList<>();
    ArrayList<YesNoModel> yesNoArrayListForDiabetis = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseBrainStrokeModels = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseLungsModels = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseAshmaModels = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseKidneyModels = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseCancerModels = new ArrayList<>();
    ArrayList<ControlDiseaseModel> controlDiseaseMentalModels = new ArrayList<>();
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
    static RecyclerView rcl_this_blood_pressure;
    static RecyclerView rcl_this_heart_attack;
    static RecyclerView rcl_this_brain_stroke;
    static RecyclerView rcl_this_lung_disease;
    static RecyclerView rcl_this_ashma;
    static RecyclerView rcl_this_kidney;
    static RecyclerView rcl_this_cancer;
    static RecyclerView rcl_this_mental_disorders;


    static TextView text_diabetis;
    static TextView text_bloodPressure;
    TextView text_blood_Pressure;
    static TextView text_heart_attack_text;
    TextView text_heart_attack;
    static TextView text_brain_stroke_text;
    TextView text_brain_stroke;
    static TextView text_lung_disease_text;
    TextView text_lung_disease;
    static TextView text_ashma_text;
    TextView text_ashma;
    static TextView text_kidney_text;
    TextView text_kidney;
    static TextView text_cancer_text;
    TextView text_cancer;
    static TextView text_mental_disorders_text;
    TextView text_mental_disorders;

    int controlDiseaseBrainStroke;
    int controlDiseaseLungDisease;
    int controlDiseaseKidney;
    int controlDiseaseMentalDisorder;
    int controlDiseaseAshma;
    int controlDiseaseCancer;

    int diabetisYesNo;
    int bloodPressureYesNo;
    int heartAttackYesNo;
    int brainStrokeYesNo;
    int lungYesNo;
    int kidneyYesNo;
    int cancerYesNo;
    int mentalDisorderYesNo;
    int ashmaYesNo;

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

    MedicineInterface bookItemInterface = new MedicineInterface() {
        @Override
        public void postion(int position, String Type) {
            if (Type.equals("D")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayList, "D");

                // mTagAddAdapters.onViewAttachedToWindow();
                rcl_this_medicine_diabetis.setAdapter(mTagAddAdapters);
                // rcl_this_blood_pressure.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayList.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayList);
                arrayList.clear();
                arrayList.addAll(hs);
                if (arrayList.size() > 0) {
                    text_diabetis.setVisibility(View.GONE);
                    rcl_this_medicine_diabetis.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_medicine_diabetis.setVisibility(View.GONE);
                    text_diabetis.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            } else if (Type.equals("B")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListBlood, "B");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_blood_pressure.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListBlood.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListBlood);
                arrayListBlood.clear();
                arrayListBlood.addAll(hs);
                if (arrayListBlood.size() > 0) {
                    text_bloodPressure.setVisibility(View.GONE);
                    rcl_this_blood_pressure.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_blood_pressure.setVisibility(View.GONE);
                    text_bloodPressure.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Heart")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListHeart, "Heart");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_heart_attack.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListHeart.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListHeart);
                arrayListHeart.clear();
                arrayListHeart.addAll(hs);
                if (arrayListHeart.size() > 0) {
                    text_heart_attack_text.setVisibility(View.GONE);
                    rcl_this_heart_attack.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_heart_attack.setVisibility(View.GONE);
                    text_heart_attack_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Brain")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListBrainStroke, "Brain");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_brain_stroke.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListBrainStroke.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListBrainStroke);
                arrayListBrainStroke.clear();
                arrayListBrainStroke.addAll(hs);
                if (arrayListBrainStroke.size() > 0) {
                    text_brain_stroke_text.setVisibility(View.GONE);
                    rcl_this_brain_stroke.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_brain_stroke.setVisibility(View.GONE);
                    text_brain_stroke_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Lung")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListLung, "Lung");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_lung_disease.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListLung.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListLung);
                arrayListLung.clear();
                arrayListLung.addAll(hs);
                if (arrayListLung.size() > 0) {
                    text_lung_disease_text.setVisibility(View.GONE);
                    rcl_this_lung_disease.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_lung_disease.setVisibility(View.GONE);
                    text_lung_disease_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Ashma")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListAshma, "Ashma");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_ashma.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListAshma.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListAshma);
                arrayListAshma.clear();
                arrayListAshma.addAll(hs);
                if (arrayListAshma.size() > 0) {
                    text_ashma_text.setVisibility(View.GONE);
                    rcl_this_ashma.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_ashma.setVisibility(View.GONE);
                    text_ashma_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Kidney")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListKidney, "Kidney");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_kidney.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListKidney.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListKidney);
                arrayListKidney.clear();
                arrayListKidney.addAll(hs);
                if (arrayListKidney.size() > 0) {
                    text_kidney_text.setVisibility(View.GONE);
                    rcl_this_kidney.setVisibility(View.VISIBLE);
                } else {
                    text_kidney_text.setVisibility(View.GONE);
                    text_heart_attack_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Cancer")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListCancer, "Cancer");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_cancer.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListCancer.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListCancer);
                arrayListCancer.clear();
                arrayListCancer.addAll(hs);
                if (arrayListCancer.size() > 0) {
                    text_cancer_text.setVisibility(View.GONE);
                    rcl_this_cancer.setVisibility(View.VISIBLE);
                } else {
                    text_cancer_text.setVisibility(View.GONE);
                    text_heart_attack_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
            else if (Type.equals("Mental")) {
                mTagAddAdapters = new MedicineRemoveOrAdd(mActivity, arrayListMental, "Mental");

                // mTagAddAdapters.onViewAttachedToWindow();

                rcl_this_mental_disorders.setAdapter(mTagAddAdapters);
                spinnerForMedicine.closeSpinerDialog();

                //  text_medicine_diabetis.setText(medicineDiabetis);
                arrayListMental.add(medicineArrayList.get(position).Name);
                HashSet hs = new HashSet();
                hs.addAll(arrayListMental);
                arrayListMental.clear();
                arrayListMental.addAll(hs);
                if (arrayListMental.size() > 0) {
                    text_mental_disorders_text.setVisibility(View.GONE);
                    rcl_this_mental_disorders.setVisibility(View.VISIBLE);
                } else {
                    rcl_this_mental_disorders.setVisibility(View.GONE);
                    text_mental_disorders_text.setVisibility(View.VISIBLE);
                }
                mTagAddAdapters.notifyDataSetChanged();
            }
        }


    };

    private void initView() {



        spinner_medicine_control_name_cancer = view.findViewById(R.id.spinner_medicine_control_name_cancer);
        checkBoxMouthMedicine_heart_attack = view.findViewById(R.id.checkBoxMouthMedicine_heart_attack);
        rcl_this_mental_disorders = view.findViewById(R.id.rcl_this_mental_disorders);
        text_mental_disorders = view.findViewById(R.id.text_mental_disorders);
        text_mental_disorders_text = view.findViewById(R.id.text_mental_disorders_text);
        text_cancer = view.findViewById(R.id.text_cancer);
        rcl_this_cancer = view.findViewById(R.id.rcl_this_cancer);
        text_cancer_text = view.findViewById(R.id.text_cancer_text);
        text_kidney = view.findViewById(R.id.text_kidney);
        rcl_this_kidney = view.findViewById(R.id.rcl_this_kidney);
        text_kidney_text = view.findViewById(R.id.text_kidney_text);
        text_ashma = view.findViewById(R.id.text_ashma);
        rcl_this_ashma = view.findViewById(R.id.rcl_this_ashma);
        text_ashma_text = view.findViewById(R.id.text_ashma_text);
        text_lung_disease = view.findViewById(R.id.text_lung_disease);
        rcl_this_lung_disease = view.findViewById(R.id.rcl_this_lung_disease);
        text_lung_disease_text = view.findViewById(R.id.text_lung_disease_text);
        text_brain_stroke = view.findViewById(R.id.text_brain_stroke);
        rcl_this_brain_stroke = view.findViewById(R.id.rcl_this_brain_stroke);
        text_brain_stroke_text = view.findViewById(R.id.text_brain_stroke_text);
        text_heart_attack = view.findViewById(R.id.text_heart_attack);
        rcl_this_heart_attack = view.findViewById(R.id.rcl_this_heart_attack);
        text_heart_attack_text = view.findViewById(R.id.text_heart_attack_text);

        text_bloodPressure = view.findViewById(R.id.text_bloodPressure);
        rcl_this_blood_pressure = view.findViewById(R.id.rcl_this_blood_pressure);
        text_blood_Pressure = view.findViewById(R.id.text_blood_Pressure);
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
        LinearLayoutManager lm1 = new LinearLayoutManager(mActivity);
        lm1.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm2 = new LinearLayoutManager(mActivity);
        lm2.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm3 = new LinearLayoutManager(mActivity);
        lm3.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm4 = new LinearLayoutManager(mActivity);
        lm4.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm5 = new LinearLayoutManager(mActivity);
        lm5.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm6 = new LinearLayoutManager(mActivity);
        lm6.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm7 = new LinearLayoutManager(mActivity);
        lm7.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager lm8 = new LinearLayoutManager(mActivity);
        lm8.setOrientation(LinearLayoutManager.VERTICAL);
        // rcl_this_medicine_diabetis.setLayoutManager(new GridLayoutManager(mActivity, 2));
        //lm.setStackFromEnd(true);
        rcl_this_medicine_diabetis.setLayoutManager(lm);
        rcl_this_blood_pressure.setLayoutManager(lm1);
        rcl_this_heart_attack.setLayoutManager(lm2);
        rcl_this_brain_stroke.setLayoutManager(lm3);
        rcl_this_lung_disease.setLayoutManager(lm4);
        rcl_this_ashma.setLayoutManager(lm5);
        rcl_this_kidney.setLayoutManager(lm6);
        rcl_this_cancer.setLayoutManager(lm7);
        rcl_this_mental_disorders.setLayoutManager(lm8);
        // onEditorAction(ed);


        yesNoArrayListForDiabetis = Utils.getyesNoList();
        controlDiseaseBrainStrokeModels = Utils.getControlDiseaseList();
        controlDiseaseLungsModels = Utils.getControlDiseaseList();
        controlDiseaseCancerModels = Utils.getControlDiseaseList();
        controlDiseaseMentalModels = Utils.getControlDiseaseList();
        controlDiseaseKidneyModels = Utils.getControlDiseaseList();
        controlDiseaseAshmaModels = Utils.getControlDiseaseList();
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

        initSubSpinner();

        text_medicine_diabetis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "D");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_blood_Pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "B");
                spinnerForMedicine.showSpinerDialog();
            }
        });
//        text_blood_Pressure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "B");
//                spinnerForMedicine.showSpinerDialog();
//            }
//        });
        text_heart_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Heart");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_brain_stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Brain");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_lung_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Lung");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_ashma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Ashma");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Kidney");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_cancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Cancer");
                spinnerForMedicine.showSpinerDialog();
            }
        });
        text_mental_disorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerForMedicine = new SpinnerForMedicine(mActivity, medicineArrayListName, "Select Medicine", bookItemInterface, "Mental");
                spinnerForMedicine.showSpinerDialog();
            }
        });


        checkboxClickListener();
        checkboxBloodGroupClickListener();
        checkboxHeartAttackClickListener();
    }

    private void checkboxHeartAttackClickListener() {
        checkBoxFoodHabit_heart_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFoodHabit_heart_attack.isChecked()) {
                    checkBoxFoodHabit_heart_attack.setChecked(false);
                } else {
                    checkBoxFoodHabit_heart_attack.setChecked(true);
                }
                checkBox(checkBoxFoodHabit_heart_attack);

            }
        });
        checkBoxNo_heart_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxNo_heart_attack.isChecked()) {
                    checkBoxNo_heart_attack.setChecked(false);
                } else {
                    checkBoxNo_heart_attack.setChecked(true);
                }
                checkBox(checkBoxNo_heart_attack);

            }
        });
        checkBoxMouthMedicine_heart_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMouthMedicine_heart_attack.isChecked()) {
                    checkBoxMouthMedicine_heart_attack.setChecked(false);
                } else {
                    checkBoxMouthMedicine_heart_attack.setChecked(true);
                }
                checkBox(checkBoxMouthMedicine_heart_attack);

            }
        });
        checkBoxExercise_heart_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxExercise_heart_attack.isChecked()) {
                    checkBoxExercise_heart_attack.setChecked(false);
                } else {
                    checkBoxExercise_heart_attack.setChecked(true);
                }
                checkBox(checkBoxExercise_heart_attack);
                Log.e("fsf", "c" + bloodGroups());

            }
        });
    }

    private void checkboxBloodGroupClickListener() {
        checkBoxFoodHabit_blood_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFoodHabit_blood_pressure.isChecked()) {
                    checkBoxFoodHabit_blood_pressure.setChecked(false);
                } else {
                    checkBoxFoodHabit_blood_pressure.setChecked(true);
                }
                checkBox(checkBoxFoodHabit_blood_pressure);

            }
        });
        checkBoxNo_blood_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxNo_blood_pressure.isChecked()) {
                    checkBoxNo_blood_pressure.setChecked(false);
                } else {
                    checkBoxNo_blood_pressure.setChecked(true);
                }
                checkBox(checkBoxNo_blood_pressure);

            }
        });
        checkBoxMouthMedicine_blood_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMouthMedicine_blood_pressure.isChecked()) {
                    checkBoxMouthMedicine_blood_pressure.setChecked(false);
                } else {
                    checkBoxMouthMedicine_blood_pressure.setChecked(true);
                }
                checkBox(checkBoxMouthMedicine_blood_pressure);

            }
        });
        checkBoxExercise_blood_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxExercise_blood_pressure.isChecked()) {
                    checkBoxExercise_blood_pressure.setChecked(false);
                } else {
                    checkBoxExercise_blood_pressure.setChecked(true);
                }
                checkBox(checkBoxExercise_blood_pressure);
                Log.e("fsf", "c" + bloodGroups());

            }
        });
    }

    public static void Show() {
        if (arrayList.size() > 0) {
            text_diabetis.setVisibility(View.GONE);
            rcl_this_medicine_diabetis.setVisibility(View.VISIBLE);
        } else {
            rcl_this_medicine_diabetis.setVisibility(View.GONE);
            text_diabetis.setVisibility(View.VISIBLE);
        }
    }

    public static void ShowBloodPressure() {
        if (arrayListBlood.size() > 0) {
            text_bloodPressure.setVisibility(View.GONE);
            rcl_this_blood_pressure.setVisibility(View.VISIBLE);
        } else {
            rcl_this_blood_pressure.setVisibility(View.GONE);
            text_bloodPressure.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowBrainStroke() {
        if (arrayListBrainStroke.size() > 0) {
            text_brain_stroke_text.setVisibility(View.GONE);
            rcl_this_brain_stroke.setVisibility(View.VISIBLE);
        } else {
            rcl_this_brain_stroke.setVisibility(View.GONE);
            text_brain_stroke_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowHeartAttack() {
        if (arrayListHeart.size() > 0) {
            text_heart_attack_text.setVisibility(View.GONE);
            rcl_this_heart_attack.setVisibility(View.VISIBLE);
        } else {
            rcl_this_heart_attack.setVisibility(View.GONE);
            text_heart_attack_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowLungDisease() {
        if (arrayListLung.size() > 0) {
            text_brain_stroke_text.setVisibility(View.GONE);
            rcl_this_lung_disease.setVisibility(View.VISIBLE);
        } else {
            rcl_this_lung_disease.setVisibility(View.GONE);
            text_brain_stroke_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowAshma() {
        if (arrayListAshma.size() > 0) {
            text_ashma_text.setVisibility(View.GONE);
            rcl_this_ashma.setVisibility(View.VISIBLE);
        } else {
            rcl_this_ashma.setVisibility(View.GONE);
            text_ashma_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowKidney() {
        if (arrayListKidney.size() > 0) {
            text_kidney_text.setVisibility(View.GONE);
            rcl_this_kidney.setVisibility(View.VISIBLE);
        } else {
            rcl_this_kidney.setVisibility(View.GONE);
            text_kidney_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowCancer() {
        if (arrayListCancer.size() > 0) {
            text_cancer_text.setVisibility(View.GONE);
            rcl_this_cancer.setVisibility(View.VISIBLE);
        } else {
            rcl_this_cancer.setVisibility(View.GONE);
            text_cancer_text.setVisibility(View.VISIBLE);
        }
    }
    public static void ShowMentalDisorder() {
        if (arrayListMental.size() > 0) {
            text_mental_disorders_text.setVisibility(View.GONE);
            rcl_this_mental_disorders.setVisibility(View.VISIBLE);
        } else {
            rcl_this_kidney.setVisibility(View.GONE);
            rcl_this_mental_disorders.setVisibility(View.VISIBLE);
        }
    }
    private void checkboxClickListener() {
        checkBoxFoodHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxFoodHabit.isChecked()) {
                    checkBoxFoodHabit.setChecked(false);
                } else {
                    checkBoxFoodHabit.setChecked(true);
                }
                checkBox(checkBoxFoodHabit);

            }
        });
        checkBoxMouthMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMouthMedicine.isChecked()) {
                    checkBoxMouthMedicine.setChecked(false);
                } else {
                    checkBoxMouthMedicine.setChecked(true);
                }
                checkBox(checkBoxMouthMedicine);

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
        checkBoxInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxInsulin.isChecked()) {
                    checkBoxInsulin.setChecked(false);
                } else {
                    checkBoxInsulin.setChecked(true);
                }
                checkBox(checkBoxInsulin);
                Log.e("fsf", "c" + diabetis());


            }
        });
        checkBoxNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxNo.isChecked()) {
                    checkBoxNo.setChecked(false);
                } else {
                    checkBoxNo.setChecked(true);
                }

                checkBox(checkBoxNo);

            }
        });
    }


    private boolean isChecked(int pos) {
        if (pos == 1 && checkBoxFoodHabit.isChecked())
            return true;
        if (pos == 2 && checkBoxExercise.isChecked())
            return true;
        if (pos == 3 && checkBoxMouthMedicine.isChecked())
            return true;
        if (pos == 4 && checkBoxInsulin.isChecked())
            return true;
        if (pos == 5 && checkBoxNo.isChecked())
            return true;


        return false;
    }

    private boolean isBloodGroupChecked(int pos) {
        if (pos == 1 && checkBoxFoodHabit_blood_pressure.isChecked())
            return true;
        if (pos == 2 && checkBoxExercise_blood_pressure.isChecked())
            return true;
        if (pos == 3 && checkBoxMouthMedicine_blood_pressure.isChecked())
            return true;
        if (pos == 4 && checkBoxNo_blood_pressure.isChecked())
            return true;


        return false;
    }
    private boolean isHeartAttackChecked(int pos) {
        if (pos == 1 && checkBoxFoodHabit_heart_attack.isChecked())
            return true;
        if (pos == 2 && checkBoxExercise_heart_attack.isChecked())
            return true;
        if (pos == 3 && checkBoxMouthMedicine_heart_attack.isChecked())
            return true;
        if (pos == 4 && checkBoxNo_heart_attack.isChecked())
            return true;


        return false;
    }

    private String diabetis() {
        String ageList = "";
        for (int i = 0; i < 6; i++) {
            if (isChecked(i)) {
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

    private String bloodGroups() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isBloodGroupChecked(i)) {
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
    private String heartAttack() {
        String ageList = "";
        for (int i = 0; i < 5; i++) {
            if (isHeartAttackChecked(i)) {
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

    private void checkBox(CheckBox checkBox) {
        {
            if (!checkBox.isChecked())
                checkBox.setChecked(true);
            else if (checkBox.isChecked())
                checkBox.setChecked(false);
        }

    }

    private void load() {
        compositeDisposable.add(Common.medicineRepository.getMedicineItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Medicine>>() {
            @Override
            public void accept(List<Medicine> customers) throws Exception {
                Log.e("Division", "Division" + new Gson().toJson(customers));
                medicineArrayList = customers;

                for (Medicine medicine : customers) {
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
                diabetisYesNo=yesNoArrayListForDiabetis.get(position).getId();
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
                bloodPressureYesNo=yesNoArrayListForBloodPressure.get(position).getId();
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
                heartAttackYesNo=yesNoArrayListForHeartAttack.get(position).getId();
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
                brainStrokeYesNo=yesNoArrayListForBrainStroke.get(position).getId();
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
                lungYesNo=yesNoArrayListForLungDisease.get(position).getId();
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
                ashmaYesNo=yesNoArrayListForAshma.get(position).getId();
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
                kidneyYesNo=yesNoArrayListForKidney.get(position).getId();
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
                cancerYesNo=yesNoArrayListForCancer.get(position).getId();
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

    private void initSubSpinner() {
        controlDiseaseBrainStrokeArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseBrainStrokeModels);
        controlDiseaseBrainStrokeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_name_brain_stroke_disease.setAdapter(controlDiseaseBrainStrokeArrayAdapter);

        spinner_medicine_name_brain_stroke_disease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseBrainStroke=controlDiseaseBrainStrokeModels.get(position).getId();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        controlDiseaseLungsArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseLungsModels);
        controlDiseaseLungsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_name_control_lung_disease.setAdapter(controlDiseaseLungsArrayAdapter);

        spinner_medicine_name_control_lung_disease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseLungDisease=controlDiseaseLungsModels.get(position).getId();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        controlDiseaseAshmaArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseAshmaModels);
        controlDiseaseAshmaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_control_name_ashma.setAdapter(controlDiseaseAshmaArrayAdapter);

        spinner_medicine_control_name_ashma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseAshma=controlDiseaseAshmaModels.get(position).getId();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        controlDiseaseKidneyArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseKidneyModels);
        controlDiseaseKidneyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_control_name_kidney_disease.setAdapter(controlDiseaseKidneyArrayAdapter);

        spinner_medicine_control_name_kidney_disease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseKidney=controlDiseaseKidneyModels.get(position).getId();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        controlDiseaseCancerArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseCancerModels);
        controlDiseaseCancerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_control_name_cancer.setAdapter(controlDiseaseCancerArrayAdapter);

        spinner_medicine_control_name_cancer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseCancer=controlDiseaseCancerModels.get(position).getId();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        controlDiseaseMentalArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, controlDiseaseMentalModels);
        controlDiseaseMentalArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_medicine_name_mental_disorder.setAdapter(controlDiseaseMentalArrayAdapter);

        spinner_medicine_name_mental_disorder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Log.e("sp_water", "" + controlDiseaseBrainStrokeModels.get(position).getId());
                controlDiseaseMentalDisorder=controlDiseaseMentalModels.get(position).getId();

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
                mentalDisorderYesNo=yesNoArrayListForMental.get(position).getId();
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
            StringBuilder tag = new StringBuilder();
            for (String s : arrayList) {
                tag.append(s + ",");

            }

            String str = tag.toString();


            if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
                str = str.substring(0, str.length() - 1);
            }


            StringBuilder tagBlood = new StringBuilder();
            for (String s : arrayListBlood) {
                tagBlood.append(s + ",");

            }

            String strBlood = tagBlood.toString();


            if (strBlood != null && strBlood.length() > 0 && strBlood.charAt(strBlood.length() - 1) == ',') {
                strBlood = strBlood.substring(0, strBlood.length() - 1);
            }


            StringBuilder tagHeartAttack= new StringBuilder();
            for (String s : arrayListHeart) {
                tagHeartAttack.append(s + ",");

            }

            String strHeartAttack = tagHeartAttack.toString();


            if (strHeartAttack != null && strHeartAttack.length() > 0 && strHeartAttack.charAt(strHeartAttack.length() - 1) == ',') {
                strHeartAttack = strHeartAttack.substring(0, strHeartAttack.length() - 1);
            }

            StringBuilder tagBrainStroke= new StringBuilder();
            for (String s : arrayListHeart) {
                tagBrainStroke.append(s + ",");

            }

            String strBrainStrok = tagBrainStroke.toString();


            if (strBrainStrok != null && strBrainStrok.length() > 0 && strBrainStrok.charAt(strBrainStrok.length() - 1) == ',') {
                strBrainStrok = strBrainStrok.substring(0, strBrainStrok.length() - 1);
            }

            StringBuilder tagLung= new StringBuilder();
            for (String s : arrayListLung) {
                tagLung.append(s + ",");

            }

            String strLung = tagLung.toString();


            if (strLung != null && strLung.length() > 0 && strLung.charAt(strLung.length() - 1) == ',') {
                strLung = strLung.substring(0, strLung.length() - 1);
            }

            StringBuilder tagAshma= new StringBuilder();
            for (String s : arrayListAshma) {
                tagAshma.append(s + ",");

            }

            String strAshma = tagAshma.toString();


            if (strAshma != null && strAshma.length() > 0 && strAshma.charAt(strAshma.length() - 1) == ',') {
                strAshma = strAshma.substring(0, strAshma.length() - 1);
            }

            StringBuilder tagKidney= new StringBuilder();
            for (String s : arrayListKidney) {
                tagKidney.append(s + ",");

            }

            String strKidney = tagKidney.toString();


            if (strKidney != null && strKidney.length() > 0 && strKidney.charAt(strKidney.length() - 1) == ',') {
                strKidney = strKidney.substring(0, strKidney.length() - 1);
            }
            StringBuilder tagCancer= new StringBuilder();
            for (String s : arrayListCancer) {
                tagCancer.append(s + ",");

            }

            String strCancer= tagCancer.toString();


            if (strCancer != null && strCancer.length() > 0 && strCancer.charAt(strCancer.length() - 1) == ',') {
                strCancer = strCancer.substring(0, strCancer.length() - 1);
            }
            StringBuilder tagMental= new StringBuilder();
            for (String s : arrayListCancer) {
                tagMental.append(s + ",");

            }

            String strMental= tagMental.toString();


            if (strMental != null && strMental.length() > 0 && strMental.charAt(strMental.length() - 1) == ',') {
                strMental = strMental.substring(0, strMental.length() - 1);
            }

            MemberMedicine memberMedicine = new MemberMedicine();
            int id=Common.memberMyselfRepository.maxValue();

            MemberMyself memberMyself=Common.memberMyselfRepository.getMemberMyselfNo(id);
            memberMedicine.MemberMyselfPhoneNumber=memberMyself.MobileNumber;

            memberMedicine.DiabetisYesNo=diabetisYesNo;
            memberMedicine.DiabetisSufferingYear=Integer.parseInt(edit_diabetis_month.getText().toString());
            memberMedicine.DiabetisControlDisease=diabetis();
            memberMedicine.DiabetisMedicineName=str;

            memberMedicine.BloodPressureYesNo=bloodPressureYesNo;
            memberMedicine.DiabetisSufferingYear=Integer.parseInt(edit_yes_blood.getText().toString());
            memberMedicine.DiabetisControlDisease=bloodGroups();
            memberMedicine.DiabetisMedicineName=strBlood;

            memberMedicine.HeartAttackYesNo=heartAttackYesNo;
            memberMedicine.HeartAttackSufferingYear=Integer.parseInt(edit_yes_heart_attack.getText().toString());
            memberMedicine.HeartAttackControlDisease=heartAttack();
            memberMedicine.HeartAttackMedicineName=strHeartAttack;

            memberMedicine.BrainStrokeYesNo=brainStrokeYesNo;
            memberMedicine.BrainStrokeSufferingYear=Integer.parseInt(edit_yes_heart_brain_stroke.getText().toString());
            memberMedicine.BrainStrokeControlDisease=String.valueOf(controlDiseaseBrainStroke);
            memberMedicine.BrainStrokeMedicineName=strBrainStrok;

            memberMedicine.LungYesNo=lungYesNo;
            memberMedicine.LungSufferingYear=Integer.parseInt(edit_yes_lung_disease.getText().toString());
            memberMedicine.LungControlDisease=String.valueOf(controlDiseaseLungDisease);
            memberMedicine.LungMedicineName=strLung;

            memberMedicine.AshmaYesNo=ashmaYesNo;
            memberMedicine.AshmaSufferingYear=Integer.parseInt(edit_yes_ashma.getText().toString());
            memberMedicine.AshmaControlDisease=String.valueOf(controlDiseaseAshma);
            memberMedicine.AshmaMedicineName=strAshma;

            memberMedicine.KidneyYesNo=kidneyYesNo;
            memberMedicine.KidneySufferingYear=Integer.parseInt(edit_yes_kidney_disease.getText().toString());
            memberMedicine.KidneyControlDisease=String.valueOf(controlDiseaseKidney);
            memberMedicine.KidneyMedicineName=strKidney;

            memberMedicine.CancerYesNo=cancerYesNo;
            memberMedicine.CancerSufferingYear=Integer.parseInt(edit_yes_cancer.getText().toString());
            memberMedicine.CancerControlDisease=String.valueOf(controlDiseaseCancer);
            memberMedicine.KidneyMedicineName=strKidney;

            memberMedicine.MentalYesNo=mentalDisorderYesNo;
            memberMedicine.MentalSufferingYear=Integer.parseInt(edit_yes_mental_disorder.getText().toString());
            memberMedicine.MentalControlDisease=String.valueOf(controlDiseaseMentalDisorder);
            memberMedicine.MentalMedicineName=strMental;

            Common.memberMedicineRepository.insertToMemberMedicine(memberMedicine);
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

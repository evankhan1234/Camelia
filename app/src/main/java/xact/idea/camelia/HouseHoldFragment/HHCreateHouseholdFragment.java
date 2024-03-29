package xact.idea.camelia.HouseHoldFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Ward;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.isNullOrEmpty;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;


public class HHCreateHouseholdFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    Spinner spinner_division;
    Spinner spinner_district;
    Spinner spinner_upazila;
    Spinner spinner_union;
    Spinner spinner_ward;
    Spinner spinner_block;
    EditText edit_unique_id;
    EditText edit_household;
    EditText edit_sub_household;
    EditText edit_village;
    EditText edit_family_memeber;
    EditText edit_household_income;
    Button create;
    ArrayAdapter<Division> divisionArrayAdapter;
    ArrayAdapter<District> districtArrayAdapter;
    ArrayAdapter<Upazila> upazilaArrayAdapter;
    ArrayAdapter<Unions> unionArrayAdapter;
    ArrayAdapter<Ward> wardArrayAdapter;
    ArrayAdapter<Block> blockArrayAdapter;
    List<Division> divisionList = new ArrayList<>();
    List<District> districtyList = new ArrayList<>();
    List<Unions> unionList = new ArrayList<>();
    List<Ward> wardList = new ArrayList<>();
    List<Block> blockList = new ArrayList<>();
    List<Upazila> upazilaList = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    int DivisionId=-1;
    int DivisionCode;
    int DistrictId=-1;
    int DistrictCode;
    int UnionId = -1;
    int UnionCode = 0;
    int UpazilaId=-1;
    int UpazilaCode;
    int WardId=-1;
    int BlockId=-1;
    String frag;

    Auth auth;

    TextView tv_household;
    TextView tv_sub_household;
    TextView tv_unique_id;
    TextView tv_village;
    TextView tv_family_member;
    TextView tv_household_income;
    TextView tv_division;
    TextView tv_district;
    TextView tv_upazila;
    TextView tv_union;
    TextView tv_ward;
    TextView tv_block;
    ScrollView scroll;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hhcreate_household, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            frag = bundle.getString("frag", "");
            Log.e("Frgalk", "uniquKey" + frag);
        } else {
            frag = "";
        }
        // display();
        load();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                initalizeSpinner();
            }
        }, 300);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initView() {
        boolean isScrollListenerAdded=false;
        scroll = view.findViewById(R.id.scroll);
        tv_block = view.findViewById(R.id.tv_block);
        tv_ward = view.findViewById(R.id.tv_ward);
        tv_union = view.findViewById(R.id.tv_union);
        tv_upazila = view.findViewById(R.id.tv_upazila);
        tv_district = view.findViewById(R.id.tv_district);
        tv_division = view.findViewById(R.id.tv_division);
        tv_household_income = view.findViewById(R.id.tv_household_income);
        tv_village = view.findViewById(R.id.tv_village);
        tv_family_member = view.findViewById(R.id.tv_family_member);
        tv_unique_id = view.findViewById(R.id.tv_unique_id);
        tv_sub_household = view.findViewById(R.id.tv_sub_household);
        spinner_division = view.findViewById(R.id.spinner_division);
        spinner_district = view.findViewById(R.id.spinner_district);
        spinner_upazila = view.findViewById(R.id.spinner_upazila);
        spinner_union = view.findViewById(R.id.spinner_union);
        spinner_ward = view.findViewById(R.id.spinner_ward);
        spinner_block = view.findViewById(R.id.spinner_block);
        edit_unique_id = view.findViewById(R.id.edit_unique_id);
        edit_household = view.findViewById(R.id.edit_household);
        edit_sub_household = view.findViewById(R.id.edit_sub_household);
        edit_village = view.findViewById(R.id.edit_village);
        edit_family_memeber = view.findViewById(R.id.edit_family_memeber);
        edit_household_income = view.findViewById(R.id.edit_household_income);
        create = view.findViewById(R.id.create);
        tv_household = view.findViewById(R.id.tv_household);
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        updateView((String)Paper.book().read("language"));
//        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
//            @Override
//            public void onWindowFocusChanged(boolean hasFocus) {
//                InputMethodManager inputMethodManager = (InputMethodManager)
//                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                // Hide the soft keyboard
//                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//            }
//
//
//        });
//        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                InputMethodManager inputMethodManager = (InputMethodManager)
//                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                // Hide the soft keyboard
//                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//            }
//        });
//        view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//
//            }
//        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNullOrEmpty(edit_household.getText().toString()) ||  isNullOrEmpty(edit_family_memeber.getText().toString()) || isNullOrEmpty(edit_village.getText().toString()) || isNullOrEmpty(edit_household_income.getText().toString())) {
                    Toast.makeText(mActivity, "Please insert all field", Toast.LENGTH_SHORT).show();

                } else {

                    if(BlockId==-1 || DistrictId==-1 ||DivisionId==-1 ||UpazilaId==-1 ||UnionId==-1 ||WardId==-1  ){
                        Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();
                    }
                    else if (edit_household.getText().toString().length()<4){
                        Toast.makeText(mActivity, "Household Number Length must be 4 digit", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        HouseHold houseHold = new HouseHold();
                        houseHold.MemberId = 100000000;
                        houseHold.BlockId = BlockId;
                        houseHold.DistrictId = DistrictId;
                        houseHold.DivisionId = DivisionId;
                        houseHold.UpazilaId = UpazilaId;
                        houseHold.UnionId = UnionId;
                        houseHold.WordId = WardId;
                        houseHold.HH = edit_household.getText().toString();
                       // houseHold.SHH = Integer.parseInt(edit_sub_household.getText().toString());
                        houseHold.UniqueId = edit_unique_id.getText().toString();
                        houseHold.VillageName = edit_village.getText().toString();
                        houseHold.FamilyIncome = Double.parseDouble(edit_household_income.getText().toString());
                        houseHold.FamilyMember = Integer.parseInt(edit_family_memeber.getText().toString());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date);
                        String language=SharedPreferenceUtil.getLanguage(mActivity);
                        if (language.equals("en")){
                            houseHold.DateValue = currentDate;
                        }
                        else{
                            houseHold.DateValue = Utils.getValue(formatter.format(date));
                        }

                        houseHold.Date = date;
                        //  houseHold.Birthdate=ed;
                        SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

                        Common.householdRepository.insertToHouseHold(houseHold);
                        if (frag.equals("frag")) {
                            ((CCUserHomeActivity) getActivity()).backForDetails();
                        } else {
                            ((HouseholdHomeActivity) getActivity()).backForDetails();
                        }

                        InputMethodManager inputMethodManager = (InputMethodManager)
                                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        // Hide the soft keyboard
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Toast.makeText(mActivity, "Successfully Created", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        edit_household.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String values1 = "";
                String values2 = "";
                values1 = s.toString();
                values2 = edit_sub_household.getText().toString();

                //  int value2;
//
//                if (values1.length()==1) {
//                    values1 = "000" + values1;
//                } else if (values1.length()==2) {
//                    values1 = "00" + values1;
//
//                } else if (values1.length()==3) {
//                    values1 = "0" + values1;
//                } else if (values1.length()==4) {
//                    values1 = ""+values1;
//                } else {
//                    values1 = "";
//                }
//                if (values2.length() == 1) {
//                    values2 = "0" + values2;
//                } else if (values2.length() == 2) {
//                    values2 = "" + values2;
//                }  else {
//                    values2 = "";
//                }

                String union="";
                if(UnionId==0){
                    union="00";
                }
                else{
                    union= String.valueOf(UnionCode);
                }
                if (WardId==-1 ){
                    Toast.makeText(mActivity, "Please Select Ward ", Toast.LENGTH_SHORT).show();
                }
                else if ( BlockId==-1){
                    Toast.makeText(mActivity, "Please Select Block", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (DistrictCode<10){
                        String val = DivisionCode + "0" + DistrictCode + UpazilaCode  +"" + "" + union + "" + "" + WardId + "" + BlockId + "" + values1;
                        edit_unique_id.setText(val);
                    }
                    else{
                        String val = DivisionCode + "" + DistrictCode + UpazilaCode  +"" + "" + union + "" + "" + WardId + "" + BlockId + "" + values1;

                        edit_unique_id.setText(val);
                    }

                }

            }
        });
//        edit_sub_household.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String values1 = "";
//                String values2 = "";
//                values2 = s.toString();
//                values1 = edit_household.getText().toString();
//
//                //  int value2;
//
//                if (values1.length()==1) {
//                    values1 = "000" + values1;
//                } else if (values1.length()==2) {
//                    values1 = "00" + values1;
//
//                } else if (values1.length()==3) {
//                    values1 = "0" + values1;
//                } else if (values1.length()==4) {
//                    values1 = ""+values1;
//                } else {
//                    values1 = "";
//                }
//                if (values2.length() == 1) {
//                    values2 = "0" + values2;
//                } else if (values2.length() == 2) {
//                    values2 = "" + values2;
//                }  else {
//                    values2 = "";
//                }
//                String union="";
//                if(UnionId==0){
//                    union="00";
//                }
//                else{
//                    union= String.valueOf(UnionId);
//                }
//                String val = DivisionId + "" + DistrictId + "" + UpazilaId + "" + union + "" + WardId + "" + BlockId + "" + values1+ "" + values2;
//                edit_unique_id.setText(val);
//            }
//        });


    }


    private void updateView(String language) {
        Context context= LocaleHelper.setLocale(mActivity,language);
        Resources resources= context.getResources();
        tv_household.setText(resources.getString(R.string.household_number));
        edit_household.setHint(resources.getString(R.string.household_text));
        tv_sub_household.setText(resources.getString(R.string.sub_household));
        edit_sub_household.setHint(resources.getString(R.string.sub_household));
        tv_unique_id.setText(resources.getString(R.string.unique_id));
        edit_unique_id.setHint(resources.getString(R.string.unique_id_));
        tv_village.setText(resources.getString(R.string.village));
        edit_village.setHint(resources.getString(R.string.village_));
        tv_family_member.setText(resources.getString(R.string.no_family));
        edit_family_memeber.setHint(resources.getString(R.string.no_family_));
        tv_household_income.setText(resources.getString(R.string.household_income));
        edit_household_income.setHint(resources.getString(R.string.household_income_));
        tv_division.setText(resources.getString(R.string.division));
        tv_district.setText(resources.getString(R.string.district));
        tv_upazila.setText(resources.getString(R.string.upazila));
        tv_union.setText(resources.getString(R.string.union));
        tv_ward.setText(resources.getString(R.string.ward));
        tv_block.setText(resources.getString(R.string.block));
        create.setText(resources.getString(R.string.done));
    }

    private void listener() {
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + districtyList.get(position).DistrictId);
                try {
                    DistrictId = Integer.parseInt(districtyList.get(position).district_code);
                    DistrictCode = Integer.parseInt(districtyList.get(position).code);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    DistrictId=-1;
                }
                Log.e("wwwww2", "wwwww" + DistrictId);
                compositeDisposable.add(Common.upazilaRepository.getUpazilaItemById(DistrictId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Upazila>>() {
                    @Override
                    public void accept(List<Upazila> customers) throws Exception {
                        Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                        upazilaList = customers;
                        if (upazilaList.size()==0){
                            UpazilaId=-1;
                        }
                        dismissLoadingProgress();
                        upazilaArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, upazilaList);
                        upazilaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_upazila.setAdapter(upazilaArrayAdapter);
                        if (auth.upazila != null && !auth.upazila.equals("")) {
                            int div = Integer.parseInt(auth.upazila);

                            for (int i = 0; i < upazilaList.size(); i++) {
                                Log.e("EEEE", "EEE" + upazilaList.get(i).upazila_code);
                                if (Integer.parseInt(upazilaList.get(i).upazila_code) == div) {
                                    Log.e("EEEE", "EEE" + upazilaList.get(i).upazila_code);
                                    spinner_upazila.setSelection(i);
                                }
                            }
                        }


                    }
                }));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_upazila.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    UpazilaId = Integer.parseInt(upazilaList.get(position).upazila_code);
                    UpazilaCode = Integer.parseInt(upazilaList.get(position).code);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    UpazilaId=-1;
                }
                Log.e("wwwww3", "wwwww" + upazilaList.get(position).UpazilaId);
                compositeDisposable.add(Common.unionRepository.getUnionItemById(UpazilaId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Unions>>() {
                    @Override
                    public void accept(List<Unions> customers) throws Exception {
                        Log.e("Union", "UnionFor" + new Gson().toJson(customers));
                        unionList = customers;
                        if (unionList.size()==0){
                            UnionId=-1;
                        }
                        dismissLoadingProgress();
                        unionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, unionList);
                        unionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_union.setAdapter(unionArrayAdapter);
                        if (auth.union != null && !auth.union.equals("")) {
                            int div = Integer.parseInt(auth.union);

                            for (int i = 0; i < unionList.size(); i++) {
                                if (Integer.parseInt(unionList.get(i).union_code) == div) {
                                    spinner_union.setSelection(i);
                                }
                            }
                        }

                    }
                }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_union.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("unionss", "" + unionList.get(position).UnionId);
                try {
                    UnionId = Integer.parseInt(unionList.get(position).union_code);
                    UnionCode = Integer.parseInt(unionList.get(position).code);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    UnionId=1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + blockList.get(position).BlockId);
                BlockId = Integer.parseInt(blockList.get(position).block_code);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + wardList.get(position).WardId);
                WardId = Integer.parseInt(wardList.get(position).ward_code);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initalizeSpinner() {
        String s = SharedPreferenceUtil.getUserRole(mActivity);
        auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(mActivity));

        Log.e("Auth", "Auth" + auth.division);
        Log.e("Auth", "Auth" + auth.district);
        int currentPosition = 0;

        if (auth.division == null || auth.division.equals("")) {

            spinner_division.setEnabled(true);
        } else {

            spinner_division.setEnabled(false);


        }
        if (auth.district == null || auth.district.equals("")) {
            spinner_district.setEnabled(true);

        } else {
            spinner_district.setEnabled(false);

        }
        if (auth.upazila == null || auth.upazila.equals("")) {
            spinner_upazila.setEnabled(true);

        } else {
            spinner_upazila.setEnabled(false);

        }
        if (auth.union == null || auth.union.equals("")) {
            spinner_union.setEnabled(true);

        } else {
            spinner_union.setEnabled(false);

        }
        if (auth.ward == null || auth.ward.equals("")) {
            spinner_ward.setEnabled(true);

        } else {
            spinner_ward.setEnabled(false);

        }
        if (auth.block == null || auth.block.equals("")) {
            spinner_block.setEnabled(true);

        } else {
            spinner_block.setEnabled(false);

        }
        if (auth.village == null || auth.village.equals("")) {

            edit_village.setFocusable(true);

        } else {
            edit_village.setFocusable(false);
            edit_village.setText(auth.village);

        }
        divisionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, divisionList);
        divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_division.setAdapter(divisionArrayAdapter);


//        for(Division division: divisionList){
//            if (division.DivisionId==div){
//
//                currentPosition=divisionList.indexOf(div);
//
//                 spinner_division.setSelection(currentPosition);
//            }
//        }

        blockArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, blockList);
        blockArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_block.setAdapter(blockArrayAdapter);


        wardArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, wardList);
        wardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ward.setAdapter(wardArrayAdapter);
        //spinner_division.setSelection(4);
        if (auth.division != null && !auth.division.equals("")) {
            int div = Integer.parseInt(auth.division);

            for (int i = 0; i < divisionList.size(); i++) {
                if (Integer.parseInt(divisionList.get(i).division_code) == div) {
                    spinner_division.setSelection(i);
                }
            }
        }


//        for(Division division: divisionList){
//            if (division.DivisionId==div){
//
//
//
//
//                currentPosition++;
//            }
//        }

        spinner_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    DivisionId = Integer.parseInt(divisionList.get(position).division_code);
                    DivisionCode = Integer.parseInt(divisionList.get(position).code);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    DivisionId=-1;
                }
                Log.e("wwwww1", "wwwww" + DivisionId);
                compositeDisposable.add(Common.districtRepository.getDistrictItemById(DivisionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<District>>() {
                    @Override
                    public void accept(List<District> customers) throws Exception {
                        Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                        districtyList = customers;
                        dismissLoadingProgress();
                        districtArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, districtyList);
                        districtArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_district.setAdapter(districtArrayAdapter);
                        if (auth.district != null && !auth.district.equals("")) {
                            int div = Integer.parseInt(auth.district);

                            for (int i = 0; i < districtyList.size(); i++) {
                                if (Integer.parseInt(districtyList.get(i).district_code)== div) {
                                    spinner_district.setSelection(i);
                                }
                            }
                        }


                    }
                }));
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listener();


        data();
    }

    private void data() {
        if (auth.ward != null && !auth.ward.equals("")) {
            int div = Integer.parseInt(auth.ward);

            for (int i = 0; i < wardList.size(); i++) {
                if (wardList.get(i).WardId == div) {
                    spinner_ward.setSelection(i);
                }
            }
        }
        if (auth.block != null && !auth.block.equals("")) {
            int div = Integer.parseInt(auth.block);

            for (int i = 0; i < blockList.size(); i++) {
                if (blockList.get(i).BlockId == div) {
                    spinner_block.setSelection(i);
                }
            }
        }
    }

    private void load() {
        showLoadingProgress(mActivity);
        compositeDisposable.add(Common.divisionRepository.getDivisionItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Division>>() {
            @Override
            public void accept(List<Division> customers) throws Exception {
                Log.e("Division", "Division" + new Gson().toJson(customers));
                divisionList = customers;

                dismissLoadingProgress();

            }
        }));


        compositeDisposable.add(Common.wardRepository.getWardItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Ward>>() {
            @Override
            public void accept(List<Ward> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                wardList = customers;
                dismissLoadingProgress();

            }
        }));

        compositeDisposable.add(Common.blockRepository.getBlockItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Block>>() {
            @Override
            public void accept(List<Block> customers) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(customers));
                blockList = customers;
                dismissLoadingProgress();


            }
        }));

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("loadload", "size" + divisionList.size());
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

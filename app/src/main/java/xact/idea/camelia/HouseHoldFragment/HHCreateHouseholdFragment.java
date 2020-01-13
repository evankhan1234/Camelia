package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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
import xact.idea.camelia.Model.DropDownModel.SexModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

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
    List<Division> divisionList= new ArrayList<>();
    List<District> districtyList= new ArrayList<>();
    List<Unions> unionList= new ArrayList<>();
    List<Ward> wardList= new ArrayList<>();
    List<Block> blockList= new ArrayList<>();
    List<Upazila> upazilaList= new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    int DivisionId;
    int DistrictId;
    int UnionId;
    int UpazilaId;
    int WardId;
    int BlockId;

    Auth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhcreate_household, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
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

    private void initView() {
        spinner_division=view.findViewById(R.id.spinner_division);
        spinner_district=view.findViewById(R.id.spinner_district);
        spinner_upazila=view.findViewById(R.id.spinner_upazila);
        spinner_union=view.findViewById(R.id.spinner_union);
        spinner_ward=view.findViewById(R.id.spinner_ward);
        spinner_block=view.findViewById(R.id.spinner_block);
        edit_unique_id=view.findViewById(R.id.edit_unique_id);
        edit_household=view.findViewById(R.id.edit_household);
        edit_sub_household=view.findViewById(R.id.edit_sub_household);
        edit_village=view.findViewById(R.id.edit_village);
        edit_family_memeber=view.findViewById(R.id.edit_family_memeber);
        edit_household_income=view.findViewById(R.id.edit_household_income);
        create=view.findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNullOrEmpty(edit_household.getText().toString()) && isNullOrEmpty(edit_sub_household.getText().toString()) && isNullOrEmpty(edit_family_memeber.getText().toString()) && isNullOrEmpty(edit_household_income.getText().toString())){
                    Toast.makeText(mActivity, "Please insert all field", Toast.LENGTH_SHORT).show();

                }
                else {

                    HouseHold houseHold= new HouseHold();
                    houseHold.MemberId=100000000;
                    houseHold.BlockId=BlockId;
                    houseHold.DistrictId=DistrictId;
                    houseHold.DivisionId=DivisionId;
                    houseHold.UpazilaId=UpazilaId;
                    houseHold.UnionId=UnionId;
                    houseHold.WordId=WardId;
                    houseHold.HH=Integer.parseInt(edit_household.getText().toString());
                    houseHold.SHH=Integer.parseInt(edit_sub_household.getText().toString());
                    houseHold.UniqueId=Integer.parseInt(edit_unique_id.getText().toString());
                    houseHold.VillageName=edit_village.getText().toString();
                    houseHold.FamilyIncome=Double.parseDouble(edit_household_income.getText().toString());
                    houseHold.FamilyMember=Integer.parseInt(edit_family_memeber.getText().toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date);
                    houseHold.DateValue=currentDate;
                    houseHold.Date=date;
                    //  houseHold.Birthdate=ed;

                    Common.householdRepository.insertToHouseHold(houseHold);
                    ((HouseholdHomeActivity) getActivity()).backForDetails();

                    Toast.makeText(mActivity, "Successfully Created", Toast.LENGTH_SHORT).show();
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

                String val=DivisionId+""+DistrictId+""+UpazilaId+""+WardId+""+BlockId+""+edit_sub_household.getText().toString();
                edit_unique_id.setText(val+s.toString());
            }
        });
        edit_sub_household.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String val=DivisionId+""+DistrictId+""+UpazilaId+""+WardId+""+BlockId+""+edit_household.getText().toString();
                edit_unique_id.setText(val+s.toString());
            }
        });


    }

    private void listener(){
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + districtyList.get(position).DistrictId);
                DistrictId=districtyList.get(position).DistrictId;
                Log.e("wwwww2", "wwwww" + DistrictId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_upazila.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                UpazilaId=upazilaList.get(position).UpazilaId;
                Log.e("wwwww3", "wwwww" + upazilaList.get(position).UpazilaId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_union.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + unionList.get(position).UnionId);
                UnionId=unionList.get(position).UnionId;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + blockList.get(position).BlockId);
                BlockId=blockList.get(position).BlockId;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + wardList.get(position).WardId);
                WardId=wardList.get(position).WardId;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DivisionId=divisionList.get(position).DivisionId;
                Log.e("wwwww1", "wwwww" + DivisionId);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initalizeSpinner() {
        String s= SharedPreferenceUtil.getUserRole(mActivity);
        auth=Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(mActivity));

        if (auth.division==null || auth.division.equals("")){

            spinner_division.setEnabled(true);
        }
        else {
            spinner_division.setEnabled(false);

        }
        if (auth.district==null || auth.district.equals("")){
            spinner_district.setEnabled(true);

        }
        else {
            spinner_district.setEnabled(false);

        }
        if (auth.upazila==null || auth.upazila.equals("")){
            spinner_upazila.setEnabled(true);

        }
        else {
            spinner_upazila.setEnabled(false);

        }
        if (auth.union==null || auth.union.equals("")){
            spinner_union.setEnabled(true);

        }
        else {
            spinner_union.setEnabled(false);

        }
        if (auth.ward==null || auth.ward.equals("")){
            spinner_ward.setEnabled(true);

        }
        else {
            spinner_ward.setEnabled(false);

        }
        if (auth.block==null || auth.block.equals("")){
            spinner_block.setEnabled(true);

        }
        else {
            spinner_block.setEnabled(false);

        }
        if (auth.village==null || auth.village.equals("")){

            edit_village.setFocusable(true);

        }
        else {
            edit_village.setFocusable(false);
            edit_village.setText(auth.village);

        }
        divisionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, divisionList);
        divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_division.setAdapter(divisionArrayAdapter);


        districtArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, districtyList);
        districtArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_district.setAdapter(districtArrayAdapter);

        blockArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, blockList);
        blockArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_block.setAdapter(blockArrayAdapter);


        unionArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, unionList);
        unionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_union.setAdapter(unionArrayAdapter);


        upazilaArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, upazilaList);
        upazilaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_upazila.setAdapter(upazilaArrayAdapter);


        wardArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, wardList);
        wardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ward.setAdapter(wardArrayAdapter);

        listener();



    }
    private  void load() {
        showLoadingProgress(mActivity);
        compositeDisposable.add(Common.divisionRepository.getDivisionItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Division>>() {
            @Override
            public void accept(List<Division> customers) throws Exception {
                Log.e("Division","Division"+new Gson().toJson(customers));
                  divisionList=customers;

                dismissLoadingProgress();

            }
        }));
        compositeDisposable.add(Common.districtRepository.getDistrictItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<District>>() {
            @Override
            public void accept(List<District> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                districtyList=customers;
                dismissLoadingProgress();

            }
        }));
//
        compositeDisposable.add(Common.upazilaRepository.getUpazilaItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Upazila>>() {
            @Override
            public void accept(List<Upazila> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                upazilaList=customers;
                dismissLoadingProgress();

            }
        }));

        compositeDisposable.add(Common.unionRepository.getUnionItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Unions>>() {
            @Override
            public void accept(List<Unions> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                unionList=customers;
                dismissLoadingProgress();
            }
        }));

        compositeDisposable.add(Common.wardRepository.getWardItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Ward>>() {
            @Override
            public void accept(List<Ward> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                wardList=customers;
                dismissLoadingProgress();

            }
        }));

        compositeDisposable.add(Common.blockRepository.getBlockItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Block>>() {
            @Override
            public void accept(List<Block> customers) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(customers));
                blockList=customers;
                dismissLoadingProgress();


            }
        }));

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("loadload","size"+divisionList.size());
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

package xact.idea.camelia.HouseHoldFragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.View.CustomViewPager;


public class HHCreateSurveyFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    ArrayAdapter<String> waterArrayAdapter;
    ArrayAdapter<String> biomasArrayAdapter;
    Spinner spinner_drinking_water;
    Spinner spinner_biomas;
    ArrayList<String> waterArrayLit= new ArrayList<>();
    ArrayList<String> biomasArrayLit= new ArrayList<>();
    RadioButton radioDrinkingYes;
    RadioButton radioDrinkingNo;
    RadioButton radioSanitaryLatrineNo;
    RadioButton radioSanitaryLatrineYes;
    RadioButton radioBondhoChulaYes;
    RadioButton radioBondhoChulaNo;
    RadioButton radioBiomassFuelYes;
    RadioButton radioBiomassFuelNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhcreate_survey, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        // display();
        return view;
    }

    private void initView() {

        biomasArrayLit.add(mActivity.getResources().getString(R.string.kath));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.patkhori));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.gobor));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.fosol));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.kura));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.alchocol));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.kerosin_chula));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.land_fill_gas));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.electric_chula));
        biomasArrayLit.add(mActivity.getResources().getString(R.string.ittadi));
        waterArrayLit.add(mActivity.getResources().getString(R.string.tubewell));
        waterArrayLit.add(mActivity.getResources().getString(R.string.filter_water));
        waterArrayLit.add(mActivity.getResources().getString(R.string.tap_water));
        waterArrayLit.add(mActivity.getResources().getString(R.string.boil_water));
        waterArrayLit.add(mActivity.getResources().getString(R.string.chlorine_water));
        spinner_drinking_water=view.findViewById(R.id.spinner_drinking_water);
        radioDrinkingYes=view.findViewById(R.id.radioDrinkingYes);
        radioSanitaryLatrineYes=view.findViewById(R.id.radioSanitaryLatrineYes);
        radioSanitaryLatrineNo=view.findViewById(R.id.radioSanitaryLatrineNo);
        radioBondhoChulaYes=view.findViewById(R.id.radioBondhoChulaYes);
        radioBondhoChulaNo=view.findViewById(R.id.radioBondhoChulaNo);
        radioBiomassFuelYes=view.findViewById(R.id.radioBiomassFuelYes);
        radioBiomassFuelNo=view.findViewById(R.id.radioBiomassFuelNo);
        spinner_biomas=view.findViewById(R.id.spinner_biomas);
        radioDrinkingYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_drinking_water.setVisibility(View.VISIBLE);
            }
        });
        radioDrinkingNo=view.findViewById(R.id.radioDrinkingNo);
        radioDrinkingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_drinking_water.setVisibility(View.GONE);
            }
        });
        radioBiomassFuelYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.VISIBLE);
            }
        });

        radioBiomassFuelNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.GONE);
            }
        });
        biomasArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, biomasArrayLit);
        biomasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_biomas.setAdapter(biomasArrayAdapter);
        waterArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, waterArrayLit);
        waterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_drinking_water.setAdapter(waterArrayAdapter);

        spinner_drinking_water.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + waterArrayLit.get(position));
               // Name = customerArrayList.get(position).Name;



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

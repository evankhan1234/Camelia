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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Activity.MainActivity;
import xact.idea.camelia.Database.Model.Survey;
import xact.idea.camelia.Model.DropDownModel.BiomasFuelModel;
import xact.idea.camelia.Model.DropDownModel.TubewellModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.Utils;
import xact.idea.camelia.View.CustomViewPager;


public class HHCreateSurveyFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    TextView text_date_current;
    ArrayAdapter<TubewellModel> waterArrayAdapter;
    ArrayAdapter<BiomasFuelModel> biomasArrayAdapter;
    Spinner spinner_drinking_water;
    Spinner spinner_biomas;
    ArrayList<TubewellModel> waterArrayLit= new ArrayList<>();
    ArrayList<BiomasFuelModel> biomasArrayLit= new ArrayList<>();
    RadioButton radioDrinkingYes;
    RadioButton radioDrinkingNo;
    RadioButton radioSanitaryLatrineNo;
    RadioButton radioSanitaryLatrineYes;
    RadioButton radioBondhoChulaYes;
    RadioButton radioBondhoChulaNo;
    RadioButton radioBiomassFuelYes;
    RadioButton radioBiomassFuelNo;
    Button create;
    int tubeWellId;
    int biomasFuelId;
    String uniquKey;


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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            uniquKey = bundle.getString("Id", "");
            Log.e("UniqueId","uniquKey"+uniquKey);
        }
        // display();
        return view;
    }

    private void initView() {

        waterArrayLit= Utils.getTubewellList(mActivity);
        biomasArrayLit= Utils.getBiomasFuelList(mActivity);

        create=view.findViewById(R.id.create);
        text_date_current=view.findViewById(R.id.text_date_current);
        spinner_drinking_water=view.findViewById(R.id.spinner_drinking_water);
        radioDrinkingYes=view.findViewById(R.id.radioDrinkingYes);
        radioSanitaryLatrineYes=view.findViewById(R.id.radioSanitaryLatrineYes);
        radioSanitaryLatrineNo=view.findViewById(R.id.radioSanitaryLatrineNo);
        radioBondhoChulaYes=view.findViewById(R.id.radioBondhoChulaYes);
        radioBondhoChulaNo=view.findViewById(R.id.radioBondhoChulaNo);
        radioBiomassFuelYes=view.findViewById(R.id.radioBiomassFuelYes);
        radioBiomassFuelNo=view.findViewById(R.id.radioBiomassFuelNo);
        spinner_biomas=view.findViewById(R.id.spinner_biomas);

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        Date date = new Date(System.currentTimeMillis());
        text_date_current.setText(formatter.format(date));
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Survey survey= new Survey();
                survey.UniqueId=uniquKey;
                Date date = new Date(System.currentTimeMillis());
                survey.CreatedDate=date;
                if (radioDrinkingYes.isChecked()){
                    survey.SafeDrinkingYesNo=1;
                    survey.SafeDrinkingDetails=tubeWellId;
                }

                if (radioDrinkingNo.isChecked()){
                    survey.SafeDrinkingYesNo=2;
                }
                if (radioSanitaryLatrineYes.isChecked()){
                    survey.SanitaryYesNo=1;
                }

                if (radioSanitaryLatrineNo.isChecked()){
                    survey.SanitaryYesNo=2;
                }

                if (radioBondhoChulaYes.isChecked()){
                    survey.BondhoChulaYesNo=1;
                }

                if (radioBondhoChulaNo.isChecked()){
                    survey.BondhoChulaYesNo=2;
                }

                if (radioBiomassFuelYes.isChecked()){
                    survey.BiomasFuelYesNo=1;
                    survey.BiomasFuelDetails=biomasFuelId;
                }

                if (radioBiomassFuelNo.isChecked()){
                    survey.BiomasFuelYesNo=2;
                }

                Common.surveyRepository.insertToSurvey(survey);
                ((HouseholdHomeActivity) getActivity()).backForDetails();
            }
        });
        radioDrinkingYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_drinking_water.setVisibility(View.VISIBLE);

                spinner_drinking_water.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + waterArrayLit.get(position));
                        // Name = customerArrayList.get(position).Name;

                        tubeWellId=waterArrayLit.get(position).getId();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
        radioDrinkingNo=view.findViewById(R.id.radioDrinkingNo);
        radioDrinkingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_drinking_water.setVisibility(View.GONE);
                tubeWellId=0;
            }
        });
        radioBiomassFuelYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.VISIBLE);

                spinner_biomas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + biomasArrayLit.get(position));
                        // Name = customerArrayList.get(position).Name;

                        biomasFuelId=biomasArrayLit.get(position).getId();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        radioBiomassFuelNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.GONE);
                biomasFuelId=0;
            }
        });
        biomasArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, biomasArrayLit);
        biomasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_biomas.setAdapter(biomasArrayAdapter);
        waterArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, waterArrayLit);
        waterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_drinking_water.setAdapter(waterArrayAdapter);



    }

}

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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Activity.MainActivity;
import xact.idea.camelia.Database.Model.Questions;
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
    int id;
    int biomasFuelId;
    String uniquKey;
    Survey surveys;
    String types;
    String frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhcreate_survey, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            uniquKey = bundle.getString("Id", "");
            types = bundle.getString("types", "");
            frag = bundle.getString("frag", "");
            id = bundle.getInt("SurveyId", 0);
            Log.e("fragsss","frag"+frag);
        }
        else{
            frag="";
        }
        initView();
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
        radioDrinkingNo=view.findViewById(R.id.radioDrinkingNo);
        radioSanitaryLatrineYes=view.findViewById(R.id.radioSanitaryLatrineYes);
        radioSanitaryLatrineNo=view.findViewById(R.id.radioSanitaryLatrineNo);
        radioBondhoChulaYes=view.findViewById(R.id.radioBondhoChulaYes);
        radioBondhoChulaNo=view.findViewById(R.id.radioBondhoChulaNo);
        radioBiomassFuelYes=view.findViewById(R.id.radioBiomassFuelYes);
        radioBiomassFuelNo=view.findViewById(R.id.radioBiomassFuelNo);
        spinner_biomas=view.findViewById(R.id.spinner_biomas);

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        Date date = new Date(System.currentTimeMillis());
        surveys=Common.surveyRepository.getSurvey(String.valueOf(id));
        biomasArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, biomasArrayLit);
        biomasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_biomas.setAdapter(biomasArrayAdapter);
        waterArrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, waterArrayLit);
        waterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_drinking_water.setAdapter(waterArrayAdapter);
        if (surveys==null){
            text_date_current.setText(formatter.format(date));
        }
        else{
            Questions questionsFor1 = Common.qustionsRepository.getQuestions("Q12", String.valueOf(id));
            Questions questionsFor2 = Common.qustionsRepository.getQuestions("Q12a", String.valueOf(id));
            Questions questionsFor3 = Common.qustionsRepository.getQuestions("Q13", String.valueOf(id));
            Questions questionsFor4 = Common.qustionsRepository.getQuestions("Q14", String.valueOf(id));
            Questions questionsFor5 = Common.qustionsRepository.getQuestions("Q15", String.valueOf(id));
            Questions questionsFor6 = Common.qustionsRepository.getQuestions("Q15a", String.valueOf(id));

            if (questionsFor1!=null){

                if (questionsFor1.answer.equals("1")){
                    radioDrinkingYes.setChecked(true);
                    radioDrinkingNo.setChecked(false);
                    spinner_drinking_water.setVisibility(View.VISIBLE);

                    if (questionsFor2!=null){
                        if (!questionsFor2.answer.equals("0")) {
                            int div = Integer.parseInt(questionsFor2.answer);


                            for (int i = 0; i < waterArrayLit.size(); i++) {
                                if (waterArrayLit.get(i).getId() == div) {
                                    spinner_drinking_water.setSelection(i);
                                }
                            }
                        }
                    }

                }
                else{
                    radioDrinkingNo.setChecked(true);
                    radioDrinkingYes.setChecked(false);
                    spinner_drinking_water.setVisibility(View.GONE);
                }
            }
            if (questionsFor3!=null){

                if (questionsFor3.answer.equals("1")){

                    radioSanitaryLatrineYes.setChecked(true);
                    radioSanitaryLatrineNo.setChecked(false);
                }
                else{
                    radioSanitaryLatrineNo.setChecked(true);
                    radioSanitaryLatrineYes.setChecked(false);
                }
            }
            if (questionsFor4!=null){

                if (questionsFor4.answer.equals("1")){
                    radioBondhoChulaYes.setChecked(true);
                    radioBondhoChulaNo.setChecked(false);
                }
                else {
                    radioBondhoChulaNo.setChecked(true);
                    radioBondhoChulaYes.setChecked(false);
                }
            }
            if (questionsFor5!=null){

                if (questionsFor5.answer.equals("1")){
                    radioBiomassFuelYes.setChecked(true);
                    radioBiomassFuelNo.setChecked(false);

                    spinner_biomas.setVisibility(View.VISIBLE);
                    if (questionsFor6!=null){

                        if (!questionsFor6.answer.equals("0")){
                            int div = Integer.parseInt(questionsFor6.answer);


                            for (int i = 0; i < biomasArrayLit.size(); i++) {
                                if (biomasArrayLit.get(i).getId() == div) {
                                    spinner_biomas.setSelection(i);
                                }
                            }
                        }
                    }

                }
                else {
                    radioBiomassFuelNo.setChecked(true);
                    radioBiomassFuelYes.setChecked(false);
                    spinner_biomas.setVisibility(View.GONE);
                }
            }

            text_date_current.setText(formatter.format(surveys.CreatedDate));
            if (surveys.SafeDrinkingYesNo==1){

            }
            else if (surveys.SafeDrinkingYesNo==2){

            }

            if (surveys.SanitaryYesNo==1){

            }
            else if (surveys.SanitaryYesNo==2){

            }
            if (surveys.BondhoChulaYesNo==1){

            }
            else if (surveys.BondhoChulaYesNo==2){

            }
            if (surveys.BiomasFuelYesNo==1){

            }
            else if (surveys.BiomasFuelYesNo==2){

            }
        }


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id==0){

                    int a=0;

                    String safeWater="";
                    String sanitary="";
                    String chula="";
                    String biomas="";
                    int b=0;
                    Survey survey= new Survey();
                    survey.UniqueId=uniquKey;
                    Date date = new Date(System.currentTimeMillis());
                    survey.CreatedDate=date;
                    if (radioDrinkingYes.isChecked()){
                        survey.SafeDrinkingYesNo=1;
                        survey.SafeDrinkingDetails=tubeWellId;
                        safeWater="1";
                        a=tubeWellId;
                    }


                    if (radioDrinkingNo.isChecked()){
                        survey.SafeDrinkingYesNo=2;
                        safeWater="2";
                        tubeWellId=0;
                    }
                    if (radioSanitaryLatrineYes.isChecked()){
                        survey.SanitaryYesNo=1;
                        sanitary="1";
                    }

                    if (radioSanitaryLatrineNo.isChecked()){
                        survey.SanitaryYesNo=2;
                        sanitary="2";
                    }

                    if (radioBondhoChulaYes.isChecked()){
                        survey.BondhoChulaYesNo=1;
                        chula="1";
                    }

                    if (radioBondhoChulaNo.isChecked()){
                        survey.BondhoChulaYesNo=2;
                        chula="2";
                    }

                    if (radioBiomassFuelYes.isChecked()){
                        survey.BiomasFuelYesNo=1;
                        survey.BiomasFuelDetails=biomasFuelId;
                        b=biomasFuelId;
                        biomas="1";
                    }

                    if (radioBiomassFuelNo.isChecked()){
                        survey.BiomasFuelYesNo=2;
                        biomas="2";
                        biomasFuelId=0;
                    }

                    if (a==-1|| b==-1){
                        Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Common.surveyRepository.insertToSurvey(survey);
                        int memberId= Common.surveyRepository.maxValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                         Date date12 = new Date(System.currentTimeMillis());
                        String currentDate = formatter.format(date12);
                        Questions questions1 = new Questions();
                        questions1.type = "survey";
                        questions1.question = "Q12";
                        questions1.member_id = String.valueOf(memberId);
                        questions1.answer = safeWater;
                        questions1.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions1);

                        Questions questions2 = new Questions();
                        questions2.type = "survey";
                        questions2.question = "Q12a";
                        questions2.member_id = String.valueOf(memberId);
                        questions2.answer = String.valueOf(tubeWellId);
                        questions2.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions2);
                        Questions questions3 = new Questions();
                        questions3.type = "survey";
                        questions3.question = "Q13";
                        questions3.member_id = String.valueOf(memberId);
                        questions3.answer = sanitary;
                        questions3.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions3);

                        Questions questions4 = new Questions();
                        questions4.type = "survey";
                        questions4.question = "Q14";
                        questions4.member_id = String.valueOf(memberId);
                        questions4.answer = chula;
                        questions4.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions4);
                        Questions questions5 = new Questions();
                        questions5.type = "survey";
                        questions5.question = "Q15";
                        questions5.member_id = String.valueOf(memberId);
                        questions5.answer = biomas;
                        questions5.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions5);

                        Questions questions6 = new Questions();
                        questions6.type = "survey";
                        questions6.question = "Q15a";
                        questions6.member_id = String.valueOf(memberId);
                        questions6.answer = String.valueOf(biomasFuelId);
                        questions6.date = currentDate;
                        Common.qustionsRepository.insertToQuestions(questions6);

                        if (frag.equals("frag")){
                            ((CCUserHomeActivity) getActivity()).backForDetails();
                        }
                        else {
                            ((HouseholdHomeActivity) getActivity()).backForDetails();
                        }

                    }

                }
                else {
                    int a=0;
                    int b=0;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date12 = new Date(System.currentTimeMillis());
                    String currentDate = formatter.format(date12);
                    String safeWater="";
                    String sanitary="";
                    String chula="";
                    String biomas="";
                    Survey survey= new Survey();
                    survey.id=surveys.id;
                    survey.UniqueId=uniquKey;
                    Date date = new Date(System.currentTimeMillis());
                    survey.CreatedDate=date;
                    if (radioDrinkingYes.isChecked()){
                        survey.SafeDrinkingYesNo=1;
                        survey.SafeDrinkingDetails=tubeWellId;
                        safeWater="1";
                        a=tubeWellId;
                    }


                    if (radioDrinkingNo.isChecked()){
                        survey.SafeDrinkingYesNo=2;
                        safeWater="2";
                        tubeWellId=0;
                    }
                    if (radioSanitaryLatrineYes.isChecked()){
                        survey.SanitaryYesNo=1;
                        sanitary="1";
                    }

                    if (radioSanitaryLatrineNo.isChecked()){
                        survey.SanitaryYesNo=2;
                        sanitary="2";
                    }

                    if (radioBondhoChulaYes.isChecked()){
                        survey.BondhoChulaYesNo=1;
                        chula="1";
                    }

                    if (radioBondhoChulaNo.isChecked()){
                        survey.BondhoChulaYesNo=2;
                        chula="2";
                    }

                    if (radioBiomassFuelYes.isChecked()){
                        survey.BiomasFuelYesNo=1;
                        survey.BiomasFuelDetails=biomasFuelId;
                        b=biomasFuelId;
                        biomas="1";
                    }

                    if (radioBiomassFuelNo.isChecked()){
                        survey.BiomasFuelYesNo=2;
                        biomas="2";
                        biomasFuelId=0;
                    }


                    if (radioBiomassFuelNo.isChecked()){
                        survey.BiomasFuelYesNo=2;
                    }
                    if (a==-1|| b==-1){
                        Toast.makeText(mActivity, "Please Select", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Questions questionsFor1 = Common.qustionsRepository.getQuestions("Q12", String.valueOf(id));
                        Questions questionsFor2 = Common.qustionsRepository.getQuestions("Q12a", String.valueOf(id));
                        Questions questionsFor3 = Common.qustionsRepository.getQuestions("Q13", String.valueOf(id));
                        Questions questionsFor4 = Common.qustionsRepository.getQuestions("Q14", String.valueOf(id));
                        Questions questionsFor5 = Common.qustionsRepository.getQuestions("Q15", String.valueOf(id));
                        Questions questionsFor6 = Common.qustionsRepository.getQuestions("Q15a", String.valueOf(id));

                        if (questionsFor1!=null){
                            Questions questions1 = new Questions();
                            questions1.type = "survey";
                            questions1.question = "Q12";
                            questions1.member_id = questionsFor1.member_id;
                            questions1.answer = safeWater;
                            questions1.date = currentDate;
                            questions1.id = questionsFor1.id;
                            Common.qustionsRepository.updateQuestions(questions1);

                        }

                        if (questionsFor2!=null){
                            Questions questions2 = new Questions();
                            questions2.type = "survey";
                            questions2.question = "Q12a";
                            questions2.member_id = questionsFor2.member_id;
                            questions2.answer = String.valueOf(tubeWellId);
                            questions2.date = currentDate;
                            questions2.id = questionsFor2.id;
                            Common.qustionsRepository.updateQuestions(questions2);
                        }

                        if (questionsFor3!=null){
                            Questions questions3 = new Questions();
                            questions3.type = "survey";
                            questions3.question = "Q13";
                            questions3.member_id = questionsFor3.member_id;
                            questions3.answer = sanitary;
                            questions3.date = currentDate;
                            questions3.id = questionsFor3.id;
                            Common.qustionsRepository.updateQuestions(questions3);

                        }
                        if (questionsFor4!=null){
                            Questions questions4 = new Questions();
                            questions4.type = "survey";
                            questions4.question = "Q14";
                            questions4.member_id = questionsFor4.member_id;
                            questions4.answer = chula;
                            questions4.date = currentDate;
                            questions4.id = questionsFor4.id;
                            Common.qustionsRepository.updateQuestions(questions4);
                        }
                        if (questionsFor5!=null){
                            Questions questions5 = new Questions();
                            questions5.type = "survey";
                            questions5.question = "Q15";
                            questions5.member_id = questionsFor5.member_id;
                            questions5.answer = biomas;
                            questions5.date = currentDate;
                            questions5.id = questionsFor5.id;
                            Common.qustionsRepository.updateQuestions(questions5);

                        }
                        if (questionsFor6!=null){
                            Questions questions6 = new Questions();
                            questions6.type = "survey";
                            questions6.question = "Q15a";
                            questions6.member_id = questionsFor6.member_id;
                            questions6.answer = String.valueOf(biomasFuelId);
                            questions6.date = currentDate;
                            questions6.id = questionsFor6.id;
                            Common.qustionsRepository.updateQuestions(questions6);
                        }
                        else {
                            Questions questions6 = new Questions();
                            questions6.type = "survey";
                            questions6.question = "Q15a";
                            questions6.member_id = questionsFor6.member_id;
                            questions6.answer = String.valueOf(biomasFuelId);
                            questions6.date = currentDate;
                            Common.qustionsRepository.insertToQuestions(questions6);
                        }
                        Common.surveyRepository.updateSurvey(survey);
                        if (frag.equals("frag")){
                            ((CCUserHomeActivity) getActivity()).backForDetails();
                        }
                        else {
                            ((HouseholdHomeActivity) getActivity()).backForDetails();
                        }
                    }

                }

            }
        });
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
                tubeWellId=0;
            }
        });
        radioBiomassFuelYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.VISIBLE);


            }
        });
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
        radioBiomassFuelNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner_biomas.setVisibility(View.GONE);
                biomasFuelId=0;
            }
        });




    }

}

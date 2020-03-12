package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCMeasurementsDetailsFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    Measurements measurements;
    int position;
    String member;
    TextView text1;
    TextView text2;
    Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ccmeasurements_details, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        // display();
        return view;
    }

    private void initView() {
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        button1 = view.findViewById(R.id.button1);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("Id", 0);
            member = bundle.getString("member", null);

//            Log.e("position", "position" + measurements.Message);
        }
        measurements = Common.measurementsRepository.getMeasurementsNo(String.valueOf(position));
        text1.setText(measurements.Message);

        if (measurements.Type.equals("Diastolic")) {
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);
            if (questions1.answer.equals("1")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("Your BP is mild high. It is an At Risk condition, please take precaution to control it at this stage and monitor your blood pressure regularly.");
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("NORMAL blood pressure. Please maintain this pressure by regular physical activity and healthy diet.");

                }
            }
            else if (questions1.answer.equals("2")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("You have developed HYPERTENSION. Please take necessary steps to control it at this stage,  consult a physician for further management and monitor your blood pressure regularly.");
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("You have NORMAL blood pressure. Please maintain this pressure by regular physical activity and healthy diet.");

                }
            }


        }
        else if (measurements.Type.equals("Systolic")){
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);
            if (questions1.answer.equals("1")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("Your BP is mild high. It is an At Risk condition, please take precaution to control it at this stage and monitor your blood pressure regularly.");
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("NORMAL blood pressure. Please maintain this pressure by regular physical activity and healthy diet.");

                }
            }
            else if (questions1.answer.equals("2")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("You have developed HYPERTENSION. Please take necessary steps to control it at this stage,  consult a physician for further management and monitor your blood pressure regularly.");
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("You have NORMAL blood pressure. Please maintain this pressure by regular physical activity and healthy diet.");

                }
            }
        }
        else if (measurements.Type.equals("BMI")){
            if (measurements.Message.equals("UnderWeight")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                text2.setText("You are UNDERWEIGHT, this may cause several health related problems, please consult with physician or nutritionist.");
            }
            else  if (measurements.Message.equals("Normal")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                text2.setText("Your BMI is in NORMAL, to maintain this level - do regular physical activity and eat balanced diet — both of which help you look and feel good and keep weight off.");
            }
            else  if (measurements.Message.equals("OverWeight")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                text2.setText("You are OVERWEIGHT, please consult with nutritionist or physician for weight reduction. Avoid taking oily & fatty food and do regular physical activity.");
            }
            else if (measurements.Message.equals("Obese")){
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                text2.setText("You are OBESE. Being OBESE you are in high risk to develop hypertension, cardiovascular disease, diabetes, please consult with nutritionist or physician for weight reduction.");
            }
        }
        else if (measurements.Type.equals("Diabetes")){
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);

            if (questions1.answer.equals("1")){
                if (measurements.Message.equals("Uncontrolled Diabetes = Refer to UHC!")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("HIGH blood sugar, immediately consult with doctor, your medicine may need to be adjusted or changed.");
                }
                else if (measurements.Message.equals("Controlled Diabetes = Follow up 6 months.")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("NORMAL blood sugar level, to maintain this level - do regular physical activity and eat balanced diet — both of which help you look and feel good and keep diabetes in control.");
                }

            }
            else  if (questions1.answer.equals("2")){
                if (measurements.Message.equals("Diabetes = Refer to UHC!")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText("You have HIGH blood sugar, you may have diabetes, immediately consult with doctor to confirm your diagnosis.");
                }
                else if (measurements.Message.equals("Controlled Diabetes = Follow up 6 months.")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText("You have NORMAL blood sugar level, to maintain this level - do regular physical activity and eat balanced diet which will help you to keep diabetes in control.");
                }
                else if (measurements.Message.equals("Pre-diabetic = Next week follow-up")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                    text2.setText("You are in PRE-DIABETIC stage, you may develop diabetes at any time, consult with doctor, do regular physical activity and eat balanced diet which will help you to keep blood sugar in control.\n");
                }
            }


        }
        else if (measurements.Type.equals("WHR")){
            text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            text2.setText("Your WHR is NORMAL, to maintain this level - do regular physical activity and eat balanced diet.");

        }
        else if (measurements.Type.equals("Pulse")){
            text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            text2.setText("Your Waist circumference is in NORMAL, to maintain this level - do regular physical activity and eat balanced diet");

        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CCUserHomeActivity) getActivity()).backForDetails();
            }
        });

    }

    public int handle() {


        if (getChildFragmentManager().findFragmentByTag(CCWaistWidthFragment.class.getSimpleName()) != null) {
            CCWaistWidthFragment f = (CCWaistWidthFragment) getChildFragmentManager()
                    .findFragmentByTag(CCWaistWidthFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        return 0;
    }
}

package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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

import io.paperdb.Paper;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

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
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        button1.setText(resources.getString(R.string.done));
        if (measurements.Type.equals("Diastolic")) {
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);
            if (questions1.answer.equals("1")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diastolic_1));
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText(resources.getString(R.string.diastolic_2));
                }
            }
            else if (questions1.answer.equals("2")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diastolic_3));
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText(resources.getString(R.string.diastolic_4));

                }
            }


        }
        else if (measurements.Type.equals("Systolic")){
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);
            if (questions1.answer.equals("1")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diastolic_1));
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText(resources.getString(R.string.diastolic_2));
                }
            }
            else if (questions1.answer.equals("2")) {
                if (measurements.Message.equals("Hypertension = Refer to UHC!")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diastolic_3));
                } else if (measurements.Message.equals("Normal = Follow up 6 months.")) {
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText(resources.getString(R.string.diastolic_4));

                }
            }
        }
        else if (measurements.Type.equals("BMI")){
            if (measurements.Message.equals("UnderWeight")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                text2.setText(resources.getString(R.string.underweight_message));
            }
            else  if (measurements.Message.equals("Normal")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                text2.setText(resources.getString(R.string.normal_message));
            }
            else  if (measurements.Message.equals("OverWeight")) {
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                text2.setText(resources.getString(R.string.overweight_message));
       }
            else if (measurements.Message.equals("Obese")){
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                text2.setText(resources.getString(R.string.obese_message));
            }
        }
        else if (measurements.Type.equals("Diabetes")){
            Questions questions1 = Common.qustionsRepository.getQuestions("Q50", member);
            if (questions1.answer.equals("1")){
                if (measurements.Message.equals("Uncontrolled Diabetes = Refer to UHC!")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diabetis_1));
                }
                else if (measurements.Message.equals("Controlled Diabetes = Follow up 6 months.")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                    text2.setText(resources.getString(R.string.diabetis_2));                }

            }
            else  if (questions1.answer.equals("2")){
                if (measurements.Message.equals("Diabetes = Refer to UHC!")){
                    text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
                    text2.setText(resources.getString(R.string.diabetis_3));                }
            }
            else if (measurements.Message.equals("Controlled Diabetes = Follow up 6 months.")){
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
                text2.setText(resources.getString(R.string.diabetis_4));            }
            else if (measurements.Message.equals("Pre-diabetic = Next week follow-up")){
                text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_processing));
                text2.setText(resources.getString(R.string.diabetis_5));
            }





        }
        else if (measurements.Type.equals("WHR")){
            text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            text2.setText(resources.getString(R.string.whr_1));

        }
        else if (measurements.Type.equals("Pulse")){
            text1.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            text2.setText(resources.getString(R.string.pulse_1));

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

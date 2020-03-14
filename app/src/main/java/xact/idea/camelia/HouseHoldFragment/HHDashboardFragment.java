package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.paperdb.Paper;
import xact.idea.camelia.Database.AnotherModel.HHDashboardModel;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;


public class HHDashboardFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;

    TextView tv_date;
    TextView tv_total_member;
    TextView tv_male;
    TextView tv_female;
    TextView tv_pregnant;
    TextView tv_diabetes;
    TextView tv_hypertension;
    TextView tv_heart_disease;
    TextView tv_stroke;
    TextView tv_lung;
    TextView tv_ashma;
    TextView tv_kidney;
    TextView tv_disability;
    TextView tv_cancer;
    TextView tv_1;
    TextView tv_2;
    TextView tv_3;
    TextView tv_4;
    TextView tv_5;
    TextView tv_6;
    TextView tv_7;
    TextView tv_8;
    TextView tv_9;
    TextView tv_10;
    TextView tv_11;
    TextView tv_12;
    TextView tv_total_member_s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhdashboard, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        // display();
        return view;
    }

    private void initView() {
        tv_total_member_s=view.findViewById(R.id.tv_total_member_s);
        tv_1=view.findViewById(R.id.tv_1);
        tv_2=view.findViewById(R.id.tv_2);
        tv_3=view.findViewById(R.id.tv_3);
        tv_4=view.findViewById(R.id.tv_4);
        tv_5=view.findViewById(R.id.tv_5);
        tv_6=view.findViewById(R.id.tv_6);
        tv_7=view.findViewById(R.id.tv_7);
        tv_8=view.findViewById(R.id.tv_8);
        tv_9=view.findViewById(R.id.tv_9);
        tv_10=view.findViewById(R.id.tv_10);
        tv_11=view.findViewById(R.id.tv_11);
        tv_12=view.findViewById(R.id.tv_12);
        tv_cancer=view.findViewById(R.id.tv_cancer);
        tv_kidney=view.findViewById(R.id.tv_kidney);
        tv_disability=view.findViewById(R.id.tv_disability);
        tv_date=view.findViewById(R.id.tv_date);
        tv_total_member=view.findViewById(R.id.tv_total_member);
        tv_male=view.findViewById(R.id.tv_male);
        tv_female=view.findViewById(R.id.tv_female);
        tv_pregnant=view.findViewById(R.id.tv_pregnant);
        tv_diabetes=view.findViewById(R.id.tv_diabetes);
        tv_hypertension=view.findViewById(R.id.tv_hypertension);
        tv_heart_disease=view.findViewById(R.id.tv_heart_disease);
        tv_stroke=view.findViewById(R.id.tv_stroke);
        tv_lung=view.findViewById(R.id.tv_lung);
        tv_ashma=view.findViewById(R.id.tv_ashma);
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        HHDashboardModel hhDashboardModel= Common.memberMyselfRepository.hhModel();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        Date date = new Date(System.currentTimeMillis());
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        tv_total_member_s.setText(resources.getString(R.string.total_member));
        tv_1.setText(resources.getString(R.string.male));
        tv_2.setText(resources.getString(R.string.female));
        tv_3.setText(resources.getString(R.string.pregnant));
        tv_4.setText(resources.getString(R.string.diabetes));
        tv_5.setText(resources.getString(R.string.hypertension));
        tv_6.setText(resources.getString(R.string.heart));
        tv_7.setText(resources.getString(R.string.stroke));
        tv_8.setText(resources.getString(R.string.tia));
        tv_9.setText(resources.getString(R.string.copd));
        tv_10.setText(resources.getString(R.string.ckd));
        tv_11.setText(resources.getString(R.string.cancer));
        tv_12.setText(resources.getString(R.string.disability));
        tv_date.setText(formatter.format(date));
        tv_male.setText(String.valueOf(hhDashboardModel.Male)+" "+resources.getString(R.string.person));
        tv_kidney.setText(String.valueOf(hhDashboardModel.female)+" "+resources.getString(R.string.person));
        tv_disability.setText(String.valueOf(hhDashboardModel.Mental)+" "+resources.getString(R.string.person));
        tv_pregnant.setText("0"+" "+resources.getString(R.string.person));
        tv_total_member.setText(String.valueOf(hhDashboardModel.total_member)+" "+resources.getString(R.string.person));
        tv_female.setText(String.valueOf(hhDashboardModel.female)+" "+resources.getString(R.string.person));
        tv_diabetes.setText(String.valueOf(hhDashboardModel.dibatis)+" "+resources.getString(R.string.person));
        tv_hypertension.setText(String.valueOf(hhDashboardModel.hypertension)+" "+resources.getString(R.string.person));
        tv_heart_disease.setText(String.valueOf(hhDashboardModel.heartdisease)+" "+resources.getString(R.string.person));
        tv_stroke.setText(String.valueOf(hhDashboardModel.stroke)+" "+resources.getString(R.string.person));
        tv_lung.setText(String.valueOf(hhDashboardModel.Lung)+" "+resources.getString(R.string.person));
        tv_ashma.setText(String.valueOf(hhDashboardModel.Ashma)+" "+resources.getString(R.string.person));
        tv_cancer.setText(String.valueOf(hhDashboardModel.Cancer)+" "+resources.getString(R.string.person));
    }


}

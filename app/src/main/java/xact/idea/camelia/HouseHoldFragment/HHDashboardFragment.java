package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import xact.idea.camelia.Database.AnotherModel.HHDashboardModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;


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
        HHDashboardModel hhDashboardModel= Common.memberMyselfRepository.hhModel();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        Date date = new Date(System.currentTimeMillis());
        tv_date.setText(formatter.format(date));
        tv_male.setText(String.valueOf(hhDashboardModel.Male)+" Persons");
        tv_kidney.setText(String.valueOf(hhDashboardModel.female)+" Persons");
        tv_disability.setText(String.valueOf(hhDashboardModel.Mental)+" Persons");
        tv_total_member.setText(String.valueOf(hhDashboardModel.total_member)+" Persons");
        tv_female.setText(String.valueOf(hhDashboardModel.female)+" Persons");
        tv_diabetes.setText(String.valueOf(hhDashboardModel.dibatis)+" Persons");
        tv_hypertension.setText(String.valueOf(hhDashboardModel.hypertension)+" Persons");
        tv_heart_disease.setText(String.valueOf(hhDashboardModel.heartdisease)+" Persons");
        tv_stroke.setText(String.valueOf(hhDashboardModel.stroke)+" Persons");
        tv_lung.setText(String.valueOf(hhDashboardModel.Lung)+" Persons");
        tv_ashma.setText(String.valueOf(hhDashboardModel.Ashma)+" Persons");
        tv_cancer.setText(String.valueOf(hhDashboardModel.Cancer)+" Persons");
    }


}

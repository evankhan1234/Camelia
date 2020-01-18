package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHSurveysAdapter;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCuserMesaurementsFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class HHSurveysListFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHSurveysAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    FloatingActionButton btn_survey_create;
    String uniqueId;
    public HHSurveysListFragment(String unique){
        uniqueId=unique;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhsurveys_list, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
         display();
        return view;
    }

    private void initView() {
        btn_survey_create =  view.findViewById(R.id.btn_survey_create);
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
        btn_survey_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new HHCreateSurveyFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                HHMembersFragment.tabLayout.setVisibility(View.GONE);
                HHMembersFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        return true;
                    }
                });
                ((HouseholdHomeActivity) getActivity()).ShowText("New Survey");
                ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });
    }
    private  void display() {

        mAdapters = new HHSurveysAdapter(mActivity);
        try {
            rcl_this_customer_list.setAdapter(mAdapters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //EmployeeStaus();

    }

    public int handle(){
Fragment fragment=getChildFragmentManager().findFragmentByTag(HHCreateSurveyFragment.class.getSimpleName());
        Log.e("xsxz","Vcxv"+fragment);
            if (getChildFragmentManager().findFragmentByTag(HHCreateSurveyFragment.class.getSimpleName()) != null)
            {
                HHCreateSurveyFragment f = (HHCreateSurveyFragment) getChildFragmentManager()
                        .findFragmentByTag(HHCreateSurveyFragment.class.getSimpleName());
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                transaction.remove(f);
                transaction.commit();
                getChildFragmentManager().popBackStack();
                HHMembersFragment.tabLayout.setVisibility(View.VISIBLE);
                HHMembersFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                ((HouseholdHomeActivity) getActivity()).ShowText("13140368003(Vikrompur)");
                ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
                Constant.code="are";
                return 3;




        }




        return 0;

    }
}

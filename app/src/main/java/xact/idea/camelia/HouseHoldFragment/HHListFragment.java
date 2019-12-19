package xact.idea.camelia.HouseHoldFragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Fragment.CCMemberStausDetailsFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCWaistWidthFragment;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class HHListFragment extends Fragment {

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHListAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhlist, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
         display();
        return view;
    }



    private void initView() {
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
    }
    private  void display() {

        mAdapters = new HHListAdapter(mActivity,clickListener);
        try {
            rcl_this_customer_list.setAdapter(mAdapters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //EmployeeStaus();

    }
    private UccMemberClickListener clickListener = new UccMemberClickListener() {
        @Override
        public void onItemClick(int position) {
            FragmentTransaction transaction;
            transaction = getChildFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("Id",2);
            Fragment f = new HHMembersFragment();
            f.setArguments(bundle);
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
            transaction.add(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
            transaction.addToBackStack(f.getClass().getSimpleName());
            transaction.commit();
            // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
            ((HouseholdHomeActivity) getActivity()).ShowText("13140368003(Vikrompur)");
            ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
          //  ((CCUserHomeActivity) getActivity()).openStatusDetails(position);
        }
    };

    public  int handels(){
        Fragment fq = getVisibleFragment();
        Log.e("DFDf1","SDfds"+fq);
        Fragment fragment= getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName());
       // Fragment fragment= getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName());

        if (fragment instanceof HHMembersFragment){
          //  boolean t
            int handlle =   ((HHMembersFragment) fragment).handle();

            if (handlle==3){

//                if (getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName()) != null) {
//                    HHMembersFragment f = (HHMembersFragment) getChildFragmentManager()
//                            .findFragmentByTag(HHMembersFragment.class.getSimpleName());
//                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                    transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
//                    transaction.remove(f);
//                    transaction.commit();
//                    getChildFragmentManager().popBackStack();
//
//                    return 2;
//                }
                if (Constant.code.equals("home")){
                    if (getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName()) != null) {
                        HHMembersFragment f = (HHMembersFragment) getChildFragmentManager()
                                .findFragmentByTag(HHMembersFragment.class.getSimpleName());
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                        transaction.remove(f);
                        transaction.commit();
                        getChildFragmentManager().popBackStack();

                        return 2;
                    }
                }
                else if (Constant.code.equals("are")) {
//                    if (getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName()) != null) {
//                        HHMembersFragment f = (HHMembersFragment) getChildFragmentManager()
//                                .findFragmentByTag(HHMembersFragment.class.getSimpleName());
//                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                        transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
//                        transaction.remove(f);
//                        transaction.commit();
//                        getChildFragmentManager().popBackStack();
//
//                        return 2;


                Constant.code="home";
            }



                }

            return 0;
        }




        return 0;
    }
    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        Collections.reverse(fragments);
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    Log.e("ggsdf","fds"+Fragment.class.getName());
                return fragment;
            }
        }
        return null;
    }
}

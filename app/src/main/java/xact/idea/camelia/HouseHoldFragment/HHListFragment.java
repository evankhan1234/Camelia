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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Fragment.CCMemberStausDetailsFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCWaistWidthFragment;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class HHListFragment extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHListAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    FloatingActionButton btn_new;
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

        return view;
    }



    private void initView() {
        btn_new =  view.findViewById(R.id.btn_new);
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new HHCreateHouseholdFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((HouseholdHomeActivity) getActivity()).ShowText("Create Household");
                ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });
    }
    private  void display() {
        compositeDisposable.add(Common.householdRepository.getHouseHoldItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<HouseHold>>() {
            @Override
            public void accept(List<HouseHold> houseHolds) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(houseHolds));
                mAdapters = new HHListAdapter(mActivity,houseHolds,clickListener);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));

        //EmployeeStaus();

    }

    @Override
    public void onPause() {
        super.onPause();
        display();
    }

    private MedicineInterface clickListener = new MedicineInterface() {
        @Override
        public void postion(int position,String Type) {

            Log.e("UniqueId","Type"+Type);
            FragmentTransaction transaction;
            transaction = getChildFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("Id",Type);
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
        Fragment fragment1= getChildFragmentManager().findFragmentByTag(HHCreateHouseholdFragment.class.getSimpleName());
       // Fragment fragment= getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName());
        Log.e("fragment1","fragment1"+fragment1);

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
                else if (Constant.code.equals("are"))
                {
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

        if (fq instanceof HHCreateHouseholdFragment)
        {
            if (getChildFragmentManager().findFragmentByTag(HHCreateHouseholdFragment.class.getSimpleName()) != null) {
                HHCreateHouseholdFragment f = (HHCreateHouseholdFragment) getChildFragmentManager()
                        .findFragmentByTag(HHCreateHouseholdFragment.class.getSimpleName());
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                transaction.remove(f);
                transaction.commit();
                getChildFragmentManager().popBackStack();

                return 2;
            }
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
    @Override
    public void onResume() {
        super.onResume();
        display();
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

package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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

import java.util.Collections;
import java.util.List;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleStatusDetailsAdapter;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCMemberStausDetailsFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    RecyclerView rcv_customer;
    CCIncompleStatusDetailsAdapter mAdapters;
    FloatingActionButton btn_measurements_new;
    String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ccmember_staus_details, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("Type", "");
            Log.e("UniqueId","uniquKey"+type);
        }
        initView();

        return view;
    }
    private void initView() {
        btn_measurements_new =  view.findViewById(R.id.btn_measurements_new);
        rcv_customer =  view.findViewById(R.id.rcv_customer);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_customer.setLayoutManager(lm);
        btn_measurements_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                bundle.putString("Type",type);
                Fragment f = new CCuserMesaurementsFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("Measurements");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });
    }
    private  void display() {

        mAdapters = new CCIncompleStatusDetailsAdapter(mActivity,clickListener);
        try {
            rcv_customer.setAdapter(mAdapters);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public int handle(){
        Fragment fq = getVisibleFragment();

        Fragment fragment= getChildFragmentManager().findFragmentByTag(CCMeasurementsDetailsFragment.class.getSimpleName());

        if (fragment instanceof CCMeasurementsDetailsFragment){
            if (getChildFragmentManager().findFragmentByTag(CCMeasurementsDetailsFragment.class.getSimpleName()) != null)
            {
                CCMeasurementsDetailsFragment f = (CCMeasurementsDetailsFragment) getChildFragmentManager()
                        .findFragmentByTag(CCMeasurementsDetailsFragment.class.getSimpleName());
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                transaction.remove(f);
                transaction.commit();
                getChildFragmentManager().popBackStack();
                CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
                CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        return false;
                    }
                });

                return 2;

            }
        }
        else{
            int handle=     ((CCuserMesaurementsFragment) fq).handle();
            Log.e("handle123","handle"+handle);
            if (handle==3)
            {
                ((CCUserHomeActivity) getActivity()).ShowText("Measurements");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
                Log.e("evankhan","handle"+handle);
                CCUserMemberStatusFragment.tabLayout.setVisibility(View.GONE);
                CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        return true;
                    }
                });
                return handle;

            }
            else {

                if (getChildFragmentManager().findFragmentByTag(CCuserMesaurementsFragment.class.getSimpleName()) != null)
                {
                    CCuserMesaurementsFragment f = (CCuserMesaurementsFragment) getChildFragmentManager()
                            .findFragmentByTag(CCuserMesaurementsFragment.class.getSimpleName());
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                    transaction.remove(f);
                    transaction.commit();
                    getChildFragmentManager().popBackStack();
                    CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
                    CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
                    {
                        @Override
                        public boolean onTouch(View v, MotionEvent event)
                        {
                            return false;
                        }
                    });

                    return 2;

                }


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
    private UccMemberClickListener clickListener = new UccMemberClickListener() {
        @Override
        public void onItemClick(int position) {
            FragmentTransaction transaction;
            transaction = getChildFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("Id",2);
            Fragment f = new CCMeasurementsDetailsFragment();
            f.setArguments(bundle);
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
            transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
            transaction.addToBackStack(f.getClass().getSimpleName());
            transaction.commit();
            // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
            ((CCUserHomeActivity) getActivity()).ShowText("Measurements Status");
            ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        display();
    }
}

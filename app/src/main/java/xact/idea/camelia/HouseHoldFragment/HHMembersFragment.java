package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Fragment.CCMemberStausDetailsFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.ViewPager.HHMemberPager;
import xact.idea.camelia.ViewPager.Pager;


public class HHMembersFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public static TabLayout tabLayout;
    //This is our viewPager
    public static ViewPager viewPager;
    String uniquKey;
    String type;
    String frag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhmembers, container, false);

        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        setRetainInstance(true);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            uniquKey = bundle.getString("Id", "");
            type = bundle.getString("type", "");
            frag = bundle.getString("frag", "");
            Log.e("UniqueId","uniquKey"+uniquKey);
        }
        else{
            frag="";
        }
        initView();
        // display();
        return view;
    }

    private void initView() {
        tabLayout =  view.findViewById(R.id.tabLayout);
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.members)));
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.survey)));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = view. findViewById(R.id.pager);

        //Creating our pager adapter
        HHMemberPager adapter = new HHMemberPager(getChildFragmentManager(), tabLayout.getTabCount(),uniquKey,type,frag);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding adapter to pager
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);
        //Adding onTabSelectedListener to swipe views
        Fragment f = getVisibleFragment();
        Log.e("a", "aa" + f);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public int handle(){
        Fragment fq = getVisibleFragment();
        Log.e("fragment1","fragment"+fq);

        Fragment fragment= getChildFragmentManager().findFragmentByTag(HHMemberListFragment.class.getSimpleName());
        Fragment fragment1= getChildFragmentManager().findFragmentByTag(HHSurveysListFragment.class.getSimpleName());
        Fragment fragment2= getChildFragmentManager().findFragmentByTag(HHMyselfFragment.class.getSimpleName());
        Log.e("fragment1","fragment1"+fragment1);
        Log.e("fragment1","fragment2"+fragment2);
        if (fq instanceof HHMemberListFragment){
            ((HHMemberListFragment) fragment).handle();
            return 3;
        }

        else if (fq instanceof HHSurveysListFragment) {
            ((HHSurveysListFragment) fq).handle();

            return 3;





            }




//        if (getChildFragmentManager().findFragmentByTag(HHMembersFragment.class.getSimpleName()) != null) {
//            HHMembersFragment f = (HHMembersFragment) getChildFragmentManager()
//                    .findFragmentByTag(HHMembersFragment.class.getSimpleName());
//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
//            transaction.remove(f);
//            transaction.commit();
//            getChildFragmentManager().popBackStack();
//
//            return 2;
//
//        }
        return 0;
    }
    public  int handels(){
        Fragment fq = getVisibleFragment();
        Log.e("DFDf1","SDfds"+fq);
        Fragment fragment= getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName());

        if (fragment instanceof CCMemberStausDetailsFragment){
            int handle=     ((CCMemberStausDetailsFragment) fragment).handle();
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

    public void data(int position){
        FragmentTransaction transaction;
        transaction = getChildFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("Id",position);
        Fragment f = new CCMemberStausDetailsFragment();
        f.setArguments(bundle);
        transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
        transaction.replace(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
        transaction.addToBackStack(f.getClass().getSimpleName());
        transaction.commit();
        CCUserMemberStatusFragment.tabLayout.setVisibility(View.GONE);
        CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
        if (frag.equals("frag")){
            ((CCUserHomeActivity) getActivity()).ShowText("Details");
            ((CCUserHomeActivity) getActivity()).showHeaderDetail("status");
        }
        else {
            ((HouseholdHomeActivity) getActivity()).ShowText("Details");
            ((HouseholdHomeActivity) getActivity()).showHeaderDetail("status");
        }

    }
}

package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCDashboardAdapter;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomViewPager;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.ViewPager.Pager;

public class CCUserMemberStatusFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    public static TabLayout tabLayout;
    //This is our viewPager
    public static CustomViewPager viewPager;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_ccuser_member_status, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);

        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);

        initView((String)Paper.book().read("language"));
       // display();

        return view;
    }



    private void initView(String language) {
        Context context= LocaleHelper.setLocale(mActivity,language);
        Resources resources= context.getResources();
        tabLayout =  view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.incomplete)));
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.complete)));
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.refer)));
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.follow_up)));
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.uhc)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = view. findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding adapter to pager
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
        Log.e("DFDf1","SDfds"+fq);


        if (getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()) != null) {
            CCMemberStausDetailsFragment f = (CCMemberStausDetailsFragment) getChildFragmentManager()
                    .findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName());
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
        return 0;
    }
    public  int handels(){
        Fragment fq = getVisibleFragment();
        Log.e("DFDf1","SDfds"+fq);
        Fragment fragment= getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName());

        if (fragment instanceof CCMemberStausDetailsFragment){
            int handle=     ((CCMemberStausDetailsFragment) fragment).handle();
            CompleteFragment.show(mActivity);
            IncompleteFragment.show(mActivity);
            FollowUpFragment.show(mActivity);
            ReferralFragment.show(mActivity);
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

    public void data(int position,String Type,String value){
        FragmentTransaction transaction;
        transaction = getChildFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("Id",position);
        bundle.putString("Type",Type);
        Fragment f = new CCMemberStausDetailsFragment();
        f.setArguments(bundle);
        transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
       if (value.equals("1")){
           transaction.replace(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
       }
       else  if (value.equals("2")){
           transaction.replace(R.id.rlt_details_fragment, f, f.getClass().getSimpleName());
       }
       else  if (value.equals("3")){
           transaction.replace(R.id.rlt_details3_fragment, f, f.getClass().getSimpleName());
       }
       else  if (value.equals("4")){
           transaction.replace(R.id.rlt_details4_fragment, f, f.getClass().getSimpleName());
       }
       else  if (value.equals("5")){
           transaction.replace(R.id.rlt_details5_fragment, f, f.getClass().getSimpleName());
       }
        transaction.addToBackStack(f.getClass().getSimpleName());
        transaction.commit();
        tabLayout.setVisibility(View.GONE);
//        CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });
        viewPager.setPagingEnabled(false);
      //  CCUserMemberStatusFragment.viewPager.beginFakeDrag();
        // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        ((CCUserHomeActivity) getActivity()).ShowTexts(resources.getString(R.string.details));
        ((CCUserHomeActivity) getActivity()).showHeaderDetail("status");
    }
}

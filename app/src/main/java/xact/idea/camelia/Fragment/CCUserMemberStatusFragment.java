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
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCDashboardAdapter;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.ViewPager.Pager;

public class CCUserMemberStatusFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    public static TabLayout tabLayout;
    //This is our viewPager
    public static ViewPager viewPager;
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
        initView();
       // display();
        return view;
    }

    private void initView() {
        tabLayout =  view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Incomplete"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.addTab(tabLayout.newTab().setText("Referral"));
        tabLayout.addTab(tabLayout.newTab().setText("Follow up"));
        tabLayout.addTab(tabLayout.newTab().setText("To UHC"));
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

        int handle = ((CompleteFragment) fq).handle();
        int handles= ((IncompleteFragment) fq).handle();

     //   Log.e("DFDf1","SDfds"+getChildFragmentManager().findFragmentByTag(IncompleteFragment.class.getSimpleName()));
//        Log.e("11","SDfds"+getChildFragmentManager().findFragmentByTag(CompleteFragment.class.getSimpleName()));
//         Log.e("11","SDfds"+getFragmentManager().findFragmentByTag(CompleteFragment.class.getSimpleName()));
////        Log.e("DFDf2","SDfds"+getChildFragmentManager().findFragmentByTag(IncompleteFragment.class.getSimpleName()));
//        if (getChildFragmentManager().findFragmentByTag(CompleteFragment.class.getSimpleName()) != null) {
//            CompleteFragment f = (CompleteFragment) getChildFragmentManager()
//                    .findFragmentByTag(CompleteFragment.class.getSimpleName());
//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
//            transaction.remove(f);
//            transaction.commit();
//            getChildFragmentManager().popBackStack();
//
//
//            return 2;
//
//        }
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

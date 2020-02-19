package xact.idea.camelia.ViewPager;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xact.idea.camelia.HouseHoldFragment.HHMemberListFragment;
import xact.idea.camelia.HouseHoldFragment.HHSurveysListFragment;

public class HHMemberPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Activity activity;
    String unique;
    String types;
    String frag;
    //Constructor to the class
    public HHMemberPager(FragmentManager fm, int tabCount,String uniqueId,String type,String frags) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.unique= uniqueId;
        this.types= type;
        this.frag= frags;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Log.e("UniqueId","uniquKey"+unique);
                HHMemberListFragment tab1 = new HHMemberListFragment(unique,frag);

                return tab1;
            case 1:
                Log.e("frag12","frags"+frag);
                HHSurveysListFragment tab2 = new HHSurveysListFragment(unique,types,frag);
                return tab2;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
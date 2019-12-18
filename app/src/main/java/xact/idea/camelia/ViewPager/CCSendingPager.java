package xact.idea.camelia.ViewPager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xact.idea.camelia.HouseHoldFragment.HHCCSendingNotSyncFragment;
import xact.idea.camelia.HouseHoldFragment.HHCCSendingSyncFragment;
import xact.idea.camelia.HouseHoldFragment.HHMemberListFragment;
import xact.idea.camelia.HouseHoldFragment.HHSurveysListFragment;

public class CCSendingPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public CCSendingPager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                HHCCSendingNotSyncFragment tab1 = new HHCCSendingNotSyncFragment();
                return tab1;
            case 1:
                HHCCSendingSyncFragment tab2 = new HHCCSendingSyncFragment();
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
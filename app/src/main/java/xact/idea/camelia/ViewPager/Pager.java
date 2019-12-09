package xact.idea.camelia.ViewPager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xact.idea.camelia.Fragment.CompleteFragment;
import xact.idea.camelia.Fragment.FollowUpFragment;
import xact.idea.camelia.Fragment.IncompleteFragment;
import xact.idea.camelia.Fragment.ReferralFragment;
import xact.idea.camelia.Fragment.UnAttendedFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
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
                IncompleteFragment tab1 = new IncompleteFragment();
                return tab1;
            case 1:
                CompleteFragment tab2 = new CompleteFragment();
                return tab2;
            case 2:
                ReferralFragment tab3 = new ReferralFragment();
                return tab3;
            case 3:
                FollowUpFragment tab4 = new FollowUpFragment();
                return tab4;
            case 4:
                UnAttendedFragment tab5 = new UnAttendedFragment();
                return tab5;

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
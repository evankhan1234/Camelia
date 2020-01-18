package xact.idea.camelia.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Fragment.CompleteFragment;
import xact.idea.camelia.Fragment.FollowUpFragment;
import xact.idea.camelia.Fragment.IncompleteFragment;
import xact.idea.camelia.Fragment.ReferralFragment;
import xact.idea.camelia.Fragment.UnAttendedFragment;
import xact.idea.camelia.HouseHoldFragment.HHMemberListFragment;
import xact.idea.camelia.HouseHoldFragment.HHMembersFragment;
import xact.idea.camelia.HouseHoldFragment.HHSurveysListFragment;
import xact.idea.camelia.R;

public class HHMemberPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Activity activity;
    String unique;

    //Constructor to the class
    public HHMemberPager(FragmentManager fm, int tabCount,String uniqueId) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.unique= uniqueId;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Log.e("UniqueId","uniquKey"+unique);
                HHMemberListFragment tab1 = new HHMemberListFragment(unique);

                return tab1;
            case 1:
                HHSurveysListFragment tab2 = new HHSurveysListFragment(unique);
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
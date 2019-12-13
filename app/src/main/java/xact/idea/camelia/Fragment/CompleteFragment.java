package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class CompleteFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_complete, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        return view;
    }
    public int handle(){

        Fragment fq = getVisibleFragment();
        Log.e("eva","SDfds"+fq);
     //   Log.e("evan","SDfds"+getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()));
        Log.e("evan","SDfds"+getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()));
//        Log.e("DFDf1","SDfds"+getFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()));
//        Log.e("DFDf2","SDfds"+getChildFragmentManager().findFragmentByTag(IncompleteFragment.class.getSimpleName()));
//        if (getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()) != null) {
//            CCMemberStausDetailsFragment f = (CCMemberStausDetailsFragment) getChildFragmentManager()
//                    .findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName());
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

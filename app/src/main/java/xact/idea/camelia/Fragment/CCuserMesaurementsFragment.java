package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class CCuserMesaurementsFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    CardView card_waist;
    CardView card_whr;
    CardView card_bmi;
    CardView card_pulse;
    CardView card_random;
    CardView card_blood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ccuser_mesaurements, container, false);

        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        // display();
        return view;
    }

    private void initView() {
        card_waist=view.findViewById(R.id.card_waist);
        card_whr=view.findViewById(R.id.card_whr);
        card_bmi=view.findViewById(R.id.card_bmi);
        card_pulse=view.findViewById(R.id.card_pulse);
        card_random=view.findViewById(R.id.card_random);
        card_blood=view.findViewById(R.id.card_blood);
        card_waist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCWaistWidthFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_root, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("Waist Width");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_whr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCWHRFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("WHR");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCBMIFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("BMI");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCBloodPressureFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("Blood Pressure");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_pulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCFastingGlucosreFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("Pulse (beat/min)");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id",2);
                Fragment f = new CCRandomGlucoseFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                ((CCUserHomeActivity) getActivity()).ShowText("Blood Glucose (Diabetes)");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });
    }

    public int handle(){
        Fragment fq = getVisibleFragment();
        Log.e("DFDf1","SDfds"+fq);


        if (getChildFragmentManager().findFragmentByTag(CCWaistWidthFragment.class.getSimpleName()) != null) {
            CCWaistWidthFragment f = (CCWaistWidthFragment) getChildFragmentManager()
                    .findFragmentByTag(CCWaistWidthFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        else if (getChildFragmentManager().findFragmentByTag(CCRandomGlucoseFragment.class.getSimpleName()) != null) {
            CCRandomGlucoseFragment f = (CCRandomGlucoseFragment) getChildFragmentManager()
                    .findFragmentByTag(CCRandomGlucoseFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        else if (getChildFragmentManager().findFragmentByTag(CCFastingGlucosreFragment.class.getSimpleName()) != null) {
            CCFastingGlucosreFragment f = (CCFastingGlucosreFragment) getChildFragmentManager()
                    .findFragmentByTag(CCFastingGlucosreFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        else if (getChildFragmentManager().findFragmentByTag(CCBloodPressureFragment.class.getSimpleName()) != null) {
            CCBloodPressureFragment f = (CCBloodPressureFragment) getChildFragmentManager()
                    .findFragmentByTag(CCBloodPressureFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        else if (getChildFragmentManager().findFragmentByTag(CCBMIFragment.class.getSimpleName()) != null) {
            CCBMIFragment f = (CCBMIFragment) getChildFragmentManager()
                    .findFragmentByTag(CCBMIFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
        }
        else if (getChildFragmentManager().findFragmentByTag(CCWHRFragment.class.getSimpleName()) != null) {
            CCWHRFragment f = (CCWHRFragment) getChildFragmentManager()
                    .findFragmentByTag(CCWHRFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();
            CCUserMemberStatusFragment.tabLayout.setVisibility(View.VISIBLE);
            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            return 3;
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

}

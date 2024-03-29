package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;


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
    String type;
    TextView tv_bmi;
    TextView tv_whr;
    TextView tv_pulse;
    TextView tv_diabetis;
    TextView tv_systolic;
    TextView tv_diastolic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ccuser_mesaurements, container, false);

        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("Type", "");
            Log.e("UniqueId", "uniquKey" + type);
        }
        initView();
        // display();
        return view;
    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(mActivity, language);
        Resources resources = context.getResources();
        tv_systolic.setText(resources.getString(R.string.systolic));
        tv_diastolic.setText(resources.getString(R.string.diastolic));
        tv_pulse.setText(resources.getString(R.string.pulse));
        tv_bmi.setText(resources.getString(R.string.bmi_));
        tv_diabetis.setText(resources.getString(R.string.blood_glucose));
        tv_whr.setText(resources.getString(R.string.whr));
    }

    private void initView() {
        tv_systolic = view.findViewById(R.id.tv_systolic);
        tv_diastolic = view.findViewById(R.id.tv_diastolic);
        tv_pulse = view.findViewById(R.id.tv_pulse);
        tv_bmi = view.findViewById(R.id.tv_bmi);
        tv_diabetis = view.findViewById(R.id.tv_diabetis);
        tv_whr = view.findViewById(R.id.tv_whr);
        card_waist = view.findViewById(R.id.card_waist);
        card_whr = view.findViewById(R.id.card_whr);
        card_bmi = view.findViewById(R.id.card_bmi);
        card_pulse = view.findViewById(R.id.card_pulse);
        card_random = view.findViewById(R.id.card_random);
        card_blood = view.findViewById(R.id.card_blood);
        Paper.init(mActivity);
        String language = SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language", language);
        updateView((String) Paper.book().read("language"));
        card_waist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCWaistWidthFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.add(R.id.rlt_root, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.diastolic));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_whr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCWHRFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.whr));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCBMIFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.bmi_));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCBloodPressureFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.systolic));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_pulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCFastingGlucosreFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.pulse));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });

        card_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", 2);
                bundle.putString("Type", type);
                Fragment f = new CCRandomGlucoseFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
                Paper.init(mActivity);
                String language= SharedPreferenceUtil.getLanguage(mActivity);
                Paper.book().write("language",language);
                Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
                Resources resources= context.getResources();
                ((CCUserHomeActivity) getActivity()).ShowText(resources.getString(R.string.blood_glucose));
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        });
    }


    public int handle() {
        Fragment fq = getVisibleFragment();
        Log.e("DFDf1", "SDfds" + fq);


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
        } else if (getChildFragmentManager().findFragmentByTag(CCRandomGlucoseFragment.class.getSimpleName()) != null) {
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
        } else if (getChildFragmentManager().findFragmentByTag(CCFastingGlucosreFragment.class.getSimpleName()) != null) {
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
        } else if (getChildFragmentManager().findFragmentByTag(CCBloodPressureFragment.class.getSimpleName()) != null) {
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
        } else if (getChildFragmentManager().findFragmentByTag(CCBMIFragment.class.getSimpleName()) != null) {
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
        } else if (getChildFragmentManager().findFragmentByTag(CCWHRFragment.class.getSimpleName()) != null) {
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
                    Log.e("ggsdf", "fds" + Fragment.class.getName());
                return fragment;
            }
        }
        return null;
    }

}

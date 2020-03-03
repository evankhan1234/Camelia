package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.Household.HouseholdHomeActivity;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHMemberListAdapter;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.MeasurementDetails;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Fragment.CCMeasurementsDetailsFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCuserMesaurementsFragment;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;


public class HHMemberListFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHMemberListAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    FloatingActionButton btn_member_new;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String uniqueId;
    String frag;

    public HHMemberListFragment(String unique,String frags){
        uniqueId=unique;
        frag=frags;
        Log.e("UniqueId","uniquKey"+unique);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhmember_list, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        setRetainInstance(true);
        initView();

         display();
        return view;
    }

    private void initView() {
        btn_member_new =  view.findViewById(R.id.btn_member_new);
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);

        btn_member_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.memberIdRepository.size()<0){
                    showInfoDialogagain(mActivity);
                }
                else{
                    if (Common.memberIdRepository.size()==1){
                        showInfoDialoga(mActivity);
                    }
                    else{
                        showInfoDialog();
                    }


                }

            }
        });
    }
    public  void showInfoDialoga(final Context mContext) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        RadioButton radioRefer = infoDialog.findViewById(R.id.radioRefer);
        RadioButton radioFollow = infoDialog.findViewById(R.id.radioFollow);
        TextView tv_info = infoDialog.findViewById(R.id.tv_info);
        tv_info.setText("You have 1 Member Id. please Sync");
        radioRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radioFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                showInfoDialog();
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                Date date = new Date(System.currentTimeMillis());
//                String currentDate = formatter.format(date);
//                Common.memberMyselfRepository.updateReciver(currentDate,member);
                infoDialog.dismiss();

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
    public  void showInfoDialogagain(final Context mContext) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        RadioButton radioRefer = infoDialog.findViewById(R.id.radioRefer);
        RadioButton radioFollow = infoDialog.findViewById(R.id.radioFollow);
        TextView tv_info = infoDialog.findViewById(R.id.tv_info);
        tv_info.setText("You have 0 Member Id. please Sync");
        radioRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radioFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                showInfoDialog();
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                Date date = new Date(System.currentTimeMillis());
//                String currentDate = formatter.format(date);
//                Common.memberMyselfRepository.updateReciver(currentDate,member);
                infoDialog.dismiss();

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
    public   void display() {
        compositeDisposable.add(Common.memberMyselfRepository.getMemberMyselfItemById(uniqueId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMyself>>() {
            @Override
            public void accept(List<MemberMyself> memberMyselfes) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(memberMyselfes));
                mAdapters = new HHMemberListAdapter(mActivity,memberMyselfes,medicineInterface);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));


    }

    MedicineInterface medicineInterface = new MedicineInterface() {
        @Override
        public void postion(int position, String Type) {
            FragmentTransaction transaction;
            transaction = getChildFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("Id",uniqueId);
            bundle.putString("frag",frag);
            bundle.putString("update",Type);
            Fragment f = new HHCreateMemberFragment();
            f.setArguments(bundle);
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
            transaction.replace(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
            transaction.addToBackStack(f.getClass().getSimpleName());
            transaction.commit();
            HHMembersFragment.tabLayout.setVisibility(View.GONE);
            if (frag.equals("frag")){
                ((CCUserHomeActivity) getActivity()).ShowText("Update Member");
                ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
            else {
                ((HouseholdHomeActivity) getActivity()).ShowText("Update Member");
                ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        display();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
    public void showInfoDialog() {

        final CustomDialog infoDialog = new CustomDialog(mActivity, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_agreement, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        TextView tv_info = infoDialog.findViewById(R.id.tv_info);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        CorrectSizeUtil.getInstance(mActivity).correctSize(main_root);
        tv_info.setMovementMethod(new ScrollingMovementMethod());
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction;
                transaction = getChildFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("Id",uniqueId);
                bundle.putString("frag",frag);
                Fragment f = new HHCreateMemberFragment();
                f.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
                transaction.replace(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
                transaction.addToBackStack(f.getClass().getSimpleName());
                transaction.commit();
                HHMembersFragment.tabLayout.setVisibility(View.GONE);
                if (frag.equals("frag")){
                    ((CCUserHomeActivity) getActivity()).ShowText("New Member");
                    ((CCUserHomeActivity) getActivity()).showHeaderDetail("Measurements");
                }
                else {
                    ((HouseholdHomeActivity) getActivity()).ShowText("New Member");
                    ((HouseholdHomeActivity) getActivity()).showHeaderDetail("Measurements");
                }

                infoDialog.dismiss();


            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
    public int handle(){
        Fragment fq = getVisibleFragment();
        Log.e("Evan","Evan"+fq);
        Fragment fragment= getChildFragmentManager().findFragmentByTag(HHMemberListFragment.class.getSimpleName());

        if (fragment instanceof HHMemberListFragment){
            if (getChildFragmentManager().findFragmentByTag(HHMemberListFragment.class.getSimpleName()) != null)
            {
                HHMemberListFragment f = (HHMemberListFragment) getChildFragmentManager()
                        .findFragmentByTag(HHMemberListFragment.class.getSimpleName());
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
                transaction.remove(f);
                transaction.commit();
                getChildFragmentManager().popBackStack();

                return 3;

            }


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

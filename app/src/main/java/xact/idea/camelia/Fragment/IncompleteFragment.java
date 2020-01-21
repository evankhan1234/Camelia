package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHMemberListAdapter;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class IncompleteFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    RecyclerView rcl_this_customer_list;
    CCIncompleteStatusAdapter mAdapters;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<MemberMyself> memberMyselfList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_incomplete, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
       /// display();

        return view;
    }

    private UccMemberClickListener clickListener = new UccMemberClickListener() {
        @Override
        public void onItemClick(int position) {
//            FragmentTransaction transaction;
//            transaction = getChildFragmentManager().beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putInt("Id",position);
//            Fragment f = new CCMemberStausDetailsFragment();
//            f.setArguments(bundle);
//            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.stand_by, R.anim.stand_by, R.anim.left_to_right);
//            transaction.add(R.id.rlt_detail_fragment, f, f.getClass().getSimpleName());
//            transaction.addToBackStack(f.getClass().getSimpleName());
//            transaction.commit();
//            CCUserMemberStatusFragment.tabLayout.setVisibility(View.GONE);
//            CCUserMemberStatusFragment.viewPager.setOnTouchListener(new View.OnTouchListener()
//            {
//                @Override
//                public boolean onTouch(View v, MotionEvent event)
//                {
//                    return true;
//                }
//            });
//           // CCUserMemberStatusFragment.viewPager.setVisibility(View.GONE);
//            ((CCUserHomeActivity) getActivity()).ShowText("Details");
            ((CCUserHomeActivity) getActivity()).openStatusDetails(position);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        display();
    }

    private void initView() {
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
    }

    public   void display() {
        compositeDisposable.add(Common.memberMyselfRepository.getMemberMyselfItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMyself>>() {
            @Override
            public void accept(List<MemberMyself> memberMyselfes) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(memberMyselfes));
                memberMyselfList=memberMyselfes;
                mAdapters = new CCIncompleteStatusAdapter(mActivity,memberMyselfes,clickListener);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));

        //EmployeeStaus();

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
    public int handle(){


        Log.e("evan","SDfds"+getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()));
        Log.e("DFDf1","SDfds"+getChildFragmentManager().findFragmentByTag(CompleteFragment.class.getSimpleName()));
//        Log.e("DFDf1","SDfds"+getFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()));
//        Log.e("DFDf2","SDfds"+getChildFragmentManager().findFragmentByTag(IncompleteFragment.class.getSimpleName()));
        if (getChildFragmentManager().findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName()) != null) {
            CCMemberStausDetailsFragment f = (CCMemberStausDetailsFragment) getChildFragmentManager()
                    .findFragmentByTag(CCMemberStausDetailsFragment.class.getSimpleName());
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.left_to_right, R.anim.left_to_right);
            transaction.remove(f);
            transaction.commit();
            getChildFragmentManager().popBackStack();


            return 2;

        }
        return 0;
    }
}

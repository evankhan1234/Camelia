package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class CompleteFragment extends Fragment {
    static Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    static RecyclerView rcl_this_customer_list;
    static CCIncompleteStatusAdapter mAdapters;
    static CompositeDisposable compositeDisposable = new CompositeDisposable();
    static  List<MemberMyself> memberMyselfList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_complete, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        return view;
    }
    private static MedicineInterface clickListener = new MedicineInterface() {
        @Override
        public void postion(int position,String Type) {

            ((CCUserHomeActivity) mActivity).openStatusDetails(position,Type,"2");
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        display();
    }

    public static void show(Activity Activity){
        mActivity=Activity;
        display();
    }
    private void initView() {
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
    }

    public static void display() {
        compositeDisposable.add(Common.memberMyselfRepository.getCompleteMembers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMyself>>() {
            @Override
            public void accept(List<MemberMyself> memberMyselfes) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(memberMyselfes));
                memberMyselfList=memberMyselfes;
                FragmentActivity activity = (FragmentActivity)mActivity;
                FragmentManager manager = activity.getSupportFragmentManager();
                mAdapters = new CCIncompleteStatusAdapter(mActivity,memberMyselfes,clickListener,manager);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));


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
}

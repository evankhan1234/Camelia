package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHMemberListAdapter;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;


public class IncompleteFragment extends Fragment {
    static Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    static RecyclerView rcl_this_customer_list;
    static  CCIncompleteStatusAdapter mAdapters;
    static  CompositeDisposable compositeDisposable = new CompositeDisposable();
    static List<MemberMyself> memberMyselfList= new ArrayList<>();
    EditText edit_content;
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
    private void updateView(String language) {
        Context context= LocaleHelper.setLocale(mActivity,language);
        Resources resources= context.getResources();
        edit_content.setHint(resources.getString(R.string.search));

    }
    private static MedicineInterface clickListener = new MedicineInterface() {
        @Override
        public void postion(int position,String Type) {

            ((CCUserHomeActivity) mActivity).openStatusDetails(position,Type,"1");
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        display();
    }

    private void initView() {
        edit_content =  view.findViewById(R.id.edit_content);
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        updateView((String)Paper.book().read("language"));
    }

    public static void display() {


        compositeDisposable.add(Common.memberMyselfRepository.getInCompleteMembers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMyself>>() {
            @Override
            public void accept(List<MemberMyself> memberMyselfes) throws Exception {
                Log.e("e","dfsdf"+new Gson().toJson(memberMyselfes));
                memberMyselfList=memberMyselfes;
                if (memberMyselfList.size()>0){
                    FragmentActivity activity = (FragmentActivity)mActivity;
                    FragmentManager manager = activity.getSupportFragmentManager();
                    mAdapters = new CCIncompleteStatusAdapter(mActivity,memberMyselfes,clickListener,manager,2);
                    try {
                        rcl_this_customer_list.setAdapter(mAdapters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }
        }));
        compositeDisposable.add(Common.memberMyselfRepository.getInCompleteMembersFor().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<MemberMyself>>() {
            @Override
            public void accept(List<MemberMyself> memberMyselfes) throws Exception {
                Log.e("bb","dfsdf"+new Gson().toJson(memberMyselfes));
                if (memberMyselfList.size()==0){
                    FragmentActivity activity = (FragmentActivity)mActivity;
                    FragmentManager manager = activity.getSupportFragmentManager();
                mAdapters = new CCIncompleteStatusAdapter(mActivity,memberMyselfes,clickListener,manager,2);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
                else {
                    memberMyselfList.addAll(memberMyselfes);
                    mAdapters.notifyDataSetChanged();
                }



            }
        }));

        //EmployeeStaus();

    }

    public static void show(Activity Activity){
        mActivity=Activity;
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

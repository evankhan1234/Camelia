package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xact.idea.camelia.Adapter.HHAdapter.HHCCSyncAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class HHCCSendingNotSyncFragment extends Fragment {

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHCCSyncAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhccsending_not_sync, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
         display();
        return view;
    }

    private void initView() {
        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
    }
    private UccMemberClickListener clickListener = new UccMemberClickListener() {
        @Override
        public void onItemClick(int position) {

        }
    };
    private  void display() {

        mAdapters = new HHCCSyncAdapter(mActivity,clickListener);
        try {
            rcl_this_customer_list.setAdapter(mAdapters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //EmployeeStaus();

    }

}

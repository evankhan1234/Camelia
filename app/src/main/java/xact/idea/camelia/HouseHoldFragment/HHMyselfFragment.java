package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.Utils;


public class HHMyselfFragment extends Fragment implements Handler.Callback{

    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public  static Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhmyself, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        handler = new Handler(this);
        initView();
        // display();
        return view;
    }

    private void initView() {
    }
    private boolean isChecked() {
//        if (Utils.isEmpty(etLocationDescriptio.getText().toString())) {
//            ((NewGroupActivty) mActivity).showTitleAlertDialog(getResources().getString(R.string.err_title), mActivity.getResources().getString(R.string.please_enter) + " Location Description", null);
//            return false;
//        }


        return true;
    }
    private void saveData(){

        if (isChecked()){
            HHCreateMemberFragment.nextPage(1);
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.what==0)
        {
            saveData();
        }
        return false;
    }
}

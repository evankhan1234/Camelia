package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Adapter.HHAdapter.HHCCSyncAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHListAdapter;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class HHCCSendingSyncFragment extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    HHCCSyncAdapter mAdapters;
    RecyclerView rcl_this_customer_list;
    static EditText edit_start_date;
    static EditText edit_end_date;
    Button btn_yes;
     EditText edit_content;
    ProgressBar progress_bar;

    TextView text_member;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhccsending_sync, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        return view;
    }

    private void initView() {
        edit_content =  view.findViewById(R.id.edit_content);
        text_member =  view.findViewById(R.id.text_member);

        rcl_this_customer_list =  view.findViewById(R.id.rcl_this_customer_list);
        progress_bar =  view.findViewById(R.id.progress_bar);
        edit_start_date =  view.findViewById(R.id.edit_start_date);
        edit_end_date =  view.findViewById(R.id.edit_end_date);
        btn_yes =  view.findViewById(R.id.btn_yes);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
        edit_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dFragment = new DatePickerToFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
        edit_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dFragment = new DatePickerFromFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.DAY_OF_MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        month=month-1;
        String days="";
        String sdays="";
        String months="";
        int a;

        if (day>31){
            a=30-day;
        }
        else{
            a=31-day;
        }
        if (a>9){
            sdays=""+a;
        }
        else{
            sdays="0"+a;
        }
        if (day>9){
            days=""+day;
        }
        else{
            days="0"+day;
        }
        if (month>9){
            months=""+month;
        }
        else{

            months="0"+month;
        }





        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = new Date(System.currentTimeMillis());
        String currentDate = formatter.format(end);
        String currentDate2 = formatter.format(date2);
        edit_start_date.setText(currentDate);
        edit_end_date.setText(currentDate2);
        btn_yes =view.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_start_date.getText().toString().matches("")) {
                    Toast.makeText(mActivity, "You did not enter a Start Date", Toast.LENGTH_SHORT).show();

                }
                else if (edit_end_date.getText().toString().matches("")){
                    Toast.makeText(mActivity, "You did not enter a End Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    display();
                }


            }
        });
        edit_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                mAdapters.getFilter().filter(edit_content.getText().toString());
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        display();
    }
    private  void display() {
        progress_bar.setVisibility(View.VISIBLE);
        String startDate=edit_start_date.getText().toString();
        String endDate=edit_end_date.getText().toString();
        MemberMyself memberMyself= Common.memberMyselfRepository.getMemberId("000000032");
        Date date1 = null;
        Date date2 = null;
        try {
            date1=new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            date2=new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        text_member.setText(String.valueOf(Common.memberMyselfRepository.Sync(date1,date2)));
        compositeDisposable.add(Common.memberMyselfRepository.getSyncMembers(date1,date2).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<SentSyncModel>>() {
            @Override
            public void accept(List<SentSyncModel> sentSyncModels) throws Exception {
                Log.e("fsd","dfsdf"+new Gson().toJson(sentSyncModels));
                mAdapters = new HHCCSyncAdapter(mActivity,sentSyncModels);
                try {
                    rcl_this_customer_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progress_bar.setVisibility(View.GONE);

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
    public static class DatePickerFromFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                calendar.setTime(sdf.parse(edit_start_date.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);

            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar today = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(chosenDate);
            edit_start_date.setText(formattedDate);
        }
    }
    public static class DatePickerToFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                calendar.setTime(sdf.parse(edit_end_date.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);

            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar today = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(chosenDate);
            edit_end_date.setText(formattedDate);
        }
    }

}

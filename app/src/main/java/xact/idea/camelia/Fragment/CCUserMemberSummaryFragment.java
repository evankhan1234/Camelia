package xact.idea.camelia.Fragment;

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

import android.os.Handler;
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

import com.github.mikephil.charting.charts.PieChart;
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
import xact.idea.camelia.Adapter.CCDashboardAdapter;
import xact.idea.camelia.Adapter.CCMemberSummaryAdapter;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.SummaryModel;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class CCUserMemberSummaryFragment extends Fragment {
    Activity mActivity;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    CorrectSizeUtil correctSizeUtil;
    ProgressBar progress_bar;
    static EditText edit_start_date;
    static EditText edit_end_date;
    Button btn_yes;
    RecyclerView rcl_approval_in_list;
    static  TextView text_date_current;
    View view;
    String currentDate;
    CCMemberSummaryAdapter mAdapters;
    static TextView text_weight_number;
    static TextView  text_hypertension ;
    static TextView  text_diabetis ;
    static TextView text_bmi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_ccuser_member_summary, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        display();
        return view;
    }

    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date(System.currentTimeMillis());
        currentDate = formatter.format(date);
        text_diabetis = view.findViewById(R.id.text_diabetis);
        text_hypertension = view.findViewById(R.id.text_hypertension);
        text_bmi = view.findViewById(R.id.text_bmi);
        progress_bar = view.findViewById(R.id.progress_bar);
        text_weight_number = view.findViewById(R.id.text_weight_number);
        text_date_current=view.findViewById(R.id.text_date_current);
        rcl_approval_in_list=view.findViewById(R.id.rcl_approval_in_list);
        edit_start_date = view.findViewById(R.id.edit_start_date);
        edit_end_date = view.findViewById(R.id.edit_end_date);
        btn_yes = view.findViewById(R.id.btn_yes);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_approval_in_list.setLayoutManager(lm);
        text_date_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dFragment = new DatePickerFromFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
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
                DialogFragment dFragment = new DatePickerFromFragments();

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





        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        SimpleDateFormat formatters = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = new Date(System.currentTimeMillis());
        String currentDate = formatters.format(end);
        String currentDate2 = formatters.format(date2);
        edit_end_date.setText(currentDate2);
        edit_start_date.setText(currentDate);
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
        DateFormat formatter4 = new SimpleDateFormat("dd-MM-yyyy");
        final Date date5 = new Date(System.currentTimeMillis());

        final String formattedDate = formatter4.format(date5);
        Date date16 = null;

        try {
            date16 = new SimpleDateFormat("dd-MM-yyyy").parse(formattedDate);
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Date finalDate = date16;
        SummaryModel summaryModel=Common.memberMyselfRepository.TotalSum(finalDate);
        text_weight_number.setText(String.valueOf(summaryModel.OverWeight));
        text_hypertension.setText(String.valueOf(summaryModel.Hypertension));
        text_diabetis.setText(String.valueOf(summaryModel.Diabetes));
        text_bmi.setText(String.valueOf(summaryModel.Obese));
        text_date_current.setText(formattedDate);
    }
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
    private  void display() {

        progress_bar.setVisibility(View.VISIBLE);
        String startDate = edit_start_date.getText().toString();
        String endDate = edit_end_date.getText().toString();
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        compositeDisposable.add(Common.memberMyselfRepository.TotalListOfSum(date1, date2).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<SummaryModel>>() {
            @Override
            public void accept(List<SummaryModel> countList) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(countList));
                mAdapters = new CCMemberSummaryAdapter(mActivity, countList);
                try {
                    rcl_approval_in_list.setAdapter(mAdapters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progress_bar.setVisibility(View.GONE);

            }
        }));
        //EmployeeStaus();

    }
    public static class DatePickerFromFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            try {
                calendar.setTime(sdf.parse(text_date_current.getText().toString()));
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
            final String formattedDate = formatter.format(chosenDate);
            Date date1 = null;

            try {
                date1 = new SimpleDateFormat("dd-MM-yyyy").parse(formattedDate);
                // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            final Date finalDate = date1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    SummaryModel summaryModel=Common.memberMyselfRepository.TotalSum(finalDate);
                    text_weight_number.setText(String.valueOf(summaryModel.OverWeight));
                    text_hypertension.setText(String.valueOf(summaryModel.Hypertension));
                    text_diabetis.setText(String.valueOf(summaryModel.Diabetes));
                    text_bmi.setText(String.valueOf(summaryModel.Obese));
                    text_date_current.setText(formattedDate);
                }
            }, 300);

        }
    }


    public static class DatePickerFromFragments extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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

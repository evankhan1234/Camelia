package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Adapter.CCDashboardAdapter;
import xact.idea.camelia.Adapter.HHAdapter.HHCCSyncAdapter;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.HouseHoldFragment.HHCCSendingSyncFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

public class CCUserDashBoardFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    static List<Integer> ydata = new ArrayList<>();
    CCDashboardAdapter mAdapters;
    private static String[] xdata ;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    static EditText edit_start_date;
    static EditText edit_end_date;
    Button btn_yes;
    static PieChart pieChart;
    RecyclerView rcl_approval_in_list;
    static TextView text_date_current;
    ProgressBar progress_bar;
    String currentDate;
    TextView text_follow_up;
    TextView text_uhc;
    TextView text_customer_name;
    TextView text_complete;
    TextView text_incomplete;
    TextView text_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ccuser_dash_board, container, false);
        mActivity = getActivity();
        correctSizeUtil = correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        initView();
        display();
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        updateView((String)Paper.book().read("language"));
        updateViewAgain((String)Paper.book().read("language"));


        return view;
    }

    private void updateView(String language) {
        Context context=LocaleHelper.setLocale(mActivity,language);
        Resources resources= context.getResources();
        xdata= new String[]{resources.getString(R.string.incomplete), resources.getString(R.string.complete), resources.getString(R.string.uhc), resources.getString(R.string.refer)};
    }
    private void updateViewAgain(String language) {
        Context context=LocaleHelper.setLocale(mActivity,language);
        Resources resources= context.getResources();
        text_follow_up.setText(resources.getString(R.string.refer));
        text_complete.setText(resources.getString(R.string.complete));
        text_incomplete.setText(resources.getString(R.string.incomplete));
        text_uhc.setText(resources.getString(R.string.uhc));
        text_customer_name.setText(resources.getString(R.string.date));
    }
    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date(System.currentTimeMillis());
        currentDate = formatter.format(date);

        text_customer_name = view.findViewById(R.id.text_customer_name);
        text_incomplete = view.findViewById(R.id.text_incomplete);
        text_complete = view.findViewById(R.id.text_complete);
        text_follow_up = view.findViewById(R.id.text_follow_up);
        text_uhc = view.findViewById(R.id.text_uhc);
        progress_bar = view.findViewById(R.id.progress_bar);
        text_date_current = view.findViewById(R.id.text_date_current);
        rcl_approval_in_list = view.findViewById(R.id.rcl_approval_in_list);
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
        pieChart = view.findViewById(R.id.pie);
        pieChart.setDrawHoleEnabled(true);
        Context context=LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        pieChart.setDescription(resources.getString(R.string.dashboard));
        pieChart.setDescriptionTextSize(13);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setCenterText(resources.getString(R.string.dashboard));
        pieChart.setCenterTextSize(10);

        pieChart.setTransparentCircleRadius(50f);
        Legend l = pieChart.getLegend();
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        text_date_current.setText(currentDate);

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(System.currentTimeMillis());
                Date date1 = null;
                String currentDate = formatter.format(date);
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy").parse(currentDate);
                    // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ydata.clear();
                Count count = Common.memberMyselfRepository.TotalCountByDate(date1);
                ydata.add(count.Incomplete1 + count.Incomplete2);
                ydata.add(count.Complete);
                ydata.add(count.UHC);
                ydata.add(count.Follow);
                addDataSet();

            }
        }, 300);


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
                    ydata.clear();
                    Count count = Common.memberMyselfRepository.TotalCountByDate(finalDate);
                    ydata.add(count.Incomplete1 + count.Incomplete2);
                    ydata.add(count.Complete);
                    ydata.add(count.UHC);
                    ydata.add(count.Follow);
                    addDataSet();
                    text_date_current.setText(formattedDate);
                }
            }, 300);

        }
    }

    private static void addDataSet() {

        ArrayList<Entry> YEntry = new ArrayList<>();

        for (int i = 0; i < ydata.size(); i++) {
            YEntry.add(new Entry(ydata.get(i), i));

        }
        ArrayList<String> XEntry = new ArrayList<>();
        for (int j = 0; j < xdata.length; j++) {

            XEntry.add(xdata[j]);
        }
        PieDataSet pie = new PieDataSet(YEntry, "");
        pie.setSliceSpace(3);
        pie.setSelectionShift(5);
        pie.setValueTextSize(35);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.parseColor("#21a839"));

        colors.add(Color.parseColor("#eab259"));
        colors.add(Color.DKGRAY);


        pie.setColors(colors);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setTextSize(13);
        pie.setValueFormatter(new MyValueFormatter());
        PieData pieData = null;
        try {
            pieData = new PieData(xdata, pie);
            pieData.setValueTextSize(10f);
            pieData.setValueTextColor(Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    public static class MyValueFormatter implements ValueFormatter {


        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value);
        }
    }

    private void display() {
        progress_bar.setVisibility(View.VISIBLE);
        String startDate = edit_start_date.getText().toString();
        String endDate = edit_end_date.getText().toString();
        MemberMyself memberMyself = Common.memberMyselfRepository.getMemberId("000000032");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        compositeDisposable.add(Common.memberMyselfRepository.TotalCountByDateRange(date1, date2).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Count>>() {
            @Override
            public void accept(List<Count> countList) throws Exception {
                Log.e("fsd", "dfsdf" + new Gson().toJson(countList));
                mAdapters = new CCDashboardAdapter(mActivity, countList);
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

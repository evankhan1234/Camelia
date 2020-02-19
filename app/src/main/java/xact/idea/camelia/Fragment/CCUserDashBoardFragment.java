package xact.idea.camelia.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xact.idea.camelia.Adapter.CCDashboardAdapter;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCUserDashBoardFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    List<Integer> ydata = new ArrayList<>();
    CCDashboardAdapter mAdapters;
    private String[] xdata = {"Incomplete", "Complete", "Referral","Follow Up"};


    PieChart pieChart;
    RecyclerView rcl_approval_in_list;
    TextView text_date_current;

    String currentDate;

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
        return view;
    }

    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date(System.currentTimeMillis());
        currentDate = formatter.format(date);

        text_date_current=view.findViewById(R.id.text_date_current);
        rcl_approval_in_list=view.findViewById(R.id.rcl_approval_in_list);

        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_approval_in_list.setLayoutManager(lm);
        pieChart = view.findViewById(R.id.pie);
        pieChart.setDrawHoleEnabled(true);

        pieChart.setDescription("Dashboard");
        pieChart.setDescriptionTextSize(13);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setCenterText("Dashboard");
        pieChart.setCenterTextSize(10);

        pieChart.setTransparentCircleRadius(50f);
        Legend l = pieChart.getLegend();
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        text_date_current.setText(currentDate);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Count count= Common.memberMyselfRepository.TotalCount();
                ydata.add(count.Incomplete1+count.Incomplete2);
                ydata.add(count.Complete);
                ydata.add(count.UHC);
                ydata.add(count.Follow);
                addDataSet();

            }
        }, 300);


    }
    private  void addDataSet() {

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
    public  class MyValueFormatter implements ValueFormatter {


        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value);
        }
    }
    private  void display() {

        mAdapters = new CCDashboardAdapter(mActivity);
        try {
            rcl_approval_in_list.setAdapter(mAdapters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //EmployeeStaus();

    }


}

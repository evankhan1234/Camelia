package xact.idea.camelia.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

public class CCIncompleStatusDetailsAdapter extends RecyclerView.Adapter<CCIncompleStatusDetailsAdapter.CCIncompleteStatusListiewHolder> {
    CCMesaurementDetailsAdapter mAdapters;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Activity mActivity = null;
    //  private List<Department> messageEntities;

    private UccMemberClickListener uccMemberClickListener;
    boolean row_index = true;
    String type;

    public CCIncompleStatusDetailsAdapter(Activity activity,String types,UccMemberClickListener uccMemberClickListeners) {
        mActivity = activity;
        type=types;
        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
         uccMemberClickListener=uccMemberClickListeners;
    }


    @Override
    public CCIncompleStatusDetailsAdapter.CCIncompleteStatusListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_status_details_list, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCIncompleStatusDetailsAdapter.CCIncompleteStatusListiewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final CCIncompleStatusDetailsAdapter.CCIncompleteStatusListiewHolder holder, final int position) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();


        if(position==0){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +" :  </font></b> <font color=#444444>"+resources.getString(R.string.bmi_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.bmi_));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));

            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("BMI",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }
        else if(position==1){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +" :  </font></b> <font color=#444444>"+resources.getString(R.string.whr_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.whr));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));
            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("WHR",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }
        else if(position==2){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +" :  </font></b> <font color=#444444>"+resources.getString(R.string.pulse_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.pulse));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));
            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("Pulse",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }
        else if(position==3){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +" :  </font></b> <font color=#444444>"+resources.getString(R.string.diabetis_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.blood_glucose));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));
            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("Diabetes",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }
        else if(position==4){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +":  </font></b> <font color=#444444>"+resources.getString(R.string.systolic_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.systolic));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));
            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("Systolic",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }
        else if(position==5){
            String normal_range = "<b><font color=#000 >"+resources.getString(R.string.normal_range) +":  </font></b> <font color=#444444>"+resources.getString(R.string.diastolic_range) +"</font>";
            holder.text_name.setText(resources.getString(R.string.diastolic));
            holder.text_normal_range.setText(Html.fromHtml(normal_range));
            compositeDisposable.add(Common.measurementsRepository.getMeasurementsItemById("Diastolic",type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Measurements>>() {
                @Override
                public void accept(List<Measurements> units) throws Exception {
                    Log.e("data","data"+new Gson().toJson(units));
                    mAdapters = new CCMesaurementDetailsAdapter(mActivity,units,uccMemberClickListener);
                    holder.rcl_this_customer_list.setAdapter(mAdapters);

                }
            }));
        }

//        if (row_index){
//            row_index=false;
//            holder.text_name.setText("Weight");
//            holder.text_weight_number.setText("Completed");
//            holder.text_weight_analysis.setText("Natural Weight");
//            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
//            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
//        }
//        else {
//            row_index=true;
//            holder.text_name.setText("High Pressure(Average)");
//            holder.text_weight_number.setText("Referral");
//            holder.text_weight_analysis.setText("Hypertension");
//            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
//            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
//
//        }
        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rcl_this_customer_list.setLayoutManager(lm);




    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return 6;
    }

    public class CCIncompleteStatusListiewHolder extends RecyclerView.ViewHolder {


        private TextView text_normal_range;
        private TextView text_name;
        private TextView text_weight_number;
        private TextView text_weight_analysis;
        private RecyclerView rcl_this_customer_list;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

            text_normal_range = itemView.findViewById(R.id.text_normal_range);
            text_name = itemView.findViewById(R.id.text_name);
            text_weight_number = itemView.findViewById(R.id.text_weight_number);
            text_weight_analysis = itemView.findViewById(R.id.text_weight_analysis);
            rcl_this_customer_list = itemView.findViewById(R.id.rcl_this_customer_list);



        }
    }
}
package xact.idea.camelia.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.ReferHistory;
import xact.idea.camelia.Database.Model.UHC;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Fragment.CCBloodPressureFragment;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.Model.DropDownModel.CenterModel;
import xact.idea.camelia.Model.DropDownModel.ClinicModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;
import static xact.idea.camelia.Utils.Utils.showLoadingProgress;

public class CCIncompleteStatusAdapter extends RecyclerView.Adapter<CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;

    private MedicineInterface uccMemberClickListener;
    int row_index = 0;
    List<MemberMyself> memberMyself;
    androidx.fragment.app.FragmentManager fragmentManagers;
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public CCIncompleteStatusAdapter(Activity activity, List<MemberMyself> memberMyselfes, MedicineInterface uccMemberClickListeners, FragmentManager fragmentManager) {
        mActivity = activity;
        uccMemberClickListener=uccMemberClickListeners;
        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        memberMyself=memberMyselfes;
        fragmentManagers=fragmentManager;
        // clickInterface=clickInterfaces;
    }


    @Override
    public CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_status_list, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder holder, final int position) {



        //    Log.e("Evan", "SDfs" + messageEntities.get(position));
        //  holder.btn_department.setHint(messageEntities.get(position).DepartmentName);



        if (memberMyself.get(position).GenderId==1){
            Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            holder.img_avatar.setImageDrawable(resource);
                        }
                    });
        }
        else{
            Glide.with(mActivity).load("https://previews.123rf.com/images/thesomeday123/thesomeday1231712/thesomeday123171200008/91087328-default-avatar-profile-icon-for-female-grey-photo-placeholder-illustrations-vector.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            holder.img_avatar.setImageDrawable(resource);
                        }
                    });
        }
        String agent_no = "<b><font color=#000 >Patient Name :  </font></b> <font color=#444444> "+memberMyself.get(position).FullName+"</font>";
        String name = "<b><font color=#000 >Member ID :  </font></b> <font color=#444444>"+memberMyself.get(position).MemberId+"</font>";
        String code = "<b><font color=#000 >Khana ID:  </font></b> <font color=#444444>"+memberMyself.get(position).UniqueId+"</font>";
        String member_id = "<b><font color=#000 >Contact No :  </font></b> <font color=#444444>"+memberMyself.get(position).MobileNumber+"</font>";
        String village = "<b><font color=#000 >Visit Date :  </font></b> <font color=#444444>"+memberMyself.get(position).VisitDate+"</font>";
        String date = "<b><font color=#000 >Ref.CC :  </font></b> <font color=#444444>N/A</font>";
        String block = "<b><font color=#000 >Condition :  </font></b> <font color=#444444> N/A</font>";
        holder.text_name.setText(Html.fromHtml(name));
        holder.text_agent.setText(Html.fromHtml(agent_no));
        holder.text_phone_number.setText(Html.fromHtml(code));
        holder.text_member_id.setText(Html.fromHtml(member_id));
        holder.text_date.setText(Html.fromHtml(date));
        holder.text_village.setText(Html.fromHtml(village));
        holder.text_block.setText(Html.fromHtml(block));
        if(Utils.isEmpty(memberMyself.get(position).VisitDate)){
            holder.text_visit.setText("Visit");
        }
        else{
            holder.text_visit.setText("Visited");
        }
        holder.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListener.postion(memberMyself.get(position).id,memberMyself.get(position).MemberId);
                //Toast.makeText(mActivity, messageEntities.get(position).FullName, Toast.LENGTH_SHORT).show();
            }
        });
        holder.text_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialogFollow(mActivity,memberMyself.get(position).MemberId);
            }
        });
        holder.text_referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialogRefer(mActivity,memberMyself.get(position).MemberId);
            }
        });
        holder.text_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showInfoDialog(mActivity,memberMyself.get(position).MemberId);
            }
        });
    }

    @Override
    public int getItemCount() {

        return memberMyself.size();
    }

    public class CCIncompleteStatusListiewHolder extends RecyclerView.ViewHolder {

        private ImageView img_avatar;
        private ImageView img_next;
        private TextView text_agent;
        private TextView text_name;
        private TextView text_phone_number;
        private TextView text_member_id;
        private TextView text_village;
        private TextView text_date;
        private TextView text_block;
        private TextView text_follow;
        private TextView text_visit;
        private TextView text_referral;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

            img_next = itemView.findViewById(R.id.img_next);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            text_agent = itemView.findViewById(R.id.text_agent);
            text_name = itemView.findViewById(R.id.text_name);
            text_phone_number = itemView.findViewById(R.id.text_phone_number);
            text_member_id = itemView.findViewById(R.id.text_member_id);
            text_village = itemView.findViewById(R.id.text_village);
            text_date = itemView.findViewById(R.id.text_date);
            text_block = itemView.findViewById(R.id.text_block);
            text_follow = itemView.findViewById(R.id.text_follow);
            text_visit = itemView.findViewById(R.id.text_visit);
            text_referral = itemView.findViewById(R.id.text_referral);


        }
    }
    public  void showInfoDialog(final Context mContext, final String member) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        RadioButton radioRefer = infoDialog.findViewById(R.id.radioRefer);
        RadioButton radioFollow = infoDialog.findViewById(R.id.radioFollow);
        radioRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radioFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                Date date = new Date(System.currentTimeMillis());
//                String currentDate = formatter.format(date);
//                Common.memberMyselfRepository.updateReciver(currentDate,member);
                infoDialog.dismiss();

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }

    public  void showInfoDialogFollow(final Activity mContext, final String member) {
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(mActivity));
        ArrayAdapter<ClinicModel> divisionArrayAdapter;
//        List<CCModel> clinicModelArrayList = new ArrayList<>();
//        clinicModelArrayList=Utils.getClinicList();
        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_followup, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        final Spinner spinner=infoDialog.findViewById(R.id.spinner_sex);
        edit_date=infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date(System.currentTimeMillis());
         Date date1 = null;
        String currentDate = formatter.format(date);
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(currentDate);
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        edit_date.setText(formatter.format(date));
        edit_date.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {


                DatePickerDeadFragments newFragment = new DatePickerDeadFragments();
                newFragment.show(fragmentManagers, "DatePicker");

            }
        });

        String divisionId="";
        String districtId="";
        String upazilaId="";
        String unionId="";
        final String[] refer = {""};
        divisionId=auth.division;
        districtId=auth.district;
        upazilaId=auth.upazila;
        unionId=auth.union;
        compositeDisposable.add(Common.ccRepository.getCCModelItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CCModel>>() {
            @Override
            public void accept(List<CCModel> customers) throws Exception {
                Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));

                ArrayAdapter<CCModel> divisionArrayAdapter;
                List<CCModel> centerModelArrayList = new ArrayList<>();

                centerModelArrayList=customers;
                divisionArrayAdapter = new ArrayAdapter<CCModel>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
                divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(divisionArrayAdapter);

                final List<CCModel> finalCenterModelArrayList = centerModelArrayList;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + finalCenterModelArrayList.get(position).name);

                        refer[0] =finalCenterModelArrayList.get(position).name;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                dismissLoadingProgress();

            }
        }));

        CorrectSizeUtil.getInstance(mContext).correctSize(main_root);
        final Date finalDate = date1;
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Common.memberMyselfRepository.updateReciverAgain("CC", refer[0], edit_date.getText().toString(), member);
                ReferHistory referHistory = new ReferHistory();
                referHistory.From = "CC";
                referHistory.To = refer[0];
                referHistory.VisitDate = edit_date.getText().toString();
                referHistory.MemberId = member;
                Common.referRepository.insertToReferHistory(referHistory);
//                Measurements measurements = new Measurements();
//                measurements.DateTime= finalDate;
//                measurements.MemberIds=member;
//                measurements.Message="N/A";
//                measurements.Type="N/A";
//                measurements.Result=0.0;
//                measurements.Refer="Follow";
//                Common.measurementsRepository.insertToMeasurements(measurements);
                Log.e("sp_water", "" + refer[0]);
                infoDialog.dismiss();


            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
     static EditText edit_date;
     static EditText edit_dates;
    public  void showInfoDialogRefer(final Context mContext, final String member) {
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(mActivity));
        showLoadingProgress(mActivity);




        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_referral, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        final Spinner spinner=infoDialog.findViewById(R.id.spinner_sex);
         edit_dates=infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date(System.currentTimeMillis());
        Date date1 = null;
        String currentDate = formatter.format(date);
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(currentDate);
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        edit_dates.setText(formatter.format(date));
        edit_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDeadFragment newFragment = new DatePickerDeadFragment();
                newFragment.show(fragmentManagers, "DatePicker");
            }
        });
        String divisionId="";
        String districtId="";
        String upazilaId="";
        String unionId="";
        final String[] refer = {""};
        divisionId=auth.division;
        districtId=auth.district;
        upazilaId=auth.upazila;
        unionId=auth.union;
        compositeDisposable.add(Common.uhcRepository.getUHCItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
            @Override
            public void accept(List<UHC> customers) throws Exception {
                Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));

                ArrayAdapter<UHC> divisionArrayAdapter;
                List<UHC> centerModelArrayList = new ArrayList<>();

                centerModelArrayList=customers;
                divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
                divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(divisionArrayAdapter);
                final List<UHC> finalCenterModelArrayList = centerModelArrayList;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + finalCenterModelArrayList.get(position).name);

                        refer[0] = finalCenterModelArrayList.get(position).name;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                dismissLoadingProgress();

            }
        }));
//        if(divisionId!=null && districtId!=null  && upazilaId!=null  && unionId!=null ){
//            compositeDisposable.add(Common.uhcRepository.getUHCItemByFour(divisionId,districtId,upazilaId,unionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
//                @Override
//                public void accept(List<UHC> customers) throws Exception {
//                    Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));
//
//                    ArrayAdapter<UHC> divisionArrayAdapter;
//                    List<UHC> centerModelArrayList = new ArrayList<>();
//
//                    centerModelArrayList=customers;
//                    divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
//                    divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner.setAdapter(divisionArrayAdapter);
//                    dismissLoadingProgress();
//
//                }
//            }));
//        }
//        else if(divisionId!=null && districtId!=null  && upazilaId!=null){
//            compositeDisposable.add(Common.uhcRepository.getUHCItemByThree(divisionId,districtId,upazilaId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
//                @Override
//                public void accept(List<UHC> customers) throws Exception {
//                    Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));
//
//                    ArrayAdapter<UHC> divisionArrayAdapter;
//                    List<UHC> centerModelArrayList = new ArrayList<>();
//
//                    centerModelArrayList=customers;
//                    divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
//                    divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner.setAdapter(divisionArrayAdapter);
//                    dismissLoadingProgress();
//
//                }
//            }));
//        }
//        else if(divisionId!=null && districtId!=null  ){
//            compositeDisposable.add(Common.uhcRepository.getUHCItemByTwo(divisionId,districtId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
//                @Override
//                public void accept(List<UHC> customers) throws Exception {
//                    Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));
//
//                    ArrayAdapter<UHC> divisionArrayAdapter;
//                    List<UHC> centerModelArrayList = new ArrayList<>();
//
//                    centerModelArrayList=customers;
//                    divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
//                    divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner.setAdapter(divisionArrayAdapter);
//                    dismissLoadingProgress();
//
//                }
//            }));
//        }
//        else if(divisionId!=null  ){
//            compositeDisposable.add(Common.uhcRepository.getUHCItemByOne(divisionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
//                @Override
//                public void accept(List<UHC> customers) throws Exception {
//                    Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));
//
//                    ArrayAdapter<UHC> divisionArrayAdapter;
//                    List<UHC> centerModelArrayList = new ArrayList<>();
//
//                    centerModelArrayList=customers;
//                    divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
//                    divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner.setAdapter(divisionArrayAdapter);
//                    dismissLoadingProgress();
//
//                }
//            }));
//        }
//        else{
//
//        }
        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        final Date finalDate = date1;
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Common.memberMyselfRepository.updateReciverAgain("UHC",   refer[0], edit_dates.getText().toString(), member);
                ReferHistory referHistory = new ReferHistory();
                referHistory.From = "UHC";
                referHistory.To = refer[0];
                referHistory.VisitDate = edit_dates.getText().toString();
                referHistory.MemberId = member;
                Common.referRepository.insertToReferHistory(referHistory);

//                Measurements measurements = new Measurements();
//                measurements.DateTime= finalDate;
//                measurements.MemberIds=member;
//                measurements.Message="N/A";
//                measurements.Type="N/A";
//                measurements.Result=0.0;
//                measurements.Refer="UHC";
//                Common.measurementsRepository.insertToMeasurements(measurements);
                infoDialog.dismiss();


            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
  public  static class DatePickerDeadFragments extends DialogFragment  implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
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
            edit_date.setText(formattedDate);
        }
    }
    public  static class DatePickerDeadFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
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
            edit_dates.setText(formattedDate);
        }
    }
}
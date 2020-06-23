package xact.idea.camelia.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;

import android.content.res.Resources;
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

import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Database.AnotherModel.VisitMyself;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.ReferHistory;
import xact.idea.camelia.Database.Model.UHC;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Visit;
import xact.idea.camelia.Fragment.CCBloodPressureFragment;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Interface.EmptyInterface;
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
    private EmptyInterface emptyInterface;
    private MedicineInterface uccMemberClickListener;
    int row_index;
    List<VisitMyself> memberMyself;
    List<MemberMyself> memberMyselfs;
    androidx.fragment.app.FragmentManager fragmentManagers;
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CCIncompleteStatusAdapter(Activity activity, List<VisitMyself> memberMyselfes, MedicineInterface uccMemberClickListeners, FragmentManager fragmentManager, int row,int data,EmptyInterface emptyInterfaces) {
        mActivity = activity;
        uccMemberClickListener = uccMemberClickListeners;
        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        memberMyself = memberMyselfes;
        fragmentManagers = fragmentManager;
        row_index = row;
        emptyInterface=emptyInterfaces;
    }
    public CCIncompleteStatusAdapter(Activity activity, List<MemberMyself> memberMyselfes, MedicineInterface uccMemberClickListeners, FragmentManager fragmentManager, int row) {
        mActivity = activity;
        uccMemberClickListener = uccMemberClickListeners;
        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        memberMyselfs = memberMyselfes;
        fragmentManagers = fragmentManager;
        row_index = row;
    }

    @Override
    public CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_status_list, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder holder, final int position) {


        if (memberMyself!=null)
        {

            if (row_index == 1 || row_index == 2 || row_index == 3) {
                holder.text_visits.setVisibility(View.GONE);
                holder.text_follow.setVisibility(View.VISIBLE);
                holder.text_referral.setVisibility(View.VISIBLE);
            } else if (row_index == 4) {
                holder.text_visits.setVisibility(View.VISIBLE);
                holder.text_follow.setVisibility(View.GONE);
                Visit visitss= Common.visitRepository.getVisitNo(String.valueOf(memberMyself.get(position).ids));
                if (visitss!=null){
                    holder.text_visits.setVisibility(View.GONE);
                    holder.text_visited.setVisibility(View.VISIBLE);
                    holder.text_visited.setText("Already Visited");
                }
            } else if (row_index == 5) {
                holder.text_visits.setVisibility(View.GONE);
                holder.text_follow.setVisibility(View.GONE);
                holder.text_referral.setVisibility(View.GONE);
                holder.text_visited.setVisibility(View.GONE);
            }

            Paper.init(mActivity);
            String language = SharedPreferenceUtil.getLanguage(mActivity);
            Paper.book().write("language", language);
            Context context = LocaleHelper.setLocale(mActivity, (String) Paper.book().read("language"));
            Resources resources = context.getResources();

            if (memberMyself.get(position).GenderId == 1) {
                Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.img_avatar.setImageDrawable(resource);
                            }
                        });
            } else {
                Glide.with(mActivity).load("https://previews.123rf.com/images/thesomeday123/thesomeday1231712/thesomeday123171200008/91087328-default-avatar-profile-icon-for-female-grey-photo-placeholder-illustrations-vector.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.img_avatar.setImageDrawable(resource);
                            }
                        });
            }
            String agent_no = "<b><font color=#000 >" + resources.getString(R.string.patient_name) + ":  </font></b> <font color=#444444> " + memberMyself.get(position).FullName + "</font>";
            String name = "<b><font color=#000 >" + resources.getString(R.string.member_id_) + " :  </font></b> <font color=#444444>" + memberMyself.get(position).MemberId + "</font>";
            String code = "<b><font color=#000 >" + resources.getString(R.string.khana_id) + ":  </font></b> <font color=#444444>" + memberMyself.get(position).UniqueId + "</font>";
            String member_id = "<b><font color=#000 >" + resources.getString(R.string.contact_no) + " :  </font></b> <font color=#444444>" + memberMyself.get(position).MobileNumber + "</font>";
            String village = "<b><font color=#000 >" + resources.getString(R.string.visit_date) + " :  </font></b> <font color=#444444>" + memberMyself.get(position).VisitDate + "</font>";
            String date = "<b><font color=#000 >" + resources.getString(R.string.ref) + " :  </font></b> <font color=#444444>N/A</font>";
            String block = "<b><font color=#000 >" + resources.getString(R.string.conditions) + ":  </font></b> <font color=#444444> N/A</font>";
            holder.text_name.setText(Html.fromHtml(name));
            holder.text_follow.setText(resources.getString(R.string.follow_));
            holder.text_referral.setText(resources.getString(R.string.refer_));
            holder.text_agent.setText(Html.fromHtml(agent_no));
            holder.text_phone_number.setText(Html.fromHtml(code));
            holder.text_member_id.setText(Html.fromHtml(member_id));
            holder.text_date.setText(Html.fromHtml(date));
            holder.text_village.setText(Html.fromHtml(village));
            holder.text_block.setText(Html.fromHtml(block));


            holder.img_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uccMemberClickListener.postion(memberMyself.get(position).id, memberMyself.get(position).UniqueCode);
                    //Toast.makeText(mActivity, messageEntities.get(position).FullName, Toast.LENGTH_SHORT).show();
                }
            });
            holder.text_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoDialogFollow(mActivity, memberMyself.get(position).UniqueId, memberMyself.get(position).UniqueCode);
                }
            });
            holder.text_referral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoDialogRefer(mActivity, memberMyself.get(position).UniqueId, memberMyself.get(position).UniqueCode);
                }
            });
            holder.text_visits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoDialog(mActivity, memberMyself.get(position).ids);
                    emptyInterface.empty();
                }
            });
        }
        else if (memberMyselfs!=null){

            if (row_index == 1 || row_index == 2 || row_index == 3) {
                holder.text_visits.setVisibility(View.GONE);
                holder.text_follow.setVisibility(View.VISIBLE);
                holder.text_referral.setVisibility(View.VISIBLE);
            } else if (row_index == 4) {
                holder.text_visits.setVisibility(View.VISIBLE);
                holder.text_follow.setVisibility(View.GONE);
            } else if (row_index == 5) {
                holder.text_visits.setVisibility(View.GONE);
                holder.text_follow.setVisibility(View.GONE);
                holder.text_referral.setVisibility(View.GONE);
            }

            Paper.init(mActivity);
            String language = SharedPreferenceUtil.getLanguage(mActivity);
            Paper.book().write("language", language);
            Context context = LocaleHelper.setLocale(mActivity, (String) Paper.book().read("language"));
            Resources resources = context.getResources();

            if (memberMyselfs.get(position).GenderId == 1) {
                Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.img_avatar.setImageDrawable(resource);
                            }
                        });
            } else {
                Glide.with(mActivity).load("https://previews.123rf.com/images/thesomeday123/thesomeday1231712/thesomeday123171200008/91087328-default-avatar-profile-icon-for-female-grey-photo-placeholder-illustrations-vector.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.img_avatar.setImageDrawable(resource);
                            }
                        });
            }
            String agent_no = "<b><font color=#000 >" + resources.getString(R.string.patient_name) + ":  </font></b> <font color=#444444> " + memberMyselfs.get(position).FullName + "</font>";
            String name = "<b><font color=#000 >" + resources.getString(R.string.member_id_) + " :  </font></b> <font color=#444444>" + memberMyselfs.get(position).MemberId + "</font>";
            String code = "<b><font color=#000 >" + resources.getString(R.string.khana_id) + ":  </font></b> <font color=#444444>" + memberMyselfs.get(position).UniqueId + "</font>";
            String member_id = "<b><font color=#000 >" + resources.getString(R.string.contact_no) + " :  </font></b> <font color=#444444>" + memberMyselfs.get(position).MobileNumber + "</font>";
            String village = "<b><font color=#000 >" + resources.getString(R.string.visit_date) + " :  </font></b> <font color=#444444>" + memberMyselfs.get(position).VisitDate + "</font>";
            String date = "<b><font color=#000 >" + resources.getString(R.string.ref) + " :  </font></b> <font color=#444444>N/A</font>";
            String block = "<b><font color=#000 >" + resources.getString(R.string.conditions) + ":  </font></b> <font color=#444444> N/A</font>";
            holder.text_name.setText(Html.fromHtml(name));
            holder.text_follow.setText(resources.getString(R.string.follow_));
            holder.text_referral.setText(resources.getString(R.string.refer_));
            holder.text_agent.setText(Html.fromHtml(agent_no));
            holder.text_phone_number.setText(Html.fromHtml(code));
            holder.text_member_id.setText(Html.fromHtml(member_id));
            holder.text_date.setText(Html.fromHtml(date));
            holder.text_village.setText(Html.fromHtml(village));
            holder.text_block.setText(Html.fromHtml(block));
            if (Utils.isEmpty(memberMyselfs.get(position).VisitDate)) {
                holder.text_visit.setText("Visit");
            } else {
                holder.text_visit.setText("Visited");
            }
            holder.img_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uccMemberClickListener.postion(memberMyselfs.get(position).id, memberMyselfs.get(position).UniqueCode);
                    //Toast.makeText(mActivity, messageEntities.get(position).FullName, Toast.LENGTH_SHORT).show();
                }
            });
            holder.text_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoDialogFollow(mActivity, memberMyselfs.get(position).UniqueId, memberMyselfs.get(position).UniqueCode);
                }
            });
            holder.text_referral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoDialogRefer(mActivity, memberMyselfs.get(position).UniqueId, memberMyselfs.get(position).UniqueCode);
                }
            });
            holder.text_visits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  showInfoDialog(mActivity, memberMyself.get(position).UniqueCode);
                }
            });
        }

    }

    @Override
    public int getItemCount() {

        if (memberMyself!=null){
            return memberMyself.size();
        }
        else if(memberMyselfs!=null){
            return memberMyselfs.size();
        }
        else{
            return memberMyself.size();
        }

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
        public TextView text_visits;
        private TextView text_visited;
        private TextView text_referral;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

            text_visited = itemView.findViewById(R.id.text_visited);
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
            text_visits = itemView.findViewById(R.id.text_visits);
            text_referral = itemView.findViewById(R.id.text_referral);


        }
    }

    public void showInfoDialog(final Context mContext, final int member) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Spinner spinner_sex = infoDialog.findViewById(R.id.spinner_sex);
        TextView tv_info = infoDialog.findViewById(R.id.tv_info);
        tv_info.setText("Are you Confirm Visit?");
        spinner_sex.setVisibility(View.GONE);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        RadioButton radioRefer = infoDialog.findViewById(R.id.radioRefer);
        RadioButton radioFollow = infoDialog.findViewById(R.id.radioFollow);
        edit_dateq = infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date(System.currentTimeMillis());
        Date date1 = null;
        final String currentDate = formatter1.format(date);

        edit_dateq.setText(formatter.format(date));
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(edit_dateq.getText().toString());
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String visits = formatter1.format(date1);
        edit_dateq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerVisitFragment newFragment = new DatePickerVisitFragment();
                newFragment.show(fragmentManagers, "DatePicker");
            }
        });
        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Visit visit = new Visit();
                visit.Created = currentDate;
                visit.VisitDate = visits;
                visit.RefId = String.valueOf(member);
                visit.VisitStatus = "1";
                Common.visitRepository.insertToVisit(visit);
                emptyInterface.empty();
                notifyDataSetChanged();
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

    public void showInfoDialogFollow(final Activity mContext,final String uniqueId,  final String uniqueCode) {
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
        final Spinner spinner = infoDialog.findViewById(R.id.spinner_sex);
        edit_date = infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date(System.currentTimeMillis());
        Date date1 = null;
        String currentDate = formatter.format(date);
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(edit_date.getText().toString());
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String visitDate = formatter1.format(date1);
        edit_date.setText(formatter.format(date));
        edit_date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                DatePickerDeadFragments newFragment = new DatePickerDeadFragments();
                newFragment.show(fragmentManagers, "DatePicker");

            }
        });

        String divisionId = "";
        String districtId = "";
        String upazilaId = "";
        String unionId = "";
        final String[] refer = {""};
        divisionId = auth.division;
        districtId = auth.district;
        upazilaId = auth.upazila;
        unionId = auth.union;
        compositeDisposable.add(Common.ccRepository.getCCModelItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CCModel>>() {
            @Override
            public void accept(List<CCModel> customers) throws Exception {
                Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));

                ArrayAdapter<CCModel> divisionArrayAdapter;
                List<CCModel> centerModelArrayList = new ArrayList<>();

                centerModelArrayList = customers;
                divisionArrayAdapter = new ArrayAdapter<CCModel>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
                divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(divisionArrayAdapter);

                final List<CCModel> finalCenterModelArrayList = centerModelArrayList;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + finalCenterModelArrayList.get(position).name);

                        refer[0] = String.valueOf(finalCenterModelArrayList.get(position).CCId);

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
                ReferHistory history = Common.referRepository.getReferHistoryFrom(uniqueId );
                if (history != null) {
                    Common.referRepository.updateReferHistoryFrom(uniqueId );
                }
                Common.memberMyselfRepository.updateReciverAgain("cc", refer[0], edit_date.getText().toString(), uniqueId);
                ReferHistory referHistory = new ReferHistory();
                referHistory.From = "cc";
                referHistory.To = "cc";
                //referHistory.UpdateNo ="1";
                referHistory.ToId = refer[0];
                referHistory.VisitDate = visitDate;
                referHistory.MemberUniqueCode = uniqueCode ;
                referHistory.UniqueId = uniqueId;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date12 = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(date12);
                referHistory.FromId = "";
                referHistory.Reason = "1";
                Date date1 = null;
                try {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                    // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                referHistory.Date = date1;
                referHistory.created_at = currentDate;
                referHistory.Type = "0";
                Common.referRepository.insertToReferHistory(referHistory);
                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
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
    static EditText edit_dateq;
    static EditText edit_dates;

    public void showInfoDialogRefer(final Context mContext,  final String uniqueId,  final String uniqueCode) {
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
        final Spinner spinner = infoDialog.findViewById(R.id.spinner_sex);
        edit_dates = infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date(System.currentTimeMillis());
        Date date1 = null;
        String currentDate = formatter.format(date);
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(edit_dates.getText().toString());
            // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String visitDate = formatter1.format(date1);
        edit_dates.setText(formatter.format(date));
        edit_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDeadFragment newFragment = new DatePickerDeadFragment();
                newFragment.show(fragmentManagers, "DatePicker");
            }
        });
        String divisionId = "";
        String districtId = "";
        String upazilaId = "";
        String unionId = "";
        final String[] refer = {""};
        divisionId = auth.division;
        districtId = auth.district;
        upazilaId = auth.upazila;
        unionId = auth.union;
        compositeDisposable.add(Common.uhcRepository.getUHCItems().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UHC>>() {
            @Override
            public void accept(List<UHC> customers) throws Exception {
                Log.e("uhcRepository", "uhcRepository" + new Gson().toJson(customers));

                ArrayAdapter<UHC> divisionArrayAdapter;
                List<UHC> centerModelArrayList = new ArrayList<>();

                centerModelArrayList = customers;
                divisionArrayAdapter = new ArrayAdapter<UHC>(mActivity, android.R.layout.simple_spinner_item, centerModelArrayList);
                divisionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(divisionArrayAdapter);
                final List<UHC> finalCenterModelArrayList = centerModelArrayList;
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("sp_water", "" + finalCenterModelArrayList.get(position).UHCId);

                        refer[0] = String.valueOf(finalCenterModelArrayList.get(position).UHCId);

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
                ReferHistory history = Common.referRepository.getReferHistoryFrom(uniqueId );
                if (history != null) {
                    Common.referRepository.updateReferHistoryFrom(uniqueId );
                }
                Common.memberMyselfRepository.updateReciverAgain("uhc", refer[0], edit_dates.getText().toString(), uniqueId);
                ReferHistory referHistory = new ReferHistory();
                referHistory.From = "cc";
                referHistory.To = "uhc";
//                referHistory.UpdateNo ="1";
                referHistory.ToId = refer[0];
                referHistory.VisitDate = visitDate;
                referHistory.MemberUniqueCode = uniqueCode ;
                referHistory.UniqueId = uniqueId;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date12 = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(date12);
                referHistory.FromId = "";
                referHistory.Reason = "1";
                Date date1 = null;
                try {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                    // date2= new SimpleDateFormat("yy-MM-dd").parse(edit_date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                referHistory.Date = date1;
                referHistory.created_at = currentDate;
                referHistory.Type = "2";
                Common.referRepository.insertToReferHistory(referHistory);
                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");
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

    public static class DatePickerDeadFragments extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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

    public static class DatePickerDeadFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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

    public static class DatePickerVisitFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
            edit_dateq.setText(formattedDate);
        }
    }
}
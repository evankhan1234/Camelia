package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Filter;
import android.widget.Filterable;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.Activity.SpalashActivity;
import xact.idea.camelia.Adapter.CCIncompleteStatusAdapter;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.ReferHistory;
import xact.idea.camelia.Filter.MemberFilter;
import xact.idea.camelia.Filter.NotSyncFilter;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;

public class HHMemberListAdapter extends RecyclerView.Adapter<HHMemberListAdapter.CCDashboardListiewHolder> implements Filterable {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Activity mActivity = null;
    int row_index = 0;
    public List<MemberMyself> memberMyselfes;
    MedicineInterface medicineInterface;
    MemberFilter memberFilter;
    FragmentManager fragmentManagers;

    public HHMemberListAdapter(Activity activity, List<MemberMyself> memberMyselfe, MedicineInterface medicineInterfaces, FragmentManager fragmentManager) {
        mActivity = activity;
        memberMyselfes = memberMyselfe;
        medicineInterface = medicineInterfaces;
        fragmentManagers = fragmentManager;
    }


    @Override
    public HHMemberListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHMemberListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHMemberListAdapter.CCDashboardListiewHolder holder, final int position) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        holder.text_name.setText(memberMyselfes.get(position).FullName);
        holder.text_phone.setText(memberMyselfes.get(position).MemberId);
        String agent_no = "<b><font color=#000 >"+resources.getString(R.string.name_) +":  </font></b> <font color=#03A9F4> " + memberMyselfes.get(position).FullName + "</font>";
        String mobile = "<b><font color=#000 >"+resources.getString(R.string.member_id) +":  </font></b> <font color=#03A9F4> " + memberMyselfes.get(position).MemberId + "</font>";
        String member = "<b><font color=#000 >"+resources.getString(R.string.number_) +" :  </font></b> <font color=#03A9F4> " + memberMyselfes.get(position).MobileNumber + "</font>";
        String dateof = "<b><font color=#000 >"+resources.getString(R.string.dob_) +" :  </font></b> <font color=#03A9F4> " + memberMyselfes.get(position).DateOfBirth + "</font>";
        holder.text_name.setText(Html.fromHtml(agent_no));
        holder.text_phone.setText(Html.fromHtml(member));
        holder.text_date_of.setText(Html.fromHtml(dateof));
        holder.text_mobile_nmber.setText(Html.fromHtml(mobile));
        holder.text_update.setText(resources.getString(R.string.view));
        if (memberMyselfes.get(position).GenderId == 1) {
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
        holder.text_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineInterface.postion(position, memberMyselfes.get(position).MemberId);
            }
        });
        if (SharedPreferenceUtil.getUserRole(mActivity).equals("hh")) {
            holder.text_cc.setVisibility(View.VISIBLE);
            holder.text_cc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfoDialog(mActivity, memberMyselfes.get(position).MemberId);
                }
            });
        } else {

            holder.text_cc.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return memberMyselfes.size();
    }

    @Override
    public Filter getFilter() {
        if (memberFilter == null) {
            memberFilter = new MemberFilter(memberMyselfes, this);
        }
        return memberFilter;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_avatar;
        private Button btn_department;
        private TextView text_name;
        private TextView text_phone;
        private TextView text_mobile_nmber;
        private TextView text_date_of;
        private TextView text_update;
        private TextView text_cc;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            text_name = itemView.findViewById(R.id.text_name);
            text_phone = itemView.findViewById(R.id.text_phone);
            text_mobile_nmber = itemView.findViewById(R.id.text_mobile_nmber);
            text_date_of = itemView.findViewById(R.id.text_date_of);
            text_update = itemView.findViewById(R.id.text_update);
            text_cc = itemView.findViewById(R.id.text_cc);
        }
    }

    public void showInfoDialog(final Context mContext, final String member) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(mActivity));
        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);
        final Spinner spinner = infoDialog.findViewById(R.id.spinner_sex);
        RadioButton radioRefer = infoDialog.findViewById(R.id.radioRefer);
        RadioButton radioFollow = infoDialog.findViewById(R.id.radioFollow);
        edit_date = infoDialog.findViewById(R.id.edit_date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date(System.currentTimeMillis());
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
        final int[] refer ={0};
        divisionId = auth.division;
        districtId = auth.district;
        upazilaId = auth.upazila;
        unionId = auth.union;
        if (divisionId != null && districtId != null && upazilaId != null && unionId != null) {
            compositeDisposable.add(Common.ccRepository.getCCModelItemByFour(divisionId,districtId,upazilaId,unionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CCModel>>() {
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

                            refer[0] = finalCenterModelArrayList.get(position).CCId;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dismissLoadingProgress();

                }
            }));
        } else if (divisionId != null && districtId != null && upazilaId != null) {
            compositeDisposable.add(Common.ccRepository.getCCModelItemByThree(divisionId,districtId,upazilaId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CCModel>>() {
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

                            refer[0] = finalCenterModelArrayList.get(position).CCId;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dismissLoadingProgress();

                }
            }));
        } else if (divisionId != null && districtId != null) {
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

                            refer[0] = finalCenterModelArrayList.get(position).CCId;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dismissLoadingProgress();

                }
            }));
        } else if (divisionId != null) {
            compositeDisposable.add(Common.ccRepository.getCCModelItemByOne(divisionId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CCModel>>() {
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

                            refer[0] = finalCenterModelArrayList.get(position).CCId;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dismissLoadingProgress();

                }
            }));
        } else {
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

                            refer[0] = finalCenterModelArrayList.get(position).CCId;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dismissLoadingProgress();

                }
            }));
        }

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
                Common.memberMyselfRepository.updateReciverAgain("CC", String.valueOf(refer[0]), edit_date.getText().toString(), member);
                ReferHistory referHistory = new ReferHistory();
                referHistory.From = "CC";
                referHistory.To = String.valueOf(refer[0]);
                referHistory.VisitDate = edit_date.getText().toString();
                referHistory.MemberId = member;
                Common.referRepository.insertToReferHistory(referHistory);
                infoDialog.dismiss();
                SharedPreferenceUtil.saveShared(mActivity, SharedPreferenceUtil.SYNC, "on");

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
}
package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.Activity.SpalashActivity;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Filter.MemberFilter;
import xact.idea.camelia.Filter.NotSyncFilter;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

public class HHMemberListAdapter extends RecyclerView.Adapter<HHMemberListAdapter.CCDashboardListiewHolder> implements Filterable {


    private Activity mActivity = null;
    int row_index = 0;
   public List<MemberMyself> memberMyselfes;
    MedicineInterface  medicineInterface;
    MemberFilter memberFilter;
    public HHMemberListAdapter(Activity activity, List<MemberMyself> memberMyselfe, MedicineInterface  medicineInterfaces) {
        mActivity = activity;
        memberMyselfes=memberMyselfe;
        medicineInterface=medicineInterfaces;
    }


    @Override
    public HHMemberListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHMemberListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHMemberListAdapter.CCDashboardListiewHolder holder, final int position) {

        holder.text_name.setText(memberMyselfes.get(position).FullName);
        holder.text_phone.setText(memberMyselfes.get(position).MemberId);
        String agent_no = "<b><font color=#000 >Name :  </font></b> <font color=#03A9F4> "+memberMyselfes.get(position).FullName + "</font>";
        String mobile = "<b><font color=#000 >MemberId :  </font></b> <font color=#03A9F4> "+memberMyselfes.get(position).MemberId + "</font>";
        String member = "<b><font color=#000 >Mobile :  </font></b> <font color=#03A9F4> "+memberMyselfes.get(position).MobileNumber + "</font>";
        String dateof = "<b><font color=#000 >DOB :  </font></b> <font color=#03A9F4> "+memberMyselfes.get(position).DateOfBirth + "</font>";
        holder.text_name.setText(Html.fromHtml(agent_no));
        holder.text_phone.setText(Html.fromHtml(member));
        holder.text_date_of.setText(Html.fromHtml(dateof));
        holder.text_mobile_nmber.setText(Html.fromHtml(mobile));
        if (memberMyselfes.get(position).GenderId==1){
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
        holder.text_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineInterface.postion(position,memberMyselfes.get(position).MemberId);
            }
        });
        if (SharedPreferenceUtil.getUserRole(mActivity).equals("hh")){
            holder.text_cc.setVisibility(View.VISIBLE);
        }
        else {

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
            img_avatar=itemView.findViewById(R.id.img_avatar);
            text_name=itemView.findViewById(R.id.text_name);
            text_phone=itemView.findViewById(R.id.text_phone);
            text_mobile_nmber=itemView.findViewById(R.id.text_mobile_nmber);
            text_date_of=itemView.findViewById(R.id.text_date_of);
            text_update=itemView.findViewById(R.id.text_update);
            text_cc=itemView.findViewById(R.id.text_cc);
        }
    }
}
package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Filter.HouseholdFilter;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHListAdapter extends RecyclerView.Adapter<HHListAdapter.CCDashboardListiewHolder> implements Filterable {


    private Activity mActivity = null;
    int row_index = 0;
    private MedicineInterface uccMemberClickListener;
    public   List<HouseHold> houseHolds;
    HouseholdFilter householdFilter;
    public HHListAdapter(Activity activity,List<HouseHold> houseHold,MedicineInterface uccMemberClickListeners) {
        mActivity = activity;
        uccMemberClickListener=uccMemberClickListeners;
        houseHolds=houseHold;
    }


    @Override
    public HHListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hh_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHListAdapter.CCDashboardListiewHolder holder, final int position) {
        MemberMyself memberMyself= Common.memberMyselfRepository.getMemberMyselfForHousehold(houseHolds.get(position).UniqueId);
        String head="";
        String number="";
        if (memberMyself!=null){
             head = "<b><font color=#000 >Khana Head :  </font></b> <font color=#444444> "+memberMyself.FullName + "</font>";
             number = "<b><font color=#000 >Phone Number :  </font></b> <font color=#444444> "+memberMyself.MobileNumber + "</font>";
        }
        else {
            head = "<b><font color=#000 >Khana Head :  </font></b> <font color=#444444>N/A</font>";
            number = "<b><font color=#000 >Phone Number :  </font></b> <font color=#444444>N/A</font>";
        }

        Glide.with(mActivity).load("https://cdn4.vectorstock.com/i/1000x1000/84/88/home-icon-mobile-app-and-web-site-start-main-page-vector-23828488.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.img_avatar.setImageDrawable(resource);
                    }
                });
        holder.text_khana_head.setText(Html.fromHtml(head));
        holder.text_no.setText(Html.fromHtml(String.valueOf(houseHolds.get(position).UniqueId)));
        holder.text_phone_number.setText(Html.fromHtml(number));
        holder.text_head.setText(Html.fromHtml(houseHolds.get(position).VillageName));
        holder.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListener.postion(position,houseHolds.get(position).UniqueId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return houseHolds.size();
    }

    @Override
    public Filter getFilter() {
        if (householdFilter == null) {
            householdFilter = new HouseholdFilter(houseHolds, this);
        }
        return householdFilter;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_no;
        private TextView text_head;
        private TextView text_khana_head;
        private TextView text_phone_number;
        CircleImageView img_avatar;
        ImageView img_next;


        public CCDashboardListiewHolder(View itemView) {

            super(itemView);
            text_no=itemView.findViewById(R.id.text_no);
            text_head=itemView.findViewById(R.id.text_head);
            text_khana_head=itemView.findViewById(R.id.text_khana_head);
            text_phone_number=itemView.findViewById(R.id.text_phone_number);
            img_avatar=itemView.findViewById(R.id.img_avatar);
            img_next=itemView.findViewById(R.id.img_next);
        }
    }
}
package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHListAdapter extends RecyclerView.Adapter<HHListAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;
    private MedicineInterface uccMemberClickListener;
    List<HouseHold> houseHolds;

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
        String head = "<b><font color=#000 >Thana Head :  </font></b> <font color=#444444> Rojob Ali</font>";
        String number = "<b><font color=#000 >Phone Number :  </font></b> <font color=#444444> +8801677182084</font>";
        Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.img_avatar.setImageDrawable(resource);
                    }
                });
        holder.text_khana_head.setText(Html.fromHtml(head));
        holder.text_no.setText(Html.fromHtml(String.valueOf(houseHolds.get(position).MemberId)));
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
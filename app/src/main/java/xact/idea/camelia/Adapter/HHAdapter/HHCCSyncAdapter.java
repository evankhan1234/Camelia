package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Filter.HouseholdFilter;
import xact.idea.camelia.Filter.NotSyncFilter;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHCCSyncAdapter extends RecyclerView.Adapter<HHCCSyncAdapter.CCDashboardListiewHolder> implements Filterable {


    private Activity mActivity = null;
    int row_index = 0;
    private UccMemberClickListener uccMemberClickListener;
    public  List<SentSyncModel> sentSyncModel;

    NotSyncFilter notSyncFilter;

    public HHCCSyncAdapter(Activity activity, List<SentSyncModel> sentSyncModels) {
        mActivity = activity;
        sentSyncModel = sentSyncModels;

    }


    @Override
    public HHCCSyncAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cc_sending_item_sync, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHCCSyncAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHCCSyncAdapter.CCDashboardListiewHolder holder, final int position) {
        String head = "<b><font color=#000 >Village :  </font></b> <font color=#444444>"+sentSyncModel.get(position).VillageName+"</font>";

        holder.text_village.setText(Html.fromHtml(head));
        holder.text_name.setText(sentSyncModel.get(position).FullName);
        holder.text_member_id.setText(sentSyncModel.get(position).MemberId);
        holder.text_unique_id.setText(sentSyncModel.get(position).UniqueId);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = formatter.format(sentSyncModel.get(position).CreatedDate);
        holder.text_date.setText(currentDate);


    }

    @Override
    public int getItemCount() {
        return sentSyncModel.size();
    }

    @Override
    public Filter getFilter() {
        if (notSyncFilter == null) {
            notSyncFilter = new NotSyncFilter(sentSyncModel, this);
        }
        return notSyncFilter;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_village;

        private TextView text_name;
        private TextView text_date;
        private TextView text_member_id;
        private TextView text_unique_id;


        public CCDashboardListiewHolder(View itemView) {

            super(itemView);
            text_village = itemView.findViewById(R.id.text_village);
            text_name = itemView.findViewById(R.id.text_name);
            text_date = itemView.findViewById(R.id.text_date);
            text_unique_id = itemView.findViewById(R.id.text_unique_id);
            text_member_id = itemView.findViewById(R.id.text_member_id);

        }
    }
}
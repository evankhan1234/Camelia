package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import de.hdodenhof.circleimageview.CircleImageView;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHCCSyncAdapter extends RecyclerView.Adapter<HHCCSyncAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;
    private UccMemberClickListener uccMemberClickListener;
    public HHCCSyncAdapter(Activity activity,UccMemberClickListener uccMemberClickListeners) {
        mActivity = activity;
        uccMemberClickListener=uccMemberClickListeners;

    }


    @Override
    public HHCCSyncAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cc_sending_item_sync, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHCCSyncAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHCCSyncAdapter.CCDashboardListiewHolder holder, final int position) {
        String head = "<b><font color=#000 >Village :  </font></b> <font color=#444444> Netrokona</font>";

        holder.text_village.setText(Html.fromHtml(head));


    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_village;



        public CCDashboardListiewHolder(View itemView) {

            super(itemView);
            text_village=itemView.findViewById(R.id.text_village);

        }
    }
}
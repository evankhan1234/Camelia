package xact.idea.camelia.Adapter;

import android.annotation.SuppressLint;
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

import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCIncompleStatusDetailsAdapter extends RecyclerView.Adapter<CCIncompleStatusDetailsAdapter.CCIncompleteStatusListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;

    private UccMemberClickListener uccMemberClickListener;
    boolean row_index = true;

    public CCIncompleStatusDetailsAdapter(Activity activity) {
        mActivity = activity;

        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        // clickInterface=clickInterfaces;
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




        String normal_range = "<b><font color=#000 >Normal Range :  </font></b> <font color=#444444>18.5-24.9</font>";

        holder.text_normal_range.setText(Html.fromHtml(normal_range));
        if (row_index){
            row_index=false;
            holder.text_name.setText("Weight");
            holder.text_weight_number.setText("Completed");
            holder.text_weight_analysis.setText("Natural Weight");
            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
        }
        else {
            row_index=true;
            holder.text_name.setText("High Pressure(Average)");
            holder.text_weight_number.setText("Referral");
            holder.text_weight_analysis.setText("Hypertension");
            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));

        }

    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return 20;
    }

    public class CCIncompleteStatusListiewHolder extends RecyclerView.ViewHolder {


        private TextView text_normal_range;
        private TextView text_name;
        private TextView text_weight_number;
        private TextView text_weight_analysis;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

            text_normal_range = itemView.findViewById(R.id.text_normal_range);
            text_name = itemView.findViewById(R.id.text_name);
            text_weight_number = itemView.findViewById(R.id.text_weight_number);
            text_weight_analysis = itemView.findViewById(R.id.text_weight_analysis);



        }
    }
}
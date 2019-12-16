package xact.idea.camelia.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Fragment.CCuserMesaurementsFragment;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.Interface.aa;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;


public class CCMesaurementDetailsAdapter extends RecyclerView.Adapter<CCMesaurementDetailsAdapter.CCIncompleteStatusListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;

   private UccMemberClickListener uccMemberClickListener;

    boolean row_index = true;

    public CCMesaurementDetailsAdapter(Activity activity, UccMemberClickListener uccMemberClickListeners) {
        mActivity = activity;

        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        uccMemberClickListener=uccMemberClickListeners;
    }


    @Override
    public CCMesaurementDetailsAdapter.CCIncompleteStatusListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wight_details_list, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCMesaurementDetailsAdapter.CCIncompleteStatusListiewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final CCMesaurementDetailsAdapter.CCIncompleteStatusListiewHolder holder, final int position) {




        String normal_range = "<b><font color=#000 >Normal Range :  </font></b> <font color=#444444>18.5-24.9</font>";

//        holder.text_normal_range.setText(Html.fromHtml(normal_range));
        if (row_index){
            row_index=false;

            holder.text_weight_number.setText("Completed");
            holder.text_weight_analysis.setText("Natural Weight");
            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_accepted));
        }
        else {
            row_index=true;

            holder.text_weight_number.setText("Referral");
            holder.text_weight_analysis.setText("Hypertension");
            holder.text_weight_number.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));
            holder.text_weight_analysis.setBackground(mActivity.getResources().getDrawable(R.drawable.status_reject));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListener.onItemClick(1);
            }
        });

    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return 20;
    }

    public class CCIncompleteStatusListiewHolder extends RecyclerView.ViewHolder {


        private TextView text_normal_range;

        private TextView text_weight_number;
        private TextView text_weight_analysis;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

         //   text_normal_range = itemView.findViewById(R.id.text_normal_range);

            text_weight_number = itemView.findViewById(R.id.text_weight_number);
            text_weight_analysis = itemView.findViewById(R.id.text_weight_analysis);



        }
    }
}
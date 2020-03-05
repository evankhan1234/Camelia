package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCDashboardAdapter extends RecyclerView.Adapter<CCDashboardAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;

    List<Count> countList;
 
    public CCDashboardAdapter(Activity activity,  List<Count> countLists) {
        mActivity = activity;
        countList=countLists;
    }


    @Override
    public CCDashboardAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cc_dashboard_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCDashboardAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCDashboardAdapter.CCDashboardListiewHolder holder, final int position) {

        int count= countList.get(position).Incomplete1+countList.get(position).Incomplete2;
        holder.text_incomplete.setText(String.valueOf(count));
        holder.text_complete.setText(String.valueOf(countList.get(position).Complete));
        holder.text_referral.setText(String.valueOf(countList.get(position).UHC));
        holder.text_follow_up.setText(String.valueOf(countList.get(position).Follow));
    }

    @Override
    public int getItemCount() {
        return countList.size();
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private Button btn_department;
        private TextView text_date;
        private TextView text_incomplete;
        private TextView text_complete;
        private TextView text_referral;
        private TextView text_follow_up;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
            text_date = itemView.findViewById(R.id.text_date);
            text_incomplete = itemView.findViewById(R.id.text_incomplete);
            text_complete = itemView.findViewById(R.id.text_complete);
            text_referral = itemView.findViewById(R.id.text_referral);
            text_follow_up = itemView.findViewById(R.id.text_follow_up);
        }
    }
}
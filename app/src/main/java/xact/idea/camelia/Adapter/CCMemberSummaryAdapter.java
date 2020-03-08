package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import xact.idea.camelia.Database.AnotherModel.SummaryModel;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCMemberSummaryAdapter extends RecyclerView.Adapter<CCMemberSummaryAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;
    int row_index = 0;
    List<SummaryModel> countList;
    public CCMemberSummaryAdapter(Activity activity,List<SummaryModel> countLists) {
        mActivity = activity;
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        // clickInterface=clickInterfaces;
        countList=countLists;
    }


    @Override
    public CCMemberSummaryAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cc_member_summary_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCMemberSummaryAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCMemberSummaryAdapter.CCDashboardListiewHolder holder, final int position) {


        //    Log.e("Evan", "SDfs" + messageEntities.get(position));
         holder.text_weight_number.setText(String.valueOf(countList.get(position).OverWeight));
         holder.text_diabteic.setText(String.valueOf(countList.get(position).Diabetes));
         holder.text_hypertension.setText(String.valueOf(countList.get(position).Hypertension));
         holder.text_bmi.setText(String.valueOf(countList.get(position).Obese));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = formatter.format(countList.get(position).DateTime);


        holder.text_date.setText(currentDate);


    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return countList.size();
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_date;
        private TextView text_hypertension;
        private TextView text_diabteic;
        private TextView text_bmi;
        private TextView text_weight_number;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);

            text_date = itemView.findViewById(R.id.text_date);
            text_hypertension = itemView.findViewById(R.id.text_hypertension);
            text_diabteic = itemView.findViewById(R.id.text_diabteic);
            text_bmi = itemView.findViewById(R.id.text_bmi);
            text_weight_number = itemView.findViewById(R.id.text_weight_number);


        }
    }
}
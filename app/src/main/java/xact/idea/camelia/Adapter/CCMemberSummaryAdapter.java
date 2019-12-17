package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCMemberSummaryAdapter extends RecyclerView.Adapter<CCMemberSummaryAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;
    int row_index = 0;

    public CCMemberSummaryAdapter(Activity activity) {
        mActivity = activity;
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        // clickInterface=clickInterfaces;
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
        //  holder.btn_department.setHint(messageEntities.get(position).DepartmentName);






    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return 20;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private Button btn_department;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);

            //  btn_department = itemView.findViewById(R.id.btn_department);


        }
    }
}
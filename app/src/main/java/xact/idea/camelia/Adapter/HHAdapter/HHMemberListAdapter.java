package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHMemberListAdapter extends RecyclerView.Adapter<HHMemberListAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;

    public HHMemberListAdapter(Activity activity) {
        mActivity = activity;

    }


    @Override
    public HHMemberListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHMemberListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHMemberListAdapter.CCDashboardListiewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private Button btn_department;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
        }
    }
}
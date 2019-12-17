package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;


import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHListAdapter extends RecyclerView.Adapter<HHListAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;

    public HHListAdapter(Activity activity) {
        mActivity = activity;

    }


    @Override
    public HHListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hh_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHListAdapter.CCDashboardListiewHolder holder, final int position) {

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
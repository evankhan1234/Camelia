package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHSurveysAdapter extends RecyclerView.Adapter<HHSurveysAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;

    public HHSurveysAdapter(Activity activity) {
        mActivity = activity;

    }


    @Override
    public HHSurveysAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_servey_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHSurveysAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHSurveysAdapter.CCDashboardListiewHolder holder, final int position) {

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
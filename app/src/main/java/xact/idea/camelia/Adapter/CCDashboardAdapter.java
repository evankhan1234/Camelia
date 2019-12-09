package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class CCDashboardAdapter extends RecyclerView.Adapter<CCDashboardAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
  //  private List<Department> messageEntities;
    int row_index = 0;
 
    public CCDashboardAdapter(Activity activity) {
        mActivity = activity;
        //messageEntities = messageEntitie;
        //mClick = mClicks;
       // clickInterface=clickInterfaces;
    }


    @Override
    public CCDashboardAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cc_dashboard_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCDashboardAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCDashboardAdapter.CCDashboardListiewHolder holder, final int position) {


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
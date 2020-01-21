package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xact.idea.camelia.Database.Model.Survey;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHSurveysAdapter extends RecyclerView.Adapter<HHSurveysAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    List<Survey> survey;
    public HHSurveysAdapter(Activity activity,List<Survey> surveys) {
        mActivity = activity;
        survey=surveys;
    }


    @Override
    public HHSurveysAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_servey_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHSurveysAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHSurveysAdapter.CCDashboardListiewHolder holder, final int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        holder.text_date.setText(formatter.format(survey.get(position).CreatedDate));
        String safe="";
        String sanitary="";
        String bondhoChula="";
        String biomasFuel="";
        if (survey.get(position).SafeDrinkingYesNo==1){
            safe  = "<b><font color=#000 >Safe Drinking Water :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            safe  = "<b><font color=#000 >Safe Drinking Water:  </font></b> <font color=#358ED3> No </font>";
            String store = "<b><font color=#000 >Store Id :  </font></b> <font color=#358ED3></font>";

        }
        if (survey.get(position).SanitaryYesNo==1){
            sanitary  = "<b><font color=#000 >Sanitary Latrine :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            sanitary  = "<b><font color=#000 >Sanitary Latrine :  </font></b> <font color=#358ED3> No </font>";
        }
        if (survey.get(position).BondhoChulaYesNo==1){
            bondhoChula  = "<b><font color=#000 >Bondho Chula :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            bondhoChula  = "<b><font color=#000 >Bondho Chula :  </font></b> <font color=#358ED3> No </font>";
        }
        if (survey.get(position).BiomasFuelYesNo==1){
            biomasFuel  = "<b><font color=#000 >Biomas Fuel :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            biomasFuel  = "<b><font color=#000 >Biomas Fuel :  </font></b> <font color=#358ED3> No </font>";
        }
        holder.text_safe_drinking.setText(Html.fromHtml(safe));
        holder.text_sanitary.setText(Html.fromHtml(sanitary));
        holder.text_bondho.setText(Html.fromHtml(bondhoChula));
        holder.text_biomas_fuel.setText(Html.fromHtml(biomasFuel));
    }

    @Override
    public int getItemCount() {
        return survey.size();
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_safe_drinking;
        private TextView text_sanitary;
        private TextView text_bondho;
        private TextView text_biomas_fuel;
        private TextView text_date;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
            text_safe_drinking=itemView.findViewById(R.id.text_safe_drinking);
            text_sanitary=itemView.findViewById(R.id.text_sanitary);
            text_bondho=itemView.findViewById(R.id.text_bondho);
            text_biomas_fuel=itemView.findViewById(R.id.text_biomas_fuel);
            text_date=itemView.findViewById(R.id.text_date);
        }

    }
}
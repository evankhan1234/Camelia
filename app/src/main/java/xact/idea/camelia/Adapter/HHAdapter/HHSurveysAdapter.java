package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import xact.idea.camelia.Database.Model.Survey;
import xact.idea.camelia.Filter.NotSyncFilter;
import xact.idea.camelia.Filter.SurveyFilter;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

public class HHSurveysAdapter extends RecyclerView.Adapter<HHSurveysAdapter.CCDashboardListiewHolder> implements Filterable {


    private Activity mActivity = null;
   public List<Survey> survey;
    UccMemberClickListener uccMemberClickListeners;
    SurveyFilter surveyFilter;
    public HHSurveysAdapter(Activity activity,List<Survey> surveys,UccMemberClickListener uccMemberClickListener) {
        mActivity = activity;
        survey=surveys;
        uccMemberClickListeners=uccMemberClickListener;
    }


    @Override
    public HHSurveysAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_servey_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHSurveysAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHSurveysAdapter.CCDashboardListiewHolder holder, final int position) {
        Paper.init(mActivity);
        String language= SharedPreferenceUtil.getLanguage(mActivity);
        Paper.book().write("language",language);
        Context context= LocaleHelper.setLocale(mActivity,(String)Paper.book().read("language"));
        Resources resources= context.getResources();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy");
        try {
            holder.text_date.setText(formatter.format(survey.get(position).CreatedDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String safe="";
        String sanitary="";
        String bondhoChula="";
        String biomasFuel="";
        if (survey.get(position).SafeDrinkingYesNo==1){
            safe  = "<b><font color=#000 >"+resources.getString(R.string.safe_drinking_1) +" :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            safe  = "<b><font color=#000 >"+resources.getString(R.string.safe_drinking_1) +":  </font></b> <font color=#358ED3> No </font>";
            String store = "<b><font color=#000 >Store Id :  </font></b> <font color=#358ED3></font>";

        }
        if (survey.get(position).SanitaryYesNo==1){
            sanitary  = "<b><font color=#000 >"+resources.getString(R.string.sanitary_latrine_1) +":  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            sanitary  = "<b><font color=#000 >"+resources.getString(R.string.sanitary_latrine_1) +" :  </font></b> <font color=#358ED3> No </font>";
        }
        if (survey.get(position).BondhoChulaYesNo==1){
            bondhoChula  = "<b><font color=#000 >"+resources.getString(R.string.bondho_chula_1) +" :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            bondhoChula  = "<b><font color=#000 >"+resources.getString(R.string.bondho_chula_1) +":  </font></b> <font color=#358ED3> No </font>";
        }
        if (survey.get(position).BiomasFuelYesNo==1){
            biomasFuel  = "<b><font color=#000 >"+resources.getString(R.string.biomas_fuel_1) +" :  </font></b> <font color=#358ED3> Yes </font>";

        }
        else{
            biomasFuel  = "<b><font color=#000 >"+resources.getString(R.string.biomas_fuel_1) +":  </font></b> <font color=#358ED3> No </font>";
        }
        holder.text_safe_drinking.setText(Html.fromHtml(safe));
        holder.text_sanitary.setText(Html.fromHtml(sanitary));
        holder.text_bondho.setText(Html.fromHtml(bondhoChula));
        holder.text_biomas_fuel.setText(Html.fromHtml(biomasFuel));
        holder.text_update.setText(resources.getString(R.string.view));
//        holder.text_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Common.surveyRepository.deleteSurveyById(survey.get(position).id);
//                notifyDataSetChanged();
//            }
//        });
//        holder.text_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Common.surveyRepository.deleteSurveyById(survey.get(position).id);
//                notifyDataSetChanged();
//            }
//        });
        holder.text_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListeners.onItemClick(survey.get(position).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return survey.size();
    }

    @Override
    public Filter getFilter() {
        if (surveyFilter == null) {
            surveyFilter = new SurveyFilter(survey, this);
        }
        return surveyFilter;
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {

        private TextView text_safe_drinking;
        private TextView text_sanitary;
        private TextView text_bondho;
        private TextView text_biomas_fuel;
        private TextView text_date;
        private TextView text_delete;
        private TextView text_update;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
            text_safe_drinking=itemView.findViewById(R.id.text_safe_drinking);
            text_sanitary=itemView.findViewById(R.id.text_sanitary);
            text_bondho=itemView.findViewById(R.id.text_bondho);
            text_biomas_fuel=itemView.findViewById(R.id.text_biomas_fuel);
            text_date=itemView.findViewById(R.id.text_date);
            text_delete=itemView.findViewById(R.id.text_delete);
            text_update=itemView.findViewById(R.id.text_update);
        }

    }
}
package xact.idea.camelia.Filter;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import xact.idea.camelia.Adapter.HHAdapter.HHSurveysAdapter;
import xact.idea.camelia.Database.Model.Survey;

public class SurveyFilter extends Filter {
    private List<Survey> items ;
    private HHSurveysAdapter adapter;

    public SurveyFilter(List<Survey> filterList, HHSurveysAdapter adapter) {
        this.adapter = adapter;
        this.items = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results = new Filter.FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<Survey> filteredPlayers = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                String   test = constraint.toString().replaceAll("\\p{P}","");
                if (items.get(i).UniqueId.toUpperCase().contains(constraint.toString())) {
                    filteredPlayers.add(items.get(i));
                }
            }
            results.count = filteredPlayers.size();
            results.values = filteredPlayers;
        } else {
            results.count = items.size();
            results.values = items;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.survey = (ArrayList<Survey>) results.values;
        adapter.notifyDataSetChanged();
    }

}
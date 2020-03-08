package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Survey;

public interface ISurveyDataSources {
    Flowable<List<Survey>> getSurveyItems();

    Flowable<List<Survey>> getSurveyItemById(String SurveyItemId);

    Survey getSurvey(String SurveyItem);

    void emptySurvey();
    void deleteSurveyById(int id);
    int size();
    int maxValue();
    Survey getSurveyNo(String SurveyItem);

    void insertToSurvey(Survey... Survey);

    void updateSurvey(Survey... Survey);

    void deleteSurvey(Survey... Survey);
}

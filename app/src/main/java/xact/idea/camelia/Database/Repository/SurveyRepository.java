package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.ISurveyDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.ISurveyDataSources;
import xact.idea.camelia.Database.Model.Survey;

public class SurveyRepository implements ISurveyDataSources {
    public ISurveyDataSources ISurveyDataSources;
    public SurveyRepository(ISurveyDataSources ISurveyDataSources){
        this.ISurveyDataSources=ISurveyDataSources;
    }
    private static  SurveyRepository instance;

    public static SurveyRepository getInstance(ISurveyDataSources iCartDataSource){
        if(instance==null)
            instance= new SurveyRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<Survey>> getSurveyItems() {
        return ISurveyDataSources.getSurveyItems();
    }

    @Override
    public Flowable<List<Survey>> getSurveyItemById(int SurveyItemId) {
        return ISurveyDataSources.getSurveyItemById(SurveyItemId);
    }

    @Override
    public Survey getSurvey(String SurveyItem) {
        return ISurveyDataSources.getSurvey(SurveyItem);
    }

    @Override
    public void emptySurvey() {
        ISurveyDataSources.emptySurvey();
    }

    @Override
    public int size() {
        return ISurveyDataSources.size();
    }

    @Override
    public Survey getSurveyNo(String SurveyItem) {
        return ISurveyDataSources.getSurveyNo(SurveyItem);
    }

    @Override
    public void insertToSurvey(Survey... Survey) {
        ISurveyDataSources.insertToSurvey(Survey);
    }

    @Override
    public void updateSurvey(Survey... Survey) {
        ISurveyDataSources.updateSurvey(Survey);
    }

    @Override
    public void deleteSurvey(Survey... Survey) {
        ISurveyDataSources.deleteSurvey(Survey);
    }
}

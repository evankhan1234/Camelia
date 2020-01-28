package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.SurveyDao;
import xact.idea.camelia.Database.DataSourcesInterface.ISurveyDataSources;
import xact.idea.camelia.Database.Model.Survey;

public class SurveyDataSources implements ISurveyDataSources {
    private SurveyDao surveyDao;
    private static SurveyDataSources instance;

    public SurveyDataSources(SurveyDao SurveyDao){
        this.surveyDao=SurveyDao;
    }
    public static SurveyDataSources getInstance(SurveyDao SurveyDao){
        if(instance==null)
            instance = new SurveyDataSources(SurveyDao);
        return instance;

    }
    @Override
    public Flowable<List<Survey>> getSurveyItems() {
        return surveyDao.getSurveyItems();
    }

    @Override
    public Flowable<List<Survey>> getSurveyItemById(String SurveyItemId) {
        return surveyDao.getSurveyItemById(SurveyItemId);
    }

    @Override
    public Survey getSurvey(String SurveyItem) {
        return surveyDao.getSurvey(SurveyItem);
    }

    @Override
    public void emptySurvey() {
        surveyDao.emptySurvey();
    }

    @Override
    public void deleteSurveyById(int id) {
        surveyDao.deleteSurveyById(id);
    }


    @Override
    public int size() {
        return surveyDao.value();
    }

    @Override
    public Survey getSurveyNo(String SurveyItem) {
        return surveyDao.getSurveyNo(SurveyItem);
    }

    @Override
    public void insertToSurvey(Survey... Survey) {
        surveyDao.insertToSurvey(Survey);
    }

    @Override
    public void updateSurvey(Survey... Survey) {
        surveyDao.updateSurvey(Survey);
    }

    @Override
    public void deleteSurvey(Survey... Survey) {
        surveyDao.deleteSurvey(Survey);
    }
}

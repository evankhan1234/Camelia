package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;

import xact.idea.camelia.Database.DataSourcesInterface.IQuestionsDataSources;
import xact.idea.camelia.Database.Model.Questions;

public class QustionsRepository implements IQuestionsDataSources {
    public IQuestionsDataSources IQuestionsDatasources;
    public QustionsRepository(IQuestionsDataSources IQuestionsDatasources){
        this.IQuestionsDatasources=IQuestionsDatasources;
    }
    private static  QustionsRepository instance;

    public static QustionsRepository getInstance(IQuestionsDataSources iCartDataSource){
        if(instance==null)
            instance= new QustionsRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Questions>> getQuestionsItems() {
        return IQuestionsDatasources.getQuestionsItems();
    }

    @Override
    public Flowable<List<Questions>> getQuestionsItemById(int QuestionsItemId) {
        return IQuestionsDatasources.getQuestionsItemById(QuestionsItemId);
    }

    @Override
    public Questions getQuestions(String QuestionsItem) {
        return IQuestionsDatasources.getQuestions(QuestionsItem);
    }

    @Override
    public void emptyQuestions() {
        IQuestionsDatasources.emptyQuestions();
    }

    @Override
    public int size() {
        return IQuestionsDatasources.size();
    }

    @Override
    public Questions getQuestionsNo(String QuestionsItem) {
        return IQuestionsDatasources.getQuestionsNo(QuestionsItem);
    }

    @Override
    public void insertToQuestions(Questions... Questions) {
        IQuestionsDatasources.insertToQuestions(Questions);
    }

    @Override
    public void updateQuestions(Questions... Questions) {
        IQuestionsDatasources.updateQuestions(Questions);
    }

    @Override
    public void deleteQuestions(Questions... Questions) {
        IQuestionsDatasources.deleteQuestions(Questions);
    }
}


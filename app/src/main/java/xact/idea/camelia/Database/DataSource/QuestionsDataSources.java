package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.QuestionsDao;
import xact.idea.camelia.Database.DataSourcesInterface.IQuestionsDataSources;
import xact.idea.camelia.Database.Model.Questions;

public class QuestionsDataSources implements IQuestionsDataSources {

    private QuestionsDao QuestionsDao;
    private static QuestionsDataSources instance;

    public QuestionsDataSources(QuestionsDao QuestionsDao){
        this.QuestionsDao=QuestionsDao;
    }
    public static QuestionsDataSources getInstance(QuestionsDao QuestionsDao){
        if(instance==null)
            instance = new QuestionsDataSources(QuestionsDao);
        return instance;

    }

    @Override
    public Flowable<List<Questions>> getQuestionsItems() {
        return QuestionsDao.getQuestionsItems();
    }

    @Override
    public Flowable<List<Questions>> getQuestionsItemById(int QuestionsItemId) {
        return QuestionsDao.getQuestionsItemById(QuestionsItemId);
    }

    @Override
    public Questions getQuestions(String QuestionsItem) {
        return QuestionsDao.getQuestions(QuestionsItem);
    }

    @Override
    public void emptyQuestions() {
        QuestionsDao.emptyQuestions();
    }

    @Override
    public int size() {
        return QuestionsDao.value();
    }

    @Override
    public Questions getQuestionsNo(String QuestionsItem) {
        return QuestionsDao.getQuestionsNo(QuestionsItem);
    }

    @Override
    public void insertToQuestions(Questions... Questions) {
        QuestionsDao.insertToQuestions(Questions);
    }

    @Override
    public void updateQuestions(Questions... Questions) {
        QuestionsDao.updateQuestions(Questions);
    }

    @Override
    public void deleteQuestions(Questions... Questions) {
        QuestionsDao.deleteQuestions(Questions);
    }
}
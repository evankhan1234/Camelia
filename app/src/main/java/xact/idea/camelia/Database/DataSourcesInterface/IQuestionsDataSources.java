package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Questions;

public interface IQuestionsDataSources {
    Flowable<List<Questions>> getQuestionsItems();

    Flowable<List<Questions>> getQuestionsItemById(int QuestionsItemId);

    Questions getQuestions(String QuestionsItem);

    void emptyQuestions();

    int size();

    Questions getQuestionsNo(String QuestionsItem);

    void insertToQuestions(Questions... Questions);

    void updateQuestions(Questions... Questions);

    void deleteQuestions(Questions... Questions);
}

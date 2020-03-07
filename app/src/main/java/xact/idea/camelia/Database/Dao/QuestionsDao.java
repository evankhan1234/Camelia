package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Questions;

@Dao
public interface QuestionsDao {
    @Query("SELECT * FROM Questions")
    Flowable<List<Questions>> getQuestionsItems();
    @Query("SELECT * FROM Questions WHERE type=:QuestionsItemId AND member_id=:MemberId")
    Flowable<List<Questions>> getQuestionsItemById(String QuestionsItemId,String MemberId);
    @Query("SELECT * FROM Questions WHERE question=:QuestionsItem AND member_id=:MemberId")
    Questions getQuestions(String QuestionsItem,String MemberId);
    @Query("SELECT * FROM Questions WHERE type=:QuestionsItem")
    Questions getQuestionsNo(String QuestionsItem);
    @Query("Select Count(id)  FROM Questions")
    int value();
    @Query("DELETE  FROM Questions")
    void emptyQuestions();
    @Insert
    void insertToQuestions(Questions...Questions);
    @Update
    void updateQuestions(Questions...Questions);
    @Delete
    void deleteQuestions(Questions...Questions);
}

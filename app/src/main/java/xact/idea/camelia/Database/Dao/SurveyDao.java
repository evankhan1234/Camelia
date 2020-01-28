package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Survey;

@Dao
public interface SurveyDao {
    @Query("SELECT * FROM Survey")
    Flowable<List<Survey>> getSurveyItems();
    @Query("SELECT * FROM Survey WHERE UniqueId=:SurveyItemId")
    Flowable<List<Survey>> getSurveyItemById(String SurveyItemId);
    @Query("SELECT * FROM Survey WHERE id=:SurveyItem")
    Survey getSurvey(String SurveyItem);
    @Query("SELECT * FROM Survey WHERE id=:SurveyItem")
    Survey getSurveyNo(String SurveyItem);
    @Query("Select Count(id)  FROM Survey")
    int value();
    @Query("DELETE  FROM Survey")
    void emptySurvey();
    @Query("DELETE  FROM Survey WHERE id=:id")
    void deleteSurveyById(int id);
    @Insert
    void insertToSurvey(Survey...Survey);
    @Update
    void updateSurvey(Survey...Survey);
    @Delete
    void deleteSurvey(Survey...Survey);
}

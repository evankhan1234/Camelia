package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.StudyClass;

@Dao
public interface StudyClassDao {
    @Query("SELECT * FROM StudyClass")
    Flowable<List<StudyClass>> getStudyClassItems();
    @Query("SELECT * FROM StudyClass WHERE id=:StudyClassId")
    Flowable<List<StudyClass>> getStudyClassItemById(int StudyClassId);
    @Query("SELECT * FROM StudyClass WHERE StudyClassId=:StudyClassItem")
    StudyClass getStudyClass(String StudyClassItem);
    @Query("SELECT * FROM StudyClass WHERE StudyClassId=:StudyClassItem")
    StudyClass getStudyClassNo(String StudyClassItem);
    @Query("Select Count(id)  FROM StudyClass")
    int value();
    @Query("UPDATE  StudyClass SET ln=:lang")
    void updateLanguage(String lang);
    @Query("DELETE  FROM StudyClass")
    void emptyStudyClass();
    @Insert
    void insertStudyClass(StudyClass...StudyClass);
    @Update
    void updateStudyClass(StudyClass...StudyClass);
    @Delete
    void deleteStudyClass(StudyClass...StudyClass);
}

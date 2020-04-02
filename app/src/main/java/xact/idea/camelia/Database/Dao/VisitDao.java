package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Visit;

@Dao
public interface VisitDao {
    @Query("SELECT * FROM Visit")
    Flowable<List<Visit>> getVisitItems();
    @Query("SELECT * FROM Visit WHERE RefId=:VisitItemId")
    Flowable<List<Visit>> getVisitItemById(int VisitItemId);
    @Query("SELECT * FROM Visit WHERE RefId=:VisitItem")
    Visit getVisit(String VisitItem);
    @Query("SELECT * FROM Visit WHERE RefId=:VisitItem")
    Visit getVisitNo(String VisitItem);
    @Query("Select Count(id)  FROM Visit")
    int value();
    @Query("DELETE  FROM Visit")
    void emptyVisit();
    @Insert
    void insertToVisit(Visit...Visit);
    @Update
    void updateVisit(Visit...Visit);
    @Delete
    void deleteVisit(Visit...Visit);
}

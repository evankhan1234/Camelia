package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MaritialStatus;

@Dao
public interface MaritialStatusDao {
    @Query("SELECT * FROM MaritialStatus")
    Flowable<List<MaritialStatus>> getMaritialStatusItems();
    @Query("SELECT * FROM MaritialStatus WHERE id=:MaritialStatusItemId")
    Flowable<List<MaritialStatus>> getMaritialStatusItemById(int MaritialStatusItemId);
    @Query("SELECT * FROM MaritialStatus WHERE MaritialId=:MaritialStatusItem")
    MaritialStatus getMaritialStatus(String MaritialStatusItem);
    @Query("SELECT * FROM MaritialStatus WHERE MaritialId=:MaritialStatusItem")
    MaritialStatus getMaritialStatusNo(String MaritialStatusItem);
    @Query("Select Count(id)  FROM MaritialStatus")
    int value();
    @Query("DELETE  FROM MaritialStatus")
    void emptyMaritialStatus();
    @Insert
    void insertToMaritialStatus(MaritialStatus...MaritialStatus);
    @Update
    void updateMaritialStatus(MaritialStatus...MaritialStatus);
    @Delete
    void deleteMaritialStatus(MaritialStatus...MaritialStatus);
}

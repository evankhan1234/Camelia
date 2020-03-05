package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.ReferHistory;

@Dao
public interface ReferralHistoryDao {
    @Query("SELECT * FROM ReferHistory")
    Flowable<List<ReferHistory>> getReferHistoryItems();
    @Query("SELECT * FROM ReferHistory WHERE id=:ReferHistoryItemId")
    Flowable<List<ReferHistory>> getReferHistoryItemById(int ReferHistoryItemId);
    @Query("SELECT * FROM ReferHistory WHERE id=:ReferHistoryItem")
    ReferHistory getReferHistory(String ReferHistoryItem);
    @Query("SELECT * FROM ReferHistory WHERE id=:ReferHistoryItem")
    ReferHistory getReferHistoryNo(String ReferHistoryItem);
    @Query("Select Count(id)  FROM ReferHistory")
    int value();
    @Query("DELETE  FROM ReferHistory")
    void emptyReferHistory();
    @Insert
    void insertToReferHistory(ReferHistory...ReferHistory);
    @Update
    void updateReferHistory(ReferHistory...ReferHistory);
    @Delete
    void deleteReferHistory(ReferHistory...ReferHistory);
}
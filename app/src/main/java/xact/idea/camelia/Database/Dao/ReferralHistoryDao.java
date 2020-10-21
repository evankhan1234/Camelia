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
    @Query("SELECT * FROM ReferHistory WHERE ids=:ReferHistoryItemId")
    Flowable<List<ReferHistory>> getReferHistoryItemById(int ReferHistoryItemId);
    @Query("SELECT * FROM ReferHistory WHERE ids=:ReferHistoryItem")
    ReferHistory getReferHistory(String ReferHistoryItem);
    @Query("SELECT * FROM ReferHistory WHERE MemberUniqueCode=:ReferHistoryItem")
    ReferHistory getReferHistoryNo(String ReferHistoryItem);
    @Query("Select Count(ids)  FROM ReferHistory")
    int value();
    @Query("DELETE  FROM ReferHistory")
    void emptyReferHistory();
    @Query("SELECT * FROM  ReferHistory as refer  WHERE  refer.Reason='1' and refer.MemberUniqueCode=:member")
    ReferHistory getReferHistoryFrom(String member);
    @Query("UPDATE  ReferHistory SET Reason='2' WHERE MemberUniqueCode=:memberId")
    void updateReferHistoryFrom(String memberId);
    @Insert
    void insertToReferHistory(ReferHistory...ReferHistory);
    @Update
    void updateReferHistory(ReferHistory...ReferHistory);
    @Delete
    void deleteReferHistory(ReferHistory...ReferHistory);

    @Query("Select * from ReferHistory where MemberUniqueCode=:ReferHistoryItem ORDER BY ids DESC LIMIT 1")
    ReferHistory getMemberReferHistoryNo(String ReferHistoryItem);
}

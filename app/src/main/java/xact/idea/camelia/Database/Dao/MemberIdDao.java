package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberId;

@Dao
public interface MemberIdDao {
    @Query("SELECT * FROM MemberId")
    Flowable<List<MemberId>> getMemberIdItems();
    @Query("SELECT * FROM MemberId WHERE id=:MemberIdItemId")
    Flowable<List<MemberId>> getMemberIdItemById(int MemberIdItemId);
    @Query("SELECT * FROM MemberId WHERE Value=:MemberIdItem")
    MemberId getMemberId(String MemberIdItem);
    @Query("SELECT * FROM MemberId WHERE id=:MemberIdItem")
    MemberId getMemberIdNo(String MemberIdItem);
    @Query("Select Count(id)  FROM MemberId")
    int value();
    @Query("Select Max(id)  FROM MemberId")
    int maxValue();
    @Query("DELETE  FROM MemberId where Value=:value")
    void emptyMemberId(String value);
    @Query("DELETE  FROM MemberId ")
    void emptyMember();
    @Insert
    void insertToMemberId(MemberId...MemberId);
    @Update
    void updateMemberId(MemberId...MemberId);
    @Delete
    void deleteMemberId(MemberId...MemberId);
}

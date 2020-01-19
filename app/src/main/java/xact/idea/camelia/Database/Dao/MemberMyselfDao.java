package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberMyself;

@Dao
public interface MemberMyselfDao {
    @Query("SELECT * FROM MemberMyself")
    Flowable<List<MemberMyself>> getMemberMyselfItems();
    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfId")
    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfId);
    @Query("SELECT * FROM MemberMyself WHERE MobileNumber=:MemberMyselfItem")
    MemberMyself getMemberMyself(String MemberMyselfItem);
    @Query("SELECT * FROM MemberMyself WHERE id=:MemberMyselfItem")
    MemberMyself getMemberMyselfNo(int MemberMyselfItem);
    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfItem AND HouseHeadId=1")
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    @Query("Select Count(id)  FROM MemberMyself")
    int value();
    @Query("Select Max(id)  FROM MemberMyself")
    int maxValue();
    @Query("DELETE  FROM MemberMyself")
    void emptyMemberMyself();
    @Insert
    void insertToMemberMyself(MemberMyself...MemberMyself);
    @Update
    void updateMemberMyself(MemberMyself...MemberMyself);
    @Delete
    void deleteMemberMyself(MemberMyself...MemberMyself);
}

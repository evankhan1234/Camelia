package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberHabit;

@Dao
public interface MemberHabitDao {
    @Query("SELECT * FROM MemberHabit")
    Flowable<List<MemberHabit>> getMemberHabitItems();
    @Query("SELECT * FROM MemberHabit WHERE id=:MemberHabitId")
    Flowable<List<MemberHabit>> getMemberHabitItemById(int MemberHabitId);
    @Query("SELECT * FROM MemberHabit WHERE id=:MemberHabitItem")
    MemberHabit getMemberHabit(String MemberHabitItem);
    @Query("SELECT * FROM MemberHabit WHERE id=:MemberHabitItem")
    MemberHabit getMemberHabitNo(String MemberHabitItem);
    @Query("Select Count(id)  FROM MemberHabit")
    int value();
    @Query("DELETE  FROM MemberHabit")
    void emptyMemberHabit();
    @Insert
    void insertToMemberHabit(MemberHabit...MemberHabit);
    @Update
    void updateMemberHabit(MemberHabit...MemberHabit);
    @Delete
    void deleteMemberHabit(MemberHabit...MemberHabit);
}

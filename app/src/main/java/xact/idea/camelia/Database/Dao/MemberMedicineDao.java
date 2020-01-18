package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberMedicine;

@Dao
public interface MemberMedicineDao {
    @Query("SELECT * FROM MemberMedicine")
    Flowable<List<MemberMedicine>> getMemberMedicineItems();
    @Query("SELECT * FROM MemberMedicine WHERE id=:MemberMedicineId")
    Flowable<List<MemberMedicine>> getMemberMedicineItemById(int MemberMedicineId);
    @Query("SELECT * FROM MemberMedicine WHERE id=:MemberMedicineItem")
    MemberMedicine getMemberMedicine(String MemberMedicineItem);
    @Query("SELECT * FROM MemberMedicine WHERE MemberMyselfPhoneNumber=:MemberMedicineItem")
    MemberMedicine getMemberMedicineNo(String MemberMedicineItem);
    @Query("Select Count(id)  FROM MemberMedicine")
    int value();
    @Query("DELETE  FROM MemberMedicine")
    void emptyMemberMedicine();
    @Insert
    void insertToMemberMedicine(MemberMedicine...MemberMedicine);
    @Update
    void updateMemberMedicine(MemberMedicine...MemberMedicine);
    @Delete
    void deleteMemberMedicine(MemberMedicine...MemberMedicine);
}

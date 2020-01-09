package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.BloodGroup;

@Dao
public interface BloodGroupDao {
    @Query("SELECT * FROM BloodGroup")
    Flowable<List<BloodGroup>> getBloodGroupItems();

    @Query("SELECT * FROM BloodGroup WHERE id=:BloodGroupItemId")
    Flowable<List<BloodGroup>> getBloodGroupItemById(int BloodGroupItemId);
    @Query("SELECT * FROM BloodGroup WHERE BloodId=:BloodGroupItem")
    BloodGroup getBloodGroup(String BloodGroupItem);
    @Query("SELECT * FROM BloodGroup WHERE BloodId=:BloodGroupItem")
    BloodGroup getBloodGroupNo(String BloodGroupItem);
    @Query("Select Count(id)  FROM BloodGroup")
    int value();
    @Query("DELETE  FROM BloodGroup")
    void emptyBloodGroup();
    @Insert
    void insertToBloodGroup(BloodGroup...BloodGroup);
    @Update
    void updateBloodGroup(BloodGroup...BloodGroup);
    @Delete
    void deleteBloodGroup(BloodGroup...BloodGroup);
}

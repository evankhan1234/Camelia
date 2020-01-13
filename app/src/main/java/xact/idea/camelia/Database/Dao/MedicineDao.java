package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Medicine;

@Dao
public interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    Flowable<List<Medicine>> getMedicineItems();
    @Query("SELECT * FROM Medicine WHERE id=:MedicineId")
    Flowable<List<Medicine>> getMedicineItemById(int MedicineId);
    @Query("SELECT * FROM Medicine WHERE id=:MedicineItem")
    Medicine getMedicine(String MedicineItem);
    @Query("SELECT * FROM Medicine WHERE id=:MedicineItem")
    Medicine getMedicineNo(String MedicineItem);
    @Query("Select Count(id)  FROM Medicine")
    int value();
    @Query("DELETE  FROM Medicine")
    void emptyMedicine();
    @Insert
    void insertToMedicine(Medicine...Medicine);
    @Update
    void updateMedicine(Medicine...Medicine);
    @Delete
    void deleteMedicine(Medicine...Medicine);
}

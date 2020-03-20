package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Upazila;

@Dao
public interface UpazilaDao {
    @Query("SELECT * FROM Upazila")
    Flowable<List<Upazila>> getUpazilaItems();
    @Query("SELECT * FROM Upazila WHERE district_id=:UpazilaItemId")
    Flowable<List<Upazila>> getUpazilaItemById(int UpazilaItemId);
    @Query("SELECT * FROM Upazila WHERE UpazilaId=:UpazilaItem")
    Upazila getUpazila(String UpazilaItem);
    @Query("SELECT * FROM Upazila WHERE UpazilaId=:UpazilaItem")
    Upazila getUpazilaNo(String UpazilaItem);
    @Query("Select Count(id)  FROM Upazila")
    int value();
    @Query("UPDATE  Upazila SET ln=:lang")
    void updateLanguage(String lang);
    @Query("DELETE  FROM Upazila")
    void emptyUpazila();
    @Insert
    void insertToUpazila(Upazila...Upazila);
    @Update
    void updateUpazila(Upazila...Upazila);
    @Delete
    void deleteUpazila(Upazila...Upazila);
}

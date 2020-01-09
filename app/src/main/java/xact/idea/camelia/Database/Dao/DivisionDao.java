package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Division;

@Dao
public interface DivisionDao {
    @Query("SELECT * FROM Division")
    Flowable<List<Division>> getDivisionItems();

    @Query("SELECT * FROM Division WHERE id=:BookItemId")
    Flowable<List<Division>> getDivisionItemById(int BookItemId);
    @Query("SELECT * FROM Division WHERE DivisionId=:BookItem")
    Division getDivision(String BookItem);
    @Query("SELECT * FROM Division WHERE DivisionId=:BookItem")
    Division getDivisionNo(String BookItem);
    @Query("Select Count(id)  FROM Division")
    int value();
    @Query("DELETE  FROM Division")
    void emptyDivision();
    @Insert
    void insertToDivision(Division...Division);
    @Update
    void updateDivision(Division...Division);
    @Delete
    void deleteDivision(Division...Division);

}

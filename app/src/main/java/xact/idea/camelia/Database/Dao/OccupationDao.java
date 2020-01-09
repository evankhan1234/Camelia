package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Occupation;

@Dao
public interface OccupationDao {
    @Query("SELECT * FROM Occupation")
    Flowable<List<Occupation>> getOccupationItems();
    @Query("SELECT * FROM Occupation WHERE id=:OccupationItemId")
    Flowable<List<Occupation>> getOccupationItemById(int OccupationItemId);
    @Query("SELECT * FROM Occupation WHERE OccupationId=:OccupationItem")
    Occupation getOccupation(String OccupationItem);
    @Query("SELECT * FROM Occupation WHERE OccupationId=:OccupationItem")
    Occupation getOccupationNo(String OccupationItem);
    @Query("Select Count(id)  FROM Occupation")
    int value();
    @Query("DELETE  FROM Occupation")
    void emptyOccupation();
    @Insert
    void insertToOccupation(Occupation...Occupation);
    @Update
    void updateOccupation(Occupation...Occupation);
    @Delete
    void deleteOccupation(Occupation...Occupation);
}

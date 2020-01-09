package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Ward;

@Dao
public interface WardDao {
    @Query("SELECT * FROM Ward")
    Flowable<List<Ward>> getWardItems();

    @Query("SELECT * FROM Ward WHERE id=:WardItemId")
    Flowable<List<Ward>> getWardItemById(int WardItemId);
    @Query("SELECT * FROM Ward WHERE ward_name_bn=:WardItem")
    Ward getWard(String WardItem);
    @Query("SELECT * FROM Ward WHERE WardId=:BookItem")
    Ward getWardNo(String BookItem);
    @Query("Select Count(id)  FROM Ward")
    int value();
    @Query("DELETE  FROM Ward")
    void emptyWard();
    @Insert
    void insertToWard(Ward...Ward);
    @Update
    void updateWard(Ward...Ward);
    @Delete
    void deleteWard(Ward...Ward);
}

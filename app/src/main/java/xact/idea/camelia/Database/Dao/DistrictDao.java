package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.District;

@Dao
public interface DistrictDao {
    @Query("SELECT * FROM District")
    Flowable<List<District>> getDistrictItems();

    @Query("SELECT * FROM District WHERE id=:DistrictItemId")
    Flowable<List<District>> getDistrictItemById(int DistrictItemId);
    @Query("SELECT * FROM District WHERE DivisionId=:DistrictItem")
    District getDistrict(String DistrictItem);
    @Query("SELECT * FROM District WHERE DivisionId=:DistrictItem")
    District getDistrictNo(String DistrictItem);
    @Query("Select Count(id)  FROM District")
    int value();
    @Query("DELETE  FROM District")
    void emptyDistrict();
    @Insert
    void insertToDistrict(District...District);
    @Update
    void updateDistrict(District...District);
    @Delete
    void deleteDistrict(District...District);
}

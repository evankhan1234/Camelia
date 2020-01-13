package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.HouseHold;

@Dao
public interface HouseholdDao {
    @Query("SELECT * FROM HouseHold")
    Flowable<List<HouseHold>> getHouseholdItems();
    @Query("SELECT * FROM HouseHold WHERE id=:HouseholdId")
    Flowable<List<HouseHold>> getHouseholdItemById(int HouseholdId);
    @Query("SELECT * FROM HouseHold WHERE id=:HouseholdItem")
    HouseHold getHousehold(String HouseholdItem);
    @Query("SELECT * FROM HouseHold WHERE id=:HouseholdItem")
    HouseHold getHouseholdNo(String HouseholdItem);
    @Query("Select Count(id)  FROM HouseHold")
    int value();
    @Query("DELETE  FROM HouseHold")
    void emptyHousehold();
    @Insert
    void insertToHousehold(HouseHold...HouseHold);
    @Update
    void updateHousehold(HouseHold...HouseHold);
    @Delete
    void deleteHousehold(HouseHold...HouseHold);
}

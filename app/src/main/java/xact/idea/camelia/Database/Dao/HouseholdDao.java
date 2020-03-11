package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.HouseHold;

@Dao
public interface HouseholdDao {
    @Query("SELECT * FROM HouseHold")
    Flowable<List<HouseHold>> getHouseholdItems();
    @Query("SELECT * FROM HouseHold WHERE id=:HouseholdId")
    Flowable<List<HouseHold>> getHouseholdItemById(int HouseholdId);
    @Query("SELECT * FROM HouseHold WHERE UniqueId=:HouseholdItem")
    HouseHold getHousehold(String HouseholdItem);
    @Query("SELECT * FROM HouseHold WHERE id=:HouseholdItem")
    HouseHold getHouseholdNo(String HouseholdItem);
    @Query("SELECT * FROM HouseHold WHERE DivisionId=:DivisionId AND DistrictId=:DistrictId AND UpazilaId=:Upazila AND UnionId=:Union")
    Flowable<List<HouseHold>> getHouseHoldItemByFour(String DivisionId, String DistrictId, String Upazila, String Union);
    @Query("SELECT * FROM HouseHold WHERE DivisionId=:DivisionId AND DistrictId=:DistrictId AND UpazilaId=:Upazila")
    Flowable<List<HouseHold>> getHouseHoldItemByThree(String DivisionId,String DistrictId,String Upazila);
    @Query("SELECT * FROM HouseHold WHERE DivisionId=:DivisionId AND DistrictId=:DistrictId ")
    Flowable<List<HouseHold>> getHouseHoldItemByTwo(String DivisionId,String DistrictId);
    @Query("SELECT * FROM HouseHold WHERE DivisionId=:DivisionId ")
    Flowable<List<HouseHold>> getHouseHoldItemByOne(String DivisionId);
    @Query("SELECT HS.UniqueId,HS.VillageName,MM.MobileNumber,MM.FullName FROM HouseHold as HS inner join MemberMyself as MM on HS.UniqueId=MM.UniqueId WHERE HouseHeadId=1")
    Flowable<List<HouseHead>> getHouseHead();
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

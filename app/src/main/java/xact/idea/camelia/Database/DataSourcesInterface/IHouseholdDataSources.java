package xact.idea.camelia.Database.DataSourcesInterface;

import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Model.HouseHold;

public interface IHouseholdDataSources {
    Flowable<List<HouseHold>> getHouseHoldItems();

    Flowable<List<HouseHold>> getHouseHoldItemById(int HouseHoldItemId);

    HouseHold getHouseHold(String HouseHoldItem);

    void emptyHouseHold();
    Flowable<List<HouseHold>> getHouseHoldItemByFour(String DivisionId, String DistrictId, String Upazila, String Union);
    Flowable<List<HouseHold>> getHouseHoldItemByThree(String DivisionId,String DistrictId,String Upazila);
    Flowable<List<HouseHold>> getHouseHoldItemByTwo(String DivisionId,String DistrictId);
    Flowable<List<HouseHold>> getHouseHoldItemByOne(String DivisionId);
    int size();
    Flowable<List<HouseHead>> getHouseHead();
    HouseHold getHouseHoldNo(String HouseHoldItem);

    void insertToHouseHold(HouseHold... HouseHold);

    void updateHouseHold(HouseHold... HouseHold);

    void deleteHouseHold(HouseHold... HouseHold);
}

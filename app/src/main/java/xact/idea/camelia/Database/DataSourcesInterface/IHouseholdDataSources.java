package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Model.HouseHold;

public interface IHouseholdDataSources {
    Flowable<List<HouseHold>> getHouseHoldItems();

    Flowable<List<HouseHold>> getHouseHoldItemById(int HouseHoldItemId);

    HouseHold getHouseHold(String HouseHoldItem);

    void emptyHouseHold();

    int size();
    Flowable<List<HouseHead>> getHouseHead();
    HouseHold getHouseHoldNo(String HouseHoldItem);

    void insertToHouseHold(HouseHold... HouseHold);

    void updateHouseHold(HouseHold... HouseHold);

    void deleteHouseHold(HouseHold... HouseHold);
}

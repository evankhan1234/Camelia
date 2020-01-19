package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.DataSourcesInterface.IHouseholdDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.IHouseholdDataSources;
import xact.idea.camelia.Database.Model.HouseHold;

public class HouseholdRepository implements IHouseholdDataSources {
    public IHouseholdDataSources IHouseholdDataSources;
    public HouseholdRepository(IHouseholdDataSources IHouseholdDataSources){
        this.IHouseholdDataSources=IHouseholdDataSources;
    }
    private static  HouseholdRepository instance;

    public static HouseholdRepository getInstance(IHouseholdDataSources iCartDataSource){
        if(instance==null)
            instance= new HouseholdRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<HouseHold>> getHouseHoldItems() {
        return IHouseholdDataSources.getHouseHoldItems();
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemById(int HouseHoldItemId) {
        return IHouseholdDataSources.getHouseHoldItemById(HouseHoldItemId);
    }

    @Override
    public HouseHold getHouseHold(String HouseHoldItem) {
        return IHouseholdDataSources.getHouseHold(HouseHoldItem);
    }

    @Override
    public void emptyHouseHold() {
        IHouseholdDataSources.emptyHouseHold();
    }

    @Override
    public int size() {
        return IHouseholdDataSources.size();
    }

    @Override
    public Flowable<List<HouseHead>> getHouseHead() {
        return IHouseholdDataSources.getHouseHead();
    }

    @Override
    public HouseHold getHouseHoldNo(String HouseHoldItem) {
        return IHouseholdDataSources.getHouseHoldNo(HouseHoldItem);
    }

    @Override
    public void insertToHouseHold(HouseHold... HouseHold) {
        IHouseholdDataSources.insertToHouseHold(HouseHold);
    }

    @Override
    public void updateHouseHold(HouseHold... HouseHold) {
        IHouseholdDataSources.updateHouseHold(HouseHold);
    }

    @Override
    public void deleteHouseHold(HouseHold... HouseHold) {
        IHouseholdDataSources.deleteHouseHold(HouseHold);
    }
}

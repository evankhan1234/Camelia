package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.HouseHead;
import xact.idea.camelia.Database.Dao.HouseholdDao;
import xact.idea.camelia.Database.DataSourcesInterface.IHouseholdDataSources;
import xact.idea.camelia.Database.Model.HouseHold;

public class HouseholdDataSources implements IHouseholdDataSources {
    private HouseholdDao HouseholdDao;
    private static HouseholdDataSources instance;

    public HouseholdDataSources(HouseholdDao HouseholdDao){
        this.HouseholdDao=HouseholdDao;
    }
    public static HouseholdDataSources getInstance(HouseholdDao HouseholdDao){
        if(instance==null)
            instance = new HouseholdDataSources(HouseholdDao);
        return instance;

    }
    @Override
    public Flowable<List<HouseHold>> getHouseHoldItems() {
        return HouseholdDao.getHouseholdItems();
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemById(int HouseHoldItemId) {
        return HouseholdDao.getHouseholdItemById(HouseHoldItemId);
    }

    @Override
    public HouseHold getHouseHold(String HouseHoldItem) {
        return HouseholdDao.getHousehold(HouseHoldItem);
    }

    @Override
    public void emptyHouseHold() {
        HouseholdDao.emptyHousehold();
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemByFour(String DivisionId, String DistrictId, String Upazila, String Union) {
        return HouseholdDao.getHouseHoldItemByFour(DivisionId, DistrictId, Upazila, Union);
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemByThree(String DivisionId, String DistrictId, String Upazila) {
        return HouseholdDao.getHouseHoldItemByThree(DivisionId, DistrictId, Upazila);
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemByTwo(String DivisionId, String DistrictId) {
        return HouseholdDao.getHouseHoldItemByTwo(DivisionId, DistrictId);
    }

    @Override
    public Flowable<List<HouseHold>> getHouseHoldItemByOne(String DivisionId) {
        return HouseholdDao.getHouseHoldItemByOne(DivisionId);
    }

    @Override
    public int size() {
        return HouseholdDao.value();
    }

    @Override
    public Flowable<List<HouseHead>> getHouseHead() {
        return HouseholdDao.getHouseHead();
    }

    @Override
    public HouseHold getHouseHoldNo(String HouseHoldItem) {
        return HouseholdDao.getHouseholdNo(HouseHoldItem);
    }

    @Override
    public void insertToHouseHold(HouseHold... HouseHold) {
        HouseholdDao.insertToHousehold(HouseHold);
    }

    @Override
    public void updateHouseHold(HouseHold... HouseHold) {
        HouseholdDao.updateHousehold(HouseHold);
    }

    @Override
    public void deleteHouseHold(HouseHold... HouseHold) {
        HouseholdDao.deleteHousehold(HouseHold);
    }
}

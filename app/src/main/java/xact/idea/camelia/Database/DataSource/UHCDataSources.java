package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.UHCDao;
import xact.idea.camelia.Database.DataSourcesInterface.IUHCDataSources;
import xact.idea.camelia.Database.Model.UHC;

public class UHCDataSources implements IUHCDataSources {

    private xact.idea.camelia.Database.Dao.UHCDao UHCDao;
    private static UHCDataSources instance;

    public UHCDataSources(UHCDao UHCDao){
        this.UHCDao=UHCDao;
    }
    public static UHCDataSources getInstance(UHCDao UHCDao){
        if(instance==null)
            instance = new UHCDataSources(UHCDao);
        return instance;

    }

    @Override
    public Flowable<List<UHC>> getUHCItems() {
        return UHCDao.getUHCItems();
    }

    @Override
    public Flowable<List<UHC>> getUHCItemById(int UHCItemId) {
        return UHCDao.getUHCItemById(UHCItemId);
    }

    @Override
    public UHC getUHC(String UHCItem) {
        return UHCDao.getUHC(UHCItem);
    }

    @Override
    public void emptyUHC() {
        UHCDao.emptyUHC();
    }

    @Override
    public int size() {
        return UHCDao.value();
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByFour(String DivisionId, String DistrictId, String Upazila, String Union) {
        return UHCDao.getUHCItemByFour(DivisionId, DistrictId, Upazila, Union);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByThree(String DivisionId, String DistrictId, String Upazila) {
        return UHCDao.getUHCItemByThree(DivisionId, DistrictId, Upazila);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByTwo(String DivisionId, String DistrictId) {
        return UHCDao.getUHCItemByTwo(DivisionId, DistrictId);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByOne(String DivisionId) {
        return UHCDao.getUHCItemByOne(DivisionId);
    }

    @Override
    public UHC getUHCNo(String UHCItem) {
        return UHCDao.getUHCNo(UHCItem);
    }

    @Override
    public void insertToUHC(UHC... UHC) {
        UHCDao.insertToUHC(UHC);
    }

    @Override
    public void updateUHC(UHC... UHC) {
        UHCDao.updateUHC(UHC);
    }

    @Override
    public void deleteUHC(UHC... UHC) {
        UHCDao.deleteUHC(UHC);
    }
}
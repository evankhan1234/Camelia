package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IUHCDataSources;
import xact.idea.camelia.Database.Model.UHC;

public class UHCRepository implements IUHCDataSources {
    public IUHCDataSources IUHCDataSources;
    public UHCRepository(IUHCDataSources IUHCDataSources){
        this.IUHCDataSources=IUHCDataSources;
    }
    private static  UHCRepository instance;

    public static UHCRepository getInstance(IUHCDataSources iCartDataSource){
        if(instance==null)
            instance= new UHCRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<UHC>> getUHCItems() {
        return IUHCDataSources.getUHCItems();
    }

    @Override
    public Flowable<List<UHC>> getUHCItemById(int UHCItemId) {
        return IUHCDataSources.getUHCItemById(UHCItemId);
    }

    @Override
    public UHC getUHC(String UHCItem) {
        return IUHCDataSources.getUHC(UHCItem);
    }

    @Override
    public void emptyUHC() {
        IUHCDataSources.emptyUHC();
    }

    @Override
    public int size() {
        return IUHCDataSources.size();
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByFour(String DivisionId, String DistrictId, String Upazila, String Union) {
        return IUHCDataSources.getUHCItemByFour(DivisionId, DistrictId, Upazila, Union);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByThree(String DivisionId, String DistrictId, String Upazila) {
        return IUHCDataSources.getUHCItemByThree(DivisionId, DistrictId, Upazila);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByTwo(String DivisionId, String DistrictId) {
        return IUHCDataSources.getUHCItemByTwo(DivisionId, DistrictId);
    }

    @Override
    public Flowable<List<UHC>> getUHCItemByOne(String DivisionId) {
        return IUHCDataSources.getUHCItemByOne(DivisionId);
    }

    @Override
    public UHC getUHCNo(String UHCItem) {
        return IUHCDataSources.getUHCNo(UHCItem);
    }

    @Override
    public void insertToUHC(UHC... UHC) {
        IUHCDataSources.insertToUHC(UHC);
    }

    @Override
    public void updateUHC(UHC... UHC) {
        IUHCDataSources.updateUHC(UHC);
    }

    @Override
    public void deleteUHC(UHC... UHC) {
        IUHCDataSources.deleteUHC(UHC);
    }
}


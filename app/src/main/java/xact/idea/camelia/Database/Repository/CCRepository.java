package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.ICCDatasources;
import xact.idea.camelia.Database.DataSourcesInterface.ICCDatasources;
import xact.idea.camelia.Database.Model.CCModel;

public class CCRepository implements ICCDatasources {
    public ICCDatasources ICCDatasources;
    public CCRepository(ICCDatasources ICCDatasources){
        this.ICCDatasources=ICCDatasources;
    }
    private static  CCRepository instance;

    public static CCRepository getInstance(ICCDatasources iCartDataSource){
        if(instance==null)
            instance= new CCRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<CCModel>> getCCModelItems() {
        return ICCDatasources.getCCModelItems();
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId) {
        return ICCDatasources.getCCModelItemById(CCModelItemId);
    }

    @Override
    public CCModel getCCModel(String CCModelItem) {
        return ICCDatasources.getCCModel(CCModelItem);
    }

    @Override
    public void emptyCCModel() {
        ICCDatasources.emptyCCModel();
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemByFour(String DivisionId, String DistrictId, String Upazila, String Union) {
        return ICCDatasources.getCCModelItemByFour(DivisionId, DistrictId, Upazila, Union);
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemByThree(String DivisionId, String DistrictId, String Upazila) {
        return ICCDatasources.getCCModelItemByThree(DivisionId, DistrictId, Upazila);
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemByTwo(String DivisionId, String DistrictId) {
        return ICCDatasources.getCCModelItemByTwo(DivisionId, DistrictId);
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemByOne(String DivisionId) {
        return ICCDatasources.getCCModelItemByOne(DivisionId);
    }

    @Override
    public int size() {
        return ICCDatasources.size();
    }

    @Override
    public CCModel getCCModelNo(String CCModelItem) {
        return ICCDatasources.getCCModelNo(CCModelItem);
    }

    @Override
    public void insertToCCModel(CCModel... CCModel) {
        ICCDatasources.insertToCCModel(CCModel);
    }

    @Override
    public void updateCCModel(CCModel... CCModel) {
        ICCDatasources.updateCCModel(CCModel);
    }

    @Override
    public void deleteCCModel(CCModel... CCModel) {
        ICCDatasources.deleteCCModel(CCModel);
    }
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IDistrictDatasources;
import xact.idea.camelia.Database.Model.District;

public class DistrictRepository implements IDistrictDatasources {
    public IDistrictDatasources IDistrictDatasources;
    public DistrictRepository(IDistrictDatasources IDistrictDatasources){
        this.IDistrictDatasources=IDistrictDatasources;
    }
    private static  DistrictRepository instance;

    public static DistrictRepository getInstance(IDistrictDatasources iCartDataSource){
        if(instance==null)
            instance= new DistrictRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<District>> getDistrictItems() {
        return IDistrictDatasources.getDistrictItems();
    }

    @Override
    public Flowable<List<District>> getDistrictItemById(int DistrictItemId) {
        return IDistrictDatasources.getDistrictItemById(DistrictItemId);
    }

    @Override
    public District getDistrict(String DistrictItem) {
        return IDistrictDatasources.getDistrict(DistrictItem);
    }

    @Override
    public void emptyDistrict() {
        IDistrictDatasources.emptyDistrict();
    }

    @Override
    public void updateLanguage(String lang) {
        IDistrictDatasources.updateLanguage(lang);
    }

    @Override
    public int size() {
        return IDistrictDatasources.size();
    }

    @Override
    public District getDistrictNo(String DistrictItem) {
        return IDistrictDatasources.getDistrictNo(DistrictItem);
    }

    @Override
    public void insertToDistrict(District... District) {
        IDistrictDatasources.insertToDistrict(District);
    }

    @Override
    public void updateDistrict(District... District) {
        IDistrictDatasources.updateDistrict(District);
    }

    @Override
    public void deleteDistrict(District... District) {
        IDistrictDatasources.deleteDistrict(District);
    }
}

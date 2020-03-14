package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.DistrictDao;
import xact.idea.camelia.Database.DataSourcesInterface.IDistrictDatasources;
import xact.idea.camelia.Database.Model.District;

public class DistrictDataSources implements IDistrictDatasources {

    private DistrictDao DistrictDao;
    private static DistrictDataSources instance;

    public DistrictDataSources(DistrictDao DistrictDao){
        this.DistrictDao=DistrictDao;
    }
    public static DistrictDataSources getInstance(DistrictDao DistrictDao){
        if(instance==null)
            instance = new DistrictDataSources(DistrictDao);
        return instance;

    }

    @Override
    public Flowable<List<District>> getDistrictItems() {
        return DistrictDao.getDistrictItems();
    }

    @Override
    public Flowable<List<District>> getDistrictItemById(int DistrictItemId) {
        return DistrictDao.getDistrictItemById(DistrictItemId);
    }

    @Override
    public District getDistrict(String DistrictItem) {
        return DistrictDao.getDistrict(DistrictItem);
    }

    @Override
    public void emptyDistrict() {
        DistrictDao.emptyDistrict();
    }

    @Override
    public void updateLanguage(String lang) {
        DistrictDao.updateLanguage(lang);
    }

    @Override
    public int size() {
        return DistrictDao.value();
    }

    @Override
    public District getDistrictNo(String DistrictItem) {
        return DistrictDao.getDistrictNo(DistrictItem);
    }

    @Override
    public void insertToDistrict(District... District) {
        DistrictDao.insertToDistrict(District);
    }

    @Override
    public void updateDistrict(District... District) {
        DistrictDao.updateDistrict(District);
    }

    @Override
    public void deleteDistrict(District... District) {
        DistrictDao.deleteDistrict(District);
    }
}

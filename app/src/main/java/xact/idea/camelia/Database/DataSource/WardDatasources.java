package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.WardDao;
import xact.idea.camelia.Database.DataSourcesInterface.IWardDatasources;
import xact.idea.camelia.Database.Model.Ward;

public class WardDatasources implements IWardDatasources {

    private WardDao WardDao;
    private static WardDatasources instance;

    public WardDatasources(WardDao WardDao){
        this.WardDao=WardDao;
    }
    public static WardDatasources getInstance(WardDao WardDao){
        if(instance==null)
            instance = new WardDatasources(WardDao);
        return instance;

    }

    @Override
    public Flowable<List<Ward>> getWardItems() {
        return WardDao.getWardItems();
    }

    @Override
    public Flowable<List<Ward>> getWardItemById(int WardItemId) {
        return WardDao.getWardItemById(WardItemId);
    }

    @Override
    public Ward getWard(String WardItem) {
        return WardDao.getWard(WardItem);
    }

    @Override
    public void emptyWard() {
        WardDao.emptyWard();
    }

    @Override
    public int size() {
        return WardDao.value();
    }

    @Override
    public void updateLanguage(String lang) {
        WardDao.updateLanguage(lang);
    }

    @Override
    public Ward getWardNo(String WardItem) {
        return WardDao.getWardNo(WardItem);
    }

    @Override
    public void insertToWard(Ward... Wards) {
        WardDao.insertToWard(Wards);
    }

    @Override
    public void updateWard(Ward... Wards) {
        WardDao.updateWard(Wards);
    }

    @Override
    public void deleteWard(Ward... Wards) {
        WardDao.deleteWard(Wards);
    }
}

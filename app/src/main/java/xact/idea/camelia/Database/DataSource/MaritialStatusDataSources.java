package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MaritialStatusDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMaritialStatusDatasources;
import xact.idea.camelia.Database.Model.MaritialStatus;

public class MaritialStatusDataSources implements IMaritialStatusDatasources {

    private MaritialStatusDao MaritialStatusDao;
    private static MaritialStatusDataSources instance;

    public MaritialStatusDataSources(MaritialStatusDao MaritialStatusDao){
        this.MaritialStatusDao=MaritialStatusDao;
    }
    public static MaritialStatusDataSources getInstance(MaritialStatusDao MaritialStatusDao){
        if(instance==null)
            instance = new MaritialStatusDataSources(MaritialStatusDao);
        return instance;

    }

    @Override
    public Flowable<List<MaritialStatus>> getMaritialStatusItems() {
        return MaritialStatusDao.getMaritialStatusItems();
    }

    @Override
    public Flowable<List<MaritialStatus>> getMaritialStatusItemById(int MaritialStatusItemId) {
        return MaritialStatusDao.getMaritialStatusItemById(MaritialStatusItemId);
    }

    @Override
    public MaritialStatus getMaritialStatus(String MaritialStatusItem) {
        return MaritialStatusDao.getMaritialStatus(MaritialStatusItem);
    }

    @Override
    public void emptyDivision() {
        MaritialStatusDao.emptyMaritialStatus();
    }

    @Override
    public int size() {
        return MaritialStatusDao.value();
    }

    @Override
    public MaritialStatus getMaritialStatusNo(String MaritialStatusItem) {
        return MaritialStatusDao.getMaritialStatusNo(MaritialStatusItem);
    }

    @Override
    public void insertToMaritialStatus(MaritialStatus... MaritialStatus) {
        MaritialStatusDao.insertToMaritialStatus(MaritialStatus);
    }

    @Override
    public void updateMaritialStatus(MaritialStatus... MaritialStatus) {
        MaritialStatusDao.updateMaritialStatus(MaritialStatus);
    }

    @Override
    public void deleteMaritialStatus(MaritialStatus... MaritialStatus) {
        MaritialStatusDao.deleteMaritialStatus(MaritialStatus);
    }
}

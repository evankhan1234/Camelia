package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMaritialStatusDatasources;
import xact.idea.camelia.Database.Model.MaritialStatus;

public class MaritialStatusRepository implements IMaritialStatusDatasources {
    public IMaritialStatusDatasources IMaritialStatusDatasources;
    public MaritialStatusRepository(IMaritialStatusDatasources IMaritialStatusDatasources){
        this.IMaritialStatusDatasources=IMaritialStatusDatasources;
    }
    private static  MaritialStatusRepository instance;

    public static MaritialStatusRepository getInstance(IMaritialStatusDatasources iCartDataSource){
        if(instance==null)
            instance= new MaritialStatusRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<MaritialStatus>> getMaritialStatusItems() {
        return IMaritialStatusDatasources.getMaritialStatusItems();
    }

    @Override
    public Flowable<List<MaritialStatus>> getMaritialStatusItemById(int MaritialStatusItemId) {
        return IMaritialStatusDatasources.getMaritialStatusItemById(MaritialStatusItemId);
    }

    @Override
    public MaritialStatus getMaritialStatus(String MaritialStatusItem) {
        return IMaritialStatusDatasources.getMaritialStatus(MaritialStatusItem);
    }

    @Override
    public void emptyDivision() {
        IMaritialStatusDatasources.emptyDivision();
    }

    @Override
    public int size() {
        return IMaritialStatusDatasources.size();
    }

    @Override
    public MaritialStatus getMaritialStatusNo(String MaritialStatusItem) {
        return IMaritialStatusDatasources.getMaritialStatusNo(MaritialStatusItem);
    }

    @Override
    public void insertToMaritialStatus(MaritialStatus... MaritialStatus) {
        IMaritialStatusDatasources.insertToMaritialStatus(MaritialStatus);
    }

    @Override
    public void updateMaritialStatus(MaritialStatus... MaritialStatus) {
        IMaritialStatusDatasources.updateMaritialStatus(MaritialStatus);
    }

    @Override
    public void deleteMaritialStatus(MaritialStatus... MaritialStatus) {
        IMaritialStatusDatasources.deleteMaritialStatus(MaritialStatus);
    }
}

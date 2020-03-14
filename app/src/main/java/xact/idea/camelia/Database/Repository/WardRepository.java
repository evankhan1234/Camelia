package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IWardDatasources;
import xact.idea.camelia.Database.Model.Ward;

public class WardRepository implements IWardDatasources {
    public IWardDatasources IWardDatasources;
    public WardRepository(IWardDatasources IWardDatasources){
        this.IWardDatasources=IWardDatasources;
    }
    private static  WardRepository instance;

    public static WardRepository getInstance(IWardDatasources iCartDataSource){
        if(instance==null)
            instance= new WardRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Ward>> getWardItems() {
        return IWardDatasources.getWardItems();
    }

    @Override
    public Flowable<List<Ward>> getWardItemById(int WardItemId) {
        return IWardDatasources.getWardItemById(WardItemId);
    }

    @Override
    public Ward getWard(String WardItem) {
        return IWardDatasources.getWard(WardItem);
    }

    @Override
    public void emptyWard() {
        IWardDatasources.emptyWard();
    }

    @Override
    public int size() {
        return IWardDatasources.size();
    }

    @Override
    public void updateLanguage(String lang) {
        IWardDatasources.updateLanguage(lang);
    }

    @Override
    public Ward getWardNo(String WardItem) {
        return IWardDatasources.getWardNo(WardItem);
    }

    @Override
    public void insertToWard(Ward... Wards) {
        IWardDatasources.insertToWard(Wards);
    }

    @Override
    public void updateWard(Ward... Wards) {
        IWardDatasources.deleteWard(Wards);
    }

    @Override
    public void deleteWard(Ward... Wards) {
        IWardDatasources.deleteWard(Wards);
    }
}

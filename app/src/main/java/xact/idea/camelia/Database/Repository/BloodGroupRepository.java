package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IBloodGroupDataSources;
import xact.idea.camelia.Database.Model.BloodGroup;

public class BloodGroupRepository implements IBloodGroupDataSources {
    public IBloodGroupDataSources IBloodGroupDataSources;
    public BloodGroupRepository(IBloodGroupDataSources IBloodGroupDataSources){
        this.IBloodGroupDataSources=IBloodGroupDataSources;
    }
    private static  BloodGroupRepository instance;

    public static BloodGroupRepository getInstance(IBloodGroupDataSources iCartDataSource){
        if(instance==null)
            instance= new BloodGroupRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<BloodGroup>> getBloodGroupItems() {
        return IBloodGroupDataSources.getBloodGroupItems();
    }

    @Override
    public Flowable<List<BloodGroup>> getBloodGroupItemById(int BloodGroupItemId) {
        return IBloodGroupDataSources.getBloodGroupItemById(BloodGroupItemId);
    }

    @Override
    public BloodGroup getBloodGroup(String BloodGroupItem) {
        return IBloodGroupDataSources.getBloodGroup(BloodGroupItem);
    }

    @Override
    public void emptyBloodGroup() {
        IBloodGroupDataSources.emptyBloodGroup();
    }

    @Override
    public int size() {
        return IBloodGroupDataSources.size();
    }

    @Override
    public BloodGroup getBloodGroupNo(String BloodGroupItem) {
        return IBloodGroupDataSources.getBloodGroupNo(BloodGroupItem);
    }

    @Override
    public void insertToBloodGroup(BloodGroup... BloodGroup) {
        IBloodGroupDataSources.insertToBloodGroup(BloodGroup);
    }

    @Override
    public void updateBloodGroup(BloodGroup... BloodGroup) {
        IBloodGroupDataSources.updateBloodGroup(BloodGroup);
    }

    @Override
    public void deleteBloodGroup(BloodGroup... BloodGroup) {
        IBloodGroupDataSources.deleteBloodGroup(BloodGroup);
    }
}

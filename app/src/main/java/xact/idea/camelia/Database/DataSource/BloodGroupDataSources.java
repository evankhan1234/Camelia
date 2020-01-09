package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.BloodGroupDao;
import xact.idea.camelia.Database.DataSourcesInterface.IBloodGroupDataSources;
import xact.idea.camelia.Database.Model.BloodGroup;

public class BloodGroupDataSources implements IBloodGroupDataSources {

    private BloodGroupDao BloodGroupDao;
    private static BloodGroupDataSources instance;

    public BloodGroupDataSources(BloodGroupDao BloodGroupDao){
        this.BloodGroupDao=BloodGroupDao;
    }
    public static BloodGroupDataSources getInstance(BloodGroupDao BloodGroupDao){
        if(instance==null)
            instance = new BloodGroupDataSources(BloodGroupDao);
        return instance;

    }

    @Override
    public Flowable<List<BloodGroup>> getBloodGroupItems() {
        return BloodGroupDao.getBloodGroupItems();
    }

    @Override
    public Flowable<List<BloodGroup>> getBloodGroupItemById(int BloodGroupItemId) {
        return BloodGroupDao.getBloodGroupItemById(BloodGroupItemId);
    }

    @Override
    public BloodGroup getBloodGroup(String BloodGroupItem) {
        return BloodGroupDao.getBloodGroup(BloodGroupItem);
    }

    @Override
    public void emptyBloodGroup() {
        BloodGroupDao.emptyBloodGroup();
    }

    @Override
    public int size() {
        return BloodGroupDao.value();
    }

    @Override
    public BloodGroup getBloodGroupNo(String BloodGroupItem) {
        return BloodGroupDao.getBloodGroupNo(BloodGroupItem);
    }

    @Override
    public void insertToBloodGroup(BloodGroup... BloodGroup) {
        BloodGroupDao.insertToBloodGroup(BloodGroup);
    }

    @Override
    public void updateBloodGroup(BloodGroup... BloodGroup) {
        BloodGroupDao.updateBloodGroup(BloodGroup);
    }

    @Override
    public void deleteBloodGroup(BloodGroup... BloodGroup) {
        BloodGroupDao.deleteBloodGroup(BloodGroup);
    }
}

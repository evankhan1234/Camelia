package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.BloodGroup;

public interface IBloodGroupDataSources {
    Flowable<List<BloodGroup>> getBloodGroupItems();

    Flowable<List<BloodGroup>> getBloodGroupItemById(int BloodGroupItemId);

    BloodGroup getBloodGroup(String BloodGroupItem);

    void emptyBloodGroup();

    int size();

    BloodGroup getBloodGroupNo(String BloodGroupItem);

    void insertToBloodGroup(BloodGroup... BloodGroup);

    void updateBloodGroup(BloodGroup... BloodGroup);

    void deleteBloodGroup(BloodGroup... BloodGroup);
}

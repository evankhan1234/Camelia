package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Ward;

public interface IWardDatasources {
    Flowable<List<Ward>> getWardItems();

    Flowable<List<Ward>> getWardItemById(int WardItemId);

    Ward getWard(String WardItem);

    void emptyWard();

    int size();
    void updateLanguage(String lang);
    Ward getWardNo(String WardItem);

    void insertToWard(Ward... Wards);

    void updateWard(Ward... Wards);

    void deleteWard(Ward... Wards);
}

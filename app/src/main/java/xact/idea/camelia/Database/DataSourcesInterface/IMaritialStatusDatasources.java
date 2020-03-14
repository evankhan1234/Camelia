package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MaritialStatus;

public interface IMaritialStatusDatasources {
    Flowable<List<MaritialStatus>> getMaritialStatusItems();

    Flowable<List<MaritialStatus>> getMaritialStatusItemById(int MaritialStatusItemId);

    MaritialStatus getMaritialStatus(String MaritialStatusItem);

    void emptyDivision();

    int size();
    void updateLanguage(String lang);
    MaritialStatus getMaritialStatusNo(String MaritialStatusItem);

    void insertToMaritialStatus(MaritialStatus... MaritialStatus);

    void updateMaritialStatus(MaritialStatus... MaritialStatus);

    void deleteMaritialStatus(MaritialStatus... MaritialStatus);
}

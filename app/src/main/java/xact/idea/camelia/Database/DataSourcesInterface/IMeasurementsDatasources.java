package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Measurements;

public interface IMeasurementsDatasources {
    Flowable<List<Measurements>> getMeasurementsItems();

    Flowable<List<Measurements>> getMeasurementsItemById(String MeasurementsItemId,String MemberId);

    Measurements getMeasurements(String MeasurementsItem);
    int maxValue();
    void emptyMeasurements();
    Measurements getMeasurementsDownloadNo(String MeasurementsId,String MemberId);

    int size();
    int valueFor(String Id);
    Measurements getMeasurementsNo(String MeasurementsItem);

    void insertToMeasurements(Measurements... Measurements);

    void updateMeasurements(Measurements... Measurements);

    void deleteMeasurements(Measurements... Measurements);
}

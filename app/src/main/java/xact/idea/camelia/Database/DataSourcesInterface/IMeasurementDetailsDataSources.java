package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MeasurementDetails;

public interface IMeasurementDetailsDataSources {
    Flowable<List<MeasurementDetails>> getMeasurementsItems();

    Flowable<List<MeasurementDetails>> getMeasurementsItemById(int MeasurementsItemId);

    MeasurementDetails getMeasurements(String MeasurementsItem);
    int maxValue();
    void emptyMeasurements();

    int size();

    MeasurementDetails getMeasurementsNo(String MeasurementsItem);

    void insertToMeasurements(MeasurementDetails... MeasurementDetails);

    void updateMeasurements(MeasurementDetails... MeasurementDetails);

    void deleteMeasurements(MeasurementDetails... MeasurementDetails);
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMeasurementDetailsDataSources;
import xact.idea.camelia.Database.Model.MeasurementDetails;

public class MeasurementDetailsRepository implements IMeasurementDetailsDataSources {
    public IMeasurementDetailsDataSources IMeasurementDetailsDataSources;
    public MeasurementDetailsRepository(IMeasurementDetailsDataSources IMeasurementDetailsDataSources){
        this.IMeasurementDetailsDataSources=IMeasurementDetailsDataSources;
    }
    private static  MeasurementDetailsRepository instance;

    public static MeasurementDetailsRepository getInstance(IMeasurementDetailsDataSources iCartDataSource){
        if(instance==null)
            instance= new MeasurementDetailsRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<MeasurementDetails>> getMeasurementsItems() {
        return IMeasurementDetailsDataSources.getMeasurementsItems();
    }

    @Override
    public Flowable<List<MeasurementDetails>> getMeasurementsItemById(int MeasurementsItemId) {
        return IMeasurementDetailsDataSources.getMeasurementsItemById(MeasurementsItemId);
    }

    @Override
    public MeasurementDetails getMeasurements(String MeasurementsItem) {
        return IMeasurementDetailsDataSources.getMeasurements(MeasurementsItem);
    }

    @Override
    public int maxValue() {
        return IMeasurementDetailsDataSources.maxValue();
    }

    @Override
    public void emptyMeasurements() {
        IMeasurementDetailsDataSources.emptyMeasurements();
    }

    @Override
    public int size() {
        return IMeasurementDetailsDataSources.size();
    }

    @Override
    public MeasurementDetails getMeasurementsNo(String MeasurementsItem) {
        return IMeasurementDetailsDataSources.getMeasurementsNo(MeasurementsItem);
    }

    @Override
    public void insertToMeasurements(MeasurementDetails... MeasurementDetails) {
        IMeasurementDetailsDataSources.insertToMeasurements(MeasurementDetails);
    }

    @Override
    public void updateMeasurements(MeasurementDetails... MeasurementDetails) {
        IMeasurementDetailsDataSources.updateMeasurements(MeasurementDetails);
    }

    @Override
    public void deleteMeasurements(MeasurementDetails... MeasurementDetails) {
        IMeasurementDetailsDataSources.deleteMeasurements(MeasurementDetails);
    }
}

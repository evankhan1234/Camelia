package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMeasurementsDatasources;
import xact.idea.camelia.Database.DataSourcesInterface.IMeasurementsDatasources;
import xact.idea.camelia.Database.Model.Measurements;

public class MeasurementsRepository implements IMeasurementsDatasources {
    public IMeasurementsDatasources IMeasurementsDatasources;
    public MeasurementsRepository(IMeasurementsDatasources IMeasurementsDatasources){
        this.IMeasurementsDatasources=IMeasurementsDatasources;
    }
    private static  MeasurementsRepository instance;

    public static MeasurementsRepository getInstance(IMeasurementsDatasources iCartDataSource){
        if(instance==null)
            instance= new MeasurementsRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<Measurements>> getMeasurementsItems() {
        return IMeasurementsDatasources.getMeasurementsItems();
    }

    @Override
    public Flowable<List<Measurements>> getMeasurementsItemById(String MeasurementsItemId) {
        return IMeasurementsDatasources.getMeasurementsItemById(MeasurementsItemId);
    }

    @Override
    public Measurements getMeasurements(String MeasurementsItem) {
        return IMeasurementsDatasources.getMeasurements(MeasurementsItem);
    }

    @Override
    public int maxValue() {
        return IMeasurementsDatasources.maxValue();
    }

    @Override
    public void emptyMeasurements() {
        IMeasurementsDatasources.emptyMeasurements();
    }

    @Override
    public int size() {
        return IMeasurementsDatasources.size();
    }

    @Override
    public Measurements getMeasurementsNo(String MeasurementsItem) {
        return IMeasurementsDatasources.getMeasurementsNo(MeasurementsItem);
    }

    @Override
    public void insertToMeasurements(Measurements... Measurements) {
        IMeasurementsDatasources.insertToMeasurements(Measurements);
    }

    @Override
    public void updateMeasurements(Measurements... Measurements) {
        IMeasurementsDatasources.updateMeasurements(Measurements);
    }

    @Override
    public void deleteMeasurements(Measurements... Measurements) {
        IMeasurementsDatasources.deleteMeasurements(Measurements);
    }
}

package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MeasurementsDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMeasurementsDatasources;
import xact.idea.camelia.Database.Model.Measurements;

public class MeasurementsDataSources implements IMeasurementsDatasources {
    private MeasurementsDao MeasurementsDao;
    private static MeasurementsDataSources instance;

    public MeasurementsDataSources(MeasurementsDao MeasurementsDao){
        this.MeasurementsDao=MeasurementsDao;
    }
    public static MeasurementsDataSources getInstance(MeasurementsDao MeasurementsDao){
        if(instance==null)
            instance = new MeasurementsDataSources(MeasurementsDao);
        return instance;

    }
    @Override
    public Flowable<List<Measurements>> getMeasurementsItems() {
        return MeasurementsDao.getMeasurementsItems();
    }

    @Override
    public Flowable<List<Measurements>> getMeasurementsItemById(int MeasurementsItemId) {
        return MeasurementsDao.getMeasurementsItemById(MeasurementsItemId);
    }

    @Override
    public Measurements getMeasurements(String MeasurementsItem) {
        return MeasurementsDao.getMeasurements(MeasurementsItem);
    }

    @Override
    public void emptyMeasurements() {
        MeasurementsDao.emptyMeasurements();
    }

    @Override
    public int size() {
        return MeasurementsDao.value();
    }

    @Override
    public Measurements getMeasurementsNo(String MeasurementsItem) {
        return MeasurementsDao.getMeasurementsNo(MeasurementsItem);
    }

    @Override
    public void insertToMeasurements(Measurements... Measurements) {
        MeasurementsDao.insertToMeasurements(Measurements);
    }

    @Override
    public void updateMeasurements(Measurements... Measurements) {
        MeasurementsDao.updateMeasurements(Measurements);
    }

    @Override
    public void deleteMeasurements(Measurements... Measurements) {
        MeasurementsDao.deleteMeasurements(Measurements);
    }
}

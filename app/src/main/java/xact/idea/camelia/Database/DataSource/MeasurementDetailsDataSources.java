package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MeasurementDetailsDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMeasurementDetailsDataSources;
import xact.idea.camelia.Database.Model.MeasurementDetails;

public class MeasurementDetailsDataSources implements IMeasurementDetailsDataSources {
    private MeasurementDetailsDao MeasurementDetailsDao;
    private static MeasurementDetailsDataSources instance;

    public MeasurementDetailsDataSources(MeasurementDetailsDao MeasurementDetailsDao){
        this.MeasurementDetailsDao=MeasurementDetailsDao;
    }
    public static MeasurementDetailsDataSources getInstance(MeasurementDetailsDao MeasurementDetailsDao){
        if(instance==null)
            instance = new MeasurementDetailsDataSources(MeasurementDetailsDao);
        return instance;

    }
    @Override
    public Flowable<List<MeasurementDetails>> getMeasurementsItems() {
        return MeasurementDetailsDao.getMeasurementsItems();
    }

    @Override
    public Flowable<List<MeasurementDetails>> getMeasurementsItemById(int MeasurementsItemId) {
        return MeasurementDetailsDao.getMeasurementsItemById(MeasurementsItemId);
    }

    @Override
    public MeasurementDetails getMeasurements(String MeasurementsItem) {
        return MeasurementDetailsDao.getMeasurements(MeasurementsItem);
    }

    @Override
    public int maxValue() {
        return MeasurementDetailsDao.maxValue();
    }

    @Override
    public void emptyMeasurements() {
        MeasurementDetailsDao.emptyMeasurements();
    }

    @Override
    public int size() {
        return MeasurementDetailsDao.value();
    }

    @Override
    public MeasurementDetails getMeasurementsNo(String MeasurementsItem) {
        return MeasurementDetailsDao.getMeasurementsNo(MeasurementsItem);
    }

    @Override
    public void insertToMeasurements(MeasurementDetails... MeasurementDetails) {
        MeasurementDetailsDao.insertToMeasurements(MeasurementDetails);
    }

    @Override
    public void updateMeasurements(MeasurementDetails... MeasurementDetails) {
        MeasurementDetailsDao.updateMeasurements(MeasurementDetails);
    }

    @Override
    public void deleteMeasurements(MeasurementDetails... MeasurementDetails) {
        MeasurementDetailsDao.deleteMeasurements(MeasurementDetails);
    }
}

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
    public Flowable<List<Measurements>> getMeasurementsItemById(String MeasurementsItemId,String MemberId) {
        return MeasurementsDao.getMeasurementsItemById(MeasurementsItemId,MemberId);
    }

    @Override
    public Measurements getMeasurements(String MeasurementsItem) {
        return MeasurementsDao.getMeasurements(MeasurementsItem);
    }

    @Override
    public int maxValue() {
        return MeasurementsDao.maxValue();
    }

    @Override
    public void emptyMeasurements() {
        MeasurementsDao.emptyMeasurements();
    }

    @Override
    public Measurements getMeasurementsDownloadNo(String MeasurementsId, String MemberId) {
        return MeasurementsDao.getMeasurementsDownloadNo(MeasurementsId, MemberId);
    }

    @Override
    public int size() {
        return MeasurementsDao.value();
    }

    @Override
    public int valueFor(String Id) {
        return MeasurementsDao.valueFor(Id);
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

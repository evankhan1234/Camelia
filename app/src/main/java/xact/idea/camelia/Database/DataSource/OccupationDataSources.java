package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.OccupationDao;
import xact.idea.camelia.Database.DataSourcesInterface.IOccupationDatasources;
import xact.idea.camelia.Database.Model.Occupation;

public class OccupationDataSources implements IOccupationDatasources {

    private OccupationDao OccupationDao;
    private static OccupationDataSources instance;

    public OccupationDataSources(OccupationDao OccupationDao){
        this.OccupationDao=OccupationDao;
    }
    public static OccupationDataSources getInstance(OccupationDao OccupationDao){
        if(instance==null)
            instance = new OccupationDataSources(OccupationDao);
        return instance;

    }

    @Override
    public Flowable<List<Occupation>> getOccupationItems() {
        return OccupationDao.getOccupationItems();
    }

    @Override
    public Flowable<List<Occupation>> getOccupationItemById(int OccupationItemId) {
        return OccupationDao.getOccupationItemById(OccupationItemId);
    }

    @Override
    public Occupation getOccupation(String OccupationItem) {
        return OccupationDao.getOccupation(OccupationItem);
    }

    @Override
    public void emptyOccupation() {
        OccupationDao.emptyOccupation();
    }

    @Override
    public void updateLanguage(String lang) {
        OccupationDao.updateLanguage(lang);
    }

    @Override
    public int size() {
        return OccupationDao.value();
    }

    @Override
    public Occupation getOccupationNo(String OccupationItem) {
        return OccupationDao.getOccupation(OccupationItem);
    }

    @Override
    public void insertToOccupation(Occupation... Occupations) {
        OccupationDao.insertToOccupation(Occupations);
    }

    @Override
    public void updateOccupation(Occupation... Occupations) {
        OccupationDao.updateOccupation(Occupations);
    }

    @Override
    public void deleteOccupation(Occupation... Occupations) {
        OccupationDao.deleteOccupation(Occupations);
    }
}

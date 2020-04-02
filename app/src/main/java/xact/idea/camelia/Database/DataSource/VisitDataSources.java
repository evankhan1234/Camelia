package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.VisitDao;

import xact.idea.camelia.Database.DataSourcesInterface.IVisitDataSources;
import xact.idea.camelia.Database.Model.Visit;

public class VisitDataSources implements IVisitDataSources {

    private VisitDao VisitDao;
    private static VisitDataSources instance;

    public VisitDataSources(VisitDao VisitDao){
        this.VisitDao=VisitDao;
    }
    public static VisitDataSources getInstance(VisitDao VisitDao){
        if(instance==null)
            instance = new VisitDataSources(VisitDao);
        return instance;

    }

    @Override
    public Flowable<List<Visit>> getVisitItems() {
        return VisitDao.getVisitItems();
    }

    @Override
    public Flowable<List<Visit>> getVisitItemById(int VisitItemId) {
        return VisitDao.getVisitItemById(VisitItemId);
    }

    @Override
    public Visit getVisit(String VisitItem) {
        return VisitDao.getVisit(VisitItem);
    }

    @Override
    public void emptyVisit() {
        VisitDao.emptyVisit();
    }

    @Override
    public int size() {
        return VisitDao.value();
    }

    @Override
    public Visit getVisitNo(String VisitItem) {
        return VisitDao.getVisitNo(VisitItem);
    }

    @Override
    public void insertToVisit(Visit... Visit) {
        VisitDao.insertToVisit(Visit);
    }

    @Override
    public void updateVisit(Visit... Visit) {
        VisitDao.updateVisit(Visit);
    }

    @Override
    public void deleteVisit(Visit... Visit) {
        VisitDao.deleteVisit(Visit);
    }
}

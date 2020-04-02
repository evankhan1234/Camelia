package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IVisitDataSources;
import xact.idea.camelia.Database.Model.Visit;

public class VisitRepository implements IVisitDataSources {
    public IVisitDataSources IVisitDatasources;
    public VisitRepository(IVisitDataSources IVisitDatasources){
        this.IVisitDatasources=IVisitDatasources;
    }
    private static  VisitRepository instance;

    public static VisitRepository getInstance(IVisitDataSources iCartDataSource){
        if(instance==null)
            instance= new VisitRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Visit>> getVisitItems() {
        return IVisitDatasources.getVisitItems();
    }

    @Override
    public Flowable<List<Visit>> getVisitItemById(int VisitItemId) {
        return IVisitDatasources.getVisitItemById(VisitItemId);
    }

    @Override
    public Visit getVisit(String VisitItem) {
        return IVisitDatasources.getVisit(VisitItem);
    }

    @Override
    public void emptyVisit() {
        IVisitDatasources.emptyVisit();
    }



    @Override
    public int size() {
        return IVisitDatasources.size();
    }

    @Override
    public Visit getVisitNo(String VisitItem) {
        return IVisitDatasources.getVisitNo(VisitItem);
    }

    @Override
    public void insertToVisit(Visit... Visit) {
        IVisitDatasources.insertToVisit(Visit);
    }

    @Override
    public void updateVisit(Visit... Visit) {
        IVisitDatasources.updateVisit(Visit);
    }

    @Override
    public void deleteVisit(Visit... Visit) {
        IVisitDatasources.deleteVisit(Visit);
    }
}

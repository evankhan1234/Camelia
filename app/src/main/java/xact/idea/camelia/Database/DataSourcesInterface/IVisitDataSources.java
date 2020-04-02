package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Visit;

public interface IVisitDataSources {
    Flowable<List<Visit>> getVisitItems();

    Flowable<List<Visit>> getVisitItemById(int VisitItemId);

    Visit getVisit(String VisitItem);

    void emptyVisit();
    int size();

    Visit getVisitNo(String VisitItem);

    void insertToVisit(Visit... Visit);

    void updateVisit(Visit... Visit);

    void deleteVisit(Visit... Visit);
}

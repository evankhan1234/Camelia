package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Occupation;

public interface IOccupationDatasources {
    Flowable<List<Occupation>> getOccupationItems();

    Flowable<List<Occupation>> getOccupationItemById(int OccupationItemId);

    Occupation getOccupation(String OccupationItem);

    void emptyOccupation();
    void updateLanguage(String lang);
    int size();

    Occupation getOccupationNo(String OccupationItem);

    void insertToOccupation(Occupation... Occupations);

    void updateOccupation(Occupation... Occupations);

    void deleteOccupation(Occupation... Occupations);
}

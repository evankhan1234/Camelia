package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Division;

public interface IDivisionDataSources {
    Flowable<List<Division>> getDivisionItems();

    Flowable<List<Division>> getDivisionItemById(int BookItemId);

    Division getDivision(String DivisionItem);

    void emptyDivision();

    int size();
    void updateLanguage(String lang);

    Division getDivisionNo(String BookItem);

    void insertToDivision(Division... Books);

    void updateDivision(Division... Books);

    void deleteDivision(Division... Books);
}

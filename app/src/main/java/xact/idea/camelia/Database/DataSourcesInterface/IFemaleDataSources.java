package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Female;

public interface IFemaleDataSources {
    Flowable<List<Female>> getFemaleItems();

    Flowable<List<Female>> getFemaleItemById(int BookItemId);

    Female getFemale(String FemaleItem);

    void emptyFemale();

    int size();
    void updateLanguage(String lang);
    Female getFemaleNo(String FemaleItem);

    void insertToFemale(Female... Female);

    void updateFemale(Female... Female);

    void deleteFemale(Female... Female);
}

package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.District;

public interface IDistrictDatasources {
    Flowable<List<District>> getDistrictItems();

    Flowable<List<District>> getDistrictItemById(int DistrictItemId);

    District getDistrict(String DistrictItem);

    void emptyDistrict();
    void updateLanguage(String lang);
    int size();

    District getDistrictNo(String DistrictItem);

    void insertToDistrict(District... District);

    void updateDistrict(District... District);

    void deleteDistrict(District... District);
}

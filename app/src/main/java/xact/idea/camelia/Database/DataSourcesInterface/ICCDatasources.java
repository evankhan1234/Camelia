package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.CCModel;

public interface ICCDatasources {

    Flowable<List<CCModel>> getCCModelItems();

    Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId);

    CCModel getCCModel(String CCModelItem);

    void emptyCCModel();

    Flowable<List<CCModel>> getCCModelItemByFour(String DivisionId, String DistrictId, String Upazila, String Union);

    Flowable<List<CCModel>> getCCModelItemByThree(String DivisionId,String DistrictId,String Upazila);

    Flowable<List<CCModel>> getCCModelItemByTwo(String DivisionId,String DistrictId);

    Flowable<List<CCModel>> getCCModelItemByOne(String DivisionId);
    int size();

    CCModel getCCModelNo(String CCModelItem);

    void insertToCCModel(CCModel... CCModel);

    void updateCCModel(CCModel... CCModel);

    void deleteCCModel(CCModel... CCModel);
}

package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.CCModel;

public interface ICCDatasources {

    Flowable<List<CCModel>> getCCModelItems();

    Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId);

    CCModel getCCModel(String CCModelItem);

    void emptyCCModel();

    int size();

    CCModel getCCModelNo(String CCModelItem);

    void insertToCCModel(CCModel... CCModel);

    void updateCCModel(CCModel... CCModel);

    void deleteCCModel(CCModel... CCModel);
}

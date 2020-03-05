package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.CCDao;
import xact.idea.camelia.Database.DataSourcesInterface.ICCDatasources;
import xact.idea.camelia.Database.Model.CCModel;

public class CCDataSources implements ICCDatasources {

    private CCDao CCModelDao;
    private static CCDataSources instance;

    public CCDataSources(CCDao CCModelDao){
        this.CCModelDao=CCModelDao;
    }
    public static CCDataSources getInstance(CCDao CCModelDao){
        if(instance==null)
            instance = new CCDataSources(CCModelDao);
        return instance;

    }

    @Override
    public Flowable<List<CCModel>> getCCModelItems() {
        return CCModelDao.getCCModelItems();
    }

    @Override
    public Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId) {
        return CCModelDao.getCCModelItemById(CCModelItemId);
    }

    @Override
    public CCModel getCCModel(String CCModelItem) {
        return CCModelDao.getCCModel(CCModelItem);
    }

    @Override
    public void emptyCCModel() {
        CCModelDao.emptyCCModel();
    }

    @Override
    public int size() {
        return CCModelDao.value();
    }

    @Override
    public CCModel getCCModelNo(String CCModelItem) {
        return CCModelDao.getCCModelNo(CCModelItem);
    }

    @Override
    public void insertToCCModel(CCModel... CCModel) {
        CCModelDao.insertToCCModel(CCModel);
    }

    @Override
    public void updateCCModel(CCModel... CCModel) {
        CCModelDao.updateCCModel(CCModel);
    }

    @Override
    public void deleteCCModel(CCModel... CCModel) {
        CCModelDao.deleteCCModel(CCModel);
    }
}

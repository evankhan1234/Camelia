package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.FemaleDao;
import xact.idea.camelia.Database.DataSourcesInterface.IFemaleDataSources;
import xact.idea.camelia.Database.Model.Female;

public class FemaleDataSources implements IFemaleDataSources {

    private FemaleDao FemaleDao;
    private static FemaleDataSources instance;

    public FemaleDataSources(FemaleDao FemaleDao){
        this.FemaleDao=FemaleDao;
    }
    public static FemaleDataSources getInstance(FemaleDao FemaleDao){
        if(instance==null)
            instance = new FemaleDataSources(FemaleDao);
        return instance;

    }

    @Override
    public Flowable<List<Female>> getFemaleItems() {
        return FemaleDao.getFemaleItems();
    }

    @Override
    public Flowable<List<Female>> getFemaleItemById(int BookItemId) {
        return FemaleDao.getFemaleItemById(BookItemId);
    }

    @Override
    public Female getFemale(String FemaleItem) {
        return FemaleDao.getFemale(FemaleItem);
    }

    @Override
    public void emptyFemale() {
        FemaleDao.emptyFemale();
    }

    @Override
    public int size() {
        return FemaleDao.value();
    }

    @Override
    public void updateLanguage(String lang) {
        FemaleDao.updateLanguage(lang);
    }

    @Override
    public Female getFemaleNo(String FemaleItem) {
        return FemaleDao.getFemaleNo(FemaleItem);
    }

    @Override
    public void insertToFemale(Female... Female) {
        FemaleDao.insertToFemale(Female);
    }

    @Override
    public void updateFemale(Female... Female) {
        FemaleDao.updateFemale(Female);
    }

    @Override
    public void deleteFemale(Female... Female) {
        FemaleDao.deleteFemale(Female);
    }
}

package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.UnionDao;
import xact.idea.camelia.Database.DataSourcesInterface.IUnionDataSources;
import xact.idea.camelia.Database.Model.Unions;

public class UnionDataSources implements IUnionDataSources {

    private UnionDao UnionDao;
    private static UnionDataSources instance;

    public UnionDataSources(UnionDao UnionDao) {
        this.UnionDao = UnionDao;
    }

    public static UnionDataSources getInstance(UnionDao UnionDao) {
        if (instance == null)
            instance = new UnionDataSources(UnionDao);
        return instance;

    }

    @Override
    public Flowable<List<Unions>> getUnionItems() {
        return UnionDao.getUnionItems();
    }

    @Override
    public Flowable<List<Unions>> getUnionItemById(int BookItemId) {
        return UnionDao.getUnionItemById(BookItemId);
    }

    @Override
    public Unions getUnion(String UnionItem) {
        return UnionDao.getUnion(UnionItem);
    }

    @Override
    public void emptyUnion() {
        UnionDao.emptyUnion();
    }

    @Override
    public int size() {
        return UnionDao.value();
    }

    @Override
    public void updateLanguage(String lang) {
        UnionDao.updateLanguage(lang);
    }

    @Override
    public Unions getUnionNo(String BookItem) {
        return UnionDao.getUnionNo(BookItem);
    }

    @Override
    public void insertToUnion(Unions... Unions) {
        UnionDao.insertToUnion(Unions);
    }

    @Override
    public void updateUnion(Unions... Unions) {
        UnionDao.updateUnion(Unions);
    }

    @Override
    public void deleteUnion(Unions... Unions) {
        UnionDao.deleteUnion(Unions);
    }
}

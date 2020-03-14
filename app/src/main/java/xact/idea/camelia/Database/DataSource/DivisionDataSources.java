package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.DivisionDao;
import xact.idea.camelia.Database.DataSourcesInterface.IDivisionDataSources;
import xact.idea.camelia.Database.Model.Division;

public class DivisionDataSources implements IDivisionDataSources {

    private DivisionDao DivisionDao;
    private static DivisionDataSources instance;

    public DivisionDataSources(DivisionDao DivisionDao){
        this.DivisionDao=DivisionDao;
    }
    public static DivisionDataSources getInstance(DivisionDao DivisionDao){
        if(instance==null)
            instance = new DivisionDataSources(DivisionDao);
        return instance;

    }


    @Override
    public Flowable<List<Division>> getDivisionItems() {
        return DivisionDao.getDivisionItems();
    }

    @Override
    public Flowable<List<Division>> getDivisionItemById(int BookItemId) {
        return DivisionDao.getDivisionItemById(BookItemId);
    }

    @Override
    public Division getDivision(String DivisionItem) {
        return DivisionDao.getDivision(DivisionItem);
    }

    @Override
    public void emptyDivision() {
        DivisionDao.emptyDivision();
    }

    @Override
    public int size() {
        return DivisionDao.value();
    }

    @Override
    public void updateLanguage(String lang) {
        DivisionDao.updateLanguage(lang);
    }

    @Override
    public Division getDivisionNo(String BookItem) {
        return DivisionDao.getDivisionNo(BookItem);
    }

    @Override
    public void insertToDivision(Division... Books) {
        DivisionDao.insertToDivision(Books);
    }

    @Override
    public void updateDivision(Division... Books) {
        DivisionDao.updateDivision(Books);
    }

    @Override
    public void deleteDivision(Division... Books) {
        DivisionDao.deleteDivision(Books);
    }


}
package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IDivisionDataSources;
import xact.idea.camelia.Database.Model.Division;

public class DivisionRepository implements IDivisionDataSources {
    public IDivisionDataSources IDivisionDataSources;
    public DivisionRepository(IDivisionDataSources IDivisionDataSources){
        this.IDivisionDataSources=IDivisionDataSources;
    }
    private static  DivisionRepository instance;

    public static DivisionRepository getInstance(IDivisionDataSources iCartDataSource){
        if(instance==null)
            instance= new DivisionRepository(iCartDataSource);
        return instance;

    }


    @Override
    public Flowable<List<Division>> getDivisionItems() {
        return IDivisionDataSources.getDivisionItems();
    }

    @Override
    public Flowable<List<Division>> getDivisionItemById(int BookItemId) {
        return IDivisionDataSources.getDivisionItemById(BookItemId);
    }

    @Override
    public Division getDivision(String DivisionItem) {
        return IDivisionDataSources.getDivision(DivisionItem);
    }

    @Override
    public void emptyDivision() {
        IDivisionDataSources.emptyDivision();
    }

    @Override
    public int size() {
        return IDivisionDataSources.size();
    }

    @Override
    public void updateLanguage(String lang) {
        IDivisionDataSources.updateLanguage(lang);
    }

    @Override
    public Division getDivisionNo(String BookItem) {
        return IDivisionDataSources.getDivisionNo(BookItem);
    }

    @Override
    public void insertToDivision(Division... Books) {
        IDivisionDataSources.insertToDivision(Books);
    }

    @Override
    public void updateDivision(Division... Books) {
        IDivisionDataSources.updateDivision(Books);
    }

    @Override
    public void deleteDivision(Division... Books) {
        IDivisionDataSources.deleteDivision(Books);
    }


}
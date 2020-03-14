package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IOccupationDatasources;
import xact.idea.camelia.Database.Model.Occupation;

public class OccupationRepository implements IOccupationDatasources {
    public IOccupationDatasources IOccupationDatasources;
    public OccupationRepository(IOccupationDatasources IOccupationDatasources){
        this.IOccupationDatasources=IOccupationDatasources;
    }
    private static  OccupationRepository instance;

    public static OccupationRepository getInstance(IOccupationDatasources iCartDataSource){
        if(instance==null)
            instance= new OccupationRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Occupation>> getOccupationItems() {
        return IOccupationDatasources.getOccupationItems();
    }

    @Override
    public Flowable<List<Occupation>> getOccupationItemById(int OccupationItemId) {
        return IOccupationDatasources.getOccupationItemById(OccupationItemId);
    }

    @Override
    public Occupation getOccupation(String OccupationItem) {
        return IOccupationDatasources.getOccupation(OccupationItem);
    }

    @Override
    public void emptyOccupation() {
        IOccupationDatasources.emptyOccupation();
    }

    @Override
    public void updateLanguage(String lang) {
        IOccupationDatasources.updateLanguage(lang);
    }

    @Override
    public int size() {
        return IOccupationDatasources.size();
    }

    @Override
    public Occupation getOccupationNo(String OccupationItem) {
        return IOccupationDatasources.getOccupationNo(OccupationItem);
    }

    @Override
    public void insertToOccupation(Occupation... Occupations) {
        IOccupationDatasources.insertToOccupation(Occupations);
    }

    @Override
    public void updateOccupation(Occupation... Occupations) {
        IOccupationDatasources.updateOccupation(Occupations);
    }

    @Override
    public void deleteOccupation(Occupation... Occupations) {
        IOccupationDatasources.deleteOccupation(Occupations);
    }
}

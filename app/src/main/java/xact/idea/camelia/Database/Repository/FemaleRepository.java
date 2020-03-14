package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IFemaleDataSources;
import xact.idea.camelia.Database.Model.Female;

public class FemaleRepository implements IFemaleDataSources {
    public IFemaleDataSources IFemaleDataSources;
    public FemaleRepository(IFemaleDataSources IFemaleDataSources){
        this.IFemaleDataSources=IFemaleDataSources;
    }
    private static  FemaleRepository instance;

    public static FemaleRepository getInstance(IFemaleDataSources iCartDataSource){
        if(instance==null)
            instance= new FemaleRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Female>> getFemaleItems() {
        return IFemaleDataSources.getFemaleItems();
    }

    @Override
    public Flowable<List<Female>> getFemaleItemById(int BookItemId) {
        return IFemaleDataSources.getFemaleItemById(BookItemId);
    }

    @Override
    public Female getFemale(String FemaleItem) {
        return IFemaleDataSources.getFemale(FemaleItem);
    }

    @Override
    public void emptyFemale() {
        IFemaleDataSources.emptyFemale();
    }

    @Override
    public int size() {
        return IFemaleDataSources.size();
    }

    @Override
    public void updateLanguage(String lang) {
        IFemaleDataSources.updateLanguage(lang);
    }

    @Override
    public Female getFemaleNo(String FemaleItem) {
        return IFemaleDataSources.getFemaleNo(FemaleItem);
    }

    @Override
    public void insertToFemale(Female... Female) {
        IFemaleDataSources.insertToFemale(Female);
    }

    @Override
    public void updateFemale(Female... Female) {
        IFemaleDataSources.updateFemale(Female);
    }

    @Override
    public void deleteFemale(Female... Female) {
        IFemaleDataSources.deleteFemale(Female);
    }
}

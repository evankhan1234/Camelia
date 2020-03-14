package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IUpazilaDatasources;
import xact.idea.camelia.Database.Model.Upazila;

public class UpazilaRepository implements IUpazilaDatasources {
    public IUpazilaDatasources IUpazilaDatasources;
    public UpazilaRepository(IUpazilaDatasources IUpazilaDatasources){
        this.IUpazilaDatasources=IUpazilaDatasources;
    }
    private static  UpazilaRepository instance;

    public static UpazilaRepository getInstance(IUpazilaDatasources iCartDataSource){
        if(instance==null)
            instance= new UpazilaRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Upazila>> getUpazilaItems() {
        return IUpazilaDatasources.getUpazilaItems();
    }

    @Override
    public Flowable<List<Upazila>> getUpazilaItemById(int UpazilaItemId) {
        return IUpazilaDatasources.getUpazilaItemById(UpazilaItemId);
    }

    @Override
    public Upazila getUpazila(String UpazilaItem) {
        return IUpazilaDatasources.getUpazila(UpazilaItem);
    }

    @Override
    public void emptyUpazila() {
        IUpazilaDatasources.emptyUpazila();
    }

    @Override
    public int size() {
        return IUpazilaDatasources.size();
    }

    @Override
    public void updateLanguage(String lang) {
        IUpazilaDatasources.updateLanguage(lang);
    }

    @Override
    public Upazila getUpazilaNo(String UpazilaItem) {
        return IUpazilaDatasources.getUpazilaNo(UpazilaItem);
    }

    @Override
    public void insertToUpazila(Upazila... Upazilas) {
        IUpazilaDatasources.insertToUpazila(Upazilas);
    }

    @Override
    public void updateUpazila(Upazila... Upazilas) {
        IUpazilaDatasources.updateUpazila(Upazilas);
    }

    @Override
    public void deleteUpazila(Upazila... Upazilas) {
        IUpazilaDatasources.deleteUpazila(Upazilas);
    }
}

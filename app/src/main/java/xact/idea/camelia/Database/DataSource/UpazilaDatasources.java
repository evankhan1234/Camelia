package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.UpazilaDao;
import xact.idea.camelia.Database.DataSourcesInterface.IUpazilaDatasources;
import xact.idea.camelia.Database.Model.Upazila;

public class UpazilaDatasources implements IUpazilaDatasources {

    private UpazilaDao UpazilaDao;
    private static UpazilaDatasources instance;

    public UpazilaDatasources(UpazilaDao UpazilaDao){
        this.UpazilaDao=UpazilaDao;
    }
    public static UpazilaDatasources getInstance(UpazilaDao UpazilaDao){
        if(instance==null)
            instance = new UpazilaDatasources(UpazilaDao);
        return instance;

    }

    @Override
    public Flowable<List<Upazila>> getUpazilaItems() {
        return UpazilaDao.getUpazilaItems();
    }

    @Override
    public Flowable<List<Upazila>> getUpazilaItemById(int UpazilaItemId) {
        return UpazilaDao.getDivisionItemById(UpazilaItemId);
    }

    @Override
    public Upazila getUpazila(String UpazilaItem) {
        return UpazilaDao.getUpazila(UpazilaItem);
    }

    @Override
    public void emptyUpazila() {
        UpazilaDao.emptyUpazila();
    }

    @Override
    public int size() {
        return UpazilaDao.value();
    }

    @Override
    public Upazila getUpazilaNo(String UpazilaItem) {
        return UpazilaDao.getUpazilaNo(UpazilaItem);
    }

    @Override
    public void insertToUpazila(Upazila... Upazilas) {
        UpazilaDao.insertToUpazila(Upazilas);
    }

    @Override
    public void updateUpazila(Upazila... Upazilas) {
        UpazilaDao.updateUpazila(Upazilas);
    }

    @Override
    public void deleteUpazila(Upazila... Upazilas) {
        UpazilaDao.deleteUpazila(Upazilas);
    }
}

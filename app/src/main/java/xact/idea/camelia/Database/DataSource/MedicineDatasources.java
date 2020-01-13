package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MedicineDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMedicineDataSources;
import xact.idea.camelia.Database.Model.Medicine;

public class MedicineDatasources implements IMedicineDataSources {
    private MedicineDao MedicineDao;
    private static MedicineDatasources instance;

    public MedicineDatasources(MedicineDao MedicineDao){
        this.MedicineDao=MedicineDao;
    }
    public static MedicineDatasources getInstance(MedicineDao MedicineDao){
        if(instance==null)
            instance = new MedicineDatasources(MedicineDao);
        return instance;

    }
    @Override
    public Flowable<List<Medicine>> getMedicineItems() {
        return MedicineDao.getMedicineItems();
    }

    @Override
    public Flowable<List<Medicine>> getMedicineItemById(int MedicineItemId) {
        return MedicineDao.getMedicineItemById(MedicineItemId);
    }

    @Override
    public Medicine getMedicine(String MedicineItem) {
        return MedicineDao.getMedicine(MedicineItem);
    }

    @Override
    public void emptyMedicine() {
        MedicineDao.emptyMedicine();
    }

    @Override
    public int size() {
        return MedicineDao.value();
    }

    @Override
    public Medicine getMedicineNo(String MedicineItem) {
        return MedicineDao.getMedicineNo(MedicineItem);
    }

    @Override
    public void insertToMedicine(Medicine... Medicine) {
        MedicineDao.insertToMedicine(Medicine);
    }

    @Override
    public void updateMedicine(Medicine... Medicine) {
        MedicineDao.updateMedicine(Medicine);
    }

    @Override
    public void deleteMedicine(Medicine... Medicine) {
        MedicineDao.deleteMedicine(Medicine);
    }
}

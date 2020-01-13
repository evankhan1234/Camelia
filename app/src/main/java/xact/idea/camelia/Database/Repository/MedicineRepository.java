package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMedicineDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.IMedicineDataSources;
import xact.idea.camelia.Database.Model.Medicine;

public class MedicineRepository implements IMedicineDataSources {
    public IMedicineDataSources IMedicineDataSources;
    public MedicineRepository(IMedicineDataSources IMedicineDataSources){
        this.IMedicineDataSources=IMedicineDataSources;
    }
    private static  MedicineRepository instance;

    public static MedicineRepository getInstance(IMedicineDataSources iCartDataSource){
        if(instance==null)
            instance= new MedicineRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<Medicine>> getMedicineItems() {
        return IMedicineDataSources.getMedicineItems();
    }

    @Override
    public Flowable<List<Medicine>> getMedicineItemById(int MedicineItemId) {
        return IMedicineDataSources.getMedicineItemById(MedicineItemId);
    }

    @Override
    public Medicine getMedicine(String MedicineItem) {
        return IMedicineDataSources.getMedicine(MedicineItem);
    }

    @Override
    public void emptyMedicine() {
        IMedicineDataSources.emptyMedicine();
    }

    @Override
    public int size() {
        return IMedicineDataSources.size();
    }

    @Override
    public Medicine getMedicineNo(String MedicineItem) {
        return IMedicineDataSources.getMedicineNo(MedicineItem);
    }

    @Override
    public void insertToMedicine(Medicine... Medicine) {
        IMedicineDataSources.insertToMedicine(Medicine);
    }

    @Override
    public void updateMedicine(Medicine... Medicine) {
        IMedicineDataSources.updateMedicine(Medicine);
    }

    @Override
    public void deleteMedicine(Medicine... Medicine) {
        IMedicineDataSources.deleteMedicine(Medicine);
    }
}

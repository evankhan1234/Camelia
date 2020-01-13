package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Medicine;

public interface IMedicineDataSources {
    Flowable<List<Medicine>> getMedicineItems();

    Flowable<List<Medicine>> getMedicineItemById(int MedicineItemId);

    Medicine getMedicine(String MedicineItem);

    void emptyMedicine();

    int size();

    Medicine getMedicineNo(String MedicineItem);

    void insertToMedicine(Medicine... Medicine);

    void updateMedicine(Medicine... Medicine);

    void deleteMedicine(Medicine... Medicine);
}

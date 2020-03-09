package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MeasurementDetails;

@Dao
public interface MeasurementDetailsDao {
    @Query("SELECT * FROM MeasurementDetails")
    Flowable<List<MeasurementDetails>> getMeasurementsItems();
    @Query("SELECT * FROM MeasurementDetails WHERE MeasurementId=:MeasurementsId")
    Flowable<List<MeasurementDetails>> getMeasurementsItemById(int MeasurementsId);
    @Query("SELECT * FROM MeasurementDetails WHERE id=:MeasurementsItem")
    MeasurementDetails getMeasurements(String MeasurementsItem);
    @Query("SELECT * FROM MeasurementDetails WHERE MeasurementId=:MeasurementsItem")
    MeasurementDetails getMeasurementsNo(String MeasurementsItem);
    @Query("Select Count(id)  FROM MeasurementDetails")
    int value();
    @Query("Select Max(id)  FROM MeasurementDetails")
    int maxValue();
    @Query("DELETE  FROM MeasurementDetails")
    void emptyMeasurements();
    @Insert
    void insertToMeasurements(MeasurementDetails...MeasurementDetails);
    @Update
    void updateMeasurements(MeasurementDetails...MeasurementDetails);
    @Delete
    void deleteMeasurements(MeasurementDetails...MeasurementDetails);

}

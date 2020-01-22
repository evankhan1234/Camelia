package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Measurements;

@Dao
public interface MeasurementsDao {
    @Query("SELECT * FROM Measurements")
    Flowable<List<Measurements>> getMeasurementsItems();
    @Query("SELECT * FROM Measurements WHERE Type=:MeasurementsId")
    Flowable<List<Measurements>> getMeasurementsItemById(String MeasurementsId);
    @Query("SELECT * FROM Measurements WHERE id=:MeasurementsItem")
    Measurements getMeasurements(String MeasurementsItem);
    @Query("SELECT * FROM Measurements WHERE id=:MeasurementsItem")
    Measurements getMeasurementsNo(String MeasurementsItem);
    @Query("Select Count(id)  FROM Measurements")
    int value();
    @Query("Select Max(id)  FROM Measurements")
    int maxValue();
    @Query("DELETE  FROM Measurements")
    void emptyMeasurements();
    @Insert
    void insertToMeasurements(Measurements...Measurements);
    @Update
    void updateMeasurements(Measurements...Measurements);
    @Delete
    void deleteMeasurements(Measurements...Measurements);
}

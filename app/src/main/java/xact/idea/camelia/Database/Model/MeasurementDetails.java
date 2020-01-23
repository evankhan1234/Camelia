package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "MeasurementDetails")
public class MeasurementDetails {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MeasurementId")
    public int MeasurementId;
    @ColumnInfo(name = "DateTime")
    public Date DateTime;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Result")
    public double Result;
}

package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Visit")
public class Visit {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "RefId")
    public String RefId;
    @ColumnInfo(name = "VisitStatus")
    public String VisitStatus;
    @ColumnInfo(name = "VisitDate")
    public String VisitDate;
    @ColumnInfo(name = "Created")
    public String Created;
}

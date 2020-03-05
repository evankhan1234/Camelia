package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ReferHistory")
public class ReferHistory {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberId")
    public String MemberId;
    @ColumnInfo(name = "From")
    public String From;
    @ColumnInfo(name = "To")
    public String To;
    @ColumnInfo(name = "VisitDate")
    public String VisitDate;

}

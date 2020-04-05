package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "ReferHistory")
public class ReferHistory {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    public int ids;
    @ColumnInfo(name = "MemberUniqueCode")
    public String MemberUniqueCode;
    @ColumnInfo(name = "UniqueId")
    public String UniqueId;
    @ColumnInfo(name = "From")
    public String From;
    @ColumnInfo(name = "FromId")
    public String FromId;
    @ColumnInfo(name = "To")
    public String To;
    @ColumnInfo(name = "ToId")
    public String ToId;
    @ColumnInfo(name = "VisitDate")
    public String VisitDate;
    @ColumnInfo(name = "Type")
    public String Type;
    @ColumnInfo(name = "Date")
    public Date Date;
    @ColumnInfo(name = "Reason")
    public String Reason;
    @ColumnInfo(name = "UpdateNo")
    public String UpdateNo;

}

package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Measurements")
public class Measurements {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberIds")
    public String MemberIds;
    @ColumnInfo(name = "DateTime")
    public Date DateTime;
    @ColumnInfo(name = "Type")
    public String Type;
    @ColumnInfo(name = "Result")
    public double Result;
    @ColumnInfo(name = "Message")
    public String Message;
    @ColumnInfo(name = "Refer")
    public String Refer;

}

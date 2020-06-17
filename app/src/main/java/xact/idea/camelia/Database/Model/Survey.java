package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Survey")
public class Survey {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "Date")
    public String Date;
    @ColumnInfo(name = "CreatedDate")
    public Date CreatedDate;
    @ColumnInfo(name = "SafeDrinkingYesNo")
    public int SafeDrinkingYesNo;
    @ColumnInfo(name = "SafeDrinkingDetails")
    public int SafeDrinkingDetails;
    @ColumnInfo(name = "SanitaryYesNo")
    public int SanitaryYesNo;
    @ColumnInfo(name = "BondhoChulaYesNo")
    public int BondhoChulaYesNo;
    @ColumnInfo(name = "BiomasFuelYesNo")
    public int BiomasFuelYesNo;
    @ColumnInfo(name = "BiomasFuelDetails")
    public int BiomasFuelDetails;
    @ColumnInfo(name = "UniqueId")
    public String UniqueId;
}

package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "HouseHold")
public class HouseHold {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberId")
    public int MemberId;
    @ColumnInfo(name = "DivisionId")
    public int DivisionId;
    @ColumnInfo(name = "DistrictId")
    public int DistrictId;
    @ColumnInfo(name = "UpazilaId")
    public int UpazilaId;
    @ColumnInfo(name = "UnionId")
    public int UnionId;
    @ColumnInfo(name = "BlockId")
    public int BlockId;
    @ColumnInfo(name = "WordId")
    public int WordId;
    @ColumnInfo(name = "HH")
    public int HH;
    @ColumnInfo(name = "SHH")
    public int SHH;
    @ColumnInfo(name = "UniqueId")
    public int UniqueId;
    @ColumnInfo(name = "FamilyMember")
    public int FamilyMember;
    @ColumnInfo(name = "FamilyIncome")
    public double FamilyIncome;

    @ColumnInfo(name = "DateValue")
    public String DateValue;
    @ColumnInfo(name = "Date")
    public Date Date;
    @ColumnInfo(name = "VillageName")
    public String VillageName;

}

package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "MemberMyself")
public class MemberMyself {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "NationalId")
    public String NationalId;
    @ColumnInfo(name = "MemberId")
    public String MemberId;
    @ColumnInfo(name = "MobileNumber")
    public String MobileNumber;
    @ColumnInfo(name = "HouseHeadId")
    public int HouseHeadId;
    @ColumnInfo(name = "FullName")
    public String FullName;
    @ColumnInfo(name = "DateOfBirth")
    public String DateOfBirth;
    @ColumnInfo(name = "CreatedDate")
    public Date CreatedDate;
    @ColumnInfo(name = "GenderId")
    public int GenderId;
    @ColumnInfo(name = "ReligionId")
    public int ReligionId;
    @ColumnInfo(name = "StudyId")
    public int StudyId;
    @ColumnInfo(name = "MaritialId")
    public int MaritialId;
    @ColumnInfo(name = "OccupationId")
    public int OccupationId;
    @ColumnInfo(name = "BloodGroupId")
    public int BloodGroupId;
    @ColumnInfo(name = "LivingId")
    public int LivingId;
    @ColumnInfo(name = "DateOfDeath")
    public String DateOfDeath;
    @ColumnInfo(name = "UniqueId")
    public String UniqueId;
    @ColumnInfo(name = "UniqueCode")
    public String UniqueCode;
    @ColumnInfo(name = "VisitDate")
    public String VisitDate;
    @ColumnInfo(name = "From")
    public String From;
    @ColumnInfo(name = "To")
    public String To;
    @ColumnInfo(name = "Status")
    public String Status;
}

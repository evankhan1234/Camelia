package xact.idea.camelia.Database.AnotherModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Members {

    @SerializedName("NationalId")
    public int NationalId;
    @SerializedName("MobileNumber")
    public String MobileNumber;
    @SerializedName("HouseHeadId")
    public int HouseHeadId;
    @SerializedName("FullName")
    public String FullName;
    @SerializedName("DateOfBirth")
    public String DateOfBirth;
    @SerializedName("CreatedDate")
    public Date CreatedDate;
    @SerializedName("GenderId")
    public int GenderId;
    @SerializedName("ReligionId")
    public int ReligionId;
    @SerializedName("StudyId")
    public int StudyId;
    @SerializedName("MaritialId")
    public int MaritialId;
    @SerializedName("OccupationId")
    public int OccupationId;
    @SerializedName("BloodGroupId")
    public int BloodGroupId;
    @SerializedName("LivingId")
    public int LivingId;
    @SerializedName("DateOfDeath")
    public String DateOfDeath;
    @SerializedName("UniqueId")
    public String UniqueId;
}

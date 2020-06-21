package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "MemberMedicine")
public class MemberMedicine {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberId")
    public String MemberId;
    @SerializedName("household_uniqe_id")
    public String household_uniqe_id;
    @SerializedName("member_national_id")
    public String member_national_id;
    @SerializedName("member_unique_code")
    public String member_unique_code;
    @SerializedName("note")
    public String note;
    @SerializedName("status")
    public String status;
    @ColumnInfo(name = "CurrentDate")
    public String CurrentDate;
}

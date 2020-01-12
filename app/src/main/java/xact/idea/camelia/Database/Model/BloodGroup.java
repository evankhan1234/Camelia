package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BloodGroup")
public class BloodGroup {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "BloodId")
    public int BloodId;
    @ColumnInfo(name = "blood_group_name_en")
    public String blood_group_name_en;
    @ColumnInfo(name = "blood_group_name_bn")
    public String blood_group_name_bn;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
}

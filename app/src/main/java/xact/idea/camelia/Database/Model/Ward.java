package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ward")
public class Ward {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "WardId")
    public int WardId;
    @ColumnInfo(name = "ward_name_en")
    public String ward_name_en;
    @ColumnInfo(name = "ward_name_bn")
    public String ward_name_bn;
    @ColumnInfo(name = "ward_shortname_en")
    public String ward_shortname_en;
    @ColumnInfo(name = "ward_shortname_bn")
    public String ward_shortname_bn;
    @ColumnInfo(name = "ward_code")
    public String ward_code;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
    @Override
    public String toString() {
        return ward_name_en;

    }
}

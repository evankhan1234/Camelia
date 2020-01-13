package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Unions")
public class Unions {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "UnionId")
    public int UnionId;
    @ColumnInfo(name = "upazila_id")
    public int upazila_id;
    @ColumnInfo(name = "union_name_en")
    public String union_name_en;
    @ColumnInfo(name = "union_name_bn")
    public String union_name_bn;
    @ColumnInfo(name = "union_shortname_en")
    public String union_shortname_en;
    @ColumnInfo(name = "union_shortname_bn")
    public String union_shortname_bn;
    @ColumnInfo(name = "union_code")
    public String union_code;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
    @Override
    public String toString() {
        return union_name_en;

    }
}

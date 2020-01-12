package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Female")
public class Female {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "FemaleId")
    public int FemaleId;
    @ColumnInfo(name = "gender_name_en")
    public String gender_name_en;
    @ColumnInfo(name = "gender_name_bn")
    public String gender_name_bn;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
}

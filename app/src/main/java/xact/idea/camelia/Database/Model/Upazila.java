package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Upazila")
public class Upazila {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "UpazilaId")
    public int UpazilaId;
    @ColumnInfo(name = "district_id")
    public int district_id;
    @ColumnInfo(name = "upazila_name_en")
    public String upazila_name_en;
    @ColumnInfo(name = "upazila_name_bn")
    public String upazila_name_bn;
    @ColumnInfo(name = "upazila_shortname_en")
    public String upazila_shortname_en;
    @ColumnInfo(name = "upazila_shortname_bn")
    public String upazila_shortname_bn;
    @ColumnInfo(name = "upazila_code")
    public String upazila_code;
    @ColumnInfo(name = "code")
    public String code;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "ln")
    public String ln;
    @Override
    public String toString() {
        if (ln!=null){
            if (ln.equals("bn")){
                return upazila_name_bn;
            }
            else{
                return upazila_name_en;
            }
        }
        else{
            return upazila_name_en;
        }


    }
}

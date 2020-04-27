package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Division")
public class Division {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "DivisionId")
    public int DivisionId;
    @ColumnInfo(name = "division_name_en")
    public String division_name_en;
    @ColumnInfo(name = "division_name_bn")
    public String division_name_bn;
    @ColumnInfo(name = "division_shortname_en")
    public String division_shortname_en;
    @ColumnInfo(name = "division_shortname_bn")
    public String division_shortname_bn;
    @ColumnInfo(name = "division_code")
    public String division_code;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "code")
    public String code;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "ln")
    public String ln;
    @Override
    public String toString() {
        if (ln!=null){
            if (ln.equals("bn")){
                return division_name_bn;
            }
            else{
                return division_name_en;
            }
        }
        else{
            return division_name_en;
        }


    }
}

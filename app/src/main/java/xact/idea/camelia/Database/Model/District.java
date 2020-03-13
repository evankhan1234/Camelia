package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "District")
public class District {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "DistrictId")
    public int DistrictId;
    @ColumnInfo(name = "DivisionId")
    public int DivisionId;
    @ColumnInfo(name = "district_name_en")
    public String district_name_en;
    @ColumnInfo(name = "district_name_bn")
    public String district_name_bn;
    @ColumnInfo(name = "district_shortname_en")
    public String district_shortname_en;
    @ColumnInfo(name = "district_shortname_bn")
    public String district_shortname_bn;
    @ColumnInfo(name = "district_code")
    public String district_code;
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
                return district_name_bn;
            }
            else{
                return district_name_en;
            }
        }
        else{
            return district_name_en;
        }


    }
}

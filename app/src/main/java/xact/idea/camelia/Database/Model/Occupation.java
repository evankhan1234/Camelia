package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Occupation")
public class Occupation {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "OccupationId")
    public int OccupationId;
    @ColumnInfo(name = "occupation_name_en")
    public String occupation_name_en;
    @ColumnInfo(name = "occupation_name_bn")
    public String occupation_name_bn;
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
                return occupation_name_bn;
            }
            else{
                return occupation_name_en;
            }
        }
        else{
            return occupation_name_en;
        }


    }
}

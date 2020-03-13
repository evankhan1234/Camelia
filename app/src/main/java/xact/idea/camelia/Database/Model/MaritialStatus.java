package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MaritialStatus")
public class MaritialStatus {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MaritialId")
    public int MaritialId;
    @ColumnInfo(name = "marital_name_en")
    public String marital_name_en;
    @ColumnInfo(name = "marital_name_bn")
    public String marital_name_bn;
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
                return marital_name_bn;
            }
            else{
                return marital_name_en;
            }
        }
        else{
            return marital_name_en;
        }


    }
}

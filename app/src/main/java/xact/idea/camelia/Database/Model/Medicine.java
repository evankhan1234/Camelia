package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import xact.idea.camelia.NetworkModel.MedicineResponses;

@Entity(tableName = "Medicine")
public class Medicine {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MedicineId")
    public int MedicineId;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "short_name")
    public String short_name;
    @ColumnInfo(name = "note")
    public String note;
    @ColumnInfo(name = "group_type_id")
    public String group_type_id;
    @ColumnInfo(name = "disease")
    public String disease;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "group_id")
    public int group_id;
    @ColumnInfo(name = "group_name")
    public String group_name;
    @ColumnInfo(name = "group_short_name")
    public String group_short_name;
    @ColumnInfo(name = "group_note")
    public String group_note;
    @ColumnInfo(name = "group_status")
    public String group_status;
  
}

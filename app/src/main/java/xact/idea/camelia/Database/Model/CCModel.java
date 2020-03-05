package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CCModel")
public class CCModel {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "CCId")
    public int CCId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "short_name")
    public String short_name;
    @ColumnInfo(name = "information")
    public String information;
    @ColumnInfo(name = "division_code")
    public int division_code;
    @ColumnInfo(name = "district_code")
    public int district_code;
    @ColumnInfo(name = "upazila_code")
    public int upazila_code;
    @ColumnInfo(name = "union_code")
    public int union_code;
    @ColumnInfo(name = "ward_code")
    public int ward_code;
    @ColumnInfo(name = "block_code")
    public int block_code;
    @ColumnInfo(name = "status")
    public int status;


}

package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UHC")
public class UHC {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "UHCId")
    public int UHCId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "code")
    public String code;
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

    @Override
    public String toString() {
        return  name ;
    }
}

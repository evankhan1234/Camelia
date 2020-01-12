package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Block")
public class Block {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "BlockId")
    public int BlockId;
    @ColumnInfo(name = "block_name_en")
    public String block_name_en;
    @ColumnInfo(name = "block_name_bn")
    public String block_name_bn;
    @ColumnInfo(name = "block_shortname_en")
    public String block_shortname_en;
    @ColumnInfo(name = "block_shortname_bn")
    public String block_shortname_bn;
    @ColumnInfo(name = "block_code")
    public String block_code;
    @ColumnInfo(name = "note_en")
    public String note_en;
    @ColumnInfo(name = "note_bn")
    public String note_bn;
    @ColumnInfo(name = "status")
    public String status;
}

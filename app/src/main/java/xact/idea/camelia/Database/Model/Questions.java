package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Questions")
public class Questions {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "master_id")
    public int master_id;
    @ColumnInfo(name = "member_id")
    public String member_id;
    @ColumnInfo(name = "question")
    public String question;
    @ColumnInfo(name = "answer")
    public String answer;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "type")
    public String type;
}

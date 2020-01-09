package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Auth")
public class Auth {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "AuthId")
    public int AuthId;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "dob")
    public String dob;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "password")
    public String password;
}

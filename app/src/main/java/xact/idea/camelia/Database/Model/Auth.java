package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

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
    @ColumnInfo(name = "fullname")
    public String fullname;
    @ColumnInfo(name = "role_name")
    public String role_name;
    @ColumnInfo(name = "user_role")
    public int user_role;
    @ColumnInfo(name = "division")
    public String  division;
    @ColumnInfo(name = "district")
    public String  district;
    @ColumnInfo(name = "upazila")
    public String  upazila;
    @ColumnInfo(name = "union")
    public String  union;
    @ColumnInfo(name = "block")
    public String  block;
    @ColumnInfo(name = "ward")
    public String  ward;
    @ColumnInfo(name = "village")
    public String  village;
    @ColumnInfo(name = "role_code")
    public String  role_code;

}

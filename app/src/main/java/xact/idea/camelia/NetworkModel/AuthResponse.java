package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class AuthResponse extends ApiResponses {

    @SerializedName("data")
    public Data  data;



    public class Data{
        @SerializedName("fullname")
        public String fullname;
        @SerializedName("role_name")
        public String role_name;
        @SerializedName("user_role")
        public String user_role;
        @SerializedName("user_id")
        public String  user_id;
        @SerializedName("user_email")
        public String  user_email;
        @SerializedName("role_code")
        public String  role_code;
        @SerializedName("profile")
        public Profile profile;
        @SerializedName("working_area")
        public WorkingArea working_area;
        public class Profile{
            @SerializedName("id")
            public String  id;
            @SerializedName("address")
            public String  address;
            @SerializedName("gender")
            public String  gender;
            @SerializedName("image")
            public String  image;
            @SerializedName("phone")
            public String  phone;
            @SerializedName("dob")
            public String  dob;
            @SerializedName("city")
            public String  city;


        }
        public class WorkingArea{
            @SerializedName("division")
            public String  division;
            @SerializedName("district")
            public String  district;
            @SerializedName("upazila")
            public String  upazila;
            @SerializedName("union")
            public String  union;
            @SerializedName("block")
            public String  block;
            @SerializedName("ward")
            public String  ward;
            @SerializedName("village")
            public String  village;


        }
    }
}

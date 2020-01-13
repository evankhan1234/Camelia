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
        @SerializedName("workingarea")
        public WorkingArea workingarea;
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
            @SerializedName("division_code")
            public String  division_code;
            @SerializedName("district_code")
            public String  district_code;
            @SerializedName("upazila_code")
            public String  upazila_code;
            @SerializedName("union_code")
            public String  union_code;
            @SerializedName("block_code")
            public String  block_code;
            @SerializedName("ward_code")
            public String  ward_code;
            @SerializedName("village")
            public String  village;


        }
    }
}

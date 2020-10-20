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
            @SerializedName("user_id")
            public String  user_id;
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
            @SerializedName("division_id")
            public String  division_id;
            @SerializedName("district_id")
            public String  district_id;
            @SerializedName("upazila_id")
            public String  upazila_id;
            @SerializedName("union_id")
            public String  union_id;
            @SerializedName("block_id")
            public String  block_id;
            @SerializedName("ward_id")
            public String  ward_id;
            @SerializedName("village")
            public String  village;
            @SerializedName("work_station")
            public String  work_station;


        }
    }
}

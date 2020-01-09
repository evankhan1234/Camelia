package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class AuthResponse extends ApiResponses {

    @SerializedName("data")
    public Data  data;



    public class Data{
        @SerializedName("profile")
        public Profile profile;
        public class Profile{
            @SerializedName("id")
            public int  id;
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
            @SerializedName("country_id")
            public String  country_id;

        }
    }
}

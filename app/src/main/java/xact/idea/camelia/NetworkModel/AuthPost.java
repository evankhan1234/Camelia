package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class AuthPost {
    @SerializedName("email")
    public String  email;
    @SerializedName("password")
    public String  password;
}

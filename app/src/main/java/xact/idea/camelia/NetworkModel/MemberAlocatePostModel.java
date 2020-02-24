package xact.idea.camelia.NetworkModel;


import com.google.gson.annotations.SerializedName;

public class MemberAlocatePostModel {
    @SerializedName("user_credential")
    public String user_credential;

    @SerializedName("data")
    public Test data = new Test();


}


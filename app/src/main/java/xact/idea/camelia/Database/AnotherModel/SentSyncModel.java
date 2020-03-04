package xact.idea.camelia.Database.AnotherModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SentSyncModel {
    @SerializedName("FullName")
    public String FullName;
    @SerializedName("CreatedDate")
    public Date CreatedDate;
    @SerializedName("MemberId")
    public String MemberId;
    @SerializedName("UniqueId")
    public String UniqueId;
    @SerializedName("VillageName")
    public String VillageName;
}

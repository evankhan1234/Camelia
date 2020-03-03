package xact.idea.camelia.Database.AnotherModel;

import com.google.gson.annotations.SerializedName;

public class SentSyncModel {
    @SerializedName("FullName")
    public int FullName;
    @SerializedName("CreatedDate")
    public String CreatedDate;
    @SerializedName("MemberId")
    public int MemberId;
    @SerializedName("UniqueId")
    public String UniqueId;
    @SerializedName("VillageName")
    public String VillageName;
}

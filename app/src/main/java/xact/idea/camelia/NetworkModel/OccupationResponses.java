package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class OccupationResponses extends ApiResponses{
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {


    }
}

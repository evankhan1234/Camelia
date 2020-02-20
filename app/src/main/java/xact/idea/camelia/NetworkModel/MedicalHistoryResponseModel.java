package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class MedicalHistoryResponseModel extends ApiResponses {
    @SerializedName("total_operated_records")
    public int total_operated_records;

    @SerializedName("total_request_records")
    public int total_request_records;
}

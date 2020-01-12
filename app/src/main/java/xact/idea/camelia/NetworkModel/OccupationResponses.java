package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OccupationResponses extends ApiResponses{
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("occupation_name_en")
        public String occupation_name_en;
        @SerializedName("occupation_name_bn")
        public String occupation_name_bn;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }
}

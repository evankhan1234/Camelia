package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UHCModel extends  ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("code")
        public String code;
        @SerializedName("information")
        public String information;
        @SerializedName("division_id")
        public int division_code;
        @SerializedName("district_id")
        public int district_code;
        @SerializedName("upazila_id")
        public int upazila_code;
        @SerializedName("union_id")
        public int union_code;
        @SerializedName("ward_id")
        public int ward_code;
        @SerializedName("block_id")
        public int block_code;
        @SerializedName("status")
        public int status;
    }
}

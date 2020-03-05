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
        @SerializedName("division_code")
        public int division_code;
        @SerializedName("district_code")
        public int district_code;
        @SerializedName("upazila_code")
        public int upazila_code;
        @SerializedName("union_code")
        public int union_code;
        @SerializedName("ward_code")
        public int ward_code;
        @SerializedName("block_code")
        public int block_code;
        @SerializedName("status")
        public int status;
    }
}

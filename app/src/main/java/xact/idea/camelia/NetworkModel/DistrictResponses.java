package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class DistrictResponses extends ApiResponses {

    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("DivisionId")
        public int DivisionId;
        @SerializedName("district_name_en")
        public String district_name_en;
        @SerializedName("district_name_bn")
        public String district_name_bn;
        @SerializedName("district_shortname_en")
        public String district_shortname_en;
        @SerializedName("district_shortname_bn")
        public String district_shortname_bn;
        @SerializedName("district_code")
        public String district_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }
}

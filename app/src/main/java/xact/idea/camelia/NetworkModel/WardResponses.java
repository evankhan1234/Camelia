package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class WardResponses extends ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("WardId")
        public int WardId;
        @SerializedName("ward_name_en")
        public String ward_name_en;
        @SerializedName("ward_name_bn")
        public String ward_name_bn;
        @SerializedName("ward_shortname_en")
        public String ward_shortname_en;
        @SerializedName("ward_shortname_bn")
        public String ward_shortname_bn;
        @SerializedName("ward_code")
        public String ward_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }
}

package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class BlockResponses extends ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("total_record")
        public int id;
        @SerializedName("total_record")
        public String block_name_en;
        @SerializedName("total_record")
        public String block_name_bn;
        @SerializedName("total_record")
        public String block_shortname_en;
        @SerializedName("block_shortname_bn")
        public String block_shortname_bn;
        @SerializedName("block_code")
        public String block_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }

}


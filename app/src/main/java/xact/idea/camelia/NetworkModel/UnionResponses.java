package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UnionResponses extends ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {

        @SerializedName("id")
        public int id;
        @SerializedName("UnionId")
        public int UnionId;
        @SerializedName("upazila_id")
        public int upazila_id;
        @SerializedName("union_name_en")
        public String union_name_en;
        @SerializedName("union_name_bn")
        public String union_name_bn;
        @SerializedName("union_shortname_en")
        public String union_shortname_en;
        @SerializedName("union_shortname_bn")
        public String union_shortname_bn;
        @SerializedName("union_code")
        public String union_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;
    }
}

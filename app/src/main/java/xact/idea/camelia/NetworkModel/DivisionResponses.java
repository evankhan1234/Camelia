package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class DivisionResponses extends ApiResponses{
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("division_name_en")
        public String division_name_en;
        @SerializedName("division_name_bn")
        public String division_name_bn;
        @SerializedName("division_shortname_en")
        public String division_shortname_en;
        @SerializedName("division_shortname_bn")
        public String division_shortname_bn;
        @SerializedName("division_code")
        public String division_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }
}

package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class GenderResponses extends ApiResponses {

    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public Data data;

    public class Data {

        @SerializedName("id")
        public int id;
        @SerializedName("blood_group_name_en")
        public String blood_group_name_en;
        @SerializedName("blood_group_name_bn")
        public String blood_group_name_bn;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;
    }

}

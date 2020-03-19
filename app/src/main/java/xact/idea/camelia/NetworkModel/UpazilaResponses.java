package xact.idea.camelia.NetworkModel;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpazilaResponses extends ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("UpazilaId")
        public int UpazilaId;
        @SerializedName("district_id")
        public int district_id;
        @SerializedName("district_code")
        public int district_code;
        @SerializedName("upazila_name_en")
        public String upazila_name_en;
        @SerializedName("upazila_name_bn")
        public String upazila_name_bn;
        @SerializedName("upazila_shortname_en")
        public String upazila_shortname_en;
        @SerializedName("upazila_shortname_bn")
        public String upazila_shortname_bn;
        @SerializedName("upazila_code")
        public String upazila_code;
        @SerializedName("note_en")
        public String note_en;
        @SerializedName("note_bn")
        public String note_bn;
        @SerializedName("status")
        public String status;

    }
}

package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LabReportsResponseModel extends ApiResponses {
    @SerializedName("total_record")
    public int total_record;
    @SerializedName("lab_reports")
    public ArrayList<Data> lab_reports;
    @SerializedName("request_data")
    public ArrayList<Visit> request_data;
    public class Data{
        @SerializedName("id")
        public int id;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("title")
        public String title;
        @SerializedName("file")
        public String file;
        @SerializedName("status")
        public String status;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("created_by")
        public String created_by;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("update_no")
        public String update_no;
    }
    public class Visit{
        @SerializedName("unique_code")
        public String unique_code;

    }
}

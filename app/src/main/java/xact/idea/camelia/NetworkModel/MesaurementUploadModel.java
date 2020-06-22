package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MesaurementUploadModel {
    @SerializedName("user_credential")
    public String user_credential;
    @SerializedName("data")
    public ArrayList<Data> data;
    public static class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("datetime")
        public String datetime;
        @SerializedName("type")
        public String type;
        @SerializedName("result")
        public String result;
        @SerializedName("referral_status")
        public String referral_status;
        @SerializedName("result_status")
        public String result_status;
        @SerializedName("message")
        public String message;
        @SerializedName("status")
        public String status;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("update_no")
        public String update_no;
        @SerializedName("created_by")
        public String created_by;
        @SerializedName("measurement_from")
        public String measurement_from;

        @SerializedName("attr_values")
        public ArrayList<AttrValues> attr_values;
        public static class AttrValues {
            @SerializedName("id")
            public int id;
            @SerializedName("measurement_id")
            public String measurement_id;
            @SerializedName("name")
            public String name;
            @SerializedName("value")
            public String value;
            @SerializedName("status")
            public String status;
            @SerializedName("type")
            public String type;
            @SerializedName("created_at")
            public String created_at;
            @SerializedName("created_by")
            public String created_by;
            @SerializedName("update_no")
            public String update_no;
        }
    }
}

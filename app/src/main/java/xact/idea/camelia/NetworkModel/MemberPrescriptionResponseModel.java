package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberPrescriptionResponseModel  {
    @SerializedName("user_credential")
    public String user_credential;

    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data{
        @SerializedName("member_unique_code")
        public String member_unique_code;
        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("from")
        public String from;
        @SerializedName("from_id")
        public String from_id;
        @SerializedName("to")
        public String to;
        @SerializedName("to_id")
        public String to_id;
        @SerializedName("visit_date")
        public String visit_date;
        @SerializedName("reason")
        public String reason;
        @SerializedName("ref_type")
        public String ref_type;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("update_no")
        public String update_no;
        @SerializedName("member_id")
        public String member_id;

    }
}

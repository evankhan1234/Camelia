package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberPrescriptionResponseModel extends ApiResponses {
    @SerializedName("total_record")
    public int total_record;

    @SerializedName("referral_history")
    public ArrayList<Data> referral_history;

    public class Data{
        @SerializedName("id")
        public int id;
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
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("created_by")
        public String created_by;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("update_no")
        public String update_no;

        @SerializedName("visits")
        public ArrayList<Visit> visits;
        public class Visit{
            @SerializedName("id")
            public String id;
            @SerializedName("ref_id")
            public String ref_id;
            @SerializedName("visit_status")
            public String visit_status;
            @SerializedName("status")
            public String status;
            @SerializedName("visit_date")
            public String visit_date;
            @SerializedName("created_at")
            public String created_at;
            @SerializedName("created_by")
            public String created_by;
            @SerializedName("updated_at")
            public String updated_at;
            @SerializedName("update_no")
            public String update_no;
        }
    }
}

package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReferallHistoryPostModel {
    @SerializedName("user_credential")
    public String user_credential;
    @SerializedName("data")
    public ArrayList<MemberBehaviorialUploadModel.Data> data;

    public static class Data {

        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("ref_type")
        public String ref_type;
        @SerializedName("member_unique_code")
        public String member_unique_code;
        @SerializedName("from")
        public String from;
        @SerializedName("from_id")
        public String from_id;
        @SerializedName("to")
        public String to;
        @SerializedName("to_id")
        public String to_id;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("visit_date")
        public String visit_date;
        @SerializedName("update_no")
        public String update_no;
        @SerializedName("details")
        public ArrayList<MemberBehaviorialUploadModel.Data.Details> details;

        public static class Details{
            @SerializedName("id")
            public int id;
            @SerializedName("question_type")
            public String question_type;
            @SerializedName("master_id")
            public int master_id;
            @SerializedName("member_id")
            public String member_id;
            @SerializedName("parent_question")
            public String parent_question;
            @SerializedName("question")
            public String question;
            @SerializedName("answer")
            public String answer;
            @SerializedName("created_at")
            public String created_at;
//            @SerializedName("created_by")
//            public String created_by;
//            @SerializedName("updated_at")
//            public String updated_at;
//            @SerializedName("update_no")
//            public String update_no;

        }
    }
}

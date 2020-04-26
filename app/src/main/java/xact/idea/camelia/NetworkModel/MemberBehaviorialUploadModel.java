package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberBehaviorialUploadModel {
    @SerializedName("user_credential")
    public String user_credential;
    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data {
//        @SerializedName("id")
//        public int id;
        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("member_national_id")
        public String member_national_id;
        @SerializedName("member_unique_code")
        public String member_unique_code;
//        @SerializedName("note")
//        public String note;
        @SerializedName("status")
        public String status;
        @SerializedName("created_at")
        public String created_at;
//        @SerializedName("created_by")
//        public String created_by;
//        @SerializedName("updated_at")
//        public String updated_at;
        @SerializedName("update_no")
        public String update_no;
        @SerializedName("details")
        public ArrayList<Details> details;

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
            @SerializedName("update_no")
            public String update_no;

        }
    }

}

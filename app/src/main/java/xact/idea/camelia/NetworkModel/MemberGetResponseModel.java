package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberGetResponseModel extends ApiResponses {
    @SerializedName("total_record")
    public int total_record;

    @SerializedName("referral_history")
    public ArrayList<Data> referral_history;

    public class Data{
        @SerializedName("id")
        public int id;
        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("unique_code")
        public String unique_code;
        @SerializedName("name")
        public String name;
        @SerializedName("mobile_number")
        public String mobile_number;
        @SerializedName("head_of_house")
        public String natiohead_of_housenal_id;
        @SerializedName("birth_date")
        public String birth_date;
        @SerializedName("date_of_data_collection")
        public String date_of_data_collection;
        @SerializedName("sex")
        public String sex;
        @SerializedName("religion")
        public String religion;
        @SerializedName("education")
        public String education;
        @SerializedName("marital_status")
        public String marital_status;
        @SerializedName("occupation")
        public String occupation;
        @SerializedName("blood_group")
        public String blood_group;
        @SerializedName("living_status")
        public String living_status;
        @SerializedName("death_date")
        public String death_date;
        @SerializedName("referred_to")
        public String referred_to;
        @SerializedName("refer_to_id")
        public String refer_to_id;
        @SerializedName("visit_date")
        public String visit_date;
        @SerializedName("note")
        public String note;
        @SerializedName("status")
        public String status;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("created_by")
        public String created_by;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("updated_by")
        public String updated_by;

        @SerializedName("medical_history_details")
        public ArrayList<Visit> medical_history_details;
        public class Visit{
            @SerializedName("id")
            public int id;
            @SerializedName("master_id")
            public String master_id;
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
            @SerializedName("created_by")
            public String created_by;
            @SerializedName("updated_at")
            public String updated_at;
            @SerializedName("update_no")
            public String update_no;
        }
        @SerializedName("behavioral_info")
        public ArrayList<Behavior> behavioral_info;
        public class Behavior{
            @SerializedName("id")
            public int id;
            @SerializedName("master_id")
            public String master_id;
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
            @SerializedName("created_by")
            public String created_by;
            @SerializedName("updated_at")
            public String updated_at;
            @SerializedName("update_no")
            public String update_no;
        }
    }
}

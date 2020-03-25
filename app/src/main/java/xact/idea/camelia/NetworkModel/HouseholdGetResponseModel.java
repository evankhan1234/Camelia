package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseholdGetResponseModel {
    @SerializedName("total_record")
    public int total_record;
    @SerializedName("khanaSurveys")
    public ArrayList<Details> khanaSurveys;
        public class Details{
            @SerializedName("id")
            public int id;
            @SerializedName("household_uniqe_id")
            public String household_uniqe_id;
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
            @SerializedName("update_no")
            public String update_no;
            @SerializedName("details")
            public ArrayList<Khana> details;

            public class Khana{
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
                @SerializedName("question_type")
                public String question_type;
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

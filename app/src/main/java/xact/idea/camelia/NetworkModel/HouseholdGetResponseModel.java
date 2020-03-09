package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseholdGetResponseModel {
    @SerializedName("total_record")
    public int total_record;
    @SerializedName("householdinfo")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("division_code")
        public String division_code;
        @SerializedName("district_code")
        public String district_code;
        @SerializedName("upazila_code")
        public String upazila_code;
        @SerializedName("union_code")
        public String union_code;
        @SerializedName("ward_code")
        public String ward_code;
        @SerializedName("block_code")
        public String block_code;
        @SerializedName("village")
        public String village;
        @SerializedName("hh_number")
        public String hh_number;
        @SerializedName("sub_hh_number")
        public String sub_hh_number;
        @SerializedName("family_member")
        public String family_member;
        @SerializedName("income_per_month")
        public String income_per_month;
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
        @SerializedName("khanaSurveys")
        public ArrayList<Details> khanaSurveys;

        public class Details{
            @SerializedName("id")
            public int id;
            @SerializedName("household_uniqe_id")
            public int household_uniqe_id;
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
            @SerializedName("khanaSurveys")
            public ArrayList<Khana> khanaSurveys;

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
}

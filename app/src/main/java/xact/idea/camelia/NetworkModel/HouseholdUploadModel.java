package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseholdUploadModel {
    @SerializedName("user_credential")
    public String user_credential;
    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data {
        @SerializedName("household_uniqe_id")
        public String household_uniqe_id;
        @SerializedName("division_id")
        public String division_id;
        @SerializedName("district_id")
        public String district_id;
        @SerializedName("upazila_id")
        public String upazila_id;
        @SerializedName("union_id")
        public String union_id;
        @SerializedName("ward_id")
        public String ward_id;
        @SerializedName("block_id")
        public String block_id;
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
    }
}

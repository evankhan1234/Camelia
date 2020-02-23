package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MeasurementsGetResponseModel extends ApiResponses {
    @SerializedName("total_record")
    public int total_record;
    @SerializedName("measurement_results")
    public ArrayList<Data> measurement_results;
    public class Data{
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
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("update_no")
        public String update_no;
        @SerializedName("values")
        public ArrayList<AttrValues> values;
        public class AttrValues {
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
            @SerializedName("created_at")
            public String created_at;
            @SerializedName("updated_at")
            public String updated_at;
        }

    }
}

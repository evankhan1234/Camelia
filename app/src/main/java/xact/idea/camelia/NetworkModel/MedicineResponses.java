package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MedicineResponses extends ApiResponses {
    @SerializedName("total_record")
    public String total_record;
    @SerializedName("data")
    public ArrayList<Data> data;
    public class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("short_name")
        public String short_name;
        @SerializedName("note")
        public String note;
        @SerializedName("group_type_id")
        public String group_type_id;
        @SerializedName("disease")
        public String disease;
        @SerializedName("status")
        public String status;
        @SerializedName("group_type")
        public GroupType group_type;
            public class GroupType {
            @SerializedName("id")
            public int id;
            @SerializedName("name")
            public String name;
            @SerializedName("short_name")
            public String short_name;
            @SerializedName("note")
            public String note;

            @SerializedName("status")
            public String status;
        }
    }

}

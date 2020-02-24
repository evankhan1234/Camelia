package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberAlocateResponseModel extends ApiResponses {

    @SerializedName("data")
    public Data data;

    public class Data{
        @SerializedName("newly_allocated_member_ids")
        public ArrayList<AllocatedMember> newly_allocated_member_ids;

        public class AllocatedMember{
            @SerializedName("generated_member_id")
            public String generated_member_id;
            @SerializedName("allocate_user_id")
            public String allocate_user_id;
            @SerializedName("used_status")
            public String used_status;
        }
    }
}

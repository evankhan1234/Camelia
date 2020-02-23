package xact.idea.camelia.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberPrescriptionGetResponseModel extends ApiResponses{
    @SerializedName("total_record")
    public int total_record;

    @SerializedName("prescriptions")
    public ArrayList<Data> prescriptions;

    public class Data{
        @SerializedName("id")
        public int id;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("next_visit")
        public String next_visit;
        @SerializedName("pulse_rate")
        public String pulse_rate;
        @SerializedName("dehydration")
        public String dehydration;
        @SerializedName("jaundice")
        public String jaundice;
        @SerializedName("swelling_feet_legs")
        public String swelling_feet_legs;
        @SerializedName("swelling_face_eyes")
        public String swelling_face_eyes;
        @SerializedName("feet_exam")
        public String feet_exam;
        @SerializedName("skin_mouth_genation")
        public String skin_mouth_genation;
        @SerializedName("bp_systolic_diastolic")
        public String bp_systolic_diastolic;
        @SerializedName("heart")
        public String heart;
        @SerializedName("lung")
        public String lung;
        @SerializedName("abdomen")
        public String abdomen;
        @SerializedName("chief_complaints")
        public String chief_complaints;
        @SerializedName("special_notes_findings")
        public String special_notes_findings;
        @SerializedName("diagnosis")
        public String diagnosis;
        @SerializedName("advices")
        public String advices;
        @SerializedName("provisional_diagnosis")
        public String provisional_diagnosis;
        @SerializedName("disease")
        public String disease;
        @SerializedName("investigations")
        public String investigations;
        @SerializedName("investigation_finding")
        public String investigation_finding;
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
        @SerializedName("visits")
        public ArrayList<Visit> visits;
        public class Visit{
            @SerializedName("id")
            public String id;
            @SerializedName("member_id")
            public String member_id;
            @SerializedName("master_id")
            public String master_id;
            @SerializedName("brand")
            public String brand;
            @SerializedName("status")
            public String status;
            @SerializedName("dose")
            public String dose;
            @SerializedName("duration")
            public String duration;
            @SerializedName("quantity")
            public String quantity;
            @SerializedName("advice")
            public String advice;
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

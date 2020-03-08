package xact.idea.camelia.Database.AnotherModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SummaryModel {
    @SerializedName("Hypertension")
    public int  Hypertension;
    @SerializedName("Obese")
    public int  Obese;
    @SerializedName("OverWeight")
    public int  OverWeight;
    @SerializedName("Diabetes")
    public int  Diabetes;
    @SerializedName("DateTime")
    public Date DateTime;

}

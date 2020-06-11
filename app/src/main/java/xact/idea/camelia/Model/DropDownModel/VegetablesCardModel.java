package xact.idea.camelia.Model.DropDownModel;

import com.google.gson.annotations.SerializedName;

public class VegetablesCardModel {
    @SerializedName("Name")
    private String Name;
    @SerializedName("Id")
    private double Id;

    public VegetablesCardModel(String name, double id) {
        Name = name;
        Id = id;
    }

    @Override
    public String toString() {
        return Name;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public double getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

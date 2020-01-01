package xact.idea.camelia.Model.DropDownModel;

import com.google.gson.annotations.SerializedName;

public class ModerateModel {
    @SerializedName("Name")
    private String Name;
    @SerializedName("Id")
    private int Id;

    public ModerateModel(String name, int id) {
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
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

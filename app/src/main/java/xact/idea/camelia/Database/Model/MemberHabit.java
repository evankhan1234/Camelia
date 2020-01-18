package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MemberHabit")
public class MemberHabit {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberMyselfPhoneNumber")
    public String MemberMyselfPhoneNumber;
    @ColumnInfo(name = "SmokeYesNo")
    public int SmokeYesNo;
    @ColumnInfo(name = "SmokeYesYears")
    public int SmokeYesYears;
    @ColumnInfo(name = "SmokeYesPerday")
    public int SmokeYesPerday;
    @ColumnInfo(name = "JordaYesNo")
    public int JordaYesNo;
    @ColumnInfo(name = "JordaYears")
    public int JordaYears;
    @ColumnInfo(name = "WorkplaceYesNo")
    public int WorkplaceYesNo;
    @ColumnInfo(name = "WorkplaceYears")
    public int WorkplaceYears;
    @ColumnInfo(name = "AlcoholYesNo")
    public int AlcoholYesNo;
    @ColumnInfo(name = "AlcoholYears")
    public int AlcoholYears;
    @ColumnInfo(name = "FruitsTypicalWeek")
    public int FruitsTypicalWeek;
    @ColumnInfo(name = "FruitsShowCard")
    public int FruitsShowCard;
    @ColumnInfo(name = "VegetablesTypicalWeek")
    public int VegetablesTypicalWeek;
    @ColumnInfo(name = "VegetablesShowCard")
    public int VegetablesShowCard;
    @ColumnInfo(name = "SaltBuy")
    public int SaltBuy;
    @ColumnInfo(name = "TakingSalt")
    public int TakingSalt;
    @ColumnInfo(name = "VigorousIntensityYesNo")
    public int VigorousIntensityYesNo;
    @ColumnInfo(name = "VigorousIntensityActivities")
    public String VigorousIntensityActivities;
    @ColumnInfo(name = "VigorousIntensityTypicalWeek")
    public int VigorousIntensityTypicalWeek;
    @ColumnInfo(name = "VigorousIntensityTypicalDay")
    public int VigorousIntensityTypicalDay;
    @ColumnInfo(name = "ModeratorIntensityYesNo")
    public int ModeratorIntensityYesNo;
    @ColumnInfo(name = "ModeratorIntensityActivities")
    public String ModeratorIntensityActivities;
    @ColumnInfo(name = "ModeratorIntensityTypicalWeek")
    public int ModeratorIntensityTypicalWeek;
    @ColumnInfo(name = "ModeratorIntensityTypicalDay")
    public int ModeratorIntensityTypicalDay;
    @ColumnInfo(name = "VigorousIntensityRecreationalYesNo")
    public int VigorousIntensityRecreationalYesNo;
    @ColumnInfo(name = "VigorousIntensityRecreationalActivities")
    public String VigorousIntensityRecreationalActivities;
    @ColumnInfo(name = "VigorousIntensityRecreationalTypicalWeek")
    public int VigorousIntensityRecreationalTypicalWeek;
    @ColumnInfo(name = "VigorousIntensityRecreationalTypicalDay")
    public int VigorousIntensityRecreationalTypicalDay;
    @ColumnInfo(name = "ModeratorIntensityRecreationalYesNo")
    public int ModeratorIntensityRecreationalYesNo;
    @ColumnInfo(name = "ModeratorIntensityRecreationalActivities")
    public String ModeratorIntensityRecreationalActivities;
    @ColumnInfo(name = "ModeratorIntensityRecreationalTypicalWeek")
    public int ModeratorIntensityRecreationalTypicalWeek;
    @ColumnInfo(name = "ModeratorIntensityRecreationalTypicalDay")
    public int ModeratorIntensityRecreationalTypicalDay;
    @ColumnInfo(name = "ReclinigActivitiesYesNo")
    public int ReclinigActivitiesYesNo;
    @ColumnInfo(name = "ReclinigActivitiesTypicalDay")
    public int ReclinigActivitiesTypicalDay;

}

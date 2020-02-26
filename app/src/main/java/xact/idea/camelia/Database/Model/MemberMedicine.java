package xact.idea.camelia.Database.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MemberMedicine")
public class MemberMedicine {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "MemberId")
    public String MemberId;
    @ColumnInfo(name = "DiabetisYesNo")
    public int DiabetisYesNo;
    @ColumnInfo(name = "DiabetisSufferingYear")
    public int DiabetisSufferingYear;
    @ColumnInfo(name = "DiabetisControlDisease")
    public String DiabetisControlDisease;
    @ColumnInfo(name = "DiabetisMedicineName")
    public String DiabetisMedicineName;

    @ColumnInfo(name = "BloodPressureYesNo")
    public int BloodPressureYesNo;
    @ColumnInfo(name = "BloodPressureSufferingYear")
    public int BloodPressureSufferingYear;
    @ColumnInfo(name = "BloodPressureControlDisease")
    public String BloodPressureControlDisease;
    @ColumnInfo(name = "BloodPressureMedicineName")
    public String BloodPressureMedicineName;

    @ColumnInfo(name = "HeartAttackYesNo")
    public int HeartAttackYesNo;
    @ColumnInfo(name = "HeartAttackSufferingYear")
    public int HeartAttackSufferingYear;
    @ColumnInfo(name = "HeartAttackControlDisease")
    public String HeartAttackControlDisease;
    @ColumnInfo(name = "HeartAttackMedicineName")
    public String HeartAttackMedicineName;

    @ColumnInfo(name = "BrainStrokeYesNo")
    public int BrainStrokeYesNo;
    @ColumnInfo(name = "BrainStrokeSufferingYear")
    public int BrainStrokeSufferingYear;
    @ColumnInfo(name = "BrainStrokeControlDisease")
    public String BrainStrokeControlDisease;
    @ColumnInfo(name = "BrainStrokeMedicineName")
    public String BrainStrokeMedicineName;

    @ColumnInfo(name = "LungYesNo")
    public int LungYesNo;
    @ColumnInfo(name = "LungSufferingYear")
    public int LungSufferingYear;
    @ColumnInfo(name = "LungControlDisease")
    public String LungControlDisease;
    @ColumnInfo(name = "LungMedicineName")
    public String LungMedicineName;

    @ColumnInfo(name = "AshmaYesNo")
    public int AshmaYesNo;
    @ColumnInfo(name = "AshmaSufferingYear")
    public int AshmaSufferingYear;
    @ColumnInfo(name = "AshmaControlDisease")
    public String AshmaControlDisease;
    @ColumnInfo(name = "AshmaMedicineName")
    public String AshmaMedicineName;

    @ColumnInfo(name = "KidneyYesNo")
    public int KidneyYesNo;
    @ColumnInfo(name = "KidneySufferingYear")
    public int KidneySufferingYear;
    @ColumnInfo(name = "KidneyControlDisease")
    public String KidneyControlDisease;
    @ColumnInfo(name = "KidneyMedicineName")
    public String KidneyMedicineName;


    @ColumnInfo(name = "CancerYesNo")
    public int CancerYesNo;
    @ColumnInfo(name = "CancerSufferingYear")
    public int CancerSufferingYear;
    @ColumnInfo(name = "CancerControlDisease")
    public String CancerControlDisease;
    @ColumnInfo(name = "CancerMedicineName")
    public String CancerMedicineName;

    @ColumnInfo(name = "MentalYesNo")
    public int MentalYesNo;
    @ColumnInfo(name = "MentalSufferingYear")
    public int MentalSufferingYear;
    @ColumnInfo(name = "MentalControlDisease")
    public String MentalControlDisease;
    @ColumnInfo(name = "MentalMedicineName")
    public String MentalMedicineName;

}

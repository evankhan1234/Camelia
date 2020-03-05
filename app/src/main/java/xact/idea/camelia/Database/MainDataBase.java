package xact.idea.camelia.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import xact.idea.camelia.Database.Dao.AuthDao;
import xact.idea.camelia.Database.Dao.BlockDao;
import xact.idea.camelia.Database.Dao.BloodGroupDao;
import xact.idea.camelia.Database.Dao.CCDao;
import xact.idea.camelia.Database.Dao.DistrictDao;
import xact.idea.camelia.Database.Dao.DivisionDao;
import xact.idea.camelia.Database.Dao.FemaleDao;
import xact.idea.camelia.Database.Dao.HouseholdDao;
import xact.idea.camelia.Database.Dao.MaritialStatusDao;
import xact.idea.camelia.Database.Dao.MeasurementDetailsDao;
import xact.idea.camelia.Database.Dao.MeasurementsDao;
import xact.idea.camelia.Database.Dao.MedicineDao;
import xact.idea.camelia.Database.Dao.MemberHabitDao;
import xact.idea.camelia.Database.Dao.MemberIdDao;
import xact.idea.camelia.Database.Dao.MemberMedicineDao;
import xact.idea.camelia.Database.Dao.MemberMyselfDao;
import xact.idea.camelia.Database.Dao.OccupationDao;
import xact.idea.camelia.Database.Dao.QuestionsDao;
import xact.idea.camelia.Database.Dao.ReferralHistoryDao;
import xact.idea.camelia.Database.Dao.StudyClassDao;
import xact.idea.camelia.Database.Dao.SurveyDao;
import xact.idea.camelia.Database.Dao.UHCDao;
import xact.idea.camelia.Database.Dao.UnionDao;
import xact.idea.camelia.Database.Dao.UpazilaDao;
import xact.idea.camelia.Database.Dao.WardDao;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Database.Model.Block;
import xact.idea.camelia.Database.Model.BloodGroup;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.District;
import xact.idea.camelia.Database.Model.Division;
import xact.idea.camelia.Database.Model.Female;
import xact.idea.camelia.Database.Model.HouseHold;
import xact.idea.camelia.Database.Model.MaritialStatus;
import xact.idea.camelia.Database.Model.MeasurementDetails;
import xact.idea.camelia.Database.Model.Measurements;
import xact.idea.camelia.Database.Model.Medicine;
import xact.idea.camelia.Database.Model.MemberHabit;
import xact.idea.camelia.Database.Model.MemberId;
import xact.idea.camelia.Database.Model.MemberMedicine;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.Occupation;
import xact.idea.camelia.Database.Model.Questions;
import xact.idea.camelia.Database.Model.ReferHistory;
import xact.idea.camelia.Database.Model.StudyClass;
import xact.idea.camelia.Database.Model.Survey;
import xact.idea.camelia.Database.Model.UHC;
import xact.idea.camelia.Database.Model.Unions;
import xact.idea.camelia.Database.Model.Upazila;
import xact.idea.camelia.Database.Model.Ward;

@Database(entities = {Auth.class, Block.class, BloodGroup.class, District.class,Division.class, Female.class, MaritialStatus.class, Occupation.class, StudyClass.class, Unions.class, Upazila.class, Ward.class, HouseHold.class, Measurements.class, Medicine.class, MemberHabit.class, MemberMedicine.class, MemberMyself.class, Survey.class, MeasurementDetails.class, MemberId.class, Questions.class, ReferHistory.class, CCModel.class, UHC.class}, version =3,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MainDataBase extends RoomDatabase {
    public abstract DivisionDao divisionDao();
    public abstract AuthDao authDao();
    public abstract BlockDao blockDao();
    public abstract BloodGroupDao bloodGroupDao();
    public abstract DistrictDao districtDao();
    public abstract FemaleDao femaleDao();
    public abstract MaritialStatusDao maritialStatusDao();
    public abstract OccupationDao occupationDao();
    public abstract StudyClassDao studyClassDao();
    public abstract UnionDao unionDao();
    public abstract UpazilaDao upazilaDao();
    public abstract HouseholdDao householdDao();
    public abstract MeasurementsDao measurementsDao();
    public abstract MedicineDao medicineDao();
    public abstract MemberMyselfDao memberMyselfDao();
    public abstract MemberHabitDao memberHabitDao();
    public abstract MemberMedicineDao memberMedicineDao();
    public abstract SurveyDao surveyDao();
    public abstract WardDao wardDao();
    public abstract MeasurementDetailsDao measurementDetailsDao();
    public abstract MemberIdDao memberIdDao();
    public abstract QuestionsDao questionsDao();
    public abstract ReferralHistoryDao referralHistoryDao();
    public abstract UHCDao uhcDao();
    public abstract CCDao ccDao();

    private static MainDataBase instance;

    public static MainDataBase getInstance(Context context) {
        instance = Room.databaseBuilder(context, MainDataBase.class, "Camelia").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        return instance;

    }


}
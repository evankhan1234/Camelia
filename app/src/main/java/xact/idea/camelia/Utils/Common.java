package xact.idea.camelia.Utils;


import xact.idea.camelia.Database.MainDataBase;
import xact.idea.camelia.Database.Repository.AuthRepository;
import xact.idea.camelia.Database.Repository.BlockRepository;
import xact.idea.camelia.Database.Repository.BloodGroupRepository;
import xact.idea.camelia.Database.Repository.CCRepository;
import xact.idea.camelia.Database.Repository.DistrictRepository;
import xact.idea.camelia.Database.Repository.DivisionRepository;
import xact.idea.camelia.Database.Repository.FemaleRepository;
import xact.idea.camelia.Database.Repository.HouseholdRepository;
import xact.idea.camelia.Database.Repository.MaritialStatusRepository;
import xact.idea.camelia.Database.Repository.MeasurementDetailsRepository;
import xact.idea.camelia.Database.Repository.MeasurementsRepository;
import xact.idea.camelia.Database.Repository.MedicineRepository;
import xact.idea.camelia.Database.Repository.MemberHabitRepository;
import xact.idea.camelia.Database.Repository.MemberIdRepository;
import xact.idea.camelia.Database.Repository.MemberMedicineRepository;
import xact.idea.camelia.Database.Repository.MemberMyselfRepository;
import xact.idea.camelia.Database.Repository.OccupationRepository;
import xact.idea.camelia.Database.Repository.QustionsRepository;
import xact.idea.camelia.Database.Repository.ReferRepository;
import xact.idea.camelia.Database.Repository.StudyClassRepository;
import xact.idea.camelia.Database.Repository.SurveyRepository;
import xact.idea.camelia.Database.Repository.UHCRepository;
import xact.idea.camelia.Database.Repository.UnionRepository;
import xact.idea.camelia.Database.Repository.UpazilaRepository;
import xact.idea.camelia.Database.Repository.VisitRepository;
import xact.idea.camelia.Database.Repository.WardRepository;
import xact.idea.camelia.Network.IRetrofitApi;
import xact.idea.camelia.Network.RetrofitClient;

public abstract class Common {
    public static MainDataBase mainDatabase;
    public static DivisionRepository divisionRepository;
    public static AuthRepository authRepository;
    public static BlockRepository blockRepository;
    public static BloodGroupRepository bloodGroupRepository;
    public static DistrictRepository districtRepository;
    public static FemaleRepository femaleRepository;
    public static MaritialStatusRepository maritialStatusRepository;
    public static OccupationRepository occupationRepository;
    public static StudyClassRepository studyClassRepository;
    public static UnionRepository unionRepository;
    public static UpazilaRepository upazilaRepository;
    public static WardRepository wardRepository;
    public static HouseholdRepository householdRepository;
    public static MeasurementsRepository measurementsRepository;
    public static MedicineRepository medicineRepository;
    public static MemberHabitRepository memberHabitRepository;
    public static MemberMedicineRepository memberMedicineRepository;
    public static MemberMyselfRepository memberMyselfRepository;
    public static SurveyRepository surveyRepository;
    public static MeasurementDetailsRepository measurementDetailsRepository;
    public static MemberIdRepository memberIdRepository;
    public static QustionsRepository qustionsRepository;
    public static CCRepository ccRepository;
    public static UHCRepository uhcRepository;
    public static ReferRepository referRepository;
    public static VisitRepository visitRepository;

 //   public static final String BASE_URL_XACT = "http://camelia-beta.ciprb.org/api/";
    public static final String BASE_URL_XACT = "http://demo.xactidea.com/camelia/api/";

    public static IRetrofitApi getApiXact() {
        return RetrofitClient.getClient(BASE_URL_XACT).create(IRetrofitApi.class);
    }
}

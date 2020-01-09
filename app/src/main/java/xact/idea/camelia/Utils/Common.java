package xact.idea.camelia.Utils;


import xact.idea.camelia.Database.MainDataBase;
import xact.idea.camelia.Database.Repository.AuthRepository;
import xact.idea.camelia.Database.Repository.BlockRepository;
import xact.idea.camelia.Database.Repository.BloodGroupRepository;
import xact.idea.camelia.Database.Repository.DistrictRepository;
import xact.idea.camelia.Database.Repository.DivisionRepository;
import xact.idea.camelia.Database.Repository.FemaleRepository;
import xact.idea.camelia.Database.Repository.MaritialStatusRepository;
import xact.idea.camelia.Database.Repository.OccupationRepository;
import xact.idea.camelia.Database.Repository.StudyClassRepository;
import xact.idea.camelia.Database.Repository.UnionRepository;
import xact.idea.camelia.Database.Repository.UpazilaRepository;
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
    public static final String BASE_URL_XACT = "http://demo.xactidea.com/camelia/api/";

    public static IRetrofitApi getApiXact() {
        return RetrofitClient.getClient(BASE_URL_XACT).create(IRetrofitApi.class);
    }
}

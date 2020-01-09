package xact.idea.camelia.Network;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xact.idea.camelia.NetworkModel.AuthPost;
import xact.idea.camelia.NetworkModel.AuthResponse;
import xact.idea.camelia.NetworkModel.BlockResponses;
import xact.idea.camelia.NetworkModel.BloodGroupResponses;
import xact.idea.camelia.NetworkModel.DistrictResponses;
import xact.idea.camelia.NetworkModel.DivisionResponses;
import xact.idea.camelia.NetworkModel.GenderResponses;
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.OccupationResponses;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.NetworkModel.UnionResponses;
import xact.idea.camelia.NetworkModel.UpazilaResponses;
import xact.idea.camelia.NetworkModel.WardResponses;

public interface IRetrofitApi {
    @GET("setupdata/studyclass")
    io.reactivex.Observable<StudyClassResponses> getStudyClass();
    @GET("setupdata/occupation")
    io.reactivex.Observable<OccupationResponses> getOccupation();
    @GET("setupdata/maritalstatus")
    io.reactivex.Observable<MaritialStatusResponses> getMaritialStatus();
    @GET("setupdata/gender")
    io.reactivex.Observable<GenderResponses> getGender();
    @GET("setupdata/bloodgroup")
    io.reactivex.Observable<BloodGroupResponses> getBloodGroup();
    @GET("setupdata/division")
    io.reactivex.Observable<DivisionResponses> getDivision();
    @GET("setupdata/district")
    io.reactivex.Observable<DistrictResponses> getDistrictClass();
    @GET("setupdata/upazila")
    io.reactivex.Observable<UpazilaResponses> getUpazilaClass();
    @GET("setupdata/union")
    io.reactivex.Observable<UnionResponses> getUnion();
    @GET("setupdata/ward")
    io.reactivex.Observable<WardResponses> getWard();
    @GET("setupdata/block")
    io.reactivex.Observable<BlockResponses> getBlock();
    @POST("user/login")
    io.reactivex.Observable<AuthResponse> Login(@Body AuthPost authPost);
}

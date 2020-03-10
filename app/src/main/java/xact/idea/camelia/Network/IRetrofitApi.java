package xact.idea.camelia.Network;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import xact.idea.camelia.NetworkModel.AuthPost;
import xact.idea.camelia.NetworkModel.AuthResponse;
import xact.idea.camelia.NetworkModel.BlockResponses;
import xact.idea.camelia.NetworkModel.BloodGroupResponses;
import xact.idea.camelia.NetworkModel.CCModelresponse;
import xact.idea.camelia.NetworkModel.DistrictResponses;
import xact.idea.camelia.NetworkModel.DivisionResponses;
import xact.idea.camelia.NetworkModel.GenderResponses;
import xact.idea.camelia.NetworkModel.HouseholdGetResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdListResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdPostModel;
import xact.idea.camelia.NetworkModel.HouseholdResponseModel;
import xact.idea.camelia.NetworkModel.HouseholdUploadModel;
import xact.idea.camelia.NetworkModel.KhanaServeyResponseModel;
import xact.idea.camelia.NetworkModel.KhanaServeyUploadModel;
import xact.idea.camelia.NetworkModel.LabReportsResponseModel;
import xact.idea.camelia.NetworkModel.MaritialStatusResponses;
import xact.idea.camelia.NetworkModel.MeasurementResponseModel;
import xact.idea.camelia.NetworkModel.MeasurementsGetResponseModel;
import xact.idea.camelia.NetworkModel.MedicalHistoryResponseModel;
import xact.idea.camelia.NetworkModel.MedicalHistoryUpload;
import xact.idea.camelia.NetworkModel.MedicineResponses;
import xact.idea.camelia.NetworkModel.MemberAlocatePostModel;
import xact.idea.camelia.NetworkModel.MemberAlocateResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialResponseModel;
import xact.idea.camelia.NetworkModel.MemberBehaviorialUploadModel;
import xact.idea.camelia.NetworkModel.MemberGetResponseModel;
import xact.idea.camelia.NetworkModel.MemberPrescriptionGetResponseModel;
import xact.idea.camelia.NetworkModel.MemberPrescriptionResponseModel;
import xact.idea.camelia.NetworkModel.MemberResponseModel;
import xact.idea.camelia.NetworkModel.MemberUploadModel;
import xact.idea.camelia.NetworkModel.MesaurementUploadModel;
import xact.idea.camelia.NetworkModel.OccupationResponses;
import xact.idea.camelia.NetworkModel.ReferallPostModel;
import xact.idea.camelia.NetworkModel.StudyClassResponses;
import xact.idea.camelia.NetworkModel.UHCModel;
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
    @GET("medicines")
    io.reactivex.Observable<MedicineResponses> getMedicines();
    @POST("user/login")
    io.reactivex.Observable<AuthResponse> Login(@Body AuthPost authPost);
    @POST("measurement/store")
    io.reactivex.Observable<MeasurementResponseModel> postMeasurementUpload(@Body MesaurementUploadModel mesaurementUploadModel);
    @POST("medicalhistory/store")
    io.reactivex.Observable<MedicalHistoryResponseModel> postMedicalHistoryUpload(@Body MedicalHistoryUpload medicalHistoryUpload);
    @POST("behavioralinfo/store")
    io.reactivex.Observable<MemberBehaviorialResponseModel> postMemberBehaviorialUpload(@Body MemberBehaviorialUploadModel medicalHistoryUpload);
    @POST("khana_survey/store")
    io.reactivex.Observable<KhanaServeyResponseModel> postKhanaServeyUpload(@Body KhanaServeyUploadModel khanaServeyUploadModel);
    @POST("members/store")
    io.reactivex.Observable<MemberResponseModel> postMemberUpload(@Body MemberUploadModel memberUploadModel);
    @POST("household/store")
    io.reactivex.Observable<HouseholdResponseModel> postHouseholdUpload(@Body HouseholdUploadModel householdUploadModel);
    @POST("member/referral_history")
    io.reactivex.Observable<MemberPrescriptionResponseModel> getMemberReferalhistory(@Body ReferallPostModel referallPostModel);
    @POST("member/lab_reports")
    io.reactivex.Observable<LabReportsResponseModel> getLabReports(@Body ReferallPostModel referallPostModel);
    @POST("member/measurements")
    io.reactivex.Observable<MeasurementsGetResponseModel> getMeasurementsGetResponse(@Body ReferallPostModel referallPostModel);
    @POST("member/prescriptions")
    io.reactivex.Observable<MemberPrescriptionGetResponseModel> getMemberPrescription(@Body ReferallPostModel referallPostModel);
    @POST("memberid/allocate")
    io.reactivex.Observable<MemberAlocateResponseModel> getMemberAlocate(@Body MemberAlocatePostModel memberAlocatePostModel);
    @POST("members")
    io.reactivex.Observable<MemberGetResponseModel> getMemberShow(@Body ReferallPostModel memberAlocatePostModel);
    @POST("household/view")
    io.reactivex.Observable<HouseholdGetResponseModel> getHouseholdShow(@Body HouseholdPostModel householdPostModel);
    @GET("uhc/list")
    io.reactivex.Observable<UHCModel> getUHC();
    @GET("cc/list")
    io.reactivex.Observable<CCModelresponse> getCC();
    @GET("household/list ")
    io.reactivex.Observable<HouseholdListResponseModel> getHouseholdList();
}

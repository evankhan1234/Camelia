package xact.idea.camelia.Database.Repository;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.HHDashboardModel;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.AnotherModel.SummaryModel;
import xact.idea.camelia.Database.AnotherModel.VisitMyself;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberMyselfDatasources;
import xact.idea.camelia.Database.Model.MemberMyself;

public class MemberMyselfRepository implements IMemberMyselfDatasources {
    public IMemberMyselfDatasources IMemberMyselfDatasources;
    public MemberMyselfRepository(IMemberMyselfDatasources IMemberMyselfDatasources){
        this.IMemberMyselfDatasources=IMemberMyselfDatasources;
    }
    private static  MemberMyselfRepository instance;

    public static MemberMyselfRepository getInstance(IMemberMyselfDatasources iCartDataSource){
        if(instance==null)
            instance= new MemberMyselfRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<MemberMyself>> getMemberMyselfItems() {
        return IMemberMyselfDatasources.getMemberMyselfItems();
    }

    @Override
    public Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfItemId) {
        return IMemberMyselfDatasources.getMemberMyselfItemById(MemberMyselfItemId);
    }

    @Override
    public MemberMyself getMemberMyself(String MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberMyself(MemberMyselfItem);
    }

    @Override
    public MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberMyselfForHousehold(MemberMyselfItem);
    }

    @Override
    public void emptyMemberMyself() {
        IMemberMyselfDatasources.emptyMemberMyself();
    }

    @Override
    public int maxValue() {
        return IMemberMyselfDatasources.maxValue();
    }

    @Override
    public Flowable<List<MemberMyself>> getMemberListCC() {
        return IMemberMyselfDatasources.getMemberListCC();
    }

    @Override
    public HHDashboardModel hhModel() {
        return IMemberMyselfDatasources.hhModel();
    }

    @Override
    public MemberMyself getMemberId(String MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberId(MemberMyselfItem);
    }

    @Override
    public int size() {
        return IMemberMyselfDatasources.size();
    }

    @Override
    public int notSync(Date from, Date to) {
        return IMemberMyselfDatasources.notSync(from, to);
    }

    @Override
    public int Sync(Date from, Date to) {
        return IMemberMyselfDatasources.Sync(from, to);
    }

    @Override
    public Flowable<List<VisitMyself>> getMemberListForRefer() {
        return IMemberMyselfDatasources.getMemberListForRefer();
    }

    @Override
    public Flowable<List<VisitMyself>> getMemberListForFollowUp() {
        return IMemberMyselfDatasources.getMemberListForFollowUp();
    }

    @Override
    public Flowable<List<MemberMyself>> getReferMembersForCC() {
        return IMemberMyselfDatasources.getReferMembersForCC();
    }

    @Override
    public Flowable<List<SummaryModel>> TotalListOfSum(Date from, Date to) {
        return IMemberMyselfDatasources.TotalListOfSum(from, to);
    }

    @Override
    public SummaryModel TotalSum(Date from) {
        return IMemberMyselfDatasources.TotalSum(from);
    }

    @Override
    public void updateReciverAgain(String from, String to, String date, String memberId) {
        IMemberMyselfDatasources.updateReciverAgain(from, to, date, memberId);
    }

    @Override
    public Flowable<List<SentSyncModel>> getSyncMembers(Date from, Date to) {
        return IMemberMyselfDatasources.getSyncMembers(from, to);
    }

    @Override
    public Flowable<List<SentSyncModel>> getNotSyncMembers(Date from, Date to) {
        return IMemberMyselfDatasources.getNotSyncMembers(from, to);
    }

    @Override
    public  Flowable<List<Count>> TotalCountByDateRange(Date from, Date to) {
        return IMemberMyselfDatasources.TotalCountByDateRange(from, to);
    }

    @Override
    public Count TotalCountByDate(Date from) {
        return IMemberMyselfDatasources.TotalCountByDate(from);
    }

    @Override
    public void updateReciver(String date, String member) {
        IMemberMyselfDatasources.updateReciver(date, member);
    }

    @Override
    public Flowable<List<MemberMyself>> getInCompleteMembers() {
        return IMemberMyselfDatasources.getInCompleteMembers();
    }

    @Override
    public Flowable<List<MemberMyself>> getCompleteMembers() {
        return IMemberMyselfDatasources.getCompleteMembers();
    }

    @Override
    public MemberMyself getMemberMyselfNo(int MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberMyselfNo(MemberMyselfItem);
    }

    @Override
    public Flowable<List<MemberMyself>> getInCompleteMembersFor() {
        return IMemberMyselfDatasources.getInCompleteMembersFor();
    }

    @Override
    public void insertToMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.insertToMemberMyself(MemberMyself);
    }

    @Override
    public Flowable<List<VisitMyself>> getReferMembersFor() {
        return IMemberMyselfDatasources.getReferMembersFor();
    }

    @Override
    public Flowable<List<MemberMyself>> getFollowUpMembersFor() {
        return IMemberMyselfDatasources.getFollowUpMembersFor();
    }

    @Override
    public void updateMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.updateMemberMyself(MemberMyself);
    }

    @Override
    public Count TotalCount() {
        return IMemberMyselfDatasources.TotalCount();
    }

    @Override
    public void deleteMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.deleteMemberMyself(MemberMyself);
    }
}

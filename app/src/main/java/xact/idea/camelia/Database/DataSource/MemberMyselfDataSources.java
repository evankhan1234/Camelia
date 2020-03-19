package xact.idea.camelia.Database.DataSource;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.HHDashboardModel;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.AnotherModel.SummaryModel;
import xact.idea.camelia.Database.Dao.MemberMyselfDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberMyselfDatasources;
import xact.idea.camelia.Database.Model.MemberMyself;

public class MemberMyselfDataSources implements IMemberMyselfDatasources {
    private MemberMyselfDao MemberMyselfDao;
    private static MemberMyselfDataSources instance;

    public MemberMyselfDataSources(MemberMyselfDao MemberMyselfDao){
        this.MemberMyselfDao=MemberMyselfDao;
    }
    public static MemberMyselfDataSources getInstance(MemberMyselfDao MemberMyselfDao){
        if(instance==null)
            instance = new MemberMyselfDataSources(MemberMyselfDao);
        return instance;

    }
    @Override
    public Flowable<List<MemberMyself>> getMemberMyselfItems() {
        return MemberMyselfDao.getMemberMyselfItems();
    }

    @Override
    public Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfItemId) {
        return MemberMyselfDao.getMemberMyselfItemById(MemberMyselfItemId);
    }

    @Override
    public MemberMyself getMemberMyself(String MemberMyselfItem) {
        return MemberMyselfDao.getMemberMyself(MemberMyselfItem);
    }

    @Override
    public MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem) {
        return MemberMyselfDao.getMemberMyselfForHousehold(MemberMyselfItem);
    }

    @Override
    public void emptyMemberMyself() {
        MemberMyselfDao.emptyMemberMyself();
    }

    @Override
    public int maxValue() {
        return MemberMyselfDao.maxValue();
    }

    @Override
    public Flowable<List<MemberMyself>> getMemberListCC() {
        return MemberMyselfDao.getMemberListCC();
    }

    @Override
    public HHDashboardModel hhModel() {
        return MemberMyselfDao.hhModel();
    }

    @Override
    public MemberMyself getMemberId(String MemberMyselfItem) {
        return MemberMyselfDao.getMemberId(MemberMyselfItem);
    }

    @Override
    public int size() {
        return MemberMyselfDao.value();
    }

    @Override
    public int notSync(Date from, Date to) {
        return MemberMyselfDao.notSync(from, to);
    }

    @Override
    public int Sync(Date from, Date to) {
        return MemberMyselfDao.Sync(from, to);
    }

    @Override
    public Flowable<List<MemberMyself>> getReferMembersForCC() {
        return MemberMyselfDao.getReferMembersForCC();
    }

    @Override
    public Flowable<List<SummaryModel>> TotalListOfSum(Date from, Date to) {
        return MemberMyselfDao.TotalListOfSum(from, to);
    }

    @Override
    public SummaryModel TotalSum(Date from) {
        return MemberMyselfDao.TotalSum(from);
    }

    @Override
    public void updateReciverAgain(String from, String to, String date, String memberId) {
        MemberMyselfDao.updateReciverAgain(from, to, date, memberId);
    }

    @Override
    public Flowable<List<SentSyncModel>> getSyncMembers(Date from, Date to) {
        return MemberMyselfDao.getSyncMembers(from, to);
    }

    @Override
    public Flowable<List<SentSyncModel>> getNotSyncMembers(Date from, Date to) {
        return MemberMyselfDao.getNotSyncMembers(from, to);
    }

    @Override
    public Flowable<List<Count>> TotalCountByDateRange(Date from, Date to) {
        return MemberMyselfDao.TotalCountByDateRange(from, to);
    }

    @Override
    public Count TotalCountByDate(Date from) {
        return MemberMyselfDao.TotalCountByDate(from);
    }

    @Override
    public void updateReciver(String date, String member) {
        MemberMyselfDao.updateReciver(date, member);
    }

    @Override
    public Flowable<List<MemberMyself>> getInCompleteMembers() {
        return MemberMyselfDao.getInCompleteMembers();
    }

    @Override
    public Flowable<List<MemberMyself>> getCompleteMembers() {
        return MemberMyselfDao.getCompleteMembers();
    }

    @Override
    public MemberMyself getMemberMyselfNo(int MemberMyselfItem) {
        return MemberMyselfDao.getMemberMyselfNo(MemberMyselfItem);
    }

    @Override
    public Flowable<List<MemberMyself>> getInCompleteMembersFor() {
        return MemberMyselfDao.getInCompleteMembersFor();
    }

    @Override
    public void insertToMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.insertToMemberMyself(MemberMyself);
    }

    @Override
    public Flowable<List<MemberMyself>> getReferMembersFor() {
        return MemberMyselfDao.getReferMembersFor();
    }

    @Override
    public Flowable<List<MemberMyself>> getFollowUpMembersFor() {
        return MemberMyselfDao.getFollowUpMembersFor();
    }

    @Override
    public void updateMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.updateMemberMyself(MemberMyself);
    }

    @Override
    public Count TotalCount() {
        return MemberMyselfDao.TotalCount();
    }

    @Override
    public void deleteMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.deleteMemberMyself(MemberMyself);
    }
}

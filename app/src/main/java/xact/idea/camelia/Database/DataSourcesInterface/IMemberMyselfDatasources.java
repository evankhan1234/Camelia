package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.Model.MemberMyself;

public interface IMemberMyselfDatasources {
    Flowable<List<MemberMyself>> getMemberMyselfItems();

    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfItemId);

    MemberMyself getMemberMyself(String MemberMyselfItem);
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    void emptyMemberMyself();
    int maxValue();
    MemberMyself getMemberId(String MemberMyselfItem);
    int size();
    int notSync(Date from,Date to);
    int Sync(Date from,Date to);
    Flowable<List<SentSyncModel>> getSyncMembers(Date from,Date to);
    Flowable<List<SentSyncModel>> getNotSyncMembers(Date from, Date to);

    void updateReciver(String date,String member);
    Flowable<List<MemberMyself>> getInCompleteMembers();
    Flowable<List<MemberMyself>> getCompleteMembers();
    MemberMyself getMemberMyselfNo(int MemberMyselfItem);
    Flowable<List<MemberMyself>> getInCompleteMembersFor();
    void insertToMemberMyself(MemberMyself... MemberMyself);
    Flowable<List<MemberMyself>> getReferMembersFor();
    Flowable<List<MemberMyself>> getFollowUpMembersFor();
    void updateMemberMyself(MemberMyself... MemberMyself);
    Count TotalCount();
    void deleteMemberMyself(MemberMyself... MemberMyself);
}

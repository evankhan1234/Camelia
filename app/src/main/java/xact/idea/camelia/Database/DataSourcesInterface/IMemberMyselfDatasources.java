package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.Model.MemberMyself;

public interface IMemberMyselfDatasources {
    Flowable<List<MemberMyself>> getMemberMyselfItems();

    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfItemId);

    MemberMyself getMemberMyself(String MemberMyselfItem);
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    void emptyMemberMyself();
    int maxValue();
    int size();
    Flowable<List<MemberMyself>> getInCompleteMembers();
    Flowable<List<MemberMyself>> getCompleteMembers();
    MemberMyself getMemberMyselfNo(int MemberMyselfItem);
    Flowable<List<MemberMyself>> getInCompleteMembersFor();
    void insertToMemberMyself(MemberMyself... MemberMyself);
    Flowable<List<MemberMyself>> getReferMembersFor();
    Flowable<List<MemberMyself>> getFollowUpMembersFor();
    void updateMemberMyself(MemberMyself... MemberMyself);

    void deleteMemberMyself(MemberMyself... MemberMyself);
}

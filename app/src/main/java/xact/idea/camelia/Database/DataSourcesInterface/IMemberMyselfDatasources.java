package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberMyself;

public interface IMemberMyselfDatasources {
    Flowable<List<MemberMyself>> getMemberMyselfItems();

    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfItemId);

    MemberMyself getMemberMyself(String MemberMyselfItem);
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    void emptyMemberMyself();
    int maxValue();
    int size();

    MemberMyself getMemberMyselfNo(int MemberMyselfItem);

    void insertToMemberMyself(MemberMyself... MemberMyself);

    void updateMemberMyself(MemberMyself... MemberMyself);

    void deleteMemberMyself(MemberMyself... MemberMyself);
}

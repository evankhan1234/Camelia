package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberId;

public interface IMemberIdDatasources {
    Flowable<List<MemberId>> getMemberIdItems();

    Flowable<List<MemberId>> getMemberIdItemById(int MemberIdItemId);

    MemberId getMemberId(String MemberIdItem);

    void emptyMemberId(String value);
    int maxValue();
    int size();

    MemberId getMemberIdNo(String MemberIdItem);

    void insertToMemberId(MemberId... MemberId);

    void updateMemberId(MemberId... MemberId);

    void deleteMemberId(MemberId... MemberId);
}

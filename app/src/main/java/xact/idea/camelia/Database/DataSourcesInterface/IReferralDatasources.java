package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.ReferHistory;

public interface IReferralDatasources {
    Flowable<List<ReferHistory>> getReferHistoryItems();

    Flowable<List<ReferHistory>> getReferHistoryItemById(int ReferHistoryItemId);
    ReferHistory getMemberReferHistoryNo(String ReferHistoryItem);
    ReferHistory getReferHistory(String ReferHistoryItem);

    void emptyReferHistory();

    int size();
    ReferHistory getReferHistoryFrom(String member);
    void updateReferHistoryFrom(String memberId);
    ReferHistory getReferHistoryNo(String ReferHistoryItem);

    void insertToReferHistory(ReferHistory... ReferHistory);

    void updateReferHistory(ReferHistory... ReferHistory);

    void deleteReferHistory(ReferHistory... ReferHistory);
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IReferralDatasources;
import xact.idea.camelia.Database.Model.ReferHistory;

public class ReferRepository implements IReferralDatasources {
    public IReferralDatasources IReferralDatasources;
    public ReferRepository(IReferralDatasources IReferralDatasources){
        this.IReferralDatasources=IReferralDatasources;
    }
    private static  ReferRepository instance;

    public static ReferRepository getInstance(IReferralDatasources iCartDataSource){
        if(instance==null)
            instance= new ReferRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<ReferHistory>> getReferHistoryItems() {
        return IReferralDatasources.getReferHistoryItems();
    }

    @Override
    public Flowable<List<ReferHistory>> getReferHistoryItemById(int ReferHistoryItemId) {
        return IReferralDatasources.getReferHistoryItemById(ReferHistoryItemId);
    }

    @Override
    public ReferHistory getReferHistory(String ReferHistoryItem) {
        return IReferralDatasources.getReferHistory(ReferHistoryItem);
    }

    @Override
    public void emptyReferHistory() {
        IReferralDatasources.emptyReferHistory();
    }

    @Override
    public int size() {
        return IReferralDatasources.size();
    }

    @Override
    public ReferHistory getReferHistoryNo(String ReferHistoryItem) {
        return IReferralDatasources.getReferHistoryNo(ReferHistoryItem);
    }

    @Override
    public void insertToReferHistory(ReferHistory... ReferHistory) {
        IReferralDatasources.insertToReferHistory(ReferHistory);
    }

    @Override
    public void updateReferHistory(ReferHistory... ReferHistory) {
        IReferralDatasources.updateReferHistory(ReferHistory);
    }

    @Override
    public void deleteReferHistory(ReferHistory... ReferHistory) {
        IReferralDatasources.deleteReferHistory(ReferHistory);
    }
}


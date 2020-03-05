package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.ReferralHistoryDao;

import xact.idea.camelia.Database.DataSourcesInterface.IReferralDatasources;
import xact.idea.camelia.Database.Model.ReferHistory;

public class ReferralHistoryDatasources implements IReferralDatasources {

    private xact.idea.camelia.Database.Dao.ReferralHistoryDao ReferralHistoryDao;
    private static ReferralHistoryDatasources instance;

    public ReferralHistoryDatasources(ReferralHistoryDao ReferralHistoryDao){
        this.ReferralHistoryDao=ReferralHistoryDao;
    }
    public static ReferralHistoryDatasources getInstance(ReferralHistoryDao ReferralHistoryDao){
        if(instance==null)
            instance = new ReferralHistoryDatasources(ReferralHistoryDao);
        return instance;

    }

    @Override
    public Flowable<List<ReferHistory>> getReferHistoryItems() {
        return ReferralHistoryDao.getReferHistoryItems();
    }

    @Override
    public Flowable<List<ReferHistory>> getReferHistoryItemById(int ReferHistoryItemId) {
        return ReferralHistoryDao.getReferHistoryItemById(ReferHistoryItemId);
    }

    @Override
    public ReferHistory getReferHistory(String ReferHistoryItem) {
        return ReferralHistoryDao.getReferHistory(ReferHistoryItem);
    }

    @Override
    public void emptyReferHistory() {
        ReferralHistoryDao.emptyReferHistory();
    }

    @Override
    public int size() {
        return ReferralHistoryDao.value();
    }

    @Override
    public ReferHistory getReferHistoryNo(String ReferHistoryItem) {
        return ReferralHistoryDao.getReferHistoryNo(ReferHistoryItem);
    }

    @Override
    public void insertToReferHistory(ReferHistory... ReferHistory) {
        ReferralHistoryDao.insertToReferHistory(ReferHistory);
    }

    @Override
    public void updateReferHistory(ReferHistory... ReferHistory) {
        ReferralHistoryDao.updateReferHistory(ReferHistory);
    }

    @Override
    public void deleteReferHistory(ReferHistory... ReferHistory) {
        ReferralHistoryDao.deleteReferHistory(ReferHistory);
    }
}
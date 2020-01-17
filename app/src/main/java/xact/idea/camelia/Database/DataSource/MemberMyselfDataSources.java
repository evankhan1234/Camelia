package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
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
    public Flowable<List<MemberMyself>> getMemberMyselfItemById(int MemberMyselfItemId) {
        return MemberMyselfDao.getMemberMyselfItemById(MemberMyselfItemId);
    }

    @Override
    public MemberMyself getMemberMyself(String MemberMyselfItem) {
        return MemberMyselfDao.getMemberMyself(MemberMyselfItem);
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
    public int size() {
        return MemberMyselfDao.value();
    }

    @Override
    public MemberMyself getMemberMyselfNo(int MemberMyselfItem) {
        return MemberMyselfDao.getMemberMyselfNo(MemberMyselfItem);
    }

    @Override
    public void insertToMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.insertToMemberMyself(MemberMyself);
    }

    @Override
    public void updateMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.updateMemberMyself(MemberMyself);
    }

    @Override
    public void deleteMemberMyself(MemberMyself... MemberMyself) {
        MemberMyselfDao.deleteMemberMyself(MemberMyself);
    }
}

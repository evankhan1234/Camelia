package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
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
    public Flowable<List<MemberMyself>> getMemberMyselfItemById(int MemberMyselfItemId) {
        return IMemberMyselfDatasources.getMemberMyselfItemById(MemberMyselfItemId);
    }

    @Override
    public MemberMyself getMemberMyself(String MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberMyself(MemberMyselfItem);
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
    public int size() {
        return IMemberMyselfDatasources.size();
    }

    @Override
    public MemberMyself getMemberMyselfNo(int MemberMyselfItem) {
        return IMemberMyselfDatasources.getMemberMyselfNo(MemberMyselfItem);
    }

    @Override
    public void insertToMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.insertToMemberMyself(MemberMyself);
    }

    @Override
    public void updateMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.updateMemberMyself(MemberMyself);
    }

    @Override
    public void deleteMemberMyself(MemberMyself... MemberMyself) {
        IMemberMyselfDatasources.deleteMemberMyself(MemberMyself);
    }
}

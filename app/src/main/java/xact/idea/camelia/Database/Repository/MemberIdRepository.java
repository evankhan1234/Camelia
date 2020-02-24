package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberIdDatasources;
import xact.idea.camelia.Database.Model.MemberId;

public class MemberIdRepository implements IMemberIdDatasources {
    public IMemberIdDatasources IMemberIdDatasources;
    public MemberIdRepository(IMemberIdDatasources IMemberIdDatasources){
        this.IMemberIdDatasources=IMemberIdDatasources;
    }
    private static  MemberIdRepository instance;

    public static MemberIdRepository getInstance(IMemberIdDatasources IMemberIdDatasources){
        if(instance==null)
            instance= new MemberIdRepository(IMemberIdDatasources);
        return instance;

    }

    @Override
    public Flowable<List<MemberId>> getMemberIdItems() {
        return IMemberIdDatasources.getMemberIdItems();
    }

    @Override
    public Flowable<List<MemberId>> getMemberIdItemById(int MemberIdItemId) {
        return IMemberIdDatasources.getMemberIdItemById(MemberIdItemId);
    }

    @Override
    public MemberId getMemberId(String MemberIdItem) {
        return IMemberIdDatasources.getMemberId(MemberIdItem);
    }

    @Override
    public void emptyMemberId(String value) {
        IMemberIdDatasources.emptyMemberId(value);
    }

    @Override
    public int maxValue() {
        return IMemberIdDatasources.maxValue();
    }

    @Override
    public int size() {
        return IMemberIdDatasources.size();
    }

    @Override
    public MemberId getMemberIdNo(String MemberIdItem) {
        return IMemberIdDatasources.getMemberIdNo(MemberIdItem);
    }

    @Override
    public void insertToMemberId(MemberId... MemberId) {
        IMemberIdDatasources.insertToMemberId(MemberId);
    }

    @Override
    public void updateMemberId(MemberId... MemberId) {
        IMemberIdDatasources.updateMemberId(MemberId);
    }

    @Override
    public void deleteMemberId(MemberId... MemberId) {
        IMemberIdDatasources.deleteMemberId(MemberId);
    }
}

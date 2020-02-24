package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;

import xact.idea.camelia.Database.Dao.MemberIdDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberIdDatasources;
import xact.idea.camelia.Database.Model.MemberId;

public class MemberIdDatasources implements IMemberIdDatasources {

    private MemberIdDao MemberIdDaos;
    private static MemberIdDatasources instance;

    public MemberIdDatasources(MemberIdDao MemberIdDaos){
        this.MemberIdDaos=MemberIdDaos;
    }
    public static MemberIdDatasources getInstance(MemberIdDao MemberIdDaos){
        if(instance==null)
            instance = new MemberIdDatasources(MemberIdDaos);
        return instance;

    }

    @Override
    public Flowable<List<MemberId>> getMemberIdItems() {
        return MemberIdDaos.getMemberIdItems();
    }

    @Override
    public Flowable<List<MemberId>> getMemberIdItemById(int MemberIdItemId) {
        return MemberIdDaos.getMemberIdItemById(MemberIdItemId);
    }

    @Override
    public MemberId getMemberId(String MemberIdItem) {
        return MemberIdDaos.getMemberId(MemberIdItem);
    }

    @Override
    public void emptyMemberId(String value) {
        MemberIdDaos.emptyMemberId(value);
    }

    @Override
    public int maxValue() {
        return MemberIdDaos.maxValue();
    }

    @Override
    public int size() {
        return MemberIdDaos.value();
    }

    @Override
    public MemberId getMemberIdNo(String MemberIdItem) {
        return MemberIdDaos.getMemberIdNo(MemberIdItem);
    }

    @Override
    public void insertToMemberId(MemberId... MemberId) {
        MemberIdDaos.insertToMemberId(MemberId);
    }

    @Override
    public void updateMemberId(MemberId... MemberId) {
        MemberIdDaos.updateMemberId(MemberId);
    }

    @Override
    public void deleteMemberId(MemberId... MemberId) {
        MemberIdDaos.deleteMemberId(MemberId);
    }
}

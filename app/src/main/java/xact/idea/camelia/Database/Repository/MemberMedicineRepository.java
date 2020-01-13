package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberMedicineDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberMedicineDataSources;
import xact.idea.camelia.Database.Model.MemberMedicine;

public class MemberMedicineRepository implements IMemberMedicineDataSources {
    public IMemberMedicineDataSources IMemberMedicineDataSources;
    public MemberMedicineRepository(IMemberMedicineDataSources IMemberMedicineDataSources){
        this.IMemberMedicineDataSources=IMemberMedicineDataSources;
    }
    private static  MemberMedicineRepository instance;

    public static MemberMedicineRepository getInstance(IMemberMedicineDataSources iCartDataSource){
        if(instance==null)
            instance= new MemberMedicineRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<MemberMedicine>> getMemberMedicineItems() {
        return IMemberMedicineDataSources.getMemberMedicineItems();
    }

    @Override
    public Flowable<List<MemberMedicine>> getMemberMedicineItemById(int MemberMedicineItemId) {
        return IMemberMedicineDataSources.getMemberMedicineItemById(MemberMedicineItemId);
    }

    @Override
    public MemberMedicine getMemberMedicine(String MemberMedicineItem) {
        return IMemberMedicineDataSources.getMemberMedicine(MemberMedicineItem);
    }

    @Override
    public void emptyMemberMedicine() {
        IMemberMedicineDataSources.emptyMemberMedicine();
    }

    @Override
    public int size() {
        return IMemberMedicineDataSources.size();
    }

    @Override
    public MemberMedicine getMemberMedicineNo(String MemberMedicineItem) {
        return IMemberMedicineDataSources.getMemberMedicineNo(MemberMedicineItem);
    }

    @Override
    public void insertToMemberMedicine(MemberMedicine... MemberMedicine) {
        IMemberMedicineDataSources.insertToMemberMedicine(MemberMedicine);
    }

    @Override
    public void updateMemberMedicine(MemberMedicine... MemberMedicine) {
        IMemberMedicineDataSources.updateMemberMedicine(MemberMedicine);
    }

    @Override
    public void deleteMemberMedicine(MemberMedicine... MemberMedicine) {
        IMemberMedicineDataSources.deleteMemberMedicine(MemberMedicine);
    }
}

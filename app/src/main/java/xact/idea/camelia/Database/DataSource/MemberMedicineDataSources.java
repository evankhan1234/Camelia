package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MemberMedicineDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberMedicineDataSources;
import xact.idea.camelia.Database.Model.MemberMedicine;

public class MemberMedicineDataSources implements IMemberMedicineDataSources {
    private MemberMedicineDao MemberMedicineDao;
    private static MemberMedicineDataSources instance;

    public MemberMedicineDataSources(MemberMedicineDao MemberMedicineDao){
        this.MemberMedicineDao=MemberMedicineDao;
    }
    public static MemberMedicineDataSources getInstance(MemberMedicineDao MemberMedicineDao){
        if(instance==null)
            instance = new MemberMedicineDataSources(MemberMedicineDao);
        return instance;

    }
    @Override
    public Flowable<List<MemberMedicine>> getMemberMedicineItems() {
        return MemberMedicineDao.getMemberMedicineItems();
    }

    @Override
    public Flowable<List<MemberMedicine>> getMemberMedicineItemById(int MemberMedicineItemId) {
        return MemberMedicineDao.getMemberMedicineItemById(MemberMedicineItemId);
    }

    @Override
    public MemberMedicine getMemberMedicine(String MemberMedicineItem) {
        return MemberMedicineDao.getMemberMedicine(MemberMedicineItem);
    }

    @Override
    public void emptyMemberMedicine() {
        MemberMedicineDao.emptyMemberMedicine();
    }

    @Override
    public int size() {
        return MemberMedicineDao.value();
    }

    @Override
    public MemberMedicine getMemberMedicineNo(String MemberMedicineItem) {
        return MemberMedicineDao.getMemberMedicineNo(MemberMedicineItem);
    }

    @Override
    public void insertToMemberMedicine(MemberMedicine... MemberMedicine) {
        MemberMedicineDao.insertToMemberMedicine(MemberMedicine);
    }

    @Override
    public void updateMemberMedicine(MemberMedicine... MemberMedicine) {
        MemberMedicineDao.updateMemberMedicine(MemberMedicine);
    }

    @Override
    public void deleteMemberMedicine(MemberMedicine... MemberMedicine) {
        MemberMedicineDao.deleteMemberMedicine(MemberMedicine);
    }
}

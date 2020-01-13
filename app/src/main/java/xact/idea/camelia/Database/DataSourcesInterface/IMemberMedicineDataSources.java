package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberMedicine;

public interface IMemberMedicineDataSources {
    Flowable<List<MemberMedicine>> getMemberMedicineItems();

    Flowable<List<MemberMedicine>> getMemberMedicineItemById(int MemberMedicineItemId);

    MemberMedicine getMemberMedicine(String MemberMedicineItem);

    void emptyMemberMedicine();

    int size();

    MemberMedicine getMemberMedicineNo(String MemberMedicineItem);

    void insertToMemberMedicine(MemberMedicine... MemberMedicine);

    void updateMemberMedicine(MemberMedicine... MemberMedicine);

    void deleteMemberMedicine(MemberMedicine... MemberMedicine);
}

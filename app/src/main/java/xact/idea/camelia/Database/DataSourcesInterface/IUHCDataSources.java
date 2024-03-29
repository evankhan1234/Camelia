package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.UHC;

public interface IUHCDataSources {
    Flowable<List<UHC>> getUHCItems();

    Flowable<List<UHC>> getUHCItemById(int UHCItemId);

    UHC getUHC(String UHCItem);

    void emptyUHC();

    int size();

    Flowable<List<UHC>> getUHCItemByFour(String DivisionId,String DistrictId,String Upazila,String Union);

    Flowable<List<UHC>> getUHCItemByThree(String DivisionId,String DistrictId,String Upazila);

    Flowable<List<UHC>> getUHCItemByTwo(String DivisionId,String DistrictId);

    Flowable<List<UHC>> getUHCItemByOne(String DivisionId);
    UHC getUHCNo(String UHCItem);

    void insertToUHC(UHC... UHC);

    void updateUHC(UHC... UHC);

    void deleteUHC(UHC... UHC);
}

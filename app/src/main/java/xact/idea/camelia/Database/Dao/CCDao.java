package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.CCModel;
import xact.idea.camelia.Database.Model.CCModel;

@Dao
public interface CCDao {
    @Query("SELECT * FROM CCModel")
    Flowable<List<CCModel>> getCCModelItems();
    @Query("SELECT * FROM CCModel WHERE id=:CCModelItemId")
    Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId);

    @Query("SELECT * FROM CCModel WHERE division_code=:DivisionId AND district_code=:DistrictId AND upazila_code=:Upazila AND union_code=:Union")
    Flowable<List<CCModel>> getCCModelItemByFour(String DivisionId, String DistrictId, String Upazila, String Union);
    @Query("SELECT * FROM CCModel WHERE division_code=:DivisionId AND district_code=:DistrictId AND upazila_code=:Upazila")
    Flowable<List<CCModel>> getCCModelItemByThree(String DivisionId,String DistrictId,String Upazila);
    @Query("SELECT * FROM CCModel WHERE division_code=:DivisionId AND district_code=:DistrictId ")
    Flowable<List<CCModel>> getCCModelItemByTwo(String DivisionId,String DistrictId);
    @Query("SELECT * FROM CCModel WHERE division_code=:DivisionId ")
    Flowable<List<CCModel>> getCCModelItemByOne(String DivisionId);
    @Query("SELECT * FROM CCModel WHERE id=:CCModelItem")
    CCModel getCCModel(String CCModelItem);
    @Query("SELECT * FROM CCModel WHERE CCId=:CCModelItem")
    CCModel getCCModelNo(String CCModelItem);
    @Query("Select Count(id)  FROM CCModel")
    int value();
    @Query("DELETE  FROM CCModel")
    void emptyCCModel();
    @Insert
    void insertToCCModel(CCModel...CCModel);
    @Update
    void updateCCModel(CCModel...CCModel);
    @Delete
    void deleteCCModel(CCModel...CCModel);
}

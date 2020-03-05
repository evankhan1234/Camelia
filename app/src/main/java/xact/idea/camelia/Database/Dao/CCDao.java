package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.CCModel;

@Dao
public interface CCDao {
    @Query("SELECT * FROM CCModel")
    Flowable<List<CCModel>> getCCModelItems();
    @Query("SELECT * FROM CCModel WHERE id=:CCModelItemId")
    Flowable<List<CCModel>> getCCModelItemById(int CCModelItemId);
    @Query("SELECT * FROM CCModel WHERE id=:CCModelItem")
    CCModel getCCModel(String CCModelItem);
    @Query("SELECT * FROM CCModel WHERE id=:CCModelItem")
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

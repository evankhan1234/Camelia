package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.UHC;

@Dao
public interface UHCDao {
    @Query("SELECT * FROM UHC")
    Flowable<List<UHC>> getUHCItems();
    @Query("SELECT * FROM UHC WHERE id=:UHCItemId")
    Flowable<List<UHC>> getUHCItemById(int UHCItemId);
    @Query("SELECT * FROM UHC WHERE UHCId=:UHCItem")
    UHC getUHC(String UHCItem);
    @Query("SELECT * FROM UHC WHERE UHCId=:UHCItem")
    UHC getUHCNo(String UHCItem);
    @Query("Select Count(id)  FROM UHC")
    int value();
    @Query("DELETE  FROM UHC")
    void emptyUHC();
    @Insert
    void insertToUHC(UHC...UHC);
    @Update
    void updateUHC(UHC...UHC);
    @Delete
    void deleteUHC(UHC...UHC);
}

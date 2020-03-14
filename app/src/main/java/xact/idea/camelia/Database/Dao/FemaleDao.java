package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Female;

@Dao
public interface FemaleDao {
    @Query("SELECT * FROM Female")
    Flowable<List<Female>> getFemaleItems();
    @Query("SELECT * FROM Female WHERE id=:BookItemId")
    Flowable<List<Female>> getFemaleItemById(int BookItemId);
    @Query("SELECT * FROM Female WHERE FemaleId =:FemaleItem")
    Female getFemale(String FemaleItem);
    @Query("SELECT * FROM Female WHERE FemaleId=:FemaleItem")
    Female getFemaleNo(String FemaleItem);
    @Query("Select Count(id)  FROM Female")
    int value();
    @Query("UPDATE  Female SET ln=:lang")
    void updateLanguage(String lang);
    @Query("DELETE  FROM Female")
    void emptyFemale();
    @Insert
    void insertToFemale(Female...Female);
    @Update
    void updateFemale(Female...Female);
    @Delete
    void deleteFemale(Female...Female);
}

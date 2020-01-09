package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Unions;

@Dao
public interface UnionDao {
    @Query("SELECT * FROM Unions")
    Flowable<List<Unions>> getUnionItems();
    @Query("SELECT * FROM Unions WHERE id=:UnionItemId")
    Flowable<List<Unions>> getUnionItemById(int UnionItemId);
    @Query("SELECT * FROM Unions WHERE UnionId=:UnionItem")
    Unions getUnion(String UnionItem);
    @Query("SELECT * FROM Unions WHERE UnionId=:UnionItem")
    Unions getUnionNo(String UnionItem);
    @Query("Select Count(id)  FROM Unions")
    int value();
    @Query("DELETE  FROM Unions")
    void emptyUnion();
    @Insert
    void insertToUnion(Unions... Unions);
    @Update
    void updateUnion(Unions... Unions);
    @Delete
    void deleteUnion(Unions... Unions);
}

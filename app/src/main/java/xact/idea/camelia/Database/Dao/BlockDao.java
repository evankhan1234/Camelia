package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Block;

@Dao
public interface BlockDao {
    @Query("SELECT * FROM Block")
    Flowable<List<Block>> getBlockItems();
    @Query("SELECT * FROM Block WHERE id=:BlockItemId")
    Flowable<List<Block>> getBlockItemById(int BlockItemId);
    @Query("SELECT * FROM Block WHERE BlockId=:BlockItem")
    Block getBlock(String BlockItem);
    @Query("SELECT * FROM Block WHERE BlockId=:BlockItem")
    Block getBlockNo(String BlockItem);
    @Query("Select Count(id)  FROM Block")
    int value();
    @Query("DELETE  FROM Block")
    void emptyBlock();
    @Insert
    void insertToBlock(Block...Block);
    @Update
    void updateBlock(Block...Block);
    @Delete
    void deleteBlock(Block...Block);
}

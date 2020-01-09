package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Block;

public interface IBlockDatasources {
    Flowable<List<Block>> getBlockItems();

    Flowable<List<Block>> getBlockItemById(int BlockItemId);

    Block getBlock(String BlockItem);

    void emptyBlock();

    int size();

    Block getBlockNo(String BlockItem);

    void insertToBlock(Block... Block);

    void updateBlock(Block... Block);

    void deleteBlock(Block... Block);
}

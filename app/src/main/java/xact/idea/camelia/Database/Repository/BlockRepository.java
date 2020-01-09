package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IBlockDatasources;
import xact.idea.camelia.Database.Model.Block;

public class BlockRepository implements IBlockDatasources {
    public IBlockDatasources IBlockDatasources;
    public BlockRepository(IBlockDatasources IBlockDatasources){
        this.IBlockDatasources=IBlockDatasources;
    }
    private static  BlockRepository instance;

    public static BlockRepository getInstance(IBlockDatasources iCartDataSource){
        if(instance==null)
            instance= new BlockRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Block>> getBlockItems() {
        return IBlockDatasources.getBlockItems();
    }

    @Override
    public Flowable<List<Block>> getBlockItemById(int BlockItemId) {
        return IBlockDatasources.getBlockItemById(BlockItemId);
    }

    @Override
    public Block getBlock(String BlockItem) {
        return IBlockDatasources.getBlock(BlockItem);
    }

    @Override
    public void emptyBlock() {
        IBlockDatasources.emptyBlock();
    }

    @Override
    public int size() {
        return IBlockDatasources.size();
    }

    @Override
    public Block getBlockNo(String BlockItem) {
        return IBlockDatasources.getBlockNo(BlockItem);
    }

    @Override
    public void insertToBlock(Block... Block) {
        IBlockDatasources.insertToBlock(Block);
    }

    @Override
    public void updateBlock(Block... Block) {
        IBlockDatasources.updateBlock(Block);
    }

    @Override
    public void deleteBlock(Block... Block) {
        IBlockDatasources.deleteBlock(Block);
    }
}

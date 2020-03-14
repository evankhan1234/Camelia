package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.BlockDao;
import xact.idea.camelia.Database.Dao.BlockDao;
import xact.idea.camelia.Database.DataSourcesInterface.IBlockDatasources;
import xact.idea.camelia.Database.Model.Block;

public class BlockDataSources implements IBlockDatasources {

    private BlockDao BlockDao;
    private static BlockDataSources instance;

    public BlockDataSources(BlockDao BlockDao){
        this.BlockDao=BlockDao;
    }
    public static BlockDataSources getInstance(BlockDao BlockDao){
        if(instance==null)
            instance = new BlockDataSources(BlockDao);
        return instance;

    }

    @Override
    public Flowable<List<Block>> getBlockItems() {
        return BlockDao.getBlockItems();
    }

    @Override
    public Flowable<List<Block>> getBlockItemById(int BlockItemId) {
        return BlockDao.getBlockItemById(BlockItemId);
    }

    @Override
    public Block getBlock(String BlockItem) {
        return BlockDao.getBlock(BlockItem);
    }

    @Override
    public void emptyBlock() {
        BlockDao.emptyBlock();
    }

    @Override
    public void updateLanguage(String lang) {
        BlockDao.updateLanguage(lang);
    }

    @Override
    public int size() {
        return BlockDao.value();
    }

    @Override
    public Block getBlockNo(String BlockItem) {
        return BlockDao.getBlockNo(BlockItem);
    }

    @Override
    public void insertToBlock(Block... Block) {
        BlockDao.insertToBlock(Block);
    }

    @Override
    public void updateBlock(Block... Block) {
        BlockDao.updateBlock(Block);
    }

    @Override
    public void deleteBlock(Block... Block) {
        BlockDao.deleteBlock(Block);
    }
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IUnionDataSources;
import xact.idea.camelia.Database.Model.Unions;

public class UnionRepository implements IUnionDataSources {
    public IUnionDataSources IUnionDataSources;
    public UnionRepository(IUnionDataSources IUnionDataSources){
        this.IUnionDataSources=IUnionDataSources;
    }
    private static  UnionRepository instance;

    public static UnionRepository getInstance(IUnionDataSources iCartDataSource){
        if(instance==null)
            instance= new UnionRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Unions>> getUnionItems() {
        return IUnionDataSources.getUnionItems();
    }

    @Override
    public Flowable<List<Unions>> getUnionItemById(int BookItemId) {
        return IUnionDataSources.getUnionItemById(BookItemId);
    }

    @Override
    public Unions getUnion(String UnionItem) {
        return IUnionDataSources.getUnion(UnionItem);
    }

    @Override
    public void emptyUnion() {
        IUnionDataSources.emptyUnion();
    }

    @Override
    public int size() {
        return IUnionDataSources.size();
    }

    @Override
    public Unions getUnionNo(String BookItem) {
        return IUnionDataSources.getUnionNo(BookItem);
    }

    @Override
    public void insertToUnion(Unions... Unions) {
        IUnionDataSources.insertToUnion(Unions);
    }

    @Override
    public void updateUnion(Unions... Unions) {
        IUnionDataSources.updateUnion(Unions);
    }

    @Override
    public void deleteUnion(Unions... Unions) {
        IUnionDataSources.deleteUnion(Unions);
    }
}

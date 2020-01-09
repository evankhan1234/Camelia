package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Unions;

public interface IUnionDataSources {
    Flowable<List<Unions>> getUnionItems();

    Flowable<List<Unions>> getUnionItemById(int BookItemId);

    Unions getUnion(String UnionItem);

    void emptyUnion();

    int size();

    Unions getUnionNo(String BookItem);

    void insertToUnion(Unions... Unions);

    void updateUnion(Unions... Unions);

    void deleteUnion(Unions... Unions);
}

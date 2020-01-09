package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Auth;

public interface IAuthDataSources {
    Flowable<List<Auth>> getAuthItems();

    Flowable<List<Auth>> getAuthItemById(int AuthItemId);

    Auth getAuth(String Username,String Password);

    void emptyAuth();

    int size();

    Auth getAuthNo(String AuthItem);

    void insertToAuth(Auth... Auth);

    void updateAuth(Auth... Auth);

    void deleteAuth(Auth... Auth);
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IAuthDataSources;
import xact.idea.camelia.Database.Model.Auth;

public class AuthRepository implements IAuthDataSources {
    public IAuthDataSources IAuthDataSources;
    public AuthRepository(IAuthDataSources IAuthDataSources){
        this.IAuthDataSources=IAuthDataSources;
    }
    private static  AuthRepository instance;

    public static AuthRepository getInstance(IAuthDataSources iCartDataSource){
        if(instance==null)
            instance= new AuthRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<Auth>> getAuthItems() {
        return IAuthDataSources.getAuthItems();
    }

    @Override
    public Flowable<List<Auth>> getAuthItemById(int AuthItemId) {
        return IAuthDataSources.getAuthItemById(AuthItemId);
    }

    @Override
    public Auth getAuth(String Username,String Password) {
        return IAuthDataSources.getAuth(Username, Password);
    }

    @Override
    public void emptyAuth() {
        IAuthDataSources.emptyAuth();
    }

    @Override
    public int size() {
        return IAuthDataSources.size();
    }

    @Override
    public Auth getAuthNo(String AuthItem) {
        return IAuthDataSources.getAuthNo(AuthItem);
    }

    @Override
    public void insertToAuth(Auth... Auth) {
        IAuthDataSources.insertToAuth(Auth);
    }

    @Override
    public void updateAuth(Auth... Auth) {
        IAuthDataSources.updateAuth(Auth);
    }

    @Override
    public void deleteAuth(Auth... Auth) {
        IAuthDataSources.deleteAuth(Auth);
    }
}

package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.AuthDao;
import xact.idea.camelia.Database.DataSourcesInterface.IAuthDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.IDivisionDataSources;
import xact.idea.camelia.Database.Model.Auth;

public class AuthDataSources implements IAuthDataSources {

    private AuthDao AuthDao;
    private static AuthDataSources instance;

    public AuthDataSources(AuthDao AuthDao){
        this.AuthDao=AuthDao;
    }
    public static AuthDataSources getInstance(AuthDao AuthDao){
        if(instance==null)
            instance = new AuthDataSources(AuthDao);
        return instance;

    }

    @Override
    public Flowable<List<Auth>> getAuthItems() {
        return AuthDao.getAuthItems();
    }

    @Override
    public Flowable<List<Auth>> getAuthItemById(int AuthItemId) {
        return AuthDao.getAuthItemById(AuthItemId);
    }

    @Override
    public Auth getAuth(String Username,String Password) {
        return AuthDao.getAuth(Username, Password);
    }

    @Override
    public void emptyAuth() {
        AuthDao.emptyAuth();
    }

    @Override
    public int size() {
        return AuthDao.value();
    }

    @Override
    public Auth getAuthNo(String AuthItem) {
        return AuthDao.getAuthNo(AuthItem);
    }

    @Override
    public void insertToAuth(Auth... Auth) {
        AuthDao.insertToAuth(Auth);
    }

    @Override
    public void updateAuth(Auth... Auth) {
        AuthDao.updateAuth(Auth);
    }

    @Override
    public void deleteAuth(Auth... Auth) {
        AuthDao.deleteAuth(Auth);
    }
}

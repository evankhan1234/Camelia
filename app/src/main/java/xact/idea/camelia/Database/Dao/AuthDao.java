package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Auth;

@Dao
public interface AuthDao {
    @Query("SELECT * FROM Auth")
    Flowable<List<Auth>> getAuthItems();

    @Query("SELECT * FROM Auth WHERE id=:AuthItemId")
    Flowable<List<Auth>> getAuthItemById(int AuthItemId);

    @Query("SELECT * FROM Auth WHERE email=:Username AND password=:Password")
    Auth getAuth(String Username,String Password);

    @Query("SELECT * FROM Auth WHERE role_code=:AuthItem")
    Auth getAuthNo(String AuthItem);

    @Query("Select Count(id)  FROM Auth")
    int value();

    @Query("DELETE  FROM Auth")
    void emptyAuth();

    @Insert
    void insertToAuth(Auth... Auth);

    @Update
    void updateAuth(Auth... Auth);

    @Delete
    void deleteAuth(Auth... Auth);

}

package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.Upazila;

public interface IUpazilaDatasources {
    Flowable<List<Upazila>> getUpazilaItems();

    Flowable<List<Upazila>> getUpazilaItemById(int UpazilaItemId);

    Upazila getUpazila(String UpazilaItem);

    void emptyUpazila();

    int size();

    Upazila getUpazilaNo(String UpazilaItem);

    void insertToUpazila(Upazila... Upazilas);

    void updateUpazila(Upazila... Upazilas);

    void deleteUpazila(Upazila... Upazilas);
}

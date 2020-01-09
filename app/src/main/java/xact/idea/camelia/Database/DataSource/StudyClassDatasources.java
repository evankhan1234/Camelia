package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.StudyClassDao;
import xact.idea.camelia.Database.DataSourcesInterface.IStudyClassDatasources;
import xact.idea.camelia.Database.Model.StudyClass;

public class StudyClassDatasources implements IStudyClassDatasources {

    private StudyClassDao StudyClassDao;
    private static StudyClassDatasources instance;

    public StudyClassDatasources(StudyClassDao StudyClassDao){
        this.StudyClassDao=StudyClassDao;
    }
    public static StudyClassDatasources getInstance(StudyClassDao StudyClassDao){
        if(instance==null)
            instance = new StudyClassDatasources(StudyClassDao);
        return instance;

    }

    @Override
    public Flowable<List<StudyClass>> getStudyClassItems() {
        return StudyClassDao.getStudyClassItems();
    }

    @Override
    public Flowable<List<StudyClass>> getStudyClassItemById(int StudyClassItemId) {
        return StudyClassDao.getStudyClassItemById(StudyClassItemId);
    }

    @Override
    public StudyClass getStudyClass(String StudyClassItem) {
        return StudyClassDao.getStudyClass(StudyClassItem);
    }

    @Override
    public void emptyStudyClass() {
        StudyClassDao.emptyStudyClass();
    }

    @Override
    public int size() {
        return StudyClassDao.value();
    }

    @Override
    public StudyClass getStudyClassNo(String StudyClassItem) {
        return StudyClassDao.getStudyClassNo(StudyClassItem);
    }

    @Override
    public void insertToStudyClass(StudyClass... StudyClass) {
        StudyClassDao.insertStudyClass(StudyClass);
    }

    @Override
    public void updateStudyClass(StudyClass... StudyClass) {
        StudyClassDao.updateStudyClass(StudyClass);
    }

    @Override
    public void deleteStudyClass(StudyClass... StudyClass) {
        StudyClassDao.deleteStudyClass(StudyClass);
    }
}

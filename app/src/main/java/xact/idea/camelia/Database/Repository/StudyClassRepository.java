package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IStudyClassDatasources;
import xact.idea.camelia.Database.Model.StudyClass;

public class StudyClassRepository implements IStudyClassDatasources {
    public IStudyClassDatasources IStudyClassDatasources;
    public StudyClassRepository(IStudyClassDatasources IStudyClassDatasources){
        this.IStudyClassDatasources=IStudyClassDatasources;
    }
    private static  StudyClassRepository instance;

    public static StudyClassRepository getInstance(IStudyClassDatasources iCartDataSource){
        if(instance==null)
            instance= new StudyClassRepository(iCartDataSource);
        return instance;

    }

    @Override
    public Flowable<List<StudyClass>> getStudyClassItems() {
        return IStudyClassDatasources.getStudyClassItems();
    }

    @Override
    public Flowable<List<StudyClass>> getStudyClassItemById(int StudyClassItemId) {
        return IStudyClassDatasources.getStudyClassItemById(StudyClassItemId);
    }

    @Override
    public StudyClass getStudyClass(String StudyClassItem) {
        return IStudyClassDatasources.getStudyClass(StudyClassItem);
    }

    @Override
    public void emptyStudyClass() {
        IStudyClassDatasources.emptyStudyClass();
    }

    @Override
    public int size() {
        return IStudyClassDatasources.size();
    }

    @Override
    public void updateLanguage(String lang) {
        IStudyClassDatasources.updateLanguage(lang);
    }

    @Override
    public StudyClass getStudyClassNo(String StudyClassItem) {
        return IStudyClassDatasources.getStudyClassNo(StudyClassItem);
    }

    @Override
    public void insertToStudyClass(StudyClass... StudyClass) {
        IStudyClassDatasources.insertToStudyClass(StudyClass);
    }

    @Override
    public void updateStudyClass(StudyClass... StudyClass) {
        IStudyClassDatasources.updateStudyClass(StudyClass);
    }

    @Override
    public void deleteStudyClass(StudyClass... StudyClass) {
        IStudyClassDatasources.deleteStudyClass(StudyClass);
    }
}

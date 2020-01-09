package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.StudyClass;

public interface IStudyClassDatasources {
    Flowable<List<StudyClass>> getStudyClassItems();

    Flowable<List<StudyClass>> getStudyClassItemById(int StudyClassItemId);

    StudyClass getStudyClass(String StudyClassItem);

    void emptyStudyClass();

    int size();

    StudyClass getStudyClassNo(String StudyClassItem);

    void insertToStudyClass(StudyClass... StudyClass);

    void updateStudyClass(StudyClass... StudyClass);

    void deleteStudyClass(StudyClass... StudyClass);
}

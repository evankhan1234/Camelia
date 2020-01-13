package xact.idea.camelia.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Dao.MemberHabitDao;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberHabitDataSources;
import xact.idea.camelia.Database.Model.MemberHabit;

public class MemberHabitDataSources implements IMemberHabitDataSources {
    private MemberHabitDao MemberHabitDao;
    private static MemberHabitDataSources instance;

    public MemberHabitDataSources(MemberHabitDao MemberHabitDao){
        this.MemberHabitDao=MemberHabitDao;
    }
    public static MemberHabitDataSources getInstance(MemberHabitDao MemberHabitDao){
        if(instance==null)
            instance = new MemberHabitDataSources(MemberHabitDao);
        return instance;

    }
    @Override
    public Flowable<List<MemberHabit>> getMemberHabitItems() {
        return MemberHabitDao.getMemberHabitItems();
    }

    @Override
    public Flowable<List<MemberHabit>> getMemberHabitItemById(int MemberHabitItemId) {
        return MemberHabitDao.getMemberHabitItemById(MemberHabitItemId);
    }

    @Override
    public MemberHabit getMemberHabit(String MemberHabitItem) {
        return MemberHabitDao.getMemberHabit(MemberHabitItem);
    }

    @Override
    public void emptyMemberHabit() {
        MemberHabitDao.emptyMemberHabit();
    }

    @Override
    public int size() {
        return MemberHabitDao.value();
    }

    @Override
    public MemberHabit getMemberHabitNo(String MemberHabitItem) {
        return MemberHabitDao.getMemberHabitNo(MemberHabitItem);
    }

    @Override
    public void insertToMemberHabit(MemberHabit... MemberHabit) {
        MemberHabitDao.insertToMemberHabit(MemberHabit);
    }

    @Override
    public void updateMemberHabit(MemberHabit... MemberHabit) {
        MemberHabitDao.updateMemberHabit(MemberHabit);
    }

    @Override
    public void deleteMemberHabit(MemberHabit... MemberHabit) {
        MemberHabitDao.deleteMemberHabit(MemberHabit);
    }
}

package xact.idea.camelia.Database.Repository;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberHabitDataSources;
import xact.idea.camelia.Database.DataSourcesInterface.IMemberHabitDataSources;
import xact.idea.camelia.Database.Model.MemberHabit;

public class MemberHabitRepository implements IMemberHabitDataSources {
    public IMemberHabitDataSources IMemberHabitDataSources;
    public MemberHabitRepository(IMemberHabitDataSources IMemberHabitDataSources){
        this.IMemberHabitDataSources=IMemberHabitDataSources;
    }
    private static  MemberHabitRepository instance;

    public static MemberHabitRepository getInstance(IMemberHabitDataSources iCartDataSource){
        if(instance==null)
            instance= new MemberHabitRepository(iCartDataSource);
        return instance;

    }
    @Override
    public Flowable<List<MemberHabit>> getMemberHabitItems() {
        return IMemberHabitDataSources.getMemberHabitItems();
    }

    @Override
    public Flowable<List<MemberHabit>> getMemberHabitItemById(int MemberHabitItemId) {
        return IMemberHabitDataSources.getMemberHabitItemById(MemberHabitItemId);
    }

    @Override
    public MemberHabit getMemberHabit(String MemberHabitItem) {
        return IMemberHabitDataSources.getMemberHabit(MemberHabitItem);
    }

    @Override
    public void emptyMemberHabit() {
        IMemberHabitDataSources.emptyMemberHabit();
    }

    @Override
    public int size() {
        return IMemberHabitDataSources.size();
    }

    @Override
    public MemberHabit getMemberHabitNo(String MemberHabitItem) {
        return IMemberHabitDataSources.getMemberHabitNo(MemberHabitItem);
    }

    @Override
    public void insertToMemberHabit(MemberHabit... MemberHabit) {
        IMemberHabitDataSources.insertToMemberHabit(MemberHabit);
    }

    @Override
    public void updateMemberHabit(MemberHabit... MemberHabit) {
        IMemberHabitDataSources.updateMemberHabit(MemberHabit);
    }

    @Override
    public void deleteMemberHabit(MemberHabit... MemberHabit) {
        IMemberHabitDataSources.deleteMemberHabit(MemberHabit);
    }
}

package xact.idea.camelia.Database.DataSourcesInterface;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.Model.MemberHabit;

public interface IMemberHabitDataSources {
    Flowable<List<MemberHabit>> getMemberHabitItems();

    Flowable<List<MemberHabit>> getMemberHabitItemById(int MemberHabitItemId);

    MemberHabit getMemberHabit(String MemberHabitItem);

    void emptyMemberHabit();

    int size();

    MemberHabit getMemberHabitNo(String MemberHabitItem);

    void insertToMemberHabit(MemberHabit... MemberHabit);

    void updateMemberHabit(MemberHabit... MemberHabit);

    void deleteMemberHabit(MemberHabit... MemberHabit);
}

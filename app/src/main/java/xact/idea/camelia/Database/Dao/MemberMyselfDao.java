package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.Model.MemberMyself;

@Dao
public interface MemberMyselfDao {
    @Query("SELECT * FROM MemberMyself")
    Flowable<List<MemberMyself>> getMemberMyselfItems();
    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfId")
    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfId);
    @Query("SELECT * FROM MemberMyself WHERE MobileNumber=:MemberMyselfItem")
    MemberMyself getMemberMyself(String MemberMyselfItem);
    @Query("SELECT * FROM MemberMyself WHERE id=:MemberMyselfItem")
    MemberMyself getMemberMyselfNo(int MemberMyselfItem);
    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfItem AND HouseHeadId=1")
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    @Query("Select Count(id)  FROM MemberMyself")
    int value();
    @Query("Select Max(id)  FROM MemberMyself")
    int maxValue();
    @Query("DELETE  FROM MemberMyself")
    void emptyMemberMyself();
    @Insert
    void insertToMemberMyself(MemberMyself...MemberMyself);
    @Update
    void updateMemberMyself(MemberMyself...MemberMyself);
    @Delete
    void deleteMemberMyself(MemberMyself...MemberMyself);
    @Query("SELECT * FROM ( SELECT q.MemberId, COUNT(q.MemberId) TypeCount FROM (SELECT ms.MemberId,ms.Type FROM Measurements ms WHERE ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')GROUP BY ms.MemberId,ms.type) q GROUP BY q.MemberId HAVING COUNT(q.MemberId) > 5 )q2 INNER JOIN MemberMyself members ON q2.MemberId = members.MobileNumber")
    Flowable<List<MemberMyself>> getCompleteMembers();
    @Query("SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId  Group BY Member.id HAVING  Count(Measure.id)<6")
    Flowable<List<MemberMyself>> getInCompleteMembers();
    @Query("SELECT  * FROM MemberMyself as Member left join Measurements as Measure ON Member.MobileNumber=Measure.MemberId  WHERE Measure.id  IS NULL")
    Flowable<List<MemberMyself>> getInCompleteMembersFor();

    @Query("SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId where  Measure.Refer='UHC' Group BY Member.id")
    Flowable<List<MemberMyself>> getReferMembersFor();

    @Query("SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId where  Measure.Refer='Follow' Group BY Member.id")
    Flowable<List<MemberMyself>> getFollowUpMembersFor();


    @Query("SELECT Count(*)as Total FROM ( SELECT q.MemberId, COUNT(q.MemberId) TypeCount FROM (SELECT ms.MemberId,ms.Type FROM Measurements ms WHERE ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')GROUP BY ms.MemberId,ms.type) q GROUP BY q.MemberId HAVING COUNT(q.MemberId) > 5 )q2 INNER JOIN MemberMyself members ON q2.MemberId = members.MobileNumber")
    int CompleteCount();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId  Group BY Member.id HAVING  Count(Measure.id)<6)")
    int InCompleteCount1();
    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member left join Measurements as Measure ON Member.MobileNumber=Measure.MemberId  WHERE Measure.id  IS NULL)")
    int InCompleteCount2();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId where  Measure.Refer='UHC' Group BY Member.id)")
    int referCount();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MobileNumber=Measure.MemberId where  Measure.Refer='Follow' Group BY Member.id)")
    int followUpCount();
}

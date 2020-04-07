package xact.idea.camelia.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import xact.idea.camelia.Database.AnotherModel.Count;
import xact.idea.camelia.Database.AnotherModel.HHDashboardModel;
import xact.idea.camelia.Database.AnotherModel.Members;
import xact.idea.camelia.Database.AnotherModel.SentSyncModel;
import xact.idea.camelia.Database.AnotherModel.SummaryModel;
import xact.idea.camelia.Database.AnotherModel.VisitMyself;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Database.Model.ReferHistory;

@Dao
public interface MemberMyselfDao {
    @Query("SELECT * FROM MemberMyself")
    Flowable<List<MemberMyself>> getMemberMyselfItems();

    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfId")
    Flowable<List<MemberMyself>> getMemberMyselfItemById(String MemberMyselfId);
    @Query("SELECT * FROM MemberMyself WHERE `From` ='CC'")
    Flowable<List<MemberMyself>> getMemberListCC();
    @Query("SELECT * FROM MemberMyself WHERE NationalId=:MemberMyselfItem")
    MemberMyself getMemberMyself(String MemberMyselfItem);

    @Query("SELECT * FROM MemberMyself WHERE id=:MemberMyselfItem")
    MemberMyself getMemberMyselfNo(int MemberMyselfItem);

    @Query("SELECT * FROM MemberMyself WHERE UniqueCode=:MemberMyselfItem")
    MemberMyself getMemberId(String MemberMyselfItem);

    @Query("SELECT * FROM MemberMyself WHERE UniqueId=:MemberMyselfItem AND HouseHeadId=1")
    MemberMyself getMemberMyselfForHousehold(String MemberMyselfItem);
    @Query("UPDATE  MemberMyself SET VisitDate=:date where UniqueCode=:member")
    void updateReciver(String date,String member);
    @Query("Select Count(id)  FROM MemberMyself")
    int value();
    @Query("Select Count(id)  FROM MemberMyself where UniqueId=:uniqueId")
    int count(String uniqueId);
    @Query("UPDATE  MemberMyself SET `From`=:from,`To`=:to,VisitDate=:date where UniqueCode=:memberId")
    void updateReciverAgain(String from,String to,String date,String memberId);
    @Query("SELECT * FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Type='1' AND refer.Reason='1' group by member.UniqueCode")
    Flowable<List<VisitMyself>> getMemberListForRefer();
    @Query("SELECT * FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Type='0' AND refer.Reason='1'group by member.UniqueCode")
    Flowable<List<VisitMyself>> getMemberListForFollowUp();
    @Query("SELECT * FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Type='2' AND refer.Reason='1'group by member.UniqueCode")
    Flowable<List<VisitMyself>> getReferMembersFor();
    @Query("Select Count(id)  FROM MemberMyself  where Status='0' and CreatedDate BETWEEN :from AND :to order By CreatedDate Desc")
    int notSync(Date from,Date to);
    @Query("Select Count(id)  FROM MemberMyself  where Status='1' and CreatedDate BETWEEN :from AND :to order By CreatedDate Desc")
    int Sync(Date from,Date to);

    @Query("Select Max(id)  FROM MemberMyself")
    int maxValue();

    @Query("DELETE  FROM MemberMyself")
    void emptyMemberMyself();

    @Insert
    void insertToMemberMyself(MemberMyself... MemberMyself);

    @Update
    void updateMemberMyself(MemberMyself... MemberMyself);

    @Delete
    void deleteMemberMyself(MemberMyself... MemberMyself);

    @Query("SELECT  ms.FullName,ms.CreatedDate,ms.MemberId,ms.UniqueId,hs.VillageName FROM  Household as hs inner join   MemberMyself as ms  on hs.UniqueId=ms.UniqueId where ms.Status='1' and ms.CreatedDate BETWEEN :from AND :to order By CreatedDate Desc")
    Flowable<List<SentSyncModel>> getSyncMembers(Date from,Date to);
    @Query("SELECT  ms.FullName,ms.CreatedDate,ms.MemberId,ms.UniqueId,hs.VillageName FROM  Household as hs inner join   MemberMyself as ms  on hs.UniqueId=ms.UniqueId where ms.Status='0' and ms.CreatedDate BETWEEN :from AND :to order By CreatedDate Desc")
    Flowable<List<SentSyncModel>> getNotSyncMembers(Date from,Date to);

    @Query("SELECT * FROM ( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (SELECT ms.MemberIds,ms.Type FROM Measurements ms WHERE ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')GROUP BY ms.MemberIds,ms.type) q GROUP BY q.MemberIds HAVING COUNT(q.MemberIds) > 5 )q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode")
    Flowable<List<MemberMyself>> getCompleteMembers();

    @Query("SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.UniqueCode=Measure.MemberIds  Group BY Member.id HAVING  Count(Measure.id)<6")
    Flowable<List<MemberMyself>> getInCompleteMembers();

    @Query("SELECT  * FROM MemberMyself as Member left join Measurements as Measure ON Member.UniqueCode=Measure.MemberIds  WHERE Measure.id  IS NULL")
    Flowable<List<MemberMyself>> getInCompleteMembersFor();


    @Query("SELECT  * FROM MemberMyself as Member  where  Member.`from`='CC' Group BY Member.id")
    Flowable<List<MemberMyself>> getReferMembersForCC();

    @Query("SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MemberId=Measure.MemberIds where  Measure.Refer='Follow' Group BY Member.id")
    Flowable<List<MemberMyself>> getFollowUpMembersFor();


    @Query("SELECT Count(*)as Total FROM ( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (SELECT ms.MemberIds,ms.Type FROM Measurements ms WHERE ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')GROUP BY ms.MemberIds,ms.type) q GROUP BY q.MemberIds HAVING COUNT(q.MemberIds) > 5 )q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.MemberId")
    int CompleteCount();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MemberId=Measure.MemberIds  Group BY Member.id HAVING  Count(Measure.id)<6)")
    int InCompleteCount1();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member left join Measurements as Measure ON Member.MemberId=Measure.MemberIds  WHERE Measure.id  IS NULL)")
    int InCompleteCount2();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MemberId=Measure.MemberIds where  Measure.Refer='UHC' Group BY Member.id)")
    int referCount();

    @Query("SELECT Count(*)as Total FROM(SELECT  * FROM MemberMyself as Member inner join Measurements as Measure ON Member.MemberId=Measure.MemberIds where  Measure.Refer='Follow' Group BY Member.id)")
    int followUpCount();

    @Query("select sum(UHC) UHC,sum(Incomplete1) Incomplete1,sum(Incomplete2) Incomplete2,sum(Follow) Follow,sum(Total5) Complete from (\n" +
            "SELECT  Count(*)as UHC,0 Incomplete1,0 Incomplete2,0 Follow,0 Total5 FROM MemberMyself as Member inner join (select distinct MemberIds,refer from Measurements) as Measure ON Member.UniqueCode=Measure.MemberIds where  \n" +
            "Member.`To`='CC'\n" +
            "UNION all\n" +
            "SELECT  0 UHC,Count(*)as Incomplete1,0 Incomplete2,0 Follow,0 Complete FROM MemberMyself as Member\n" +
            " left join  Measurements as Measure ON Member.UniqueCode=Measure.MemberIds  where Measure.id is null\n" +
            "UNION all\n" +
            "SELECT 0 UHC,0 Incomplete1,count(*) Incomplete2,0 Follow,0 Complete FROM\n" +
            "( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            " \n" +
            "SELECT ms.MemberIds,ms.Type FROM (select distinct MemberIds,type from Measurements) ms\n" +
            "WHERE\n" +
            "ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "GROUP BY ms.MemberIds,ms.type\n" +
            ") q\n" +
            "GROUP BY q.MemberIds\n" +
            "HAVING COUNT(q.MemberIds) < 6\n" +
            ") q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode\n" +
            "UNION all\n" +
            "SELECT 0 UHC,0 Incomplete1,0 Incomplete2, Count(*) as Follow,0 Complete FROM MemberMyself as Member inner join (select distinct MemberIds,refer from Measurements) as Measure ON Member.UniqueCode=Measure.MemberIds where  Measure.Refer='Follow' Group BY Member.id\n" +
            "UNION all\n" +
            "SELECT 0 UHC,0 Incomplete1,0 Incomplete2,0 Follow, Count(*)as Complete FROM\n" +
            "( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            " \n" +
            "SELECT ms.MemberIds,ms.Type FROM (select distinct MemberIds,type from Measurements) ms\n" +
            "WHERE\n" +
            "ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "GROUP BY ms.MemberIds,ms.type\n" +
            ") q\n" +
            "GROUP BY q.MemberIds\n" +
            "HAVING COUNT(q.MemberIds) > 5\n" +
            ") q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode)")
    Count TotalCount();


    @Query("select sum(dibatis) dibatis,  sum(hypertension) hypertension, sum(heartdisease) heartdisease,sum(stroke) stroke, sum(Lung) Lung, sum(Ashma) Ashma, sum(Kidney) Kidney, sum(Cancer) Cancer, sum(Mental) Mental,sum(male) Male,sum(female) female,sum(total_member) total_member  from (\n" +
            "Select count(*) dibatis, 0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q49' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis, count(*) hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q50' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension, count(*) heartdisease ,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member  from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q51' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,count(*) stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q52' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,count(*) Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q53' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,count(*) Ashma ,0 Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q54' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,count(*) Kidney,0 Cancer,0 Mental,0 Male,0 female,0 total_member    from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q55' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,count(*) Cancer,0 Mental,0 Male,0 female,0 total_member   from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q56' and qs.answer='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,count(*) Mental,0 Male,0 female,0 total_member  from MemberMyself as ms inner join Questions as qs on ms.UniqueCode=qs.member_id where qs.question='Q53' and qs.answer='1' \n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,count(*) Male,0 female,0 total_member from MemberMyself where GenderId='1'\n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,count(*) Female,0 total_member from MemberMyself where GenderId='2' \n" +
            "union all\n" +
            "Select 0 dibatis,0 hypertension,0 heartdisease,0 stroke,0 Lung,0 Ashma,0 Kidney,0 Cancer,0 Mental,0 Male,0 Female,count(*) total_member from MemberMyself\n" +
            ")\n")
    HHDashboardModel hhModel();

    @Query("select datetime,sum(UHC) UHC,sum(Incomplete1) Incomplete1,sum(Incomplete2) Incomplete2,sum(Follow) Follow,sum(Total5) Complete from (\n" +
            "                      SELECT Date datetime, Count(*)as UHC,0 Incomplete1,0 Incomplete2,0 Follow,0 Total5 FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Date between :from and :to AND refer.Type='2' AND refer.Reason='1'group by member.UniqueCode\n" +
            "                     UNION all\n" +
            "                    SELECT CreatedDate datetime,0 UHC,Count(*)as Incomplete1,0 Incomplete2,0 Follow,0 Complete FROM MemberMyself as Member\n" +
            "                       left join  Measurements as Measure ON Member.UniqueCode=Measure.MemberIds  where Measure.id is null and Member.CreatedDate between :from and :to\n" +
            "                        UNION all\n" +
            "                       SELECT datetime,0 UHC,0 Incomplete1,count(*) Incomplete2,0 Follow,0 Complete FROM\n" +
            "                        ( SELECT datetime, q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            "                        \n" +
            "                        SELECT ms.datetime,ms.MemberIds,ms.Type FROM (select distinct MemberIds,type,datetime from Measurements where datetime between :from and :to) ms\n" +
            "                        WHERE\n" +
            "                        ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "                        GROUP BY ms.MemberIds,ms.type,ms.datetime\n" +
            "                        ) q\n" +
            "                       GROUP BY q.MemberIds,datetime\n" +
            "                        HAVING COUNT(q.MemberIds) < 6\n" +
            "                       ) q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode\n" +
            "                       UNION all\n" +
            "                        SELECT Date datetime,0 UHC,0 Incomplete1,0 Incomplete2, Count(*) as Follow,0 Complete FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Date between :from and :to AND refer.Type='1' AND refer.Reason='1'group by member.UniqueCode\n" +
            "                     UNION all\n" +
            "                       SELECT datetime,0 UHC,0 Incomplete1,0 Incomplete2,0 Follow, Count(*)as Complete FROM\n" +
            "                        ( SELECT datetime,q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            "                        \n" +
            "                        SELECT datetime,ms.MemberIds,ms.Type FROM (select distinct MemberIds,type,datetime from Measurements where datetime between :from and :to) ms\n" +
            "                        WHERE\n" +
            "                        ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "                       GROUP BY ms.MemberIds,ms.type,datetime\n" +
            "                        ) q\n" +
            "                        GROUP BY q.MemberIds,datetime\n" +
            "                        HAVING COUNT(q.MemberIds) > 5\n" +
            "                        ) q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode) where datetime is not null \n" +
            "                        group by datetime")
    Flowable<List<Count>> TotalCountByDateRange(Date from ,Date to);

    @Query("select sum(UHC) UHC,sum(Incomplete1) Incomplete1,sum(Incomplete2) Incomplete2,sum(Follow) Follow,sum(Total5) Complete from (\n" +
            "                       SELECT  Count(*)as UHC,0 Incomplete1,0 Incomplete2,0 Follow,0 Total5 FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Date = :from AND refer.Type='2' AND refer.Reason='1'group by member.UniqueCode\n" +
            "                       UNION all\n" +
            "                        SELECT  0 UHC,Count(*)as Incomplete1,0 Incomplete2,0 Follow,0 Complete FROM MemberMyself as Member\n" +
            "                       left join  Measurements as Measure ON Member.UniqueCode = Measure.MemberIds  where Measure.id is null and Member.CreatedDate = :from\n" +
            "                        UNION all\n" +
            "                       SELECT 0 UHC,0 Incomplete1,count(*) Incomplete2,0 Follow,0 Complete FROM\n" +
            "                       ( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            "                      \n" +
            "                       SELECT ms.MemberIds,ms.Type FROM (select distinct MemberIds,type from Measurements where datetime = :from) ms\n" +
            "                       WHERE\n" +
            "                       ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "                       GROUP BY ms.MemberIds,ms.type\n" +
            "                        ) q\n" +
            "                        GROUP BY q.MemberIds\n" +
            "                       HAVING COUNT(q.MemberIds) < 6\n" +
            "                        ) q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode\n" +
            "                        UNION all\n" +
            "                       SELECT 0 UHC,0 Incomplete1,0 Incomplete2, Count(*) as Follow,0 Complete FROM MemberMyself as member inner join ReferHistory as refer on member.UniqueCode=refer.MemberUniqueCode WHERE refer.Date = :from AND refer.Type='1' AND refer.Reason='1'group by member.UniqueCode\n" +
            "                       UNION all\n" +
            "                       SELECT 0 UHC,0 Incomplete1,0 Incomplete2,0 Follow, Count(*)as Complete FROM\n" +
            "                        ( SELECT q.MemberIds, COUNT(q.MemberIds) TypeCount FROM (\n" +
            "                        SELECT ms.MemberIds,ms.Type FROM (select distinct MemberIds,type from Measurements where datetime = :from) ms\n" +
            "                        WHERE\n" +
            "                        ms.type IN ('BMI','Diastolic','WHR','Systolic','Pulse','Diabetes')\n" +
            "                        GROUP BY ms.MemberIds,ms.type\n" +
            "                        ) q\n" +
            "                        GROUP BY q.MemberIds\n" +
            "                        HAVING COUNT(q.MemberIds) > 5\n" +
            "                        ) q2 INNER JOIN MemberMyself members ON q2.MemberIds = members.UniqueCode)")
    Count TotalCountByDate(Date from);


    @Query("SELECT DateTime , sum(Hypertension) Hypertension, sum(Obese) Obese,sum(OverWeight) OverWeight,sum(Diabetes) Diabetes FROM (\n" +
            "SELECT DateTime ,count(*) Hypertension, 0 Obese,0 OverWeight, 0 Diabetes FROM Measurements where Message='Hypertension = Refer to UHC!' and  Datetime=:from GROUP BY DateTime\n" +
            "union all\n" +
            "SELECT DateTime, 0 Hypertension, count(*) Obese,0 OverWeight, 0 Diabetes FROM Measurements where Message='Obese' and  Datetime=:from GROUP BY DateTime\n" +
            "union all\n" +
            "SELECT DateTime, 0 Hypertension, 0 Obese, count(*) OverWeight, 0 Diabetes  FROM Measurements where Message='OverWeight = Refer to UHC!' and  Datetime=:from GROUP BY DateTime\n" +
            "union all\n" +
            "SELECT DateTime, 0 Hypertension, 0 Obese, 0 OverWeight, count(*) Diabetes FROM Measurements where Message='Diabetes = Refer to UHC!' and  Datetime=:from GROUP BY DateTime) ")
    SummaryModel TotalSum(Date from);

    @Query("SELECT DateTime, sum(Hypertension) Hypertension, sum(Obese) Obese,sum(OverWeight) OverWeight,sum(Diabetes) Diabetes FROM (\n" +
            "SELECT MemberIds,DateTime, count(*) Hypertension, 0 Obese,0 OverWeight, 0 Diabetes FROM Measurements where Message='Hypertension = Refer to UHC!' and  Datetime between :from and :to GROUP BY MemberIds,DateTime\n" +
            "union all\n" +
            "SELECT MemberIds,DateTime, 0 Hypertension, count(*) Obese,0 OverWeight, 0 Diabetes FROM Measurements where Message='Obese' and  Datetime between :from and :to GROUP BY MemberIds,DateTime\n" +
            "union all\n" +
            "SELECT MemberIds,DateTime, 0 Hypertension, 0 Obese, count(*) OverWeight, 0 Diabetes  FROM Measurements where Message='OverWeight = Refer to UHC!' and  Datetime between :from and :to GROUP BY MemberIds,DateTime\n" +
            "union all\n" +
            "SELECT MemberIds,DateTime, 0 Hypertension, 0 Obese, 0 OverWeight, count(*) Diabetes FROM Measurements where Message='Diabetes = Refer to UHC!' and  Datetime between :from and :to GROUP BY MemberIds,DateTime) where DateTime is not null group by DateTime")
    Flowable<List<SummaryModel>> TotalListOfSum(Date from ,Date to);

}

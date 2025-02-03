package com.boot.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.boot.api.models.ApiRecords;
import jakarta.transaction.Transactional;

public interface ApiCallsRepo extends JpaRepository<ApiRecords, Integer>
{
	
	@Query("select i.registerMail from ApiRecords i where i.registerMail = :x")
	public String findEmail(@Param("x") String resgisterMail);
	
	
	@Query("select i from ApiRecords i where i.registerMail = :x")
	public ApiRecords findCompelete(@Param("x") String resgisterMail);

    @Modifying
    @Transactional
    @Query("update ApiRecords i set i.newsRequestCalls =:y  where i.registerMail = :x")
    public void updateNewsCount(@Param("x") String resgisterMail,@Param("y") Integer count);
    
    @Modifying
    @Transactional
    @Query("update ApiRecords i set i.apodRequestCalls =:y ,i.apodSearchKey =:z where i.registerMail = :x")
    public void updateApodCount(@Param("x") String resgisterMail,@Param("y") Integer count,@Param("z") String seachKey);
    
    @Modifying
    @Transactional
    @Query("update ApiRecords i set i.pexelRequestCalls =:y ,i.pexelSearchKey =:z where i.registerMail = :x")
    public void updatePexelCount(@Param("x") String resgisterMail,@Param("y") Integer count,@Param("z") String seachKey);
     
    @Modifying
    @Transactional
    @Query("update ApiRecords i set i.newsRequestCalls =:y , i.newsTimeStamp =:z where i.registerMail = :x")
    public void updateTimeStamp(@Param("x") String resgisterMail,@Param("y") Integer count,@Param("z") String time);
    
    @Modifying
    @Transactional
    @Query("update ApiRecords i set i.searchKey =:y  where i.registerMail = :x")
    public void updateSearchKey(@Param("x") String resgisterMail,@Param("y") String seachKey);
    
    @Modifying
    @Transactional
    @Query("update ApiRecords i set  i.nextTimeStamp =:z where i.registerMail = :x")
    public void updateNext12hrTimeStamp(@Param("x") String resgisterMail,@Param("z") String time);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM ApiRecords a WHERE a.registerMailId = :regisId")
    void deleteApiRecordsByRegisterId(@Param("regisId") int regisId);

}

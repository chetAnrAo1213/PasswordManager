package com.boot.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.models.UserRegistrationDetails;

import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;

public interface RegistrationRepo extends CrudRepository<UserRegistrationDetails, Integer>
{

	@Query("select i.urq.fq, i.urq.sq, i.urq.tq from UserRegistrationDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public List<String> findEmailQuestions(@Param("x") String mailid);

	@Query("select i.email from UserRegistrationDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.cache.retriveMode",value="USE"),
		@QueryHint(name="jakarta.persistence.cache.storeMode",value="USE"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public String findEmail(@Param("x") String mailid);

	@Query("select i from UserRegistrationDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public UserRegistrationDetails findCompelete(@Param("x") String mailid);
	/*___________________________________________________________________________________________________________________*/	
	
	@Modifying
	@Transactional
    @Query("update UserRegistrationDetails i set i.cpassword = :password, i.cpin = :pin where i.email = :email")
    public int updatePasswordAndPin(@Param("password") String password, @Param("pin") String pin, @Param("email") String email);
	/*___________________________________________________________________________________________________________________*/	
	
	@Modifying
	@Transactional
    @Query("update UserLoginDetails i set i.password = :password, i.pin = :pin where i.email = :email")
    public int modifyPasswordAndPin(@Param("password") String password, @Param("pin") String pin, @Param("email") String email);
	
	@Query("select i.email from UserRegistrationDetails i")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public List<String> findAllEmails();
}

package com.boot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.models.UserLoginDetails;

import jakarta.persistence.QueryHint;

public interface LoginRepo extends CrudRepository<UserLoginDetails, Integer>
{
	
	@Query("select i from UserLoginDetails i where i.email= :x and i.password =:y")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public UserLoginDetails findByMail(@Param("x") String mailId, @Param("y")String password);
/*___________________________________________________________________________________________________________________*/	
	
	@Query("select i.pin from UserLoginDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public String findPasswordAndPin(@Param("x") String MailId);
}

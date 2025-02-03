package com.boot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.boot.models.UserRegistrationDetails;
import com.boot.models.UserRegistrationQuestions;

import jakarta.persistence.QueryHint;

@Repository
public interface QuestionsRepo extends CrudRepository<UserRegistrationQuestions, Integer>
{

	@Query("select i.email from UserRegistrationDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public String findEmail(@Param("x") String mailid);
	/*___________________________________________________________________________________________________________________*/	
	
	@Query(" select i from UserRegistrationDetails i where i.email = :x ")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public UserRegistrationDetails findAnswers(@Param("x") String mailid);
	/*___________________________________________________________________________________________________________________*/	
	
	@Query(" select i.cpin from UserRegistrationDetails i where i.email = :x ")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public String findPin(@Param("x") String mailid);
}

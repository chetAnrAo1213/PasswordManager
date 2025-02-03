package com.boot.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.models.UserPasswords;

import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;

@Repository
public interface PasswordRepo extends JpaRepository<UserPasswords, Integer>
{
	
	@Query("select i from UserPasswords i where i.registeredusermail = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public List<UserPasswords> findUserPasswords(@Param("x") String mailid);
	/*___________________________________________________________________________________________________________________*/	
	

	/*____________________________________________________________________________________________________________________*/
	@Query("select i from UserPasswords i where i.registeredusermail = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public Page<UserPasswords> findUserPasswordsPerPage(@Param("x") String mailid, Pageable pages);
	
	/*___________________________________________________________________________________________________________________*/		
	@Modifying
	@Transactional
	@Query("UPDATE UserPasswords u SET u.title = :title, u.about = :about, u.tags = :tags, u.createddate = :createddate, u.phone = :phone,"
			+ " u.pin = :pin, u.username = :username, u.mailid = :mailid, u.password = :password WHERE u.id = :id")
	public int updateAllById(@Param("id") int id, @Param("title") String title, @Param("about") String about,
			@Param("tags") String tags, @Param("createddate") String createddate, @Param("phone") String phone,
			@Param("pin") String pin, @Param("username") String username, @Param("mailid") String mailid,
			@Param("password") String password);
	
	@Modifying
	@Transactional
	@Query("UPDATE UserPasswords u SET u.password = :y WHERE u.mailid = :x")
	public void encryptPassword(@Param("x") String mailid,@Param("y") String pass);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM UserPasswords u WHERE u.registeredusermailid = :regisId")
	void deleteUserPasswordsByRegisterId(@Param("regisId") int regisId);


}

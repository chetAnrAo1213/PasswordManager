package com.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.boot.models.QuickNote;
import com.boot.models.UserRegistrationDetails;

import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;


public interface NotesRepo extends JpaRepository<QuickNote, Integer>
{

	@Query("select i.noteCount from UserRegistrationDetails i where i.email = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public int findNoteCount(@Param("x") String mailid);
	/*___________________________________________________________________________________________________________________*/	
	
	@Modifying
	@Transactional
	@Query("update UserRegistrationDetails i set i.noteCount =:y  where i.email = :x")
	public void updateNoteCount(@Param("x") String mailid,@Param("y") int notecount);
	/*___________________________________________________________________________________________________________________*/	
	
	@Modifying
	@Transactional
	@Query("update QuickNote q SET q.title = :title, q.tags = :tags, q.content = :content, q.lastModifiedDate = :lastModifiedDate WHERE q.id = :id")
	public void updateNote(@Param("id") int noteId, 
	                      @Param("title") String title, 
	                      @Param("tags") String tags, 
	                      @Param("content") String content,
	                      @Param("lastModifiedDate") String modified
	                      );
	/*___________________________________________________________________________________________________________________*/	
	
	@Query("select i from QuickNote i where i.registeredusermail = :x")
	@QueryHints({
		@QueryHint(name="org.hibernate.readOnly",value="true"),
		@QueryHint(name="org.hibernate.fetchSize",value="100"),
		@QueryHint(name="org.hibernate.cachable",value="true"),
		@QueryHint(name="jakarta.persistence.query.timeout",value="2000")
	})
	public QuickNote findNote(@Param("x") String mailid);
	
  /*_______________________________________________________________________________________________________________________*/
	@Modifying
	@Transactional
	@Query("DELETE FROM QuickNote q WHERE q.registeredusermailid = :regisId")
	void deleteNoteThroughRegisterId(@Param("regisId") int regisId);

}

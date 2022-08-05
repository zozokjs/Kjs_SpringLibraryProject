package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.web.dto.community.BFreeListInterface;


public interface SingleQuestionRepository extends JpaRepository<SingleQuestion, Integer>{
	
	@Query(value = "SELECT * FROM SingleQuestion ORDER BY id DESC", 
	countQuery = "SELECT count(*) FROM SingleQuestion ",
    nativeQuery = true)
	Page<SingleQuestion> findByAllDesc(Pageable pageable);
	
	
	/*@Query(value = "INSERT INTO Commentsq(userId, singleQuestionId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	public void  sqCommentSave(int userId, int boardId, String content);*/
}

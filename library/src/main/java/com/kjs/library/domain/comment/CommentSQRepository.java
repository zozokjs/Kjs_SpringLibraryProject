package com.kjs.library.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.community.SingleQuestion;

public interface CommentSQRepository extends JpaRepository<CommentSQ, Integer> {

	@Query(value = "INSERT INTO Commentsq(userId, singleQuestionId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	public void  commentSQSave(int userId, int singleQuestionId, String content);

	@Query(value = "SELECT * FROM Commentsq WHERE singleQuestionId = :singleQuestionId", nativeQuery = true)
	public CommentSQ findbySingleQuestionId(int singleQuestionId);
	
}

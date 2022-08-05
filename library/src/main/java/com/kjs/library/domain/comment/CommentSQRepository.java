package com.kjs.library.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.community.SingleQuestion;

public interface CommentSQRepository extends JpaRepository<SingleQuestion, Integer> {

	@Query(value = "INSERT INTO Commentsq(userId, content, createDate) VALUES(?1, ?2, now())", nativeQuery = true)
	public void  commentSQSave(int userId, int boardId, String content);
	
}

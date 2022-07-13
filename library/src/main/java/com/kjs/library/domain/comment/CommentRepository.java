package com.kjs.library.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query(value = "INSERT INTO comment(userId, boardFreeId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	public void  commentSave(int userId, int boardId, String content);
	
}

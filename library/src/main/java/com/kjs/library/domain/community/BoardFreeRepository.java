package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.web.dto.community.BFreeListInterface;


public interface BoardFreeRepository extends JpaRepository<BoardFree, Integer>{

	/*	@Query(value = "SELECT * FROM BoardFree ORDER BY createDate DESC", 
		       countQuery = "SELECT count(*) FROM BoardFree ORDER BY createDate DESC",           
            nativeQuery = true)*/
	/*
	@Query(value = "SELECT b.id, b.title, b.content, b.readCount, b.createDate, b.editDate, u.username, count(c.boardFreeId) AS commentCount"
		+ " FROM Boardfree b "
		+ " INNER JOIN Comment c "
		+ " ON b.id = c.boardFreeId "
		+ " INNER JOIN User u "
		+ " ON b.userId = u.id "
		+ " GROUP BY c.boardFreeId "
		+ " ORDER BY b.createDate desc "*/
	@Query(value = "SELECT b.id, b.title, b.content, b.readCount, b.createDate, b.editDate, u.username,  COUNT(c.id) AS commentCount"
			+ " FROM Boardfree b "
			+ " Left outer JOIN Comment c"
			+ " ON b.id = c.boardFreeId"
			+ " INNER JOIN User u"
			+ " ON b.userId = u.id"
			+ " GROUP BY b.id"
			+ " ORDER BY b.id desc",
    countQuery = "SELECT count(*) FROM BoardFree ORDER BY createDate DESC", 
    nativeQuery = true)
	Page<BFreeListInterface> findByAllDesc(Pageable pageable);
	//SELECT b.id, b.content, b.userId, b.createDate, b.title,b.editDate, count(c.boardFreeId) AS commentCount FROM Boardfree b INNER JOIN Comment c ON b.id = c.boardFreeId GROUP BY c.boardFreeId ORDER BY b.createDate desc 

	
}

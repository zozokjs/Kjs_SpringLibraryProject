package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.web.dto.community.BFreeListInterface;
import com.kjs.library.web.dto.community.UserBoardHistoryInterface;


public interface BoardFreeRepository extends JpaRepository<BoardFree, Integer>{
	
	/** 
	 * 자유게시판 목록 조회(삭제된 것(useState = false) 제외)
	 * */
	@Query(value = "SELECT b.id, b.title, b.content, b.readCount, b.createDate, b.editDate, b.useState, u.username,  COUNT(c.id) AS commentCount"
			+ " FROM BoardFree b "
			+ " Left outer JOIN Comment c"
			+ " ON b.id = c.boardFreeId"
			+ " INNER JOIN User u"
			+ " ON b.userId = u.id"
			+ " GROUP BY b.id"
			+"  HAVING b.useState = TRUE "
			+ " ORDER BY b.id desc",
    countQuery = "SELECT count(*) FROM BoardFree WHERE useState = TRUE  ORDER BY createDate DESC", 
    nativeQuery = true)
	Page<BFreeListInterface> findByAllDesc(Pageable pageable);
	//SELECT b.id, b.content, b.userId, b.createDate, b.title,b.editDate, count(c.boardFreeId) AS commentCount FROM Boardfree b INNER JOIN Comment c ON b.id = c.boardFreeId GROUP BY c.boardFreeId ORDER BY b.createDate desc 

	
	/** 
	 * userId가 작성한 자유게시판, 1대1게시판 목록 조회(삭제된 것(useState = false) 제외)
	 * 여기서 sequence는 행 출력 번호를 뜻함
	 * */
	@Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY createDate) AS sequence, A.id, A.title, A.userId,  A.flag , A.createDate "
			+ " FROM ("
			+ " SELECT b.id, b.title, b.userId, b.createDate, 1 AS flag"
			+ " FROM Boardfree b WHERE b.userId = :userId AND b.useState = TRUE"
			+ " UNION"
			+ " SELECT s.id, s.title, s.userId, s.createDate, 2 AS flag"
			+ " FROM Singlequestion s WHERE userId = :userId AND useState = TRUE ) AS A"
			+ " ORDER BY createDate desc",
	countQuery=  "SELECT count(*) FROM ("
			+ " SELECT b.id, b.title, b.userId, b.createDate, 1 AS flag"
			+ " FROM Boardfree b WHERE b.userId = :userId AND b.useState = TRUE"
			+ " UNION"
			+ " SELECT s.id, s.title, s.userId, s.createDate, 2 AS flag"
			+ " FROM Singlequestion s WHERE userId = :userId AND useState = TRUE ) A"
			+ " ORDER BY createDate desc",
	nativeQuery = true)
	Page<UserBoardHistoryInterface> findBoardHistoryByUserId( int userId, Pageable pageable);
	
	
}

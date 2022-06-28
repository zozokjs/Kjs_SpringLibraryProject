package com.kjs.library.domain.lend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.book.Samebook;
import com.kjs.library.web.dto.lend.UserLendListInterface;

public interface LendRepository  extends JpaRepository<Lend, Integer>{
	
	//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
	@Query(value=" SELECT id FROM samebook where bookId = :bookId AND lendState = 0 ORDER BY id LIMIT 1" , nativeQuery = true)
	int findKdcCallSignBybookid(int bookId);
	
	
	//대출 가능 여부 확인
	@Query(value="SELECT * FROM lend WHERE bookId =  :bookId AND userId = :userId AND returnDate IS NULL" , nativeQuery = true)
	Lend findLendAbleByBookIdAndUserId(int bookId, int userId);
	
	
	//잔여 책 존재여부
	@Query(value = "SELECT id FROM samebook where bookId = :bookId AND lendState = 0", nativeQuery = true)
	List<Integer> findLendAbleSamebookVolume(int bookId);
	

	//사용자가 대출 했으면서 반납하지 않은 목록의 lendId,  Book title, Book writer, Lend createDate, Lend returnPlanDate
	@Query(value="SELECT L.id, B.id AS bookId, B.title, B.writer,  B.publish,  B.bindType,  L.createDate, L.returnPlanDate FROM(SELECT returnPlanDate, samebookId, bookId, id, createDate FROM lend WHERE returnDate IS NULL AND userId = :userId) AS L INNER JOIN samebook AS S ON L.samebookId = S.id INNER JOIN book AS B ON L.bookId = B.id;",nativeQuery = true)
	List<UserLendListInterface> findUserLendListByUserId(int userId);
		
	
	//사용자가 대출 + 반납한 모든 정보
	@Query(value="SELECT L.id,  L.returnDate, B.id AS bookId,  B.title, B.writer,  B.publish,  B.bindType,  L.createDate, L.returnPlanDate FROM(SELECT returnPlanDate, samebookId, bookId, id, createDate, returnDate FROM lend WHERE returnDate IS NOT NULL AND userId = :userId) AS L INNER JOIN samebook AS S ON L.samebookId = S.id INNER JOIN book AS B ON L.bookId = B.id;",nativeQuery = true)
	List<UserLendListInterface> findUserLendHistoryByUserId(int userId);
}


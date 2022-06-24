package com.kjs.library.domain.lend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.book.Samebook;

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
	
}

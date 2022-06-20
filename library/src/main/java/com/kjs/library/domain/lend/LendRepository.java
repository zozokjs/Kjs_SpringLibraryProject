package com.kjs.library.domain.lend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.book.Samebook;

public interface LendRepository  extends JpaRepository<Lend, Integer>{
	
	//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
	@Query(value="SELECT kdcCallSign FROM samebook WHERE bookId = :bookId AND lendId IS NULL ORDER BY id LIMIT 1;", nativeQuery = true)
	String findKdcCallSignBybookid(int bookId);
	
	
	//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
	@Query(value="SELECT id FROM samebook where bookId = :bookId ORDER BY id LIMIT 1;", nativeQuery = true)
	String findKdcCallSignBybookidTest(int bookId);
	
	
	
	//대출번호찾기
	@Query(value="SELECT lendListId FROM lend WHERE bookId = :bookId AND lendUserId = :userId ORDER BY id LIMIT 1;", nativeQuery = true)
	int findLendListIdByBookIdAndUserId(int bookId, int userId);
	
	//대출 가능 여부 확인
	@Query(value="SELECT * FROM lend WHERE lendBookId =  :bookId AND lendUserId = :userId " , nativeQuery = true)
	Lend findLendAbleByBookIdAndUserId(int bookId, int userId);
	
}

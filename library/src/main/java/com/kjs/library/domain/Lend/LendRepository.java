package com.kjs.library.domain.Lend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.domain.book.Samebook;

public interface LendRepository  extends JpaRepository<Lend, Integer>{
	
	@Query(value="SELECT kdcCallSign FROM samebook WHERE bookId = :bookId AND lendId IS NULL ORDER BY id LIMIT 1;", nativeQuery = true)
	String findKdcCallSignBybookid(int bookId);
	
	@Query(value="SELECT lendListId FROM lend WHERE bookId = :bookId AND lendUserId = :userId ORDER BY id LIMIT 1;", nativeQuery = true)
	int findLendListIdByBookIdAndUserId(int bookId, int userId);
}

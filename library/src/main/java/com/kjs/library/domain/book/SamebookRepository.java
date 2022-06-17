package com.kjs.library.domain.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SamebookRepository  extends JpaRepository<Samebook, Integer>{

	@Query(value="SELECT * FROM samebook WHERE bookId = :bookId", nativeQuery = true)
	List<Samebook> findBybookid(int bookId);
}

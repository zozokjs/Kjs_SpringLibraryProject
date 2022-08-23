package com.kjs.library.domain.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SamebookRepository  extends JpaRepository<Samebook, Integer>{

	@Query(value="SELECT * FROM Samebook WHERE bookId = :bookId AND usestate = true", nativeQuery = true)
	List<Samebook> findBybookid(int bookId);
	
	@Query(value = "SELECT lendState FROM Samebook WHERE id IN (:samebookIdList) AND useState = true", nativeQuery = true)
	List<Boolean> editAbleKdcCallSign(@Param("samebookIdList") List<Integer> samebookIdList);

	@Query(value="SELECT * FROM Samebook WHERE bookId = :bookId AND lendState = true", nativeQuery = true)
	List<Samebook> findLendStateBybookid(int bookId);

}

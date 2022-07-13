package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardFreeRepository extends JpaRepository<BoardFree, Integer>{

	@Query(value = "SELECT * FROM boardFree ORDER BY createDate DESC", 
		       countQuery = "SELECT count(*) FROM boardFree ORDER BY createDate DESC",           
            nativeQuery = true)
	Page<BoardFree> findByAllDesc(Pageable pageable);
	
}

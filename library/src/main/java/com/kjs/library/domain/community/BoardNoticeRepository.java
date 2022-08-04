package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.web.dto.boardFree.BoardFreeListInterface;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Integer>{

	@Query(value = "SELECT * FROM Boardnotice ORDER BY id DESC", 
    nativeQuery = true)
	Page<BoardNotice> findByAllDesc(Pageable pageable);

	
}

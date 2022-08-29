package com.kjs.library.domain.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kjs.library.web.dto.community.BFreeListInterface;
import com.kjs.library.web.dto.community.BNoticeResponseDto;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Integer>{

	@Query(value = "SELECT * FROM BoardNotice ORDER BY id DESC", 
	countQuery = "SELECT count(*) FROM BoardNotice ",
    nativeQuery = true)
	Page<BoardNotice> findByAllDesc(Pageable pageable);

	@Query(value = "SELECT * FROM BoardNotice ORDER BY id DESC limit 10", 
    nativeQuery = true)
	List<BoardNotice> findByAllDescTop10();
}

package com.kjs.library.domain.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitorCountRepository  extends JpaRepository<VisitorCount, Integer>{

	@Query(value = "SELECT countToday, countYesterday, countTotal FROM VisitorCount", nativeQuery = true)
	Integer findCount();
}

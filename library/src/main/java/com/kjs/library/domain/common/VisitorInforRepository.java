package com.kjs.library.domain.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitorInforRepository  extends JpaRepository<VisitorInfor, Integer>{

	
	@Query(value = "SELECT * FROM VisitorInfor WHERE ip = :ip AND cookieValue = :cookieValue", nativeQuery = true)
	VisitorInfor findInformationByipCookie(String ip, String cookieValue);
}

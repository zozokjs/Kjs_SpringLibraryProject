package com.kjs.library.domain.common;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 사용 안 됨
 * */
//방문자 수 집계를 위해 방문자 정보를 저장하는 테이블
@Entity // JPA를 위함. 디비에 테이블을 생성함
@Data
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 생성자
public class VisitorInfor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//접속자 아이피
	private String ip;
	
	//접속자 쿠키 정보
	private String cookieValue;
	
	private LocalDateTime createDate;

	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}

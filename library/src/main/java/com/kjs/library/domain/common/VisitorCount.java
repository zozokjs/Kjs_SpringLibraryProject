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

//방문자 수를 집계하는 테이블
@Entity // JPA를 위함. 디비에 테이블을 생성함
@Data
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 생성자
public class VisitorCount {
	
	@Id
	private int id; //자동 증가 안 함
	
	//오늘 방문자 수
	private int countToday;
	
	//어제 방문자 수
	private int countYesterday;
	
	//전체 방문자 수 
	private int countTotal;
	
	private LocalDateTime createDate;

	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}

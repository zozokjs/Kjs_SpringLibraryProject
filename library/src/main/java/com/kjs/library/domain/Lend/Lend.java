package com.kjs.library.domain.Lend;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//대여 내역
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//빌린 사람 아이디
	private int lendUserId;
	
	//빌린 책 아이디
	private int lendBookId;
	
	//빌린 날짜
	private String lendDate;
	
	//반납 날짜
	private String returnDate;
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}


package com.kjs.library.domain.book;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//같은 책을 구분하기 위한 테이블
@Builder
@Entity 
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class SameBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//책 아이디
	@JoinColumn(name = "bookId")
	@ManyToOne
	private Book bookId;
	
	//청구 기호
	//책 아이디
	@JoinColumn(name = "kdcCallSign")
	@ManyToOne
	private Book kdcCallSign;

	//수정 날자
	private String updateDate;
	
	//등록 날짜
	private LocalDateTime createDate;
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

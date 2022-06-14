package com.kjs.library.domain.book;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//한 권의 책을 정의하는 테이블
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
	//같은 책은 하나의 id를 가진다.
	//하나의 id는 여러 종류의 sameBook을 가진다. 그래서 sameBook이 N, book은 1
	@JsonIgnoreProperties({"user"})//이것은 지정된 것의 JSON 직렬화, 역직렬화를 무시한다. 
	@JoinColumn(name = "bookId")
	@ManyToOne
	private Book bookId;
	
	//청구 기호
	private String kdcCallSign;

	//수정 날자
	private String updateDate;
	
	//등록 날짜
	private LocalDateTime createDate;
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

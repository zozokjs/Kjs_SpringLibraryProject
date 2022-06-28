package com.kjs.library.domain.book;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//한 권의 책을 정의하는 테이블

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Samebook{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//책 아이디
	@JoinColumn(name = "bookId")
	@ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
	private Book book;
	
	/**대출 = true, 반납 = false
	 * **/
	private boolean lendState;
	
	//청구 기호
	private String kdcCallSign;

	//등록 날짜
	private LocalDateTime createDate;
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	public Samebook(Book book, String kdcCallSign) {
		super();
		this.kdcCallSign = kdcCallSign;
		this.book = book;
	}

	
	
	
}

package com.kjs.library.domain.book;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.Lend.Lend;
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
public class Samebook{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//책 아이디
	//한 권의 책은 한 종류의 책을 가진다. 여러 종류의 책을 가지는 건 불가능함.
	//한 종류의 책은 여러 권의 책을 가진다. 한 권만 가질 수도 있음.
	@JoinColumn(name = "bookId")
	@ManyToOne
	private Book book;
	
	//한 권의 책은 한 줄의 대여 정보를 가진다.     ->   책 : 대여정보 = 1 : 1
	//여러 권의 책은 한 줄의 대여 내역을 가진다.. 불가능
	//한 줄의 대여 내역은 많은 권의 책을 가진다.. 불가능
	//여러 줄의 대여 내역은 한 권의 책을 가진다.. 불가능
	//SAMEBOOK이 1, LEND가 1
	//
	///////////////////////////////////////////////////////////////
	@JoinColumn(name="lendId")
	@OneToOne(mappedBy = "samebook")
	private Lend lend;
	
	
	//청구 기호
	private String kdcCallSign;

	//등록 날짜
	private LocalDateTime createDate;
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	
	
}

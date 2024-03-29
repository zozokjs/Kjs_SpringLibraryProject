package com.kjs.library.domain.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//책 한 종류를 정의하는 테이블
@Builder
@Entity // JPA를 위함. 디비에 테이블을 생성함
@Data
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 생성자
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//isbn 번호
	private String isbn;
	
	//isbnSet 번호
	private String isbnSet;
	
	//남은 양(대여 가능한 책 수량)
	private String remainAmount;
	
	//총량(반입된 책 수량)
	private String totalAmount;
	
	//제목
	@Column(nullable = false)
	private String title;
	
	//저자
	@Column(nullable = false)
	private String writer;
	
	//이미지 위치
	private String titleImageUrl;
	
	//제본형식
	/* 1: 종이 / 2. 마력 / 3. 전자잉크
	 * 4. 음성 /5. 영상 / 6.기타
	 * */
	private String bindType;
	
	//페이지
	@Column(columnDefinition = "int default '0' ")
	private int page;
	
	//언어
	private String language;
	
	//가격
	/* 책의 발행처에 따라 '원'이 될 수도 '달러'가 될 수 도 있기에 String
	 * **/
	private String price;
	
	//발행일
	private String publishDate;
	
	//납본여부
	/* 1. 했음  / 2. 안 했음
	 * */
	private String deliveryState;
	
	//발행처
	private String publish;
	
	//크기 128*188*20mm...
	private String size;
	
	//내용
	@Lob
	private String contents;
	
	//KDC대분류(십진분류에 따름) 
	/*  000 총류 / 100 철학 /200 종교
	 *  300 사회과학 / 400 자연과학 / 500 기술과학
	 *  600 예술 / 700 언어 / 800 문학 / 900 역사
	 * */
	private String kdcTable;
	
	//대표 청구기호 김15소, 박211가...
	private String kdcCallSignFamily;

	
	/* 2022.06.14 주석처리
	등록한 사람
	1명의 유저는 여러 개의 책을 등록할 수 있음.
	 *  1권의 책은 1명이 등록할 수 있음
	 *  그래서 책과 유저는 N : 1의 관계
	 *  
	 * @Entity라고 적었으므로 DB에는 테이블이 생성될텐데, 아래는 USER 오브젝트이므로 FK가 생성된다. 
	 * @JoinColumn으로 FK 이름을 지정함
	 * */
	/*
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;*/

	//책 정보 수정한 사람. 일단 String으로 저장함...
	@Column(nullable = false)
	private String editedUserId;
	
	//책 정보를 입력한 사람. 일단 String으로 저장함...
	@Column(nullable = false)
	private String registrationUserId;
		
	//책을 생성하면 true, 책을 삭제하면 false 처리
	@Column(nullable = false)
	private boolean useState;
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	public Book(int id) {
		super();
		this.id = id;
	}
	
	
	
	
	
}


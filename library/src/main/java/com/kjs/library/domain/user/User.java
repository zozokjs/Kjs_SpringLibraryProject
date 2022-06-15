package com.kjs.library.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.kjs.library.domain.book.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//유저 한 사람을 정의하는 테이블
@Builder
@Data
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 생성자
@Entity // JPA를 위함. 디비에 테이블ㅇ을 생성함
public class User {
	
	//회원 번호
	@Id // PK값 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;

	/*
	@JoinColumn(name = "bookId")
	@OneToMany//기본 전력은 LAZY
	private List<Book> book;
	*/
	
	//아이디
	@Column(length = 100, unique = true)
	private String username;
	
	//비번
	@Column(nullable = false)
	private String password;
	
	//이름
	@Column(nullable = false)
	private String name;
	
	//종족
	private String species; //Human, Elf, Dwarf, Ork, Fairy(요정), Furry(수인), Other
	
	//국가 
	private String country; //Thordus, Elinia, Kalieus, FlutterNus, Perl, Other 
		
	//직업
	private String job;//
	
	//생년월일
	private String birth;
	
	//이메일
	@Column(nullable = false)
	private String email;
	
	//주소
	private String address;
	
	//연락처
	@Column(nullable = false)
	private String phoneNumber;
	
	//권한
	@Column(nullable = false) //ROLE_USER, ROLE_SASEO, ROLE_ADMIN
	private String role;
	
	//프로필 이미지
	private String profileImageUrl;
	
	//회원 가입 날짜
	private LocalDateTime createDate;
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

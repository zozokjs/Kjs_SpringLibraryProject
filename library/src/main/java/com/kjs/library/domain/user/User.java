package com.kjs.library.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.lend.Lend;

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
public class User{
	
	//회원 번호
	@Id // PK값 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy="user")//기본 전력은 LAZY
	private List<Lend> lend;
	
	//아이디
	@Column(length = 100, unique = true)
	private String username;
	
	//비번
	@Column(nullable = false)
	private String password;
	
	//이름
	private String name;
	
	//회원가입 플랫폼 구분(KAKAO, NAVER, GOOGLE... null이면 일반 가입)
	private String oAuthPlatform;
	
	/** 계정 활성화 여부(기본값 0(false)), 비번이 5번 틀리면 비활성화 된다. */
	@Column(nullable = false, columnDefinition = "int default '0' ")
	private boolean isEnabled;
	
	/**로그인 실패 횟수(기본값 0) 
	 * 로그인 5회 실패 시 잠김.
	 * 로그인 성공시 0으로 초기화*/
	@Column(nullable = false, columnDefinition = "int default '0' ")
	private int loginFailCount;
	
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
	//@Column(nullable = false) //ROLE_NOT, ROLE_USER, ROLE_SASEO, ROLE_ADMIN
	//private String role;
	
	//권한 EnumType을 String으로 해야 차후에 혼란이 없다고 함.
	/**YET : 가입 미승인, NOT : 계정 정지, USER : 일반 사용자, SESEO : 관리자, ADMIN : 최종관리자 */
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	//프로필 이미지
	private String profileImageUrl;
	
	//회원 가입 날짜
	private LocalDateTime createDate;
	
	/**
	 * 유저가 비밀번호 찾을 때
	 * 유저가 입력한 이메일로 비밀번호 초기화 링크를 보내는데 그때 사용되는 난수임
	 * */
	private String passwordAuthCode;
	private String passwordAuthTime;
	
	
	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	/*
	public boolean getEnable() {
		return this.enable;
	}*/

	public User(String username, String password, boolean isEnabled, String email,String phoneNumber, RoleType roleType) {
		
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.roleType = roleType;
		//this.roleType = role;
	}

	
	
}

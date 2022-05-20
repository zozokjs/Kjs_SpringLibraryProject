package com.kjs.library.web.dto.auth;

import com.kjs.library.domain.user.User;

import lombok.Data;

@Data
public class SignupDto {

	
	//아이디(유저네임), 비번, 이메일, 이름, 종족, 국가, 직업, 생년월일, 주소, 연락처
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private String species; 
	
	private String country;
	
	private String birth;
	
	private String job;
	
	private String address;
	
	private String phoneNumber;
	
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.species(species)
				.country(country)
				.birth(birth)
				.job(job)
				.address(address)
				.phoneNumber(phoneNumber)
				.build();
	}
	
	
}

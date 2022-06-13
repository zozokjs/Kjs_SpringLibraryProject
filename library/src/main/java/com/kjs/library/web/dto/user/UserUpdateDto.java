package com.kjs.library.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.kjs.library.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String password;
	
	private String email;
	
	//직업
	private String job;
	
	//주소
	private String address;

	//연락처
	@NotBlank
	private String phoneNumber;
	
	
	public User toEntity() {

		 return User.builder()
				 .password(password)
				 .email(email)
				 .job(job)
				 .address(address)
				 .phoneNumber(phoneNumber)
				 .build();
	}
	
	
}

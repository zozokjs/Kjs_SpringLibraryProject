package com.kjs.library.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;

@Controller
public class UserController {
	
	//내 서재 페이지로 이동
	@GetMapping("/user/myLibrary")
	public String myLibraryForm() {
		
		return "user/myLibrary";
	}
	
	
	//회원 정보 페이지로 이동
	@GetMapping("/user/{id}/userInfor")
	public String userInforForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		/**
		 * 매개변수로 @AuthenticationPrincial을 적고 header에서 principal을 jstl 태그 써서 지정하면
		 * 유저 정보를 body에서 출력할 수 있음
		 * 
		 * */
		
		//로그인 한 사람의 회원 정보 표시 .. 이후 수정, 탈퇴
		//계급도 같이 표시 ..
		//사서의 개인정보는 다른 메뉴에서 표시하는 게 좋을 거 같음.
		
		return "user/userInfor";
	}
	
	
	//회원 수정 페이지로 이동
	@PostMapping("/user/{id}/userUpdate")
	public String userUpdateForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//권한 검사... 사서나 관리자는 여기서 수정 불가.?
		
		return "user/userUpdate";
	}
	
}

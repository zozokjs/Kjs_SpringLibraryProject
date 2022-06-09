package com.kjs.library.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class GuideController {

	//도서관 이용안내 1차분류 컨트롤

	//찾아오시는 길
	@GetMapping("/guide/wayToHome")
	public String wayToHome() {
		return "guide/wayToHome";
	}
	
	//회원가입 안내
	@GetMapping("/guide/guideSignup")
	public String guideSignup() {
		return "guide/guideSignup";
	}
	
	//이용시간과 휴관일
	@GetMapping("/guide/closeTime")
	public String closeTime() {
		return "guide/closeTime";
	}

	// 시설 이용 안내
	@GetMapping("/guide/infraUse")
	public String infraUse() {
		return "guide/infraUse";
	}

	// 대출/반납/예약/연장
	@GetMapping("/guide/loanReturnReserveExtension")
	public String loanReturnReserveExtension() {
		return "guide/loanReturnReserveExtension";
	}

	// 자료 기증 ..안내 후 버튼을 통해 기증 메뉴로 이동되어야 함
	@GetMapping("/guide/donation")
	public String donation() {
		return "guide/donation";
	}

	// 조직도 및 답당 업무
	@GetMapping("/guide/organizationChart")
	public String index() {
		return "guide/organizationChart";
	}
		
	
}

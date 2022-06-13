package com.kjs.library.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.service.SaseoService;
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SaseoController {
	
	//도서관리화면으로 이동
	//신규 도서 등록/수정/삭제
	//보유 도서 권수수정
	//보유 도서의 십진분류(자동 채번?)

	private final SaseoService saseoService;
	
	//도서 관리 화면으로 이동
	@GetMapping("/saseo/bookManage")
	public String bookManageForm(Model model) {
		
		//신규 등록된 도서가 표시되어야 함
		List<Book> book = saseoService.bookSelect();
		
		model.addAttribute("book",book);
		
		return "saseo/bookManage";
	}
	
	
	//도서 등록 화면으로 이동
	@GetMapping("/saseo/bookRegistration")
	public String bookRegistrationForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인 해야합니다", null);
		}else {
			return "saseo/bookRegistration";
		}
		
	}

	
	
	//도서 등록 처리
	@PostMapping("/saseo/bookRegistration")
	public String bookRegistration(BookRegistrationDto bookRegistrationDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		//로그인 및 권한 검사 필요
		//로그인 검사 통과 못하면 업로드 불가능함.
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인이 안 되었습니다. 등록할 수 없습니다", null);
			/**
			  1. CustomValidationException 실행. 이것의 생성자에는 String과 ErrorMap이 전달되어야 함.
			  2. CustomValidationException의 부모클래스가 String을 전달 받고 get한다.
			  3. ControllerExceptionHandler에서 CustomValidationException을 @ExceptionHandler을 통해 핸들링하고 있으므로
			  이것을 잡아채게 되고
			  결과적으로 Script가 리턴됨.
			 * */
		}else if( !((principalDetails.getUser().getRole().equalsIgnoreCase("ROLE_SASEO")) || (principalDetails.getUser().getRole() != "ROLE_ADMIN")) ){

			//System.out.println("권한 확인 ------------------");
			//System.out.println(principalDetails.getUser().getRole());

			throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);
		}else {
			
			//System.out.println("로그인 됐음");
			saseoService.책등록(bookRegistrationDto, principalDetails);
			//업로드 잘 됐다고 스크립트로 알려줘야함
			
			return "main/index";
		}
	}
	
	//도서 상세 화면으로 이동
	@GetMapping("/saseo/{id}/bookInfor")
	public String bookInformationForm(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		if(principalDetails == null) {			
			throw new CustomValidationException("로그인 해야합니다", null);
		}else {
			
			//System.out.println("--------------------------");
			//System.out.println("전달 받은 책 id > "+id);
			Book bookEntity = saseoService.bookSelectOne(id);
			model.addAttribute("book",bookEntity);
			
			return "saseo/bookInfor";
		}
	
	}
	
	
	//도서 수정 화면으로 이동
	@GetMapping("/saseo/{id}/bookUpdate")
	public String bookUpdateForm(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인 해야합니다", null);
		}else {
			//System.out.println("--------------------------");
			//System.out.println("전달 받은 책 id > "+id);

			Book bookEntity = saseoService.bookSelectOne(id);
			
			model.addAttribute("book",bookEntity);

			return "saseo/bookUpdate";
		}
		
	}
	
	/*
	//도서 수정 완료 처리
	@PostMapping("/saseo/bookUpdate")
	public String bookUpdate(BookRegistrationDto bookRegistrationDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인이 안 되었습니다. 등록할 수 없습니다", null);
		}else if( !((principalDetails.getUser().getRole().equalsIgnoreCase("ROLE_SASEO")) || (principalDetails.getUser().getRole() != "ROLE_ADMIN")) ){
			throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);
		}else {
			saseoService.책수정(bookRegistrationDto, principalDetails);
			
			//ajax로 해야 하나? ... 
			//더티채킹 이뤄지게 하려면 그냥 컨트롤러에서 해도 되지 않을까
			//만약에 더치채킹 안 이뤄지면 ajax 써야 함
			
			return "main/index";
		}}*/
	
	
	
	
	
	
	
}

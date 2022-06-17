package com.kjs.library.web;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.service.SaseoService;
import com.kjs.library.web.dto.book.BookRegistrationDto;
import com.kjs.library.web.dto.book.BookRegistration_kdcDto;

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
	
	//도서 등록-같은 책의 청구기호 등록 화면으로 이동
	@GetMapping("/saseo/{id}/bookRegistration_kdc")
	public String bookRegistrationForm2(@PathVariable int id,  Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		//System.out.println("전달 된 책 아이디 "+id);
		
		Book bookEntity = saseoService.bookSelectOne(id);
		model.addAttribute("book",bookEntity);
		//System.out.println("> "+bookEntity.getVolume());
		return "saseo/bookRegistration_kdc";
		
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
			
			saseoService.책등록(bookRegistrationDto, principalDetails);
			//업로드 잘 됐다고 스크립트로 알려줘야함
			
			return "main/index";
		}
	}
		
	//도서 등록-같은 책의 청구기호 등록 처리
	@PostMapping("/saseo/bookRegistration_kdc")
	public String bookRegistration_kdc(BookRegistration_kdcDto bookRegistration_kdcDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인이 안 되었습니다. 등록할 수 없습니다", null);
		}else if( !((principalDetails.getUser().getRole().equalsIgnoreCase("ROLE_SASEO")) || (principalDetails.getUser().getRole() != "ROLE_ADMIN")) ){
			throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);
		}else {
			
			System.out.println("읽음");
			
			saseoService.책청구기호등록(bookRegistration_kdcDto, principalDetails);
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
				
			//책 1개의 정보
			Book bookEntity = saseoService.bookSelectOne(id);
			model.addAttribute("book",bookEntity);
				
			//책 1개의 청구기호 정보
			List<Samebook> sameBookEntity = saseoService.sameBookSelectOne(id);
	
			
			//System.out.println("> "+ sameBookEntity.get(0).getKdcCallSign());
				
			model.addAttribute("sameBook",sameBookEntity);
				
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
	
	
	//도서 청구기호 수정 화면으로 이동
	@GetMapping("/saseo/{id}/bookUpdate_kdc")
	public String bookUpdate_kdcForm(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(principalDetails == null) {			
			throw new CustomValidationException("로그인 해야합니다", null);
		}else {
			//System.out.println("--------------------------");
			//System.out.println("전달 받은 책 id > "+id);
			
			//책 1개의 정보를 가져옴
			Book bookEntity = saseoService.bookSelectOne(id);
			model.addAttribute("book",bookEntity);
			
			//책 1권의 청구기호 정보 가져옴
			List<Samebook> sameBookEntity = saseoService.sameBookSelectOne(id);
			model.addAttribute("sameBook",sameBookEntity);
	
			return "saseo/bookUpdate_kdc";
		}
	}
	
	
	
	
	
	
}

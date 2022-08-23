package com.kjs.library.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.service.SaseoSelectService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.UserService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.book.BookRegistrationDto;
import com.kjs.library.web.dto.book.BookRegistration_kdcDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SaseoController {

	private final SaseoService saseoService;
	private final CommonService commonService;
	private final SaseoSelectService saseoSelectService;
	private final UserService userService;

	// 도서 목록(도서 관리를 위한) 화면으로 이동
	@GetMapping("/saseo/bookManage")
	public String bookManageForm(Model model, @PageableDefault(size = 9) Pageable pageable) {

		// 신규 등록된 도서가 표시되어야 함
		Page<Book> book = saseoSelectService.bookSelectAllToPage(pageable);

		model.addAttribute("book", book);

		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(book, 10);

		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));

		return "saseo/bookManage";
	}

	// 도서 등록 화면으로 이동
	@GetMapping("/saseo/bookRegistration")
	public String bookRegistrationForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		commonService.로그인검사(principalDetails);
		
		return "saseo/bookRegistration";
		

	}

	// 도서 등록-같은 책의 청구기호 등록 화면으로 이동
	@GetMapping("/saseo/{id}/bookRegistration_kdc")
	public String bookRegistrationForm2(@PathVariable int id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		// System.out.println("전달 된 책 아이디 "+id);

		Book bookEntity = saseoSelectService.bookSelectOne(id);
		model.addAttribute("book", bookEntity);
		// System.out.println("> "+bookEntity.getVolume());
		return "saseo/bookRegistration_kdc";

	}

	// 도서 등록 처리
	@PostMapping("/saseo/bookRegistration")
	public String bookRegistration(BookRegistrationDto bookRegistrationDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		//로그인 및 권한 검사 필요
		//로그인 검사 통과 못하면 업로드 불가능함.
		
		commonService.로그인검사(principalDetails);
		
		commonService.권한검사(principalDetails, RoleType.SASEO);
		
		saseoService.책등록(bookRegistrationDto, principalDetails);
		//업로드 잘 됐다고 스크립트로 알려줘야함
		
		return "redirect:/saseo/bookManage";
	
	}

	// 도서 등록-같은 책의 청구기호 등록 완료 처리
	@PostMapping("/saseo/{bookId}/bookRegistration_kdc")
	public String bookRegistration_kdc(@PathVariable int bookId, BookRegistration_kdcDto bookRegistration_kdcDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		

		commonService.로그인검사(principalDetails);
		commonService.권한검사(principalDetails, RoleType.SASEO);
		

		saseoService.책청구기호등록(bookId, bookRegistration_kdcDto, principalDetails);

		// Book 테이블의 totalAmount 컬럼 업데이트를 위함.
		String kdcCallSignList = bookRegistration_kdcDto.getKdcCallSign();
		String[] array = kdcCallSignList.split(",");
		int arrayLength = array.length;

		//System.out.println("업데이트 길이 " + arrayLength);

		saseoService.totalAmountSave(bookId, arrayLength);

		// 업로드 잘 됐다고 스크립트로 알려줘야함

		//return "main/index";
		//return "redirect:main/index";  //http://localhost:8080/saseo/3/main/index
		//return "redirect:main/index" //404
		return "redirect:/saseo/bookManage";
	}

	// 도서 상세 화면으로 이동
	@GetMapping("/saseo/{id}/bookInfor")
	public String bookInformationForm(@PathVariable int id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		commonService.로그인검사(principalDetails);
		// 책 1개의 정보
		Book bookEntity = saseoSelectService.bookSelectOne(id);
		model.addAttribute("book", bookEntity);

		// 책 1개의 청구기호 정보
		List<Samebook> sameBookEntity = saseoSelectService.sameBookSelectOne(id);
		
		model.addAttribute("sameBook", sameBookEntity);

		return "saseo/bookInfor";
		

	}

	// 도서 수정 화면으로 이동
	@GetMapping("/saseo/{id}/bookUpdate")
	public String bookUpdateForm(@PathVariable int id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		commonService.로그인검사(principalDetails);
	
		// System.out.println("--------------------------");
		// System.out.println("전달 받은 책 id > "+id);

		Book bookEntity = saseoSelectService.bookSelectOne(id);

		model.addAttribute("book", bookEntity);

		return "saseo/bookUpdate";
		
	}

	// 도서 청구기호 수정 화면으로 이동
	@GetMapping("/saseo/{id}/bookUpdate_kdc")
	public String bookUpdate_kdcForm(@PathVariable int id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		commonService.로그인검사(principalDetails);
		// System.out.println("--------------------------");
		// System.out.println("전달 받은 책 id > "+id);

		// 책 1개의 정보를 가져옴
		Book bookEntity = saseoSelectService.bookSelectOne(id);
		model.addAttribute("book", bookEntity);

		// 책 1권의 청구기호 정보 가져옴
		List<Samebook> sameBookEntity = saseoSelectService.sameBookSelectOne(id);
		model.addAttribute("sameBook", sameBookEntity);

		return "saseo/bookUpdate_kdc";
		
	}

	// 가입 승인 화면으로 이동
	@GetMapping("/saseo/signinRequest")
	public String signinRequestForm(Model model, @PageableDefault(size = 2) Pageable pageable) {

		// 가입 대기 회원 목록
		Page<User> userEntity = userService.가입대기회원목록(pageable);

		model.addAttribute("userList", userEntity);

		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(userEntity, 10);

		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));

		return "saseo/userSigninRequest";
	}

	// 비활성화된 회원 관리 화면으로 이동
	@GetMapping("/saseo/userLockManage")
	public String userLockManageForm(Model model, @PageableDefault(size = 2) Pageable pageable) {

		// 가입 대기 회원 목록
		Page<User> userEntity = userService.정지된회원목록(pageable);

		model.addAttribute("userList", userEntity);

		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(userEntity, 10);

		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));

		return "saseo/userLockManage";
	}

}

package com.kjs.library.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.service.BookService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final BookService bookService;
	private final CommonService commonService;
	
	//내 서재 페이지로 이동
	@GetMapping("/user/myLibrary")
	public String myLibraryForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		//대출된 목록 표시
		List<UserLendListInterface> userLendList = bookService.대출목록(principalDetails.getUser().getId());

		/*
		for (int i = 0; i < userLendList.size(); i++) {
			System.out.println("체크");
			System.out.println(userLendList.get(i).getReturnPlanDate()  );
			System.out.println(userLendList.get(i).getBindTypeToString()  );
			System.out.println(userLendList.get(i).getStandardCreateDate() );
		}*/
		
		model.addAttribute("userLendList",userLendList);
		
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
	

	//반납 완료 내역 페이지로 이동
	@GetMapping("/user/myLendHistory")
	public String myLendHistoryForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,  @PageableDefault( size = 1 ) Pageable pageable) {
		
		//대출된 목록 표시
		Page<UserLendListInterface> userLendHistoryList = bookService.반납완료내역(principalDetails.getUser().getId(), pageable);
		model.addAttribute("userLendHistoryList",userLendHistoryList);
		
		
		int pageCurrent = userLendHistoryList.getPageable().getPageNumber();//현재 페이지
		int pageTotal = userLendHistoryList.getTotalPages(); //전체 페이지 수
		int pageButtonLength = 10; //한 번에 표시할 페이지 버튼 수
		int pageStart = 0; //페이지 버튼 처음 숫자
		int pageEnd = 0; //페이지 버튼 마지막 숫자
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(pageCurrent, pageTotal, pageButtonLength);
		
		System.out.println(pageCurrent +"/ "+pageTotal+" / "+pageButtonLength);
		
		pageStart = pageMap.get("pageStart");
		pageEnd = pageMap.get("pageEnd");
		
		System.out.println("current : "+pageCurrent +" / total : "+pageTotal);
		System.out.println("pageStart : "+pageStart +" / pageEnd : "+pageEnd);
		
		model.addAttribute("startPage",pageStart);
		model.addAttribute("endPage",pageEnd);
		
		return "user/myLendHistory";
	}
	
	


}

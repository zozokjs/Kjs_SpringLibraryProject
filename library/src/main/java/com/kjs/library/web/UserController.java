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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.AuthService;
import com.kjs.library.service.BookSelectService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.community.UserBoardHistoryInterface;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
	
	private final BookService bookService;
	private final CommonService commonService;
	private final BookSelectService bookSelectService;
	private final CommunityService communityService;
	private final AuthService authService;
	
	//내 서재 페이지로 이동
	@GetMapping("/user/myLibrary")
	public String myLibraryForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		//대출된 목록 표시
		List<UserLendListInterface> userLendList = bookSelectService.대출목록(principalDetails.getUser().getId());

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
	//@GetMapping("/user/{id}/userInfor")
	@GetMapping("/user/userInfor/{id}")
	public String userInforForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		/**
		 * 매개변수로 @AuthenticationPrincial을 적고 header에서 principal을 jstl 태그 써서 지정하면
		 * 유저 정보를 body에서 출력할 수 있음
		 * */
		
		//로그인 한 사람의 회원 정보 표시 .. 이후 수정, 탈퇴
		//계급도 같이 표시 ..
		//사서의 개인정보는 다른 메뉴에서 표시하는 게 좋을 거 같음.
		
		return "user/userInfor";
	}
	
	
	//회원 수정 페이지로 이동 전 비밀번호 확인
	@PostMapping("/user/userUpdateBefore/{username}")
	public String userUpdateBeforeForm(@PathVariable String username, Model model) {
		
		User userEntity = authService.유저정보(username);
		
		String oAuthPlatform = userEntity.getOAuthPlatform();
		
		//OAuth로 가입한 회원이면 비밀번호 확인 없이 수정 화면으로 이동함.
		if( oAuthPlatform == null || oAuthPlatform.isEmpty() || oAuthPlatform.equals("")) {
			log.info("oauth 가입 안 했음");

			
			return "user/userUpdateBefore";
		}else {
			log.info("oauth 가입 했음");
			
			model.addAttribute("oAuthPlatform", oAuthPlatform);
			
			return "user/userUpdate";
		}
	}
	
	//비밀번호 확인 후 회원 수정 페이지로 이동
	@PostMapping("/user/userUpdate")
	public String userUpdateForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		String username = principalDetails.getUser().getUsername();
		User userEntity = authService.유저정보(username);
		String oAuthPlatform = userEntity.getOAuthPlatform();
		model.addAttribute("oAuthPlatform", oAuthPlatform);
		
		log.info("userUpdateForm read  {}",oAuthPlatform);

		
		return "user/userUpdate";
	}
	

	//반납 완료 내역 페이지로 이동
	@GetMapping("/user/myLendHistory")
	public String myLendHistoryForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,  @PageableDefault( size = 1 ) Pageable pageable) {
		
		//대출된 목록 표시
		Page<UserLendListInterface> userLendHistoryList = bookSelectService.반납완료내역(principalDetails.getUser().getId(), pageable);
		model.addAttribute("userLendHistoryList",userLendHistoryList);
		//System.out.println("  "+userLendHistoryList.getContent().get(0).getTitle());
		//;
		/*
		int pageCurrent = userLendHistoryList.getPageable().getPageNumber();//현재 페이지
		int pageTotal = userLendHistoryList.getTotalPages(); //전체 페이지 수
		int pageButtonLength = 10; //한 번에 표시할 페이지 버튼 수
		int pageStart = 0; //페이지 버튼 처음 숫자
		int pageEnd = 0; //페이지 버튼 마지막 숫자
		*/
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(userLendHistoryList, 10);
		model.addAttribute("startPage",pageMap.get("pageStart"));
		model.addAttribute("endPage",pageMap.get("pageEnd"));
		
		return "user/myLendHistory";
	}
	
	
	//작성글 목록 페이지로 이동
	@GetMapping("/user/myBoardHistory")
	public String myBoardHistoryForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,  @PageableDefault( size = 3) Pageable pageable) {
		
		//로그인 된 user의 id 가져옴
		int userId = principalDetails.getUser().getId();
		
		Page<UserBoardHistoryInterface> userBoardHistory = communityService.작성글목록(userId, pageable);
		model.addAttribute("userBoardHistory",userBoardHistory);
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(userBoardHistory, 10);
		model.addAttribute("startPage",pageMap.get("pageStart"));
		model.addAttribute("endPage",pageMap.get("pageEnd"));
		
		return "user/myBoardHistory";
	}


}

package com.kjs.library.web;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.domain.community.SingleQuestion;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.community.BFreeRegistrationDto;
import com.kjs.library.web.dto.community.BFreeResponseDto;
import com.kjs.library.web.dto.community.BNoticeRegistrationDto;
import com.kjs.library.web.dto.community.BNoticeResponseDto;
import com.kjs.library.web.dto.community.BFreeListInterface;
import com.kjs.library.web.dto.community.SQuestionRegistrationDto;
import com.kjs.library.web.dto.community.SQuestionResponseDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class CommunityController {
	
	private final CommunityService communityService;
	private final CommonService commonService;
	private final DateCommonService dateCommonService;
	
	//게시판에 등록된 내용 표시
	/*
	 * 게시글 작성
	 * 게시글 수정
	 * 게시글 삭제
	 * 댓글 작성
//	 * 댓글 수정
	 * 댓글 삭제
	 * 게시글 목록
	 * */
	
	//자유게시판 화면으로 이동
	@GetMapping("/community/boardFree")
	public String boardFreeForm(@PageableDefault(size = 5) Pageable pageable, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {

		//댓글 수, 작성자, 제목, 등록날짜, 조회수 등 표시
		Page <BFreeListInterface> boardFree= communityService.게시글목록(pageable);
		model.addAttribute("boardFree",boardFree);
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(boardFree, 10);
		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));
		
		return "community/boardFree";
	}
	
	//자유게시판 게시글 등록 화면으로 이동
	@GetMapping("/community/boardFreeRegistrationForm")
	public String boardFreeRegistrationForm(@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		commonService.로그인검사(principalDetails);
		return "community/boardFreeRegistration";
	}
	
	//자유게시판 게시글 등록 처리
	@GetMapping("/community/boardFreeRegistration")
	public String boardFreeRegistration(
			@Valid BFreeRegistrationDto freeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		commonService.로그인검사(principalDetails);
		commonService.권한검사(principalDetails, RoleType.USER);
		
		communityService.게시글등록(freeRegistrationDto, principalDetails);
		
		return "redirect:/community/boardFree";
	}
	

	//자유게시판 게시글 1개 조회	
	@GetMapping("/community/{boardFreeId}/infor")
	public String boardFreeInforForm(@PathVariable int boardFreeId, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		
		BFreeResponseDto bfResponseDto = communityService.게시글조회(boardFreeId) ;
		
		//게시글 날짜 포맷 변경
	//	String createDate =dateCommonService.날짜포맷변경시간추가(bfResponseDto.getCreateDate());

		//게시글 1개의 조회수 증가
		communityService.게시글조회수증가(boardFreeId, request, response, session);
		
		//model.addAttribute("createDate", createDate);
		model.addAttribute("boardFree", bfResponseDto);
		return "community/boardFreeInfor";
	}
	
	
	//자유게시판 게시글 수정 페이지로 이동
	@GetMapping("/community/{id}/boardFreeUpdate")
	public String boardFreeEditForm(@PathVariable int id, Model model) {
		
		BFreeResponseDto community = communityService.게시글조회(id);
		
		model.addAttribute("community", community);
		return "community/boardFreeUpdate";
	}
	

	//자유게시판 게시글 수정 완료 처리
	@PostMapping("/community/{id}/boardFreeUpdate")
	public String boardFreeEdit(
			@PathVariable int id,
			@Valid BFreeRegistrationDto boardFreeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		communityService.게시글수정(id, boardFreeRegistrationDto);
		
		//model.addAttribute("community", community);
		return "redirect:/community/"+id+"/infor";
	}
	
    /////////////////////////////////////////////////////////////
	
	//공지사항 게시판 목록으로 이동
	@GetMapping("/community/boardNotice")
	public String boardNoticeForm(@PageableDefault(size = 5) Pageable pageable, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {

		//댓글 수, 작성자, 제목, 등록날짜, 조회수 등 표시
		Page <BoardNotice> boardNotice= communityService.공지사항목록(pageable);
		model.addAttribute("boardNotice",boardNotice);
			
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(boardNotice, 10);
		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));
		
		return "community/boardNotice";
	}


	//공지사항 게시글 등록 화면으로 이동
	@GetMapping("/community/boardNoticeRegistrationForm")
	public String boardNoticeRegistrationForm(@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		commonService.로그인검사(principalDetails);
		return "community/boardNoticeRegistration";
	}
	
	
	//공지사항 게시글 등록 처리
	@GetMapping("/community/boardNoticeRegistration")
	public String boardNoticeRegistration(
			@Valid BNoticeRegistrationDto bNoticeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		commonService.로그인검사(principalDetails);
		commonService.권한검사(principalDetails, RoleType.SASEO); //사서 이상이어야 함.
		
		communityService.공지사항게시글등록(bNoticeRegistrationDto, principalDetails);
		
		return "redirect:/community/boardNotice";
	}
	
	
	//공지사항 게시글 1개 조회	
	@GetMapping("/community/{boardNoticeId}/noticeInfor")
	public String boardNoticeInforForm(@PathVariable int boardNoticeId, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		
		BNoticeResponseDto bnResponseDto = communityService.공지사항게시글조회(boardNoticeId) ;

		//게시글 1개의 조회수 증가
		communityService.공지사항게시글조회수증가(boardNoticeId, request, response, session);
		
		//model.addAttribute("createDate", createDate);
		model.addAttribute("boardNotice", bnResponseDto);
		return "community/boardNoticeInfor";
	}
	
	
	//공지사항 게시글 수정 페이지로 이동
	@GetMapping("/community/{id}/boardNoticeUpdate")
	public String boardNoticeEditForm(@PathVariable int id, Model model) {
		
		BNoticeResponseDto bnResponseDto  = communityService.공지사항게시글조회(id);
		
		model.addAttribute("boardNotice", bnResponseDto);
		return "community/boardNoticeUpdate";
	}
	

	//공지사항 게시글 수정 완료 처리
	@PostMapping("/community/{id}/boardNoticeUpdate")
	public String boardNoticeEdit(
			@PathVariable int id,
			@Valid BNoticeRegistrationDto boardNoticeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		communityService.공지사항게시글수정(id, boardNoticeRegistrationDto);
		
		//model.addAttribute("community", community);
		return "redirect:/community/"+id+"/noticeInfor";
	}


    /////////////////////////////////////////////////////////////

	//1대1질문하기 게시판으로 이동
	@GetMapping("/community/singleQuestion")
	public String singleQuestion(@PageableDefault(size = 5) Pageable pageable, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		Page<SingleQuestion> singleQuestionList = communityService.일대일문의게시판목록(pageable);
		
		//System.out.println("---ivsi "+singleQuestionList.getContent().get(0).getTitle());
		
		model.addAttribute("singleQuestionList",singleQuestionList);
		
		return "/community/singleQuestion";
	}
	
	
	//1대1질문하기 질문 작성 화면으로 이동
	@GetMapping("/community/singleQuestionRegistrationForm")
	public String singleQuestionRegistrationForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		return "/community/singleQuestionRegistration";
	}

	//singleQuestionInfor

	//1대1질문하기 질문 등록
	@GetMapping("/community/singleQuestionRegistration")
	public String singleQuestionRegistration(
			@Valid SQuestionRegistrationDto singleQuestionRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		commonService.로그인검사(principalDetails);
		commonService.권한검사(principalDetails, RoleType.USER); 
		
		communityService.일대일문의게시글등록(singleQuestionRegistrationDto, principalDetails);
		
		return "redirect:/community/singleQuestion";
	}

	//자유게시판 게시글 1개 조회	
	@GetMapping("/community/{singleQuestionId}/singleQuestionInfor")
	public String singleQuestionInforForm(@PathVariable int singleQuestionId, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		
		SQuestionResponseDto sqResponseDto = communityService.일대일질문게시글조회(singleQuestionId) ;
		
		//model.addAttribute("createDate", createDate);
		model.addAttribute("sqResponseDto", sqResponseDto);
		return "community/singleQuestionInfor";
	}
	
	
	
	/////////////////////////////////////////////////////////////
	
	//자주묻는질문 게시판으로 이동
	@GetMapping("/community/manyQuestion")
	public String manyQuestionForm() {
		
		return "/community/manyQuestion";
	}
}

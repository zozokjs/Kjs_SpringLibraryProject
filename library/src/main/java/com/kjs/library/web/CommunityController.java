package com.kjs.library.web;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.boardFree.BFreeResponseDto;
import com.kjs.library.web.dto.boardFree.BoardFreeRegistrationDto;

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
	 * 댓글 수정
	 * 댓글 삭제
	 * 게시글 목록
	 * */
	
	//자유게시판 화면으로 이동
	@GetMapping("/community/boardFree")
	public String boardFreeForm(@PageableDefault(size = 5) Pageable pageable, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {

		//댓글 수, 작성자, 제목, 등록날짜, 조회수 등 표시
		Page <BoardFree> community = communityService.게시글목록(pageable);
		model.addAttribute("community",community);

		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(community, 10);
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
			@Valid BoardFreeRegistrationDto freeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		commonService.로그인검사(principalDetails);
		commonService.권한검사(principalDetails, RoleType.USER);
		
		communityService.게시글등록(freeRegistrationDto, principalDetails);
		
		return "redirect:/community/boardFree";
	}
	

	//게시글 1개 조회	
	@GetMapping("/community/{boardFreeId}/infor")
	public String boardFreeInforForm(@PathVariable int boardFreeId, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		
		BFreeResponseDto bfResponseDto = communityService.게시글조회(boardFreeId) ;
		
		//게시글 날짜 포맷 변경
		String createDate =dateCommonService.날짜포맷변경시간추가(bfResponseDto.getCreateDate());

		//게시글 1개의 조회수 증가
		communityService.게시글조회수증가(boardFreeId, request, response, session);
		
		model.addAttribute("createDate", createDate);
		model.addAttribute("boardFree", bfResponseDto);
		return "community/boardFreeInfor";
	}
	
	
	//게시글 수정 페이지로 이동
	@GetMapping("/community/{id}/boardFreeUpdate")
	public String boardFreeEditForm(@PathVariable int id, Model model) {
		
		//BoardFree community = communityService.게시글조회(id);
		
	//	model.addAttribute("community", community);
		return "community/boardFreeUpdate";
	}
	

	//게시글 수정 완료 처리
	@PostMapping("/community/{id}/boardFreeUpdate")
	public String boardFreeEdit(
			@PathVariable int id,
			@Valid BoardFreeRegistrationDto boardFreeRegistrationDto,
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws ParseException {
		
		communityService.게시글수정(id, boardFreeRegistrationDto);
		
		//model.addAttribute("community", community);
		return "redirect:/community/"+id+"/infor";
	}
	
	

}

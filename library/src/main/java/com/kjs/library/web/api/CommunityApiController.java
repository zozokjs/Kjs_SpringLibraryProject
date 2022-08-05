package com.kjs.library.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.community.CommentRegistrationDto;
import com.kjs.library.web.dto.community.SQuestionCommentRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommunityApiController {
	
	
	private final CommunityService commuService;
	private final CommonService commonService;
	/*
	@PostMapping("/api/community/{boardFreeId}/read")
	public  ResponseEntity<?> rad(@PathVariable int boardFreeId){
		
		System.err.println("> "+boardFreeId);
		
		BoardFree boardFree = commuService.게시글조회(boardFreeId);
		
		if(boardFree == null) {
			System.err.println("null");
		}else {
			System.err.println("null 아님");
			
		}
		
		return new ResponseEntity<>(new CMRespDto<>(1,"조회 성공.",boardFree),HttpStatus.OK);
	}*/
	
	
	/**게시글 삭제 처리*/
	@PostMapping("/api/community/{boardFreeId}/boardFreeDelete")
	public ResponseEntity<?> boardFreeDelete(@PathVariable int boardFreeId) {

		//username을 db 조회해서 있는지 봐야함
		commuService.게시글삭제(boardFreeId);

		return new ResponseEntity<>(new CMRespDto<>(1,"삭제 완료 되었습니다.",null),HttpStatus.OK);
		
	}
	

	/**게시글의 댓글 등록 처리*/
	@PutMapping("/api/community/{boardFreeId}/{userId}/boardFreeCommentRegistration")
	public ResponseEntity<?> boardFreeCommentRegistration(
			@RequestBody  @Valid CommentRegistrationDto commentRegistrationDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(commonService.로그인검사TrueFalse(principalDetails) == false) {
			return new ResponseEntity<>(new CMRespDto<>(0,"로그인 해야 합니다.",null),HttpStatus.BAD_REQUEST);
		}
		
		//System.err.println("1 "+commentRegistrationDto.getBoardFreeId());
		//System.err.println("2"+commentRegistrationDto.getContent());
		//System.err.println("3 "+commentRegistrationDto.getUserId());
		
		commuService.댓글등록(commentRegistrationDto);
		
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글 등록 되었습니다.",null),HttpStatus.OK);
		
	}

	/**게시글의 댓글 삭제 처리*/
	@PutMapping("/api/community/{boardFreeId}/boardFreeCommentDelete")
	public ResponseEntity<?> boardFreeCommentDelete(
			@PathVariable int boardFreeId, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(commonService.로그인검사TrueFalse(principalDetails) == false) {
			return new ResponseEntity<>(new CMRespDto<>(0,"로그인 해야 합니다.",null),HttpStatus.BAD_REQUEST);
		}
		
		commuService.댓글삭제(boardFreeId);
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글 삭제 되었습니다.",null),HttpStatus.OK);
		
	}
	
	
	/**공지사항 게시글 삭제 처리*/
	@PostMapping("/api/community/{boardNoticeId}/boardNoticeDelete")
	public ResponseEntity<?> boardNoticeDelete(@PathVariable int boardNoticeId) {

		//username을 db 조회해서 있는지 봐야함
		commuService.공지사항게시글삭제(boardNoticeId);

		return new ResponseEntity<>(new CMRespDto<>(1,"삭제 완료 되었습니다.",null),HttpStatus.OK);
		
	}
	
	
	/**1대1질문 게시글 삭제 처리*/
	@PostMapping("/api/community/{singleQuestionId}/singleQuestionDelete")
	public ResponseEntity<?> singleQuestionDelete(@PathVariable int singleQuestionId) {

		//username을 db 조회해서 있는지 봐야함
		commuService.일대일질문게시글삭제(singleQuestionId);

		return new ResponseEntity<>(new CMRespDto<>(1,"삭제 완료 되었습니다.",null),HttpStatus.OK);
		
	}
	
	
	/**1대1질문 게시글의 답변 등록 처리*/
	@PutMapping("/api/community/{singleQuestionId}/{userId}/singleQuestionCommentRegistration")
	public ResponseEntity<?> singleQuestionCommentRegistration(
			@RequestBody  @Valid SQuestionCommentRegistrationDto sqCommentRegistrationDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(commonService.로그인검사TrueFalse(principalDetails) == false) {
			return new ResponseEntity<>(new CMRespDto<>(0,"로그인 해야 합니다.",null),HttpStatus.BAD_REQUEST);
		}
		
		//System.err.println("1 "+commentRegistrationDto.getBoardFreeId());
		//System.err.println("2"+commentRegistrationDto.getContent());
		//System.err.println("3 "+commentRegistrationDto.getUserId());
		
		commuService.일대일질문게시글답변등록(sqCommentRegistrationDto);
		
		
		return new ResponseEntity<>(new CMRespDto<>(1,"답변이 등록 되었습니다.",null),HttpStatus.OK);
		
	}
	
	
	
}

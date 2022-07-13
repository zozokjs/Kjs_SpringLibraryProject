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
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.boardFree.CommentRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommunityApiController {
	
	
	private final CommunityService commuService;
	private final CommonService commonService;
	
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
		
		commonService.로그인검사(principalDetails);
		
		//System.err.println("1 "+commentRegistrationDto.getBoardFreeId());
		//System.err.println("2"+commentRegistrationDto.getContent());
		//System.err.println("3 "+commentRegistrationDto.getUserId());
		
		commuService.댓글등록(commentRegistrationDto);
		
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글 등록 되었습니다.",null),HttpStatus.OK);
		
	}

	
}

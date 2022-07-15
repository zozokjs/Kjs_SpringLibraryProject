package com.kjs.library.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.comment.CommentRepository;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardFreeRepository;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.boardFree.BFreeCommentResponseDto;
import com.kjs.library.web.dto.boardFree.BFreeResponseDto;
import com.kjs.library.web.dto.boardFree.BoardFreeRegistrationDto;
import com.kjs.library.web.dto.boardFree.CommentRegistrationDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

	private final BoardFreeRepository boardFreeRepository;
	private final DateCommonService dateCommonService;
	private final CommentRepository commentRepository;
	
	//게시글 등록
	@Transactional
	public void 게시글등록(BoardFreeRegistrationDto boardFreeRegistrationDto, PrincipalDetails principalDetails) {
		
		BoardFree com = boardFreeRegistrationDto.toEntity();
		com.setUser(principalDetails.getUser());
		boardFreeRepository.save(com);
	}
	
	
	//게시글 리스트
	@Transactional(readOnly = true)
	public Page<BoardFree> 게시글목록(Pageable pageable){
		
		return boardFreeRepository.findByAllDesc(pageable);
	}
	
	
	//게시글 1개 조회
	@Transactional(readOnly = true)
	public BFreeResponseDto 게시글조회(int id) {
		
		BoardFree boardFree = boardFreeRepository.findById(id).orElseThrow();
		
		return getBoardFreeAndCommentRequest(boardFree);
	}
	
	private BFreeResponseDto getBoardFreeAndCommentRequest(BoardFree boardFree) {
		
		BFreeResponseDto bfdto = new BFreeResponseDto(boardFree);
		
		List<BFreeCommentResponseDto> commentList = new ArrayList<>();
				
		for(int i =0; i < boardFree.getComments().size(); i++) {
			
			BFreeCommentResponseDto bfComment = new BFreeCommentResponseDto();
			bfComment.setId(boardFree.getComments().get(i).getId());
			bfComment.setContent(boardFree.getComments().get(i).getContent());
			bfComment.setCreateDate(boardFree.getComments().get(i).getCreateDate());
			bfComment.setUser(boardFree.getComments().get(i).getUser());
			commentList.add(bfComment);
		}
		
		bfdto.setComments(commentList);
				
		
		return bfdto;
	}
	

	//게시글 1개 수정
	@Transactional
	public BoardFree 게시글수정(int id, BoardFreeRegistrationDto boardFreeRegistrationDto) throws ParseException {
		
		BoardFree comEntity  = boardFreeRepository.findById(id).orElseThrow();
		
		//내용 변경
		comEntity.setContent(boardFreeRegistrationDto.getContent());
		
		//수정 날짜 변경
		LocalDateTime now = LocalDateTime.now(); 
		String extensionDate = dateCommonService.날짜포맷변경시간추가(now);
		
		comEntity.setEditDate(extensionDate);
		
		return comEntity;
	}


	//게시글 1개 삭제
	@Transactional
	public void 게시글삭제(int id) {
		boardFreeRepository.deleteById(id);
	}
	
	//게시글 1개 조회수 증가
	@Transactional
	public BoardFree 게시글조회수증가(int boardFreeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		//요청으로부터 받은 쿠키
		Cookie[] cookies = request.getCookies();
		
		/**
		 https://developersoo.tistory.com/14
		 * */
		BoardFree comEntity  = boardFreeRepository.findById(boardFreeId).orElseThrow();
		
		Cookie 기존쿠키 = null;
		
		if(cookies != null && cookies.length > 0) {
			
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("customCookie"+boardFreeId)) {
					System.out.println("쿠키 있음. 조회수 증가 안 함");
					기존쿠키 = cookies[i];
				}
			}
		}
		
		
		//저장된 쿠키가 없을 때
		if(기존쿠키 == null) {
			System.out.println("쿠키 없음. 조회수 증가.");
			
			//name = customCookie글번호, value = 글번호
			Cookie 새쿠키 = new Cookie("customCookie"+boardFreeId,"["+boardFreeId+"]");
			
			response.addCookie(새쿠키);
			comEntity.addReadCount(boardFreeId);
			
		}
		//저장된 쿠키가 있을 때
		else {
			//조회수 증가하지 않음
		}

		return comEntity;
	}
	

	
	//게시글의 댓글 등록
	@Transactional
	public void 댓글등록(CommentRegistrationDto c) {
		commentRepository.commentSave(Integer.parseInt(c.getUserId()), Integer.parseInt(c.getBoardFreeId()), c.getContent());
		
	}
	
	/*
	@Transactional(readOnly = true)
	public List<Comment> 댓글목록(int boardFreeId){
		
		//commentRepository.findb
		boardFreeRepository.findById(boardFreeId);
		
		return null;
	}*/
	
}

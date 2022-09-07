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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.comment.CommentRepository;
import com.kjs.library.domain.comment.CommentSQ;
import com.kjs.library.domain.comment.CommentSQRepository;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardFreeRepository;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.domain.community.BoardNoticeRepository;
import com.kjs.library.domain.community.SingleQuestion;
import com.kjs.library.domain.community.SingleQuestionRepository;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.community.BFreeCommentResponseDto;
import com.kjs.library.web.dto.community.BFreeListInterface;
import com.kjs.library.web.dto.community.BFreeRegistrationDto;
import com.kjs.library.web.dto.community.BFreeResponseDto;
import com.kjs.library.web.dto.community.BNoticeRegistrationDto;
import com.kjs.library.web.dto.community.BNoticeResponseDto;
import com.kjs.library.web.dto.community.CommentRegistrationDto;
import com.kjs.library.web.dto.community.SQuestionCommentRegistrationDto;
import com.kjs.library.web.dto.community.SQuestionRegistrationDto;
import com.kjs.library.web.dto.community.SQuestionResponseDto;
import com.kjs.library.web.dto.community.UserBoardHistoryInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService/*CommunityService*/ {

	private final BoardFreeRepository boardFreeRepository;
	private final BoardNoticeRepository boardNoticeRepository;
	private final SingleQuestionRepository singleQuestionRepository;
	private final DateCommonService dateCommonService;
	private final CommentRepository commentRepository;
	private final CommentSQRepository commentSQRepository;
	
	//자유게시판 게시글 등록
	@Transactional
	public void 게시글등록(BFreeRegistrationDto bFreeRegistrationDto , PrincipalDetails principalDetails) {
		
		BoardFree com = bFreeRegistrationDto.toEntity();
		com.setUser(principalDetails.getUser());
		boardFreeRepository.save(com);
	}
	
	
	//자유게시판 게시글 리스트
	@Transactional(readOnly = true)
	public Page<BFreeListInterface> 게시글목록(Pageable pageable){
		
		return boardFreeRepository.findByAllDesc(pageable);
	}
	
	
	//자유게시판 게시글 1개 조회
	@Transactional(readOnly = true)
	public BFreeResponseDto 게시글조회(int id) {
		
		BoardFree boardFree = boardFreeRepository.findById(id).orElseThrow();

		return getBoardFreeAndCommentRequest(boardFree);
	}
	
	/**
	 * boardFree 테이블 조회 결과를 DTO에 담아내기 위한 함수 
	 * */
	private BFreeResponseDto getBoardFreeAndCommentRequest(BoardFree boardFree) {
		
		BFreeResponseDto bfdto = new BFreeResponseDto(boardFree);
		
		List<BFreeCommentResponseDto> commentList = new ArrayList<>();
		
		//댓글 목록
		for(int i =0; i < boardFree.getComments().size(); i++) {
			
			BFreeCommentResponseDto bfComment = new BFreeCommentResponseDto();
			bfComment.setId(boardFree.getComments().get(i).getId());
			bfComment.setContent(boardFree.getComments().get(i).getContent());
			bfComment.setUser(boardFree.getComments().get(i).getUser());
			//bfComment.setCreateDate(boardFree.getComments().get(i).getCreateDate());
			//댓글 작성 날짜 포맷 변경
			try {
				bfComment.setCreateDateFormatted(DateCommonService.날짜포맷변경시간추가(boardFree.getComments().get(i).getCreateDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			commentList.add(bfComment);
		}
		
		//게시글 작성 날짜 포맷 변경
		try {
			bfdto.setCreateDateFormatted(DateCommonService.날짜포맷변경시간추가(boardFree.getCreateDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bfdto.setComments(commentList);
		
		return bfdto;
	}
	

	//자유게시판 게시글 1개 수정
	@Transactional
	public BoardFree 게시글수정(int id, BFreeRegistrationDto boardFreeRegistrationDto) throws ParseException {
		
		BoardFree comEntity  = boardFreeRepository.findById(id).orElseThrow();
		
		//내용 변경
		comEntity.setContent(boardFreeRegistrationDto.getContent());
		
		//수정 날짜 변경
		LocalDateTime now = LocalDateTime.now(); 
		String extensionDate = dateCommonService.날짜포맷변경시간추가(now);
		
		comEntity.setEditDate(extensionDate);
		
		return comEntity;
	}


	//자유게시판 게시글 1개 삭제 시 useState를 false로 변경
	@Transactional
	public BoardFree 게시글삭제(int id) {
		
		BoardFree bfEntity = boardFreeRepository.findById(id).orElseThrow();
		bfEntity.setUseState(false);
		
		return bfEntity;
	}
	
	//자유게시판 게시글 1개 조회수 증가
	@Transactional
	public BoardFree 게시글조회수증가(int boardFreeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		//요청으로부터 받은 쿠키
		Cookie[] cookies = request.getCookies();
		
		/**
		 https://developersoo.tistory.com/14
		 * */
		BoardFree comEntity  = boardFreeRepository.findById(boardFreeId).orElseThrow();
		
		Cookie 기존쿠키 = null;
		
		//저장된 쿠키가 있거나 길이가 0보다 클 때
		if(cookies != null && cookies.length > 0) {
			
			//쿠키 길이만큼 반복
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("FreeBoardReadCookie"+boardFreeId)) {
					System.out.println("쿠키 있음. 조회수 증가 안 함");
					기존쿠키 = cookies[i];
				}
			}
		}
		
		
		//저장된 쿠키가 없을 때
		if(기존쿠키 == null) {
			System.out.println("쿠키 없음. 조회수 증가.");
			
			//name = customCookie글번호, value = 글번호
			Cookie 새쿠키 = new Cookie("FreeBoardReadCookie"+boardFreeId,"["+boardFreeId+"]");
			
			response.addCookie(새쿠키);
			comEntity.addReadCount(boardFreeId);
		}
		//저장된 쿠키가 있을 때
		else {
			//조회수 증가하지 않음
		}
		return comEntity;
	}
	
	
	//자유게시판 게시글의 댓글 등록
	@Transactional
	public void 댓글등록(CommentRegistrationDto c) {
		commentRepository.commentSave(Integer.parseInt(c.getUserId()), Integer.parseInt(c.getBoardFreeId()), c.getContent());
		
	}
	
	//자유게시판 댓글 1개 삭제
	@Transactional
	public void 댓글삭제(int id) {
		commentRepository.deleteById(id);
	}
	
	
	
	
	//공지사항 목록
	public Page<BoardNotice> 공지사항목록(Pageable pageable){
		
		return boardNoticeRepository.findByAllDesc(pageable);
	}
	
	
	//공지사항 목록 10개만
	public List<BoardNotice> 공지사항목록10개(){
		
		return boardNoticeRepository.findByAllDescTop10();
		
	}
	
	
	//공지사항 게시글 등록
	@Transactional
	public void 공지사항게시글등록(BNoticeRegistrationDto bNoticeRegistrationDto , PrincipalDetails principalDetails) {
		
		BoardNotice com = bNoticeRegistrationDto.toEntity();
		com.setUser(principalDetails.getUser());
		boardNoticeRepository.save(com);
	}
	
	
	//공지사항 게시글 1개 조회
	@Transactional(readOnly = true)
	public BNoticeResponseDto 공지사항게시글조회(int id) {
		
		BoardNotice boardNotice = boardNoticeRepository.findById(id).orElseThrow();

		return getBoardNoticeRequest(boardNotice);
	}
	
	
	//자유게시판 게시글 1개 조회수 증가
	@Transactional
	public BoardNotice 공지사항게시글조회수증가(int boardNoticeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		//요청으로부터 받은 쿠키
		Cookie[] cookies = request.getCookies();
		
		/**
		 https://developersoo.tistory.com/14
		 * */
		BoardNotice comEntity  = boardNoticeRepository.findById(boardNoticeId).orElseThrow();
		
		Cookie 기존쿠키 = null;
		
		//저장된 쿠키가 있거나 길이가 0보다 클 때
		if(cookies != null && cookies.length > 0) {
			
			//쿠키 길이만큼 반복
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("NoticeBoardReadCookie"+boardNoticeId)) {
					System.out.println("쿠키 있음. 조회수 증가 안 함");
					기존쿠키 = cookies[i];
				}
			}
		}
		
		
		//저장된 쿠키가 없을 때
		if(기존쿠키 == null) {
			System.out.println("쿠키 없음. 조회수 증가.");
			
			//name = customCookie글번호, value = 글번호
			Cookie 새쿠키 = new Cookie("NoticeBoardReadCookie"+boardNoticeId,"["+boardNoticeId+"]");
			
			response.addCookie(새쿠키);
			comEntity.addReadCount(boardNoticeId);
		}
		//저장된 쿠키가 있을 때
		else {
			//조회수 증가하지 않음
		}
		return comEntity;
	}

	
	
	
	
	/**
	 * BoardNotice 테이블 조회 결과를 DTO에 담아내기 위한 함수 
	 * */
	private BNoticeResponseDto getBoardNoticeRequest(BoardNotice boardNotice) {
		
		BNoticeResponseDto bndto = new BNoticeResponseDto(boardNotice);
		
		//게시글 작성 날짜 포맷 변경
		try {
			bndto.setCreateDateFormatted(DateCommonService.날짜포맷변경시간추가(boardNotice.getCreateDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bndto;
	}
	
	
	
	
	//공지사항 게시글 1개 수정
	@Transactional
	public BoardNotice 공지사항게시글수정(int id, BNoticeRegistrationDto boardNoticeRegistrationDto) throws ParseException {
		
		BoardNotice comEntity  = boardNoticeRepository.findById(id).orElseThrow();
		
		//내용 변경
		comEntity.setContent(boardNoticeRegistrationDto.getContent());
		
		//수정 날짜 변경
		LocalDateTime now = LocalDateTime.now(); 
		String extensionDate = dateCommonService.날짜포맷변경시간추가(now);
		
		comEntity.setEditDate(extensionDate);
		
		return comEntity;
	}


	
	//공지사항 게시글 1개 삭제
	@Transactional
	public BoardNotice 공지사항게시글삭제(int id) {
		
		BoardNotice bnEntity = boardNoticeRepository.findById(id).orElseThrow();
		bnEntity.setUseState(false);
		
		return bnEntity;
	}
	
	
	
	
	
	
	//1대1 문의 게시판 목록 보기
	@Transactional
	public  Page<SingleQuestion>  일대일문의게시판목록(Pageable pageable) {
		return singleQuestionRepository.findByAllDesc(pageable);
	}

	
	//1대1 문의 질문글 등록
	public void 일대일문의게시글등록(
			SQuestionRegistrationDto singleQuestionRegistrationDto,
			PrincipalDetails principalDetails) {
		
		SingleQuestion sq = singleQuestionRegistrationDto.toEntity();
		sq.setAnswerOk(false);
		sq.setUser(principalDetails.getUser());
		sq.setUseState(true); // insert 기본 값 세팅
		singleQuestionRepository.save(sq);
		
		
	}


	
	//1대1 문의 게시글 1개 조회
	@Transactional(readOnly = true)
	public SQuestionResponseDto 일대일질문게시글조회(int singleQuestionId) {
		
		SingleQuestion sq = singleQuestionRepository.findById(singleQuestionId).orElseThrow();

		return getSingleQuestionAndCommentRequest(sq);
	}
	
	/**
	 * boardFree 테이블 조회 결과를 DTO에 담아내기 위한 함수 
	 * */
	private SQuestionResponseDto getSingleQuestionAndCommentRequest(SingleQuestion sq) {
		
		SQuestionResponseDto sqResponseDto = new SQuestionResponseDto(sq);
		
		//답변 세팅
		//질문 게시글에 달린 답변을 찾아야 함
		int singleQuestionId = sq.getId();
		CommentSQ commentSQ = commentSQRepository.findbySingleQuestionId(singleQuestionId);
		
		if(!(commentSQ == null)) {
			//세팅한 답변을 게시글에 세팅
			sqResponseDto.setCommentSQ(commentSQ);
		}
	
		//게시글 작성 날짜 포맷 변경
		try {
			sqResponseDto.setCreateDateFormatted(DateCommonService.날짜포맷변경시간추가(sq.getCreateDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqResponseDto;
	}
		
	
	//1대1 문의 게시판 게시글 1개 삭제
	@Transactional
	public SingleQuestion 일대일질문게시글삭제(int id) {
		
		SingleQuestion sqEntity = singleQuestionRepository.findById(id).orElseThrow();
		sqEntity.setUseState(false);
		
		return sqEntity;
	}
	
	
	//1대1 문의 게시판 답변 등록
	@Transactional
	public SingleQuestion 일대일질문게시글답변등록(SQuestionCommentRegistrationDto s) {
		
		//답변여부 True로 수정
		SingleQuestion sq = singleQuestionRepository.findById(s.getSingleQuestionId()).orElseThrow();
		sq.setAnswerOk(true);
		
		int singleQuestionId = sq.getId();
		commentSQRepository.commentSQSave(Integer.parseInt(s.getUserId()), singleQuestionId, s.getContent());
		
		return sq;
		
	}

	
	//userId의 작성글 목록
	@Transactional
	public Page<UserBoardHistoryInterface> 작성글목록(int userId, Pageable pageable){
		
		Page<UserBoardHistoryInterface> userBoardHistory = boardFreeRepository.findBoardHistoryByUserId(userId, pageable);
		
		return userBoardHistory;
	}



}

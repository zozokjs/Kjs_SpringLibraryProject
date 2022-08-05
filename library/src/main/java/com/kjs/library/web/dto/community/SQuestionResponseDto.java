package com.kjs.library.web.dto.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.comment.CommentSQ;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.domain.community.SingleQuestion;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.common.DateCommonService;

import lombok.Getter;
import lombok.Setter;

/**
 * SingleQuestion(1대1문의게시판)과 CommentSQ(1대1문의게시판 답글)간의 무한 반복을 제거하기 위한 DTO
 * */
@Getter
@Setter
public class SQuestionResponseDto {
		
	private int id; //게시글 아이디
	private String title; //게시글 제목
	private String content; //내용
	private LocalDateTime createDate; //생성일
	
	private String createDateFormatted; //생성일(포맷 변경)
	
	private final User user; //작성자 정보
	private CommentSQ commentSQ; //답변 정보
	
	
	public SQuestionResponseDto(SingleQuestion singleQuestion) {
		this.id = singleQuestion.getId();
		this.title = singleQuestion.getTitle();
		this.content = singleQuestion.getContent();
		this.createDate = singleQuestion.getCreateDate();
		this.user = singleQuestion.getUser();
		this.commentSQ = singleQuestion.getCommentSQ();
	}
}

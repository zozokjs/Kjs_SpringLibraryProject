package com.kjs.library.web.dto.community;

import java.time.LocalDateTime;
import java.util.List;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.comment.CommentSQ;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * SingleQuestion(1대1질문게시판)과 Comment(그 답변)간의 무한 반복을 제거하기 위한 DTO
 * */
@Getter
@Setter
@NoArgsConstructor
public class SQuestionCommentResponseDto {

	private int id;
	private String content; //답변 내용
	private User user; //답변 작성자 정보
	private LocalDateTime createDate; //생성일
	private String createDateFormatted;
	
	public SQuestionCommentResponseDto(CommentSQ commentSQ) {
		this.id = commentSQ.getId();
		this.content = commentSQ.getContent();
		this.user = commentSQ.getUser();
		this.createDate = commentSQ.getCreateDate();
	}
	
}

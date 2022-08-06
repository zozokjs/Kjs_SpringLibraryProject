package com.kjs.library.web.dto.community;

import javax.validation.constraints.NotBlank;

import com.kjs.library.domain.comment.Comment;

import lombok.Data;

/**
 * 1대1문의게시판(SingleQuestion)에서 답변(CommentSQ)을 등록할 때 사용하는 DTO
 * 
 * */
@Data
public class SQuestionCommentRegistrationDto {

	@NotBlank
	private String content;
	
	private String userId;
	
	private Integer singleQuestionId;
	
}

package com.kjs.library.web.dto.community;

import javax.validation.constraints.NotBlank;

import com.kjs.library.domain.comment.Comment;

import lombok.Data;

@Data
public class SQuestionCommentRegistrationDto {

	@NotBlank
	private String content;
	
	private String userId;
	
	private Integer singleQuestionId;
	
}

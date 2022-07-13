package com.kjs.library.web.dto.boardFree;

import javax.validation.constraints.NotBlank;

import com.kjs.library.domain.comment.Comment;

import lombok.Data;

@Data
public class CommentRegistrationDto {

	@NotBlank
	private String content;
	
	private String userId;
	
	private String boardFreeId;
	
}

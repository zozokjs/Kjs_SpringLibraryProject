package com.kjs.library.web.dto.boardFree;

import java.time.LocalDateTime;
import java.util.List;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BFreeCommentResponseDto {

	private int id;
	private String content; //댓글 내용
	private User user; //댓글 작성자 정보
	private LocalDateTime createDate; //생성일
	
	public BFreeCommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.user = comment.getUser();
		this.createDate = comment.getCreateDate();
	}
	
}

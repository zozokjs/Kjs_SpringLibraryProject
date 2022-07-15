package com.kjs.library.web.dto.boardFree;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BFreeResponseDto {
	
	private final int id; //게시글 아이디
	private final String title; //게시글 제목
	private final String content; //내용
	private final int readCount; //조회수
	private final String editDate; //수정일
	private final LocalDateTime createDate; //생성일
	private final User user; //작성자 정보
	private List<BFreeCommentResponseDto> comments; //댓글 정보
	
	public BFreeResponseDto(BoardFree boardFree) {
		this.id = boardFree.getId();
		this.title = boardFree.getTitle();
		this.content = boardFree.getContent();
		this.readCount = boardFree.getReadCount();
		this.editDate = boardFree.getEditDate();
		this.createDate = boardFree.getCreateDate();
		this.user = boardFree.getUser();
		this.comments = new ArrayList<>();
	}
	
}

package com.kjs.library.web.dto.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.common.DateCommonService;

import lombok.Getter;
import lombok.Setter;

/**
 * boardNotice와 Comment간의 무한 반복을 제거하기 위한 DTO
 * */
@Getter
@Setter
public class BNoticeResponseDto {
		
	private int id; //게시글 아이디
	private String title; //게시글 제목
	private String content; //내용
	private int readCount; //조회수
	private String editDate; //수정일
	private LocalDateTime createDate; //생성일
	
	private String createDateFormatted; //생성일(포맷 변경)
	
	private final User user; //작성자 정보
	//private List<BFreeCommentResponseDto> comments; //댓글 정보
	
	
	public BNoticeResponseDto(BoardNotice boardNotice) {
		this.id = boardNotice.getId();
		this.title = boardNotice.getTitle();
		this.content = boardNotice.getContent();
		this.readCount = boardNotice.getReadCount();
		this.editDate = boardNotice.getEditDate();
		this.createDate = boardNotice.getCreateDate();
		this.user = boardNotice.getUser();
		//this.comments = new ArrayList<>();
	}
}

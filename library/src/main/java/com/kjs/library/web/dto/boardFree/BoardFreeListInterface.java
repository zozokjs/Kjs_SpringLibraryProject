package com.kjs.library.web.dto.boardFree;

import java.time.LocalDateTime;

import com.kjs.library.domain.user.User;

public interface BoardFreeListInterface {
	 int getId(); // 게시글 id
	 String getTitle(); //게시글 제목
	 int getContent(); // 게시글 내용
	 int getReadCount(); //조회수 
	 //String getUserId(); //게시글 작성자 아이디
	 LocalDateTime getCreateDate(); //게시글 작성 날짜
	 String getEditDate(); //게시글 수정일
	 String getUsername();
	 int getCommentCount(); //게시글에 등록된 댓글 수
}

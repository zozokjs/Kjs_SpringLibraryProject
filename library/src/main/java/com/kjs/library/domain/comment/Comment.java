package com.kjs.library.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	//여러 개의 댓글은 하나의 게시글에 존재한다.
	//@JsonIgnoreProperties({"comment"})
	@ManyToOne
	@JoinColumn(name="boardFreeId")
	private BoardFree boardFree;
	
	//하나의 댓글은 하나의 유저가 가진다.
	//여러개의 댓글은 하나의 유저가 가진다.
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	//내용
	private String content;
	
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}

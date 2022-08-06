package com.kjs.library.domain.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.SingleQuestion;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//1대1 게시판의 답변이 저장됨
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentSQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	@JoinColumn(name="userId")
	@OneToOne
	private User user;
	
	@JoinColumn(name="singleQuestionId")
	@OneToOne
	private SingleQuestion singleQuestion;
	
	
	@Lob //대용량 데이터 저장
	@Column(nullable = false)
	private String content;
	
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}

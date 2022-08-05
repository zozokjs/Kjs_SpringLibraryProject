package com.kjs.library.domain.community;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.comment.CommentSQ;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//1대1 질문게시판
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SingleQuestion {
	
	//게시글 번호
	@Id // PK값 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	//작성자 정보
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	//1대1질문게시판의 질문 글에 대한 답변
	//@OneToMany(mappedBy = "singleQuestion", fetch = FetchType.LAZY)
	
	@JoinColumn(name="commentSqId")
	@OneToOne
	private CommentSQ commentSQ;
	
	
	//게시글 제목
	@Column(nullable = false, length = 100)
	private String title;

	//내용
	@Lob //대용량 데이터 저장
	@Column(nullable = false)
	private String content;
	
	//질문처리여부
	@Column(columnDefinition = "boolean default false")
	private boolean answerOk;
	
	//게시글 작성 날짜
	private LocalDateTime createDate;
	
	//게시글 작성 날짜
	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
		
}

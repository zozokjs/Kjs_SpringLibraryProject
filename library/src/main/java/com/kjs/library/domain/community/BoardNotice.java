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
import javax.persistence.PrePersist;

import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class BoardNotice {
	
	//게시글 번호
	@Id // PK값 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	//작성자 정보
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	//게시글 제목
	@Column(nullable = false, length = 100)
	private String title;

	//내용
	@Lob //대용량 데이터 저장
	@Column(nullable = false)
	private String content;
	
	//조회수
	@Column(columnDefinition = "int default '0' ")
	private int readCount;

	//게시글 수정 날짜
	private String editDate;
	
	//게시글 작성 날짜
	private LocalDateTime createDate;
	
	//게시글 작성 날짜
	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	/**	SETTER                                  */
	//조회수 1증가
	public void addReadCount(int id) {
		this.readCount += 1;
	}
}

package com.kjs.library.domain.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.comment.Comment;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//setter 대신 변경 지점이 명확하도록 메소드를 별도 지정함. 
@Entity
@NoArgsConstructor//
@Data
@AllArgsConstructor
@Builder
public class BoardFree {
	
		//게시글 번호
		@Id // PK값 설정
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
		private int id;
		
		//작성자 정보
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="userId")
		private User user;
		
		//게시글에 등록된 댓글 정보
		/** 
		 * https://velog.io/@conatuseus/%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84-%EB%A7%A4%ED%95%91-%EA%B8%B0%EC%B4%88-2-%EC%96%91%EB%B0%A9%ED%96%A5-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84%EC%99%80-%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84%EC%9D%98-%EC%A3%BC%EC%9D%B8
		 * 하나의 게시글은 여러 개의 댓글을 가진다.
		 * 게시글을 조회할 때는 BoardFree, User, Comment 테이블이 Join 되어 조회되어야 함.  하지만 JPA를 쓰고 있고, BoardFree 클래스 내부에 User와 Comment가 있어서 안 그래도 된다.
			하나의 게시글에는 여러 개의 댓글이 생길 수 있으므로 BoardFree 테이블에는 Comment 컬럼이 생기지 않아야 한다.
		 * 그래서 mappedBy에 Comment 테이블의 FK를 적어 명시한다. 
		 * 이 둘 사이으 주인은 Comment라는 뜻이다.
		 * 따라서 연관관계의 주인인 Comment는 테이블은 외래키를 등록, 수정, 삭제할 수 있고
		 * 주인이 아닌 BoardFree는 읽기만 할 수 있다.
		 * */
		@JsonIgnoreProperties({"boardFree"})
		@OneToMany(mappedBy = "boardFree", fetch = FetchType.EAGER)
		private List<Comment> comment;
		
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
		//작성자 정보 세팅
		public void setUser(User user) {
			this.user = user;
		}
		
		//내용 세팅
		public void setContent(String content) {
			this.content = content;
		}
		
		//조회수 1증가
		public void addReadCount(int id) {
			this.readCount += 1;
		}
		
		//제목 세팅
		public void setTitle(String title) {
			this.title=title;
		}

		//게시글 수정 날짜 세팅
		public void setEditDate(String editDate) {
			this.editDate=editDate;
		}

		
}

package com.kjs.library.domain.lend;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.DynamicInsert;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//한 줄의 대여 내역을 정의하는 테이블
@Entity
@Getter
@Setter
@NoArgsConstructor // 빈 생성자
@DynamicInsert
public class Lend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; //자식 entity는 List<Lend>
	
	@JoinColumn(name="bookId")
	@OneToOne
	private Book book; //자식 entity는 lend
	
	@JoinColumn(name="samebookId")
	@OneToOne
	private Samebook samebook; //자식 entity는 book과 lend

	
	
	
	//JPA를 통하지 않고 DB에 직접 값을 넣는 경우에는 Default 옵션과 DynamicInsert 옵션이 통하지 않음.
	//연장 가능 횟수(기본값 1)
	@Column(columnDefinition = "int default '1' ")
	private int extensionAbleCount;
	
	//연장 날짜
	private String extensionDate;

	//반납 예정일
	private String returnPlanDate;
	
	//실제 반납 날짜
	private String returnDate;
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}


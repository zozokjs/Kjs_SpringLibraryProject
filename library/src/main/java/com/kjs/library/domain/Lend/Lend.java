package com.kjs.library.domain.Lend;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
//대여 내역
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="lend_uk", columnNames = { "lendUserId","lendBookId" }
						)
		}
			)
public class Lend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가 설정함. 정책은 DB처럼 번호 증가함
	private int id;
	
//빌린 사람 아이디
//Lend가  Many, User가 One
@JoinColumn(name="lendUserId")
@ManyToOne
private User lendUser;

//빌린 책 아이디
@JoinColumn(name="lendBookId")
@OneToMany(mappedBy = "") //Lend가 One, Book이 Many 
private List<Book> lendBook;
	
	//빌린 날짜
	private String lendDate;
	
	//반납 날짜
	private String returnDate;
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

*/
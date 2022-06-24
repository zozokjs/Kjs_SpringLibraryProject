package com.kjs.library.domain.lend;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//한 줄의 대여 내역을 정의하는 테이블
@Entity
@Getter
@Setter
@NoArgsConstructor // 빈 생성자
/*@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="lend_uk",  //유니크 제약 조건 이름
						columnNames = { "lendUserId","lendSamebook" } //실제 db에 잇는 컬럼 이름을 써야 함
						)
		}
)//db 테이블에서 하나의 행을 보면 1, 2가 들어 갔을 때 그 다음 행에서 1, 2가 들어가는 걸 방지하는 조치. 중복 방지. 39번 영상
*/
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
	
	//반납 날짜
	private String returnDate;
	
	//등록 날짜
	private LocalDateTime createDate;

	@PrePersist // db에 insert 되기 직전에 실행 됨. 직전에 현재 시간을 가져와 넣어줌.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}


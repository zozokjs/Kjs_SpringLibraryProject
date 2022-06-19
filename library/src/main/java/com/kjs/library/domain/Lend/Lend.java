package com.kjs.library.domain.Lend;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
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
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//한 장의 대여 내역을 정의하는 테이블
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	//대여 번호
	private int lendListId;
	
	//빌린 사람 아이디
	//[유저 : 한 장의 대여 내역]
	//한 명의 유저는 여러 장의 대여 내역을 가진다. ->  1 : N  
	//한 명의 유저는 한 장의 대여 내역을 가진다.    ->  1 : 1
	//여러 명의 유저는 한 장의 대여 내역을 가진다...불가능      
	//여러 명의 유저는 여러 장의 대여 내역을 가진다.. ->  N : N 
	//한 장의 대여 내역은 한 명의 유저를 가진다.     ->  1 : 1
	//한 장의 대여 내역은 여러 명의 유저를 가진다...불가능
	//여러 장의 대여 내역은 한 명의 유저를 가진다.  -> 1 : N
	//한 장의 대여내역Lend가  Many, 유저User가 One
	//연관 관계 주인은 LEND. LEND가 FK를 가진다.
	@JoinColumn(name="lendUserId")
	@ManyToOne
	private User user;
	
	//빌린 책 아이디
	//한 권의 책은 한 장의 대여 정보를 가진다.     ->   책 : 대여정보 = 1 : 1
	//여러 권의 책은 한 장의 대여 내역을 가진다.  ->  책 : 대여정보 = N : 1
	//한 장의 대여 내역은 많은 권의 책을 가진다.   -> 책 : 대여정보 = N : 1
	//여러 장의 대여 내역은 한 권의 책을 가진다.. 불가능
	//SAMEBOOK이 N, LEND가 1
	//연관 관계의 주인은 SAMEBOOK. FK는 SAMEBOOK이 가진다.
	//SAMEBOOK 모델에서 LEND 객체를 가져온 뒤 JOINCOLUMN(lendId)를 설정함.
	///////////////////////////////////////////////////////////////
	@Column(name="lendSamebookId")
	@OneToMany(mappedBy = "lend")
	private List<Samebook> lendSamebook;
	
	
	//빌린 책 한 종류의 아이디
	//[한 종류의 책 : 한 장의 대여 내역]
	//한 종류의 책은 한 장의 대여 내역을 가진다.    -> 1 : 1
	//여러 종류의 책은 한 장의 대여 내역을 가진다.  -> N : 1
	//여러 종류의 책은 여러 장의 대여 내역을 가진다. -> N : N 
	//여러 장의 대여 내역은 한 종류의 책을 가진다.   -> 1 : N
	//
	//private Book book;  ..불가능.. 이게 있으면 중간테이블 생성 이유가 없음
	private int bookId;
	
	
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


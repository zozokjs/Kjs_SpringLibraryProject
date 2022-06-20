package com.kjs.library.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final LendRepository lendRepository;
	private final SamebookRepository samebookRepository;
	
	
	//책 대출, 반납, 희망도서 신청, 희망도서 신청 취소 등
	
	//1권 씩 대출할 때?
	@Transactional
	public void 책대출(int bookId, int loginId) {                       
		
		/**
		 * 1. 로그인 아이디로 책을 대출 했는지 확인
		 * 2. 책 아이디로 대여 가능 권 수 확인
		 *   -> 가능하면 
		 *     -> bookId로 SameBook 데이터를 가져와서 대출 안 된 청구기호를 가져옴
		 *     -> 그걸 db에 넣는다.
		 *   -> 불가능하면 안 된다고 알려줌
		 * 
		 * */
		
		Lend lend = new Lend();
		Book book = new Book();
		Samebook samebook = new Samebook();
		
		User user = new User();
		
		//System.out.println("---책 아이디 "+bookId);
		//System.out.println("---로그인 아이디 " +loginId);
		
		//대출 가능한 청구 기호가 없거나 이미 대출된 책bookId인 경우
		
		//1. 청구기호 찾기 및 세팅
		String kdcCallSignOne = 청구기호찾기(bookId);
		if(kdcCallSignOne == null) {
			System.out.println("대출 가능한 청구 기호 없음. 대출 불가 처리 필요");
		}else {
			samebook.setKdcCallSign(kdcCallSignOne); //청구기호 세팅
			lend.setSamebook(samebook); //자식 entity도 영속화되게끔 cascade 옵션을 Lend의 sameBook에 추가했음.
		}
		
		//System.out.println("---세팅된 청구기호 " +kdcCallSignOne);
		
		//2. 책 1종류의 아이디를 세팅 
		book.setId(bookId);
		lend.setBook(book); 
		
		//3. 로그인 한 사람의 아이디를 세팅
		user.setId(loginId);
		lend.setUser(user);
		
		//4. 대출 날짜를 세팅
		LocalDateTime now = LocalDateTime.now();
		lend.setLendDate(now.toString());
		
		
		//System.out.println("---세팅된 대출 날짜 " +now.toString());
		
		System.out.println("서비스 다 읽음---------");
		lendRepository.save(lend);
				
	}
	
	
	@Transactional(readOnly = true)
	public String 청구기호찾기(int bookId) {
		
		//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
		//String kdcCallSignOne = lendRepository.findKdcCallSignBybookid(bookId);
		
		String kdcCallSignOne = lendRepository.findKdcCallSignBybookidTest(bookId);
		return kdcCallSignOne;
	}
	
	@Transactional(readOnly = true)	
	public Integer 대출번호찾기(int bookId, int loginId) {
		
		//findLendListIdByBookIdAndUserId
		int lendListId = lendRepository.findLendListIdByBookIdAndUserId(bookId,loginId);
		
		return lendListId;
	}
	
	
	
	@Transactional(readOnly = true)
	public boolean 대출가능여부(int bookId, int loginId) {
		
		//로그인 된 아이디로 bookid를 대출 했는가?
		//bookid의 대출 가능한 청구 기호가 존재하는가? 
		
		return true;
	}
	
	
}

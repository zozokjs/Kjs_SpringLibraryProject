package com.kjs.library.service;

import java.util.List;

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

	private final SamebookRepository samebookRepository;
	private final LendRepository lendRepository;
	
	//책 대출, 반납, 희망도서 신청, 희망도서 신청 취소 등
	
	//1권 씩 대출할 때?
	@Transactional
	public void 책대출(int bookId, int loginId) {                       
		
		Lend lend = new Lend();
		Book book = new Book();
		Samebook samebook = new Samebook();
		User user = new User();
		
		//대출 가능한 청구 기호가 없거나 이미 대출된 책bookId인 경우
		
		//1. 청구기호 찾기 및 세팅
		int samebookId = 청구기호아이디찾기(bookId);
		samebook.setId(samebookId); 	
		lend.setSamebook(samebook); //청구기호 세팅	
		
		
		//2. 책 1종류의 아이디를 세팅 
		book.setId(bookId);
		lend.setBook(book); 
		
		//3. 로그인 한 사람의 아이디를 세팅
		user.setId(loginId);
		lend.setUser(user);
		
		//4. 대출 여부를 Samebook 테이블에 true로 세팅
		Samebook samebookEntity = samebookRepository.findById(samebookId).orElseThrow();//영속화
		samebookEntity.setLendState(true); 
		
		
		System.out.println("세팅된 책번호  " +bookId);
		System.out.println("세팅된 청구기호 아이디 " +samebookId);
		
		lend.setSamebook(samebook);
		
		lendRepository.save(lend);
		
	}
	
	@Transactional(readOnly = true)
	public Integer 청구기호아이디찾기(int bookId) {
		
		//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
		int samebooId =  lendRepository.findKdcCallSignBybookid(bookId);
			
		return samebooId;
	}


	@Transactional(readOnly = true)
	public boolean 대출했다(int bookId, int loginId) {
		
		//로그인 된 아이디로 bookid를 대출 했는가?
		Lend lend = lendRepository.findLendAbleByBookIdAndUserId(bookId, loginId);
		//lend 값이 있다 > 대출
		
		if(lend != null) {
			return true; //대출 했음
		}else {
			return false; //대출 안 했음
		}
	}
	
	
	@Transactional(readOnly = true)
	public boolean 잔여책존재한다(int bookId) {
		
		//bookId로 검색했을 때, 대여 가능한 책이 있는가?
		List<Integer> samebookVolume = lendRepository.findLendAbleSamebookVolume(bookId);
		
		//lend 값이 있으면 대출 가능
		if(!samebookVolume.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	
}

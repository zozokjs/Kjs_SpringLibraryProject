package com.kjs.library.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.Lend.Lend;
import com.kjs.library.domain.Lend.LendRepository;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
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
		 * 
		 * 1. 로그인 아이디로 책을 대출 했는지 확인
		 * 2. 책 아이디로 대여 가능 권 수 확인
		 *   -> 가능하면 
		 *     -> bookId로 SameBook 데이터를 가져와서 대출 안 된 청구기호를 가져옴
		 *     -> 그걸 db에 넣는다.
		 *   -> 불가능하면 안 된다고 알려줌
		 * 
		 * */
		
		/**
		 * 1. 프론트에서 넘어온 bookId로 청구기호 검색
		 * 2. 청구기호 중 대출중이 아닌 첫번째 청구기호를 여기로 가져옴
		 * 3. 청구기호를 기준으로 bookId를 검색함
		 * 
		 * */
		
		Lend lend = new Lend();
		Samebook samebook = new Samebook();
		
		User user = new User();
		
		System.out.println("------------");
		System.out.println("---책 아이디 "+bookId);
		System.out.println("---로그인 아이디 " +loginId);
		
		//1. 청구기호 찾기 및 세팅
		String kdcCallSignOne = 청구기호찾기(bookId);
		if(kdcCallSignOne == null) {
			System.out.println("대출 가능한 청구 기호 없음. 대출 불가 처리 필요");
		}else {
			samebook.setKdcCallSign(kdcCallSignOne); //청구기호 세팅
		}
		List<Samebook> samebookList = Arrays.asList(new Samebook[]{samebook});
		//samebookList.set(0, samebook);
		
		lend.setLendSamebook(samebookList);
		System.out.println("---세팅된 청구기호 " +kdcCallSignOne);
		
		//2. 책 1종류의 아이디를 세팅 
		lend.setBookId(bookId); 
		
		//3. 로그인 한 사람의 아이디를 세팅
		user.setId(loginId);
		lend.setUser(user);
		
		//4. 대출 날짜를 세팅
		LocalDateTime now = LocalDateTime.now();
		lend.setLendDate(now.toString());
		
		
		System.out.println("---세팅된 대출 날짜 " +now.toString());
		//5. 대출 번호를 세팅
		/*
		int lendListId = 대출번호찾기(bookId, loginId); //기존 대출 번호를 가져옴
		lendListId = lendListId + 1;   // 1개 증가
		lend.setLendListId(lendListId); 
		*/
		
		System.out.println("서비스 다 읽음---------");
		lendRepository.save(lend);
				
	}
	
	
	@Transactional(readOnly = true)
	public String 청구기호찾기(int bookId) {
		
		//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
		String kdcCallSignOne = lendRepository.findKdcCallSignBybookid(bookId);
		
		return kdcCallSignOne;
	}
	
	@Transactional(readOnly = true)	
	public Integer 대출번호찾기(int bookId, int loginId) {
		
		//findLendListIdByBookIdAndUserId
		int lendListId = lendRepository.findLendListIdByBookIdAndUserId(bookId,loginId);
		
		return lendListId;
	}
	
	
	
	@Transactional(readOnly = true)
	public boolean 대출가능여부(int bookId) {
		
		return true;
	}
	
	
}

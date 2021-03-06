package com.kjs.library.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final SamebookRepository samebookRepository;
	private final LendRepository lendRepository;
	private final DateCommonService dateCommonService;
	
	private final BookSelectService bookSelectService;
	
	
	
	//INSERT
	//1권 씩 대출할 때
	/**한 권 대출 처리*/
	@Transactional
	public void 책대출(int bookId, int loginId) {                       
		
		Lend lend = new Lend();
		Book book = new Book();
		Samebook samebook = new Samebook();
		User user = new User();
		
		//대출 가능한 청구 기호가 없거나 이미 대출된 책bookId인 경우
		
		//1. 청구기호 찾기 및 세팅
		int samebookId = bookSelectService.청구기호아이디찾기(bookId);
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
		
		
		//5. 반납예정일 세팅
		String returnPlanDate = null;
		try {
			//오늘 날짜를 가져와서 반납예정날자를 구함(그 날짜가 주말이면 1일 더함.. 그 이후 계산 안 됨. 미완성)
			returnPlanDate = dateCommonService.반납예정날짜();
		} catch (Exception e) {
			System.out.println("에러 "+e);
		}
		lend.setReturnPlanDate(returnPlanDate);
		
		
		//System.out.println("세팅된 책번호  " +bookId);
		//System.out.println("세팅된 청구기호 아이디 " +samebookId);
		
		lend.setSamebook(samebook);
		
		lendRepository.save(lend);
		
	}
	
	
	//UPDATE
	/**한 권 대출할 때 Book 테이블의 remainAmount 수정*/
	@Transactional
	public Book 책대출2차처리(int bookId) {
		Book bookEntity = bookRepository.findById(bookId).orElseThrow(); //영속화
		
		String remainAmount = bookEntity.getRemainAmount();
		//System.out.println("대출 전 remain > "+remainAmount);

		int remainAmount_int = Integer.parseInt(remainAmount);
		bookEntity.setRemainAmount(String.valueOf(remainAmount_int-1)); //수정하고 세팅
		
		//System.out.println("대출 후 remain > "+bookEntity.getRemainAmount());
		return bookEntity;
	}
	
	
	//UPDATE
	/**한 권  반납 처리*/
	@Transactional
	public Lend 책반납(int lendId) throws ParseException {
		
		//1. Lend 테이블 가져와서 영속화
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		
		//2. 반납 날짜 세팅
		LocalDateTime now = LocalDateTime.now(); 
		String returnDate = dateCommonService.날짜포맷변경(now);
		
		lend.setReturnDate(returnDate);
		return lend;
	}
	
	/**한 권 반납할 때 Book 테이블의 remainAmount 수정*/
	@Transactional
	public Book 책반납2차처리(int lendId) {
		
		//1. bookId 가져오기
		Lend lendEntity = lendRepository.findById(lendId).orElseThrow();
		int bookId = lendEntity.getBook().getId();
		
		//2. bookId로 영속화
		Book bookEntity = bookRepository.findById(bookId).orElseThrow(); //영속화
		
		String remainAmount = bookEntity.getRemainAmount();
		//System.out.println("반납 전 remain > "+remainAmount);

		int remainAmount_int = Integer.parseInt(remainAmount);
		bookEntity.setRemainAmount(String.valueOf(remainAmount_int+1)); //수정하고 세팅
		
		//System.out.println("반납 후 remain > "+bookEntity.getRemainAmount());
		return bookEntity;
	}
	
	//UPDATE
	/** 대출 상태를 false(반납상태)로 변경*/
	@Transactional
	public Samebook 대출상태false변경(int lendId) {
		
		//1. lendId로 samebook Id 찾기
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		int samebookId = lend.getSamebook().getId();
	
		//2. samebookId로 lendState 업데이트
		Samebook samebook = samebookRepository.findById(samebookId).orElseThrow();
		samebook.setLendState(false);		
		return samebook;
	}
	
	
	//UPDATE
	/**책 대출 기한 연장 처리*/
	@Transactional
	public Lend 책연장(int lendId) throws Exception {
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		
		/**
		 * 책 대출 연장 처리 순서
		 * 1. 연장 횟수 확인
		 * 2. 오늘 날짜 가져옴
		 * 3. 연장 날자 세팅
		 * 4. 반납 날짜를 연장 날짜에서 14일 더하고 세팅
		 * 5. 연장 횟수 세팅
		 * **/
	
		//1. 연장 날짜 세팅
		LocalDateTime now = LocalDateTime.now(); 
		String extensionDate = dateCommonService.날짜포맷변경(now);
		lend.setExtensionDate(extensionDate);
		
		
		//2. 반납예정일 세팅(업데이트)
		String returnPlanDate = null;
		try {
			//오늘 날짜를 가져와서 반납예정날자를 구함(그 날짜가 주말이면 1일 더함.. 그 이후 계산 안 됨. 미완성)
			returnPlanDate = dateCommonService.반납예정날짜();
		} catch (Exception e) {
			System.out.println("에러 "+e);
		}
		lend.setReturnPlanDate(returnPlanDate);
		
		//3. 연장 횟수 세팅
		lend.setExtensionAbleCount(0);
		
		//System.out.println("연장 날짜 "+extensionDate);
		//System.out.println("반납 날짜 "+returnPlanDate);
		//
		return lend;
	}
	



	
}

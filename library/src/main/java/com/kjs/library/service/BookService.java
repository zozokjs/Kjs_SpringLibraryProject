package com.kjs.library.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final SamebookRepository samebookRepository;
	private final LendRepository lendRepository;
	private final CommonService commonService;
	
	
	//책 대출, 반납, 희대도서 신청, 희망도서 신청 취소 등
	
	//사용자 1명의 반납 안 한 대출 목록 
	@Transactional
	public List<UserLendListInterface> 대출목록(int loginId){
		
		List<UserLendListInterface> lend = lendRepository.findUserLendListByUserId(loginId);

		/* 값 체크
		for (int i = 0; i < lend.size(); i++) {
			System.out.println(lend.get(i).getCreateDate() + "    /  " +lend.get(i).getBindType());
		}
		*/
		return lend;
	}
	
	
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
		
		
		//5. 반납예정일 세팅
		String returnPlanDate = null;
		try {
			//오늘 날짜를 가져와서 반납예정날자를 구함(그 날짜가 주말이면 1일 더함.. 그 이후 계산 안 됨. 미완성)
			returnPlanDate = commonService.반납예정날짜();
		} catch (Exception e) {
			System.out.println("에러 "+e);
		}
		lend.setReturnPlanDate(returnPlanDate);
		
		
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
	
	
	@Transactional
	public Lend 책반납(int lendId) throws ParseException {
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		
		//System.out.println(lend);
		
		LocalDateTime now = LocalDateTime.now(); 
		String returnDate = CommonService.날짜포맷변경(now);
		
		lend.setReturnDate(returnDate);
	
		return lend;
	}
	
	
	
	
	
	
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
		String extensionDate = CommonService.날짜포맷변경(now);
		lend.setExtensionDate(extensionDate);
		
		
		//2. 반납예정일 세팅(업데이트)
		String returnPlanDate = null;
		try {
			//오늘 날짜를 가져와서 반납예정날자를 구함(그 날짜가 주말이면 1일 더함.. 그 이후 계산 안 됨. 미완성)
			returnPlanDate = commonService.반납예정날짜();
		} catch (Exception e) {
			System.out.println("에러 "+e);
		}
		lend.setReturnPlanDate(returnPlanDate);
		
		//3. 연장 횟수 세팅
		lend.setExtensionAbleCount(0);
		
		System.out.println("연장 날짜 "+extensionDate);
		System.out.println("반납 날짜 "+returnPlanDate);
		
		return lend;
	}
	
	
	@Transactional(readOnly = true)
	public boolean 책연장가능하다(int lendId) {
		
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		int bookExtensionAbleCount = lend.getExtensionAbleCount();
		
		if(bookExtensionAbleCount == 0) {
			return false; //연장 불가
		}else {
			return true; //연장 가능
		}
		
	}
	
	
	
}

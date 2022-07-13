package com.kjs.library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.RequiredArgsConstructor;

/**
 * BookService 중 SELECT 하는 것만 별도 관리
 * */
@RequiredArgsConstructor
@Service
public class BookSelectService {
	
	private final BookRepository bookRepository;
	private final SamebookRepository samebookRepository;
	private final LendRepository lendRepository;
	
	//SELECT
	/**사용자 1명의 반납 안 한 대출 목록*/
	@Transactional(readOnly = true)
	public List<UserLendListInterface> 대출목록(int loginId){
		
		List<UserLendListInterface> lend = lendRepository.findUserLendListByUserId(loginId);

		/* 값 체크
		for (int i = 0; i < lend.size(); i++) {
			System.out.println(lend.get(i).getCreateDate() + "    /  " +lend.get(i).getBindType());
		}
		*/
		return lend;
	}
		
	//SELECT
	/**bookId 기준, 대출 가능한 가장 빠른 청구기호 검색*/
	@Transactional(readOnly = true)
	public Integer 청구기호아이디찾기(int bookId) {
		
		//bookId 기준, 대출 상태가 아닌(lendId가 null) 가장 빠른 청구기호 1개 검색
		int samebooId =  lendRepository.findKdcCallSignBybookid(bookId);
			
		return samebooId;
	}

	
	//SELECT
	/**대출 했다면 True*/
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
	
	
	//SELECT
	/**대출 가능한 잔여 책 존재하면 True*/
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
	
	
	//SELECT
	/**대출 기간 연장이 가능하면 True*/
	@Transactional(readOnly = true)
	public boolean 책연장가능하다(int lendId) {
		
		Lend lend = lendRepository.findById(lendId).orElseThrow();
		int bookExtensionAbleCount = lend.getExtensionAbleCount();
		
		//System.out.println("전달 받은 id " + lendId);
		//System.out.println("결과 " + bookExtensionAbleCount);
		
		
		if(bookExtensionAbleCount == 0) {
			return false; //연장 불가
		}else {
			return true; //연장 가능
		}
		
	}
	
	
	//SELECT
	/**책 반납이 가능하면 True*/
	@Transactional
	public boolean 책반납가능하다(int lendId) {
		
		Lend lendEntity = lendRepository.findById(lendId).orElseThrow();
		
		//반납 날짜가 null일 때만 반납 처리
		if(lendEntity.getReturnDate() == null) {
			return true;
		}else {
			System.err.println("반납 처리 불가. returnDate에 값이 있습니다.");
			return false;
		}
	}
	
	//SELECT
	/**사람 1명의 반납 완료된 내역*/
	@Transactional(readOnly = true)
 	public Page<UserLendListInterface> 반납완료내역(int loginId, Pageable pageable){
		Page<UserLendListInterface> lendHistory = lendRepository.findUserLendHistoryByUserId(loginId, pageable);
		return lendHistory;
	}

	
	
}

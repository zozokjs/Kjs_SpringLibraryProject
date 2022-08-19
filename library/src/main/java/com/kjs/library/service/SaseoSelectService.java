package com.kjs.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.handler.aop.ex.CustomApiException;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.book.BookUpdate_kdcDto;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

/**
 * SaseoService 중 SELECT 하는 것만 별도 관리
 * */
@RequiredArgsConstructor
@Service
public class SaseoSelectService {
	
		private final BookRepository bookRepository;
		private final SamebookRepository sameBookRepository;
		
		// SELECT 책 전부 조회(페이징 추가)
		@Transactional(readOnly = true)
		public Page<Book> bookSelectAllToPage(Pageable pageable) {
			
			//Page 처리를 위해 DESC 정렬된 쿼리를 사용함
			Page<Book> bookList = bookRepository.findByAll(pageable);
			return bookList;
		}
		
		//SELECT(createDate 기준으로 가장 최근에 등록된 책 3권만 조회)
		@Transactional(readOnly = true)
		public List<Book> bookSelectLimit3() {
			
			List<Book> bookList = bookRepository.findBookLimit3();
			
			return bookList;
		}
	    	
		
		// SELECT 책 1개의 정보만 가져옴
		@Transactional(readOnly = true)
		public Book bookSelectOne(int id) {
		
			Book bookEntity = bookRepository.findById(id).orElseThrow(()->{
				throw new CustomApiException("해당하는 책이 존재하지 않습니다! 관리자에게 문의하셈");
			});
			
			return bookEntity;
		 }
		
		
		// SELECT 책 1개의 청구기호 정보만 가져옴		
		@Transactional(readOnly = true)
		public List<Samebook> sameBookSelectOne(int bookId) {
			
			//청구기호가 존재하지 않는다면 아무 조치도 취하지 않음
			//System.out.println("전달된 책 아이디 >"+bookId);
			
			List<Samebook> sameBookEntity = sameBookRepository.findBybookid(bookId);
			
			if(sameBookEntity.isEmpty()) {
				//System.out.println("등록된 청구기호가 없음");
				
			}else {
				//System.out.println("등록된 청구기호가 잇음");
				
			}
			return sameBookEntity;
		}
		
		// SELECT
		/** 청구기호 수정 가능 여부 return
		수정 가능하면 True */
		@Transactional(readOnly = true)
		public boolean 청구기호수정가능하다(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto ) {
	
			//bookId로 등록된 samebook 데이터 들고옴
			//List<Samebook> samebookEntity = sameBookRepository.findBybookid(bookId); 
			
			//수정하려는 청구기호를 나눔
			List<Integer> samebookIdList = bookUpdate_kdcDto.getSamebookId();
			
			List<Boolean> result = new ArrayList<>();
			
			//samebookIdList가 null 아닐 때
			if( !(CollectionUtils.isEmpty(samebookIdList)) ) {
				result = sameBookRepository.editAbleKdcCallSign(samebookIdList); ;
				
				//System.out.println("청구기호 수정 가능 여부를 확인합니다.  samebookIdList 길이 > "+samebookIdList.size());
				//System.out.println("청구기호 수정 가능 여부를 확인합니다.  result 길이 > "+result.size());
				
			}else {
				result.add(0, false);
			}
			
			//result에 true가 있으면 true 리턴함.
			boolean resultB = result.contains(true);
			if(resultB == true) {
				return false; //청구기호 수정 불가
			}else {
				return true; //청구기호 수정 가능
			}
}
	

}
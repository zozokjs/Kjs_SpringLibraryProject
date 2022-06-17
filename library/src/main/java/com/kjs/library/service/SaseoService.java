package com.kjs.library.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.handler.aop.ex.CustomApiException;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.web.dto.book.BookRegistrationDto;
import com.kjs.library.web.dto.book.BookRegistration_kdcDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.book.BookUpdate_kdcDto;
import com.kjs.library.web.dto.book.ImageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaseoService {

		private final CommonService commonService;
		private final BookRepository bookRepository;
		private final SamebookRepository sameBookRepository;
		
		// SAVE 책 등록
		@Transactional
		public void 책등록(BookRegistrationDto bookRegistrationDto, PrincipalDetails principalDetails) {                       
			String imageFileName = "";
			
			ImageDto imageDto = new ImageDto();
			
			//첨부한 이미지가 없다면 
			if( bookRegistrationDto.getFile().isEmpty()) {
				imageFileName ="noTitleImage.jpg";
			}else {
				imageDto.setFile(bookRegistrationDto.getFile());
				imageFileName = commonService.사진저장(imageDto, "imageTitle");
			}
			
			Book book = bookRegistrationDto.toEntity(imageFileName);
			
			bookRepository.save(book);
		}
		
		
		
		// SAVE 책 청구기호 등록
		@Transactional
		public void 책청구기호등록(BookRegistration_kdcDto bookRegistration_kdcDto, PrincipalDetails principalDetails) {                       
			
			//넘어온 청구 기호 출력
			System.out.println("-------------------------");
			System.out.println(bookRegistration_kdcDto); //BookRegistration_kdcDto(bookId=1, kdcCallSign=7,8,9)
			System.out.println(bookRegistration_kdcDto.getBook().getId());
			System.out.println(bookRegistration_kdcDto.getKdcCallSign());
			
			String kdcCallSignList = bookRegistration_kdcDto.getKdcCallSign(); // 2,3,4
			String[] array = kdcCallSignList.split(",");
					    
			//출력				
			Samebook sameBook = new Samebook();
			
			LocalDateTime now = LocalDateTime.now();
			
			for(int i=0;i<array.length;i++) {
					sameBook.setUpdateDate(now.toString()); //수정 시간
					sameBook = bookRegistration_kdcDto.toEntity(bookRegistration_kdcDto.getBook(), array[i]);
					sameBookRepository.save(sameBook);
			}
		}
		
		
		
		
		// UPDATE 책 수정
		@Transactional
		public Book 책수정(int bookId, BookUpdateDto bookUpdateDto, String loginedId) {                       
			
			String imageFileName = "";
			
			Book bookEntity = bookRepository.findById(bookId).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 책입니다. 수정 불가");
			});
			
			ImageDto imageDto = new ImageDto();
			
			//이미지가 변경되지 않는다면 null이 표시되고... 기본 이미지가 저장됨
			//null이더라도 기존의 이미지와 같다면 저장하지 않아야 함. 
			
			/*
			System.out.println("기존 거");
			System.out.println(bookEntity.getTitleImageUrl());
			System.out.println("바뀐 거");
			System.out.println(bookUpdateDto.getTitleImageUrl());
			System.out.println("첨부한 파일 이름");
			System.out.println(bookUpdateDto.getFile().getOriginalFilename());
		 	*/
			
			//첨부한 파일 이름
			//bookUpdateDto.getFile().getOriginalFilename();
			
			//파일을 첨부 안 했음
			if(bookUpdateDto.getFile() == null || bookUpdateDto.getFile().isEmpty() ) {
				
				/*
				//기존 것이 noTitleImage라면 저장 안함
				if(bookEntity.getTitleImageUrl().equals("noTitleImage.jpg")) {
					System.out.println("----------------------");
					System.out.println("첨부 안 했는데 기존 것이 noImageTitle이라서 저장 안함");
				}
				//기존 것과 noTitleImage가 아니라면 기본 이미지를 저장함
				else {
					System.out.println("----------------------");
					System.out.println("첨부 안 했는데 기존 것이 존재하므로 기존 것을 유지함");
				}
				*/
			}
			//파일을 첨부 했음
			else {
				imageDto.setFile(bookUpdateDto.getFile());
				imageFileName = commonService.사진저장(imageDto, "imageTitle");
				
				//System.out.println(imageFileName);
				
				//변경된 이미지 이름을 저장함.
				bookEntity.setTitleImageUrl(imageFileName);
				
				System.out.println("----------------------");
				System.out.println("변경된 이미지를 저장함");
			}
			
			
			bookEntity.setEditedUser(loginedId);
			
			bookEntity.setTitle(bookUpdateDto.getTitle());
			bookEntity.setWriter(bookUpdateDto.getWriter());
			bookEntity.setBindType(bookUpdateDto.getBindType());
			bookEntity.setPage(bookUpdateDto.getPage());
			bookEntity.setLanguage(bookUpdateDto.getLanguage());
			bookEntity.setPrice(bookUpdateDto.getPrice());
			bookEntity.setPublishDate(bookUpdateDto.getPublishDate());
			bookEntity.setDeliveryState(bookUpdateDto.getDeliveryState());
			bookEntity.setPublish(bookUpdateDto.getPublish());
			bookEntity.setVolume(bookUpdateDto.getVolume());
			bookEntity.setKdcTable(bookUpdateDto.getKdcTable());
			bookEntity.setKdcCallSignFamily(bookUpdateDto.getKdcCallSignFamily());
			
			//System.out.println("=-==============================");
			//System.out.println(bookEntity);
			
			return bookEntity; //이후 더티 채킹 발생하여 업데이트 됨...
		}
		
		
		
		
		// UPDATE 책 청구기호 수정
		@Transactional
		public List<Samebook> 책청구기호수정(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto, String loginedId) {
			
			System.out.println("----------------");
			Book bookEntity = bookRepository.findById(bookId).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 책 입니다. 책을 찾을 수 없으므로 청구기호 수정 불가");
			});
			
			//기존 db 값을 가져와서 영속화
			List<Samebook> samebookEntity = sameBookRepository.findBybookid(bookId); 
	
			bookEntity.setId(bookId);
						
			String kdcCallSignList = bookUpdate_kdcDto.getKdcCallSign(); // 2,3,4
			String[] array = kdcCallSignList.split(",");
			
			//값을 변경 시킴
			for(int i = 0; i < samebookEntity.size(); i++) {
				
				samebookEntity.get(i).setBook(bookEntity);
				samebookEntity.get(i).setKdcCallSign(array[i]);
			}
			
			//return과 동시에 값 변경을 감지하고 자동 업데이트
			return samebookEntity;
		}
		
		
		
		
		// SELECT 책 전부 조회
		@Transactional(readOnly = true)
		// 서비스 단에는 select만 하더라도 Transactional을 걸어주는 게 좋다.
		// readonly True를 넣으면 읽기전용으로 인식하므로 jpa는
		// 영속성 컨텍스트 내의 변경 여부를 감시 및 감지 하지 않는다.
		public List<Book> bookSelect() {
			
			List<Book> bookList = bookRepository.findAll();
			
			System.out.println("--------------------------");
			System.out.println(bookList.get(0).getTitleImageUrl() );
			System.out.println(bookList.get(1).getTitleImageUrl() );
			
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
				System.out.println("등록된 청구기호가 없음");
				
				
				
			}else {
				System.out.println("등록된 청구기호가 잇음");
				
			}
			return sameBookEntity;
		}

		
		
		
		
}

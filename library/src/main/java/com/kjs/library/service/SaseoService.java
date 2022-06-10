package com.kjs.library.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.handler.aop.ex.CustomApiException;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.web.dto.book.BookRegistrationDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.book.ImageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaseoService {

		private final CommonService commonService;
		private final BookRepository bookRepository;
		
		
		// SAVE
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
			
			
			
			
			Book book = bookRegistrationDto.toEntity(imageFileName, principalDetails.getUser());
			
			bookRepository.save(book);
		}
		
		
		// UPDATE
		@Transactional
		public Book 책수정(int bookId, BookUpdateDto bookUpdateDto, String loginedId) {                       
			
			String imageFileName = "";
			
			Book bookEntity = bookRepository.findById(bookId).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 책입니다. 수정 불가");
			});
			
			ImageDto imageDto = new ImageDto();
			
			//이미지가 변경되지 않는다면 null이 표시되고... 기본 이미지가 저장됨
			//null이더라도 기존의 이미지와 같다면 저장하지 않아야 함. 
			
			
			System.out.println("기존 거");
			System.out.println(bookEntity.getTitleImageUrl());
			System.out.println("바뀐 거");
			System.out.println(bookUpdateDto.getTitleImageUrl());
			System.out.println("첨부한 파일 이름");
			System.out.println(bookUpdateDto.getFile().getOriginalFilename());

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
			bookEntity.setKdcCallSign(bookUpdateDto.getKdcCallSign());
			
			//System.out.println("=-==============================");
			//System.out.println(bookEntity);
			
			return bookEntity; //이후 더티 채킹 발생하여 업데이트 됨...
		}
		
		
		//SELECT
		@Transactional(readOnly = true)
		// 서비스 단에는 select만 하더라도 Transactional을 걸어주는 게 좋다.
		// readonly True를 넣으면 읽기전용으로 인식하므로 jpa는
		// 영속성 컨텍스트 내의 변경 여부를 감시 및 감지 하지 않는다.
		public List<Book> bookSelect() {
			
			List<Book> bookList = bookRepository.findAll();
			
			//System.out.println("--------------------------");
			//System.out.println(bookList.get(0).getTitleImageUrl() );
			//System.out.println(bookList.get(1).getTitleImageUrl() );
			
			return bookList;
		}
		
		
		//책 1개의 정보만 가져옴
		@Transactional(readOnly = true)
		public Book bookSelectOne(int id) {
		
			Book bookEntity = bookRepository.findById(id).orElseThrow(()->{
				throw new CustomApiException("해당하는 책이 존재하지 않습니다! 관리자에게 문의하셈");
			});
			
			return bookEntity;
		 }
		
		
		
		
		
		
}

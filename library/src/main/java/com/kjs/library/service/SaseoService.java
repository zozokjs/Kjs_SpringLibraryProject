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
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaseoService {

	
		//타이틀 이미지만 모아두는 곳
		@Value("${file.path.upload_imageTitle}")
		private String uploadFolder;

		private final BookRepository bookRepository;
		
		
		
	
		
		// SAVE
		// 책 타이틀 이미지, 유저 프로필 이미지 등...
		@Transactional
		public void 사진업로드(BookRegistrationDto bookRegistrationDto, PrincipalDetails principalDetails) {
			
			String imageFileName = "";
			
			//첨부한 이미지가 없다면 
			if( bookRegistrationDto.getFile().isEmpty()) {
				imageFileName ="noTitleImage.jpg";
			}
			//첨부한 이미지가 있을 때
			else {
				//유일성이 보장되는 id 생성
				UUID uuid = UUID.randomUUID();
				
				imageFileName = uuid+"_"+bookRegistrationDto.getFile().getOriginalFilename();
				Path imageFilePath =Paths.get(uploadFolder+imageFileName);

				try {
					//imageFilePath 경로에 DTO에 담긴 파일을 가져와서 byte 형태로 저장시킴
					Files.write(imageFilePath, bookRegistrationDto.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("이미지 이름 : " +imageFileName );	
			
			//로그인 검사 필요함 - jsp 페이지 접근할 때, 여기서 save 할 때
			Book book = bookRegistrationDto.toEntity(imageFileName, principalDetails.getUser());
			
			bookRepository.save(book);
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
		
		
		
		
		
		
}

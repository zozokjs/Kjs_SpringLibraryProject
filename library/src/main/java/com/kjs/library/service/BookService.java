package com.kjs.library.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	
	//타이틀 이미지만 모아두는 곳
	@Value("${file.path.upload_imageTitle}")
	private String uploadFolder;

	private final BookRepository bookRepository;
	
	public void 사진업로드(BookRegistrationDto bookRegistrationDto, PrincipalDetails principalDetails) {
		
		//유일성이 보장되는 id 생성
		UUID uuid = UUID.randomUUID();
		
		String imageFileName = uuid+"_"+bookRegistrationDto.getFile().getOriginalFilename();
		
		System.out.println("이미지 이름 : " +imageFileName );
		
		
		
		Path imageFilePath =Paths.get(uploadFolder+imageFileName);

		try {
		
			//imageFilePath 경로에 DTO에 담긴 파일을 가져와서 byte 형태로 저장시킴
			Files.write(imageFilePath, bookRegistrationDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//로그인 검사 필요함 - jsp 페이지 접근할 때, 여기서 save 할 때
		Book book = bookRegistrationDto.toEntity(imageFileName, principalDetails.getUser());
		
		bookRepository.save(book);
	
	}
	
	

}

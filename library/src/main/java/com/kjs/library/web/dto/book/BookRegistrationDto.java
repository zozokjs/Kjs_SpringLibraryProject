package com.kjs.library.web.dto.book;

import org.springframework.web.multipart.MultipartFile;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.user.User;

import lombok.Data;

@Data
public class BookRegistrationDto {

	private MultipartFile file;
	
	//isbn 번호
	private Integer isbn;
	
	//isbnSet 번호
	private Integer isbnSet;
	
	//제목
	private String title;
	
	//저자
	private String writer;
	
	//이미지 위치
	private String titleImageUrl;
	
	//제본형식
	private String bindType;
	
	//페이지
	private Integer page;
	
	//언어
	private String language;
	
	//가격
	private Integer price;
	
	//발행일
	private String publishDate;
	
	//납본여부
	private String deliveryState;
	
	//발행처
	private String publish;
	
	//권 수
	private String volume;
	
	//내용
	private String contents; 
	
	//십진분류표 000, 100, 200...
	private String kdcTable;
	
	//청구기호 김15소, 박211가
	private String kdcCallSignFamily;
	
	
	public Book toEntity(String imageUri) {
		return Book.builder()
				.isbn(isbn)
				.isbnSet(isbnSet)
				.title(title)
				.writer(writer)
				.titleImageUrl(imageUri)
				.bindType(bindType)
				.page(page)
				.language(language)
				.price(price)
				.publishDate(publishDate)
				.deliveryState(deliveryState)
				.publish(publish)
				.volume(volume)
				.contents(contents)
				.kdcTable(kdcTable)
				.kdcCallSignFamily(kdcCallSignFamily)
				.build();
				
				
	}
	
	
}

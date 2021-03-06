package com.kjs.library.web.dto.book;

import java.util.List;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



//도서 청구기호 저장할 때 db에 던지기 위해 사용함
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRegistration_kdcDto {

	private Book book;
	
	private String kdcCallSign;
	
	private boolean kdcCallSignExistState; //청구기호 등록 여부
	
	public Samebook toEntity(Book bookId, String kdcCallSign) {
		return Samebook.builder()
				.book(book)
				.kdcCallSign(kdcCallSign)
				.build();
	}
				
}

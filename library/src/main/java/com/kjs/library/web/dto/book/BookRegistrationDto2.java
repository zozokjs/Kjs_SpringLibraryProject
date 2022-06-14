package com.kjs.library.web.dto.book;

import java.util.List;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.SameBook;
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
public class BookRegistrationDto2 {

	private Book bookId;
	
	private String kdcCallSign;
	
	public SameBook toEntity(Book bookId, String kdcCallSign) {
		return SameBook.builder()
				.bookId(bookId)
				.kdcCallSign(kdcCallSign)
				.build();
	}
				
}

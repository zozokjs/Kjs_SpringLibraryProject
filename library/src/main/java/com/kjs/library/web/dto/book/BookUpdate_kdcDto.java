package com.kjs.library.web.dto.book;

import org.springframework.web.multipart.MultipartFile;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//도서 청구기호 업데이트 할 때 사용함
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdate_kdcDto {
	
	private Book book;
	private String kdcCallSign;

	
	public Samebook toEntity(Book bookId, String kdcCallSign) {
		
		return Samebook.builder()
					.book(bookId)
					.kdcCallSign(kdcCallSign)
					.build();
	}
}

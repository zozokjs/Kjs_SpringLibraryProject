package com.kjs.library.web.dto.resource;

import org.springframework.data.domain.Page;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.user.User;
import com.kjs.library.web.dto.lend.LendDto;

import lombok.Data;



@Data
public class BookDataBySearchDto {

	int startPage;
	int endPage;
	Page<Book> bookDataBySearch;
}

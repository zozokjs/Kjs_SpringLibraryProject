package com.kjs.library.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.service.BookService;
import com.kjs.library.util.Script;
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class BookController {
	
	private final BookService bookService;
	


}

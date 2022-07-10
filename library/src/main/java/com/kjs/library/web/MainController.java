package com.kjs.library.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kjs.library.domain.book.Book;
import com.kjs.library.service.SaseoSelectService;
import com.kjs.library.service.SaseoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final SaseoService saseoService;
	private final SaseoSelectService saseoSelectService;
	
	//메인 화면으로 이동
	@GetMapping("/")
	public String index(Model model) {
		
		//신규 등록된 도서가 표시되어야 함
		//가장 최근에 등록된 3건만
		List<Book> book = saseoSelectService.bookSelectLimit3();
		
		if(book.size() == 0) {
			System.out.println("등록된 책이 없습니다.");
		}else {
			model.addAttribute("book",book);
		}
		
		return "main/index";
	}
	
	//공사중 화면으로이동
	@GetMapping("/공사중")
	public String constructionForm(Model model) {
		
		return "main/공사중";
	}

	
}

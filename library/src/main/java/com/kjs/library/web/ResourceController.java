package com.kjs.library.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kjs.library.domain.book.Book;
import com.kjs.library.service.SaseoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ResourceController {
	
	private final SaseoService saseoService;
	
	//신착 도서 목록 화면으로 이동
	@GetMapping("/resource/newBook")
	public String newBookForm(Model model) {
		
		//신규 등록된 도서가 표시되어야 함
		//일단 모든 목록 표시
		List<Book> book = saseoService.bookSelect();
		model.addAttribute("book",book);
		
		//전체 권수 , 대출된 권수
		
		return "resource/newBook";
	}
	
	
	//책 1 종류에 대한 상세 화면으로 이동
	@GetMapping("/resource/{id}/bookInfor")
	public String bookInformationForm(@PathVariable int id, Model model) {
		
		System.out.println("-=-----------------------");
		System.out.println(id);
		//책 1개의 정보
		Book bookEntity = saseoService.bookSelectOne(id);
		model.addAttribute("book",bookEntity);
		
		return "resource/bookInfor";
	}
	
}

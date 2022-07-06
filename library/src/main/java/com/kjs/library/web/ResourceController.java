package com.kjs.library.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kjs.library.domain.book.Book;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ResourceController {
	
	private final SaseoService saseoService;
	private final CommonService commonService;
	
	//신착 도서 목록 화면으로 이동
	@GetMapping("/resource/newBook")
	public String newBookForm(Model model, @PageableDefault(size=3) Pageable pageable) {

		//신규 등록된 도서가 표시되어야 함
		Page<Book> book = saseoService.bookSelectAllToPage(pageable);
		model.addAttribute("book",book);
		
		int pageCurrent = book.getPageable().getPageNumber();//현재 페이지
		int pageTotal = book.getTotalPages(); //전체 페이지 수
		int pageButtonLength = 10; //한 번에 표시할 페이지 버튼 수
		int pageStart = 0; //페이지 버튼 처음 숫자
		int pageEnd = 0; //페이지 버튼 마지막 숫자
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(pageCurrent, pageTotal, pageButtonLength);
		
		pageStart = pageMap.get("pageStart");
		pageEnd = pageMap.get("pageEnd");
		model.addAttribute("startPage",pageStart);
		model.addAttribute("endPage",pageEnd);
		
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

package com.kjs.library.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.domain.book.Book;
import com.kjs.library.service.BookSelectService;
import com.kjs.library.service.SaseoSelectService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.resource.BookDataBySearchDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ResourceController {
	
	private final SaseoService saseoService;
	private final CommonService commonService;
	private final SaseoSelectService saseoSelectService;
	private final BookSelectService bookSelectService;
	
	
	//신착 도서 목록 화면으로 이동
	@GetMapping("/resource/newBook")
	public String newBookForm(Model model, @PageableDefault(size=2) Pageable pageable) {

		//신규 등록된 도서가 표시되어야 함
		Page<Book> book = saseoSelectService.bookSelectAllToPage(pageable);
		model.addAttribute("book",book);
		
		/*
		int pageCurrent = book.getPageable().getPageNumber();//현재 페이지
		int pageTotal = book.getTotalPages(); //전체 페이지 수
		int pageButtonLength = 10; //한 번에 표시할 페이지 버튼 수
		int pageStart = 0; //페이지 버튼 처음 숫자
		int pageEnd = 0; //페이지 버튼 마지막 숫자
		*/
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(book,10);
		
		model.addAttribute("startPage", pageMap.get("pageStart"));
		model.addAttribute("endPage", pageMap.get("pageEnd"));
		
		return "resource/newBook";
	}
	
	
	//책 1 종류에 대한 상세 화면으로 이동
	@GetMapping("/resource/{id}/bookInfor")
	public String bookInformationForm(@PathVariable int id, Model model) {
		
		//System.out.println("-=-----------------------");
		//System.out.println(id);
		//책 1개의 정보
		Book bookEntity = saseoSelectService.bookSelectOne(id);
		model.addAttribute("book",bookEntity);
		
		return "resource/bookInfor";
	}
	
	
	//통합 검색 화면으로 이동
	@GetMapping("/resource/bookSearch")
	public String bookSearchForm() {
	
		return "resource/bookSearch";
	}
		

	


}

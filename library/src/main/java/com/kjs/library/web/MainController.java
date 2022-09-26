package com.kjs.library.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.service.CommunityService;
import com.kjs.library.service.SaseoSelectService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.common.CommonCookieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {
	
	private final SaseoSelectService saseoSelectService;
	private final CommunityService communityService;
	private final CommonCookieService commonCookieService;
	
	
	/**
	 * index페이지로 이동하되, frontVisited 쿠키가 있다면 frontPage로 이동시킴
	 * @param model, Model, 공지사항 및 신착 도서가 저장됨
	 * @param request, HttpServletRequest, 쿠키를 가져올 요청 객체
	 * @param response, HttpServletResponse, 쿠키를 세팅할 응답 객체
	 * @return 
	 * @throws  
	 * */
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//commonCookieService.deleteOldCookie(response, "frontVisited");
		
		String oldCookieValue = commonCookieService.findCookieValue(request, "frontVisited");
		
		if(oldCookieValue == null || oldCookieValue.equals("") || oldCookieValue.length() == 0 || oldCookieValue.isEmpty()) {
			commonCookieService.setNewCookie(response, "frontVisited", 86400);
			return "main/frontPage";
		}else {
			//신규 등록된 도서가 표시되어야 함
			//가장 최근에 등록된 3건만
			List<Book> book = saseoSelectService.bookSelectLimit3();
			
			//공지사항 최근 10건을 표시함
			List<BoardNotice> boardNotice = communityService.공지사항목록10개();
			
			if(book.size() == 0) {
				System.out.println("등록된 책이 없습니다.");
			}else {
				/**
				 * 아래에서 model에 add하는 book과 boardnotice 중
				 * 하나라도 값이 없다면 JSP에 출력되지 않음
				 * (둘 다 값이 있어야 출력된다.)			 
				 * */
				model.addAttribute("book",book);
				model.addAttribute("boardNotice",boardNotice);
			}
			//log.info("index로 이동합니다.");
			return "main/index";
		} //end of First if
	}//end of method
	
	
	//공사중 화면으로이동
	@GetMapping("/scriptAlertPage")
	public String scriptAlertPageForm(Model model) {
		
		return "main/scriptAlertPage";
	}

	
	//공사중 화면으로이동
	@GetMapping("/공사중")
	public String constructionForm(Model model) {
		
		return "main/공사중";
	}

	//공사중 화면으로이동
	@GetMapping("/mailSample")
	public String mailSample(Model model) {
		
		return "main/mailSample";
	}

	//홈페이지 제작 정보 화면으로 이동
	@GetMapping("/tnlProductionInfor")
	public String tnlibraryProductInforForm() {
		
		return "main/tnlProductionInfor";
	}
	
	
	
	@GetMapping("/frontPage")
	public String frontPageForm() {
		//log.info("frontPage로 이동합니다.");

		return "main/frontPage";
	}
	
}

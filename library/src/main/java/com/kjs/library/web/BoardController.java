package com.kjs.library.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kjs.library.service.AuthService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class BoardController {
private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	//게시판 샘플
	@GetMapping("/board/boardSample")
	public String boardSample() {
		return "board/boardSample";
	}
	
	//게시판 샘플2
	@GetMapping("/board/boardSample2")
	public String boardSample2() {
		return "board/boardSample2";
	}
}

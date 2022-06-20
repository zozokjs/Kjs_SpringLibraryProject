package com.kjs.library.testSample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestSampleController {
	
	private final TestSampleService testService;
	
	@GetMapping("/save")
	public String 저장하기(@RequestBody TestSample test) {
		
		testService.저장하기(test);
		
		return "/";
	}
}

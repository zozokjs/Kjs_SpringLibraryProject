package com.kjs.library.testSample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class XssRequestController {

	@PostMapping("/xss")
	public String xss(@RequestBody XssRequestDto xDto) {
		
		log.info("requestDTO",xDto);
		
		return xDto.getContent();
	}
}

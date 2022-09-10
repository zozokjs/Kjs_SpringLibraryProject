package com.kjs.library.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.domain.common.VisitorCount;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MainApiController {
	
	private final CommonService commonService;
	
	/**
	 * footer에 방문수 표시
	 * */
	@PutMapping("/api/main/footer/getVisitorCount")
	public ResponseEntity<?> getVisitorCount() {
		
		VisitorCount visitorCount  = new VisitorCount();
		
		visitorCount = commonService.접속자수();
		
		if(visitorCount == null) { 
			return new ResponseEntity<>(new CMRespDto<>(0,"전송성공","방문자 수 획득 실패"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto<>(1,"전송성공",visitorCount), HttpStatus.OK);
		}
		
	}
}

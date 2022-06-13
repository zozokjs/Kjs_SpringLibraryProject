package com.kjs.library.handler.aop;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kjs.library.handler.aop.ex.CustomApiException;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.util.Script;
import com.kjs.library.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public String validationException(CustomValidationException e) {
		
	
		if(e.getErrorMap() == null){
			System.out.println("리턴1");
			return Script.back(e.getMessage()); //자바스크립트 리턴
		}else {
			System.out.println("리턴2");
			return Script.back(e.getErrorMap().toString()); //자바스크립트 리턴
			//return e.getErrorMap().toString();
		}
		
	}

	/*
	//
	@ExceptionHandler(value = Exception.class)
	public String handler404() {
		
		System.out.println("404 catch");

		//System.out.println(ex);
		
		return "main/404custom";
	}*/
	
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST); //데이터 리턴
	}

	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST); //데이터 리턴
	}
	
	
}

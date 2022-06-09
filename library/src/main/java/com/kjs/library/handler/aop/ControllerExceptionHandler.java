package com.kjs.library.handler.aop;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
	
		if(e.getErrorMap() == null){
			return Script.back(e.getMessage()); //자바스크립트 리턴
		}else {
			return Script.back(e.getErrorMap().toString()); //자바스크립트 리턴
		}
		
	}
	

	//동작 안 함... 혹시 작동할지도 모르니 남겨둠..
	@ExceptionHandler(value = Exception.class)
	public String handler404() {
		
		System.out.println("404 catch");

		//System.out.println(ex);
		
		return "main/404custom";
	}
	

	
	
	
}

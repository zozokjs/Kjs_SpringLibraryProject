package com.kjs.library.handler.aop.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private Map<String, String>errorMap;
	
	public CustomValidationException(String message, Map<String, String> errorMap) {
		super(message);
		//부모한테 던짐. 부모(Throwable 클래스의 getMessage())가 message를 return하고 있으므로 Getter()가 별도로 필요 없음. 
		//
		
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
	
	
}

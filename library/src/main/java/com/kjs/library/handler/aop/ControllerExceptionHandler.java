package com.kjs.library.handler.aop;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
		//System.out.println("출력합니다. "+e);
		if(e.getErrorMap() == null){
			System.out.println("리턴1");
			return Script.back(e.getMessage()); //자바스크립트 리턴
		}else {
			System.out.println("리턴2");
			return Script.back(e.getErrorMap().toString()); //자바스크립트 리턴
		}
	}
	
	//InvalidDataAccessResourceUsageException
	@ExceptionHandler(NullPointerException.class)
	public String nullPointExp(NullPointerException exc) {
		System.out.println("NullPointer 에러가 발생하였습니다.");
		System.out.println(exc);
		printStackTraceCustom(exc);
		return Script.back(exc.getMessage()); //자바스크립트 리턴
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public String nullPointExp(DataIntegrityViolationException exc) {
		System.out.println("DataIntegrityViolationException 데이터 무결성 위반 예외가 발생하였습니다.");
		System.out.println(exc);
		printStackTraceCustom(exc);
		return Script.back(exc.getMessage()); //자바스크립트 리턴
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public String IllegalStateExceptionExp(IllegalStateException exc) {
		System.out.println("IllegalStateException 불법 상태 예외가 발생하였습니다(메소드 처리에 적합한 상태가 아닙니다).");
		System.out.println(exc);
		printStackTraceCustom(exc);
		return Script.back(exc.getMessage()); //자바스크립트 리턴
	}
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public String InvalidDataAccessResourceUsageExp(InvalidDataAccessResourceUsageException exc) {
		System.out.println("InvalidDataAccessResourceUsageException 잘못된 데이터가 Access되어 예외처리 되었습니다.");
		System.out.println(exc);
		printStackTraceCustom(exc);
		return Script.back(exc.getMessage()); //자바스크립트 리턴
	}
	
	//JpaSystemException
	
	@ExceptionHandler(JpaSystemException.class)
	public String JpaSystemExp(JpaSystemException exc) {
		System.out.println("JpaSystemException. JPA System 로딩 에러가 발생했습니다.");
		System.out.println(exc);
		printStackTraceCustom(exc);
		return Script.back(exc.getMessage()); //자바스크립트 리턴
	}
	
	public void printStackTraceCustom(Exception exc) {

		StackTraceElement[] stackTraceArrays = exc.getStackTrace();
		for (int i = 0; i < stackTraceArrays.length; i++) {
			System.err.println("          "+stackTraceArrays[i]); //에러 내용 출력됨
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

package com.kjs.library.handler.aop;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		//return e.getErrorMap();
		return Script.back(e.getErrorMap().toString());
		
	}
}

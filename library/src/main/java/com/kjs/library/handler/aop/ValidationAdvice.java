package com.kjs.library.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.web.dto.book.BookRegistrationDto;

@Component
@Aspect // aop처리 되는 핸들러 명시
public class ValidationAdvice {

	@Around("execution(* com.kjs.library.web.*Controller.*(..))")
	public Object advise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// proceedingJoinPoint는 메소드의 매게변수와 메소드 내부에 접근할 수 있다.
		// return proceedingJoinPoint.proceed(); //그 함수로 다시 돌아가라는 뜻이다.
		
		Object[] args = proceedingJoinPoint.getArgs();
		
		//매개변수를 뽑아낸다.
		for(Object arg : args) {
			
			//매개변수가 BindingResult 타입일 때 걸러내서
			if(arg instanceof BindingResult) {
				//System.out.println("유효성 검사 결과가 표시됩니다.");
				
				//걸러낸 것을 BindingResult 타입으로 캐스팅하고
				BindingResult bindingResult = (BindingResult) arg;
				
				//캐스팅 된 것에 에러가 있을 때
				if(bindingResult.hasErrors()) {
					
					//Map 객체를 만들어서
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						
						System.out.println("------------------------------");
						System.out.println(error.getDefaultMessage());
						System.out.println("------------------------------");
							
					}
					//throw new RuntimeException("실패패패팻");
					throw new CustomValidationException("실패",errorMap);
					/**
					 * '실패'랑 errorMap를 같이 리턴시켜야 함.
					 * */
					
				} //end of if
				
			}
			
			/*
			else if(arg instanceof BookRegistrationDto) {
				//로그인 검사해서 로그인 안 되어 있으면 에러 리턴해야 하는데
				//System.out.println("advice-------------------");
				
				//System.out.println("check > "+SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
				
				//로그인 안 했음. false
				//System.out.println("check2 > "+SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty());
				
				
			}*/
		}
	
		return proceedingJoinPoint.proceed(); 
	}

}

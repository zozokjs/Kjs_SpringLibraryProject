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
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.web.dto.book.BookRegistrationDto;

@Component
@Aspect // aop처리 되는 핸들러 명시
public class ValidationAdvice {

	//Controller 검사... return CustomValidationException
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
			
		}
	
		return proceedingJoinPoint.proceed(); 
	}
	
	// Api Controller 검사... return CustomValidationApiException
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// proceedingJoinPoint는 위에서 명시한 범위 내의 매개변수 등에 접근할 수 있음
		// return proceedingJoinPoint.proceed();는 모두 접근한 다음에 그 함수로 돌아가라는 명령이다.
		/**
		 * 예를 들어, profile() 함수보다 먼저 이 메서드가 실행 되며 return proceedingJoinPoint.proceed();하는
		 * 순간 profile() 함수가 실행되기 시작함.
		 * 
		 * 
		 */
		System.out.println("web api 컨트롤러========================================");

		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			// System.out.println(arg);

			// org.springframework.validation.BeanPropertyBindingResult:
			/**
			 * 위와 같은 타입이면 아래처럼 낚아 챌 수 있다.
			 */
			if (arg instanceof BindingResult) {
				System.out.println("유효성을 검사하는 함수입니다.");

				BindingResult bindingResult = (BindingResult) arg;

				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for (FieldError error : bindingResult.getFieldErrors()) {

						errorMap.put(error.getField(), error.getDefaultMessage());
						/*
							 System.out.println("======================");				 
							 System.out.println(error.getDefaultMessage());  
							 System.out.println("======================");		
						*/
					}
					throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
				}

			}

		}

		return proceedingJoinPoint.proceed();

	}

}

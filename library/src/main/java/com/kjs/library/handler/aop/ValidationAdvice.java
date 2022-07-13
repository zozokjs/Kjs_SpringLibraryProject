package com.kjs.library.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.handler.aop.ex.CustomValidationException;
@Component
@Aspect // aop처리 되는 핸들러 명시
public class ValidationAdvice {
	
	
	@Pointcut("execution(* com.kjs.library.web.*Controller.*(..))")
    private void allController() {
	}

	
	@Pointcut("execution(* com.kjs.library.web.api.*Controller.*(..))")
    private void allApiController() {
	}
	
	
	@AfterThrowing(pointcut = "allController(), allApiController() ", throwing = "exp")
	public void afterThrowingTragetMethod(JoinPoint jp, Exception exp) throws Exception{
		String methodName = jp.getSignature().getName(); //메소드 이름을 얻어옴
		System.out.println(methodName + "() 실행 중 예외가 발생하였습니다.");
		System.out.println(jp.getSignature());
		System.err.println(exp);
	}
	
	
	
	//Controller 검사... return CustomValidationException
	@Around("execution(* com.kjs.library.web.*Controller.*(..))")
	public Object advise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// proceedingJoinPoint는 메소드의 매게변수와 메소드 내부에 접근할 수 있다.
		// return proceedingJoinPoint.proceed(); //그 함수로 다시 돌아가라는 뜻이다.
		/**
		 * 예를 들어 Controller의 profile() 함수를 호출 했다면,
		 * 1. 현재 클래스의 advice() 실행... 
		 * 2. profile()로 전달된 매개변수 검사..
		 * 3. proceedingJoinPoint.proceed()를 읽음
		 * 4. profile() 함수 실행 시작* */
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
					
					//bindingResult의 FieldError만
					for(FieldError error : bindingResult.getFieldErrors()) {
						//Map 객체에 집어 넣는다.
						errorMap.put(error.getField(), error.getDefaultMessage());
						
						System.out.println("------------------------------");
						//System.out.println(error.getField());
						//System.out.println(error.getDefaultMessage());
						System.out.println("------------------------------");
							
					} // end of for
					throw new CustomValidationException("Validation 검사 실패",errorMap);
					/**
					 * '실패'랑 errorMap를 같이 리턴시켜야 함.
					 * */
				} //end of if
			} //end of if
			
		}//end of for
	
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

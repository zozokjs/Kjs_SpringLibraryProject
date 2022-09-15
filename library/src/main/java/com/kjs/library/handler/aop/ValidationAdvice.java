package com.kjs.library.handler.aop;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kjs.library.domain.common.VisitorInfor;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;

import lombok.extern.slf4j.Slf4j;

/* AOP 참고
 * https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte:fdl:aop:aspectj
 * */

@Slf4j
@Component
@Aspect // aop처리 되는 핸들러 명시
public class ValidationAdvice {
	
 	
	private CommonService commonService;
	
	public ValidationAdvice(CommonService cs) {
		this.commonService  = cs;
	}
	
	@Pointcut("execution(* com.kjs.library.web.*Controller.*(..))")
    private void allController() {
	}

	
	@Pointcut("execution(* com.kjs.library.web.api.*Controller.*(..))")
    private void allApiController() {
	}

	/**
	 * 메소드 수행 이후에 무조건 수행됨 (메소드 정상 종료, 예외 발생 경우로 인한 종료 포함)
	 * */
	@After("allController(), allApiController()")
	public void visitorChecker(JoinPoint joinpoint) throws Throwable {
		//Request
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		//Response
		HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();

		//1. request 객체에서 쿠키 key가 visitorCookie인 값을 가져옴
		String oldCookieValue = findCookieValue(request, "visitorCookie");
		//log.info("find 결과  {}",oldCookieValue);
		
		//아이피 얻어옴
		String ip = commonService.getClientIP(request);
		
		
		//2. 쿠키 없으면 쿠키 세팅, 방문자 증가
		if(oldCookieValue == null || oldCookieValue.equals("") ) {
			//log.info("접속 기록 및 쿠키 없음. 쿠키 세팅. 방문자 증가");
			setNewCookie(response);
			commonService.방문자증가();
			
			//방문자 집계 지점 확인을 위해 ip저장
			commonService.접속기록저장(ip);
		}else {
			//3. 쿠키 있으면 쿠키에서 생성 날짜 가져옴
			int checkPosition= oldCookieValue.indexOf("///");
			String createDate = oldCookieValue.substring(0, checkPosition);
			//log.info("쿠키 생성 시각 {}", createDate);
			
			//4. 생성 날짜가 오늘이 아닌 경우 기존 쿠키 만료하고 새 쿠키 발급
			if(DateCommonService.오늘날짜다(createDate) == false){
				//log.info("오늘 날짜 아니라서 방문수 증가");

				deleteOldCookie(response);
				
				setNewCookie(response);
				commonService.방문자증가();
				
				//방문자 집계 지점 확인을 위해 ip저장
				commonService.접속기록저장(ip);
			}else {
				//5. 생성 날짜가 오늘이면 아무것도 안 함
				//log.info("오늘 날짜라서 아무것도 안 함");
				
			}
		}//end of First if
	}
	
	
	/**
	 * 메소드 수행 중 예외사항을 반환하고 종료하는 경우 수행됨.
	 * */
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
		//System.out.println("advise가 실행됩니다. ");
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
	
	
	/**
	 * 쿠키 세팅
	 * */
	public void setNewCookie(HttpServletResponse response) {
		//log.info("새 쿠키가 추가 됨");
	
		//1. 날짜 포맷 세팅
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		//2. 오늘 날짜를 가져옴
		Date dateNowDate = new Date();
		//3. dateNowDate을 주어진 포맷으로 변경(Date -> String)
		String dateNowFormatted= simpleDateFormat.format(dateNowDate);
		//4. 새로운 uuid 생성
		UUID uuid = UUID.randomUUID();
		//5. uuid에 dateNowFormatted 더함
		String cookieValue = dateNowFormatted+"///"+uuid.toString();
		// 예)20220901///asdqwdasdkljqiwofh
		//log.info(" {}",cookieValue);
		//6 쿠키 세팅
		Cookie newCookie = new Cookie("visitorCookie",cookieValue);//새 쿠키 생성
		newCookie.setMaxAge(60*60*25); //쿠키 유지 시간 설정 : 25시간
		response.addCookie(newCookie); //쿠키 세팅
	}
	
	
	/**
	 * cookie 아이디가 cookieKey인 것의 value를 가져옴
	 * */
	public String findCookieValue(HttpServletRequest request, String cookieKey) {
		
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String key = cookie.getName();
				
				//visitorCookie 쿠키가 있을 때
				if(key.equals(cookieKey)) {
					//log.info("{}에 해당하는 쿠키 Key가 있습니다. ", cookieKey);
					cookieValue =  cookie.getValue();
				}else {
					//log.info("{}에 해당하는 쿠키 Key가 없습니다. ", cookieKey);
					//cookieValue = "";
				}
			}
		}else{
			//log.info("쿠키가 없습니다");
		}// end of if
		
		return cookieValue;
		
	} // end of findCookieValue()

	
	public void deleteOldCookie(HttpServletResponse response) {
		
		//log.info("기존 쿠키가 제거 됨");
		
		Cookie deleteCookie = new Cookie("visitorCookie",null); //Null 값의 쿠키 생성
		deleteCookie.setMaxAge(0); //쿠키 유지 시간 설정 
		response.addCookie(deleteCookie ); //쿠키 세팅
	}
	
}

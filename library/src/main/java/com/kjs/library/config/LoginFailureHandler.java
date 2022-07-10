package com.kjs.library.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;

/**
 * final 붙은 필드는 반드시 초기화되어야 한다.
 * 객체는 생성될 때 값이 들어가야 함(초기화 되어야 함). 그래야만 메모리에 올라가기 때문임. 
 * 기본 생성자에 매개변수가 들어간다면 기본 생성자는 삭제됨. 
 * */
//@RequiredArgsConstructor 
public class LoginFailureHandler implements AuthenticationFailureHandler{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String DEFAULT_FAILURE_URL= "/auth/signinFail";
	private int 로그인실패횟수;
	String errorMessage = null;
	
	//private final UserRepository userRepository;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserRepository userRepository;
	//public LoginFailureHandler(UserRepository userRepository) {
	//	this.userRepository = userRepository;
		//this.authService = authService;
	//}
	
	/*
	public LoginFailureHandler() {}
	private UserRepository userRepository;
	public LoginFailureHandler(Logger log, int 로그인실패횟수, String errorMessage, UserRepository userRepository) {
		this.log = log;
		this.로그인실패횟수 = 로그인실패횟수;
		this.errorMessage = errorMessage;
		this.userRepository = userRepository;
	}
	 */
	/**
	 * HttpServletRequest -> Front에서 넘어온 request 값을 갖고 있음
	 * HttpServletResponse -> 출력을 정의함
	 * AuthenticationException -> 로그인 실패 정보를 갖고 있음
	 * */
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		/*
		 * request.getParameter(username));
		 * request.getParameter(password));
		 * 
		 * */
		userRepository.count();
		
		User userEntity = userRepository.findByUsername(request.getParameter("username"));
		
		
		if(exception instanceof DisabledException) {
			
			errorMessage = "계정이 비활성화 되어 있습니다. 관리자에게 문의하세요. ";
		}else if(exception instanceof UsernameNotFoundException) {
			errorMessage = "아이디가 존재하지 않습니다.";
		
		}
		else if(exception instanceof BadCredentialsException){

			errorMessage = "아이디 및 비밀번호가 일치하지 않습니다.";

			int failCount = authService.로그인실패횟수조회(request.getParameter("username"));
			
			if(failCount < 5) {
				authService.로그인실패횟수증가(request.getParameter("username"));
				errorMessage += "로그인 실패 횟수가 "+failCount+"입니다. 5회 이상 실패 시 계정이 잠깁니다.";
				
			}else {				
				authService.계정잠금(request.getParameter("username"));
				errorMessage += "로그인 실패 횟수가 5회 입니다. 계정이 잠겼습니다. 관리자에게 문의하세요 ";
				
			}
			
			
			
		}else if (exception instanceof InternalAuthenticationServiceException ){
			errorMessage = "내부적으로 발생한 시스템 문제로 인해 해당 요청을 처리할 수 없습니다. 관리자에게 문의하세요";
		}else if(exception instanceof  AuthenticationException) {
			errorMessage = "AuthenticationException 에러 발생. 관리자에게 문의하세요";
			System.err.println(exception);
		}else if(exception instanceof DisabledException){
			
			errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요";
			System.err.println(exception);
		}else {
			errorMessage = "알 수 없는 이유로 로그인에 실패했습니다. 관리자에게 문의하세요";
			System.err.println(exception);
			
		}
		
		request.setAttribute("errorMsg", errorMessage);
		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
	}
	
	
	@Transactional
	private User 로그인실패횟수증가(String username) {
		//초기화
		
		//db에서 가져옴
		//User userEntity = userRepository.findById(loginId).orElseThrow();
		User userEntity = userRepository.findByUsername(username);
		//로그인실패횟수 = userEntity.getLoginFailCount();
	//	log.info("초기값 : "+로그인실패횟수);

	//	로그인실패횟수 = 로그인실패횟수 + 1;

		userEntity.getId();
		
		log.info(userEntity.getUsername());
		log.info(String.valueOf(userEntity.getId()));
		
		
		//log.info("변경된 로그인 실패 횟수 : "+로그인실패횟수);
		log.info("업데이트 되나요?");
		userEntity.setLoginFailCount(3);
		
		return userEntity;
		
	}
	
	
	/*
	@Transactional
	private boolean 계정잠금처리(int loginId) {
		
		//실패 횟수 초기화
		로그인실패횟수 = 0;
		boolean 계정잠김 = false;
		
		//계정 잠김 여부 가져옴
		User userEntity = userRepository.findById(loginId).orElseThrow();
		계정잠김 = userEntity.isEnabled();
		
		로그인실패횟수 =userEntity.getLoginFailCount();

		log.info("기존 계정잠김상태 : "+계정잠김);
		log.info("시도된 아이디 : "+loginId);
		log.info("기존 로그인 실패 횟수 : "+로그인실패횟수);
		
		
		//계정 안 잠겼음
		if(계정잠김 == false) {
			
			//로그인 실패 횟수가 5회 이하인 경우
			if(로그인실패횟수 < 5) {
				//1회 증가
				로그인실패횟수 = 로그인실패횟수 + 1;

				log.info("변경된 로그인 실패 횟수 : "+로그인실패횟수);
				log.info("업데이트 되나요?");
				userEntity.setLoginFailCount(로그인실패횟수);
			}else {
				//로그인 실패 횟수가 5회 이상인 경우
				//잠금 처리
				계정잠김 = true;
				log.info("계정이 잠금 처리 됩니다.: ");
				userEntity.setEnabled(계정잠김);
			}
		}
		
		return 계정잠김;
	}*/
	
	
	
	
	
	
	
	
	
	
}

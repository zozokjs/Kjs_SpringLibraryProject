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
import com.kjs.library.util.Custom_UserLoginFailCountOverException;
import com.kjs.library.util.Custom_UserNotApprovalException;

/**
 * final 붙은 필드는 반드시 초기화되어야 한다.
 * 객체는 생성될 때 값이 들어가야 함(초기화 되어야 함). 그래야만 메모리에 올라가기 때문임. 
 * 기본 생성자에 매개변수가 들어간다면 기본 생성자는 삭제됨. 
 * */
//@RequiredArgsConstructor 
public class LoginFailureHandler implements AuthenticationFailureHandler{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String DEFAULT_FAILURE_URL= "/auth/signinFail";
	String errorMessage = null;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * HttpServletRequest -> Front에서 넘어온 request 값을 갖고 있음
	 * HttpServletResponse -> 출력을 정의함
	 * AuthenticationException -> 로그인 실패 정보를 갖고 있음
	 * */
	/*
	 * signinOtherPage.jsp 상단 참조. request에 errorMessage를 담았으니 JSP에서 받아서 스크립트로 출력해야 함
	 * */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if(exception instanceof Custom_UserNotApprovalException) {
			
			errorMessage = "아직 가입이 승인되지 않았습니다. 관리자에게 문의하세요. ";
		}
		else if(exception instanceof DisabledException) {
			
			errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요. ";
		}
		else if(exception instanceof UsernameNotFoundException) {
			
			errorMessage = "아이디가 존재하지 않습니다.";
		}
		else if(exception instanceof Custom_UserLoginFailCountOverException) {

			errorMessage = "비밀번호가 5번 이상 틀렸습니다. 관리자에게 문의하세요";
		}
		else if(exception instanceof BadCredentialsException){

			errorMessage = "아이디 및 비밀번호가 일치하지 않습니다.";

			int failCount = authService.로그인실패횟수조회(request.getParameter("username"));
			
			
			if(failCount < 5) {
				failCount+=1;
				authService.로그인실패횟수증가(request.getParameter("username"));
				errorMessage += "로그인 실패 횟수가 "+failCount+"회 입니다. 5회 이상 실패 시 계정이 잠깁니다.";
				
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
	
	
	
	
	
	
	
	
	
	
	
}

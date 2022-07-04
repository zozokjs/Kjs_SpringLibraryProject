package com.kjs.library.config;

import java.io.IOException;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler{

	private final String DEFAULT_FAILURE_URL= "/auth/signinFail";
	
	String errorMessage = null;
	
	/**
	 * HttpServletRequest -> Front에서 넘어온 request 값을 갖고 있음
	 * HttpServletResponse -> 출력을 정의함
	 * AuthenticationException -> 로그인 실패 정보를 갖고 있음
	 * */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if(exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
			errorMessage = "아이디나 비밀번호가 일치하지 않습니다";
		}else if(exception instanceof DisabledException) {
			errorMessage = "계정이 비활성화 되어 있습니다. 관리자에게 문의하세요. ";
		}else if (exception instanceof InternalAuthenticationServiceException ){
			errorMessage = "알 수 없는 이유로 로그인에 실패했습니다. 관리자에게 문의하세요";
		}else {
			errorMessage = "알 수 없는 이유로 로그인에 실패했습니다. 사서에게 문의하세요";
			System.err.println(exception);
		}
		
		request.setAttribute("errorMsg", errorMessage);
		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
	}
}

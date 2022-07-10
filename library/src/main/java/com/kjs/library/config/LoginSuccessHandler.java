package com.kjs.library.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.kjs.library.service.AuthService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private String username;
	private String defaulUrl;
	
	
	private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();
	
	@Autowired
	AuthService authService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("로그인 성공 했습니다.");
		
		authService.로그인실패횟수초기화(request.getParameter("username"));
		
		redirectStragtegy.sendRedirect(request, response, "/");
		
		//로그인실패횟수 초기화
		//메인화면으로 이동시킴
	}

}

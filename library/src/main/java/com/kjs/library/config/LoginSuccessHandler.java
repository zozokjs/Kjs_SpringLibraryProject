package com.kjs.library.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.ForwardHeadersStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.kjs.library.service.AuthService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();
	private RequestCache  rc = new HttpSessionRequestCache();

	
	
	@Autowired
	AuthService authService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//log.info("로그인 성공 했습니다.");
		
		authService.로그인실패횟수초기화(request.getParameter("username"));
	
		
		/**
		 * https://to-dy.tistory.com/94
		 * 1. A 페이지에서 B페이지로 이동함. B페이지에서는 로그인을 요구함.  
		 * 2. 비로그인 상태에서 접근 했으므로 로그인 페이지 연결됨.
		 * 3. 로그인 뒤, A페이지가 아닌, B페이지로 이동하게 끔하는 로직.
		 * */
		SavedRequest sr = rc.getRequest(request, response);
		
		if(sr != null) {
			String targetUrl = sr.getRedirectUrl();
			System.out.println("target URL "+targetUrl);
			redirectStragtegy.sendRedirect(request, response, targetUrl);;
		}else {
			redirectStragtegy.sendRedirect(request, response, "/");;
		}
		
		
		//로그인실패횟수 초기화
		//메인화면으로 이동시킴
	}
		
	

}

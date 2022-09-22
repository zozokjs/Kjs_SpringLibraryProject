package com.kjs.library.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kjs.library.config.auth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration // ioc에 등록
@Slf4j
//@RequiredArgsConstructor 
public class SpringConfig extends WebSecurityConfigurerAdapter {

	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encode(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		//http.csrf().disable(); 
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		/**
		 * CSRF 토큰은 사용자가 받는 데이터가 내 서버에서 보낸 데이터가 맞는지 검증하는 방법 중 하나
		withHttpOnlyFalse() 
		-> setCookieHttpOnly(boolean)가 false로 설정된 인스턴스를 편리하게 생성하기 위한 팩토리 메소드.
			 반환값: setCookieHttpOnly(boolean)가 false로 설정된 CookieCsrfTokenRepository의 인스턴스
		 * */
		http.authorizeRequests()
			.antMatchers("/user/**","/community/boardFreeRegistration").authenticated() //이 주소는 인증 필요
			.antMatchers("/saseo/**").hasAnyRole("SASEO", "ADMIN") //SASEO 및 ADMIN 권한이 있는 경우 접근 가능
			.anyRequest().permitAll() //그 외의 요청은 허용
			.and()
				.formLogin()
				.loginPage("/auth/signinOtherPage") // //antMatcher()에 적힌 주소로 접근한다면 이 주소로 향해라  GET요청임
				.loginProcessingUrl("/auth/signin") //이 주소로 POST 방식 요청하면 시큐리티가 로그인을 진행한다.
				.defaultSuccessUrl("/")  //loginPage()에 적힌 주소에서 인증 되었다면 그 다음 이 주소로 향해라.
				.successHandler(successHandler())
				.failureHandler(failureHandler())
			
			.and()
				.logout()
				.logoutSuccessUrl("/") //로그아웃 성공시 목적지 주소
				.addLogoutHandler(new LogoutHandler() {
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
						
						System.out.println("dddddddddddd");
						
						HttpSession session = request.getSession();
						String accessToken = (String) session.getAttribute("kakaoAccessToken");
						
						if(accessToken == null || accessToken.equals(null) || accessToken.equals("")) {
							
						}else {
							log.info("로그아웃 시 엑세스 토큰 "+accessToken);
							
							RestTemplate rt = new RestTemplate();
							HttpHeaders headers = new HttpHeaders();
							headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
							headers.add("Authorization","Bearer "+accessToken);
							//http 바디 생성
							MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
							//헤더와 바디를 하나에 담기
							HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(params, headers);
							//http 요청하기
							ResponseEntity<String> response2 = rt.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST,kakaoLogoutRequest, String.class);
							System.out.println(response2.getBody());
						}
					}
				})
				.and()
				.oauth2Login() 
				.defaultSuccessUrl("/")
				//.loginPage("/loginForm")
				.userInfoEndpoint()
				.userService(principalOauth2UserService);
			
			
			

			
			
		
		/*
		 * @Override
			protected void configure(HttpSecurity http) throws Exception {
    		protected void configure(HttpSecurity http) throws Exception {
    			http.logout() // 로그아웃 처리
		        .logoutUrl("/logout") // 로그아웃 처리 URL
		        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동 URL
		        .deleteCookies(" JESSIONID", " remember-me ") // 로그아웃 후 쿠키 삭제
		        .addLogoutHandler(logoutHandler()) // 로그아웃 후 핸들러
		        .logoutSuccessHandler(logoutSuccessHandler()); // 로그아웃 성공 후 핸들러
}*/	
		
	}
	
	
	
	/**
	 * Controller나 Service 등에서 AuthenticationManager 객체를 @Autowired 없이 주입하기 위해 작성됨
	 * */
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailureHandler();
	}

	
	
}

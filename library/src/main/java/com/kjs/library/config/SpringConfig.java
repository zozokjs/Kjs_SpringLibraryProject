package com.kjs.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@EnableWebSecurity
@Configuration // ioc에 등록
//@RequiredArgsConstructor 
public class SpringConfig extends WebSecurityConfigurerAdapter {

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
			.logoutSuccessUrl("/");
			
		
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
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailureHandler();
	}
	
	
	
}

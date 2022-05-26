package com.kjs.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration // ioc에 등록
public class SpringConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encode(){
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.csrf().disable(); //일단 CSRF 토큰 비활성화.
		/**
		 * CSRF 토큰은 사용자가 받는 데이터가 내 서버에서 보낸 데이터가 맞는지 검증하는 방법 중 하나
	
	
		 * */
		
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() //이 주소는 인증 필요
			.anyRequest().permitAll() //그 외의 요청은 허용
			.and()
			.formLogin()
			.loginPage("/auth/signin") // //antMatcher()에 적힌 주소로 접근한다면 이 주소로 향해라  GET요청임
			.loginProcessingUrl("/auth/signin") //이 주소로 POST 방식 요청하면 시큐리티가 로그인을 낚아채서 진행해줌.
			.defaultSuccessUrl("/");  //loginPage()에 적힌 주소에서 인증 되었다면 그 다음 이 주소로 향해라.
	}
	
	
	
}

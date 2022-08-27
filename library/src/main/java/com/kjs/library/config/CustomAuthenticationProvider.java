package com.kjs.library.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.config.auth.PrincipalDetailsService;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.util.Custom_UserLoginFailCountOverException;
import com.kjs.library.util.Custom_UserNotApprovalException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component//AuthenticationProvider를 imple 받는 놈이 이것밖에 없어서 빈으로 올리기만해도 되는 것 같음.
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final PrincipalDetailsService principalDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	
	/**
	 * https://velog.io/@ewan/Spring-Security-Custom-Authentication-Provider
	 * 
	 * Front에서 아이디, 비번 입력하면 이쪽으로 전달되서 비번 일치여부를 확인한다.
	 * 
	 * 이 메소드의 매개변수 authentication은 Authentication Manager에서 넘겨준 객체임.
	 * 이 안에는 form 등에서 가져온(사용자가 직접 입력한) 아이디나 비번이 들어가 잇다.
	 * DB에서 값을 들고 오려면 UserDetailsService(여기서는 PrincipalDetailsService)를 이용하면 된다.
	 * UserDetailsService는 UserDetails(여기서는 PrincipalDetails)를 리턴한다.
	 * 이 리턴된 값을 갖고 이 메소드의 매개변수인 authentication과 비교하여 성공하면 
	 * 인증 완료된 UsernamePasswordAuthenticationToken을 만들 수 있다..
	 * */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//authentication으로부터 Front에서 사용자가 직접 입력한 아이디와 비번을 가져옴
		String loginId = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		//System.out.println("chekcer > "+password);
		

		//Front에서 사용자가 직접 입력한 id를 갖고 DB에 전달하여 user 정보를 받아온다.
		PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(loginId);
		
		//System.out.println("chekcer > "+principalDetails.isEnabled());
		//System.out.println("chekcer2 > "+principalDetails.getUser().isEnabled());
		
		//log.info("role 체크 "+principalDetails.getUser().getRole());
		
		//비번이 5번 이상 틀였을 때 
		//가입 했는데 관리자 승인이 없다면 isEnabled 컬림이 1(True)이 된다.
		//계정이 관리자에 의해 정지되면 NOT이된다.
		
		//로그인이 불가능한 경우 > 계정 없음, 비번 틀림, 비번 5번 틀림, 가입 미승인, 계정 정지
		
		if(principalDetails == null) {
			//log.info("username이 없습니다.");
			throw new UsernameNotFoundException(loginId);
		}
		else if(principalDetails.getUser().getRoleType()  == RoleType.NOT) {
			//log.info("계정이 잠겼습니다. RoleType이 NOT");
			throw new DisabledException(loginId);
		}
		else if( (principalDetails.getUser().getLoginFailCount() > 5) == true   ) {
			//log.info("비번이 5번 이상 틀렸습니다.");
			throw new Custom_UserLoginFailCountOverException("ABC", 0);
		}
		else if(비번일치함(password, principalDetails.getPassword() )== false) {
			//log.info("비번이 틀렸습니다.");
			throw new BadCredentialsException(loginId);
		}
		//else if(principalDetails.getUser().getRoleType().equals("YET")) {
		else if(principalDetails.getUser().getRoleType() == RoleType.YET) {
			//log.info("가입 미승인 상태입니다. Role_Type이 YET");
			throw new Custom_UserNotApprovalException("ABC", 0);
		}
		//아무런 문제 없을 때
		else {
		//매개변수가 2개 = 인증 전에 호출되는 생성자임
		//매개변수가 3개 = 인증 후에 호출되는 생성자임
		return 
				new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getPassword(), principalDetails.getAuthorities());
		}
	}

	//암호화 안 된 것, 암호화 된 비번의 일치여부를 비교함. 일치하면 true 리턴
	private boolean 비번일치함(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }

	/**
	 * 현재 클래스인 CustomAuthenticationProvider가 authentication을 지원하는 경우 True를 리턴함(supports() 메소드의 설명 참조) 
	 * */	
	@Override
	public boolean supports(Class<?> authentication) {
		   return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

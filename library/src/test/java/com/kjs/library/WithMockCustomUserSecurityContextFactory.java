package com.kjs.library;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.testSample.Account;

/**
 * Security Context를 생성함.
 * */

//Not use
public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser>{

	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		
		
		PrincipalDetails principal = PrincipalDetails(customUser.username(), customUser.name());
		
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
		
		context.setAuthentication(auth);
				
		return context;
	}

	private PrincipalDetails PrincipalDetails(String first, String second) {
		// TODO Auto-generated method stub
		return null;
	}

}

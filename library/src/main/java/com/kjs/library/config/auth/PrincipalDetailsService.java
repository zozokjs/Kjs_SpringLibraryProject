package com.kjs.library.config.auth;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//로그인 진행 관련 클래스
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	//사용자가 로그인하면 그때 입력한 아이디가 username의 형태로 여기로 전달됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//db에서 username과 일치하는 사용자를 가져 옴
		//그 결과를 리턴해줘야 함
		User userEntity = userRepository.findByUsername(username);
		
		//db 검색 결과 없음
		if(userEntity == null) {			
			return null;
		}else {	 //결과 있음		
			//인증 처리.
			
			return new PrincipalDetails(userEntity);			
			//return principalDetails;
		}
			
	}

}

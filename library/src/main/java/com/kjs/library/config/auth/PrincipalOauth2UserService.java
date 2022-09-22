package com.kjs.library.config.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kjs.library.domain.oauth.NaverProfile;
import com.kjs.library.domain.oauth.OAuth2UserInfo;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	private final AuthService authService;
	//private final AuthenticationManager authManager;
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		log.info(" token "+userRequest.getAccessToken().getTokenValue());
		log.info(" regis "+userRequest.getClientRegistration());
	
		
		//네이버로부터 받은 모든 데이터가 저장됨
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		log.info(" 2 "+oAuth2User.getAttributes().toString());
		
		//네이버로부터 받은 데이터 중 response에 해당되는 값이 저장됨
		OAuth2UserInfo oAuthUserInfo = new NaverProfile((Map)oAuth2User.getAttributes().get("response"));
		/*
		if(userRequest.getClientRegistration().equals("naver")) {
			log.info("네이버에서 로그인을 요청했습니다.");
			oauthUserInfo = new NaverProfile((Map)oAuth2User.getAttributes().get("response"));
		}*/
		String platform = oAuthUserInfo.getPlatform();
		String platformId = oAuthUserInfo.getPlatformId();
		String username = platform+"_"+platformId; //naver_1234
		String password = "gdgd";
		String email = oAuthUserInfo.getEmail();
		
		log.info("platform "+platform);
		log.info("platformId "+platformId);
		log.info("name "+username);
		log.info("email "+email);
		
		//db 삽입
		User userEntity = User.builder()
				.username(username)
				.password(cosKey)
				.email(email)
				.isEnabled(true)
				.loginFailCount(0)
				.phoneNumber("YET")
				.oAuthPlatform("NAVER")
				.build();
		
		//session.setAttribute("kakaoAccessToken", accessToken);
		
		
		//가입자, 비가입자 체크(username 기준)
		boolean registAble = authService.가입된유저다(username);
		
		//비가입자일 때 회원가입 진행
		if(registAble == true) {
			log.info("이미 가입된 회원입니다.");
			
			/*
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			*/
			//return "redirect:/";
			
		}else {
			authService.회원가입(userEntity);

			log.info("가입 성공.");
			/*
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication); 
			*/
			//return "redirect:/";
		}
		
		//이렇게 리턴 되면서 Authentication 객체에 들어감
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}

	
}

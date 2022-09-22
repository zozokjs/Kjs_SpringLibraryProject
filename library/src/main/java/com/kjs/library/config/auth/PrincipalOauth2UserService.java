package com.kjs.library.config.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kjs.library.domain.oauth.KakaoProfile;
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
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		//log.info(" token "+userRequest.getAccessToken().getTokenValue());
		
		//1. 플랫폼 이름 가져옴(kakao ... naver...)
		String platformName = userRequest.getClientRegistration().getRegistrationId();
		log.info(" platform "+platformName);
		
		//2. 카카오나 네이버로부터 받은 사용자 데이터가 저장됨
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info(" 정보 "+oAuth2User.getAttributes().toString());
		
		//3. OAuth2 플랫폼 별 사용자 아이디가 저장됨
		String platformId = null;
		
		//4. 플랫폼마다 획득한 사용자 정보를 변형함
		OAuth2UserInfo oAuth2UserInfo = null;
		if(platformName.equals("naver")) {
		
			//log.info("네이버에서 로그인을 요청했습니다.");
			oAuth2UserInfo = new NaverProfile((Map)oAuth2User.getAttributes().get("response"));
			platformId = oAuth2UserInfo.getPlatformId();
		}
		else if(platformName.equals("kakao")){
			
			//log.info("카카오에서 로그인을 요청했습니다.");
			oAuth2UserInfo = new KakaoProfile((Map)oAuth2User.getAttributes().get("kakao_account"));
			platformId = oAuth2User.getAttribute("id").toString();
			//log.info(" platformId "+oAuth2User.getAttribute("id").toString());
		}
		
		String username = platformName+"_"+platformId; //naver_1234
		String password = cosKey;
		String email = oAuth2UserInfo.getEmail();
		
		log.info("platform "+platformName);
		log.info("platformId "+platformId);
		log.info("name "+username);
		log.info("email "+email);
		
		
		//5. db 삽입
		User userEntity = User.builder()
				.username(username)
				.password(new BCryptPasswordEncoder().encode(password))
				.email(email)
				.isEnabled(true)
				.loginFailCount(0)
				.phoneNumber("YET")
				.oAuthPlatform(platformName)
				.build();
		
		//6. 가입자, 비가입자 체크(username 기준)
		boolean registAble = authService.가입된유저다(username);
		
		//7. 비가입자일 때 회원가입 진행
		if(registAble == true) {
			//log.info("이미 가입된 회원입니다.");
			
		}else {
			authService.회원가입(userEntity);
			//log.info("가입 성공.");
		}
		
		//리턴 되면서 Authentication 객체에 들어가면서 자동 로그인됨
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}

	
}

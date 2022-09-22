package com.kjs.library.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjs.library.domain.oauth.KakaoProfile;
import com.kjs.library.domain.oauth.OAuthToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * OAuth 로그인, 회원가입 처리를 위한 클래스
 * */
@RequiredArgsConstructor
@Service
@Slf4j
public class AuthOAuthService {

	
	/**
	 * accessCode를 던져서 acceeToken 받음
	 * @param accessCode, String, Kakao로부터 받은 회원 계정에 대한 Access Code
	 * @return accessToken, String
	 * @throws  
	 * */
	public String getAccessToken(String accessCode){
		
		//엑세스 토큰 받기
		RestTemplate rt = new RestTemplate();
		
		//http 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//http 바디 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "01d44202f2f9d241ef89ae26c19707c1");
		params.add("redirect_uri", "http://localhost:8080/auth/oauth/kakao/callback");
		params.add("code", accessCode);
		
		//헤더와 바디를 하나에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		//http 요청하기
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		//log.info(" "+response); //성공
		
		ObjectMapper mapper = new ObjectMapper();
		OAuthToken token = null;
		try {
			token = mapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log.info("message "+token.getAccess_token());
		return token.getAccess_token();
	}
	
	
	/**
	 * accessToken를 던져서 회원 정보 가져옴
	 * @param accessToken, String, Kakao로부터 받은 회원 계정에 대한 accessToken
	 * @return KakaoProfile, KakaoProfile 객체
	 * @throws  
	 * */
	public KakaoProfile getOAuthProfile(String accessToken) {
	
		//사용자 정보 가져오기
		RestTemplate rt2 = new RestTemplate();
		
		//http 헤더 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization", "Bearer "+accessToken);
		//헤더와 바디를 하나에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		//http 요청하기
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2,
				String.class);

		//log.info("2 "+response2.getBody());
		
		ObjectMapper mapper2 = new ObjectMapper();
		KakaoProfile profile = null;
		try {
			profile = mapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profile;
	}
	
	
	
}

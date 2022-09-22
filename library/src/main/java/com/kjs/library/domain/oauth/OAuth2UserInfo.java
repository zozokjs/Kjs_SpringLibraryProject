package com.kjs.library.domain.oauth;

public interface OAuth2UserInfo {
	
	//OAuth2 로그인을 제공하는 플랫폼 이름
	String getPlatform();
	
	//OAuth2 로그인을 제공하는 플랫폼에서 사용하는 사용자 고유 아이디
	String getPlatformId();
	
	//플랫폼에서 얻은 사용자 이름
	String getName();
	
	//플랫폼에서 얻은 사용자 이메일
	String getEmail();
}

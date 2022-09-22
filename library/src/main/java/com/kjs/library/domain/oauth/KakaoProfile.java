package com.kjs.library.domain.oauth;

import java.util.Map;

import javax.annotation.Generated;

import lombok.Data;

public class KakaoProfile implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	
	public KakaoProfile(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getPlatform() {
		return null;
	}

	@Override
	public String getPlatformId() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}



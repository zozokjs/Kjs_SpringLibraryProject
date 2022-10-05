package com.kjs.library.domain.oauth;

import java.util.Map;

public class GoogleProfile implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	
	public GoogleProfile(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getPlatform() {
		return "google";
	}

	@Override
	public String getPlatformId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

}

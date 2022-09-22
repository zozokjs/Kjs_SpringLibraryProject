package com.kjs.library.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;

import lombok.Data;

/**
 * 로그인 완료되면 UserDetails 타입의 오브젝트를 시큐리티의 고유한 세션 저장소에 저장한다.
 * */
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;
	
	private User user;
	//private boolean isEnabled;
	private  Map<String, Object> attributes;
	
	//일반 로그인할 때 사용함
	public PrincipalDetails(User user) {
		this.user=user;
	}
	
	//oauth 로그인할 때 사용함
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user=user;
	}
		
	
	//계정 잠김 여부 세팅
	public void setEnabled(User user) {
		//this.isEnabled = user.getena
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collector = new ArrayList<>();
		
		collector.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRoleType();
			}
		});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 잠김 여부
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.kjs.library.util;

import org.springframework.security.core.AuthenticationException;

public class Custom_UserNotApprovalException extends AuthenticationException{

	private static final long serialVersionUID = 1L;
	
	public Custom_UserNotApprovalException(String msg, int errCode){
		super(msg);
	}
	
}

package com.kjs.library.service.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class DomainCommonService {

	/**
	 * int 타입의 제본형식을 String으로 변환
	 * **/
	public static String 제본형식toString(int bindType) {

		String bindTypeString = "기타";
		
		switch(bindType) {
		
			case 1: bindTypeString ="종이" ;
						break;

			case 2: bindTypeString ="마력" ;
						break;

			case 3:  bindTypeString ="전자잉크" ;
						break;

			case 4: bindTypeString ="음성" ;
						break;

			case 5: bindTypeString ="영상" ;
						break;

			case 6: bindTypeString ="기타" ;
						break;
						
			default : bindTypeString ="기타" ;
			            break;
		}
		
		//System.out.println("주어진 제본형식 : "+bindType);
		//System.out.println("변경된 제본형식 : "+bindTypeString);

		return bindTypeString;
	}



}

package com.kjs.library.service.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonCookieService {

	
	/**
	 * 신규 쿠키를 세팅함
	 * @param response, HttpServletResponse, 쿠키가 세팅될 응답 객체
	 * @param cookieKey, String, 세팅될 쿠키의 Key 이름
	 * @param maxAge, int, 세팅될 쿠키의 유지시간(ms)
	 * @return
	 * @throws  
	 * */
	public void setNewCookie(HttpServletResponse response, String cookieKey, int maxAge) {
		//1. 날짜 포맷 세팅
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		//2. 오늘 날짜를 가져옴
		Date dateNowDate = new Date();
		//3. dateNowDate을 주어진 포맷으로 변경(Date -> String)
		String dateNowFormatted= simpleDateFormat.format(dateNowDate);
		//4. 새로운 uuid 생성
		UUID uuid = UUID.randomUUID();
		//5. uuid에 dateNowFormatted 더함
		String cookieValue = dateNowFormatted+"///"+uuid.toString();
		// 예)20220901///asdqwdasdkljqiwofh
		//6 쿠키 세팅
		Cookie newCookie = new Cookie(cookieKey, cookieValue);//새 쿠키 생성
		newCookie.setMaxAge(maxAge); //쿠키 유지 시간 설정 : 25시간 90000
		response.addCookie(newCookie); //쿠키 세팅
	}
	
	
	/**
	 * cookieKey에 해당되는 쿠키의 Value를 가져옴
	 * @param request, HttpServletRequest, 쿠키를 가져올 요청 객체
	 * @param cookieKey, String, 찾을 쿠키 Key 이름
	 * @return 쿠키의 Value 
	 * @throws  
	 * */
	public String findCookieValue(HttpServletRequest request, String cookieKey) {
		
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String key = cookie.getName();
				
				//visitorCookie 쿠키가 있을 때
				if(key.equals(cookieKey)) {
					//log.info("{}에 해당하는 쿠키 Key가 있습니다. ", cookieKey);
					cookieValue =  cookie.getValue();
				}else {
					//log.info("{}에 해당하는 쿠키 Key가 없습니다. ", cookieKey);
					//cookieValue = "";
				}
			}
		}else{
			//log.info("쿠키가 없습니다");
		}// end of if
		//log.info(cookieKey+"로 찾은 값: "+ cookieValue);

		return cookieValue;
		
	} // end of findCookieValue()
	
	
	/**
	 * cookieKey에 해당되는 쿠키를 만료 시킴
	 * @param response, response, 만료시킬 쿠키를 가져 올 응답객체
	 * @param cookieKey, String, 만료시킬 쿠키 Key 이름
	 * @return 
	 * @throws  
	 * */
	public void deleteOldCookie(HttpServletResponse response, String cookieKey) {
		
		Cookie deleteCookie = new Cookie(cookieKey, null); //Null 값의 쿠키 생성
		deleteCookie.setMaxAge(0); //쿠키 유지 시간 설정 
		response.addCookie(deleteCookie ); //쿠키 세팅
	}
	
	
	
	
}

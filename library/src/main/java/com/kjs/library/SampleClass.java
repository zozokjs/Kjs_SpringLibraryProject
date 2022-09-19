package com.kjs.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kjs.library.web.dto.CMRespDto;

public class SampleClass {

	/* 공백 확인
	List<String> kdcCallSignList_공백제거 = new ArrayList<>();
	for (int i = 0; i < kdcCallSignList.size(); i++) {
		//i번째 항목이 공백이 아닐 때만 add
		if(!kdcCallSignList.get(i).equals("")) {
			kdcCallSignList_공백제거.add(kdcCallSignList.get(i));
			//System.out.println("공백이 없습니다." + i);
		}
	}
	 */
	
	/**
	 * 주어진 ip와 값이 DB에 있는지 비교
	 * @param ip, String
	 * @param cookieValue : UUID.toString() 형태, String
	 * @return ip와 cookieValue에 해당 되는 값이 있으면 True, 없으면 False
	 * @throws  
	 * 
	 * */
	/*
	public void sampleMethod() {
		
	}
	 */
	/**
	 * Ajax로 요청 했을 때 Ajax로 되돌려 주기
	 * 0이면 처리 실패
	 * 1이면 처리 성공
	 * */
	//return new ResponseEntity<>(new CMRespDto<>(0,"로그인 해야 합니다.",null),HttpStatus.BAD_REQUEST);
	
	/*
	Enumeration<String>  e = session.getAttributeNames();
	
	while(e.hasMoreElements()) {
		System.out.println("nEXT  "+e.nextElement());
		
		String element = e.nextElement().toString();
		System.out.println("Element > "+ element);
		
		System.out.println("GET > "+session.getAttribute(element));

		if(element.equals("visit")) {
			System.out.println("visit와 일치함 > "+session.getAttribute("visit") );
			session.getMaxInactiveInterval();//유지시간
			session.getLastAccessedTime();//최종 접촉 시간
			session.getCreationTime(); //생성시간
			session.
			
		}else {
			System.out.println("일치된 값이 없어 visit를 세팅 합니다. ");
			session.setAttribute("visit", "kkkkk");
		}
	 	**/
}

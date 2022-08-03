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
	 * Ajax로 요청 했을 때 Ajax로 되돌려 주기
	 * 0이면 처리 실패
	 * 1이면 처리 성공
	 * */
	//return new ResponseEntity<>(new CMRespDto<>(0,"로그인 해야 합니다.",null),HttpStatus.BAD_REQUEST);



}

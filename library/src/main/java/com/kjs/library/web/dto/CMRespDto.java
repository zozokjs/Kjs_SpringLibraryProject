package com.kjs.library.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
	
	private int code; //성공, 실패 코드
	private String message; //리턴 메세지
	private T data; //리턴 데이터

}

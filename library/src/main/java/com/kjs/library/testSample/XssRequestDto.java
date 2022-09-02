package com.kjs.library.testSample;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class XssRequestDto {
	
	private String content;
	
	public XssRequestDto(String content) {
		this.content = content;
	}

}

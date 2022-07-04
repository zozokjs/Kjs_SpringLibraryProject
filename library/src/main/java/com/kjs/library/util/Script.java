package com.kjs.library.util;

public class Script {
	
	/**
	  1. CustomValidationException 실행. 이것의 생성자에는 String과 ErrorMap이 전달되어야 함.
	  2. CustomValidationException의 부모클래스가 String을 전달 받고 get한다.
	  3. ControllerExceptionHandler에서 CustomValidationException을 @ExceptionHandler을 통해 핸들링하고 있으므로
	  이것을 잡아채게 되고
	  결과적으로 Script가 리턴됨.
	 * */
	public static String back(String msg) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}
}

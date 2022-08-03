package com.kjs.library.domain.user;

//DB에 삽입되는 값을 고정함
/** YET : 가입 미승인, 
 * NOT : 계정 정지,
 * USER : 일반 사용자
 * SASEO : 사서(관리자)
 * ADMIN : 최종관리자
*/
public enum RoleType {
	YET,NOT,USER,SASEO,ADMIN
}

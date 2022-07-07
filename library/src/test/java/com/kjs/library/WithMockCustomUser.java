package com.kjs.library;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;


/**새로운 어노테이션 생성
 * 이 어노테이션의 결과로 Security context(username과 ps가 db로부터 이상 없음을 인증 받은 authentication 객체가 저장되는 공간)가 생성된다.
 * @WithSecurityContext가 등록되어 있기 때문에 Security Context가 생성된다. 
 * @WithSecurityContext에는 Security Context Factory를 지정해야 한다. 그래야 Security Context를 생성할 수 있음.
 * */
//Not use
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

	String username() default "rob";
	String name() default "Rob wnch";
	
}

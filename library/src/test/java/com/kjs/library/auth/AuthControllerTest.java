package com.kjs.library.auth;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.testSample.TestSample;
import com.kjs.library.testSample.TestSampleRepository;

import lombok.extern.slf4j.Slf4j;

// 통합 테스트

/***
 * @SpringBootTest 이걸 붙이면 서버 실행하듯이 모두 ioc에 올라감
 * WebEnvironment.MOCK = 실제 톰캣이 아니라 다른 가짜 톰켓으로 테스트함
 * WebEnvironment.RANDOM_PORT = 실제 톰켓으로 테스트함
 *
 * @AutoConfigureMockMvc = MockMvc를 IOC에 등록해준다.
 * @Transactional = 각각 테스트 함수가 종료될 때마다 트랜잭션을 롤백해준다. (db에 넣어준 걸 뺴준다)
 * 
 */
@Slf4j
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE) 
public class AuthControllerTest {
/*
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	//@BeforeEach //@Test라고 붙은 함수가 실행되기 전에 이 함수가 한 번씩 실행되도록 한다.
	public void init() {
		//id를 1로 초기화
		entityManager.createNativeQuery("ALTER TABLE testsample AUTO_INCREMENT = 1").executeUpdate();
	}
	
	
	
	
	
	@Test
	public void finalAll테스트() throws Exception{
		//given
		String code = "$2a$10$Styy3vtHcl4H16FktvFuDedoCO3PaJgYA3gDG3JS9MV6NxupdTI1W";
		
		User user = userRepository.findByPasswordAuthCodeToUser(code);
		
		//User user = userRepository.findByUsername("zozo2");
		
		System.out.println("> > "+user.getEmail());
		
	
		
	}
	*/
	
	
}

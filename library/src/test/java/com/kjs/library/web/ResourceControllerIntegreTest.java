package com.kjs.library.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

// 통합 테스트

/***
 * @SpringBootTest 이걸 붙이면 서버 실행하듯이 모두 ioc에 올라감
 * WebEnvironment.MOCK = 실제 톰캣이 아니라 다른 가짜 톰켓으로 테스트함
 * WebEnvironment.RANDOM_PORT = 실제 톰켓으로 테스트함
 *
 * @AutoConfigureMockMvc = MockMvc를 IOC에 등록해준다.
 * @Transactional = 각각 테스트 함수가 종료될 때마다 트랜잭션을 롤백해준다. (db에 넣어준 걸 뺴준다?)
 * 
 */
@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) 
public class ResourceControllerIntegreTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void saveTest()  {
		log.info("save 테스트 시작-------------------------------" );
	}
	
}

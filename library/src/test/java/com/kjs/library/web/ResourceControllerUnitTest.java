package com.kjs.library.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.CommonService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.UserService;
import com.kjs.library.testSample.TestSample;
import com.kjs.library.testSample.TestSampleRepository;
import com.kjs.library.testSample.TestSampleService;

import lombok.extern.slf4j.Slf4j;

//단위 테스트 (Controller, Filter, ControllerAdvice 등만 올라감)
@Slf4j
@WebMvcTest
public class ResourceControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	//최초 실행 시 이 클래스에서는 Controller 테스트하는 거라서 Controller 관련된 것만 뜨지
	//Controller 테스트에 필요한 Service는 뜨지 않음
	//그래서 MockBean이라는 어노테이션을 붙여서 가짜 서비스를 생성한다.
	//진짜 서비스를 연결한다면 그 서비스에 연결된 db 관련된 것도 다 연결해야 하기 때문임.
	@MockBean
	private BookService bookService;
	
	@MockBean
	private AuthService authService;
	
	@MockBean
	private CommonService commonService;
	
	@MockBean
	private SaseoService saseoService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TestSampleService testSampleService ;
	
	@MockBean
	private TestSampleRepository testSampleRepository;
	
	
	@Test
	public void saveTest() throws JsonProcessingException {
		log.info("save 테스트 시작-------------------------------" );

		//writeValueAsString는 JSON으로 만들어준다.
		//String content = new ObjectMapper().writeValueAsString(new Lend(1, null,null,null, "title","title",null));
		
		
		String content = new ObjectMapper().writeValueAsString(new TestSample(1, "title", "content"));
		
		log.info(content);
		
		
	}
	
	
}

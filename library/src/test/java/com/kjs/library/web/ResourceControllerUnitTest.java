package com.kjs.library.web;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
	
	
	//@Test
	public void saveTest() throws Exception {
		log.info("save 테스트 시작-------------------------------" );

		//writeValueAsString는 JSON으로 만들어준다.
		//String content = new ObjectMapper().writeValueAsString(new Lend(1, null,null,null, "title","title",null));
		//given, when, then  .. bdd 패턴
		//given(테스트를 위한 준비)
		TestSample testSample = new TestSample(1, "abc", "content");//객체 만들고
		String content = new ObjectMapper().writeValueAsString(testSample); //제이슨으로 바꿈
		//when(testSampleService.저장하기(testSample)).thenReturn(new TestSample(1,"abc","content")); //실행되면 이것이 리턴될 것이다?
		
		//when( 테스트 실행 )
		ResultActions resultAction =
		mockMvc.perform(post("/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8) //보내는 타입
				.content(content) //보낼 거
				.accept(MediaType.APPLICATION_JSON_UTF8));//받는 타입
		
		//then ( 검증 )
		resultAction
			.andExpect(status().isCreated()) //응답은 201을 기대함
			.andExpect(jsonPath("$.title").value("abc")) //json을 기대함. title이라는 변수의 값이 abc가 맞는가?
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void finalAll테스트() throws Exception{
		//given
		//가짜 db로 하니까 이렇게 데이터를 넣어줘야 함
		List<TestSample> testSamples = new ArrayList<>();
		testSamples.add(new TestSample(1, "abc","ㅇㄷㄹ"));
		testSamples.add(new TestSample(2, "dcv","ㄱㄴㄷ"));
		when(testSampleService.모두가져오기()).thenReturn(testSamples);
		
		//when
		ResultActions resultAction =
				mockMvc.perform(get("/testSample")
						.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$",Matchers.hasSize(2))   ) //json을 기대함. 전체의 / 사이즈가 2개인가?
		.andExpect(jsonPath("$.[0].title").value("abc")) //전체 중 0번지의 타이틀 / 그 값이 abc인가?
		.andDo(MockMvcResultHandlers.print()); //결과물을 콘솔에 출력
	}
	
	
	@Test
	public void findById_테스트() throws Exception{
		//given
		int id = 1;
		
		when(testSampleService.한건가져오기(id)).thenReturn(new TestSample(1,"ㄱㄱㄱ","sss"));
		
		//when
		ResultActions resultAction =
				mockMvc.perform(get("/testSample/{id}", id)
						.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$.title").value("ㄱㄱㄱ"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void update_테스트() throws Exception{
		
		//given
		int id = 1;
		TestSample testSample = new TestSample(1, "cccc", "ㅁㄴㅁㄴㅁㄴ");//객체 만들고
		String content = new ObjectMapper().writeValueAsString(testSample); //제이슨으로 바꿈
		when(testSampleService.수정하기(id, testSample)).thenReturn(new TestSample(1,"cccc","content")); //실행되면 이것이 리턴될 것이다?

		
		//when
		ResultActions resultAction =
		mockMvc.perform(put("/testSample/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_UTF8) //보내는 타입
				.content(content) //보낼 거
				.accept(MediaType.APPLICATION_JSON_UTF8));//받는 타입
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$.title").value("cccc"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	
	@Test
	public void delete_테스트() throws Exception{
		
		//given
		int id = 1;
		when(testSampleService.삭제하기(id)).thenReturn("ok");
		
		//when
		ResultActions resultAction =
		mockMvc.perform(delete("/testSample/{id}", id)
				.accept(MediaType.TEXT_PLAIN));//받는 타입(문자열)
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 200을 기대함
		.andDo(MockMvcResultHandlers.print());
		
		//문자열 응답을 검증할 때 
		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals("ok",result);

	}
	
}

package com.kjs.library.testSample.web;

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
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) 
public class TestSampleControllerIntegreTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TestSampleRepository testSampleRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach //@Test라고 붙은 함수가 실행되기 전에 이 함수가 한 번씩 실행되도록 한다.
	public void init() {
		//id를 1로 초기화
		entityManager.createNativeQuery("ALTER TABLE testsample AUTO_INCREMENT = 1").executeUpdate();
	}
	
	
	
	
	
	//@Test
	public void saveTest() throws Exception {
		log.info("save 테스트 시작-------------------------------" );

		//writeValueAsString는 JSON으로 만들어준다.
		//String content = new ObjectMapper().writeValueAsString(new Lend(1, null,null,null, "title","title",null));
		//given, when, then  .. bdd 패턴
		//given(테스트를 위한 준비)
		TestSample testSample = new TestSample(1, "abc", "content");//객체 만들고
		String content = new ObjectMapper().writeValueAsString(testSample); //제이슨으로 바꿈
		
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
		//진짜 db로 하는데 값이 없으니까 이렇게 데이터를 넣어줘야 함
		List<TestSample> testSamples = new ArrayList<>();
		testSamples.add(new TestSample(1, "abc","ㅇㄷㄹ"));
		testSamples.add(new TestSample(2, "dcv","ㄱㄴㄷ"));
		testSampleRepository.saveAll(testSamples);
		
		//when
		ResultActions resultAction =
				mockMvc.perform(get("/testSample")
						.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$",Matchers.hasSize(2))   ) //json을 기대함. 전체의 / 사이즈가 2개인가?
		.andExpect(jsonPath("$.[0].title").value("abc")) //전체 중 0번지의 타이틀 / 그 값이 abc인가?
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	
	
	@Test
	public void findById_테스트() throws Exception{
		//given
	
		List<TestSample> testSamples = new ArrayList<>();
		testSamples.add(new TestSample(1, "abc","ㅇㄷㄹ"));
		testSamples.add(new TestSample(2, "dcv","ㄱㄴㄷ"));
		testSampleRepository.saveAll(testSamples);
		
		int id = 2;
		
		//when
		ResultActions resultAction =
				mockMvc.perform(get("/testSample/{id}", id)
						.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$.title").value("dcv"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	
	
	@Test
	public void update_테스트() throws Exception{
		
		//given
		int id = 1;
		List<TestSample> testSamples = new ArrayList<>();
		testSamples.add(new TestSample(1, "abc","ㅇㄷㄹ"));
		testSamples.add(new TestSample(2, "dcv","ㄱㄴㄷ"));
		testSampleRepository.saveAll(testSamples);
		
		TestSample testSample = new TestSample(1, "zzzz", "ㅁㄴㅁㄴㅁㄴ");//객체 만들고
		String content = new ObjectMapper().writeValueAsString(testSample); //제이슨으로 바꿈

		
		//when
		ResultActions resultAction =
		mockMvc.perform(put("/testSample/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_UTF8) //보내는 타입
				.content(content) //보낼 거
				.accept(MediaType.APPLICATION_JSON_UTF8));//받는 타입
		
		//then
		resultAction
		.andExpect(status().isOk()) //응답은 201을 기대함
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.title").value("zzzz"))
		.andDo(MockMvcResultHandlers.print());
	}
	
}

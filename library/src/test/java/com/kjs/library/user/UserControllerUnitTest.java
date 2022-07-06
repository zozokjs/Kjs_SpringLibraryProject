package com.kjs.library.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.UserService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.testSample.TestSampleService;
import com.kjs.library.web.dto.auth.SignupDto;
import com.kjs.library.web.dto.lend.UserLendListInterface;

import lombok.extern.slf4j.Slf4j;

//단위 테스트용
@Transactional //실제 DB를 사용해줌. 테스트 내용은 롤백됨
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class UserControllerUnitTest {

	/**
	 * 이 클래스는 Controller만 테스트함. 그래서 Controller 관련된 것만 메모리에 올라간다. 
	 * 그래서 MockBean을 붙여서 컨트롤러에 필요한 가짜 서비스를 생성함
	 * 진짜 서비스를 연결한다면, 그 서비스에 연결된 db도 모두 연결되어야 하기에 단위 테스트라는 목적에 맞지 않음.
	 * */

	@Autowired
	private MockMvc mockMvc;
	
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
	private TestSampleService testSampleService;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private LendRepository lendRepository;
	
	
	
	
	
	//String으로 오는 걸 바꿔야 함. 년월일로
	
	
	
	
	@Test
	public void CommonServiceTest() throws ParseException {
		log.info("테스트 시작");
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(12, 1200, 10);
		
		log.info("> "+ pageMap.get("pageStart"));
		log.info(">> " + pageMap.get("pageEnd"));
		log.info(">>> " + pageMap.get("a"));
		
		
		Iterator<String> pages = pageMap.keySet().iterator();
		
		log.info("check "+pages.hasNext());
		
		while(pages.hasNext()) {
			String keys = pages.next();
			System.out.println("key "+keys +"val :" + pageMap.get(keys));
			log.info("3");
		}
		
		
		
	}
	
	
	
	
	//특정 사용자의 대출 목록을 보여줘야 함
	 
	
	//Given
	//When
	//Then
	@Test
	public void 대출목록() throws Exception {
		log.info("테스트 시작");
		
		//Given
	/*	SignupDto signupDto = new SignupDto();
		signupDto.setUsername("zozo");
		signupDto.setPassword("1234");
		User user = signupDto.toEntity();
		authService.회원가입(user);
		
		User user2 = new User(1,null, "zozo","1234", null, null, null, null, null, null, null, null, null, null, null);
		
		//List<UserLendListInterface> userLendList = bookService.대출목록(1);
		
		String content = new ObjectMapper().writeValueAsString(user2);
		
		//When
		ResultActions resultAction =
				//mockMvc.perform(get("/user/myLibrary")
				mockMvc.perform(post("/auth/signin")
						.contentType(MediaType.APPLICATION_JSON_UTF8) //보내는 타입
						.content(content) //보낼 거
						.accept(MediaType.APPLICATION_JSON_UTF8));//받는 타입
		
		
		//Then
		resultAction
		.andExpect(status().isOk()); //응답은 201을 기대함*/
		
	}
	
	

}

package com.kjs.library.auth;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.catalina.security.SecurityConfig;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;
import com.kjs.library.testSample.Account;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=SecurityConfig.class)
@WebAppConfiguration*/
public class loginTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AuthService authService;
	
	
	//@BeforeEach
	public void setup() {
		
		mvc =  MockMvcBuilders
					.webAppContextSetup(context)
					.apply(springSecurity())
					.build();
				
		//public User(String username, String password, boolean isEnabled, String email,String phoneNumber,String role)
		//User user = new User("id",passwordEncoder.encode("1234"),true,"email","1234","ROLE_USER");
		//userRepository.save(user);
	}
	
	@Test
	public void loginTest111() throws Exception{
		
		//given
		/*
		int id =1;
		String username = "idas";
		String password = "1234";
		boolean enabled = true;
		String email = "zozo@naver.com";
		String phoneNumber = "010-313";
		String role = "ROLE_USER";
		User userMock = this.createUser(id, username, password , enabled, email, phoneNumber, role);
		*/
		//formLogin()을 쓴다면, 기존 DB에 존재하는 유저를 사용하여 테스트 함.
		//여기서는 테스트용 유저를 만들어서 테스트 함
		
		//when
		//mvc.perform(formLogin()) // 이것은 사용자 이름 user, 비번 password와 유효한 csrf 토큰으로 /login에 POST 요청한다.
		//mvc.perform(formLogin("/auth").user("zozo").password("pass") //이것은 이름 zozo, 비번 pass와 유효한 csrf 토큰으로 /auth에 POST요청한다. 
		//mvc.perform(formLogin("/auth").user("a", "zozo").password("b", "pass") //http 파라미터의 a에 사용자 이름을, b에 비밀번호를 추가하도록 요청한다.
		//mvc.perform(logout()) //   /logout에 Post 요청 보냄
		//mvc.perform(logout("/signout")) //   /signout에 Post 요청 보냄
		//mvc.perform(formLogin().user("zozo").password("1234"))
		//mvc.perform(formLogin("/auth/signin").user("zozo"))
		//.andExpect(authenticated().withUsername("zozo"));

		//mvc.perform(formLogin()) //Authentication should not be null
		mvc.perform(formLogin("/auth/signin").user("zozo"))
		.andExpect(authenticated())
		.andDo(print())
        .andExpect(status().isOk());

		
	}
	
	
	//public User(String username, String password, boolean isEnabled, String email,String phoneNumber,String role)
	//User user = new User("id",passwordEncoder.encode("1234"),true,"email","1234","ROLE_USER");
	/*
	private User createUser(int id, String username, String password, boolean isEnabled, String email,String phoneNumber,String role) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEnabled(isEnabled);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setRole("ROLE_USER");
		return authService.회원가입(user);
		
 	}*/
	
	//지정한 사용자 이름, 비번, 권한으로 UserDetails를 생성한다.
	//지정하지 않았다면 기본값을 가짐.
	@WithMockUser
	@Test
	public void testWithMockUser() throws Exception {
		
	}
	
	//익명 유저를 생성한다.
	@WithAnonymousUser
	@Test
	public void testAnonymousUser() throws Exception {
		
	}
	
	/**
	지정한 username으로 계정을 조회, UserDetails 객체를 조회한다.(실제 db에 존재하는 username이어야 한다)
	userDetailsServiceBeanName에는 UserDetailsService를 구현한 빈 이름이 들어가야 한다. 하나만 있다면 생략 가능.
	PrincipalDetailsService 클래스는 UserDetailsService를 구현받고 있다
	 * */
	//@WithUserDetails(value="zozo") //성공
	@WithUserDetails(value="1234", userDetailsServiceBeanName = "principalDetailsService") //db에 value에 명시한 아이디가 있을 때 성공
	@Test
	public void testUserDetails() throws Exception {
		
	}
	
	/**@WithUserDetails를 사용하면서 특정한 사용자를 미리 생성하고 테스트하고 싶다면
	 * @Before 말고 @BeforeTransaction을 이용해야 한다. 
	 * 그리고 여기서 1234라는 유저를 생성하는데 성공했다면,  @WithUserDetails에서도 1234를 지정하여 테스트 가능함
	 * */
	//@BeforeTransaction
	public void accontSetup(){
	 userRepository.save(User.builder()
				.username("1234")
				.password("1234")
				.email("zo@naver,cvom")
				.phoneNumber("010-1")
				.build());
	}
	
	
	/**성공-단순 무작위 유저를 사용하여 인증 테스트*/
	@Test
	public void testAnonymous() throws Exception{
		mvc.perform(get("/").with(anonymous())
				)//인증되지 않은 유저
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	/**성공-DB에 유저가 없더라도, 그 유저가 로그인한 상태라고 가정하여 인증 테스트*/
	@Test
	public void testUser() throws Exception{
		mvc.perform(get("/").with(user("1234").roles("USER"))
				)//유저 인증 정보를 MOCKING 하고 테스트 진행. DB에는 없음
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	
	
	
}

package com.kjs.library.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.testSample.TestSample;

import lombok.extern.slf4j.Slf4j;



//@Slf4j
@Transactional	
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //mysql db 사용
//@DataJpaTest //Repository 들을 모두 IOC에 등록해준다.
@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryUnitTest {

	/*
	//@DataJpaTest 안에 있는 @ExtendWith(SpringExtension.class) 때문에 이미 빈이 등록되어 있기 때문에
	//별도의 어노테이션을 붙일 필요 없음
	//실제 db를 써서 응답 받을 것임.
	@Autowired
	private UserRepository userRepository;
		
	@WithUserDetails(value="1234", userDetailsServiceBeanName = "principalDetailsService") //db에 value에 명시한 아이디가 있을 때 성공
	@Test
	public void testUserDetails() throws Exception {
		
	}
	
	
	@Test
	@WithUserDetails(value="zozo", userDetailsServiceBeanName = "principalDetailsService")
	public void findLoginFailCount테스트() {
		// given
		User user2 = new User();
		user2.setUsername("bbbb");
		user2.setPassword("1234");
		user2.setEmail("ema");
		user2.setPhoneNumber("pe");
		user2.setEnabled(false);
		user2.setLoginFailCount(0);
		//userRepository.save(user2);
		
		//userRepository.save(new User("zozo","1234"));
		
		// when
		int result = userRepository.findLoginFailCount("zozo");
		
		// then
		//log.info("result : "+result );
		assertNotEquals("ad",1);
		assertEquals("as",0);
	}
	*/
}

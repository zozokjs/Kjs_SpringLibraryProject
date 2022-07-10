package com.kjs.library.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.testSample.domain.TestSampleRepositoryUnitTest;
import com.kjs.library.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional	
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //mysql db 사용
@DataJpaTest //Repository 들을 모두 IOC에 등록해준다.
//@RequiredArgsConstructor
public class AuthenticateTest {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	public void jpaTest() {
		//(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
		User user2 = new User();
		user2.setUsername("bbbb");
		user2.setPassword("1234");
		user2.setEmail("ema");
		user2.setPhoneNumber("pe");
		user2.setEnabled(false);
		user2.setLoginFailCount(0);
		user2.setRole("USER");
		userRepository.save(user2);
		
		List<User> users = userRepository.findAll();
		
		assertTrue(users.stream().anyMatch(user -> user.getUsername().equals("bbbb")));
		
	}
	
	
	//arrertTrue(users.stre)
	
}

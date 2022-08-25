package com.kjs.library.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.common.DateCommonService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;

//단위 테스트
@Transactional
@AutoConfigureMockMvc
public class AuthServiceUnitTest {

	//@InjectMocks//@Mock이라고 붙은 'Mock객체'를 @InjectMocks가 붙은 객체에 주입한다.
	//서비스를 테스트하려면 repository가 가진 db 함수가 필요하므로 주입 필요함
	private AuthService authService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private DateCommonService dateCommonService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	public void db에서_가져온_값에_시간더하기() throws ParseException {
		
		String code = "$2a$10$Styy3vtHcl4H16FktvFuDedoCO3PaJgYA3gDG3JS9MV6NxupdTI1W";
		
		//User user = userRepository.findByPasswordAuthCodeToUser(code);
		
		User user = userRepository.findByUsername("zozo2");
		
		
		System.out.println("이름 갖 >"+user.getUsername());
		
	}
	
	private Lend createCreateLendRequest(User user, Book book, Samebook samebook) {
		Lend lend = new Lend();
		lend.setUser(user);
		lend.setBook(book);
		lend.setSamebook(samebook);
		lend.setId(7);
		return lend;
	}
	
	private User createUserRequest() {
		User user = new User();
		user.setId(4);
		user.setUsername("zozo");
		return user;
	}
	
	private Book createBookRequest() {
		Book book = new Book();
		book.setId(2);
		book.setTitle("Title");
		return book;
	}
	
	private Samebook createSamebookRequest() {
		Samebook samebook = new Samebook();
		samebook.setId(1);
		samebook.setKdcCallSign("8745");
		return samebook;
	}
	

	
}

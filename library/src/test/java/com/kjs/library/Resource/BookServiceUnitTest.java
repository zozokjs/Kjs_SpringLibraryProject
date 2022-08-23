package com.kjs.library.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.BookService;
import com.kjs.library.service.common.DateCommonService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;

//단위 테스트
@Slf4j
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

	//@InjectMocks//@Mock이라고 붙은 'Mock객체'를 @InjectMocks가 붙은 객체에 주입한다.
	//서비스를 테스트하려면 repository가 가진 db 함수가 필요하므로 주입 필요함
	private BookService bookService;
	
	@InjectMocks
	private DateCommonService dateCommonService;
	
	@Mock
	private LendRepository lendRepository;
	
	@Test
	public void 날짜() throws Exception {
		
		String finalDate = null;
		String givenDate  = "20220909";
		//특정한 날짜가 공휴일이거나 일요일이면 다음 날짜 변경...
		int year = 2022;
		int month = 8;
		int day = 28;
		//System.out.println(dateCommonService.일요일이다("20220828"));
		boolean 공휴일 = true;
		
		
		
		while(공휴일) {
			System.out.println("기준 날짜 : "+givenDate);
			if(dateCommonService.일요일이다(givenDate)  || dateCommonService.공휴일이다(givenDate)  ) {
				givenDate = dateCommonService.날짜더하기(givenDate,1);
				System.out.println("변경된 날자 : "+givenDate);
			}else {
				공휴일 = false;				
			}

		}
		
		
		/*		while(공휴일) {
					System.out.println("날짜가 변경 되었습니다 > "+givenDate);
				}
		*/		
	}
	
	
	//@Test
	public void 대출Test() {
		
		//Book, Samebook, User, Lend
		//given
		User user = createUserRequest();
		Book book = createBookRequest();
		Samebook samebook = createSamebookRequest();
		Lend lend = createCreateLendRequest(user, book, samebook);

		
		
		//when
		lendRepository.save(lend); 
		//bookService.책대출(book.getId(), user.getId());
		
		//test excute
	
		
		//then
		//assertEquals(bookEntity, book);
		 lendRepository.findById(7);
		log.info("save 테스트 시작-------------------------------" );
		//log.info(lendEn.get);
		
		
		assertEquals(lend.getSamebook().getKdcCallSign(), samebook.getKdcCallSign());
		
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

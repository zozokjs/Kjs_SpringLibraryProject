package com.kjs.library.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.testSample.TestSample;
import com.kjs.library.testSample.TestSampleRepository;

import lombok.extern.slf4j.Slf4j;

//단위 테스트( db 관련된 bean만 ioc에 등록)

/**
 * @AutoConfigureTestDatabase(replace = Replace.ANY) 가짜 db로 테스트 함
 * @AutoConfigureTestDatabase(replace = Replace.NONE) 실제 DB로 테스트 함 
 * */
@Slf4j
@Transactional	
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //mysql db 사용
//@AutoConfigureTestDatabase(replace = Replace.ANY) //내장 db 사용
@DataJpaTest //Repository 들을 모두 IOC에 등록해준다.
public class UserRepositoryUnitTest {

	//@DataJpaTest 안에 있는 @ExtendWith(SpringExtension.class) 때문에 이미 빈이 등록되어 있기 때문에
	//별도의 어노테이션을 붙일 필요 없음
	//실제 db를 써서 응답 받을 것임.
	@Autowired
	private UserRepository userRepository;

	
	@Test
	public void findAll_테스트() throws ParseException {
		
		// when
		User user= userRepository.findByUsername("zozo2");
		
				
		System.out.println("date < "+user.getEmail());
		
		//1. 저장된 코드 생성 시간 가져옴
		String createTimeByAuthCode = user.getPasswordAuthTime();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		System.out.println("저장 시간 ㅣ "+createTimeByAuthCode);
		Date date = new Date();
		
		date = sdformat.parse(createTimeByAuthCode); //String > Date
		cal.setTime(date);
		cal.add(Calendar.HOUR, 3); //3시간 더함
		String afterDate_String = sdformat.format(cal.getTime()); //String Return
		
		Date dateNow = new Date();
		
		Date dateAfter = sdformat.parse(afterDate_String);

		
		
		
		
	}
	//@Test
	public void 저장_테스트() {
		/*
		//given
		TestSample testSample = new TestSample(1,"ㅁaa","bbb");
		
		//when
		TestSample testSampleEntity = testSampleRepository.save(testSample);
		
		//then
		//assertEquals( 기대값, 실제 값)
		assertEquals("ㅁaa", testSampleEntity.getTitle());
		*/
	}
	
	
	


}

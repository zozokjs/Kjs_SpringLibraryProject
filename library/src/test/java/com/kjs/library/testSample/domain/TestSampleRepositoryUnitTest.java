package com.kjs.library.testSample.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

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
public class TestSampleRepositoryUnitTest {

	//@DataJpaTest 안에 있는 @ExtendWith(SpringExtension.class) 때문에 이미 빈이 등록되어 있기 때문에
	//별도의 어노테이션을 붙일 필요 없음
	//실제 db를 써서 응답 받을 것임.
	@Autowired
	private TestSampleRepository testSampleRepository;

	//@Test
	public void 저장_테스트() {
		
		//given
		TestSample testSample = new TestSample(1,"ㅁaa","bbb");
		
		//when
		TestSample testSampleEntity = testSampleRepository.save(testSample);
		
		//then
		//assertEquals( 기대값, 실제 값)
		assertEquals("ㅁaa", testSampleEntity.getTitle());
		
	}
	
	
	
	//@Test
	public void findAll_테스트() {
		// given
		testSampleRepository.saveAll(
				Arrays.asList(
						new TestSample(1, " abc ", "def"),
						new TestSample(2, "123", "456")
				)
			);
		
		// when
		List<TestSample> bookEntitys = testSampleRepository.findAll();
		
		// then
		//log.info("bookEntitys : "+bookEntitys.size() );
		assertNotEquals(0, bookEntitys.size());
		assertEquals(2, bookEntitys.size());
	}

}

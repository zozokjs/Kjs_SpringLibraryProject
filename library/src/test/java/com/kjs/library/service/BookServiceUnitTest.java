package com.kjs.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kjs.library.testSample.TestSample;
import com.kjs.library.testSample.TestSampleRepository;
import com.kjs.library.testSample.TestSampleService;
/**
 * 단위 테스트( 서비스와 관련된 것만 메모리에 띄움)
 * Service만 테스트함. 그런데 서비스 테스트하려면 BookRepository가 있어야 함. 하지만 그게 있으면 db 연결이 필요하기 때문에
 * 결과적으로 서비스만 테스트 하는 게 아니게 된다.
 * 그래서 가짜 객체로 만들어야 하는데 그걸 MockitoExtension가 지원함.
 *
 * */

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

	@InjectMocks //BookService 객체가 생성될 때 BookServiceUnitTest 파일에서 @Mock으로 등록된 모든 애들을 주입시킨다.
	//그러므로 아래의 @Mock private BookRepository bookRepository가 반드시 필요함
	private TestSampleService testSampleService;
	
	@Mock
	private TestSampleRepository testSampleRepository;
	
	
	public void 저장_테스트() {
		
		TestSample testSample = new TestSample();
		testSample.setAuthor("aaa");
		testSample.setTitle("bbb");
		
		//stub
		when(testSampleRepository.save(testSample)).thenReturn(testSample);
		
		//tst execute
		TestSample testSampleEntity = testSampleService.저장하기(testSample);
		
		//then
		//assertEquals(null, null);
		
	}
	
	
	
	
}

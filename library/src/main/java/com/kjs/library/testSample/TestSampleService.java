package com.kjs.library.testSample;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class TestSampleService {

	private final TestSampleRepository testSampleRepository;
	
	@Transactional
	public TestSample 저장하기(TestSample test) {
		
		return  testSampleRepository.save(test);
		
	}
	
	@Transactional(readOnly = true)
	public List<TestSample> 모두가져오기() {
		return testSampleRepository.findAll();
	}
	
	
	@Transactional(readOnly = true)
	public TestSample 한건가져오기(int id) {
		return testSampleRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("Book id를 확인해주세요."));
	}
	
	@Transactional
	public TestSample 수정하기(int id, TestSample book) {
		TestSample testSampleEntity = testSampleRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("Book id를 확인해주세요."));
		testSampleEntity.setTitle(book.getTitle());
		testSampleEntity.setAuthor(book.getAuthor());
		return testSampleEntity;
	}
	
	@Transactional
	public String 삭제하기(int id) {
		testSampleRepository.deleteById(id);
		return "ok";
	}
	
	
	
	
}

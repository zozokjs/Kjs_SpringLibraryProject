package com.kjs.library.testSample;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class TestSampleService {

	private final TestSampleRepository testRepository;
	
	@Transactional
	public void 저장하기(TestSample test) {
		
		testRepository.save(test);
		
	}
}

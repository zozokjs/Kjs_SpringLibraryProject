package com.kjs.library.testSample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestSampleController {
	
	private final TestSampleService testService;
	
	
	@PostMapping("/testSample")
	public ResponseEntity<?> save(@RequestBody TestSample book){
		return new ResponseEntity<TestSample>(testService.저장하기(book), HttpStatus.CREATED); // 201
	}
	
	@GetMapping("/testSample")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(testService.모두가져오기(), HttpStatus.OK); // 200
	}
	
	@GetMapping("/testSample/{id}")
	public ResponseEntity<?> findById(@PathVariable int id){
		return new ResponseEntity<TestSample>(testService.한건가져오기(id), HttpStatus.OK);
	}
	
	@PutMapping("/testSample/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody TestSample book){
		return new ResponseEntity<>(testService.수정하기(id, book), HttpStatus.OK);
	}
	
	@DeleteMapping("/testSample/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		return new ResponseEntity<>(testService.삭제하기(id), HttpStatus.OK);
	}
	
	
	
	
}

package com.kjs.library.web.api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.jasper.runtime.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.AuthService;
import com.kjs.library.web.dto.CMRespDto;
import com.sun.mail.util.logging.MailHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthApiController {

	
	private final UserRepository userRepository;
	
	//private final JavaMailSenderImpl javaMailSender;
	
	
	
	private  final AuthService authService;
	
	
	/**
	 * 아이디 사용 가능 확인 실시간
	 * */
	@PostMapping("/api/auth/match/{username}")
	public ResponseEntity<?> findByUsernameR(@PathVariable String username) {
		
		//System.out.println("------------------------");
		//System.out.println("프론트에서 넘어 옴 > "+username);
		
		//username을 db 조회해서 있는지 봐야함
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) { 
			//System.out.println("아이디 중복안됨");
			return new ResponseEntity<>(new CMRespDto<>(1,"전송성공","아이디 사용 가능합니다"), HttpStatus.OK);
		}else {
			//System.out.println("아이디 중복됨");			
			return new ResponseEntity<>(new CMRespDto<>(2,"전송성공","아이디가 중복 됩니다"), HttpStatus.OK);
		}
		
	}
	
	
	/**
	 * 회원 가입 중 이메일 인증을 위해 메일 전송함
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * 
	 * */
	@PostMapping("/api/auth/emailAuthenticationSending/{toEmailAddress}")
	public ResponseEntity<?> emailAuthenticationSending(@PathVariable String toEmailAddress) throws InterruptedException, ExecutionException {
	
		int authCode = (int)(Math.random()*1000000); //현재 시간을 기반으로 하는 6자리의 난수 생성
		
		System.out.println("목적지 이메일 주소 : "+toEmailAddress);
		/**
		 * https://codechacha.com/ko/java-executors/
		 * 
		 * 
		 * */
		
		Future future;;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		//future에 작업 결과가 저장된다.
		future = executor.submit(new Runnable() {
			@Override
			public void run() {
				authService.회원가입인증메일전송(authCode, toEmailAddress);
			}
		});

		//작업이 완료되면 쓰레드 풀을 종료함
		executor.shutdown();
		
		//10초 안에 모든 작업이 끝나길 기다린다. 끝나질 않으면 false 리턴됨.
		if(executor.awaitTermination(10, TimeUnit.SECONDS)) {
			System.out.println("모든 작업이 완료 되었습니다.");
		}else {
			System.out.println("작업 중 일부가 완료 되지 않았습니다. 작업을 강제 종료합니다.");
			executor.shutdown();
		}

		
		//작업이 성공하면 future.get()은 null을 리턴함
		if(future.get() == null) {
			return new ResponseEntity<>(new CMRespDto<>(1,"이메일 전송 성공", authCode), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto<>(0,"이메일 전송 실패",null), HttpStatus.OK);
		}
		
		
	}
	
	
	
	/**
	 * 이메일로 아이디 찾기
	 * @throws MessagingException 
	 * */
	@PostMapping("/api/auth/findId/{emailAddress}")
	public ResponseEntity<?> findToId(@PathVariable String emailAddress) throws  InterruptedException, ExecutionException {
		
		System.out.println("목적지 이메일 주소 : "+emailAddress);
		
		Future future;;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		//future에 작업 결과가 저장된다.
		future = 	executor.submit(new Runnable() {
			
			@Override
			public void run() {
				authService.아이디찾기메일전송(emailAddress);
			}
		});

		//작업이 완료되면 쓰레드 풀을 종료함
		executor.shutdown();
		
		//10초 안에 모든 작업이 끝나길 기다린다. 끝나질 않으면 false 리턴됨.
		if(executor.awaitTermination(10, TimeUnit.SECONDS)) {
			System.out.println("모든 작업이 완료 되었습니다.");
		}else {
			System.out.println("작업 중 일부가 완료 되지 않았습니다. 작업을 강제 종료합니다.");
			executor.shutdown();
		}
		
		//작업이 성공하면 future.get()은 null을 리턴함
		if(future.get() == null) {
			return new ResponseEntity<>(new CMRespDto<>(1,"이메일 전송 성공", null), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto<>(0,"이메일 전송 실패",null), HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/api/auth/resetPassword/{username}/{emailAddress}")
	public ResponseEntity<?> findToId(@PathVariable String emailAddress, @PathVariable String username) throws  InterruptedException, ExecutionException {
		
		System.out.println("목적지 이메일 주소 : "+emailAddress);
		System.out.println("비교할 username : "+username);
		
		
		Future future;;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		//future에 작업 결과가 저장된다.
		future = 	executor.submit(new Runnable() {
			
			@Override
			public void run() {
				authService.비밀번호초기화메일전송(username, emailAddress);
			}
		});

		//작업이 완료되면 쓰레드 풀을 종료함
		executor.shutdown();
		
		//10초 안에 모든 작업이 끝나길 기다린다. 끝나질 않으면 false 리턴됨.
		if(executor.awaitTermination(10, TimeUnit.SECONDS)) {
			System.out.println("모든 작업이 완료 되었습니다.");
		}else {
			System.out.println("작업 중 일부가 완료 되지 않았습니다. 작업을 강제 종료합니다.");
			executor.shutdown();
		}
		
		//작업이 성공하면 future.get()은 null을 리턴함
		if(future.get() == null) {
			return new ResponseEntity<>(new CMRespDto<>(1,"이메일 전송 성공", null), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto<>(0,"이메일 전송 실패",null), HttpStatus.OK);
		}
	}
		
}

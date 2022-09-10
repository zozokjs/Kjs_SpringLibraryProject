package com.kjs.library.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

//회원가입, 탈퇴 등 구체적인 로직 처리

@EnableAsync //?
@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final CommonService commonService;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AuthDataService authDateService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public void 회원가입(User user) {
		//1. 비번 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		//2, 비번 세팅
		user.setPassword(encPassword);
		//3. 권한 세팅
		user.setRoleType(RoleType.YET);
		//4. 회원정보 INSERT
		userRepository.save(user);
	}

	
	@Transactional
	public User 로그인실패횟수증가(String username) {
		
		int 로그인실패횟수;
		User userEntity = userRepository.findByUsername(username);
		로그인실패횟수 = userEntity.getLoginFailCount();
		//log.info("초기값 : "+로그인실패횟수);

		로그인실패횟수 = 로그인실패횟수 + 1;

		//log.info("변경된 로그인 실패 횟수 : "+로그인실패횟수);
		userEntity.setLoginFailCount(로그인실패횟수);
		return userEntity;
	}
	
	
	@Transactional(readOnly = true)
	public Integer 로그인실패횟수조회(String username) {
		
		int 로그인실패횟수 = userRepository.findLoginFailCount(username);
		return 로그인실패횟수;
	}
	
	@Transactional
	public User 로그인실패횟수초기화(String username) {
		
		User userEntity = userRepository.findByUsername(username);
		userEntity.setLoginFailCount(0);
		return userEntity;
	}
	
	
	/**Enabled 컬럼을 True로 변경함
	 * 권한을 NOT로 변경함
	 * */
	@Transactional
	public User 계정잠금(String username) {
		
		User userEntity = userRepository.findByUsername(username);
		userEntity.setEnabled(true);
		userEntity.setRoleType(RoleType.NOT);
		return userEntity;
	}
	
	@Transactional
	public void 회원가입인증메일전송(int authCode , String toEmailAddress)  {
		
		String mailTitle = "[토르두스국립도서관]회원가입 이메일 인증 번호 안내 메일입니다.";
		
		String mailContent = authDateService.회원가입이메일인증메일_내용(authCode);
		
		try {
			//이메일 전송함
			commonService.mailSendering(toEmailAddress, mailTitle,  mailContent);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void 아이디찾기메일전송(String toEmailAddress) {
		
		String mailTitle = "[토르두스국립도서관]아이디 찾기 안내 메일입니다.";
		
		String username = userRepository.findByEmailToUsername(toEmailAddress);
		
		String mailContent = authDateService.아이디찾기이메일_내용(username);
		
		//email에 해당하는 아이디가 없음
		if(username == null) {
			//아무것도 안 함
		}else {
			try {
				commonService.mailSendering(toEmailAddress, mailTitle,  mailContent);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Transactional
	public void 비밀번호초기화메일전송(String username, String toEmailAddress) {
		
		//0. username과 이메일이 일치하는 계정의 존재유무를 확인함(있으면 true(1), 없으면 null)
		Integer result = userRepository.findByEmailAndUsernameToExist(username, toEmailAddress);
		
		//1. 난수 생성
		Random random = new Random();
		Long randomNumber = random.nextLong();
		//2. 난수를 구분하기 어렵도록 암호화
		String encRandomNumber= bCryptPasswordEncoder.encode(Long.toString(randomNumber));
		//3. 난수와 난수 생성 시각을 세팅
		비밀번호난수세팅(username, encRandomNumber);
		
		String mailTitle = "[토르두스국립도서관]비밀번호 재설정 안내 메일입니다.";
		String Link = "http://localhost:8080/auth/passwordReset?accessCode="+encRandomNumber;
		String mailContent = authDateService.비밀번호초기화이메일_내용(Link);
		
		if(result != null) {
			try {
				//4. 이메일 전송함
				commonService.mailSendering(toEmailAddress, mailTitle,  mailContent);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			System.out.println("결과가 없음");
		}
		
		//String mailContent = authDateService.아이디찾기이메일_내용(username);
	}
	
	
	@Transactional
	public User 비밀번호난수세팅(String username, String encRandomNumber) {
		
		//1. 현재 데이터 호출
		User userEntity = userRepository.findByUsername(username);
		
		System.out.println("가져온 값 : "+userEntity.getEmail());
		
		//2. 난수 세팅
		userEntity.setPasswordAuthCode(encRandomNumber);
		
		//3. 현재 날짜 구함
		Date date = new Date();
		
		//4. 날짜 포맷 생성
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		//5. 날짜 포맷에 현재 날짜 맞춤
		String now = sdformat.format(date);
		
		//6. 현재 날짜 세팅
		userEntity.setPasswordAuthTime(now);
		System.out.println("세팅된 난수  : "+encRandomNumber);
		System.out.println("세팅된 시간  : "+now);
		
		return userEntity;
		
	}

	/**
	 * 비밀번호 인증 코드를 통해 유저 정보를 가져옴
	 * @throws ParseException 
	 * */
	@Transactional
	public User 비밀번호인증코드로유저찾기(String authCode) throws ParseException {
		
		//비번 재설정하면 code와 시간이 초기화 됨
		
		//만료 시간 가져와서 계산 후 문제 있으면 통과 못함.
		User user = userRepository.findByPasswordAuthCodeToUser(authCode);
		
	
		if(user == null) {
			//비번 재설정 했다면 code가 초기화 됨
			//접근 불가능하다고 얘기 해줘야 함
			
			System.out.println("code와 일치하는 값이 없습니다.");
			
			return null;
		}else {
			System.out.println("code와 일치하는 값이 있습니다.");
			
			//time 가져와서 12시간 지났으면 만료시키고 접근 불가능하다고 얘기 해줘야 함.
			//1. 저장된 코드 생성 시간 가져옴
			String createTime = user.getPasswordAuthTime();
			
			//2. 코드 생성 시간에서 12시간을 더해서 만료 시간 구함
			String terminateTime = DateCommonService.시간더하기(createTime, 12);
			
			//3. 현재시간과 만료시간 비교. 만료 시간이 늦으면 True
			boolean compareResult = DateCommonService.현재시간보다늦음(terminateTime);
			
			System.out.println("코드 생성 시간 : "+createTime);
			System.out.println("코드 만료 시간 : "+terminateTime);
			System.out.println("비교 결과 : "+compareResult);

			//4. 
			if(compareResult == true) {
				//코드 만료 안 됐음.
				System.out.println("code와 일치하는 값이 있어 정상 처리됩니다.");
				return user;
			}else{
				
				System.out.println("code와 일치하는 값이 있으나 만료되었습니다.");
				비밀번호인증코드초기화(authCode);
				return null;
			}
		}
		
	}

	@Transactional
	public User 비밀번호인증코드초기화(String authCode) {
		
		User user = userRepository.findByPasswordAuthCodeToUser(authCode);
		
		user.setPasswordAuthCode(null);
		user.setPasswordAuthTime(null);
		
		System.out.println("코드와 시간이 초기화 되었습니다.");
		
		return user;
	}
	
	
	/**
	 * 새 비밀번호 가져와서 재설정
	 * */
	@Transactional
	public User 비밀번호재설정(SignupDto signupDto) {
		
		User user = userRepository.findByUsername(signupDto.getUsername());
		
		//1. 비번 암호화
		String encPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());
		//2, 비번 세팅
		user.setPassword(encPassword);
		
		//3. 비밀번호 찾기 코드와 코드 생성 시간 초기화
		user.setPasswordAuthCode(null);
		user.setPasswordAuthTime(null);
		
		System.out.println("비밀번호가 재설정 되었습니다. 코드가 초기화 되었습니다.");
		
		return user;
	}

}

package com.kjs.library.web;

import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kjs.library.domain.oauth.KakaoProfile;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.AuthOAuthService;
import com.kjs.library.service.AuthService;
import com.kjs.library.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

//로그인 및 가입 처리
@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	private final AuthOAuthService oAuthService;

	private final AuthenticationManager authManager;

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@Value("${cos.key}")
	private String cosKey;
	
	//로그인 화면으로 이동
	@GetMapping("/auth/signin")
	public String signinPage() {
		return "auth/signinOtherPage";
	}
	
	//로그인 별도 페이지로 이동
	@GetMapping("/auth/signinOtherPage")
	public String signinOtherPage() {
		
		return "auth/signinOtherPage";
	}
	
	//회원가입 화면으로 이동
	@GetMapping("/auth/signup")
	public String signupPage() {
		
		return "auth/signup";
	}
	
	//로그인 실패 시 다시 로그인 화면으로 이동시킴
	@PostMapping("/auth/signinFail")
	public String signinForm() {
		System.out.println("exception 걸렸으므로 리턴 됩니다. ");
		 //return "redirect:/auth/signin";
		 return "auth/signinOtherPage";

	}
		
	
	//회원가입 처리
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		
		//log.info("--------------------------");
		//log.info(signupDto.toString());
		
		User user = signupDto.toEntity();
		authService.회원가입(user);
		
		//가입 환영 페이지로 이동
		return "auth/signupSuccess";
	}
	
	
	//회원가입 성공 페이지
	@GetMapping("/auth/signupSuccess")
	public String signupSuccess() {
		
		return "auth/signupSuccess";
	}
	
	
	//아이디, 비밀번호 찾기 페이지로 이동
	@GetMapping("/auth/findToIdPassword")
	public String findToIdPasswordForm() {
		
		return "auth/findToIdPassword";
	}

	//비밀번호 재설정 페이지로 이동
	@GetMapping("/auth/passwordReset")
	public String passwordResetForm(@RequestParam("accessCode") String accessCode, Model model) throws ParseException {
		
		System.out.println("전달된 값 > "+accessCode);
		System.out.println("access 코드를 받았습니다.");
		
		//accessCode로 조회해서 코드 생성 시간에서 일정 시간 지났으면 만료 처리하고 메인화면으로 이동
		User user = authService.비밀번호인증코드로유저찾기(accessCode);
		
		if(user != null) {
			
			System.out.println("user를 찾았습니다. 비밀번호 재설정 페이지로 이동합니다.");
			
			model.addAttribute("user", user);
			return "auth/resetPassword";
			
		}else {
			System.out.println("alert 페이지로 이동합니다.");
			
			model.addAttribute("msg", "페이지가 만료 되었습니다. 다시 시도해주세요.");
			model.addAttribute("targetPage", "/");
			return "main/scriptAlertPage";
		}
	}
	
	//비밀번호 재설정 처리
	@PostMapping("/auth/resetPassword")
	public String passwordResetProcess(SignupDto signupDto, Model model) {
		
		authService.비밀번호재설정(signupDto);

		//System.out.println("비밀번호가 재설정 되었습니다.");
		model.addAttribute("msg", "비밀번호가 재설정 되었습니다.");
		model.addAttribute("targetPage", "/");
		return "main/scriptAlertPage";
	}

	
	/**
	 * OAuth 라이브러리 사용하면서 deprecated
	 * OAuth를 이용한 카카오 회원 가입 및 로그인 
	 * @param accessCode, String, Kakao로부터 받은 회원 계정에 대한 Access코드
	 * @return 
	 * @throws  
	 * */
	//@GetMapping("/auth/oauth/kakao/callback")
	/**
	public String kakaoCallback(@RequestParam("code") String accessCode, HttpSession session) {
		log.info("code {}",accessCode);
		
		//accessCode 던져서 accessToken 얻어옴
		String accessToken = oAuthService.getAccessToken(accessCode);
		log.info("로그인 시 엑세스 토큰 "+accessToken);
		
		//accessToken 던져서 회원 정보 얻어옴
		KakaoProfile profile = oAuthService.getOAuthProfile(accessToken);
		
		//log.info("카카오아이디   {}",profile.getId());
		//log.info("블로그에 쓸 이메일   {}",profile.getKakao_account().getEmail());
		//log.info("블로그에 쓸 유저네임 {}",profile.getKakao_account().getEmail()+"_"+profile.getId());

		//db 삽입
		User kakaoUser = User.builder()
				.username(profile.getKakao_account().getEmail()+"_"+profile.getId())
				.password(cosKey)
				.email(profile.getKakao_account().getEmail())
				.isEnabled(true)
				.loginFailCount(0)
				.phoneNumber("YET")
				.oAuthPlatform("KAKAO")
				.build();
		
		session.setAttribute("kakaoAccessToken", accessToken);
		
		
		//가입자, 비가입자 체크(username 기준)
		boolean registAble = authService.가입된유저다(profile.getKakao_account().getEmail()+"_"+profile.getId());
		
		//비가입자일 때 회원가입 진행
		if(registAble == true) {
			log.info("이미 가입된 회원입니다.");
			
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "redirect:/";
			
		}else {
			authService.회원가입(kakaoUser);

			log.info("가입 성공.");
			
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "redirect:/";
		}
	}*/

	
	
}

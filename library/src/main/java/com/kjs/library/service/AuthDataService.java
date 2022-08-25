package com.kjs.library.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//회원가입, 탈퇴 등 구체적인 로직 처리

@RequiredArgsConstructor
@Service
public class AuthDataService {

	
	public String 회원가입이메일인증메일_내용(int authCode) {
		
		String a = 
				"<div style=\"display:block;\">\r\n"
				+ "\r\n"
				+ "	<div style=\"font-family:sans-serif;  \">\r\n"
				+ "		<div style=\"margin: 0 auto; max-width:800px;\">\r\n"
				+ "\r\n"
				+ "			<div >\r\n"
				+ "				<div style=\"text-align: center;\">\r\n"
				+ "					<!-- 1100 x 634px; -->\r\n"
				+ "					<img style=\"width: 30%;\" src=\"cid:logoImage\" />\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "			\r\n"
				+ "			<div style=\"padding-bottom:30px;\">\r\n"
				+ "				<div >\r\n"
				+ "					<div >\r\n"
				+ "	<p style=\"font-weight: bold; font-size:22px;\">이메일인증</p>"
				+ "					</div>\r\n"
				+ "					<div style=\" font-size:15px; \">\r\n"
				+ "						<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							안녕하세요. 토르두스국립도서관입니다. <br>\r\n"
				+ "							아래 번호를 \r\n"
				+ "							<span style=\" display: inline-block; background: #ff7200; color: #fff; border-radius: 2px; padding-left: 6px; padding-right: 6px; margin-left: 3px; margin-right: 3px;\">\r\n"
				+ "							화면에 입력하신 뒤 확인 버튼을 눌러 이메일 인증을 완료</span>\r\n"
				+ "							해주세요.<br>\r\n"
				+ "							<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">해당 인증 번호는 \r\n"
				+ "							<span style=\"text-decoration: underline; font-weight: bold;\">\r\n"
				+ "							전송 시각으로부터 10분 뒤 만료</span>\r\n"
				+ "							되오니 인증이 되지 않으신다면 <br>\r\n"
				+ "							새로고침 뒤 다시 시도해주시기 바랍니다.</p>\r\n"
				+ "							인증번호<br>\r\n"
				+ "						</p>\r\n"
				+ "							<div  >\r\n"
				+ "								<div >\r\n"
				+ "									<input type=\"text\"   value=\" "+authCode+" \"  style=\"font-size:30px; color: royalblue; text-align:center;     padding: 1px 2px;\">\r\n"
				+ "								</div>\r\n"
				+ "							</div>\r\n"
				+ "							<hr>\r\n"
				+ "							\r\n"
				+ "							<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							감사합니다.<br>\r\n"
				+ "							본 메일은 발신 전용이므로 회신되지 않습니다.<br>\r\n"
				+ "							문의사항이나 불편한 사항은 사이트 아래 관리자 연락처를 통해 연락주시기 바랍니다.</p>\r\n"
				+ "						</div>\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</div>";

		
		return a;
	}
	
	
	public String 아이디찾기이메일_내용(String username) {
		
		String a = 
				"<div style=\"display:block;\">\r\n"
				+ "\r\n"
				+ "	<div style=\"font-family:sans-serif;  \">\r\n"
				+ "		<div style=\"margin: 0 auto; max-width:800px;\">\r\n"
				+ "\r\n"
				+ "			<div >\r\n"
				+ "				<div style=\"text-align: center;\">\r\n"
				+ "					<!-- 1100 x 634px; -->\r\n"
				+ "					<img style=\"width: 30%;\" src=\"cid:logoImage\" />\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "			\r\n"
				+ "			<div style=\"padding-bottom:30px;\">\r\n"
				+ "				<div >\r\n"
				+ "					<div >\r\n"
				+ "						<p style=\"font-weight: bold; font-size:22px;\">아이디 찾기</p>"
				+ "					</div>\r\n"
				+ "					<div style=\" font-size:15px; \">\r\n"
				+ "						<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							안녕하세요. 토르두스국립도서관입니다. <br>\r\n"
				+ "							아이디를 아래와 같이 알려드립니다.\r\n"
				+ "						</p>"
				+ "							<div  >\r\n"
				+ "								<div >\r\n"
				+ "									<input type=\"text\"   value=\" "+username+" \"  style=\"font-size:30px; color: royalblue; text-align:center;     padding: 1px 2px;\">\r\n"
				+ "								</div>\r\n"
				+ "							</div>\r\n"
				+ "							<hr>\r\n"
				+ "							\r\n"
				+ "							<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							감사합니다.<br>\r\n"
				+ "							본 메일은 발신 전용이므로 회신되지 않습니다.<br>\r\n"
				+ "							문의사항이나 불편한 사항은 사이트 아래 관리자 연락처를 통해 연락주시기 바랍니다.</p>\r\n"
				+ "						</div>\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</div>";

		
		return a;
	}
	
	
	public String 비밀번호초기화이메일_내용(String passwordResetLink) {
		
		String a = 
				"<div style=\"display:block;\">\r\n"
				+ "\r\n"
				+ "	<div >\r\n"
				+ "		<div style=\"margin-top: 50px; max-width:800px;\">\r\n"
				+ "\r\n"
				+ "			<div >\r\n"
				+ "				<div style=\"text-align: center;\">\r\n"
				+ "					<!-- 1100 x 634px; -->\r\n"
				+ "					<img alt=\"\" style=\"width: 30%;\" src=\"/img_custom/로고수직_완성1.png\" />\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "			\r\n"
				+ "			<div style=\"padding-bottom:30px;\">\r\n"
				+ "				<div >\r\n"
				+ "					<div >\r\n"
				+ "						<!-- <h1 style=\" display: block; font-size: 2em; margin-block-start: 0.67em; margin-block-end: 0.67em; margin-inline-start: 0px; margin-inline-end: 0px; font-weight: bold;\">\r\n"
				+ "						이메일인증</h1> -->\r\n"
				+ "						<p style=\"font-weight: bold; font-size:22px;;\">비밀번호 재설정</p>\r\n"
				+ "					</div>\r\n"
				+ "					<div >\r\n"
				+ "						<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							안녕하세요. 토르두스국립도서관입니다. <br>\r\n"
				+ "							<span class=\"hightile-a\" style=\" display: inline-block; background: #ff7200; color: #fff; border-radius: 2px; padding-left: 6px; padding-right: 6px; margin-left: 3px; margin-right: 3px;\">\r\n"
				+ "							아래와 링크를 통해 비밀번호를 재설정</span>할 수 있습니다.<br>\r\n"
				+ "							\r\n"
				+ "							링크를 클릭해 주세요.<br>\r\n"
				+ "							<span class=\"underLine-a\" style=\"text-decoration: underline; font-weight: bold;\">\r\n"
				+ "							이 링크는 전송 시각으로부터 12시간이 지나면 만료</span>되오니 참고바랍니다.\r\n"
				+ "						</p>\r\n"
				+ "					\r\n"
				+ "						<div>\r\n"
				+ "							<a href = \" "+passwordResetLink+"\" >"+passwordResetLink+"</a>\r\n"
				+ "						</div>\r\n"
				+ "							<hr>\r\n"
				+ "							\r\n"
				+ "							<p style=\"display: block; margin-block-start: 1em; margin-block-end: 1em; margin-inline-start: 0px; margin-inline-end: 0px;\">\r\n"
				+ "							감사합니다.<br>\r\n"
				+ "							본 메일은 발신 전용이므로 회신되지 않습니다.<br>\r\n"
				+ "							문의사항이나 불편한 사항은 사이트 아래 관리자 연락처를 통해 연락주시기 바랍니다.</p>\r\n"
				+ "						</div>\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</div>"
				+ "";
		
		return a;
	}
}

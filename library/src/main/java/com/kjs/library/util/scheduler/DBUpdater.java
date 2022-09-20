package com.kjs.library.util.scheduler;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kjs.library.domain.common.VisitorCountRepository;
import com.kjs.library.domain.common.VisitorInforRepository;
import com.kjs.library.service.common.CommonService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DBUpdater {

	
	private final CommonService commonService;
	
	//@Scheduled(cron = "0/3 * * * * *") //3초마다 실행됨
	@Scheduled(cron = "0 1 0 ? * *") //매일 0시 1분에 실행됨
	public void up() {
		
		commonService.접속기록갱신();
		log.debug("접속 기록이 갱신되었습니다.");

	}
}

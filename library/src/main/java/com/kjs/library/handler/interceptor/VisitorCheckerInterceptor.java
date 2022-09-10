package com.kjs.library.handler.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.kjs.library.domain.comment.CommentRepository;
import com.kjs.library.domain.comment.CommentSQRepository;
import com.kjs.library.domain.community.BoardFreeRepository;
import com.kjs.library.domain.community.BoardNoticeRepository;
import com.kjs.library.domain.community.SingleQuestionRepository;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.service.common.DomainCommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//사용 안 됨
@Slf4j
public class VisitorCheckerInterceptor implements HandlerInterceptor {
	
	///컨트롤러 실행 후, 뷰 렌더링 후 실행된다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			//log.info("인터셉터가 실행되었습니다. ");
	}
	
}

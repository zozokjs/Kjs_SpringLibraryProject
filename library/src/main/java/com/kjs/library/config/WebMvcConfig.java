package com.kjs.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${file.path.upload_imageTitle}")
	private String upload_imageTitleFolder; //위 어노테이션에 담긴 위치를 변수에 담는다
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);

		registry.addResourceHandler("/upload/**")
		//명시한 URL 패턴에 일치하는 요청이 발생하면 이 핸들러가 동작함.
		//JSP 페이지에서 /upload/~~이런 패턴이 나오면 발동됨.
		.addResourceLocations("file:///"+upload_imageTitleFolder)
		//이 메소드로 정적 콘텐츠를 제공할 리소스 위치를 추가함.
		.setCachePeriod(3600)
		//이미지를 빨리 호출하기 위해 브라우저가 이미지를 HDD에 임시 저장하는 걸 캐싱이라고 하는데
		//이 캐싱 기간을 설정함
		.resourceChain(true) //이것을 false로 한다면, 요청할때마다 브라우저가 자원을 새로 다운로드함
		.addResolver(new PathResourceResolver());
	
	
	}
	
}

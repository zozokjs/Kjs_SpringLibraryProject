package com.kjs.library.service.common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.web.dto.book.ImageDto;

import lombok.NoArgsConstructor;

//어딘든지 공통으로 사용되는 서비스 모음
@Service
public class CommonService {

	
	//책 타이틀 이미지만 모아두는 곳
	@Value("${file.path.upload_imageTitle}")
	private String uploadTitleFolder;
	
	//유저 프로필 이미지만 모아두는 곳
	@Value("${file.path.upload_imageProfile}")
	private String uploadProfileFolder;

	
	/**사진만 저장함(책 타이틀 이미지, 유저 프로필 이미지 등)
	 * */
	@Transactional
	public String 사진저장(ImageDto imageDto, String imagePath) {
		
		String imageFileName = "";
		
		//첨부한 이미지가 없다면 
		if( imageDto.getFile().isEmpty() || imageDto.getFile() == null ) {
			imageFileName ="noTitleImage.jpg";
		}
		//첨부한 이미지가 있을 때
		else {
			
			//유일성이 보장되는 id 생성
			UUID uuid = UUID.randomUUID();
			
			//도서 타이틀 이미지일 때
			if(imagePath == "imageTitle") {
				
				imageFileName = uuid+"_"+imageDto.getFile().getOriginalFilename();
				Path imageFilePath =Paths.get(uploadTitleFolder+imageFileName);

				try {
					//imageFilePath 경로에 DTO에 담긴 파일을 가져와서 byte 형태로 저장시킴
					Files.write(imageFilePath, imageDto.getFile().getBytes());
					System.out.println("이미지 쓰기 성공");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//유저 프로필 사진일 때 
			else if(imagePath == "imageProfile") {
				imageFileName = uuid+"_"+imageDto.getFile().getOriginalFilename();
				Path imageFilePath =Paths.get(uploadProfileFolder+imageFileName);

				try {
					//imageFilePath 경로에 DTO에 담긴 파일을 가져와서 byte 형태로 저장시킴
					Files.write(imageFilePath, imageDto.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("이미지 이름 : " +imageFileName );
				
		}
		
		return imageFileName; //저장된 사진 이름을 리턴함
			
	}
	
	
	/**시작과 끝 페이지 구함
	 *  (Entity, 표시할 버튼 갯수)
	 * */
	@Transactional(readOnly = true)
	public Map<String, Integer> 시작끝페이지구하기(Page<?> page, int pageButtonLength) {
		
		int pageCurrent = page.getPageable().getPageNumber();//현재 페이지
		int pageTotal = page.getTotalPages(); //전체 페이지 수
		//int pageButtonLength = 10; //한 번에 표시할 페이지 버튼 수
		int pageStart = 0; //페이지 버튼 처음 숫자
		int pageEnd = 0; //페이지 버튼 마지막 숫자
		
		/**현재 페이지가 11일 때, 버튼 숫자가 1 ~ 10으로 나타나는 것에 대한 처리
		pageCurrent를 10으로 나눴을 때의 나머지가 0일 때 */
		if(Math.floorMod(pageCurrent, 10) == 0){
			pageStart = ( pageCurrent  / pageButtonLength ) * pageButtonLength + 1;
			
			/**마지막 페이지에 대한 처리
			현재 페이지가 전체 페이지와 같을 때 
			마지막 페이지 = 전체페이지*/
			if(pageCurrent + 1 == pageTotal) {
				pageEnd = pageTotal;
				//System.out.println("-마지막 페이지가 있습니다.");
			}
			/**현재페이지가 전체페이지보다 작을 때
			 * 예) 현재페이지 : 24, 전체페이지 : 22 
			 * 마지막 페이지 = 24 */
			else if(pageCurrent + 1 < pageTotal){
				pageEnd = pageStart + pageButtonLength - 1;
				
				/**마지막페이지가 전체페이지보다 크거나 같을 때
				 * 예) 마지막페이지 : 30, 전체페이지 : 24 
				 * 마지막 페이지 = 전체페이지* */
				if(pageEnd >= pageTotal) {
					pageEnd = pageTotal;
				}
			}
		}else {
			pageStart = ((pageCurrent - 1) / pageButtonLength ) * pageButtonLength + 1; 
			/**마지막 페이지에 대한 처리
			현재 페이지가 전체 페이지와 같을 때 */
			if(pageCurrent + 1 == pageTotal) {
				pageEnd = pageTotal;
			}else if(pageCurrent + 1 < pageTotal){
				pageEnd = pageStart + pageButtonLength - 1;
				
				/**마지막페이지가 전체페이지보다 크거나 같을 때
				 * 예) 마지막페이지 : 30, 전체페이지 : 24 
				 * 마지막 페이지 = 전체페이지* */
				if(pageEnd >= pageTotal) {
					pageEnd = pageTotal;
				}
			}
		}
		
		//페이징 값 체크
		System.out.println("pageCurrent : "+pageCurrent +"/ pageTotal : "+pageTotal+" /pageButtonLength : "+pageButtonLength);
		System.out.println("startPage : "+pageStart +"/ endPage : "+pageEnd);
		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("pageStart", pageStart);
		pageMap.put("pageEnd", pageEnd);
		pageMap.put("pageCurrent", pageCurrent);
		pageMap.put("pageTotal", pageTotal);
		pageMap.put("pageButtonLength", pageButtonLength);
		
		return pageMap;
		
	}
	

	/**로그인 안 되어 있으면 throws 됨*/
	public void 로그인검사(PrincipalDetails principalDetails) {
		if(principalDetails == null) {
			throw new CustomValidationException("로그인 해야합니다", null);
		}
	}
	
	/**(PrincipalDetails, 최소권한)
	 * 최소권한이 SESEO인데, PrincipalDetails가 USER 권한이면 throws 됨*/
	public void 권한검사(PrincipalDetails principalDetails, RoleType roleType) {
		
		//System.out.println("보유권한 "+principalDetails.getUser().getRoleType());
		//System.out.println("요구권한 "+roleType);
		
		//요구 권한이 USER일 때
		if(roleType.equals(RoleType.USER)) {
			//보유 권한이 USER, SASEO, ADMIN이 아니면 throws
			if(    !(     (principalDetails.getUser().getRoleType().equals(RoleType.USER))  
					     || (principalDetails.getUser().getRoleType().equals(RoleType.SASEO)) 
					     || (principalDetails.getUser().getRoleType().equals(RoleType.ADMIN)) 
					  )   
				){
				System.out.println(roleType+" 권한이 있어야 합니다");
				throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);

			}
		}
		else if(roleType.equals(RoleType.SASEO)) {
			if(    !(    (principalDetails.getUser().getRoleType().equals(RoleType.SASEO))  
					    || (principalDetails.getUser().getRoleType().equals(RoleType.ADMIN)) 
					   )   
			    ){
				System.out.println(roleType+" 권한이 있어야 합니다");
				throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);

			}
		}
		else if(roleType.equals(RoleType.ADMIN)) {
			if(    !( (principalDetails.getUser().getRoleType().equals(RoleType.ADMIN)) )   ){
				System.out.println(roleType+" 권한이 있어야 합니다");
				throw new CustomValidationException("권한이 없습니다. 등록할 수 없습니다", null);

			}
		}
		
		/**
		  1. CustomValidationException 실행. 이것의 생성자에는 String과 ErrorMap이 전달되어야 함.
		  2. CustomValidationException의 부모클래스가 String을 전달 받고 get한다.
		  3. ControllerExceptionHandler에서 CustomValidationException을 @ExceptionHandler을 통해 핸들링하고 있으므로
		  이것을 잡아채게 되고
		  결과적으로 Script가 리턴됨.
		 * */
		
	}
	
	
	//List 받아서 공백 체크하고 새로운 List 리턴
	/*
	public List<?> List공백제거( List<?> originalList) {
		
		List<?> List공백제거됨 = null;
		
		for (int index = 0; index < originalList.size(); index ++) {

			if(originalList.get(index) == "") {
				
			}else {
				List공백제거됨.add(originalList.get(index)); //여기서 오류 남
			}
		}
		return null;
	}
	*/
	
	
	
}

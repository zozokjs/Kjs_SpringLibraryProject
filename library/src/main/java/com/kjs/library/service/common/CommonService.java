package com.kjs.library.service.common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kjs.library.web.dto.book.ImageDto;

//어딘든지 공통으로 사용되는 서비스 모음
@Service
public class CommonService {

	//책 타이틀 이미지만 모아두는 곳
	@Value("${file.path.upload_imageTitle}")
	private String uploadTitleFolder;
	
	//유저 프로필 이미지만 모아두는 곳
	@Value("${file.path.upload_imageProfile}")
	private String uploadProfileFolder;
	
	
	// 책 타이틀 이미지, 유저 프로필 이미지 등...
	//사진만 저장함.
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

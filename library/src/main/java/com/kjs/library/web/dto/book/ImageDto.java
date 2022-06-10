package com.kjs.library.web.dto.book;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageDto {

	private MultipartFile file;
	
}

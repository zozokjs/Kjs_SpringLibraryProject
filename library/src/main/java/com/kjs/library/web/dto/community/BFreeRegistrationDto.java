package com.kjs.library.web.dto.community;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.web.dto.book.BookUpdateDto;

import lombok.Builder;
import lombok.Data;

@Data
public class BFreeRegistrationDto {
	
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	public BoardFree toEntity() {
		return BoardFree.builder()
		.title(title)
		.content(content)
		.build();
	}
	
}

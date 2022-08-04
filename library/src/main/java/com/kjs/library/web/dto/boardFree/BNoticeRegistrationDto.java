package com.kjs.library.web.dto.boardFree;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.kjs.library.domain.community.BoardFree;
import com.kjs.library.domain.community.BoardNotice;
import com.kjs.library.web.dto.book.BookUpdateDto;

import lombok.Builder;
import lombok.Data;

@Data
public class BNoticeRegistrationDto {
	
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	public BoardNotice toEntity() {
		return BoardNotice.builder()
		.title(title)
		.content(content)
		.build();
	}
	
}

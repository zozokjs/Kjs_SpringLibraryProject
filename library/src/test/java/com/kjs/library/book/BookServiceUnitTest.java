package com.kjs.library.book;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.lend.LendRepository;
import com.kjs.library.service.common.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

	//@Mock이라고 붙은 것을 주입함.
	@InjectMocks
	private CommonService commonService;
	
	@Mock
	private LendRepository lendRepository;
	
	//사용자가 대출한 목록을 표시해야 함
	public void 대출목록_테스트() {
		
		int userId = 1;
		Optional<Lend> lend = null;
		
		//stub
		when(lendRepository.findById(userId)).thenReturn(lend);
		
		//then
		
		
	}

}

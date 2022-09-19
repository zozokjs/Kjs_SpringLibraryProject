package com.kjs.library.user;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.service.UserService;
import com.kjs.library.util.openApi.IpSearch;


/**
 * 서비스만 테스트하기에 서비스 관련된 것만 메모리에 올린다.
 * 서비스 테스트하려면 Repository가 필요한데, 그게 있으면 db 연결이 필요하기에 결과적으로 서비스만 테스트하는 게 아니게 됨.
 * 그래서 MockitoExtension으로 가까 객체를 만든다.
 * **/
//@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

	/**
	 * UserService가 생성될 때 이 클래스 파일에서 @Mock으로 등록된 것들을 주입한다.
	 * UserService에 @Mock을 붙였으므로 그것에 필요한 Repository도 주입 필요함.
	 * **/
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private IpSearch ipSearch;
	
	//@Test
	public void 대출Test() {
			
			
		}
}

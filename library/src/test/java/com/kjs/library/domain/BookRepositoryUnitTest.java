package com.kjs.library.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.BookRepository;

//단위 테스트( db 관련된 bean만 ioc에 등록)

/**
 * @AutoConfigureTestDatabase(replace = Replace.ANY) 가짜 db로 테스트 함
 * @AutoConfigureTestDatabase(replace = Replace.NONE) 실제 DB로 테스트 함 
 * */

@Transactional	
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest //Repository 들을 모두 IOC에 등록해준다.
public class BookRepositoryUnitTest {

	//@DataJpaTest 안에 있는 @ExtendWith(SpringExtension.class) 때문에 이미 빈이 등록되어 있기 때문에
	//별도의 어노테이션을 붙일 필요 없음
	private BookRepository bookRepository;



}

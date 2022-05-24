package com.kjs.library.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//<오브젝트, FK 타입>
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//JPA QUERY METHOD 양식을 써서 메소드 생성함. db와 통신하려면 정해진 양식 써야 됨
	User findByUsername(String username);
}

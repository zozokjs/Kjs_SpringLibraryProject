package com.kjs.library.domain.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//<오브젝트, FK 타입>
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//JPA QUERY METHOD 양식을 써서 메소드 생성함. db와 통신하려면 정해진 양식 써야 됨
	User findByUsername(String username);
	
	/**비활성화된 계정 모음*/
	@Query(value = "SELECT * FROM user WHERE isEnabled IS FALSE", 
					countQuery = "SELECT count(*) FROM user WHERE isEnabled IS FALSE",
					nativeQuery = true)
	Page<User> findEnabledFalseUserList(Pageable pageable);

	
	/**해당 id가 비활성화 되었는지 확인함*/
	@Query(value="SELECT isEnabled FROM user WHERE id = :userId ", nativeQuery = true)
	Boolean findUserAccountEnable(int userId);
	
	
	/**해당 username의 로그인 실패 횟수를 확인함*/
	@Query(value="SELECT loginFailCount FROM user WHERE username = :username ", nativeQuery = true)
	Integer findLoginFailCount(String username);
}

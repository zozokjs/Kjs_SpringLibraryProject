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
	
	/** 비활성화된(비밀번호 틀린 횟수 5번 틀림 등) 계정 모음*/
	@Query(value = "SELECT * FROM User WHERE isEnabled IS FALSE", 
					countQuery = "SELECT count(*) FROM User WHERE isEnabled IS FALSE",
					nativeQuery = true)
	Page<User> findEnabledFalseUserList(Pageable pageable);

	
	/** 관리자에 의해 정지된 계정 모음*/
	@Query(value = "SELECT * FROM User WHERE roleType = \"NOT\" ", 
					countQuery = "SELECT count(*) FROM User WHERE roleType = \"NOT\" ",
					nativeQuery = true)
	Page<User> findRoleNotUserList(Pageable pageable);

	
	/** 회원 가입은 했는데 관리자 미승인 상태인 계정 모음*/
	@Query(value = "SELECT * FROM User WHERE roleType = \"YET\" ", 
			countQuery = "SELECT count(*) FROM User WHERE roleType = \"YET\" ",
			nativeQuery = true)
    Page<User> findSigninRequestUserList(Pageable pageable);
	

	/**해당 username의 로그인 실패 횟수를 확인함*/
	@Query(value="SELECT loginFailCount FROM User WHERE username = :username ", nativeQuery = true)
	Integer findLoginFailCount(String username);
	
	
	/**해당 email에 해당하는 username을 확인함*/
	@Query(value="SELECT username FROM User WHERE email = :email ", nativeQuery = true)
	String findByEmailToUsername(String email);
	
	
	/**해당 email과 username에 해당하는 값이 있는지 확인함*/
	@Query(value=""
			+ "SELECT "
			+ " case when id IS NULL then FALSE "
			+ "         when id IS NOT NULL then TRUE "
			+"  END AS result"
			+ " FROM User "
			+ " WHERE email = :email "
			+ " AND username = :username", nativeQuery = true)
	Integer findByEmailAndUsernameToExist(String username, String email);
	
	
	/**해당 passwordAuthCode에 해당하는 유저를 찾음
	 * 비밀번호 초기화 메일 전송할 때, (비밀번호 초기화를 요청한) 유저를 구분하는 유일한 값으로 사용됨
	 * */
	@Query(value="SELECT * FROM User WHERE passwordAuthCode = :passwordAuthCode", nativeQuery = true)
	User findByPasswordAuthCodeToUser(String passwordAuthCode);
	
}

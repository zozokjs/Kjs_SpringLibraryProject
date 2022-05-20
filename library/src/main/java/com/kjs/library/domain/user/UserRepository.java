package com.kjs.library.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//<오브젝트, FK 타입>
public interface UserRepository extends JpaRepository<User,Integer>{

}

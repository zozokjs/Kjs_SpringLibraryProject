package com.kjs.library.domain.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	
	@Query(value = "SELECT * FROM book ORDER BY id", 
			       countQuery = "SELECT count(*) FROM book",           
                   nativeQuery = true)
	Page<Book> findByAll(Pageable pageable);
	
	
	@Query(value = "SELECT * FROM book ORDER BY createDate DESC LIMIT 3" , nativeQuery = true)
	List<Book> findBookLimit3();

}

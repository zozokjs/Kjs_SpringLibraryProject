package com.kjs.library.domain.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	
	@Query(value = "SELECT * FROM Book WHERE useState = TRUE ORDER BY id", 
			       countQuery = "SELECT count(*) FROM Book WHERE useState = TRUE ",           
                   nativeQuery = true)
	Page<Book> findByAll(Pageable pageable);
	
	
	@Query(value = "SELECT * FROM Book WHERE useState = TRUE ORDER BY createDate DESC LIMIT 3" , nativeQuery = true)
	List<Book> findBookLimit3();
	
	/* 가능한 언어 
	 * 책제목 / 작가이름 / 출판사 / 출판년도 / 출판국가 / 언어  
	 * 
	 * */
	@Query(value=
				"SELECT * FROM "
				+ "(SELECT * FROM Book "
				+ " WHERE useState = TRUE) AS A"
				+ " WHERE A.writer LIKE %:bookSearchKeyWord% "
				+ " OR"
				+ " A.publish LIKE %:bookSearchKeyWord% "
				+ " OR"
				+ " A.title LIKE %:bookSearchKeyWord% ",
			countQuery =
					"SELECT count(*) FROM "
							+ "(SELECT * FROM Book "
							+ " WHERE useState = TRUE) AS A"
							+ " WHERE A.writer LIKE %:bookSearchKeyWord% "
							+ " OR"
							+ " A.publish LIKE %:bookSearchKeyWord% "
							+ " OR"
							+ " A.title LIKE %:bookSearchKeyWord% ",
			nativeQuery = true)
	Page<Book> findBookDataBySearch(String bookSearchKeyWord, Pageable pageable);
}

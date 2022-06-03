package com.kjs.library.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kjs.library.domain.user.User;

public interface BookRepository extends JpaRepository<Book, Integer>{

}

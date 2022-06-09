package com.kjs.library.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

}

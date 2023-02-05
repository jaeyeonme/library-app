package com.group.libraryapp.controller.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.libraryapp.service.book.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping("/book")
	public void saveBook() {
		bookService.saveBook();
	}
}

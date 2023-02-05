package com.group.libraryapp.service.book;

import org.springframework.stereotype.Service;

import com.group.libraryapp.repository.book.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	// private final BookMemoryRepository bookMemoryRepository = new BookMemoryRepository();
	private final BookRepository bookRepository;

	public void saveBook() {
		bookRepository.saveBook();
	}
}

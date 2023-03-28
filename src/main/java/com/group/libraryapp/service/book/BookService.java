package com.group.libraryapp.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final UserLoanHistoryRepository userLoanHistoryRepository;
	private final UserRepository userRepository;

	public void saveBook(BookCreateRequest request) {
		bookRepository.save(new Book(request.getName()));
	}

	public void loanBook(BookLoanRequest request) {
		// 1. 책 정보를 가져온다.
		Book book = bookRepository.findByName(request.getBookName())
			.orElseThrow(IllegalAccessError::new);

		// 2. 대출기록 정보를 확인해서 대출중인지 확인한다.
		// 3. 만약에 확인했는데 대출 중이라면 예외를 발생시킨다.
		if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
			// 대출중인 책이 있다면
			throw new IllegalArgumentException("진짜 대출되어 있는 책입니다.");
		}

		// 4. 유저 정보를 가져온다.
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);

		user.loanBook(book.getName());
	}

	public void returnBook(BookReturnRequest request) {
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);

		user.returnBook(request.getBookName());
	}
}

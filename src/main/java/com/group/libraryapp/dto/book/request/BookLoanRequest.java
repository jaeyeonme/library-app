package com.group.libraryapp.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookLoanRequest {

	private String userName;
	private String bookName;
}

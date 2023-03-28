package com.group.libraryapp.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookReturnRequest {

	private String userName;
	private String bookName;
}

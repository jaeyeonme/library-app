package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.group.libraryapp.domain.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoanHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	private String bookName;
	private boolean isReturn;

	public UserLoanHistory(User user, String bookName) {
		this.user = user; // User를 받아서 넣어준다.
		this.bookName = bookName;
		this.isReturn = false;
	}

	public void doReturn() {
		isReturn = true;
	}

	public String getBookName() {
		return this.bookName;
	}
}

package com.group.libraryapp.domain.user.loanhistory;

import org.jetbrains.annotations.NotNull;

import com.group.libraryapp.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoanHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@ManyToOne @NotNull
	@JoinColumn(nullable = false)
	private User user;

	@NotNull
	private String bookName;
	private boolean isReturn;

	public UserLoanHistory(User user, String bookName, boolean isReturn) {
		this.user = user; // User를 받아서 넣어준다.
		this.bookName = bookName;
		this.isReturn = isReturn;
	}

	public void doReturn() {
		isReturn = true;
	}

	public String getBookName() {
		return this.bookName;
	}
}

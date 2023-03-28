package com.group.libraryapp.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(nullable = false, length = 20, name = "name")
	private String name;
	private Integer age;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

	public User(String name, Integer age) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
		}
		this.name = name;
		this.age = age;
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void loanBook(String bookName) {
		this.userLoanHistories.add(new UserLoanHistory(this, bookName));
	}

	public void returnBook(String bookName) {
		UserLoanHistory targetHistory = this.userLoanHistories
			.stream().filter(userLoanHistory -> userLoanHistory.getBookName().equals(bookName))
			.findFirst()
			.orElseThrow(IllegalAccessError::new);
		targetHistory.doReturn();
	}
}

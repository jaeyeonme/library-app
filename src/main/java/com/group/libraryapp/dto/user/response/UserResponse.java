package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

	private long id;
	private String name;
	private Integer age;

	public UserResponse(long id, User user) {
		this.id = id;
		this.name = user.getName();
		this.age = user.getAge();
	}
}

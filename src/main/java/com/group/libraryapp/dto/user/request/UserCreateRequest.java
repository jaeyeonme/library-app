package com.group.libraryapp.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequest {

	private String name;
	private Integer age; // int

}

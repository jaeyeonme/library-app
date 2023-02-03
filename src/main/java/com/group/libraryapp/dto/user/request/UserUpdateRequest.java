package com.group.libraryapp.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {

	private long id;
	private String name;
}

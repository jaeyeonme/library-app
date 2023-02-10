package com.group.libraryapp.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceV2 {

	private final UserRepository userRepository;

	// 아래 있는 함수가 시작될 때 start transaction 을 해준다. (트랜잭션을 시작!)
	// 함수가 예외 없이 잘 끝났다면 commit
	// 혹시라도 문제가 있다면 rollback
	@Transactional
	public void saveUser(UserCreateRequest request) {
		userRepository.save(new User(request.getName(), request.getAge()));
		throw new IllegalArgumentException();
	}

	public List<UserResponse> getUsers() {
		return userRepository.findAll()
			.stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		// select * from user where id = ?;
		User user = userRepository.findById(request.getId())
			.orElseThrow(IllegalAccessError::new);

		user.updateName(request.getName());
	}

	@Transactional
	public void deleteUser(String name) {
		// SELECT * FROM user WHERE name = ?
		User user = userRepository.findByName(name)
			.orElseThrow(IllegalAccessError::new);
		userRepository.delete(user);
	}
}

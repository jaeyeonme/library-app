package com.group.libraryapp.controller.user;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final JdbcTemplate jdbcTemplate;

	@PostMapping("/user") // POST /user
	public void saveUser(@RequestBody UserCreateRequest request) {
		String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
		jdbcTemplate.update(sql, request.getName(), request.getAge());
	}

	@GetMapping("/fruit")
	public Fruit fruit() {
		return new Fruit("바나나", 2000);
	}

	@GetMapping("/user")
	public List<UserResponse> getUsers() {
		String sql = "SELECT * FROM user";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			return new UserResponse(id, name, age);
		});
	}

	@PutMapping("/user")
	public void updateUser(@RequestBody UserUpdateRequest request) {
		String readSql = "SELECT * FROM user WHERE id = ?";
		boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}

		String sql = "UPDATE user SET name = ? WHERE id = ?";
		jdbcTemplate.update(sql, request.getName(), request.getId());
	}

	@DeleteMapping("/user")
	public void deleteUser(@RequestParam String name) {
		String readSql = "SELECT * FROM user WHERE name = ?";
		boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}

		String sql = "DELETE FROM user WHERE name = ?";
		jdbcTemplate.update(sql, name);
	}
}

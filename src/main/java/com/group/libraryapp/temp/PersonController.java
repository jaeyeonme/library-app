package com.group.libraryapp.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {

	private final PersonService personService;

	@GetMapping("/test/person")
	public void testPerson() {
		personService.savePerson();
	}
}

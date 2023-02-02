package com.group.libraryapp.dto.calculator.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatorAddRequest {

	private final int number1;
	private final int number2;
}

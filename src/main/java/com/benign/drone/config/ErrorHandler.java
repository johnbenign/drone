package com.benign.drone.config;

import com.benign.drone.dto.response.ResponseDto;
import com.benign.drone.exception.BadRequestException;
import com.benign.drone.exception.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Configuration
public class ErrorHandler {

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDto misMatchErrorHandler(NotFoundException ex) {
		System.out.println("throwing this::::::::::::: " + ex.getMessage());
		return new ResponseDto(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDto handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		System.out.println("Method Argument not valid throwing....");

		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseDto(HttpStatus.BAD_REQUEST.value(), errorList.get(0));

	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDto illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		return new ResponseDto(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDto badRequestExceptionHandler(BadRequestException ex) {
		return new ResponseDto(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

}

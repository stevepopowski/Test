package com.ciena.sca.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ApiExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public void handleMethodArgumentNotValid (MethodArgumentNotValidException e, HttpServletResponse response) {
    	logger.error(e.getMessage());
    	try {
    		List<String> errorMessage = new ArrayList<String>();
            List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
            for (FieldError fieldError: fieldErrors) {
            	errorMessage.add("Error on field: " + fieldError.getField() + ", " + fieldError.getDefaultMessage());
            }
			response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage.toString());
		} catch (IOException e1) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleBadInput(HttpMessageNotReadableException e, HttpServletResponse response) {
    	logger.error(e.getMessage());
    	try {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getCause().getMessage());
		} catch (IOException e1) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler({MefApiException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleMefExceptions (MefApiException e, HttpServletResponse response) {
    	logger.error(e.getMessage());
    	try {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		} catch (IOException e1) {
			e.printStackTrace();
		}
    }

	@ExceptionHandler({MefApiNotFoundException.class})
	public void handleMefNotFoundExceptions (MefApiNotFoundException e, HttpServletResponse response) {
    	logger.error(e.getMessage());
    	try {
			response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		} catch (IOException e1) {
			e.printStackTrace();
		}
    }
	
	@ExceptionHandler({Exception.class})
	public void handleOtherErrors(Exception e, HttpServletResponse response) {
    	logger.error(e.getMessage());
    	try {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		} catch (IOException e1) {
			e.printStackTrace();
		}
    }
}

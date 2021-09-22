package com.bank.accountmanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsufficientBalanceExceptionController {
	
	   @ExceptionHandler(value = InsufficientBalanceException.class)
	   public ResponseEntity<Object> exception(InsufficientBalanceException exception) {
	      return new ResponseEntity<>("Cannot make the transaction ! Insufficient balance !", HttpStatus.NOT_FOUND);
	   }

}

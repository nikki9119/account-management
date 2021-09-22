package com.bank.accountmanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MaxWithdrawalLimitExceededExceptionController {
	
	   @ExceptionHandler(value = MaxWithdrawalLimitExceededException.class)
	   public ResponseEntity<Object> exception(MaxWithdrawalLimitExceededException exception) {
	      return new ResponseEntity<>("Transactionf failed! Maximum withdrawal limit ($10000) for 24 hours exceeded. ", HttpStatus.NOT_FOUND);
	   }

}

package com.keyrus.virtualStore.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The class catches the VirtualStoreException and show the error message in the response body.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(VirtualStoreException.class)
    protected ResponseEntity<ErrorMessage> handleVirtualStoreException(VirtualStoreException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.CONFLICT);
        errorMessage.setMessage(e.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }
}

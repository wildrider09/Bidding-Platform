package com.nitesh.biddingPlatform.api;

import com.nitesh.biddingPlatform.exceptions.InvalidEntryException;
import com.nitesh.biddingPlatform.exceptions.ResourceNotFoundException;
import com.nitesh.biddingPlatform.model.ApplicationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
class ErrorHandlerService extends ResponseEntityExceptionHandler {
    @Value("${api_doc_url}")
    private String details;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApplicationError> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(101);
        error.setMessage(exception.getMessage());
        error.setDetails(details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidEntryException.class)
    public ResponseEntity<ApplicationError> handleInvalidEntryException(InvalidEntryException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();
        error.setCode(101);
        error.setMessage(exception.getMessage());
        error.setDetails(details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
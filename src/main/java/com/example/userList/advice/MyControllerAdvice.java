package com.example.userList.advice;

import com.example.userList.exceptions.EmptyInputExceptions;
import com.example.userList.exceptions.NoHandlerFoundExceptions;
import com.example.userList.exceptions.NoUserFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyInputExceptions.class)
    public ResponseEntity<EmptyInputExceptions> handleEmptyField(EmptyInputExceptions emptyInputExceptions){
        return new ResponseEntity<EmptyInputExceptions>(emptyInputExceptions, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<EmptyInputExceptions> handleNullField(){
        EmptyInputExceptions emptyInputExceptions = new EmptyInputExceptions();
        emptyInputExceptions.setStatusCode("605");
        emptyInputExceptions.setStatusMessage("One or More fields are Missing");
        return new ResponseEntity<EmptyInputExceptions>(emptyInputExceptions, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<NoHandlerFoundExceptions> handleOtherUrl(){
        NoHandlerFoundExceptions noHandlerFoundExceptions = new NoHandlerFoundExceptions();
        noHandlerFoundExceptions.setStatusCode("610");
        noHandlerFoundExceptions.setStatusMessage("Incorrect Url");
        return new ResponseEntity<NoHandlerFoundExceptions>(noHandlerFoundExceptions, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoUserFoundExceptions.class)
    public ResponseEntity<NoUserFoundExceptions> handleWrongId(NoUserFoundExceptions noUserFoundExceptions){
        return new ResponseEntity<NoUserFoundExceptions>(noUserFoundExceptions, HttpStatus.NOT_FOUND);
    }

}

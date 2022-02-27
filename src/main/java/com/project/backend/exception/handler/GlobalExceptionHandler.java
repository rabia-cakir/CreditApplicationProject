package com.project.backend.exception.handler;

import com.project.backend.entity.AppError;
import com.project.backend.exception.IdentityNumberIsAlreadyExistException;
import com.project.backend.exception.PhoneNumberIsAlreadyExistException;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.repository.IAppErrorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    IAppErrorRepository appErrorRepository;

    public GlobalExceptionHandler(IAppErrorRepository appErrorRepository) {
        this.appErrorRepository = appErrorRepository;
    }

    private AppError creatingAppErrorObject(HttpStatus status, String message)
    {
        return AppError.builder()
                .message(message)
                .status(status.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppError> exceptionHandling(ResourceNotFoundException exception)
    {
        AppError appError=creatingAppErrorObject(HttpStatus.NOT_FOUND,exception.getMessage());
        appErrorRepository.save(appError);
        return new ResponseEntity<>(appError,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IdentityNumberIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<AppError> exceptionHandling(IdentityNumberIsAlreadyExistException exception)
    {
        AppError appError=creatingAppErrorObject(HttpStatus.CONFLICT,exception.getMessage());
        appErrorRepository.save(appError);
        return new ResponseEntity<>(appError,HttpStatus.CONFLICT);

    }

    @ExceptionHandler(PhoneNumberIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<AppError> exceptionHandling(PhoneNumberIsAlreadyExistException exception)
    {
        AppError appError=creatingAppErrorObject(HttpStatus.CONFLICT,exception.getMessage());
        appErrorRepository.save(appError);
        return new ResponseEntity<>(appError,HttpStatus.CONFLICT);

    }




}

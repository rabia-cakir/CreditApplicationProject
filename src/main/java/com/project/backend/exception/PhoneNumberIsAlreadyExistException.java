package com.project.backend.exception;

public class PhoneNumberIsAlreadyExistException extends RuntimeException{
    public PhoneNumberIsAlreadyExistException(String message) {
        super(message);
    }
}

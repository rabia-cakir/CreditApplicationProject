package com.project.backend.exception;

public class IdentityNumberIsAlreadyExistException extends RuntimeException{

    public IdentityNumberIsAlreadyExistException(String message) {
        super(message);
    }
}

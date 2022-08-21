package com.foxmindedjavaspring.university.exception;

public class UniversityDataAcessException extends RuntimeException {
    public UniversityDataAcessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

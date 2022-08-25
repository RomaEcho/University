package com.foxmindedjavaspring.university.exception;

public class UniversityDataAcessException extends RuntimeException {
    public UniversityDataAcessException(Throwable throwable, String message, 
            Object... params) {
        super(String.format(message.replace("{}", "%s"), params), throwable);
    }

}

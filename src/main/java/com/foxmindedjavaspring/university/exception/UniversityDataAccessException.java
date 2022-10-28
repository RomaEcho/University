package com.foxmindedjavaspring.university.exception;

public class UniversityDataAccessException extends RuntimeException {
    public UniversityDataAccessException(Throwable throwable, String message,
                                         Object... params) {
        super(String.format(message.replace("{}", "%s"), params), throwable);
    }

}

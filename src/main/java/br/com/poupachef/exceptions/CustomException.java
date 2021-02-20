package br.com.poupachef.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}

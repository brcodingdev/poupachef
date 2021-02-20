package br.com.poupachef.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<StandardError> generalError(CustomException ex) {
        StandardError error = new StandardError(ex.getHttpStatus().value(), ex.getMessage(),
                getTimeMillis());
        return ResponseEntity.status(ex.getHttpStatus().value()).body(error);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<StandardError> generalError(HttpClientErrorException ex) {
        StandardError error = new StandardError(ex.getRawStatusCode(), ex.getMessage(), getTimeMillis());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getRawStatusCode()).value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationError err = new ValidationError(getTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de validação");
        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> conversionError(MethodArgumentTypeMismatchException ex) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de conversão: " + ex.getMessage(), getTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    public static long getTimeMillis() {
        return Instant.now().toEpochMilli();
    }

}

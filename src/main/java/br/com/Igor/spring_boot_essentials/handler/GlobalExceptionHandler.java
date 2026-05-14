package br.com.Igor.spring_boot_essentials.handler;

import br.com.Igor.spring_boot_essentials.exceptions.ErrorResponse;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

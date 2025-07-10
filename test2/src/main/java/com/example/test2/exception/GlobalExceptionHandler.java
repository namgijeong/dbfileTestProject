package com.example.test2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.response.ExceptionResponse;
import com.example.test2.response.ExceptionCodeType;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FailFileOpen.class)
    public ResponseEntity<ExceptionResponse> handleFailFileOpen(FailFileOpen exception) {
        final ExceptionResponse response = ExceptionResponse.makeExceptionResponse(ExceptionCodeType.FAIL_FILE_OPEN, "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(WrongFileExtension.class)
    public ResponseEntity<ExceptionResponse> handleFailFileOpen(WrongFileExtension exception) {
        log.warn("파일 오류 글로벌 익셉션에 들어옴 ");
        final ExceptionResponse response = ExceptionResponse.makeExceptionResponse(ExceptionCodeType.WRONG_FILE_EXTENSION, "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

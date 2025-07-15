package com.example.test2.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.example.test2.response.ExceptionResponse;
import com.example.test2.response.ResponseBase;
import com.example.test2.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FailFileOpen.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleFailFileOpen(FailFileOpen exception) {
        final ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_FILE_OPEN, ""));
        return ResponseEntity.status(HttpStatus.OK).body(response);

//        final ExceptionResponse response = ExceptionResponse.makeExceptionResponse(ExceptionCodeType.FAIL_FILE_OPEN, "");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(WrongFileExtension.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleWrongFileExtension(WrongFileExtension exception) {
        log.warn("파일 오류 글로벌 익셉션에 들어옴 ");
        final ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.WRONG_FILE_EXTENSION, ""));
        return ResponseEntity.status(HttpStatus.OK).body(response);
//        final ExceptionResponse response = ExceptionResponse.makeExceptionResponse(ExceptionCodeType.WRONG_FILE_EXTENSION, "");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("아이디, 비밀번호 글로벌 익셉션에 들어옴 ");

        /*
            @valid에서 직접 지정한 메시지를 가져오기 위해서는
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
         */

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        List<String> errorMessageList = new ArrayList<>();
        for (ObjectError error : errors) {
            errorMessageList.add(error.getDefaultMessage());
        }
        final ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, errorMessageList));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

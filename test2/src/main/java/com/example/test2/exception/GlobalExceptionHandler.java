package com.example.test2.exception;

import java.util.ArrayList;
import java.util.List;

import com.example.test2.data.dto.LoginField;
import com.example.test2.data.dto.UserResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.example.test2.response.ResponseBase;
import com.example.test2.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * dbfile 열기 실패일때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(FailFileOpen.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleFailFileOpen(FailFileOpen exception) {
        ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_FILE_OPEN, ""));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * .dbfile 확장자가 아닌 파일을 올릴때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(WrongFileExtension.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleWrongFileExtension(WrongFileExtension exception) {
        log.warn("파일 오류 글로벌 익셉션에 들어옴 ");
        ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.WRONG_FILE_EXTENSION, ""));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * LoginUserDTO valid 통과못할때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBase<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("아이디, 비밀번호 글로벌 익셉션에 들어옴 ");

        /*
            @valid에서 직접 지정한 메시지를 가져오기 위해서는
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
            => 하지만 이는 필드에러와 글로벌 에러를 포함한다.

            필드에러(FieldError) => 특정 필드 값에 대한 유효성 검증 실패
            글로벌에러(ObjectError) => 객체 전체 또는 필드 조합에 대한 검증 실패
         */

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        List<UserResultDTO> errorMessageList = new ArrayList<>();
        for (ObjectError error : errors) {
            UserResultDTO userResultDTO = new UserResultDTO();
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
//                if (fieldError.getField().equals("id")) { //id field @valid가 걸리면
//                    userResultDTO.setFailText("id");
//                } else if (fieldError.getField().equals("pwd")) { //pwd field @valid가 걸리면
//                    userResultDTO.setFailText("pwd");
//                }

                userResultDTO.setLoginField(LoginField.from(fieldError.getField()));

                userResultDTO.setExceptionMessage(fieldError.getDefaultMessage());
            } else {
                userResultDTO.setExceptionMessage(error.getDefaultMessage());
            }

            errorMessageList.add(userResultDTO);
        }
        ResponseBase<ErrorResponse> response = ResponseBase.makeResponseBase(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, errorMessageList));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package com.example.test3.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dto.LoginField;
import com.example.test3.data.dto.UserResultDTO;
import com.example.test3.response.ErrorResponse;
import com.example.test3.response.ResponseBase;
import com.example.test3.utility.Utility;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * dbfile 열기 실패일때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(FailFileOpen.class)
    public ResponseEntity<ResponseBase<ErrorResponse<String>>> handleFailFileOpen(FailFileOpen exception) {
        return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_FILE_OPEN, ""));

    }

    /**
     * .dbfile 확장자가 아닌 파일을 올릴때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(WrongFileExtension.class)
    public ResponseEntity<ResponseBase<ErrorResponse<String>>> handleWrongFileExtension(WrongFileExtension exception) {
        log.warn("파일 오류 글로벌 익셉션에 들어옴 ");
        return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.WRONG_FILE_EXTENSION, ""));

    }

    /**
     * LoginUserDTO valid 통과못할때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBase<ErrorResponse<UserResultDTO>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("아이디, 비밀번호 글로벌 익셉션에 들어옴 ");

        /*
            @valid에서 직접 지정한 메시지를 가져오기 위해서는
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
            => 하지만 이는 필드에러와 글로벌 에러를 포함한다.

            필드에러(FieldError) => 특정 필드 값에 대한 유효성 검증 실패
            글로벌에러(ObjectError) => 객체 전체 또는 필드 조합에 대한 검증 실패
         */

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        ObjectError error = errors.get(0);
        UserResultDTO userResultDTO = new UserResultDTO();
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;

            //문자열 필드이름대로 enum 세팅
            userResultDTO.setLoginField(LoginField.findLoginFieldEnum(fieldError.getField()));
            userResultDTO.setExceptionMessage(fieldError.getDefaultMessage());
        }

//        List<UserResultDTO> errorMessageList = new ArrayList<>();
//
//        //loginField를 이미 맞닥뜨렸는지 판단하기 위해 카운트를 저장
//        Map<LoginField, Integer> loginFieldCountMap = new HashMap<>();
//
//        //loginField 상수 개수만큼 map에 세팅해준다
//        LoginField[] loginFields = LoginField.values();
//        for (LoginField loginField : loginFields) {
//            loginFieldCountMap.put(loginField, 0);
//        }
//
//        for (ObjectError error : errors) {
//            UserResultDTO userResultDTO = new UserResultDTO();
//            if (error instanceof FieldError) {
//                FieldError fieldError = (FieldError) error;
//
//                // 아이디나 비밀번호 필드 관련 메시지를 한번도 뽑지 않았다면
//                if (loginFieldCountMap.get(LoginField.findLoginFieldEnum(fieldError.getField())) == 0) {
//                    //카운트 저장
//                    loginFieldCountMap.put(LoginField.findLoginFieldEnum(fieldError.getField()), 1);
//                    //문자열 필드이름대로 enum 세팅
//                    userResultDTO.setLoginField(LoginField.findLoginFieldEnum(fieldError.getField()));
//                    userResultDTO.setExceptionMessage(fieldError.getDefaultMessage());
//
//                    errorMessageList.add(userResultDTO);
//                }
//            }
//
////            } else { //필드에러가 아닌 글로벌 에러 - 지금으로썬 쓸일 없다.
////                userResultDTO.setExceptionMessage(error.getDefaultMessage());
////                errorMessageList.add(userResultDTO);
////            }
//
//
//        }

        //log.warn("errorMessageList : "+errorMessageList);
        //return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, errorMessageList));

        return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, userResultDTO));
    }
}

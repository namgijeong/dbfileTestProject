package com.example.test3.exception;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dto.LoginField;
import com.example.test3.data.dto.ProcessResultDTO;
import com.example.test3.response.ErrorResponse;
import com.example.test3.response.ResponseBase;
import com.example.test3.utility.Utility;
import com.example.test3.data.dto.ProcessResultDTO;


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
    public ResponseEntity<ResponseBase<ErrorResponse<ProcessResultDTO>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("아이디, 비밀번호 글로벌 익셉션에 들어옴 ");

        /*
            @valid에서 직접 지정한 메시지를 가져오기 위해서는
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
            => 하지만 이는 필드에러와 글로벌 에러를 포함한다.

            필드에러(FieldError) => 특정 필드 값에 대한 유효성 검증 실패
            글로벌에러(ObjectError) => 객체 전체 또는 필드 조합에 대한 검증 실패
         */

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();

        // 현재 요청 URL을 확인하여 로그인/회원가입을 구분
        //RequestContextHolder => Spring에서 현재 HTTP 요청의 컨텍스트를 저장하고 있는 클래스
        //RequestContextHolder.getRequestAttributes() => RequestAttributes 객체를 반환
        //ServletRequestAttributes => RequestAttribute의 자식 클래스
        //ServletRequestAttributes => HttpServletRequest를 담고 있는 객체
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //getRequestURI() 메서드 => 현재 HTTP 요청의 URI를 반환
        String requestURI = request.getRequestURI();
        ObjectError error = errors.get(0);
        ProcessResultDTO processResultDTO = new ProcessResultDTO();
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;

            //문자열 필드이름대로 enum 세팅
            //processResultDTO.setErrorField(fieldError.getDefaultMessage(),LoginField.findLoginFieldEnum(fieldError.getField()));
            processResultDTO.setErrorMessage(LoginField.findLoginFieldEnum(fieldError.getField()).getMessage());
        }

        log.info("processResultDTO : "+processResultDTO);
        log.info("Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO)) : "+Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO)));
        return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO));
    }
}

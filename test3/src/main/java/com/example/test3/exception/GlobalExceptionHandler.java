package com.example.test3.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.test3.data.dto.RegisterField;


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
     * RegisterUserDTO valid 통과 못할때
     * @param exception
     * @return ResponseBase 비정상응답이라는 내용
     */
    //Spring의 @ExceptionHandler는 리플렉션 기반 자동 호출
    //리플렉션(reflection) => 코드가 자기 자신(클래스, 메서드, 필드 등)을 런타임에 분석하고 조작할 수 있는 기능
    //Java에서는 런타임(즉, 리플렉션 사용하는 시점)에는 제네릭 타입 정보가 모두 지워지기 때문
    //<?>는 제네릭 타입 선언이 아니라, 타입 사용 시에 "어떤 타입이든 상관없음"이라는 의미이기 때문에 런타임에서도 안전하게 사용
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ResponseBase<ErrorResponse<?>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("글로벌 익셉션에 들어옴 ");

        List<ObjectError> errors = exception.getBindingResult().getAllErrors();

        // 현재 요청 URL을 확인하여 로그인/회원가입을 구분
        //RequestContextHolder => Spring에서 현재 HTTP 요청의 컨텍스트를 저장하고 있는 클래스
        //RequestContextHolder.getRequestAttributes() => RequestAttributes 객체를 반환
        //ServletRequestAttributes => RequestAttribute의 자식 클래스
        //ServletRequestAttributes => HttpServletRequest를 담고 있는 객체
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //getRequestURI() 메서드 => 현재 HTTP 요청의 URI를 반환
        String requestURI = request.getRequestURI();

        log.warn("requestURI : "+requestURI);

        List<ProcessResultDTO> processResultDTOList = new ArrayList<>();
        ProcessResultDTO processResultDTO = new ProcessResultDTO();
        boolean isRegister = false;

        switch (requestURI) {
            case "/register/register_check":
                // 모든 필드별 에러메시지를 출력- 각 필드별로 에러메시지는 하나만
                processResultDTOList = makeRegisterErrorMessageList(errors);
                log.warn("processResultDTOList : "+processResultDTOList);
                isRegister = true;
                break;

            case "/register/update_check":
                // 모든 필드별 에러메시지를 출력- 각 필드별로 에러메시지는 하나만
                processResultDTOList = makeUpdateErrorMessageList(errors);
                log.warn("processResultDTOList : "+processResultDTOList);
                isRegister = true;
                break;

            case "/login/login_check":
                // 아이디나 비밀번호중 하나만 랜덤으로 출력
                processResultDTO = makeLoginErrorMessage(errors);
                break;

            default:
        }

        if (isRegister) {
            log.warn("Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_REGISTER_VALID, processResultDTOList)) : "+Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_REGISTER_VALID, processResultDTOList)));
            return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_REGISTER_VALID, processResultDTOList));
        }

        log.info("Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO)) : "+Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO)));
        return Utility.makeResponseEntity(false, ErrorResponse.makeErrorResponse(ExceptionCodeType.FAIL_LOGIN_VALID, processResultDTO));
    }


    /*
            @valid에서 직접 지정한 메시지를 가져오기 위해서는
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
            => 하지만 이는 필드에러와 글로벌 에러를 포함한다.

            필드에러(FieldError) => 특정 필드 값에 대한 유효성 검증 실패
            글로벌에러(ObjectError) => 객체 전체 또는 필드 조합에 대한 검증 실패
     */
    public List<ProcessResultDTO> makeRegisterErrorMessageList(List<ObjectError> errors) {

        List<ProcessResultDTO> processResultDTOList = new ArrayList<>();
        //registerField를 이미 맞닥뜨렸는지 판단하기 위해 카운트를 저장
        Map<RegisterField, Integer> registerFieldCountMap = new HashMap<>();

        //RegisterField 상수 개수만큼 map에 세팅해준다
        RegisterField[] registerFields = RegisterField.values();
        for (RegisterField registerField : registerFields) {
            registerFieldCountMap.put(registerField, 0);
        }

        log.warn("registerFieldCountMap :"+registerFieldCountMap);
        for (ObjectError error : errors) {
            ProcessResultDTO processResultDTO = new ProcessResultDTO();
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                // 같은 종류의 필드 관련 메시지를 한번도 뽑지 않았다면 => ex 아이디 필드를 처음맞닥뜨리면
                log.warn("전체 에러메시지들 목록에서 : "+error.getDefaultMessage());
                log.warn("egisterFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())) : "+registerFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())));
                if (registerFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())) == 0) {
                    registerFieldCountMap.put(RegisterField.findRegisterFieldEnum(fieldError.getField()), 1);
                    
                    //만약 아이디 필드면 valid에서 설정한 고유메시지를 넣는다 => RegisterField ID로만 들어가기 때문에 분간이 안됨
                    //name을 해야지 enum을 문자열로 변환
                    if (RegisterField.ID.name().equalsIgnoreCase(fieldError.getField())) {
                        log.warn("error.getDefaultMessage() : "+error.getDefaultMessage());
                        processResultDTO.setErrorMessage(error.getDefaultMessage());
                    } else {
                        processResultDTO.setErrorMessage(RegisterField.findRegisterFieldEnum(fieldError.getField()).getMessage());
                    }

                    processResultDTOList.add(processResultDTO);
                } else {
                    continue;
                }

            }
            else {
                log.warn("혹시 글로벌 에러로 들어오니?");
            } //필드에러가 아닌 글로벌 에러 - 지금으로썬 쓸일 없다.
        }

        return processResultDTOList;
    }

    public List<ProcessResultDTO> makeUpdateErrorMessageList(List<ObjectError> errors) {

        List<ProcessResultDTO> processResultDTOList = new ArrayList<>();
        //registerField를 이미 맞닥뜨렸는지 판단하기 위해 카운트를 저장
        Map<RegisterField, Integer> registerFieldCountMap = new HashMap<>();

        //RegisterField 상수 개수만큼 map에 세팅해준다
        RegisterField[] registerFields = RegisterField.values();
        for (RegisterField registerField : registerFields) {
            registerFieldCountMap.put(registerField, 0);
        }

        log.warn("registerFieldCountMap :"+registerFieldCountMap);
        for (ObjectError error : errors) {
            ProcessResultDTO processResultDTO = new ProcessResultDTO();
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                // 같은 종류의 필드 관련 메시지를 한번도 뽑지 않았다면 => ex 아이디 필드를 처음맞닥뜨리면
                log.warn("전체 에러메시지들 목록에서 : "+error.getDefaultMessage());
                log.warn("egisterFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())) : "+registerFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())));
                if (registerFieldCountMap.get(RegisterField.findRegisterFieldEnum(fieldError.getField())) == 0) {
                    registerFieldCountMap.put(RegisterField.findRegisterFieldEnum(fieldError.getField()), 1);

                    //name을 해야지 enum을 문자열로 변환
                    processResultDTO.setErrorMessage(RegisterField.findRegisterFieldEnum(fieldError.getField()).getMessage());

                    processResultDTOList.add(processResultDTO);
                } else {
                    continue;
                }

            }
            else {
                log.warn("혹시 글로벌 에러로 들어오니?");
            } //필드에러가 아닌 글로벌 에러 - 지금으로썬 쓸일 없다.
        }

        return processResultDTOList;
    }

    public ProcessResultDTO makeLoginErrorMessage(List<ObjectError> errors) {
        ObjectError error = errors.get(0);
        ProcessResultDTO processResultDTO = new ProcessResultDTO();
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;

            //문자열 필드이름대로 enum 세팅
            processResultDTO.setErrorMessage(LoginField.findLoginFieldEnum(fieldError.getField()).getMessage());
        }
        else {} //필드에러가 아닌 글로벌 에러 - 지금으로썬 쓸일 없다.
        return processResultDTO;
    }
}


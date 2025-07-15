package com.example.test2.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.example.test2.exception.ExceptionCodeType;

@Getter
@Setter
@ToString
public class ErrorResponse<T> {
    private ExceptionCodeType exceptionCode;
    //List<String>으로 쓸 수도 있다.
    private T exceptionMessage;

    public ErrorResponse(ExceptionCodeType exceptionCode, T exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }


    /**
     * ExceptionResponse를 직접 new 하는것보다는 return 하는것이 좋다고 하셨다.
     *
     * @param exceptionCode
     * @param exceptionMessage
     * @return ErrorResponse
     */
    public static <T> ErrorResponse<T> makeErrorResponse(ExceptionCodeType exceptionCode, T exceptionMessage) {
        return new ErrorResponse<>(exceptionCode,exceptionMessage);
    }

}



package com.example.test2.response;

import com.example.test2.exception.ExceptionCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorResponse {
    private ExceptionCodeType exceptionCode;
    private String exceptionMessage;

    public ErrorResponse(ExceptionCodeType exceptionCode, String exceptionMessage) {
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
    public static ErrorResponse makeErrorResponse(ExceptionCodeType exceptionCode, String exceptionMessage) {
        return new ErrorResponse(exceptionCode,exceptionMessage);
    }

}

package com.example.test2.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionResponse extends BaseResponse {
    private ExceptionCodeType exceptionCode;
    private String exceptionMessage;

    public ExceptionResponse(ExceptionCodeType exceptionCode, String exceptionMessage) {
        super(false);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }


    //ExceptionResponse를 직접 new 하는것보다는 return 하는것이 좋다고 하셨다.
    public static ExceptionResponse makeExceptionResponse(ExceptionCodeType exceptionCode, String exceptionMessage) {
        return new ExceptionResponse(exceptionCode,exceptionMessage);
    }
}

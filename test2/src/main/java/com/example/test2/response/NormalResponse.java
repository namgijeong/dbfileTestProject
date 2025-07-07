package com.example.test2.response;

import lombok.Getter;

@Getter
public class NormalResponse<T> extends BaseResponse {

    //어떤 DTO 객체든 대입 할 수 있다.
    private T content;

    public NormalResponse (T content) {
        super(true);
        this.content = content;
    }

    //ExceptionResponse를 직접 new 하는것보다는 return 하는것이 좋다고 하셨다.
    public static <T> NormalResponse<T> makeNormalResponse(T content) {
        return new NormalResponse<> (content);
    }

}

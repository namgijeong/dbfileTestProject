package com.example.test3.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseBase<T> {

    //익셉션응답 객체든 , 정상적 응답객체든 플래그를 보고 정상 비정상인지 구분하기 위해
    private boolean isNormal;

    //어떤 DTO 객체든 대입할 수 있다.
    private T content;

    public ResponseBase(boolean isNormal, T content) {
        this.isNormal = isNormal;
        this.content = content;
    }

    /**
     * ResponseBase를 직접 new 하는것보다는 return 하는것이 좋다고 하셨다.
     *
     * @param "boolean isNormal" 정상인지,예외인지 플래그
     * @param "T content" 어떤 객체도 내용으로 담기 가능
     * @return ResponseBase
     */
    public static <T> ResponseBase<T> makeResponseBase(boolean isNormal, T content) {
        return new ResponseBase<>(isNormal, content);
    }

}

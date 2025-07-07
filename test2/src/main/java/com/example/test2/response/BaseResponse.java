package com.example.test2.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponse {
    //익셉션응답 객체든 , 정상적 응답객체든 플래그를 보고 정상 비정상인지 구분하기 위해
     private boolean isNormal;

    public BaseResponse(boolean isNormal) {
        this.isNormal = isNormal;
    }
}

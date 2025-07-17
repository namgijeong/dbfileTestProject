package com.example.test2.data.dto;

import java.util.Arrays;

public enum SuccessField {
    SUCCESS,
    FAIL;

    /**
     * 특정 문자열을 가지고 SuccessField enum에 존재하는 값이면
     * SuccessField 상수를 반환하여 멤버변수에 세팅하기 위함
     * 없으면 null을 반환하도록 하겠다.
     * @param value 문자열
     * @return SuccessField 상수 반환
     */
    public static SuccessField findSuccessFieldEnum(String value) {
        return Arrays.stream(SuccessField.values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(FAIL);
    }
}

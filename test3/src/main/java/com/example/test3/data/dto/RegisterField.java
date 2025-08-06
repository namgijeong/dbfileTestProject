package com.example.test3.data.dto;

import java.util.Arrays;

public enum RegisterField {
    //enum 매핑
    //enum 상수 하나당 자신의 인스턴스를 하나씩 만들어 public static final 필드로 공개
    DUPLICATED_ID("아이디가 중복되었습니다."),
    NOT_DUPLICATED_ID("아이디가 중복되지않았습니다."),
    ID("아이디를 입력해주세요."),
    PWD("패스워드를 입력해주세요"),
    NAME("이름을 입력해주세요."),
    LEVEL("레벨을 설정해주세요.");

    private final String message;

    //생성자에 "아이디가 틀렸습니다")가 들어간다
    private RegisterField(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }


    /**
     * 특정 문자열을 가지고 LoginField enum에 존재하는 값이면
     * LoginField 상수를 반환하여 멤버변수에 세팅하기 위함
     * 없으면 null을 반환하도록 하겠다.
     * @param value 문자열
     * @return LoginField 상수 반환
     */
    public static RegisterField findRegisterFieldEnum(String value) {
        return Arrays.stream(RegisterField.values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}

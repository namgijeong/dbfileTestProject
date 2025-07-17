package com.example.test2.data.dto;

import java.util.Arrays;

public enum LoginField {

    ID,
    PWD;

    /**
     * 특정 문자열을 가지고 LoginField enum에 존재하는 값이면
     * LoginField 상수를 반환하여 멤버변수에 세팅하기 위함
     * @param value 문자열
     * @return LoginField 상수 반환
     */
    public static LoginField from(String value) {
        //enumClass.values()는 enum에 정의된 모든 상수를 배열로 반환하는, enum에 자동으로 붙는 static 메서드
        return Arrays.stream(LoginField.values())

                //enum 상수.name()  -> enum 함수
                //그 enum 상수의 정의된 이름을 문자열로 반환
                //equalsIgnoreCase() 함수 -> String 클래스에 있는 메서드
                //문자열 내용이 같으면 true를 반환하고, 대소문자 차이는 무시
                //filter는 optional이 아닌 stream을 반환
                .filter(x -> x.name().equalsIgnoreCase(value))

                //findFirst() 함수 -> Java Stream API 함수
                //스트림에서 첫 번째 요소를 반환. 필터링 후, 첫 번째 일치 값. Optional<T> 반환
                .findFirst()

                //Java의 Optional 클래스에서 제공
                //Optional 안에 값이 있으면 그 값 반환, 값이 없으면 지정한 기본 값 반환.
                .orElse(ID);
    }

    /**
     * 특정 문자열을 가지고 LoginField enum에 존재하는 값이면
     * LoginField 상수를 반환하여 멤버변수에 세팅하기 위함
     * 없으면 null을 반환하도록 하겠다.
     * @param value 문자열
     * @return LoginField 상수 반환
     */
    public static LoginField findLoginFieldEnum(String value) {
        return Arrays.stream(LoginField.values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}

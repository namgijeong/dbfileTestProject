package com.example.test3.data.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션을 적용할 수 있는 위치
//ElementType.FIELD => 필드가 대상
@Target(ElementType.FIELD)
//class 파일에도 유지되고 JVM 실행 시에도 유지된다.
@Retention(RetentionPolicy.RUNTIME)
//검증할 검증 클래스 
@Constraint(validatedBy = IdDuplicatedCheckValidator.class)
public @interface DuplicatedId {

    //유효하지 않을 경우 반환할 메세지
    String message() default "아이디가 중복됩니다.";

    //유효성 검증이 진행될 그룹=> @Validated를 사용해서 그룹을 묶을 때 사용
    Class<?>[] groups() default {};
    //유효성 검증 시에 전달할 메타 정보
    Class<? extends Payload>[] payload() default {};
}

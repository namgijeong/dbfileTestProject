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
//실행하는 동안 어노테이션을 적용함
@Retention(RetentionPolicy.RUNTIME)
//검증할 검증 클래스 
@Constraint(validatedBy = IdDuplicatedCheckValidator.class)
public @interface IdDuplicatedCheck {
    
    String message() default "아이디가 중복됩니다.";

    Class<?>[] groups() default {};  // 반드시 선언해야 함
    Class<? extends Payload>[] payload() default {};  // 권장
}

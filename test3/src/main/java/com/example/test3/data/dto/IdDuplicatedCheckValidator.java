package com.example.test3.data.dto;

import com.example.test3.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class IdDuplicatedCheckValidator implements ConstraintValidator<DuplicatedId, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        ProcessResultDTO processResultDTO = userService.isIDDuplicated(value);

        log.info("isvalid에서의 processResultDTO: "+processResultDTO);
        //boolean은 getter 메소드 이름이 is
        if (processResultDTO.isSuccessFlag()) {
            log.info("custom validated : "+true);
            return true;
        }

        log.info("custom validated : "+false);

        //context.getDefaultConstraintMessageTemplate() => @interface의 message()를 읽어오는 역할
//        context.disableDefaultConstraintViolation(); // 기존 메시지 비활성화
//        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()) // 커스텀 메시지 지정
//                .addConstraintViolation(); // 메시지 추가
        return false;
    }
}

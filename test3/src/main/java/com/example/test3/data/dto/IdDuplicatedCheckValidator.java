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

        return false;
    }
}

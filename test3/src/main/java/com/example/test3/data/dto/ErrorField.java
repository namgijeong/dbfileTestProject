package com.example.test3.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorField {
    String exceptionMessage;
    LoginField loginField;
}

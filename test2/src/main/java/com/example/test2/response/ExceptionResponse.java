package com.example.test2.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String exceptionCode;
    private final String exceptionMessage;
}

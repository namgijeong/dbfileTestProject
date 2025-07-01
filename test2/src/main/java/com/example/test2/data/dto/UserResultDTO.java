package com.example.test2.data.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserResultDTO {
    /*1이면 성공, 0이면 실패*/
    private int successFlag;
    /*성공했다면 몇번째 줄인지*/
    private int successLine;
    /*실패했다면 몇번째 줄인지*/
    private int failLine;
    /*실패했다면 그 줄 내용이 무엇인지*/
    private String failText;
}

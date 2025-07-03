package com.example.test2.data.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import com.example.test2.data.dto.UserDtoBase;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member implements UserDtoBase {

    private String id;

    private String pwd;

    private String name;

    private String level;

    private String desc;

    private LocalDateTime regDate;
}

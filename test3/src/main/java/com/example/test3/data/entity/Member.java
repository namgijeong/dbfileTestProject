package com.example.test3.data.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.example.test3.data.dto.UserDtoBase;

@Getter
@Setter
@ToString
public class Member implements UserDtoBase {
    private String id;

    private String pwd;

    private String name;

    private String level;

    private String desc;

    private Timestamp regDate;
}

package com.example.test2.data.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.example.test2.data.dto.UserDtoBase;

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

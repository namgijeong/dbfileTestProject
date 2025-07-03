package com.example.test2.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.example.test2.data.dto.UserDtoBase;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@ToString
public class User implements UserDtoBase {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String level;

    /*desc 컬럼이름 자체가 예약어로 취급. 이스케이프 문자 사용.*/
    @Column(name = "\"desc\"")
    private String desc;

    @Column(nullable = false)
    private LocalDateTime regDate;

}

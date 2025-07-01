package com.example.test2.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user")
public class User {

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

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}

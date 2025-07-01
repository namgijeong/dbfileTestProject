package com.example.test2.data.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class UserDTO {
    private String id;

    private String pwd;

    private String name;

    private String level;

    private String desc;

    /*
        db에 timestamp로 넣기 위해
        String을 LocalDateTime으로 변환
    */
    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

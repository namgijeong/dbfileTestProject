package com.example.test2.data.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString
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

    private UserDTO(Builder builder){
        this.id = builder.id;
        this.pwd = builder.pwd;
        this.name = builder.name;
        this.level = builder.level;
        this.desc = builder.desc;
        this.regDate = builder.regDate;
    }

    public static class Builder{
        private String id;
        private String pwd;
        private String name;
        private String level;
        private String desc;
        private LocalDateTime regDate;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder pwd(String pwd){
            this.pwd = pwd;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder level(String level){
            this.level = level;
            return this;
        }

        public Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        public Builder regDate(LocalDateTime regDate){
            this.regDate = regDate;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(this);
        }
    }


}

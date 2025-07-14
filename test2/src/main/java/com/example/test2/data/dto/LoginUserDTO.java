package com.example.test2.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUserDTO {
    private String id;
    private String pwd;

    public LoginUserDTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}

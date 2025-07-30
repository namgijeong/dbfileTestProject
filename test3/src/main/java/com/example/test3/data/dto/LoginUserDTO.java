package com.example.test3.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUserDTO {
    @NotBlank(message = "아이디가 틀렸습니다.")
    @Size(min = 4, message = "아이디가 틀렸습니다.")
    private String id;

    @NotBlank(message = "비밀번호가 틀렸습니다.")
    @Size(min = 8, message = "비밀번호가 틀렸습니다.")
    private String pwd;

    public LoginUserDTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}

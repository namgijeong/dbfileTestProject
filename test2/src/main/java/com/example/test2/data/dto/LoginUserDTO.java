package com.example.test2.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUserDTO {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 4, message = "아이디는 4글자 이상입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, message = "비밀번호는 8글자 이상입니다.")
    private String pwd;

    public LoginUserDTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}

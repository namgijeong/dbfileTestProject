package com.example.test3.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
/**
 *  검색하는 정보를 담을 용도
 */
public class RegisterUserDTO {
    @NotBlank(message = "아이디는 4글자 이상이어야합니다.")
    @Size(min = 4, message = "아이디는 4글자 이상이어야합니다.")
    private String id;

    @NotBlank(message = "비밀번호는 8글자 이상이어야합니다.")
    @Size(min = 8, message = "비밀번호는 8글자 이상이어야합니다.")
    private String pwd;

    @NotBlank(message = "이름을 입력하여야합니다.")
    private String name;

    @NotBlank(message = "레벨을 입력하여야합니다.")
    private String level;

    private String desc;

    //Jackson의 @JsonFormat,JsonProperty는 @RequestBody일 때만 적용
    //하지만 @ModelAttribute일 경우에는 @DateTimeFormat을 사용
    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

}

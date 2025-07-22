package com.example.test2.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchUserDTO {
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
}

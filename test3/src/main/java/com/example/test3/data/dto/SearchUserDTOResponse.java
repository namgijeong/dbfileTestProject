package com.example.test3.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
public class SearchUserDTOResponse {
    String id;

    String pwd;

    String name;

    String level;

    String desc;

    //Json 응답 사용을 위해
    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp regDate;


    public SearchUserDTOResponse() {
    }

    // select 절에 대상을 지정하는 것.
    @QueryProjection
    public SearchUserDTOResponse(String id, String pwd, String name, String level, String desc, Timestamp regDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.level = level;
        this.desc = desc;
        this.regDate = regDate;
    }
}

package com.example.test2.data.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 *  검색하는 정보를 담을 용도
 */
public class SearchUserDTO {
    private String id;

    private String pwd;

    private String name;

    private String level;

    private String desc;


    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp regDate;

    @JsonProperty("reg_date_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp regDateEnd;

    /**
     * entity를 dto로 변환한다.
     * @param "UserDtoBase를 상속한 Entity" 객체
     */
    public SearchUserDTO(String id, String pwd, String name, String level, String desc, Timestamp regDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.level = level;
        this.desc = desc;
        this.regDate = regDate;
    }
}

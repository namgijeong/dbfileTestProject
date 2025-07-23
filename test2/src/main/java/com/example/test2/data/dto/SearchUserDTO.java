package com.example.test2.data.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
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

    private Long pageNumber;

//    @JsonProperty("reg_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime regDate;
//
//    @JsonProperty("reg_date_end")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime regDateEnd;

    //날짜 문자열을 Java 날짜 객체로 변환
    //자바 날짜 객체를 문자열로 변환
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate regDate;


    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;


}

package com.example.test3.data.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
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

    //Jackson의 @JsonFormat,JsonProperty는 @RequestBody일 때만 적용
    //하지만 @ModelAttribute일 경우에는 @DateTimeFormat을 사용
    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    /*
       db에 timestamp로 넣기 위해
       String을 LocalDateTime으로 변환
   */
    @JsonProperty("start_reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startRegDate;

    /*
        db에 timestamp로 넣기 위해
        String을 LocalDateTime으로 변환
    */
    @JsonProperty("end_reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endRegDate;

    private Long pageNumber;

}

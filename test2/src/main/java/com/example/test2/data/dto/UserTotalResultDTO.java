package com.example.test2.data.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class UserTotalResultDTO {
    /*각 파일줄마다 성공인지,실패인지,몇번째 줄인지,내용에 대한 정보*/
    List<UserResultDTO> userResultDTOList;
    /*전체 파일 줄 개수 */
    int totalCount;
    /*전체 삽입 성공한 파일 줄 개수*/
    int successCount;
}

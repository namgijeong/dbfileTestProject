package com.example.test3.data.dto;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ProcessTotalResultDTO {
    /*각 파일줄마다 성공인지,실패인지,몇번째 줄인지,내용에 대한 정보*/
    List<ProcessResultDTO> processResultDTOList;

    /*전체 파일 줄 개수 */
    int totalCount;

    /*전체 삽입 성공한 파일 줄 개수*/
    int successCount;

}

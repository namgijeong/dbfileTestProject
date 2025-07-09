package com.example.test2.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ButtonBlockDTO {
    //현재 페이지 번호
    private Long currentPageNumber;
    //현재 페이지가 몇번째 블록인지 구하기
    private Long currentBlockNumber;
    //현재 블록에서 가장 첫번째 버튼 번호
    private Long currentBlockFirstNumber;
    //현재 블록에서 가장 마지막 버튼 번호 구하기
    private Long currentBlockLastNumber;
    //현재 블록에서 이전 블록이 존재
    private boolean previousBlock;
    //이전 블록이 존재한다면 < 는 몇번째 페이지인지
    private Long previousBlockPageNumber;
    // 현재 블록에서 다음 블록이 존재하는지
    private boolean nextBlock;
    //다음 블록이 존재한다면 > 는 몇번째 페이지인지
    private Long nextBlockPageNumber;

}

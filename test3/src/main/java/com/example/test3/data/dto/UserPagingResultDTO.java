package com.example.test3.data.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserPagingResultDTO<T> {
    //T userDTOList; 이렇게 하면 userDTOList가 단일 객체로 취급
    //하지만, 여러 개의 객체를 담으려면 List<T> 또는 Set<T>와 같은 컬렉션 타입을 사용
    /*10개씩 최신순으로 뽑아온 회원정보 리스트*/
    List<T> userDTOList;

    /*버튼 페이징 번호 정보 객체*/
    ButtonBlockDTO buttonBlockDTO;
}

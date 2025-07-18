package com.example.test2.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class UserPagingResultDTO {

    /*10개씩 최신순으로 뽑아온 회원정보 리스트*/
    List<UserDTO> userDTOList;

    /*버튼 페이징 번호 정보 객체*/
    ButtonBlockDTO buttonBlockDTO;
}

package com.example.test3.data.repository;

import java.util.List;

import com.example.test3.data.dto.UserDTO;
import com.example.test3.data.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Pageable;

import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;

public interface UserRepositoryCustom {
    /**
     * 페이징 기법 적용
     * @param dto
     * @param pageable
     * @return UserDTO List
     */
    List<UserDTO> searchUsers(SearchUserDTO dto, Pageable pageable);

    /**
     * 페이징 기법 아님
     * 검색결과 회원 리스트 뽑기
     * @param dto
     * @return UserDTO List
     */
    //List<UserDTO> searchUsers(SearchUserDTO dto);

    Long searchUsersCount(SearchUserDTO dto);

}

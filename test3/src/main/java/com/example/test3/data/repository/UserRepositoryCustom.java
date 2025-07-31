package com.example.test3.data.repository;

import java.util.List;

import com.example.test3.data.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;

public interface UserRepositoryCustom {
//    List<UserDTO> searchUsers(UserDTO dto, Pageable pageable);

    /**
     * dhtmlx8용
     * 검색결과 회원 리스트 뽑기
     * @param dto
     * @return UserDTO List
     */
    List<UserDTO> searchUsers(SearchUserDTO dto);

    Long searchUsersCount(UserDTO dto);
}

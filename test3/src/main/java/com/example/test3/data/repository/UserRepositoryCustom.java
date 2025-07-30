package com.example.test3.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;

public interface UserRepositoryCustom {
    List<SearchUserDTOResponse> searchUsers(SearchUserDTO dto, Pageable pageable);

    Long searchUsersCount(SearchUserDTO dto);
}

package com.example.test2.data.repository;

import com.example.test2.data.dto.SearchUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.test2.data.dto.SearchUserDTOResponse;

import java.util.List;

public interface UserRepositoryCustom {

    List<SearchUserDTOResponse> searchUsers(SearchUserDTO dto, Pageable pageable);

    Long searchUsersCount(SearchUserDTO dto);
}

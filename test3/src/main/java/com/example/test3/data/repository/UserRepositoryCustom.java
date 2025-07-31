package com.example.test3.data.repository;

import java.util.List;

import com.example.test3.data.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import com.example.test3.data.dto.SearchUserDTO;
import com.example.test3.data.dto.SearchUserDTOResponse;

public interface UserRepositoryCustom {
    List<UserDTO> searchUsers(UserDTO dto, Pageable pageable);

    Long searchUsersCount(UserDTO dto);
}

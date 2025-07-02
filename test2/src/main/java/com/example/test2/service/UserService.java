package com.example.test2.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserResultDTO;
import com.example.test2.data.dto.UserTotalResultDTO;

public interface UserService {
    /*아이디를 가지고 user 레코드를 찾는다.*/
    public UserDTO findUserById(String id);
    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    public UserTotalResultDTO userInsert(MultipartFile file);
    /*db table에 있는 user 레코드를 모두 조회한다.*/
    public List<UserDTO> findAll();
    public void deleteAll();
}

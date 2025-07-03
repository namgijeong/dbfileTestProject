package com.example.test2.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserTotalResultDTO;
import com.example.test2.exception.FailFileOpen;
import com.example.test2.exception.WrongFileExtension;
import com.example.test2.exception.EmptyUserTable;

public interface UserService {
    /*아이디를 가지고 user 레코드를 찾는다.*/
    public UserDTO findUserById(String id);
    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    public UserTotalResultDTO userInsert(MultipartFile file) throws FailFileOpen, WrongFileExtension;
    /*db table에 있는 user 레코드를 모두 조회한다.*/
    public List<UserDTO> findAll() throws EmptyUserTable;
    /*db table에 있는 user 레코드를 모두 지운다.*/
    public void deleteAll();
}

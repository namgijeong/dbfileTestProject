package com.example.test3.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.test3.data.dto.*;
import com.example.test3.exception.FailFileOpen;
import com.example.test3.exception.WrongFileExtension;

public interface UserService {
    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    public ProcessTotalResultDTO userInsert(MultipartFile file) throws FailFileOpen, WrongFileExtension;

    /*db table에 있는 user 레코드를 모두 조회한다.*/
    public List<UserDTO> findAll();

    /*db table에 있는 user 레코드를 모두 지운다.*/
    public void deleteAll();

    /*user 로그인을 수행한다.*/
    public ProcessResultDTO userLogin(String id, String pwd);

    /*
        등록 최신순 user 10명을 찾는다.
        등록된 전체 유저 개수를 세서 페이징 버튼들 블록 처리
    */
//    public UserPagingResultDTO<UserDTO> select10Users(long pageNumber);

    /**
     * dhtmlx8용
     * @return UserDTO LIST
     */
    public UserPagingResultDTO<UserDTO> selectAllUsers();

    // 검색 dto를 가지고 회원들을 찾는다.
    public UserPagingResultDTO<UserDTO> selectUsersBySearchUserDTO(SearchUserDTO searchUserDTO);
}
